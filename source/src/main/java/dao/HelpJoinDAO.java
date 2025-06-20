package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Region;

public class HelpJoinDAO {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	String sql = "SELECT link FROM region WHERE region_id = (SELECT region_id FROM users WHERE user_id = ?)";
	int user = 1;

	public List<Region> getGarbageLink(int userId) {
		List<Region> region = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			st = conn.prepareStatement(sql);
			st.setInt(1, userId);
			System.out.println(st);
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getString("link") != null) {
					region.add(new Region(1, "", rs.getString("link")));
				
//		    ここから
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
	}
		System.out.println(region.get(0));
		return region;

	}

}
