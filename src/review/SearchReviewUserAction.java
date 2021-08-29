package review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SearchReviewBean;
import dao.ReviewDAO;
import tool.Action;

public class SearchReviewUserAction extends Action{


	public String execute(
				HttpServletRequest request,HttpServletResponse response
			)throws Exception{

				HttpSession session=request.getSession();

				int accountCode = (Integer) session.getAttribute("accountCode");

				ReviewDAO dao = new ReviewDAO();
				List<SearchReviewBean>	reviewUserList=dao.userSearch(accountCode);

				request.setAttribute("reviewUserList", reviewUserList);

				//if(reviewUserList==null) {
				//reviewUserList=new ArrayList<ReviewBean>();
				//session.setAttribute("reviewUserList",reviewUserList);
				//}

				//for (ReviewBean r:review) {
					//if(r.getProduct().getId()==id) {
						//r.setCount(r.getCount()+1);
						//return "searchReviewUser.jsp";
			//}
		//}
				//List<Product> list=(List<Product>)session.getAttribute("list");
				//for(Product p:list) {
					//if(p.getId()==id) {
						//Item i=new Item();
						//i.setProduct(p);
						//i.setCount(1);
						//cart.add(i);
					//}
			//}
				return "searchReviewUser.jsp";
		}
	}
