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
	            Date date = rs.getDate("region_date");
	            String type = rs.getString("types");
	            resultMap.computeIfAbsent(date, k -> new ArrayList<>()).add(type);
	        }
	        rs.close();
	        st.close();

	        // calendar にある current をすべて取得
	        PreparedStatement currentSt = conn.prepareStatement("SELECT current FROM calendar WHERE user_id = ?");
	        currentSt.setInt(1, userId);
	        ResultSet currentRs = currentSt.executeQuery();
	        while (currentRs.next()) {
	            Date checkedDate = currentRs.getDate("current");

	            // すでに region_date にある → "チェック済み" を先頭に追加
	            if (resultMap.containsKey(checkedDate)) {
	                List<String> list = resultMap.get(checkedDate);
	                if (!list.contains("チェック済み")) {
	                    list.add(0, "チェック済み");
	                }
	            } else {
	                // 存在しない → "チェック済み" のみの新しいエントリを追加
	                List<String> list = new ArrayList<>();
	                list.add("チェック済み");
	                resultMap.put(checkedDate, list);
	            }
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


}
