package searchMedia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.IndexMediaBean;
import bean.UploadBean;
import dao.MediaDAO;
import dao.UploadDAO;
import tool.Action;


public class SearchCategoryAction extends Action{

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//HttpSession session = request.getSession();
		String mediaCodeString = null;

		//入力されたkeywordをもとに検索
		String category=request.getParameter("category");

		if(category.contains("（")) {
			category = category.replace("（", "(");
		}
		if(category.contains("）")) {
			category = category.replace("）", ")");
		}

		//映像情報(mediaCodeとmediaTitle)を取得。
		MediaDAO dao = new MediaDAO();
		List<IndexMediaBean> categoryList = dao.menuBoxMedia(category);
		request.setAttribute("categoryList", categoryList);
		//request.setAttribute("mediaList_length", mediaList_length);

		//ユーザーが見ることの出来る映像情報のmediaCodeだけを取ってくる
		//Ghost化したものは取ってこない。
//		List<Integer> searchMcode = dao.searchMC(keyword);
//		for(int i=0; i<searchMcode.size(); i++) {
//			System.out.println(searchMcode.get(i));
//		}

		//入力されたkeywordをもとに検索
		//画像へのpath情報を取ってくる。映像詳細時はbeanで取ったが今回は理由があってリストで。
		try {
		UploadDAO daoUp = new UploadDAO();

		//uploadのDBデータを全部持ってきてる。
		List<UploadBean> imageInfo = daoUp.imageInfoAll();
		request.setAttribute("imageInfo", imageInfo);


		//画像へのpath情報を取ってくる。path名を調整したいのでbeanで。
		//pathだけ直で記載してもいいが、せっかくなので凝った方法で取得してみる。
		//必要なのはコンテキストパスだけなので、適当んmediaCodeを代入すればok
		UploadBean imageInfom = daoUp.imageURL("1"); //mediaCode入れる

		//String imagefilename = imageInfo.getFilename();
		String pathAll = imageInfom.getFilePath();
		String pathContext = pathAll.substring(pathAll.length() - 25);//コンテキストパス部分から切り出し
		//String compImagePath = pathContext+imagefilename;

		//↓がJSPで表示されることは確認できている（値は取れているという意味）
		//request.setAttribute("pathContext", pathContext);
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			String messageNPE ="NullPointerException";
			request.setAttribute("messageNPE", messageNPE);

		}

		return "mediaCategoryList.jsp";
	}

}

