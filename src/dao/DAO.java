package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	static DataSource ds;

		public Connection getConnection() throws Exception {
			if (ds==null) {
				InitialContext ic=new InitialContext(); //これを窓口にデータソースを取得
				ds=(DataSource)ic.lookup("java:/comp/env/jdbc/gogo_search");
				// ds=(DataSource)ic.lookup("java:/comp/env/jdbc/book");
			}
			return ds.getConnection();
		}

}
