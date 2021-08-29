package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MediaBean;
import dao.MediaDAO;
import tool.Action;

public class CheckReviewAdminAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();

		//まず、映像情報詳細の部分のデータをDBから取ってくる。
		String mediaCode = request.getQueryString(); //getQuery部分の映像コード値を流し込む
		//String mediaCode = request.getParameter("mediaCode"); //getParameterに映像コードを流し込む
		//request.getAttribute("mediaCode"); //${ml.mediaCode}

		MediaDAO dao = new MediaDAO();
		List<MediaBean> mediaDetailAdmin = dao.detailAdmin(mediaCode); //作品情報をDBからひっぱってくる
		session.setAttribute("mediaDetailAdmin", mediaDetailAdmin);
		//request.setAttribute("mediaDetailAdmin", mediaDetailAdmin);
		//return "medialist.jsp";

		//メディア情報編集・削除の場合はレビュー表示はいらない

		//次に、レビュー表示部分のデータをDBから取ってくる。
		//ReviewDAOのインスタンス作る
//		ReviewDAO daoR = new ReviewDAO();
//		List<ReviewBean> userReviewAll = daoR.search(mediaCode);
//		request.setAttribute("userReviewAll", userReviewAll);

		return "dispMediaDetailAdmin.jsp";

	}

}