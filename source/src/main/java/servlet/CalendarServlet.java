package servlet;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.CalendarJoinDAO;

public class CalendarServlet {
    public static void main(String[] args) {
        try {
            CalendarJoinDAO dao = new CalendarJoinDAO();

            // 任意の user_id（例: 1）
            int userId = 1;

            // DAOから Map<Date, List<String>> を取得（チェック済み対応）
            Map<Date, List<String>> calendarData = dao.getGarbageTypesByUserId(userId);

            // TreeMapでソートして出力
            Map<Date, List<String>> sortedMap = new TreeMap<>(calendarData);

            for (Map.Entry<Date, List<String>> entry : sortedMap.entrySet()) {
                Date date = entry.getKey();
                List<String> types = entry.getValue();

                System.out.println("📅 日付: " + date);

                for (int i = 0; i < types.size(); i++) {
                    String label = (i == 0 && "チェック済み".equals(types.get(0))) ? "✅ " : "  - ";
                    System.out.println(label + types.get(i));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("カレンダー情報の取得に失敗しました。");
        }
    }
}
