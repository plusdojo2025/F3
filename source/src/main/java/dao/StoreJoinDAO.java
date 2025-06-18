package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.StoreJoin;

public class StoreJoinDAO {
	public boolean update(StoreJoin List) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO iconstatus (user_id, icon_id) VALUES (2, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);//保持アイコンに追加するSQL
			String sql2 = "UPDATE scorepoint SET point = point - ? WHERE user_id = 2;";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

			
			 String checkSql = "SELECT point FROM scorepoint WHERE user_id = 2;";
		     PreparedStatement checkStmt = conn.prepareStatement(checkSql);
		     ResultSet rs = checkStmt.executeQuery();
		     if (rs.next()) {
		            int currentPoints = rs.getInt("point");

		            // ポイントがマイナスになる場合はSQLを実行せずfalseを返す
		            if (currentPoints - List.getPrice() < 0) {
		            	result = false;
		                return result;
		            }
		        }
			// SQL文を完成させる
			
			pStmt.setInt(1, List.getIcon_id());
			pStmt.executeUpdate();
			
			pStmt2.setInt(1,List.getPrice());
			//pStmt2.setInt(2,List.getUser_id());
			pStmt2.executeUpdate();
			
			result = true;
			// SQL文を実行する
//			if (pStmt.executeUpdate() == 1) {
//				result = true;
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}
	
	public int getpoint(StoreJoin List) {
		Connection conn = null;
        int points = 0;
        String sql = "SELECT point FROM scorepoint WHERE user_id = 2;";

        try  {
        	// JDBCドライバを読み込む
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             try (ResultSet rs = pstmt.executeQuery()) {
                 if (rs.next()) {
                     points = rs.getInt("point");
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
 			// データベースを切断
 			if (conn != null) {
 				try {
 					conn.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 			}
         }
        return points;
	}
}
