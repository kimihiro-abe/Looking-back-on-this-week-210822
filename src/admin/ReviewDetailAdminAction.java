package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SearchReviewBean;
import dao.ReviewDAO;
import tool.Action;

public class ReviewDetailAdminAction extends Action{

	private String mode;
	private String reviewCode;
	private String QueryString;
	final private int select_all=1;
	final private int select_nonGhost=2;
	final private int select_Ghost=3;

	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();

		//reviewAdmin.jspからのデータを受け取って、if-elseで条件分けする。
		QueryString = request.getQueryString();//クエリ(?以降の部分、reviewCode+[mode]にしてる)を取得。
		String QueryStringSrc[] = QueryString.split("\\+");//reviewCodeと[mode]を分割して配列に入れる。
		reviewCode = QueryStringSrc[0];//reviewCodeを代入
		mode = QueryStringSrc[1];//modeを代入

		if(mode.equals("delete")) {//削除（Ghost化）する

			ReviewDAO dao1 = new ReviewDAO();
			int line = dao1.delete(reviewCode); //削除（Ghost化）処理部分

			//更新処理が完了したら、そのデータをDBから取得する。
			ReviewDAO dao2 = new ReviewDAO();//ghost化したことを確認する為、変更後レビューデータを取得する。
			List<SearchReviewBean> delresReviewDetail = dao2.searchReviewCodeAdmin(reviewCode, select_Ghost);//
			request.removeAttribute("delresReviewDetail");//delres = delete restoreの略
			request.setAttribute("delresReviewDetail", delresReviewDetail);//delres = delete restoreの略
			//return "medialist.jsp";

			return "dispRestoredDeleteReviewDetail_Result.jsp";

		}
		else if(mode.equals("restore")) {//復元（Ghost化からの復元）

			ReviewDAO dao1 = new ReviewDAO();
			int line = dao1.restore(reviewCode); //復元（Ghost化からの復元）処理部分

			//更新処理が完了したら、そのデータをDBから取得する。
			ReviewDAO dao2 = new ReviewDAO();//ghost化したことを確認する為、変更後レビューデータを取得する。
			List<SearchReviewBean> delresReviewDetail = dao2.searchReviewCodeAdmin(reviewCode, select_nonGhost);//
			request.removeAttribute("delresReviewDetail");//delres = delete restoreの略
			request.setAttribute("delresReviewDetail", delresReviewDetail);//delres = delete restoreの略
			//return "medialist.jsp";

			return "dispRestoredDeleteReviewDetail_Result.jsp";

		}
		else if(mode.equals("edit")) {// 編集処理

			//データだけ取って、変更処理は次のJSPに飛ばす。
			ReviewDAO dao1 = new ReviewDAO();//ghost化したことを確認する為、変更後レビューデータを取得する。
			List<SearchReviewBean> editReviewDetail = dao1.searchReviewCodeAdmin(reviewCode, select_all);//
			request.removeAttribute("delresReviewDetail");//delres = delete restoreの略
			request.removeAttribute("editReviewDetail");//delres = delete restoreの略
			request.setAttribute("editReviewDetail", editReviewDetail);//delres = delete restoreの略
			//return "medialist.jsp";

			return "editReviewDetail.jsp";

		}

		//↓入れないとエラーでる。
		return "";

	}

}