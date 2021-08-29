package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MediaBean;
import dao.MediaDAO;
import tool.Action;


public class SearchMediaAdminAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//HttpSession session = request.getSession();

		String keyword=request.getParameter("keyword");
		int select = Integer.parseInt(request.getParameter("select"));

		MediaDAO dao = new MediaDAO();
		List<MediaBean> mediaListAdmin = dao.searchMediaAdmin(keyword,select);
		request.setAttribute("mediaListAdmin", mediaListAdmin);

		return "mediaListAdmin.jsp";
	}

}

