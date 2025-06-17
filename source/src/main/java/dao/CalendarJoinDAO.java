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

public class CalendarJoinDAO {

	public Map<Date, List<String>> getGarbageTypesByUserId(int userId) throws Exception {
	    Map<Date, List<String>> resultMap = new HashMap<>();
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/webcontent?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
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

	        // region_date, types を取得
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
	            Date date = rs.getDate("region_date");
	            String type = rs.getString("types");
	            resultMap.computeIfAbsent(date, k -> new ArrayList<>()).add(type);
	        }
	        rs.close();
	        st.close();

	        // current を取得
	        Date current = null;
	        PreparedStatement currentSt = conn.prepareStatement("SELECT current FROM calendar WHERE user_id = ?");
	        currentSt.setInt(1, userId);
	        ResultSet currentRs = currentSt.executeQuery();
	        if (currentRs.next()) {
	            current = currentRs.getDate("current");
	        }
	        currentRs.close();
	        currentSt.close();

	        // current に一致する日付の List<String> の先頭に "チェック済み" を追加
	        if (current != null && resultMap.containsKey(current)) {
	            List<String> list = resultMap.get(current);
	            list.add(0, "チェック済み");
	        }

	    } finally {
	        if (rs != null) rs.close();
	        if (st != null) st.close();
	        if (conn != null) conn.close();
	    }

	    return resultMap;
	}

}
