package upload;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.MediaBean;
import bean.UploadBean;
import dao.MediaDAO;
import dao.UploadDAO;
import tool.Action;

public class UpdateAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		String srcURL = null;
		String passURL = null;
		String imagePath = null;
		String path = null;
		String mediaCodeString = null;
		String mediaTitleString = null;
		int mediaCode;




		//uploadDBの内容はupdateだけど、実行パス内のファイルは削除しないといけない。

//↓↓のアップロード部分は一緒なので、その前にすでにある画情報を消すことを考える。
// <input type="file" name="uploadfile">からMultipart形式のアップロードコンテンツの内容を取得
		Part part = request.getPart("uploadfile");
		String filename = null;
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			cd = cd.trim();
			if (cd.startsWith("filename")) {
				// ファイル名は=の右側以降の文字列。
				// ただし利用環境によってはだブルコーテーションが含まれているので、取り除く。
				filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				break;
			}
		}

		// アップロードしたファイルを書き出す
		String message = null;
		UploadDAO uplDAO = new UploadDAO();

		if (filename != null && uplDAO.search(filename).size() == 0) {
			// アップロードされたファイル名は、OS依存のファイルパスなどを含んでいるので置換する。
			// \は/に置換し、その後ファイル名のみ抽出する。
			filename = filename.replace("\\", "/");
			int pos = filename.lastIndexOf("/");
			if (pos >= 0) {
				filename = filename.substring(pos + 1);
			}
			// 実行パスの「images」フォルダにファイルをアップロードする場合の指定
			//try{part.write(request.getServletContext().getRealPath(request.getServletContext().getContextPath()) + "/images/" + filename); //これが先生の案
			//↓/tmp0/までがサーバーパス、/wtpwebapps/がデプロイパス、そのデプロイパス以下にプロジェクトが作成される
			//try{part.write("C:/pleiades-2020-06-java-win-64bit_20200702/pleiades/book/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/book/upload"+ "/images/" + filename);

			//正確な場所。先生の指定した方法だと「パスが見つからない」と言われるので、replaceで調整する。
			//誤、C:/pleiades-2020-06-java-win-64bit_20200702/pleiades/GoGoSeach/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GoGoSeach/GoGoSeach/images/a0523646685_10.jpg
			//正  C:/pleiades-2020-06-java-win-64bit_20200702/pleiades/GoGoSeach/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GoGoSeach/upload/images/
			//この置換処理をDownloadFile.javaでも行うが、「実行パス」にしなくても良いのでは？疑惑。画面左に作ったupload/imagesを右クリしてエクスプローラの場所を直接していでもいけるような？

			//getRealPath()		指定した仮想パスを物理パスに変化する。仮想パス＝webアプリ上のファイルパス（コンテキストルート起点）。物理パスwebアプリが動作するサーバ上のパス。
			//getRealPath()		教科書p295に～ String path = context.getRealPath("/WEB-INF/setting.txt");　みたいな記載ある。
			//getServletContext()	このサーブレットのServletContextオブジェクトを取得
			//getContextPath()	コンテキストパスの取得。/GoGoSeach

			try{ // このtryで書いたpathの記載をDownloadFiles.javaの同じような箇所にコピペする。
			//////////	実行パスの「images」フォルダにファイルをアップロードする場合の指定
				//先生のやり方のパスで行くなら法（それでも一部置換処理は必要）
//				srcURL = request.getServletContext().getRealPath(request.getServletContext().getContextPath());
//				passURL = srcURL.replace("\\GoGoSeach\\GoGoSeach", "\\GoGoSeach\\upload") + "/images/";
//				imagePassURL = passURL + filename;
//				part.write(imagePassURL);

			//↑を多少分解して記載した上でgetRealPath内を直接記載。結果としては一緒。今回はこっちで行く！
				ServletContext context = request.getServletContext();
				path = context.getRealPath("/upload/images/");//カッコ内は仮想パス（webアプリ上のファイルパス（コンテキストルート起点）
				imagePath = path + filename;
				part.write(imagePath);

			//Eclipseのエクスプローラーに出ている/upload/images/に入れるには、String contextに直で↓を入れる。
				//C:\pleiades-2020-06-java-win-64bit_20200702\pleiades\GoGoSeach\GoGoSeach\WebContent
				//String context = request.getContextPath();
//				String context = "C:\\pleiades-2020-06-java-win-64bit_20200702\\pleiades\\GoGoSeach\\GoGoSeach\\WebContent";
//				path = context + "/upload/images/";
//				imagePath = path + filename;
//				part.write(imagePath);


			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//mediaCodeの型をint型にする
			mediaCodeString = request.getParameter("mediaCodeUpload"); //mediaCodeStringはString型
			mediaTitleString = request.getParameter("mediaTitleUpload"); //mediaCodeStringはString型
			mediaCode = Integer.parseInt(mediaCodeString);

			//DBへのinsert処理の前にuploadbean化する。
			UploadBean uplBean = new UploadBean();
			uplBean.setMediaCode(mediaCode);
			uplBean.setMediaTitle(mediaTitleString);
			uplBean.setFilename(filename);
			uplBean.setFilePath(path); //pathは、このコード内の79行目のやつ。


			// アップロードが完了した後はデータベースに登録する
			// mediaCode(int), fileName(String), filePath(STring)を渡す
			uplDAO.insert(uplBean);
			//System.out.println("srcURL: " + srcURL);
			//System.out.println("passURL: " + passURL);
			System.out.println("path: " + path);
			System.out.println("imagePathURL: " + imagePath);

			//アップロードの完了メッセージ
			message = "[ " + filename + " ]のアップロードが完了しました。";

			//画像情報に対応した映像情報詳細をDBから取ってくる。
			MediaDAO daoM = new MediaDAO();
			List<MediaBean> mediaDetailAdmin = daoM.detailAdmin(mediaCodeString);
			request.removeAttribute("mediaDetailAdmin");
			request.setAttribute("mediaDetailAdmin", mediaDetailAdmin);

			//画像へのpath情報を取ってくる。path名を調整したいのでbeanで。
			//パスだけ直で記載してもいいが、せっかくなので凝った方法で。
			UploadDAO uplDAO2 = new UploadDAO();
			UploadBean imageInfo = uplDAO2.imageURL(mediaCodeString);

			String imagefilename = imageInfo.getFilename();
			String pathAll = imageInfo.getFilePath();
			String pathContext = pathAll.substring(pathAll.length() - 25);//コンテキストパス部分から切り出し
			String compImagePath = pathContext+imagefilename;

			request.setAttribute("imagefilename", imagefilename);
			request.setAttribute("compImagePath", compImagePath);

		} else {
			message = "アップロードが失敗しました。（すでにそのファイル名は使われています）";
			String mode = "0"; // return先の表示分けをするのに使う。
			request.setAttribute("mode", mode);

			return "upload-out.jsp";


		}
		//成功時にはこっちから。returnの先は失敗時と一緒。
		String mode = "1"; // return先の表示分けをするのに使う。
		request.setAttribute("mode", mode);
		request.setAttribute("message", message);

		return "upload-out.jsp";
	}

}
