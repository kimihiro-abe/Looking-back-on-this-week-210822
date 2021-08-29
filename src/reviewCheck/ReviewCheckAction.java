package reviewCheck;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ReviewBean;
import dao.ReviewDAO;
import tool.Action;

public class ReviewCheckAction extends Action{
	public String execute(
		HttpServletRequest request,HttpServletResponse response
	)throws Exception{

		String keyword=request.getParameter("keyword");

		ReviewDAO dao=new ReviewDAO();
		List<ReviewBean> reviewList=dao.search(keyword);

		request.setAttribute("reviewList",reviewList);

		return "reviewlist.jsp";
	}
}