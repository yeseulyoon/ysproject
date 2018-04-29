package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	static ResourceBundle resource = null;
	static DataSource ds;
	static {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
			resource =ResourceBundle.getBundle("conf/sql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static ResourceBundle getResourceBundle(){
		return resource;
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public static void close(Connection con, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
