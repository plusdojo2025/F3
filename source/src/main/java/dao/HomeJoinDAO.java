package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.HomeJoin;

public class HomeJoinDAO {

	public HomeJoin homeGet(int userId, Date now) throws Exception {
		Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    HomeJoin home = new HomeJoin();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/f3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password");
	     // types
	        String sql1 = """
	            SELECT types
	            FROM garbage_type
	            WHERE region_id = (
	                SELECT region_id FROM users WHERE user_id = ?
	            )
	            AND region_date = ?;
	        """;
	        st = conn.prepareStatement(sql1);
	        st.setInt(1, userId);
	        st.setDate(2, now);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            home.setTypes(rs.getString("types"));
	        }
	        rs.close();
	        st.close();

	        // degree_name
	        String sql2 = """
	            SELECT degree_name
	            FROM degree
	            WHERE degree_id = (
	                SELECT region_id FROM users WHERE user_id = ?
	            );
	        """;
	        st = conn.prepareStatement(sql2);
	        st.setInt(1, userId);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            home.setDegree_name(rs.getString("degree_name"));
	        }
	        rs.close();
	        st.close();

	        // score, point
	        String sql3 = """
	            SELECT score, point
	            FROM scorePoint
	            WHERE user_id = ?;
	        """;
	        st = conn.prepareStatement(sql3);
	        st.setInt(1, userId);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            home.setScore(rs.getInt("score"));
	            home.setPoint(rs.getInt("point"));
	        }
	        rs.close();
	        st.close();

	        // current（チェック済み日付）
	        String sql4 = """
	            SELECT current
	            FROM calendar
	            WHERE user_id = ?
	            AND current = ?;
	        """;
	        st = conn.prepareStatement(sql4);
	        st.setInt(1, userId);
	        st.setDate(2, now);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            home.setCurrent(rs.getDate("current"));
	        }
	        rs.close();
	        st.close();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return home;
		
	}
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
	public boolean insertCal(int user_id, Date current, int score, int point) {
		Connection conn = null;
		PreparedStatement st = null;
		boolean checkInsert = false;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql1 = "insert into calendar values(?, ?)";
			
			rs = st.executeQuery();
			st.setInt(1, user_id);
			st.setDate(2, current);
			int check = st.executeUpdate();
			String sql2 = """					
							UPDATE scorePoint
							SET score = ?
							WHERE USER_ID=?;
						""";			
			st = conn.prepareStatement(sql2);
			st.setInt(1, user_id);
			st.setInt(2, score);
			
			
			
			String sql3 = """					
					UPDATE scorePoint
					SET point = ?
					WHERE USER_ID=?;
				""";
			st = conn.prepareStatement(sql1);
			st.setInt(1, user_id);
			st.setInt(2, point);
			
			
			
			
			if(check>0) {
				checkInsert = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkInsert;
	}
	public boolean updaPointScore(int score, int point) {
		return false;
	}
}
