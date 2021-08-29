package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MediaBean;
import dao.MediaDAO;
import tool.Action;

public class MediaDetailRestoreAction extends Action{

	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception{

		//HttpSession session = request.getSession();

		//まず、映像情報詳細の部分のデータをDBから取ってくる。
		String mediaCode = request.getQueryString(); //getQuery部分の映像コード値を流し込む
		//String mediaCode = request.getParameter("mediaCode"); //getParameterに映像コードを流し込む
		//request.getAttribute("mediaCode"); //${ml.mediaCode}

		MediaDAO dao1 = new MediaDAO();
		int line = dao1.restore(mediaCode); //作品情報をDBからひっぱってくる

		//更新処理が完了したら、そのデータをDBから取得する。
		MediaDAO dao2 = new MediaDAO();
		List<MediaBean> restoredMediaDetail = dao2.detailAdmin(mediaCode); //作品情報をDBからひっぱってくる
		request.setAttribute("restoredMediaDetail", restoredMediaDetail);
		//return "medialist.jsp";

		return "dispRestoredDeleteMediaDetail_Result.jsp";

	}
}
