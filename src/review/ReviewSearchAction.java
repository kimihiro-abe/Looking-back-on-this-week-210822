package review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ReviewBean;
import dao.ReviewDAO;
import tool.Action;


public class ReviewSearchAction extends Action{
	
	public String execute(
		HttpServletRequest request,HttpServletResponse response
	)throws Exception{
		
		HttpSession session = request.getSession();

		String keyword=request.getParameter("keyword");
		if(keyword==null) keyword="";

		ReviewDAO dao=new ReviewDAO();
		List<ReviewBean> list=dao.search(keyword);

		session.setAttribute("list",list);
		return "reviewSearchItiran.jsp";
	}
}