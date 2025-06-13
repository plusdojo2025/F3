package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Users;

public class UsersDAO {
    // データベース接続メソッド
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webcontent?"
                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
                "root", "password");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 全ユーザーを取得するメソッド
    public List<Users> allUsers() throws Exception {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Users> usersList = new ArrayList<>();

        try {
            conn = getConnection();
            if (conn == null) {
                throw new Exception("データベース接続に失敗しました。");
            }

            st = conn.prepareStatement("SELECT * FROM USERS");
            rs = st.executeQuery();

            while (rs.next()) {
                usersList.add(new Users(
                    rs.getInt("user_id"),
                    rs.getInt("region_id"),
                    rs.getInt("icon_id"),
                    rs.getInt("degree_id"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getString("mail")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        }

        return usersList;
    }
}
