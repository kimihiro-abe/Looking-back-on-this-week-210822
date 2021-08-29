package upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MediaBean;
import bean.UploadBean;
import dao.MediaDAO;
import dao.UploadDAO;
import tool.Action;

public class SearchAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();

		//String mediaCodePre = request.getParameter("mediaCode");

		//画像情報をDBから探してくる部分
		String mediaCode = request.getParameter("mediaCode");
		if (mediaCode == null) mediaCode = ""; //「""」処理をintでやろうとすると不可

		request.removeAttribute("mediaCode");
		request.setAttribute("mediaCode", mediaCode);

		//uploadDBから必要な情報を取ってくる。
		UploadDAO dao = new UploadDAO();
		List<UploadBean> imageList = dao.imageURLs(mediaCode);

		session.removeAttribute("imageList");
		session.setAttribute("imageList", imageList);

		//画像重複があるかの判定
		int checkIfPosted = dao.checkIfPosted_mediaCode(mediaCode);
		request.setAttribute("checkIfPosted", checkIfPosted);


		//画像情報に対応した映像情報詳細をDBから取ってくる。
		MediaDAO daoM = new MediaDAO();
		List<MediaBean> mediaDetailAdmin = daoM.detailAdmin(mediaCode);

		session.removeAttribute("mediaDetailAdmin");
		session.setAttribute("mediaDetailAdmin", mediaDetailAdmin);

		return "upload-in.jsp";
	}
}
//先生のコード
/*		HttpSession session = request.getSession();

		String filename = request.getParameter("filename");
		if (filename == null) filename = "";

		UploadDAO dao=new UploadDAO();
		List<UploadBean> imageList = dao.search(filename);

		session.removeAttribute("imageList");
		session.setAttribute("imageList", imageList);

		return "upload-in.jsp";*/
