package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import tool.Action;

public class AccountAuthorityChangeAction extends Action{

	private String mode;
	private String QueryString;
	private int targetAccountCode;
	private int targetAdmin;
	final private int select_all=1; //DBにある全データを選択
	final private int select_nonGhost=2; //ゴースト化してないデータを選択
	final private int select_Ghost=3;//ゴースト化したデータのみを選択

	private AccountBean AccountAfterChange;
	private int accountCode;
	private String nickName;
	private String loginID;
	private int admin;

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		int sessionAdmin = (Integer)session.getAttribute("admin");//自身のadmin値を取得

		//accountAuthorityAdmin.jspから値を取ってくる
		targetAccountCode = Integer.parseInt(request.getParameter("targetAccountCode"));//accountCodeを代入、変更対象のaccountCode
		targetAdmin = Integer.parseInt(request.getParameter("targetAdmin"));//adminを代入、変更対象のadmin
		mode = request.getParameter("mode");

		if(mode == null) {
			String message = "※付与あるいは剥奪を選択してから『変更』を押してください";
			request.setAttribute("message", message);
			return "AccountUpdate.action?" + targetAccountCode +"+change";
		}


		//付与剥奪は下記6パターン
		//
		//自身のadmin値が２、対象のadmin値が１の場合：付与。権限２へ
		//自身のadmin値が２、対象のadmin値が２の場合：剥奪。権限１へ
		//自身のadmin値が３、対象のadmin値が１の場合：付与。権限２へ
		//自身のadmin値が３、対象のadmin値が２の場合：付与。権限３へ
		//自身のadmin値が３、対象のadmin値が２の場合：剥奪。権限１へ
		//自身のadmin値が３、対象のadmin値が３の場合：剥奪。権限２へ

		AccountDAO dao1 = new AccountDAO();

		//自身のadmin値が２の場合
		if(sessionAdmin == 2) {

			//対象のadmin値が１の場合：付与。権限２へ
			if(targetAdmin == 1 && mode.equals("assignment")) {
				targetAdmin = 2;//権限を１から２へと昇格

				//DB変更処理部分
				int line  = dao1.authorityChangeAdmin(targetAccountCode, targetAdmin);
				String tAdmin_String = String.valueOf(targetAccountCode);

				//変更後情報をDBから取ってきて、setAttributeする。
				List<AccountBean> AccountAfterChange = dao1.searchAfterDetail(tAdmin_String,1);

				//権限変更完了画面で使用するデータをとってくる
				session.setAttribute("AccountAfterChange", AccountAfterChange);

				//『アカウントの管理権限変更　完了画面』にメッセージを表示する。
				String message ="※権限を１から２へと昇格。管理者となりました。";
				request.setAttribute("message", message);

				return "dispAccountAuthorityChange_Result.jsp";
			}

			//対象のadmin値が２の場合：剥奪。権限１へ
			else if(targetAdmin == 2 && mode.equals("deprivation")) {
				targetAdmin = 1;//権限を２から１へと降格

				//DB変更処理部分
				int line  = dao1.authorityChangeAdmin(targetAccountCode, targetAdmin);
				String tAdmin_String = String.valueOf(targetAccountCode);

				//変更後情報をDBから取ってきて、setAttributeする。
				List<AccountBean> AccountAfterChange = dao1.searchAfterDetail(tAdmin_String,1);

				//権限変更完了画面で使用するデータをとってくる
				session.setAttribute("AccountAfterChange", AccountAfterChange);

				//『アカウントの管理権限変更　完了画面』にメッセージを表示する。
				String message ="※権限を２から１へと降格。ユーザーとなりました。";
				request.setAttribute("message", message);

				return "dispAccountAuthorityChange_Result.jsp";
			}
		}

		//自身のadmin値が３の場合
		else if(sessionAdmin == 3) {
			//対象のadmin値が１の場合：付与。権限２へ
			if(targetAdmin == 1 && mode.equals("assignment")) {
				targetAdmin = 2;//権限を１から２へと昇格
				int line  = dao1.authorityChangeAdmin(targetAccountCode, targetAdmin);
				String tAdmin_String = String.valueOf(targetAccountCode);

				//変更後情報をDBから取ってきて、setAttributeする。
				List<AccountBean> AccountAfterChange = dao1.searchAfterDetail(tAdmin_String,1);

				//権限変更完了画面で使用するデータをとってくる
				session.setAttribute("AccountAfterChange", AccountAfterChange);

				//『アカウントの管理権限変更　完了画面』にメッセージを表示する。
				String message ="※権限を１から２へと昇格。管理者となりました。";
				request.setAttribute("message", message);

				return "dispAccountAuthorityChange_Result.jsp";
			}

			//対象のadmin値が２の場合：付与。権限３へ
			else if(targetAdmin == 2 && mode.equals("assignment")) {
				targetAdmin = 3;//権限を２から３へと昇格
				int line  = dao1.authorityChangeAdmin(targetAccountCode, targetAdmin);
				String tAdmin_String = String.valueOf(targetAccountCode);

				//変更後情報をDBから取ってきて、setAttributeする。
				List<AccountBean> AccountAfterChange = dao1.searchAfterDetail(tAdmin_String,1);

				//権限変更完了画面で使用するデータをとってくる
				session.setAttribute("AccountAfterChange", AccountAfterChange);

				//『アカウントの管理権限変更　完了画面』にメッセージを表示する。
				String message ="※権限を２から３へと昇格。スーパー管理者となりました。";
				request.setAttribute("message", message);

				return "dispAccountAuthorityChange_Result.jsp";
			}

			//対象のadmin値が２の場合：剥奪。権限１へ
			else if(targetAdmin == 2 && mode.equals("deprivation")) {
				targetAdmin = 1;//権限を２から１へと降格
				int line  = dao1.authorityChangeAdmin(targetAccountCode, targetAdmin);
				String tAdmin_String = String.valueOf(targetAccountCode);

				//変更後情報をDBから取ってきて、setAttributeする。
				List<AccountBean> AccountAfterChange = dao1.searchAfterDetail(tAdmin_String,1);

				//権限変更完了画面で使用するデータをとってくる
				session.setAttribute("AccountAfterChange", AccountAfterChange);

				//『アカウントの管理権限変更　完了画面』にメッセージを表示する。
				String message ="※権限を２から１へと降格。ユーザーとなりました。";
				request.setAttribute("message", message);


				return "dispAccountAuthorityChange_Result.jsp";

			}//対象のadmin値が３の場合：剥奪。権限２へ
			else if(targetAdmin == 3 && mode.equals("deprivation")) {
				targetAdmin = 2;//権限を３から２へと降格
				int line  = dao1.authorityChangeAdmin(targetAccountCode, targetAdmin);
				String tAdmin_String = String.valueOf(targetAccountCode);

				//変更後情報をDBから取ってきて、setAttributeする。
				List<AccountBean> AccountAfterChange = dao1.searchAfterDetail(tAdmin_String,1);

				//権限変更完了画面で使用するデータをとってくる
				session.setAttribute("AccountAfterChange", AccountAfterChange);

				//『アカウントの管理権限変更　完了画面』にメッセージを表示する。
				String message ="※権限を３から２へと降格。管理者となりました。";
				request.setAttribute("message", message);

				return "dispAccountAuthorityChange_Result.jsp";
			}

		}
		return "";

	}

}