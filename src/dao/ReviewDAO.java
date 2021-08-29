package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ReviewBean;
import bean.ReviewBean2;
import bean.SearchReviewBean;


public class ReviewDAO extends DAO{
	final private String empty = "";
	final private String nonGhost = " and reviewGhost = 0"; //andの前にwhereが必要ならば入れる。0は非ghost 状態。
	final private String Ghost = " and reviewGhost = 1"; //andの前にwhereが必要ならば入れる。1はghost状態。
	private String mode;
	private int checkIfPosted;

	//映像情報詳細表示時にレビュー一覧を取得するのに使用するメソッド
	public List<ReviewBean> search(String mediaCode) throws Exception {
		List<ReviewBean> list=new ArrayList<>();

		Connection con= getConnection();

		String sql = "select * from review where mediaCode=?"
		+ nonGhost + " order by reviewDate desc";
		//String sql = "select * from review where mediaCode like ?" + nonGhost; //原型
		//String sql2 = "select A.nickName, R.reviewDate, R.reviewContent from review R inner join media M on R.mediaCode = M.mediaCode inner join account A on R.accountCode = A.accountCode where M.mediaCode = ?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setString(1, mediaCode);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				ReviewBean rb=new ReviewBean();
				rb.setReviewCode(rs.getInt("reviewCode"));
				rb.setMediaCode(rs.getInt("mediaCode"));
				rb.setAccountCode(rs.getInt("accountCode"));
				rb.setNickName(rs.getString("nickName"));
				rb.setReviewContent(rs.getString("reviewContent"));
				rb.setReviewDate(rs.getString("reviewDate"));
				rb.setNetabare(rs.getInt("netabare"));
				list.add(rb);
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

	//映像情報詳細表示時にレビュー一覧を取得するのに使用するメソッド、こちらの方が厳格なデータを取ってるので正
	public List<ReviewBean2> search2(String mediaCode) throws Exception {
		List<ReviewBean2> list=new ArrayList<>();

		Connection con= getConnection();

		String sql = "select A.ghost, R.* from review R inner join account A on A.accountCode = R.accountCode where mediaCode=?"
		+ nonGhost + " order by reviewDate desc";
		//String sql = "select * from review where mediaCode like ?" + nonGhost; //原型
		//String sql2 = "select A.nickName, R.reviewDate, R.reviewContent from review R inner join media M on R.mediaCode = M.mediaCode inner join account A on R.accountCode = A.accountCode where M.mediaCode = ?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setString(1, mediaCode);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				ReviewBean2 rb=new ReviewBean2();
				rb.setGhost(rs.getInt("ghost"));
				rb.setReviewCode(rs.getInt("reviewCode"));
				rb.setMediaCode(rs.getInt("mediaCode"));
				rb.setAccountCode(rs.getInt("accountCode"));
				rb.setNickName(rs.getString("nickName"));
				rb.setReviewContent(rs.getString("reviewContent"));
				rb.setReviewDate(rs.getString("reviewDate"));
				rb.setNetabare(rs.getInt("netabare"));
				list.add(rb);
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

	//トップ画面のおすすめレビュー紹介専用メソッド。order byしたときにおすすめレビュワーを一番上に持ってくるメソッド
	public List<ReviewBean2> search3(String mediaCode, String recommendAccountCode) throws Exception {
		List<ReviewBean2> list=new ArrayList<>();

		Connection con= getConnection();

		String sql = "select A.ghost, R.* from review R inner join account A on A.accountCode = R.accountCode where mediaCode=?"
		+ nonGhost + " order by A.accountCode=? desc";
		//String sql = "select * from review where mediaCode like ?" + nonGhost; //原型
		//String sql2 = "select A.nickName, R.reviewDate, R.reviewContent from review R inner join media M on R.mediaCode = M.mediaCode inner join account A on R.accountCode = A.accountCode where M.mediaCode = ?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setString(1, mediaCode);
		st.setString(2, recommendAccountCode);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				ReviewBean2 rb=new ReviewBean2();
				rb.setGhost(rs.getInt("ghost"));
				rb.setReviewCode(rs.getInt("reviewCode"));
				rb.setMediaCode(rs.getInt("mediaCode"));
				rb.setAccountCode(rs.getInt("accountCode"));
				rb.setNickName(rs.getString("nickName"));
				rb.setReviewContent(rs.getString("reviewContent"));
				rb.setReviewDate(rs.getString("reviewDate"));
				rb.setNetabare(rs.getInt("netabare"));
				list.add(rb);
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

	//指定日数分のレビューを取得するメソッド
	public List<ReviewBean> searchReviewDays(int days) throws Exception {
		List<ReviewBean> list=new ArrayList<>();

		Connection con= getConnection();

		String sql = "select * from review where reviewDate >= DATE_SUB(NOW(),INTERVAL ? DAY)"
		+ nonGhost;
		//SELECT * FROM review where reviewDate >= DATE_SUB(NOW(),INTERVAL 7 DAY) and reviewGhost = 0;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setInt(1, days);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				ReviewBean rb=new ReviewBean();
				rb.setReviewCode(rs.getInt("reviewCode"));
				rb.setMediaCode(rs.getInt("mediaCode"));
				rb.setAccountCode(rs.getInt("accountCode"));
				rb.setNickName(rs.getString("nickName"));
				String preTrim = rs.getString("reviewContent");
				rb.setReviewContent(preTrim.substring(0, 30));
				rb.setReviewDate(rs.getString("reviewDate"));
				rb.setNetabare(rs.getInt("netabare"));
				list.add(rb);
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

	//アカウント情報を更新した時に、レビューの名前にも変更をかけるメソッド
	public int nickNameUpdate(int accountCode, String nickName) throws Exception{

		int line;
		Connection con=getConnection();

		String sql ="update review set nickname=? where accountcode=?";
		PreparedStatement st=con.prepareStatement(sql);

			st.setString(1, nickName);
			st.setInt(2, accountCode);

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

	//ユーザーマイページからレビュー一覧を取得するのに使用するメソッド
		public List<SearchReviewBean> userSearch(int accountCode) throws Exception {

			List<SearchReviewBean> list=new ArrayList<>(); //複数パラメータを流し込むのでBeansを使う
			Connection con= getConnection();

			String sql = "select M.*,R.* from media as M inner join review as R on M.mediaCode = R.mediaCode "
					+ "where accountCode = ?" + nonGhost;

			//SQL文をデータベースに送るためのオブジェクト生成
			PreparedStatement st=con.prepareStatement(sql);
					//引数にSQL文を書く
			st.setInt(1, accountCode);

			try {
				ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

				while (rs.next()) {
					SearchReviewBean srb=new SearchReviewBean();
					srb.setMediaCode(rs.getInt("mediaCode"));
					srb.setMediaTitle(rs.getString("mediaTitle"));
					srb.setMediaInfo(rs.getString("mediaInfo"));
					srb.setReviewCode(rs.getInt("reviewCode"));
					srb.setAccountCode(rs.getInt("accountCode"));
					srb.setNickName(rs.getString("nickName"));
					srb.setReviewContent(rs.getString("reviewContent"));
					srb.setReviewDate(rs.getString("reviewDate"));
					srb.setNetabare(rs.getInt("netabare"));
					//srb.setReviewGhost(rs.getInt("reviewGhost"));

					list.add(srb);

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


	public int checkIfPosted(String mediaCode, String accountCode) throws Exception {
		Connection con= getConnection();

		String sql = "select count(*) from review where mediaCode=? and accountCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setString(1, mediaCode);
		st.setString(2, accountCode);

		try {//
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {//rs.next()
				checkIfPosted = rs.getInt(1);

			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return checkIfPosted;
	}


	//管理者がレビューを管理する際に使用するメソッドその１
	public List<SearchReviewBean> searchReviewAdmin(String keyword, int select) throws Exception { //特定映像メディアのレビューを引っ張ってくる
		int selectMode = select;

		if(selectMode == 1) {//全レビューから検索(Ghost化したものも含む)
			mode = empty ;
		}else if(selectMode == 2) {//ゲスト・ユーザーが閲覧可能なレビューからの検索(Ghost化したものは除外)
			mode = " and reviewGhost = 0";
		}else {//削除済みレビューの検索(削除＝Ghost化)
			mode = " and reviewGhost = 1";
		}

		List<SearchReviewBean> list=new ArrayList<>(); //複数パラメータを流し込むのでBeansを使う
		Connection con= getConnection();

		//String concatSet = "nickName,reviewContent";
		String sql = "select M.*,R.* from media as M inner join review as R on M.mediaCode = R.mediaCode "
					+ "where concat (nickName,reviewContent) like ?" + mode;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setString(1, "%"+keyword+"%");

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				SearchReviewBean srb=new SearchReviewBean();
				srb.setMediaCode(rs.getInt("mediaCode"));
				srb.setMediaTitle(rs.getString("mediaTitle"));
				srb.setMediaInfo(rs.getString("mediaInfo"));
				srb.setReviewCode(rs.getInt("reviewCode"));
				srb.setAccountCode(rs.getInt("accountCode"));
				srb.setNickName(rs.getString("nickName"));
				srb.setReviewContent(rs.getString("reviewContent"));
				srb.setReviewDate(rs.getString("reviewDate"));
				srb.setNetabare(rs.getInt("netabare"));
				srb.setReviewGhost(rs.getInt("reviewGhost"));
				list.add(srb);

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

	//管理者がレビューを管理する際に使用するメソッドその２（削除・復元・編集後に変更後データを取得するのに使用）
	public List<SearchReviewBean> searchReviewCodeAdmin(String rCode, int select) throws Exception { //特定映像メディアのレビューを引っ張ってくる
		int selectMode = select;
		int reviewCode = Integer.parseInt(rCode);

		if(selectMode == 1) {//全レビューから検索(Ghost化したものも含む)
			mode = empty ;
		}else if(selectMode == 2) {//ゲスト・ユーザーが閲覧可能なレビューからの検索(Ghost化したものは除外)
			mode = " and reviewGhost = 0";
		}else {//削除済みレビューの検索(削除＝Ghost化)
			mode = " and reviewGhost = 1";
		}

		List<SearchReviewBean> list=new ArrayList<>(); //複数パラメータを流し込むのでBeansを使う
		Connection con= getConnection();

		//String concatSet = "nickName,reviewContent";
		String sql = "select M.*,R.* from media as M inner join review as R on M.mediaCode = R.mediaCode "
					+ "where reviewCode = ?" + mode;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setInt(1, reviewCode);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				SearchReviewBean srb=new SearchReviewBean();
				srb.setMediaCode(rs.getInt("mediaCode"));
				srb.setMediaTitle(rs.getString("mediaTitle"));
				srb.setMediaInfo(rs.getString("mediaInfo"));
				srb.setReviewCode(rs.getInt("reviewCode"));
				srb.setAccountCode(rs.getInt("accountCode"));
				srb.setNickName(rs.getString("nickName"));
				srb.setReviewContent(rs.getString("reviewContent"));
				srb.setReviewDate(rs.getString("reviewDate"));
				srb.setNetabare(rs.getInt("netabare"));
				srb.setReviewGhost(rs.getInt("reviewGhost"));
				list.add(srb);

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

	//
	public int insert(ReviewBean reviewBean) throws Exception {

		int line;
		Connection con = getConnection();

		//並び：reviewCode,meidaCode,accountCode,nickName,投稿日時,netabare
		String sql = "insert into review values(null, ?, ?, ?, ?, NOW(), ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, reviewBean.getMediaCode()); //mediaCode　詳細ページを開いた際にリクエスト属性使って取得？
		st.setInt(2, reviewBean.getAccountCode()); //accountCode　ログイン時のセッション情報からとる
		st.setString(3, reviewBean.getNickName()); //nickName　ログイン時のセッション情報からとる
		st.setString(4, reviewBean.getReviewContent()); //reviewContent　JSPからの入力を受け取る。
		st.setInt(5, 0); //netabare　デフォルトはゼロ。後でレビュー投下時にネタバレ可否部分を作るとかで対応。
		st.setInt(6, 0); //netabare　デフォルトはゼロ。後でレビュー投下時にネタバレ可否部分を作るとかで対応。

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


	//レビューの削除(レビューコードを選んで削除)
	//public int remove
	//(String reviewCode)
	//(ReviewBean ReviewBean)


	//レビューの削除(レビューコードを選んで削除)　あべ改変版
	public int remove(int reviewCode)

		throws Exception {
		int line;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"delete from review where reviewCode =?");


		//st.setString(1, reviewCode);
		//st.setInt(1, ReviewBean.getReviewCode());

		st.setInt(1, reviewCode);


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

	//レビューの削除、Ghost化して見えなくする。
	public int delete(String reviewCode) throws Exception {//Ghost化する
		int line; //更新なのでline数には変化がない
		Connection con=getConnection();

		//ghost=1でゴースト化
		//update media set ghost=1 where mediaCode=41;
		String sql = "update review set reviewGhost=1 where reviewCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, reviewCode);

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

	//レビューの復元、非Ghost化して見えるようにする。
	public int restore(String reviewCode) throws Exception {//Ghost化からの復元
		int line; //更新なのでline数には変化がない
		Connection con=getConnection();

		//ghost=1でゴースト化
		//update media set ghost=1 where mediaCode=41;
		String sql = "update review set reviewGhost=0 where reviewCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, reviewCode);

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

	//レビュー内容を編集し更新処理をする際に使用するメソッド
	public int update(String reviewCode, String reviewContent) throws Exception {//Ghost化からの復元
		int line; //更新なのでline数には変化がない
		Connection con=getConnection();

		//ghost=1でゴースト化
		//update media set ghost=1 where mediaCode=41;
		String sql = "update review set reviewContent=? where reviewCode=?";
		//update review set reviewContent='更新ほげ' where reviewCode=11;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(2, reviewCode);
		st.setString(1, reviewContent);

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

	//総reviewer数(ghost化したものは含まず)を得るメソッド
	public int totalReviewer() throws Exception {//Ghost化からの復元
		Connection con= getConnection();

		int totalReviewer = 0;
		//reviewでcount使ってとろうと思って上手くいかず、accoutから取ることにした。
		//明後日発表なのでこのままいく（accountDAO使うとインスタンス新たに立ち上げねば問題もあるので）
		String sql = "select count(*) from account where ghost=0";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		//st.setString(1, mediaCode);
		//st.setString(2, accountCode);

		try {//
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {//rs.next()
				totalReviewer = rs.getInt(1);

			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}

		return totalReviewer;
	}

	//総review数(ghost化したものは含まず)を得るメソッド
	public int totalReviews() throws Exception {//Ghost化からの復元
		Connection con= getConnection();

		int totalReviews = 0;
		String sql = "select count(*) from review where reviewghost=0";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		//st.setString(1, mediaCode);
		//st.setString(2, accountCode);

		try {//
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {//rs.next()
				totalReviews = rs.getInt(1);

			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}

		return totalReviews;
	}
}
