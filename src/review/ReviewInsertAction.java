package review;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ReviewBean;
import dao.ReviewDAO;
import tool.Action;

public class ReviewInsertAction extends Action{

	public String execute(
			HttpServletRequest request,HttpServletResponse response

		)throws Exception{

			int line = 0;
			HttpSession session = request.getSession(); //getSessionメゾットでセッションを開始

			int mediaCode = Integer.parseInt(request.getParameter("mediaCode")); //リクエスト属性でとってこれぬか
			String reviewContent = request.getParameter("reviewContent");

			//int reviewCode = Integer.parseInt(request.getParameter("reviewCode"));
			//int reviewCode = 1; //リクエスト属性でとってこれぬか

			Integer accountCode = (Integer) session.getAttribute("accountCode");
			String nickName = (String) session.getAttribute("nickName");
			int netabare = 0;
			int reviewGhost = 0;

			ReviewBean rb = new ReviewBean();
			//rb.setReviewCode(reviewCode); //自動連番になるので、これは含めなくてOK
			rb.setMediaCode(mediaCode);
			rb.setAccountCode(accountCode);
			rb.setNickName(nickName);
			rb.setReviewContent(reviewContent);
			//reviewDateはreviewDAOのinsert()メソッドで自動で入るので入れない
			rb.setNetabare(netabare);
			rb.setReviewGhost(reviewGhost);

			if(reviewContent==null) {
				return "review-error-enply.jsp";
			}
			//↑のif文に引っかからなかったら追加される。
			ReviewDAO dao = new ReviewDAO();
			try {
			line=dao.insert(rb);
			}
			catch(SQLException e) {
				e.printStackTrace();
				String txt = "SQLException：レビュー投稿時にUnicode絵文字を使用している可能性";
				String message = "Unicodeの絵文字を使用するこは出来ません！＞＜";
				request.setAttribute("message", message);
				System.out.println(txt);

				return "../searchMedia/CheckMedia.action?" + mediaCode;
			}

			if(line !=1) { //データベースには一行追加されるので、1以外だったらエラー判定。

				request.removeAttribute("mediaCode");
				request.setAttribute("mediaCode", mediaCode);

				return "review-error-insert.jsp";
			}
				request.removeAttribute("mediaCode");
				request.setAttribute("mediaCode", mediaCode);

				return "reviewInsert-out.jsp";
	}
}




