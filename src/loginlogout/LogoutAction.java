package loginlogout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;


public class LogoutAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("account") != null) {
			session.removeAttribute("account");
			session.removeAttribute("accountCode");
			session.removeAttribute("nickName");
			session.removeAttribute("loginID");
			session.removeAttribute("admin");

			//login-success
			String form_action = "<form action=\"../index.jsp\">";

			//備忘録メモ（あべ）：${account.nickName}のまま出力されて不可能。
			String result = "<p>ログアウトしました。</p><br>";

			String message1 = "またのご利用おまちしております！";

			String url = "<meta http-equiv=\"refresh\" content=\"3;URL=../index.jsp\">";

			String submit = "<p><input type=\"submit\" value=\"トップ画面に戻る\" ></p>\n" +
			"(３秒後に自動で遷移もします。）";

			request.setAttribute("form_action", form_action);
			request.setAttribute("result", result);
			request.setAttribute("message1", message1);
			request.setAttribute("url", url);
			request.setAttribute("submit", submit);

			return "transition_loginlogout.jsp";

		}

		//login-error
		String form_action = "<form action=\"../index.jsp\">";

		//備忘録メモ（あべ）：${account.nickName}のまま出力されて不可能。
		String result = "<font color=\"red\"><p>すでにログアウトしています。</p><br></font>";

		//String message1 = "新作が入荷されたので早速チェックへGoGo！";

		String url = "<meta http-equiv=\"refresh\" content=\"3;URL=../index.jsp\">";

		String submit = "<p><input type=\"submit\" value=\"トップ画面に戻る\" ></p>\n" +
		"(３秒後に自動で遷移もします。）";

		request.setAttribute("form_action", form_action);
		request.setAttribute("result", result);
		//request.setAttribute("message1", message1);
		request.setAttribute("url", url);
		request.setAttribute("submit", submit);

		return "transition_loginlogout.jsp";

	}

}
