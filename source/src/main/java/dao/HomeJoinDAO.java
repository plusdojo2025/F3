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
	    PreparedStatement st1 = null;
	    PreparedStatement st2 = null;
	    PreparedStatement st3 = null;
	    boolean checkInsert = false;

	    try {
	        conn = getConnection();
	        conn.setAutoCommit(false); // 🔸 トランザクション開始

	        // 🔹 INSERT into calendar
	        String sql1 = "INSERT INTO calendar (user_id, current) VALUES (?, ?)";
	        st1 = conn.prepareStatement(sql1);
	        st1.setInt(1, user_id);
	        st1.setDate(2, current);
	        int inserted = st1.executeUpdate();

	        // 🔹 UPDATE score
	        String sql2 = "UPDATE scorePoint SET score = ? WHERE user_id = ?";
	        st2 = conn.prepareStatement(sql2);
	        st2.setInt(1, score+1);
	        st2.setInt(2, user_id);
	        st2.executeUpdate();

	        // 🔹 UPDATE point
	        String sql3 = "UPDATE scorePoint SET point = ? WHERE user_id = ?";
	        st3 = conn.prepareStatement(sql3);
	        st3.setInt(1, point+1);
	        st3.setInt(2, user_id);
	        st3.executeUpdate();

	        conn.commit(); // ✅ すべて成功 → コミット
	        checkInsert = inserted > 0;
	        System.out.println("すべて正常に処理されました");

	    } catch (Exception e) {
	        if (conn != null) {
	            try {
	            	//エラーが合ったらrollback
	                conn.rollback();
	                System.out.println("エラーが発生したためロールバックしました");
	            } catch (Exception rbEx) {
	                rbEx.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st1 != null) st1.close();
	            if (st2 != null) st2.close();
	            if (st3 != null) st3.close();
	            if (conn != null) conn.setAutoCommit(true);
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return checkInsert;
	}
}
