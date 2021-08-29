package admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SearchReviewBean;
import dao.ReviewDAO;
import tool.Action;

public class EditReviewDetailAdminAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {


		HttpSession session = request.getSession();

		//まず、映像情報詳細の部分のデータをDBから取ってくる。
		//String mediaCode = request.getQueryString(); //getQuery部分の映像コード値を流し込む
		String reviewCode = request.getParameter("reviewCode"); //getParameterに映像コードを流し込む
		String reviewContent = request.getParameter("reviewContent");
		//request.getAttribute("mediaCode"); //${ml.mediaCode}

		//まず内容のアップデートを行う。
		ReviewDAO dao1 = new ReviewDAO();
		try {
		int line  = dao1.update(reviewCode, reviewContent); //作品情報をDBからひっぱってくる
		}
		catch(SQLException e) {
			e.printStackTrace();
			String txt = "SQLException：レビュー投稿時にUnicode絵文字を使用している可能性";
			String message = "Unicodeの絵文字を使用するこは出来ません！＞＜";
			request.setAttribute("SQLeroor_message", message);
			System.out.println(txt);

			return "ReviewDetailAdmin.action?" + reviewCode +"+edit";
		}


		//次に、更新したレビューデータを持ってくる。
		ReviewDAO dao2 = new ReviewDAO();

		//2つ目の引数「１」はGhost化したものも取ってこれるモードをセレクトする数字。
		List<SearchReviewBean> editReviewDetail = dao2.searchReviewCodeAdmin(reviewCode,1);
		request.removeAttribute("editReviewDetail");
		request.setAttribute("editReviewDetail", editReviewDetail);

		return "dispEditReviewContent_Result.jsp";

	}

}