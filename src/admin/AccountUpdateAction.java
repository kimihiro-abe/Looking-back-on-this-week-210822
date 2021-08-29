package admin;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import tool.Action;

public class AccountUpdateAction extends Action{

	private String mode;
	private String accountCode;
	private String QueryString;
	final private int select_all=1; //DBにある全データを選択
	final private int select_nonGhost=2; //ゴースト化してないデータを選択
	final private int select_Ghost=3;//ゴースト化したデータのみを選択
	int line = 0;

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {


		HttpSession session = request.getSession();

		//accountListAdmin.jspからのデータを受け取って、if-elseで条件分けする。
		QueryString = request.getQueryString();//クエリ(?以降の部分、accountCode+[mode]にしてる)を取得。
		String QueryStringSrc[] = QueryString.split("\\+");//accountCodeと[mode]を分割して配列に入れる。
		accountCode = QueryStringSrc[0];//accountCodeを代入
		mode = QueryStringSrc[1];//modeを代入

		if(mode.equals("delete")) {//削除（Ghost化）する

			//現在のアカウントデータを取ってくる
			AccountDAO dao = new AccountDAO();
			AccountBean currentAc = dao.searchDetail(accountCode, select_nonGhost);//
			String loginID = currentAc.getLoginID();
			String nickName = currentAc.getNickName();

			//削除処理
			AccountDAO dao1 = new AccountDAO();
			line = dao1.delete(accountCode,loginID ,nickName); //削除（Ghost化）処理部分

			//更新処理が完了したら、そのデータをDBから取得する。
			AccountDAO dao2 = new AccountDAO();//ghost化したことを確認する為、変更後レビューデータを取得する。
			List<AccountBean> delresAccountDetail = dao2.searchAfterDetail(accountCode, select_Ghost);//
			request.removeAttribute("delresAccountDetail");//delres = delete restoreの略
			request.setAttribute("delresAccountDetail", delresAccountDetail);//delres = delete restoreの略
			//return "medialist.jsp";

			return "dispRestoredDeleteAccountDetail_Result.jsp";

		}
		else if(mode.equals("restore")) {//復元（Ghost化からの復元）

			//現在のアカウントデータを取ってくる
			AccountDAO dao = new AccountDAO();
			AccountBean currentAc = dao.searchDetail(accountCode, select_Ghost);//
			String loginID = currentAc.getLoginID();
			String nickName = currentAc.getNickName();

			//復元処理
			AccountDAO dao1 = new AccountDAO();
			try {
			line = dao1.restore(accountCode,loginID ,nickName); //復元（Ghost化からの復元）処理部分
			}
			catch(SQLIntegrityConstraintViolationException e) {
			//名前重複時にエラーが出る問題をどうにか出来るかも

				String message = "先程選択したアカウントを復元した場合、<br>\r\n" +
						"loginIDあるいはニックネームが重複してしまうため復元不可能です。<br>\r\n" +
						"現役のアカウント保持者がloginIDとニックネームの保持優先権を持つので、<br>\r\n" +
						"過去名称でのアカウント復元を諦めてください。";
				request.setAttribute("message", message);

				return "SearchAccountAdmin.action";
			}
			catch(NumberFormatException e) {
				String message = "先程選択したアカウントを復元した場合、<br>\r\n" +
						"loginIDあるいはニックネームが重複してしまうため復元不可能です。<br>\r\n" +
						"現役のアカウント保持者がloginIDとニックネームの保持優先権を持つので、<br>\r\n" +
						"過去名称でのアカウント復元を諦めてください。";
				request.setAttribute("message", message);

				return "SearchAccountAdmin.action";
			}

			//更新処理が完了したら、そのデータをDBから取得する。
			AccountDAO dao2 = new AccountDAO();//ghost化したことを確認する為、変更後レビューデータを取得する。
			List<AccountBean> delresAccountDetail = dao2.searchAfterDetail(accountCode, select_nonGhost);//
			request.removeAttribute("delresAccountDetail");//delres = delete restoreの略
			request.setAttribute("delresAccountDetail", delresAccountDetail);//delres = delete restoreの略
			//return "medialist.jsp";

			return "dispRestoredDeleteAccountDetail_Result.jsp";

		}
		else if(mode.equals("change")) { // 編集処理（権限の付与剥奪）

			//データだけ取って、変更処理は次のJSPに飛ばす。
			AccountDAO dao = new AccountDAO();//ghost化したことを確認する為、変更後レビューデータを取得する。
			List<AccountBean> currentAc = dao.searchAfterDetail(accountCode, select_nonGhost);//
			//request.removeAttribute("currentAc");//delres = delete restoreの略
			request.removeAttribute("currentAc");//delres = delete restoreの略
			request.setAttribute("currentAc", currentAc);//delres = delete restoreの略
			//return "medialist.jsp";

			return "accountAuthorityAdmin.jsp";

		}

		//↓入れないとエラーでる。↑の３つしか行きようがないので下記で問題なし。
		return "";

	}

}