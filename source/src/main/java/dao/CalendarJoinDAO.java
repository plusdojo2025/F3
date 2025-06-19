package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.CalendarJoin;

public class CalendarJoinDAO {

	public Map<Date, List<CalendarJoin>> getCalendarJoinMapByUserId(int userId) throws Exception {
	    Map<Date, List<CalendarJoin>> resultMap = new HashMap<>();
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/F3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password");

	        // region_id を取得
	        int regionId = -1;
	        PreparedStatement regionSt = conn.prepareStatement("SELECT region_id FROM users WHERE user_id = ?");
	        regionSt.setInt(1, userId);
	        ResultSet regionRs = regionSt.executeQuery();
	        if (regionRs.next()) {
	            regionId = regionRs.getInt("region_id");
	        }
	        regionRs.close();
	        regionSt.close();

	        // region_date と types を取得
	        String sql = """
	            SELECT region_date, types
	            FROM garbage_type
	            WHERE region_id = (
	                SELECT region_id FROM users WHERE user_id = ?
	            )
	        """;
	        st = conn.prepareStatement(sql);
	        st.setInt(1, userId);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            Date regionDate = rs.getDate("region_date");
	            String types = rs.getString("types");

	            CalendarJoin bean = new CalendarJoin();
	            bean.setRegion_date(regionDate);
	            bean.setTypes(types);

	            resultMap.computeIfAbsent(regionDate, k -> new ArrayList<>()).add(bean);
	        }
	        rs.close();
	        st.close();

	        // チェック済み日付を取得して反映
	        PreparedStatement currentSt = conn.prepareStatement("SELECT current FROM calendar WHERE user_id = ?");
	        currentSt.setInt(1, userId);
	        ResultSet currentRs = currentSt.executeQuery();

	        while (currentRs.next()) {
	            Date checkedDate = currentRs.getDate("current");

	            CalendarJoin bean = new CalendarJoin();
	            bean.setCurrent(checkedDate);  // これは region_date と同じでよい
	            bean.setTypes("チェック済み");

	            resultMap.computeIfAbsent(checkedDate, k -> new ArrayList<>()).add(0, bean);  // 先頭に追加
	        }

	        currentRs.close();
	        currentSt.close();

	    } finally {
	        if (rs != null) rs.close();
	        if (st != null) st.close();
	        if (conn != null) conn.close();
	    }

	    return resultMap;
	}
	public List<CalendarJoin> getLink(int userId) throws Exception {
		System.out.println("getLinkD確認");
		System.out.println("getLink-User="+userId);
		List<CalendarJoin> join = new ArrayList<>();
		Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/f3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password");
	     // types
	        String sql1 = """
		            SELECT link
		            FROM region
		            WHERE region_id = (
		                SELECT region_id FROM users WHERE user_id = ?
		            );
		        """;
	        st = conn.prepareStatement(sql1);
	        st.setInt(1, userId);
	        rs = st.executeQuery();
	        while (rs.next()) {
	        	CalendarJoin calendar_join = new CalendarJoin();
	        	calendar_join.setLink(rs.getString("link"));
	        	join.add(calendar_join);
	        }

	        rs.close();
	        st.close();
	    }  catch (Exception e) {
			e.printStackTrace();
	    } finally {
	        if (rs != null) try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
	        if (st != null) try { st.close(); } catch (Exception e) { e.printStackTrace(); }
	        if (conn != null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
	    }
						
		return join;
	}
}
