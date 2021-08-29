package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MediaBean;
import dao.MediaDAO;
import tool.Action;

public class CheckEditMediaDetailAdminAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();

		//まず、映像情報詳細の部分のデータをDBから取ってくる。
		String mediaCode = request.getQueryString(); //getQuery部分の映像コード値を流し込む
//		String reviewCode = request.getParameter("reviewCode"); //getParameterに映像コードを流し込む
//		String reviewContent = request.getParameter("reviewContent");
		//request.getAttribute("mediaCode"); //${ml.mediaCode}

		//まず内容のアップデートを行う。
//		ReviewDAO dao1 = new ReviewDAO();
//		int line  = dao1.update(reviewCode, reviewContent); //作品情報をDBからひっぱってくる

		//更新する映像情報詳細データをDBから持ってくる。

		MediaDAO dao = new MediaDAO();

		//2つ目の引数「１」はGhost化したものも取ってこれるモードをセレクトする数字。
		List<MediaBean> editMediaDetail = dao.detailAdmin(mediaCode);
		request.removeAttribute("editMediaDetail");
		request.setAttribute("editMediaDetail", editMediaDetail);

		return "editMediaDetail.jsp";

	}

}