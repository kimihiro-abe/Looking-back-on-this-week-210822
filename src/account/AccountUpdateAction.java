package account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import dao.ReviewDAO;
import tool.Action;

public class AccountUpdateAction extends Action {

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		HttpSession session=request.getSession();

		int accountCode = (Integer) session.getAttribute("accountCode");
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");

		AccountBean ab = new AccountBean();
		ab.setAccountCode(accountCode);
		ab.setLoginID(loginID);
		ab.setPassword(password);
		ab.setNickName(nickName);

		try {
			//上記の更新した情報でDBを更新
			AccountDAO dao1 = new AccountDAO();
			int line = dao1.update(ab);

			if(line == 1) { //DBへの追加に成功した時

				//現セッション情報を破棄する前に、reviewのニックネームを書き換える。
				//上記のニックネームと破棄前のセッション情報のaccountCodeを使う。
				//nickNameUpdate(int accountCode, String nickName)
				ReviewDAO daoR = new ReviewDAO();
				int line2 = daoR.nickNameUpdate(accountCode, nickName);

				if(line !=1) {
					String txt1 = "なんらかの例外エラー";
					String txt2 = "データベース更新が出来ず、レビューでの投稿名称を変更出来ませんでした。";
					String txt3 = "account-update.jsp";
					String txt4 = "アカウント情報変更画面に戻る";
					request.setAttribute("message1", txt1);
					request.setAttribute("message2", txt2);
					request.setAttribute("message3", txt3);
					request.setAttribute("message4", txt4);

					return "unexpectedError-indication.jsp";

				}

				//現セッション情報の破棄
				session.removeAttribute("account");
				session.removeAttribute("accountCode");
				session.removeAttribute("nickName");
				session.removeAttribute("loginID");
				session.removeAttribute("admin");
				session.removeAttribute("conf");//アカウント情報変更時に使用したもの

				//更新した情報をDBから取ってくる
				AccountDAO dao2 = new AccountDAO();

				//loginメソッドを使ってセッションデータ用にデータを取る
				AccountBean account = dao2.login(loginID, password);
				accountCode = account.getAccountCode();
				nickName = account.getNickName();
				loginID = account.getLoginID();
				int admin = account.getAdmin();

				session.setAttribute("account", account); //ユーザー情報表示等で使う
				session.setAttribute("accountCode", accountCode); //様々なActionで使うのに保持
				session.setAttribute("nickName", nickName); //様々なActionで使うのに保持
				session.setAttribute("loginID", loginID); //様々なActionで使うのに保持
				session.setAttribute("admin", admin); //様々なActionで使うのに保持


				return "account-update-done.jsp"; //
				}
			}
			catch(Exception e) { //なんらかの例外があった場合
				String txt1 = "なんらかの例外エラー";
				String txt2 = "なんらかの理由でデータベース更新が出来ませんでした。";
				String txt3 = "account-update.jsp";
				String txt4 = "アカウント情報変更画面に戻る";
				request.setAttribute("message1", txt1);
				request.setAttribute("message2", txt2);
				request.setAttribute("message3", txt3);
				request.setAttribute("message4", txt4);

				e.printStackTrace();//デバッグようにしこんでおく
				return "unexpectedError-indication.jsp";
			}
		//DBへの書き込みに失敗した場合
		String txt1 = "データベース更新エラー";
		String txt2 = "なんらかの理由でデータベース更新が出来ませんでした。";
		String txt3 = "account-update.jsp";
		String txt4 = "アカウント情報変更画面に戻る";
		request.setAttribute("message1", txt1);
		request.setAttribute("message2", txt2);
		request.setAttribute("message3", txt3);
		request.setAttribute("message4", txt4);


		return "unexpectedError-indication.jsp"; //"account-update-error-insert.jsp"
	}
}
