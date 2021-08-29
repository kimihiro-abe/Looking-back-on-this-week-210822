package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SearchReviewBean;
import dao.ReviewDAO;
import tool.Action;


public class SearchReviewAdminAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//HttpSession session = request.getSession();

		String keyword=request.getParameter("keyword");
		int select = Integer.parseInt(request.getParameter("select"));

		ReviewDAO dao = new ReviewDAO();
		List<SearchReviewBean> reviewListAdmin = dao.searchReviewAdmin(keyword,select);
		request.setAttribute("reviewListAdmin", reviewListAdmin);

		//System.out.println(reviewListAdmin.size());

		return "reviewListAdmin.jsp";
	}
}
