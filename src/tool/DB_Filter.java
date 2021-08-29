package tool;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.IndexMediaBean;
import bean.MediaGenreBean;
import bean.ReviewBean;
import dao.MediaDAO;
import dao.ReviewDAO;

@WebFilter(urlPatterns = {"/index.jsp"})
public class DB_Filter implements Filter {

	MediaDAO daoM = new MediaDAO();
	ReviewDAO daoR = new ReviewDAO();

// index.jspで表示する項目をDBから取ってくるために使うfilter
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain chain
		) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession(); //getSessionメゾットでセッションを開始

		session.removeAttribute("latestMediaList");
		session.removeAttribute("WesternMovieList");
		session.removeAttribute("JpnMovieList");
		session.removeAttribute("reviewDaysList");


		//最新作表示用のlistを準備する
		List<IndexMediaBean> preList1 = new ArrayList<IndexMediaBean>(); //DBから取ってきたものを入れるlist
		List<IndexMediaBean> latestMediaList = new ArrayList<IndexMediaBean>(); //上記リストからランダムに取り出して入れる用list

		//
		List<IndexMediaBean> preList2 = new ArrayList<IndexMediaBean>(); //DBから取ってきたものを入れるlist
		List<IndexMediaBean> WesternMovieList = new ArrayList<IndexMediaBean>(); //上記リストからランダムに取り出して入れる用list

		//
		List<IndexMediaBean> preList3 = new ArrayList<IndexMediaBean>(); //DBから取ってきたものを入れるlist
		List<IndexMediaBean> JpnMovieList = new ArrayList<IndexMediaBean>(); //上記リストからランダムに取り出して入れる用list
		//
		List<ReviewBean> preList4 = new ArrayList<ReviewBean>(); //DBから取ってきたものを入れるlist
		List<ReviewBean> reviewDaysList = new ArrayList<ReviewBean>(); //上記リストからランダムに取り出して入れる用list


//画像へのコンテキストパスを取る
		String pathAll = null;
		String pathContext;
		//下二行が駄目だった場合は、その下に記載したチカラ技のやつを使う。
		ServletContext context = request.getServletContext();
		pathAll = context.getRealPath("/upload/images/");

		//チカラ技でやったやり方、あとで何とかする。
		//pathAll = "C:/pleiades-2020-06-java-win-64bit_20200702/pleiades/GoGoSeach/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GoGoSeach/upload/images/";
		pathContext = pathAll.substring(pathAll.length() - 25);//コンテキストパス部分から切り出し
		System.out.println("pathContext from DB_filter: "+pathContext);//確認のためコンソールに出力。

		//このセッションはindexに戻るたびにremoveされる内容ではない
		session.setAttribute("pathContext", pathContext);

//カテゴリ検索のgenreを取ってくる
		//MediaDAO gDAO = new MediaDAO();
		List<MediaGenreBean> genreList = new ArrayList<MediaGenreBean>();
		try {
			genreList = daoM.genreCategory(); //
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("genreListの取得エラー");
		}
		//このセッションはindexに戻るたびにremoveされる内容ではない
		session.setAttribute("genreList", genreList);

//登録された総media数(ghost化したものは含まず)を得るメソッド
		//MediaDAO daoMtotal = new MediaDAO();
			int mediaTotal =0;
			try {
				mediaTotal = daoM.totalMedia();
			} catch (Exception e2) {
				e2.printStackTrace();
				String messageTM = "総media数取得エラー/";
				request.setAttribute("messageTM", messageTM);
			}
			request.setAttribute("mediaTotal", mediaTotal);
			System.out.println("mediaTotal: " + mediaTotal);

//総reviewerの数(ghost化したものは含まず)を得るメソッド
		//ReviewDAO daoRver = new ReviewDAO();
			int totalReviewer = 0;
			try {
				totalReviewer = daoR.totalReviewer();
			} catch (Exception e1) {
				e1.printStackTrace();
				String messageTRvwer = "総reviewer数取得エラー/";
				request.setAttribute("messageTRver", messageTRvwer);
			}
			request.setAttribute("totalReviewer", totalReviewer);
			System.out.println("messageTRver: " + totalReviewer);

//総review数(ghost化したものは含まず)を得るメソッド
		//ReviewDAO daoRv = new ReviewDAO();
		int totalReviews = 0;
			try {
				totalReviews = daoR.totalReviews();
			} catch (Exception e1) {
				e1.printStackTrace();
				String messageTRvs = "総review数取得エラー";
				request.setAttribute("messageTRvs", messageTRvs);
			}
			request.setAttribute("totalReviews", totalReviews);
			System.out.println("totalReviews: "+totalReviews);


//最新作の何作かをDBから取得してセッション化する
		try {
			int rankNumber= 10; //DBから取ってくる最新の数を設定する。
			//MediaDAO dao = new MediaDAO();

			//List<MediaBean> preList1 = null; //DBから取ってきたものを入れるlist
			//List<MediaBean> latestMediaList = null; //上記リストからランダムに取り出して入れたlist
			//*** DBからのデータ取得に失敗した場合、SQLSyntaxErrorException出ることある。
			preList1 = daoM.latestMedia2(rankNumber);

			Random r = new Random();

			//DBから取ってきた数作からさらにindex.jspで表示する一作をランダムに決める
			for(int i = 0; i < 1; i++) {//1回取り出すときは『i < 1』と設定。
				int removePostition = r.nextInt(preList1.size());
				latestMediaList.add(preList1.remove(removePostition));//ここで表示する一作が決まる
			}
		}
		catch (SQLSyntaxErrorException e)  {
			e.printStackTrace();
			String messageSSEE1 = "最新作取得：SQL構文エラー or 画像データ未登録";
			System.out.println(messageSSEE1);
			request.setAttribute("messageSSEE1", messageSSEE1);
		}
		catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			String messageEE1 = "最新作取得：例外エラー_Exception e";
			System.out.println(messageEE1);
			request.setAttribute("messageEE1", messageEE1);

		}
			//映像情報のセッション情報化、removeはこのclassの冒頭で行っている。
			session.setAttribute("latestMediaList", latestMediaList);


//洋画の何作かをDBから取得してセッション化する
		try {
			int rankNumber= 10; //検索して持ってくる数
			String keyword2= "洋画";
			preList2 = daoM.genreMedia(keyword2, rankNumber);

			Random r = new Random();

			//DBから取ってきた数作からさらにindex.jspで表示する作品をランダムに決める
			for(int i = 0; i < 5; i++) {//1回取り出すときは『i < 1』と設定。
				int removePostition = r.nextInt(preList2.size());
				WesternMovieList.add(preList2.remove(removePostition));
			}
		}
		catch (SQLSyntaxErrorException e)  {
			e.printStackTrace();
			String messageSSEE2 = "洋画５選：SQL構文エラー or 画像データ未登録";
			System.out.println(messageSSEE2);
			request.setAttribute("messageSSEE2", messageSSEE2);
		}
		catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			String messageEE2 = "洋画５選：例外エラー_Exception e「最低５作品の登録必要」";
			System.out.println(messageEE2);
			request.setAttribute("messageEE2", messageEE2);

		}
			//洋画五選映像情報のセッション情報化、removeはこのclassの冒頭で行っている。
			session.setAttribute("WesternMovieList", WesternMovieList);

//邦画の何作かをDBから取得してセッション化する
		try {
			int rankNumber= 10; //検索して持ってくる数
			String keyword3= "邦画";
			preList3 = daoM.genreMedia(keyword3, rankNumber);

			Random r = new Random();

			//DBから取ってきた数作からさらにindex.jspで表示する作品をランダムに決める
			for(int i = 0; i < 5; i++) {//1回取り出すときは『i < 1』と設定。
				int removePostition = r.nextInt(preList3.size());
				JpnMovieList.add(preList3.remove(removePostition));
			}
		}
		catch (SQLSyntaxErrorException e)  {
			e.printStackTrace();
			String messageSSEE3 = "邦画５選：SQL構文エラー or 画像データ未登録";
			System.out.println(messageSSEE3);
			request.setAttribute("messageSSEE3", messageSSEE3);
		}
		catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			String messageEE3 = "邦画５選：例外エラー_Exception e「最低５作品の登録必要」";
			System.out.println(messageEE3);
			request.setAttribute("messageEE3", messageEE3);

		}
			//洋画五選映像情報のセッション情報化、removeはこのclassの冒頭で行っている。
			session.setAttribute("JpnMovieList", JpnMovieList);

//直近●日間の口コミを取得してきて表示するための。
			try {
				//ReviewDAO daoR = new ReviewDAO();
				int days= 7; //７は一週間と一緒だが、あくまで日数な点に注意。
				preList4 = daoR.searchReviewDays(days);

				Random r = new Random();

				//DBから取ってきた数作からさらにindex.jspで表示する作品をランダムに決める
				for(int i = 0; i < 5; i++) {//1回取り出すときは『i < 1』と設定。
					int removePostition = r.nextInt(preList4.size());
					reviewDaysList.add(preList4.remove(removePostition));
				}
			}
			catch (SQLSyntaxErrorException e)  {
				e.printStackTrace();
				String messageSSEE4 = "直近●日間の口コミ：SQL構文エラー or 画像データ未登録";
				System.out.println(messageSSEE4);
				request.setAttribute("messageSSEE4", messageSSEE4);
			}
			catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				String messageEE4 = "直近●日間の口コミ：例外エラー_Exception e<br>「最低５作品へのレビュー登録」or「レビュー文字数が規定に足りていない場合」";
				System.out.println(messageEE4);
				request.setAttribute("messageEE4", messageEE4);

			}
			//洋画五選映像情報のセッション情報化、removeはこのclassの冒頭で行っている。
			session.setAttribute("reviewDaysList", reviewDaysList);



//				System.out.println("フィルタの前処理");//↑
		chain.doFilter(request, response);
//				System.out.println("フィルタの後処理");//↓
	}
		public void init(FilterConfig FilterConfig) {}
		public void destroy() {}

}


