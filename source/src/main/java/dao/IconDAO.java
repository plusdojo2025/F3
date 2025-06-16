package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Icon;

public class IconDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/WebContent";
    private static final String USER = "root";
    private static final String PASS = "password";
    
    public List<Icon> getAllIcon() {
        List<Icon> iconList = new ArrayList<>();
        String sql = "SELECT icon_id, icon_name, price FROM icons";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Icon icon = new Icon(rs.getInt("icon_id"), rs.getString("icon_name"), rs.getInt("price"));
                iconList.add(icon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return iconList;
    }
}


