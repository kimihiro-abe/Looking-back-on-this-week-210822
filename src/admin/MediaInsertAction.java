package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MediaBean;
import dao.MediaDAO;
import tool.Action;

public class MediaInsertAction extends Action{

	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception{

			//新規登録時あとに再度ログインをやる気もするから、ここでセッション情報取らなくていいかも。
			//というのもログイン時にセッション情報をとっているので。。
			//HttpSession session=request.getSession();
			//SimpleDateFormat relDateFmt = new SimpleDateFormat("yyyy-MM-dd");
			//SimpleDateFormat depDateFmt = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

			String mediaTitle = request.getParameter("mediaTitle");
			String mediaInfo = request.getParameter("mediaInfo");
			String mediaType = request.getParameter("mediaType");
			String releaseDate = request.getParameter("releaseDate");
			//Date releaseDate = (Date) relDateFmt.parse(request.getParameter("releaseDate"));
			String director = request.getParameter("director");
			String leadingActor = request.getParameter("leadingActor");
			String music = request.getParameter("music");
			String productionYear = request.getParameter("productionYear"); //string
			String deploymentDate = request.getParameter("deploymentDate");
			//Date deploymentDate = (Date) depDateFmt.parse(request.getParameter("deploymentDate"));
			int ghost = 0; //新規登録自は0でok
			String genre1 = request.getParameter("genre1");
			String genre2 = request.getParameter("genre2");
			String genre3 = request.getParameter("genre3");
			String genre4 = request.getParameter("genre4");
			String genre5 = request.getParameter("genre5");

			MediaBean mb=new MediaBean();
			mb.setMediaTitle(mediaTitle);
			mb.setMediaInfo(mediaInfo);
			mb.setMediaType(mediaType);
			mb.setReleaseDate(releaseDate); //桁数足りない＆数字以外は文字入れるとDataTruncationエラーがでる。
			mb.setDirector(director);
			mb.setLeadingActor(leadingActor);
			mb.setMusic(music);
			mb.setProductionYear(productionYear); //桁数足りない＆数字以外はDataTruncationエラーがでる。
			mb.setDeploymentDate(deploymentDate); //桁数足りない＆数字以外はDataTruncationエラーがでる。
			mb.setGhost(ghost);
			mb.setGenre1(genre1);
			mb.setGenre2(genre2);
			mb.setGenre3(genre3);
			mb.setGenre4(genre4);
			mb.setGenre5(genre5);


			//request.getRequestDispatcher("mediaInsert.jsp").forward(request, response);
			//return "mediaInsert_test.jsp"; //エラーが出たときに画面遷移しない方法にした方が良いかも（希望的なんちゃら


			MediaDAO dao2=new MediaDAO();
			int line=dao2.insert(mb);
			if(line == 1) { //DBへの追加に成功した時

				//次のページで結果を表示するためのデータを取ってきてセットする。
				//MediaDAO dao = new MediaDAO();
				MediaBean mediaCodeBean = dao2.getMediaCodeAdmin(mediaTitle); //作品情報をDBからひっぱってくる
				Integer mediaCodeInt = mediaCodeBean.getMediaCode();
				String mediaCode = mediaCodeInt.toString();

				List<MediaBean> mediaDetailAdmin = dao2.detailAdmin(mediaCode); //作品情報をDBからひっぱってくる
				request.setAttribute("mediaDetailAdmin", mediaDetailAdmin);

				return "dispInsertEditMediaDetailAdmin_Result.jsp"; //
				//return "mediaInsert_done.jsp"; //
				//dispInsertEditMediaDetailAdmin_Result.jsp
			}
			//DBへの書き込みに失敗。「同タイトルがすでにある」が主に考えうる。
			return "";
			}
		}
