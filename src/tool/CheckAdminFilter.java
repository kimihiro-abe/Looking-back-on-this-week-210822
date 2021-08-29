/*package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

*//**
	 * 管理者しか見ることの出来ないページにかけるフィルタ
	 * 本番前にテストを十分に出来ずなので、プレゼン時は外します
	 *//*

		@WebFilter(urlPatterns = {"/admin/*","/upload/upload-in.jsp","/upload/upload-out.jsp","/upload/download/","/upload/download","/upload/DownloadFile.java","/upload/SearchAction.java","/upload/UpdateAction.java","/upload/UploadAction.java"}) //例：/admin/*

		public class CheckAdminFilter implements Filter {

		public void doFilter(
				ServletRequest request, ServletResponse response,
				FilterChain chain
			) throws IOException, ServletException {

			try {
				HttpSession session = ((HttpServletRequest) request).getSession(); //getSessionメゾットでセッションを開始
				Integer admin = (Integer) session.getAttribute("admin");

				if(admin < 2) {
					((HttpServletResponse) response).sendRedirect("/GoGoSeach/");
				}
			}catch(NullPointerException e) {
				((HttpServletResponse) response).sendRedirect("/GoGoSeach/");

			}
		//				System.out.println("フィルタの前処理");//↑
				chain.doFilter(request, response);
		//				System.out.println("フィルタの後処理");//↓
		}
				public void init(FilterConfig FilterConfig) {}
				public void destroy() {}
		}
		*/