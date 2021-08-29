package account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import tool.Action;

public class AccountAddAction extends Action {

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		HttpSession session=request.getSession();

		//ユーザー新規登録

		try {
			int line = 0;

			AccountBean accountBean = (AccountBean)session.getAttribute("conf");
			AccountDAO dao = new AccountDAO();
			line = dao.insert(accountBean);

			if(line == 0) { //DBへの追加に失敗した時
				String txt1 = "データベース更新エラー";
				String txt2 = "なんらかの理由でデータベース更新が出来ませんでした。";
				String txt3 = "account-add.jsp";
				String txt4 = "新規ユーザー登録画面に戻る";
				request.setAttribute("message1", txt1);
				request.setAttribute("message2", txt2);
				request.setAttribute("message3", txt3);
				request.setAttribute("message4", txt4);

				return "unexpectedError-indication.jsp";
				}
		}
		//Unicode絵文字の例外対応：ここにいれたのでは機能しない.
//		catch(SQLException e) {
//			e.printStackTrace();
//			String message = "Unicodeの絵文字は使えません！＞＜";
//			request.setAttribute("message", message);
//
//			return "account-add.jsp";
//		}
		catch(Exception e ) { //なんらかの例外があった場合
			String txt1 = "なんらかの例外エラー";
			String txt2 = "なんらかの理由でデータベース更新が出来ませんでした。";
			String txt3 = "account-add.jsp";
			String txt4 = "新規ユーザー登録画面に戻る";
			request.setAttribute("message1", txt1);
			request.setAttribute("message2", txt2);
			request.setAttribute("message3", txt3);
			request.setAttribute("message4", txt4);

			e.printStackTrace();//デバッグようにしこんでおく
			return "unexpectedError-indication.jsp";
		}



		//アカウントの新規登録に成功
		return "account-add-done.jsp";
	}
}
	/*
 *

		//新規登録時あとに再度ログインをやる気もするから、ここでセッション情報取らなくていいかも。
		//というのもログイン時にセッション情報をとっているので。。
		//HttpSession session=request.getSession();

		String loginID=request.getParameter("loginID"); //入力されたログインID
		String password=request.getParameter("password"); //入力されたパスワード
		String nickName=request.getParameter("nickName"); //入力されたニックネーム
		int admin=1; //ユーザーは問答無用で１を設定
		int ghost=0; //新規登録自は0でok

		AccountBean p=new AccountBean();
		p.setLoginID(loginID);
		p.setPassword(password);
		p.setNickName(nickName);
		p.setAdmin(admin);
		p.setGhost(ghost);

		//どれかが空だったらという意味。レビュー対応あとにもっと細かい処理をする。
		if(loginID.isEmpty() || password.isEmpty() || nickName.isEmpty()) {
			return "account-add-error-empty.jsp"; //エラーが出たときに画面遷移しない方法にした方が良いかも（希望的なんちゃら
		}

		AccountDAO dao=new AccountDAO();
		int line=dao.insert(p);

 		if(line==0) { //ここ、「line>0」みたいな記載あったけど、アカウント追加されたら増えるから動作正常でもエラー画面に遷移してたので直しました。
			return "account-add-error-insert.jsp";
		}
		return "account-add-done.jsp";
	}
}
 */

