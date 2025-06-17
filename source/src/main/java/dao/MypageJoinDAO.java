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
	public MypageJoin mypageSelect(String mail){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		MypageJoin userinf = new MypageJoin();
		
		
		try{
				conn = getConnection();
				if (conn == null) {
					throw new Exception("データベース接続に失敗しました。");
				}

				st = conn.prepareStatement("SELECT point,degree_name,user_name,region_id,region_name,mail,icon_name,icon_id from (((users JOIN degree ON degree_id = degree_id) JOIN icon ON icon_id = icon_id) JOIN region ON region_id) WHERE mail = ?");
				st.setString(1,mail);
				rs = st.executeQuery();
				//データ格納
				userinf = new MypageJoin(rs.getInt("point"),rs.getString("degree_name"),rs.getString("user_name"),rs.getInt("regoin_id"),rs.getString("region_name"),rs.getString("mail"),rs.getString("icon_name"),rs.getInt("icon_id"));
				
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
			
			PreparedStatement pStmt = conn.prepareStatement("UPDATE users (region_id,icon_id,user_name,mail) SET (?,?,?,?)");
			
			//region_id
			pStmt.setInt(1, user.getRegion_id());
			//icon_id
			
			pStmt.setInt(2, user.getIcon_id());
			
			//user_name
			pStmt.setString(3, user.getUser_name());
			
			//mail
			pStmt.setString(4, user.getMail());
			
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
	
	
	
	//アイコン一覧取得メソッド
	private static final String SELECT_ICONS = "SELECT * FROM icon";
	
	public List<Icon> getIcons(){
		List<Icon> icon = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			PreparedStatement stmt = conn.prepareStatement(SELECT_ICONS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				icon.add(new Icon(rs.getInt("icon_id"), rs.getString("icon_name"),rs.getInt("price")));
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
