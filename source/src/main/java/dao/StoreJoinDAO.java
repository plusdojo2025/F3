package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.IconStatus;
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
			String sql = "INSERT INTO iconstatus (user_id, icon_id) VALUES (1, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);//保持アイコンに追加するSQL
			String sql2 = "UPDATE scorepoint SET point = point - ? WHERE user_id = 1;";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

			
			 String checkSql = "SELECT point FROM scorepoint WHERE user_id = 1;";
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
	
	//ポイント表示処理
	public int getpoint(StoreJoin List) {
		Connection conn = null;
        int points = 0;
        String sql = "SELECT point FROM scorepoint WHERE user_id = 1;";

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
	
	//保持アイコン一覧取得メソッド
		public List<IconStatus> getIcons(int id){
			List<IconStatus> iconid = new ArrayList<>();
			Connection conn = null;
			String sql = "SELECT user_id, icon_id FROM iconstatus WHERE user_id=?";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1,id);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
	                IconStatus icon = new IconStatus(rs.getInt("user_id"), rs.getInt("icon_id"));
	                iconid.add(icon);
	            }
			}catch (SQLException e) {
	            e.printStackTrace();
	        }catch (ClassNotFoundException e) {
				e.printStackTrace();
				iconid = null;
			}
			return iconid;
		}
}
