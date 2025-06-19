package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Icon;
import dto.MypageJoin;
import dto.Region;
import dto.Users;

public class MypageJoinDAO {
	// データベース接続メソッド
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
	//ユーザー情報を取得するメソッド
	public MypageJoin mypageSelect(int id){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		MypageJoin userinf = new MypageJoin();
		
		
		try{
				conn = getConnection();
				if (conn == null) {
					throw new Exception("データベース接続に失敗しました。");
				}

				st = conn.prepareStatement("SELECT U.user_id,point,D.degree_name,U.user_name,R.region_id,R.region_name,U.mail,I.icon_name,I.icon_id from ((((users U JOIN degree D ON U.degree_id = D.degree_id) JOIN icon I ON U.icon_id = I.icon_id) JOIN region R ON U.region_id=R.region_id)JOIN scorepoint S ON U.user_id=S.user_id) WHERE U.user_id = ?;");
				st.setInt(1,id);
				rs = st.executeQuery();
				//データ格納
				rs.next();
				userinf = new MypageJoin(rs.getInt("user_id"),rs.getInt("point"),rs.getString("degree_name"),rs.getString("user_name"),rs.getInt("region_id"),rs.getString("region_name"),rs.getString("mail"),rs.getString("icon_name"),rs.getInt("icon_id"));
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userinf;
	}
	//ユーザー情報を更新するメソッド
	
	public boolean mypageUpdate(Users user) {
		Connection conn = null;
		boolean result = false;
		
		try{
			
			conn = getConnection();
			if (conn == null) {
				throw new Exception("データベース接続に失敗しました。");
			}
			
			PreparedStatement pStmt = conn.prepareStatement("UPDATE users SET region_id = ?, icon_id = ?, user_name = ?,mail = ? WHERE user_id = ?");
			
			//region_id
			pStmt.setInt(1, user.getRegion_id());
			//icon_id
			
			pStmt.setInt(2, user.getIcon_id());
			
			//user_name
			pStmt.setString(3, user.getUser_name());
			
			//mail
			pStmt.setString(4, user.getMail());
			
			//条件(uid)
			pStmt.setInt(5,user.getUser_id());
			
			
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return result;
	}
	
	
	
	//保持アイコン一覧取得メソッド-----要修正
	private static final String SELECT_ICONS = "SELECT C.icon_id,C.icon_name FROM icon C JOIN iconstatus L ON C.icon_id=L.icon_id WHERE L.user_id=?";
	
	public List<Icon> getIcons(int id){
		List<Icon> icon = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			PreparedStatement stmt = conn.prepareStatement(SELECT_ICONS);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				icon.add(new Icon(rs.getInt("icon_id"), rs.getString("icon_name"),0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return icon;
	}
	
	//地域取得メソッド
	private static final String SELECT_REGIONS = "SELECT region_id, region_name FROM region";

	public List<Region> getRegions() {
		List<Region> regions = new ArrayList<>();
		Connection conn = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			PreparedStatement stmt = conn.prepareStatement(SELECT_REGIONS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				regions.add(new Region(rs.getInt("region_id"), rs.getString("region_name"), ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regions;
	}
}
