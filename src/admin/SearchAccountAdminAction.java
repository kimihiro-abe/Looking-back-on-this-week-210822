package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDAO;
import tool.Action;


public class SearchAccountAdminAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		int sessionAdmin = (Integer)session.getAttribute("admin");
		int sessionAcode = (Integer)session.getAttribute("accountCode");

		String keyword=request.getParameter("keyword");
		int select = Integer.parseInt(request.getParameter("select"));

		AccountDAO dao = new AccountDAO();
		List<AccountBean> accountListAdmin = dao.searchAccountList(keyword,select,sessionAdmin,sessionAcode);
		request.removeAttribute("accountListAdmin");
		request.setAttribute("accountListAdmin", accountListAdmin);

		//System.out.println(reviewListAdmin.size());

		return "accountListAdmin.jsp";
	}

}

