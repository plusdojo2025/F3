package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Region;
import dto.Users;

public class UsersDAO {
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
				usersList.add(new Users(rs.getInt("user_id"), rs.getInt("region_id"), rs.getInt("icon_id"),
						rs.getInt("degree_id"), rs.getString("user_name"), rs.getString("password"),
						rs.getString("mail")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}

		return usersList;
	}

	public boolean isLoginOK(Users users) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SELECT文を準備する
			String sql = "SELECT count(*) FROM Users WHERE mail=? AND password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, users.getMail());
			pStmt.setString(2, users.getPassword());
			System.out.println(pStmt);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			System.out.println(rs);

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}

	public boolean createAccount(Users users) {
		Connection conn = null;
		boolean result = false;
		boolean create_user_result = false;
		boolean create_sp_result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			conn.setAutoCommit(false);

			// INSERT文を準備する
			String uSql = "insert into users (region_id, icon_id, degree_id, user_name, password, mail) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement uStmt = conn.prepareStatement(uSql);
			// // SQL文を完成させる

			/*
			 * String hashPw = users.getPassword(); byte[] cipher_byte; for (int i = 0; i <
			 * 10; i++) { MessageDigest md = MessageDigest.getInstance("SHA-256");
			 * md.update(hashPw.getBytes()); cipher_byte = md.digest(); StringBuilder sb =
			 * new StringBuilder(2 * cipher_byte.length); for (byte b : cipher_byte) {
			 * sb.append(String.format("%02x", b & 0xff)); } hashPw = sb.toString();
			 * System.out.println(i); System.out.println(hashPw); }
			 */
			if (users.getRegion_id() != 0) {
				uStmt.setInt(1, users.getRegion_id());
			} else {
				uStmt.setInt(1, 0);
			}
			if (users.getUser_name() != "") {
				uStmt.setInt(2, 1);
				uStmt.setInt(3, 1);
			} else {
				uStmt.setInt(2, 0);
				uStmt.setInt(3, 0);
			}
			if (users.getUser_name() != "") {
				uStmt.setString(4, users.getUser_name());
			} else {
				uStmt.setString(4, "");
			}
			if (users.getPassword() != "") {
				uStmt.setString(5, users.getPassword());
			} else {
				uStmt.setString(5, "");
			}
			if (users.getMail() != "") {
				uStmt.setString(6, users.getMail());
			} else {
				uStmt.setString(6, "");
			}

			// SQL文を実行する
			if (uStmt.executeUpdate() == 1) {
				create_user_result = true;
			}

			// SELECT文を準備する
			String selectSql = "SELECT user_id FROM Users WHERE mail=? AND password=?";
			PreparedStatement selectStmt = conn.prepareStatement(selectSql);
			selectStmt.setString(1, users.getMail());
			selectStmt.setString(2, users.getPassword());
			System.out.println(selectStmt);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = selectStmt.executeQuery();
			System.out.println(rs);

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			rs.next();
			int user_id = rs.getInt("user_id");
			System.out.println("user_id : " + user_id);
			
			String pSql = "insert into scorepoint (user_id) values (?)";
			PreparedStatement pStmt = conn.prepareStatement(pSql);
			
			if (users.getUser_id() != 0) {
				pStmt.setInt(1, 0);
				System.out.println("ue");
			} else {
				pStmt.setInt(1, user_id);
				System.out.println("sita");
			}

			if (pStmt.executeUpdate() == 1) {
				create_sp_result = true;
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		if (create_user_result && create_sp_result) {
			result = true;
		}
		return result;
	}

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