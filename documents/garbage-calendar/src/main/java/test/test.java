package test;

import java.sql.SQLException;
import java.util.List;

import dao.GarbageRuleDAO;
import model.GarbageRule;

public class test {
    public static void main(String[] args) {
        GarbageRuleDAO dao = new GarbageRuleDAO();

        try {
            List<GarbageRule> rules = dao.getRules();

            if (rules == null || rules.isEmpty()) {
                System.out.println("データが見つかりません。");
            } else {
                System.out.println("ごみ収集ルール一覧：");
                for (GarbageRule rule : rules) {
                    System.out.printf("種別: %s, ルール: %s, 間隔: %dヶ月, 開始月: %d, 地域: %s%n",
                        rule.getType(),
                        rule.getRule(),
                        rule.getIntervalMonths(),
                        rule.getStartMonth(),
                        rule.getRegion()
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("データベースアクセス時にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
