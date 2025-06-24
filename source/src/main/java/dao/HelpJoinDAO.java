package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.HelpJoin;

public class HelpJoinDAO {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public HelpJoin getGarbageLink(int userId) {
		HelpJoin help = new HelpJoin();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		System.out.println("help1番");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			String sql = "SELECT link FROM region WHERE region_id = (SELECT region_id FROM users WHERE user_id = ?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, userId);
			System.out.println(st);
			rs = st.executeQuery();
			if (rs.next()) {
				String link = rs.getString("link");
	            if (link != null) {
	                help.setLink(link); // ← Regionにセット
	                System.out.println("linkは。。。"+link);
	            }
				}
		    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (st != null) st.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	    System.out.println(help.getLink());
	    return help;
	}

}
