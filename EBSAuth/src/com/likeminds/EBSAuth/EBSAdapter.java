package com.likeminds.EBSAuth;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class EBSAdapter {

	// ORACLE JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@ebs.likemindscloud.com:1528:VIS";

	// Database credentials
	static final String USER = "apps";
	static final String PASS = "apps";

	Connection conn = null;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		return conn;
	}

	public static void reset_session(int p_session_id) {
		try {
			Connection conn = EBSAdapter.getConnection();
			String plSql = "{call FND_SESSION_MANAGEMENT.reset_session(?)}";
			CallableStatement stmt = conn.prepareCall(plSql);
			stmt.setInt(1, p_session_id);
			stmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String getxid(int sid) {
		String user1 = null;
		try {
			Connection conn = EBSAdapter.getConnection();
			PreparedStatement pstmt1 = null;
			ResultSet rs1 = null;
			pstmt1 = conn.prepareStatement("select xsid from icx_sessions where session_id = ?");
			pstmt1.setInt(1, sid);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				user1 = rs1.getString(1);
				System.out.println("XSID = " + " " + user1);
			}
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return user1;
	}

	public static int create_session(int user_id) {

		int result = 0;
		try {
			Connection conn = EBSAdapter.getConnection();
			String plSql = "{ ? = call FND_SESSION_MANAGEMENT.CREATESESSION( ?, ?, ?, ?, ?, ?, ? ) }";
			CallableStatement stmt = conn.prepareCall(plSql);
			// stmt.setQueryTimeout(1800);
			stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			stmt.setInt(2, user_id);
			stmt.setString(3, "115J");
			stmt.setNull(4, oracle.jdbc.OracleTypes.NUMBER);
			stmt.setString(5, null);
			stmt.setString(6, null);
			stmt.setString(7, "US");
			stmt.setNull(8, oracle.jdbc.OracleTypes.NUMBER);
			stmt.execute();

			result = stmt.getInt(1);
			System.out.println(" Session ID ==== " + result);
			conn.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return result;
	}

}
