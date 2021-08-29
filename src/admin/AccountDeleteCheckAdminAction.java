package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import tool.Action;


public class AccountDeleteCheckAdminAction extends Action{

	int count;

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();

		//セッション情報から自分のアカウント情報で必要なものを持ってくる
		int admin = (int) session.getAttribute("admin");

		//前提：admin権限2の人の場合、admin権限3の人は管理画面上に表示されない仕様。
		//故に上位権限の人は削除出来ないので、自分と同格権限者の数をDBから取ってくる。

		try {
		//このactionはadmin権限でないと見ることの出来ないページに存在し、
		//自分のadmin値を引数にしている関係上、最低でも一人以上は確定する。
		AccountDAO dao = new AccountDAO();
		count = dao.deleteCheckAdmin(admin);
		System.out.println("count: " + count);

		}
		//例外処理
		catch(Exception e) {
			System.out.println("ccountDeleteCheckAdminAction(java)_printStackTrace: ");
		    e.printStackTrace();

		    String message = "<font color=\"red\">例外的エラーが起きたのでmypageのトップに戻りました。</font>";
		    request.removeAttribute("message");
		    request.setAttribute("message", message);

			return "../mypage/mypage.jsp";
		}

		//現アカウントの管理者が一人の場合：削除不可能通知処理
		if(count==1) {

			return "dispAccountDeleteCheckAdmin.jsp"; //admin階層のjsp
		}
		//削除処理に進む。
		else {
			return "../account/account-del-confirm.jsp"; //account階層のjsp(ユーザーと併用する)
		}
	}
}

//>管理者数を引っ張ってくる
//select count(*) from account where admin = 2 and where admin =3;