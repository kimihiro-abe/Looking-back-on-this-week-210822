package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.AccountBean;
import tool.GetDateTime;

public class AccountDAO extends DAO {

	final private String nonGhost = " and ghost = 0"; //andの前にwhereが必要ならば入れる。0は非ghost状態。
	final private String Ghost = " and ghost = 1"; //andの前にwhereが必要ならば入れる。1はghost状態。
	private int mode;//検索モードセレクト時に使用する

	public AccountBean login(String login, String password)
		throws Exception {
		AccountBean account=null;

		Connection con= getConnection(); //p199 connectionオブジェクトを取得する
		String sql = "select * from account where loginID=? and password=?" + nonGhost; //ghost化していないユーザーのみを読み込む

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st;
		st = con.prepareStatement(sql);//引数にSQL文を書く
				//"select * from account where loginID=? and password=?"); //引数にSQL文を書く "select * from account where login=? and password=?"
		st.setString(1, login); //
		st.setString(2, password); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				account =new AccountBean();
				account.setAccountCode(rs.getInt("accountCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				account.setLoginID(rs.getString("loginID"));
				account.setPassword(rs.getString("password"));
				account.setNickName(rs.getString("nickName"));
				account.setRegisterDate(rs.getString("registerDate"));
				account.setAdmin(rs.getInt("admin"));
				account.setGhost(rs.getInt("ghost"));
			}
		}
		catch(Exception e) { //アカウントが存在しない、パスワード違い、ghost化している場合はNullPointerExceptionがでる。
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return account;
	}


//insert情報を追加 shimizu

	public int insert(AccountBean accountBean) throws Exception{
//public int insert(AccountBean accountBean, String loginID, String password,
//			String nickName, int admin, int ghost ) throws Exception{

		int line;
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"insert into account values(null, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?)");
//					"insert into account values(null, ?, ?, ?, now(), ?, ?)");

		st.setString(1, accountBean.getLoginID());
		st.setString(2, accountBean.getPassword());
		st.setString(3, accountBean.getNickName());
		st.setInt(4, 1); //権限設定:user = 1, admin = 2, 2以上の設定も可能だが悪戯に2以上は設定しないこと。
		st.setInt(5, 0); //ghost化設定、初回登録時は0設定で決め打ち

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


//delete情報を追加 shimizu

//	public int delete(AccountBean accountBean) throws  Exception{
//		int line;
//		Connection con=getConnection();
//
//		PreparedStatement st=con.prepareStatement(
//			"delete from account where accountCode =?");
//
//		st.setInt(1, accountBean.getAccountCode());
//
//		try {
//			line = st.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			st.close();
//			con.close();
//		}
//		return line;
//	}

	//ユーザー・管理者自身が『退会』として使用するdeleteメソッド
	//deleteとは記載したが実際にはGhost化して見えなくする。
	//引数で受け取るbeanの内容はAccountDelAction.javaで設定する。項目少ないと更新時に値がnullになる
	public int delete(AccountBean accountBean) throws Exception {
		int line; //更新なのでline数には変化がない
		Connection con = getConnection();
		String cLoginID = accountBean.getLoginID();// cはcurrentのC
		String cNickName = accountBean.getNickName();// cはcurrentのC
		int accountCode = accountBean.getAccountCode();

		//loginIDとnickNameを改変する
		//toolパッケージの中にあるGetDateTime.javaのstaticメソッドを使い現在時刻を取得し、
		//その時刻をloginIDとnickNameの末尾に付加してあげる
		String nowDateTime = GetDateTime.cDateTime();
		String changeLoginID = cLoginID + "+" + nowDateTime;
		String changeNickName = cNickName + "+" + nowDateTime;

		//ghost=1でゴースト化
		//update media set ghost=1 where mediaCode=41;
		String sql = "update account set ghost=1 ,loginID=? ,nickName=? where accountCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, changeLoginID);
		st.setString(2, changeNickName);
		st.setInt(3, accountCode);

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

	//ログインIDチェック(入力されたものと登録済データとのチェック)
	public int checkLoginID(String loginID) throws  Exception{
		Connection con= getConnection();
		PreparedStatement st=con.prepareStatement(
				"select * from account where loginID = ?");
		st.setString(1, loginID);
		ResultSet rs=st.executeQuery();

		int line = 0;
		while (rs.next()) {
			line++;
		}

		st.close();
		con.close();
		return line;
	}

	//ニックネームチェック(入力されたものと登録済データとのチェック)
	public int checkNickName(String nickName) throws  Exception{
		Connection con= getConnection();
		PreparedStatement st=con.prepareStatement(
				"select * from account where nickName = ?");
		st.setString(1, nickName);
		ResultSet rs=st.executeQuery();

		int line = 0;
		while (rs.next()) {
			line++;
		}

		st.close();
		con.close();
		return line;
	}

	//update(ユーザー情報更新)情報を追加 shimizu

	public int update(AccountBean accountBean) throws Exception{

		int line;
		Connection con=getConnection();

		String sql ="update account set"
				+ " loginID=?, password=?, nickName=? where accountCode=?";
		PreparedStatement st=con.prepareStatement(sql);

			st.setString(1, accountBean.getLoginID());
			st.setString(2, accountBean.getPassword());
			st.setString(3, accountBean.getNickName());
			st.setInt(4, accountBean.getAccountCode());

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


	//ログインIDチェック2(入力されたものと同一accountCodeを除く登録済データとのチェック)
	public int checkLoginID2(int accountCode, String loginID) throws  Exception{
		Connection con= getConnection();
		PreparedStatement st=con.prepareStatement(
				"select * from account where accountCode !=? and loginID = ?");
		st.setInt(1, accountCode);
		st.setString(2, loginID);
		ResultSet rs=st.executeQuery();

		int line = 0;
		while (rs.next()) {
			line++;
		}

		st.close();
		con.close();
		return line;
	}

	//ニックネームチェック2(入力されたものと同一accountCodeを除く登録済データとのチェック)
	public int checkNickName2(int accountCode, String nickName) throws  Exception{
		Connection con= getConnection();
		PreparedStatement st=con.prepareStatement(
				"select * from account where accountCode !=? and nickName = ?");
		st.setInt(1, accountCode);
		st.setString(2, nickName);
		ResultSet rs=st.executeQuery();

		int line = 0;
		while (rs.next()) {
			line++;
		}

		st.close();
		con.close();
		return line;
	}

	//ユーザー詳細表示メソッド
	public List<AccountBean> detail(String accountCode) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<AccountBean> list=new ArrayList<>();

		Connection con= getConnection();

		String sql ="select * from account where accountCode = ?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, accountCode); //

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				AccountBean ab=new AccountBean();
				ab.setAccountCode(rs.getInt("accountCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				ab.setLoginID(rs.getString("loginID"));
				ab.setPassword(rs.getString("password"));
				ab.setNickName(rs.getString("nickName"));
				ab.setRegisterDate(rs.getString("registerDate"));
				ab.setAdmin(rs.getInt("admin"));
				ab.setGhost(rs.getInt("ghost"));

				list.add(ab);

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




//～～～　以下は管理者マイページ関連のメソッドを格納する場所とさせてください（あべ）　～～～

	//管理者が使用するユーザー検索メソッド。リストを返す。
	public List<AccountBean> searchAccountList(String keyword, int select, int sessionAdmin, int sessionAcode)
		throws Exception {
		AccountBean account=null; //１：ユーザー(admin 1)、２：管理者(admin 2>)、３：ゴースト

		int sAdmin = sessionAdmin;
		String sql;
		mode = select;

		List<AccountBean> list=new ArrayList<>();
		Connection con= getConnection(); //p199 connectionオブジェクトを取得する

		//select * from account where nickName like '%kite%' and admin = 1 and ghost = 0;
		if(mode == 1) {
			sql = "select * from account where nickName like ? and admin = 1 and accountCode !=?" + nonGhost; //ユーザー（Ghost除く）を検索
		}

		else if(mode == 2 && sAdmin == 2) { //自分の権限が２の時には権限2の管理者しかデータを取らない

			sql = "select * from account where nickName like ? and admin = 2 and accountCode !=?" + nonGhost; //管理者（Ghost除く）を検索
		}
		else if(mode == 2 && sAdmin == 3) { //自分の権限が３の時には権限2以上の管理者データを取る

			sql = "select * from account where nickName like ? and admin >= 2 and accountCode !=?" + nonGhost; //管理者（Ghost除く）を検索
		}
		else {
			sql = "select * from account where nickName like ? and accountCode !=?" + Ghost; //削除済みアカ（Ghost化 ）を検索
		}

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st;
		st = con.prepareStatement(sql);//引数にSQL文を書く
				//"select * from account where loginID=? and password=?"); //引数にSQL文を書く "select * from account where login=? and password=?"
		st.setString(1, "%"+keyword+"%"); //
		st.setInt(2, sessionAcode); //


		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				AccountBean ab =new AccountBean();
				ab.setAccountCode(rs.getInt("accountCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				ab.setLoginID(rs.getString("loginID"));
				ab.setPassword(rs.getString("password"));
				ab.setNickName(rs.getString("nickName"));
				ab.setRegisterDate(rs.getString("registerDate"));
				ab.setAdmin(rs.getInt("admin"));
				ab.setGhost(rs.getInt("ghost"));
				list.add(ab);
			}
		}
		catch(Exception e) { //アカウントが存在しない、パスワード違い、ghost化している場合はNullPointerExceptionがでる。
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//管理者権限で削除・復元・編集を行ったあとで更新済みデータを取得するのに使用するメソッド
	public AccountBean searchDetail(String accountCode, int select_Mode)
		throws Exception {
		AccountBean account=null;
		String sql = null;
		mode = select_Mode;

		Connection con= getConnection(); //p199 connectionオブジェクトを取得する

		if(mode == 3) {//ghost化した特定アカウントをDBから持ってくる
			sql = "select * from account where accountCode=?" + Ghost; //ghost化していないユーザーのみを読み込む
		}
		else if(mode == 2) {//ghost化から復元した特定アカウントをDBから持ってくる
			sql = "select * from account where accountCode=?" + nonGhost; //ghost化していないユーザーのみを読み込む
		}

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st;
		st = con.prepareStatement(sql);//引数にSQL文を書く
				//"select * from account where loginID=? and password=?"); //引数にSQL文を書く "select * from account where login=? and password=?"
		st.setString(1, accountCode);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				account =new AccountBean();
				account.setAccountCode(rs.getInt("accountCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				account.setLoginID(rs.getString("loginID"));
				account.setPassword(rs.getString("password"));
				account.setNickName(rs.getString("nickName"));
				account.setRegisterDate(rs.getString("registerDate"));
				account.setAdmin(rs.getInt("admin"));
				account.setGhost(rs.getInt("ghost"));
			}
		}
		catch(Exception e) { //アカウントが存在しない、パスワード違い、ghost化している場合はNullPointerExceptionがでる。
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return account;
	}

	//変更後の詳細をリストで受け取る。
	public List<AccountBean> searchAfterDetail(String accountCode, int select_Mode)
			throws Exception {
		List<AccountBean> list=new ArrayList<>(); //JSPでforEachを使うにはコレクション（Listとか）でないといかんので

		AccountBean account=null;
		String sql = null;
		mode = select_Mode;

		Connection con= getConnection(); //p199 connectionオブジェクトを取得する

		if(mode == 3) {//ghost化した特定アカウントをDBから持ってくる
			sql = "select * from account where accountCode=?" + Ghost; //ghost化していないユーザーのみを読み込む
		}
		else if(mode == 2) {//ghost化から復元した特定アカウントをDBから持ってくる
			sql = "select * from account where accountCode=?" + nonGhost; //ghost化していないユーザーのみを読み込む
		}
		else {//modeの値を上記以外にした場合。まぁ、１とか入れるのが妥当。
			sql = "select * from account where accountCode=?"; //ghost,非ghost問わず
		}


		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st;
		st = con.prepareStatement(sql);//引数にSQL文を書く
				//"select * from account where loginID=? and password=?"); //引数にSQL文を書く "select * from account where login=? and password=?"
		st.setString(1, accountCode);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				AccountBean ac =new AccountBean();
				ac.setAccountCode(rs.getInt("accountCode")); //データベースのカラム名。String型なので必要あらば型を変換。
				ac.setLoginID(rs.getString("loginID"));
				ac.setPassword(rs.getString("password"));
				ac.setNickName(rs.getString("nickName"));
				ac.setRegisterDate(rs.getString("registerDate"));
				ac.setAdmin(rs.getInt("admin"));
				ac.setGhost(rs.getInt("ghost"));

				list.add(ac);
			}
		}
		catch(Exception e) { //アカウントが存在しない、パスワード違い、ghost化している場合はNullPointerExceptionがでる。
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return list;
	}

	//変更後の詳細をbeanで受け取る。
	public AccountBean searchAfterDetailBean(String accountCode, int select_Mode)
			throws Exception {
		AccountBean account=null;
		String sql = null;
		mode = select_Mode;

		Connection con= getConnection(); //p199 connectionオブジェクトを取得する

		if(mode == 3) {//ghost化した特定アカウントをDBから持ってくる
			sql = "select * from account where accountCode=?" + Ghost; //ghost化していないユーザーのみを読み込む
		}
		else if(mode == 2) {//ghost化から復元した特定アカウントをDBから持ってくる
			sql = "select * from account where accountCode=?" + nonGhost; //ghost化していないユーザーのみを読み込む
		}
		else {//modeの値を上記以外にした場合。まぁ、１とか入れるのが妥当。
			sql = "select * from account where accountCode=?"; //ghost,非ghost問わず
		}


		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st;
		st = con.prepareStatement(sql);//引数にSQL文を書く
				//"select * from account where loginID=? and password=?"); //引数にSQL文を書く "select * from account where login=? and password=?"
		st.setString(1, accountCode);

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				account =new AccountBean();
				account.setAccountCode(rs.getInt("accountCode"));
				account.setLoginID(rs.getString("loginID"));
				account.setPassword(rs.getString("password"));
				account.setNickName(rs.getString("nickName"));
				account.setRegisterDate(rs.getString("registerDate"));
				account.setAdmin(rs.getInt("admin"));
				account.setGhost(rs.getInt("ghost"));

			}
		}
		catch(Exception e) { //アカウントが存在しない、パスワード違い、ghost化している場合はNullPointerExceptionがでる。
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return account;
	}

	//アカウントの削除、Ghost化して見えなくする。
	public int delete(String accountCode, String loginID, String nickname) throws Exception { //Ghost化する
		int line; //更新なのでline数には変化がない
		String cLoginID = loginID;// cはcurrentのC
		String cNickName = nickname;// cはcurrentのC
		Connection con = getConnection();

		//loginIDとnickNameを改変する
		//toolパッケージの中にあるGetDateTime.javaのstaticメソッドを使い現在時刻を取得し、
		//その時刻をloginIDとnickNameの末尾に付加してあげる
		String nowDateTime = GetDateTime.cDateTime();
		String changeLoginID = cLoginID + "+" + nowDateTime;
		String changeNickName = cNickName + "+" + nowDateTime;

		//ghost=1でゴースト化
		//update media set ghost=1 where mediaCode=41;
		String sql = "update account set ghost=1 ,loginID=? ,nickName=? where accountCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, changeLoginID);
		st.setString(2, changeNickName);
		st.setString(3, accountCode);

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

	//アカウントーの復元、非Ghost化して見えるようにする。
	public int restore(String accountCode, String loginID, String nickname) throws Exception {//Ghost化からの復元
		int line; //更新なのでline数には変化がない
		String cLoginID = loginID;// cはcurrentのC
		String cNickName = nickname;// cはcurrentのC
		Connection con=getConnection();

		String ghostText = cLoginID.substring(cLoginID.length()-15);
		String restoreLoginID = cLoginID.replace(ghostText, "");
		String restoreNickName = cNickName.replace(ghostText, "");
		//ghost=1でゴースト化
		//update media set ghost=1 where mediaCode=41;
		String sql = "update account set ghost=0 ,loginID=? ,nickName=? where accountCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setString(1, restoreLoginID);
		st.setString(2, restoreNickName);
		st.setString(3, accountCode);

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

	//アカウントの管理者数を確認するメソッド
	public int deleteCheckAdmin(int admin) throws Exception {//Ghost化からの復元
		int count = 0; //DBからとってきた数字
		int currentAdmin = admin;// cはcurrentのC

		Connection con=getConnection();

		//管理者数を調べる
		String sql = "select count(*) from account where admin = ?" + nonGhost;

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setInt(1, currentAdmin);


		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {
				count = rs.getInt(1);

			}
		} catch (Exception e) {
			throw e;
		} finally {
			st.close();
			con.close();
		}
		return count;
	}

	//admin権限の付与と剥奪を操作するメソッド
	public int authorityChangeAdmin(int targetAccountCode, int targetAdmin) throws Exception {//Ghost化からの復元
		int line = 0; //DBからとってきた数字

		Connection con=getConnection();

		//管理者数を調べる
		String sql = "update account set admin=? where accountCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql); //引数にSQL文を書く
		st.setInt(1, targetAdmin);
		st.setInt(2, targetAccountCode);

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

}
