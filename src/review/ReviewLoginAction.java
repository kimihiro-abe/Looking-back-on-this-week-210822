package review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class ReviewLoginAction extends Action{
	
		public String execute(
				HttpServletRequest request,HttpServletResponse response
			)throws Exception{

				HttpSession session=request.getSession();//getSessionメゾットでセッションを開始
				
				if(session.getAttribute("account")==null) {
					return "review-error-login.jsp";
				}
					return "reviewInsert-in.jsp";
				}
		}
