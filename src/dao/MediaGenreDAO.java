package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.MediaGenreBean;

public class MediaGenreDAO extends DAO{

	public List<MediaGenreBean> loadGenreName(String sqlTxt) throws Exception {//複数パラメータを流し込むのでBeansを使う
		List<MediaGenreBean> list=new ArrayList<>();
		String addSQL = sqlTxt;

		Connection con= getConnection();

		String sql ="select * from " + addSQL; //addSQLに「media_genre」を入れることでジャンル一覧は取れる。

		//SQL文をデータベースに送るためのオブジェクト生成
		PreparedStatement st=con.prepareStatement(sql);//引数にSQL文を書く

		try {
			ResultSet rs=st.executeQuery(); //クエリの結果を保持するクラス

			while (rs.next()) {  //ひとまずタイトルだけ持ってくる。
				MediaGenreBean mgb=new MediaGenreBean();
				mgb.setGenre_code(rs.getInt("genre_code"));
				mgb.setGenre_name(rs.getString("genre_name"));

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

}
