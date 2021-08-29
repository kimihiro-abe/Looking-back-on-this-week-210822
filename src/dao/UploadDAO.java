package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.UploadBean;

public class UploadDAO extends DAO {

	private int checkIfPostedMC;

	//ファイルネームで検索
	public List<UploadBean> search(String filename) throws Exception {
		List<UploadBean> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from upload where filename like ?");
		st.setString(1, "%"+filename+"%");
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			UploadBean p=new UploadBean();
			p.setId(rs.getInt("id"));
			p.setMediaCode(rs.getInt("mediaCode"));
			p.setFilename(rs.getString("filename"));
			p.setFilePath(rs.getString("filePath"));
			list.add(p);
		}

		st.close();
		con.close();

		return list;
	}

	//メディアコードで検索　返りがリスト。DBの全データを持ってくる。/upload/SearchAction.javaで使用
	public List<UploadBean> imageURLs(String mediaCode) throws Exception {
		List<UploadBean> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from upload where mediaCode=?");
		st.setString(1, mediaCode);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			UploadBean p=new UploadBean();
			p.setId(rs.getInt("id"));
			p.setMediaCode(rs.getInt("mediaCode"));
			p.setMediaTitle(rs.getString("mediaTitle"));
			p.setFilename(rs.getString("filename"));
			p.setFilePath(rs.getString("filePath"));

//			System.out.println("mediaCode: " + rs.getInt("mediaCode"));
//			System.out.println("mediaTitle: " + rs.getString("mediaTitle"));
//			System.out.println("filename " + rs.getString("filename"));
//			System.out.println("filepath: " + rs.getString("filePath"));

			list.add(p);
		}

		st.close();
		con.close();

		return list;
	}

	//引数無しでDBの全データを持ってくる。/searchMedia/SearchMediaAction.javaで使用
	public List<UploadBean> imageInfoAll() throws Exception {
		List<UploadBean> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from upload where mediaCode");
		//st.setString(1, "%"+mediaCode+"%");
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			UploadBean p=new UploadBean();
			p.setId(rs.getInt("id"));
			p.setMediaCode(rs.getInt("mediaCode"));
			p.setMediaTitle(rs.getString("mediaTitle"));
			p.setFilename(rs.getString("filename"));
			p.setFilePath(rs.getString("filePath"));

//			System.out.println("mediaCode: " + rs.getInt("mediaCode"));
//			System.out.println("mediaTitle: " + rs.getString("mediaTitle"));
//			System.out.println("filename " + rs.getString("filename"));
//			System.out.println("filepath: " + rs.getString("filePath"));

			list.add(p);
		}

		st.close();
		con.close();

		return list;
	}

	/*	//　List<Integer>検索　返りがリスト
		public List<UploadBean> imageURLs2(List searchMcode) throws Exception {
			List<UploadBean> list=new ArrayList<>();

			Connection con=getConnection();

			for(int i=0; i<searchMcode.size(); i++) {
					String e = (String) searchMcode.get(i);


				PreparedStatement st=con.prepareStatement(
					"select * from upload where mediaCode=?");
				st.setString(1, e);
				ResultSet rs=st.executeQuery();
				System.out.println("e: " + e);

				while (rs.next()) {
					UploadBean p=new UploadBean();
					p.setId(rs.getInt("id"));
					p.setMediaCode(rs.getInt("mediaCode"));
					p.setMediaTitle(rs.getString("mediaTitle"));
					p.setFilename(rs.getString("filename"));
					p.setFilePath(rs.getString("filePath"));

					System.out.println("mediaCode: " + rs.getInt("mediaCode"));
					System.out.println("mediaTitle: " + rs.getString("mediaTitle"));
					System.out.println("filename " + rs.getString("filename"));
					System.out.println("filepath: " + rs.getString("filePath"));

					list.add(p);
				}
			}

			//st.close();
			con.close();

			return list;

		}*/

	//メディアコードで検索　返しがbean。
	public UploadBean imageURL(String mediaCode) throws Exception {
		UploadBean ub=new UploadBean();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"select * from upload where mediaCode=?");
		st.setString(1, mediaCode);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			ub.setId(rs.getInt("id"));
			ub.setMediaCode(rs.getInt("mediaCode"));
			ub.setFilename(rs.getString("filename"));
			ub.setFilePath(rs.getString("filePath"));

		}

		st.close();
		con.close();

		return ub;
	}

	//DBのuploadに、同メディアコードの登録が幾つあるか見る。
	public int checkIfPosted_mediaCode(String mediaCode) throws Exception {
		Connection con= getConnection();

		String sql = "select count(*) from upload where mediaCode=?";

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);
				//引数にSQL文を書く
		st.setString(1, mediaCode);

		try {//
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {//rs.next()
				checkIfPostedMC = rs.getInt(1);

			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			st.close();
			con.close();
		}
		return checkIfPostedMC;
	}

	/*	//画像のDB登録
		public int insert(int mediaCode, String fileName, String filePath) throws Exception {
			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement(
				"insert into upload values(null, ?, ?, ?)");
			st.setInt(1, mediaCode);
			st.setString(2, fileName);// mediaCodeを引っ張ってくるようにする
			st.setString(3, filePath);// mediaCodeを引っ張ってくるようにする
			int line=st.executeUpdate();

			st.close();
			con.close();
			return line;
		}*/

	//画像のDB登録 Beanで登録する
	public int insert(UploadBean uploadBean) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into upload values(null, ?, ?, ?, ?)");
		st.setInt(1, uploadBean.getMediaCode());
		st.setString(2, uploadBean.getMediaTitle());// mediaCodeを引っ張ってくるようにする
		st.setString(3, uploadBean.getFilename());// mediaCodeを引っ張ってくるようにする
		st.setString(4, uploadBean.getFilePath());// mediaCodeを引っ張ってくるようにする
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}

	//DBからの削除処理
	public int delete(String filename, int mediaCode) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"delete from upload where filename and mediacode=?");
		st.setString(1, filename);
		st.setInt(2, mediaCode);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}


}
