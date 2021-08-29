package account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import tool.Action;


public class AccountChkidAction extends Action{
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception{

		HttpSession session=request.getSession();

//		Integer.parseInt(request.getParameter("accountCode"));
		String loginID=request.getParameter("loginID");
		String password=request.getParameter("password");
		String nickName=request.getParameter("nickName");

		request.setAttribute("loginID", loginID);
		request.setAttribute("password", password);
		request.setAttribute("nickName", nickName);

		//ログインID、パスワード、ニックネームのどれかが空白だった場合のエラー
		if(loginID == "" || password == "" || nickName == "" ) {

			String txt = "ログインID、パスワード、ニックネームのいずれかが空欄です。";
			request.removeAttribute("message");
			request.setAttribute("message", txt);


			return "account-add-confirm.jsp"; //

		} else {

			//ログインIDのルール指定：英数字で6～15文字以内
			//Pattern pid = Pattern.compile("^[a-zA-Z0-9あ-を]{6,15}$");
			Pattern pid = Pattern.compile("^[a-zA-Z0-9]{6,15}$");
			Matcher mid = pid.matcher(loginID);

			if(mid.find() == true) {

				//パスワードのルール指定：英数字で4～10文字以内
				//Pattern ppass = Pattern.compile("^[a-zA-Z0-9あ-を]{4,10}$");
				Pattern ppass = Pattern.compile("^[a-zA-Z0-9]{4,10}$");
				Matcher mpass = ppass.matcher(password);

				if(mpass.find() == true) {
					int line = 0;

					AccountDAO dao1 = new AccountDAO();
					line = dao1.checkLoginID(loginID);			//ログインIDの重複チェックを実施

					if(line == 0) {
						int line2 = 0;
						AccountDAO dao2 = new AccountDAO();
						line2 = dao2.checkNickName(nickName);	//ニックネームの重複チェックを実施

						if(line2 == 0) {
							AccountBean account = new AccountBean();
							account.setLoginID(loginID);
							account.setPassword(password);
							account.setNickName(nickName);

							//これから登録する情報を保持
							session.setAttribute("conf", account);

							//各種チェック問題ない場合、登録確認画面に進む
							return "account-add-confirm.jsp";

						} else {
							//ニックネームがすでに登録ある場合のエラーに進む
							String txt = "すでに同じニックネームが登録されているので、変更をお願いいいたします。";
							request.removeAttribute("message");
							request.setAttribute("message", txt);

							return "account-add.jsp"; // "account-add-error-chk-nickname.jsp"

						}
					} else {
						//ログインIDがすでに登録ある場合のエラーに進む
						String txt = "すでに同じIDが登録されているので、変更をお願いいいたします。";
						request.removeAttribute("message");
						request.setAttribute("message", txt);
						return "account-add.jsp"; // "account-add-error-chk-loginid.jsp"

						}
				} else {
						//パスワードの文字列が英数字以外、文字数に一致しない場合のエラーに進む
						String txt = "パスワードは半角英数、4～10文字の範囲内で設定してください。";
						request.removeAttribute("message");
						request.setAttribute("message", txt);
						return "account-add.jsp"; // "account-add-error-insert-pass.jsp"

					}
			}else {
						//ログインIDの文字列が英数字以外、文字数に一致しない場合のエラーに進む
						String txt = "ログインIDは半角英数、6～15文字の範囲内で設定してください。";
						request.removeAttribute("message");
						request.setAttribute("message", txt);
						return "account-add.jsp"; // "account-add-error-insert-loginid.jsp"
			}
		}
	}
}

