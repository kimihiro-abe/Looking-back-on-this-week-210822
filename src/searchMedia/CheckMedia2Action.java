package searchMedia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MediaBean;
import bean.ReviewBean2;
import bean.UploadBean;
import dao.MediaDAO;
import dao.ReviewDAO;
import dao.UploadDAO;
import tool.Action;

public class CheckMedia2Action extends Action{

	private String QueryString;

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		Object obj = null;
		int checkIfPosted;
		String mediaCodeString = null;

		//getQuery部分の映像コード値を流し込む
		//String mediaCode = request.getQueryString();
		//String mediaCode = request.getQueryString();


		QueryString = request.getQueryString();//クエリ(?以降の部分、reviewCode+[mode]にしてる)を取得。
		String QueryStringSrc[] = QueryString.split("\\+");//reviewCodeと[mode]を分割して配列に入れる。
		String mediaCode = QueryStringSrc[0];//modeを代入
		String recommendAccountCode = QueryStringSrc[1];//reviewCodeを代入

//		System.out.println("mediaCode" + mediaCode);
//		System.out.println("recommendAccountCode" + recommendAccountCode);


		//画像へのpath情報を取ってくる。path名を調整したいのでbeanで。
		//パスだけ直で記載してもいいが、せっかくなので凝った方法で。
		try {
		UploadDAO daoUp = new UploadDAO();
		UploadBean imageInfo = daoUp.imageURL(mediaCode);

		String imagefilename = imageInfo.getFilename();
		String pathAll = imageInfo.getFilePath();
		String pathContext = pathAll.substring(pathAll.length() - 25);//コンテキストパス部分から切り出し
		String compImagePath = pathContext+imagefilename;

		request.setAttribute("imagefilename", imagefilename);
		request.setAttribute("compImagePath", compImagePath);
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			String messageNPE ="リンク切れ or DB接続エラー";
			request.setAttribute("messageNPE", messageNPE);

		}

		//映像情報詳細の部分のデータをDBから取ってくる。
		MediaDAO dao = new MediaDAO();
		List<MediaBean> mediaDetail = dao.detail(mediaCode); //作品情報をDBからひっぱってくる
		request.setAttribute("mediaDetail", mediaDetail);
		//return "medialist.jsp";

		//次に、レビュー表示部分のデータをDBから取ってくる。
		//ReviewDAOのインスタンス作る
		ReviewDAO daoR = new ReviewDAO();
		//List<ReviewBean> userReviewAll = daoR.search(mediaCode);
		List<ReviewBean2> userReviewAll = daoR.search3(mediaCode, recommendAccountCode); //退会済みユーザー表示テスト
		request.setAttribute("recommendAccountCode", recommendAccountCode);
		request.setAttribute("userReviewAll", userReviewAll);


		//映像情報詳細のコンテンツに対して自分がレビューしてるか確認するための準備
		try {
			obj = session.getAttribute("accountCode");
			String accountCode = obj.toString();
			ReviewDAO daoC = new ReviewDAO();
			checkIfPosted = daoC.checkIfPosted(mediaCode, accountCode);//引数は両方String
			request.setAttribute("CheckIfPosted", checkIfPosted);
		}
		//ゲスト時はcheckIfPosted()の値がとれないのでエラーが出るので
		//例外処理でありえない値（マイナス）を入れて返してあげることでゲスト時の判定を行う。
		catch(NullPointerException e) {
			checkIfPosted = -1;
			request.setAttribute("CheckIfPosted", checkIfPosted);
			return "dispMediaDetail2.jsp";
		}

		//該当作品のmediaCodeに、自分のaccountCodeでreviewがあるかを調べる

		//デバッグ用コンソールに値を表示：上記条件での返り値が１だったらレビュー投稿済み
		System.out.println("CheckIfPosted: " + checkIfPosted );

		return "dispMediaDetail2.jsp";

	}

}