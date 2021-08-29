package account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import tool.Action;


public class AccountDelAction extends Action {

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception{

		HttpSession session = request.getSession();			//セッション破棄するために必要なもよう

		int accountCode = (int) session.getAttribute("accountCode");
		String loginID = (String) session.getAttribute("loginID");
		String nickName = (String) session.getAttribute("nickName");

//chapter15 Deleteを参照 shimizu :try/catchありのほうを採用してみました。
		try {
			int line = 0;

			//account-del-confirm.jspから値を取ってくる。loginIDとnickNameはセッション情報から取る
			//int accountCode=Integer.parseInt(request.getParameter("accountCode"));

			AccountBean ab = new AccountBean();
			ab.setAccountCode(accountCode);
			ab.setLoginID(loginID);
			ab.setNickName(nickName);

			AccountDAO dao = new AccountDAO();
			line = dao.delete(ab);

			if (line>0) { //変更がかかったのが１つあるはずのでtrue
				if(session.getAttribute("account")!=null) {
					session.invalidate();					//セッションごと破棄(ログイン状態を解除)
					//System.out.println("line: "+line);
					return "account-del-done.jsp"; //アカウントの消去に成功した時
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "account-del-error.jsp"; //例外処理、時間ないので↓の同じ場所にとばす

		}

		// DBに変更がかからなかった時
		return "account-del-error.jsp";
	}
}

/*
*
//CartRemoveActionを参照
		int accountCode=Integer.parseInt(request.getParameter("accountCode"));

		AccountBean p=new AccountBean();
		p.setAccountCode(accountCode);


		AccountDAO dao=new AccountDAO();
		int line=dao.delete(p);

//			if(line!=accountCode) {
		if (line>0) {
		return "account-del-done.jsp";

		}
		return null "account-del-error.jsp";;
	}
}
*/