package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GarbageRule;

public class GarbageRuleDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/garbage_calendar?serverTimezone=UTC";
    private static final String USER = "root";       // 環境に合わせて変更
    private static final String PASS = "password";   // 環境に合わせて変更

    static {
        try {
            // JDBCドライバを明示的にロード（JDBC4以降は不要な場合もありますがトラブル防止で）
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<GarbageRule> getRules() throws SQLException {
        List<GarbageRule> rules = new ArrayList<>();

        String sql = "SELECT type, rule, interval_months, start_month FROM garbage_rules";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GarbageRule r = new GarbageRule();
                r.setType(rs.getString("type"));
                r.setRule(rs.getString("rule"));
                r.setIntervalMonths(rs.getInt("interval_months"));
                r.setStartMonth(rs.getInt("start_month"));
       
                rules.add(r);
            }
        }

        return rules;
    }
}
