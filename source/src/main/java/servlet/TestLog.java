package servlet;

import java.util.List;

import dao.UsersDAO;
import dto.Users;

public class TestLog {
    public static void main(String[] args) {
        try {
            // DAOインスタンスを作成
            UsersDAO usersDAO = new UsersDAO();

            // ユーザー一覧を取得
            List<Users> usersList = usersDAO.allUsers();

            // 取得したユーザー情報を表示
            if (usersList.isEmpty()) {
                System.out.println("ユーザー情報がありません。");
            } else {
                for (Users user : usersList) {
                    System.out.println(user.getUser_id());
                    System.out.println(user.getRegion_id());
                    System.out.println(user.getIcon_id());
                    System.out.println(user.getDegree_id());
                    System.out.println(user.getUser_name());
                    System.out.println(user.getPassword());
                    System.out.println(user.getMail());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ユーザー情報の取得に失敗しました。");
        }
    }
}
