package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.IndexMediaBean;
import bean.MediaBean;
import bean.MediaGenreBean;

public class MediaDAO extends DAO{

	//sqlの定型文をfinalで設定しておく。
	final private String empty = ""; //現在から過去のものだけを表示
	final private String beforePubDate = " and deploymentDate <= now()"; //現在から過去のものだけを表示
	final private String afterPubDate = " and deploymentDate > now()"; //現在から未来のものだけを表示
	final private String nonGhost = " and ghost = 0"; //andの前にwhereが必要ならば入れる。0は非ghost 状態。
	final private String Ghost = " and ghost = 1"; //andの前にwhereが必要ならば入れる。1はghost状態。
	String mode;

	//メニューから使える検索窓から使う検索メソッド（ゲスト、ユーザー、管理者）
	//公開日時が現在より未来にあるメディアを表示させない検索メソッド
	public List<IndexMediaBean> search(String keyword) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<IndexMediaBean> list=new ArrayList<>();

		Connection con= getConnection();

		//210807ver
//		String concatSet = "mediaTitle,mediaInfo,director,leadingActor,music";
//		String sql ="select distinct mediaTitle,mediaCode,mediaInfo from media "
//				+ "where concat(" + concatSet +") like ?" + beforePubDate + nonGhost;

		//210815ver.
		String concatSet = "M.mediaTitle,M.mediaInfo,M.director,M.leadingActor,M.music";
		String sql ="select distinct M.mediaCode, M.mediaTItle, M.mediaInfo, U.fileName, U.filePath "
				+ "from media as M inner join upload as U on M.mediaCode = U.mediaCode "
				+ "where concat(" + concatSet +") like ?" + beforePubDate + nonGhost;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く

		//sqlのテスト
		//"select distinct mediaTitle,mediaCode,mediaInfo from media where concat(mediaTitle,mediaInfo) like ?"
		//select distinct mediaTitle,mediaCode from media where mediaTitle like ?"
		//select distinct mediaTitle,lpad(mediaCode,3,'0') mediaCode from media where mediaTitle like '%ハリー%';

		st.setString(1, "%"+keyword+"%"); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				IndexMediaBean mb=new IndexMediaBean();
				mb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				mb.setMediaTitle(rs.getString("mediaTitle"));
				mb.setMediaInfo(rs.getString("mediaInfo"));
				mb.setFileName(rs.getString("fileName"));
				mb.setFilePath(rs.getString("filePath"));


				list.add(mb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//画像表示のため、mediaCodeだけ取得したいので作ったメソッド
	public List<Integer> searchMC(String keyword) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<Integer> list=new ArrayList<>();

		Connection con= getConnection();

		String concatSet = "mediaTitle,mediaInfo,director,leadingActor,music";
		String sql ="select distinct mediaTitle,mediaCode,mediaInfo from media "
				+ "where concat(" + concatSet +") like ?" + beforePubDate + nonGhost;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く

		//"select distinct mediaTitle,mediaCode,mediaInfo from media where concat(mediaTitle,mediaInfo) like ?"
		//select distinct mediaTitle,mediaCode from media where mediaTitle like ?"
		//select distinct mediaTitle,lpad(mediaCode,3,'0') mediaCode from media where mediaTitle like '%ハリー%';

		st.setString(1, "%"+keyword+"%"); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				list.add(rs.getInt("mediaCode"));
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//ユーザーが検索リストから詳細情報を得るのに使うメソッド
	public List<MediaBean> detail(String mediaCode) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<MediaBean> list=new ArrayList<>();

		Connection con= getConnection();

		String sql ="select * from media where mediaCode = ?" + beforePubDate;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, mediaCode); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				MediaBean mb=new MediaBean();
				mb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				mb.setMediaTitle(rs.getString("mediaTitle"));
				mb.setMediaInfo(rs.getString("mediaInfo"));
				mb.setMediaType(rs.getString("mediaType"));
				mb.setReleaseDate(rs.getString("releaseDate"));
				mb.setDirector(rs.getString("director"));
				mb.setLeadingActor(rs.getString("leadingActor"));
				mb.setMusic(rs.getString("music"));
				mb.setProductionYear(rs.getString("productionYear"));
				mb.setGhost(rs.getInt("ghost"));
				mb.setGenre1(rs.getString("genre1"));
				mb.setGenre2(rs.getString("genre2"));
				mb.setGenre3(rs.getString("genre3"));
				mb.setGenre4(rs.getString("genre4"));
				mb.setGenre5(rs.getString("genre5"));

				list.add(mb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

/*	ここから下は管理者が使用するメソッド	*/
	//管理者用検索。４つの検索方法で映像情報を検索する。
	public List<MediaBean> searchMediaAdmin(String keyword, int select) throws Exception {//複数パラメータを流し込むのでBeansを使う
		int selectMode = select;

		if(selectMode == 1) {//全検索
			mode = empty;
		}else if(selectMode == 2) {//配信中のメディア情報検索（一般ユーザーが使用している検索と同じ）
			mode = beforePubDate;
		}else if(selectMode == 3) {//未配信のメディア情報検索
			mode = afterPubDate;
		}else {
			mode = Ghost; // ghost化したメデァア情報の検索
		}

		List<MediaBean> list=new ArrayList<>();
		Connection con= getConnection();

		String concatSet = "mediaCode,mediaTitle,mediaInfo,director,leadingActor,music";
		String sql ="select distinct mediaTitle,mediaCode,mediaInfo from media where concat("
				+ concatSet + ") like ?" + mode;

		//select distinct mediaTitle,lpad(mediaCode,3,'0') mediaCode from media where mediaTitle like '%ハリー%';

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);

		st.setString(1, "%"+keyword+"%"); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				MediaBean mb=new MediaBean();
				mb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				mb.setMediaTitle(rs.getString("mediaTitle"));

				list.add(mb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//管理者専用の映像詳細表示メソッド
	public List<MediaBean> detailAdmin(String mediaCode) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<MediaBean> list=new ArrayList<>();

		Connection con= getConnection();

		String sql ="select * from media where mediaCode = ?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, mediaCode); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				MediaBean mb=new MediaBean();
				mb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				mb.setMediaTitle(rs.getString("mediaTitle"));
				mb.setMediaInfo(rs.getString("mediaInfo"));
				mb.setMediaType(rs.getString("mediaType"));
				mb.setReleaseDate(rs.getString("releaseDate"));
				mb.setDirector(rs.getString("director"));
				mb.setLeadingActor(rs.getString("leadingActor"));
				mb.setMusic(rs.getString("music"));
				mb.setProductionYear(rs.getString("productionYear"));
				mb.setDeploymentDate(rs.getString("deploymentDate"));
				mb.setGhost(rs.getInt("ghost"));
				mb.setGenre1(rs.getString("genre1"));
				mb.setGenre2(rs.getString("genre2"));
				mb.setGenre3(rs.getString("genre3"));
				mb.setGenre4(rs.getString("genre4"));
				mb.setGenre5(rs.getString("genre5"));

				list.add(mb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//管理者専用の映像詳細表示メソッド　その２。映像情報を新規追加後にDBからデータを取得するために必要。
	//登録後にmediaCodeは振られるので、mediaCodeが降られたあとで。
	public MediaBean getMediaCodeAdmin(String mediaTitle) throws Exception {//複数パラメータを流し込むのでBeansを使う
		//List<MediaBean> list=new ArrayList<>();
		MediaBean mb=new MediaBean();

		Connection con= getConnection();
		String sql ="select * from media where mediaTitle = ?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, mediaTitle); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス


			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。

				mb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
//				mb.setMediaTitle(rs.getString("mediaTitle"));
//				mb.setMediaInfo(rs.getString("mediaInfo"));
//				mb.setMediaType(rs.getString("mediaType"));
//				mb.setReleaseDate(rs.getString("releaseDate"));
//				mb.setDirector(rs.getString("director"));
//				mb.setLeadingActor(rs.getString("leadingActor"));
//				mb.setMusic(rs.getString("music"));
//				mb.setProductionYear(rs.getString("productionYear"));
//				mb.setDeploymentDate(rs.getString("deploymentDate"));
//				mb.setGhost(rs.getInt("ghost"));
//				mb.setGenre1(rs.getString("genre1"));
//				mb.setGenre2(rs.getString("genre2"));
//				mb.setGenre3(rs.getString("genre3"));
//				mb.setGenre4(rs.getString("genre4"));
//				mb.setGenre5(rs.getString("genre5"));

				//list.add(mb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return mb;
	}


	public int insert(MediaBean mediaBean) throws Exception{

		int line;
		Connection con=getConnection();
		String sql ="insert into media values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st=con.prepareStatement(sql);
//							"insert into account values(null, ?, ?, ?, now(), ?, ?)");

		st.setString(1, mediaBean.getMediaTitle());
		st.setString(2, mediaBean.getMediaInfo());
		st.setString(3, mediaBean.getMediaType());
		st.setString(4, mediaBean.getReleaseDate());
		st.setString(5, mediaBean.getDirector());
		st.setString(6, mediaBean.getLeadingActor());
		st.setString(7, mediaBean.getMusic());
		st.setString(8, mediaBean.getProductionYear());
		st.setString(9, mediaBean.getDeploymentDate());
		st.setInt(10, mediaBean.getGhost());
		st.setString(11, mediaBean.getGenre1());
		st.setString(12, mediaBean.getGenre2());
		st.setString(13, mediaBean.getGenre3());
		st.setString(14, mediaBean.getGenre4());
		st.setString(15, mediaBean.getGenre5());

		try {
			line = st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			st.close();
			con.close();
		}
		return line;
	}

	//メディア情報の変更時に使用する。insertと比較するとmediaCode項目が増えている。
	public int edit(MediaBean mediaBean) throws Exception{

		int line;
		Connection con=getConnection();
		//String sql ="insert into media values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql ="update media set"
		+ " mediaTitle=?, mediaInfo=?, mediaType=?, releaseDate=?, director=?, leadingActor=?," //6個
		+ " music=?, productionYear=?, deploymentDate=?, ghost=?," //4個
		+ " genre1=?, genre2=?, genre3=?, genre4=?, genre5=?" //5個
		+ "where mediaCode=?"; //1個
		PreparedStatement st=con.prepareStatement(sql);
//							"insert into account values(null, ?, ?, ?, now(), ?, ?)");

		st.setString(1, mediaBean.getMediaTitle());
		st.setString(2, mediaBean.getMediaInfo());
		st.setString(3, mediaBean.getMediaType());
		st.setString(4, mediaBean.getReleaseDate());
		st.setString(5, mediaBean.getDirector());
		st.setString(6, mediaBean.getLeadingActor());
		st.setString(7, mediaBean.getMusic());
		st.setString(8, mediaBean.getProductionYear());
		st.setString(9, mediaBean.getDeploymentDate());
		st.setInt(10, mediaBean.getGhost());
		st.setString(11, mediaBean.getGenre1());
		st.setString(12, mediaBean.getGenre2());
		st.setString(13, mediaBean.getGenre3());
		st.setString(14, mediaBean.getGenre4());
		st.setString(15, mediaBean.getGenre5());
		st.setInt(16, mediaBean.getMediaCode());

		try {
			line = st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			st.close();
			con.close();
		}
		return line;
	}

	//media情報の削除は、updateでghost化させてユーザー検索リストに表示させないようにする。
	public int delete(String mediaCode) throws Exception { //id値だけ入れるのでBeansを使わない。

		int line; //更新なのでline数には変化がない
		Connection con=getConnection();

		//ghost=1でゴースト化
		//update media set ghost=1 where mediaCode=41;
		String sql = "update media set ghost=1 where mediaCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, mediaCode);

		try {
			line=st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			st.close();
			con.close();
		}
		return line;
	}

	//media情報の復元は、updateで非ghost化させてユーザー検索リストに表示させるようにする。
	public int restore(String mediaCode) throws Exception {//複数パラメータを流し込むのでBeansを使う

		int line; //更新なのでline数には変化がない
		Connection con=getConnection();

		//ghost=0でゴースト化
		//update media set ghost=0 where mediaCode=41;
		String sql = "update media set ghost=0 where mediaCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, mediaCode);

		try {
			line=st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			st.close();
			con.close();
		}
		return line;
	}

/*	ここから下はwelcomeページのindex.jspで必要なデータを取得すのに使用するメソッド	*/
	//最新作を取得する。
	public List<MediaBean> latestMedia(int rankNumber) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<MediaBean> list=new ArrayList<>();

		Connection con= getConnection();
		//int rankNumberInt = Integer.parseInt(rankNumber);// Stringのままやろうとしたら出来なかった。

		//String concatSet = "mediaTitle,mediaInfo,director,leadingActor,music";
//		String sql ="select * from " +
//		"(select * from media order by releaseDate desc limit ?) as A order by releaseDate"
//		+ beforePubDate + nonGhost;

		//String sql ="select * from media"; //これは動作OK

		String sql ="select * from (select * from media order by releaseDate desc limit ?)"
				+ "as A order by releaseDate and deploymentDate <= now() and ghost=0";

		//select * from (select * from media order by releaseDate desc limit 10) as A
		//order by releaseDate and deploymentDate <= now() and ghost=0;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く
		st.setInt(1, rankNumber); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				MediaBean mb=new MediaBean();
				mb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				mb.setMediaTitle(rs.getString("mediaTitle"));
				mb.setMediaInfo(rs.getString("mediaInfo"));

				System.out.println(rs.getInt("mediaCode"));
				System.out.println(rs.getString("mediaTitle"));
				System.out.println(rs.getString("mediaInfo"));


				list.add(mb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//最新作を取得する。新たにIndexMediaBeanを使ってデータを１つにして取ってくる。こっちが正式採用
	public List<IndexMediaBean> latestMedia2(int rankNumber) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<IndexMediaBean> list=new ArrayList<>();

		Connection con= getConnection();
		//String sql ="select * from media"; //これは動作OK

		String sql ="select M.mediaCode, M.mediaTItle, M.mediaInfo, U.fileName, U.filePath"
				+ " from media as M inner join upload as U on M.mediaCode = U.mediaCode"
				+ " order by releaseDate <=now() and ghost=0 desc limit ?";

//		select M.mediaCode, M.mediaTItle, M.mediaInfo, U.fileName, U.filePath
//		from media as M inner join upload as U on M.mediaCode = U.mediaCode
//		order by releaseDate <=now() and ghost=0 desc limit 10;


		//select * from (select * from media order by releaseDate desc limit 10) as A
		//order by releaseDate and deploymentDate <= now() and ghost=0;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く
		st.setInt(1, rankNumber); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				IndexMediaBean imb = new IndexMediaBean();
				imb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				imb.setMediaTitle(rs.getString("mediaTitle"));
				imb.setMediaInfo(rs.getString("mediaInfo"));
				imb.setFileName(rs.getString("fileName"));
				imb.setFilePath(rs.getString("filePath"));

				list.add(imb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//indexの特定ジャンルをひっぱってくるメソッド
	public List<IndexMediaBean> genreMedia(String keyword,int rankNumber) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<IndexMediaBean> list=new ArrayList<>();

		Connection con= getConnection();
		//String sql ="select * from media"; //これは動作OK

		String sql ="select distinct M.mediaCode, M.mediaTitle, M.mediaInfo, M.genre1, U.fileName, U.filePath from media as M"
				+ " inner join upload as U on M.mediaCode = U.mediaCode where genre1 like ? and deploymentDate <= now() and ghost=0"
				+ " order by releaseDate <=now() desc limit ?";

		//select distinct M.mediaCode, M.mediaTitle, M.mediaInfo, M.genre1, U.fileName, U.filePath from media as M
		// inner join upload as U on M.mediaCode = U.mediaCode where genre1 like '%洋画%' and deploymentDate <= now() and ghost=0
		// order by releaseDate <=now() desc limit 10;

		//select * from (select * from media order by releaseDate desc limit 10) as A
		//order by releaseDate and deploymentDate <= now() and ghost=0;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く
		st.setString(1, "%"+keyword+"%"); //
		st.setInt(2, rankNumber); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				IndexMediaBean imb = new IndexMediaBean();
				imb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				imb.setMediaTitle(rs.getString("mediaTitle"));
				imb.setMediaInfo(rs.getString("mediaInfo"));
				imb.setGenre1(rs.getString("genre1"));
				imb.setFileName(rs.getString("fileName"));
				imb.setFilePath(rs.getString("filePath"));

				list.add(imb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//indexのmenu-boxのカテゴリ検索用メソッド,,,5つあるgenre枠の全てを検索する
	public List<IndexMediaBean> menuBoxMedia(String category) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<IndexMediaBean> list=new ArrayList<>();

		Connection con= getConnection();
		//String sql ="select * from media"; //これは動作OK

		String sql ="select distinct M.mediaCode, M.mediaTitle, M.mediaInfo, U.fileName, U.filePath from media as M"
				+ " inner join upload as U on M.mediaCode = U.mediaCode"
				+ " where concat(M.genre1, M.genre2, M.genre3, M.genre4, M.genre5) like ?"
				+ " and M.deploymentDate <= now() and M.ghost=0"
				+ " order by M.releaseDate <=now() desc;";

		//select distinct M.mediaCode, M.mediaTitle, M.mediaInfo, M.genre1, U.fileName, U.filePath from media as M
		// inner join upload as U on M.mediaCode = U.mediaCode where genre1 like '%洋画%' and deploymentDate <= now() and ghost=0
		// order by releaseDate <=now() desc limit 10;

		//select * from (select * from media order by releaseDate desc limit 10) as A
		//order by releaseDate and deploymentDate <= now() and ghost=0;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く
		st.setString(1, "%"+category+"%");

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				IndexMediaBean imb = new IndexMediaBean();
				imb.setMediaCode(rs.getInt("mediaCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				imb.setMediaTitle(rs.getString("mediaTitle"));
				imb.setMediaInfo(rs.getString("mediaInfo"));
				imb.setFileName(rs.getString("fileName"));
				imb.setFilePath(rs.getString("filePath"));

				list.add(imb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//indexのカテゴリー検索に必要なgenreを取得するメソッド
	public List<MediaGenreBean> genreCategory() throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<MediaGenreBean> list=new ArrayList<>();

		Connection con= getConnection();
		String sql ="select * from media_genre order by genre_code asc";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く
		//st.setString(1, "%"+category+"%");

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				MediaGenreBean mgb = new MediaGenreBean();
				mgb.setGenre_code(rs.getInt("genre_code")); //データベースのカラム名。String型なので必要あらば型を変換。
				mgb.setGenre_name(rs.getString("genre_name"));

//				System.out.println("genre_code: "+rs.getInt("genre_code"));
//				System.out.println("genre_name: "+rs.getString("genre_name"));

				list.add(mgb);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//登録された総media数(ghost化したものは含まず)を得るメソッド
	public int totalMedia() throws Exception {//Ghost化からの復元
		Connection con= getConnection();

		int totalMedia = 0;
		String sql = "select count(*) from media where ghost=0";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		//st.setString(1, mediaCode);
		//st.setString(2, accountCode);

		try {//
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {//rs.next()
				totalMedia = rs.getInt(1);

			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}

		return totalMedia;
	}
}
