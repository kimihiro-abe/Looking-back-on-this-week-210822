package loginlogout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import tool.Action;


public class LoginAction extends Action{

	private AccountBean account;
	private int accountCode;
	private String nickName;
	private String loginID;
	private int admin;

public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();


		String login=request.getParameter("loginID");
		String password=request.getParameter("password");


		AccountDAO dao = new AccountDAO();
		AccountBean account = dao.login(login, password); //login時にユーザー情報全部持ってきてる

		try {
		accountCode = account.getAccountCode();
		nickName = account.getNickName();
		loginID = account.getLoginID();
		admin = account.getAdmin();

		}
		catch (NullPointerException e) {

			//login-error
			String form_action = "<form action=\"../loginlogout/login-in.jsp\">";

			//備忘録メモ（あべ）：${account.nickName}をいると、そのまま出力されて不可能。
			String result = "<font color=\"red\"><p>ログイン名またはパスワードが違います。</p><br></font>";

			//String message1 = "新作が入荷されたので早速チェックへGoGo！";

			String url = "<meta http-equiv=\"refresh\" content=\"3;URL=../loginlogout/login-in.jsp\">";

			String submit = "<p><input type=\"submit\" value=\"ログイン画面に戻る\" ></p>\n" +
			"(３秒後に自動で遷移もします。）";

			request.setAttribute("form_action", form_action);
			request.setAttribute("result", result);
			//request.setAttribute("message1", message1);
			request.setAttribute("url", url);
			request.setAttribute("submit", submit);

			return "transition_loginlogout.jsp";

		}
		if (account != null) {
			session.setAttribute("account", account); //ユーザー情報表示等で使う
			session.setAttribute("accountCode", accountCode); //様々なActionで使うのに保持
			session.setAttribute("nickName", nickName); //様々なActionで使うのに保持
			session.setAttribute("loginID", loginID); //様々なActionで使うのに保持
			session.setAttribute("admin", admin); //様々なActionで使うのに保持

				return "login-out.jsp";

		}
		//login-error
		String form_action = "<form action=\"../loginlogout/login-in.jsp\">";

		//備忘録メモ（あべ）：${account.nickName}のまま出力されて不可能。
		String result = "<font color=\"red\"><p>ログイン名またはパスワードが違います。</p><br></font>";

		//String message1 = "新作が入荷されたので早速チェックへGoGo！";

		String url = "<meta http-equiv=\"refresh\" content=\"3;URL=../loginlogout/login-in.jsp\">";

		String submit = "<p><input type=\"submit\" value=\"ログイン画面に戻る\" ></p>\n" +
		"(３秒後に自動で遷移もします。）";

		request.setAttribute("form_action", form_action);
		request.setAttribute("result", result);
		//request.setAttribute("message1", message1);
		request.setAttribute("url", url);
		request.setAttribute("submit", submit);

		return "transition_loginlogout.jsp";
	}
}

