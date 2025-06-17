package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Icon;

//	ストア一覧表示処理
public class IconDAO {
    public List<Icon> getAllIcon() {
    	Connection conn = null;
        List<Icon> iconList = new ArrayList<>();
        String sql = "SELECT icon_id, icon_name, price FROM icon;";

        try  {
        	// JDBCドライバを読み込む
        	Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Icon icon = new Icon(rs.getInt("icon_id"), rs.getString("icon_name"), rs.getInt("price"));
                iconList.add(icon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
			e.printStackTrace();
			iconList = null;
		}

        return iconList;
    }
}
//ストア一覧表示処理終わり

