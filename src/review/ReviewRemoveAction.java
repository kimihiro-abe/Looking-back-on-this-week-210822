package review;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDAO;
import tool.Action;

public class ReviewRemoveAction extends Action{

		private String QueryString;
		private int reviewCode;
		private String mediaCode;


		public String execute(
				HttpServletRequest request,HttpServletResponse response
			)throws Exception{

				HttpSession session=request.getSession();
				//int reviewCode = Integer.parseInt(request.getParameter("reviewCode"));
				//int reviewCode = Integer.parseInt(request.getQueryString());

				QueryString = request.getQueryString();//クエリ(?以降の部分、reviewCode+[mode]にしてる)を取得。
				String QueryStringSrc[] = QueryString.split("\\+");//reviewCodeと[mode]を分割して配列に入れる。
				reviewCode = Integer.parseInt(QueryStringSrc[0]);//reviewCodeを代入
				mediaCode = QueryStringSrc[1];//modeを代入

				try {

					//String reviewCode = request.getParameter("reviewCode");

					//ReviewDAO dao=new ReviewDAO();

					//try {
						//int line = dao.remove(reviewCode);
						//if (line < 1){
						//return "review-error-insert.jsp";
						//}
					//}catch (Exception e) {
						//return "review-error-insert.jsp";
					//}
						//session.removeAttribute("reviewCode");

						//return "review-del-end.jsp";
					//}
		//}

//					ReviewBean rb=new ReviewBean();
//					rb.setReviewCode(reviewCode);


					ReviewDAO dao=new ReviewDAO();
					int line=dao.remove(reviewCode);//int line=dao.remove(rb);

					if (line>0) {
						//if(session.getAttribute("reviewCode")!=null)
						{
							//session.invalidate();					//セッションごと破棄(ログイン状態を解除)
							//String url = "../searchMedia/CheckMedia.action" + "?" + mediaCode;

							int mediaCodeInt = Integer.parseInt(mediaCode);
							request.removeAttribute("mediaCode");
							request.setAttribute("mediaCode", mediaCodeInt);
							String url = "review-del-media.jsp";
							return url;//"review-error-insert.jsp"
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			return "review-del-end.jsp";
			}
		}

				//int reviewCode=Integer.parseInt(request.getParameter("reviewCode"));
					//リクエストパラメータで取得

				//List<ReviewBean>	list=(List<ReviewBean>)session.getAttribute("list");
					//セッション属性から取得

				//for (ReviewBean rb:list) {
					//if(rb.getReviewCode()==reviewCode) {
						//list.remove(rb);
						//break;
					//指定された商品番号の項目Beanをカートから検索
					//見つかった項目Beanを削除
					//拡張for分を使ってカート内の項目Beanを順に調べる
					//指定の商品番号が見つかったらremoveメゾットを使って項目Beanを削除
					//break分で繰り返し

					//}
				//}
				//return "../mypage/mypage.jsp";
		//}
	//}
