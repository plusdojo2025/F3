package servlet;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GarbageRuleDAO;
import model.GarbageRule;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // デフォルトの年と月（現在）
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        // パラメータに年・月が指定されていれば使用
        if (request.getParameter("year") != null) {
            year = Integer.parseInt(request.getParameter("year"));
        }
        if (request.getParameter("month") != null) {
            month = Integer.parseInt(request.getParameter("month"));
        }

        // 月初日とその曜日（0:日曜〜6:土曜）および月末日
        LocalDate firstDate = LocalDate.of(year, month, 1);
        int firstDayOfWeek = firstDate.getDayOfWeek().getValue() % 7; // Javaは月曜=1なので、日曜=0 に合わせる
        int daysInMonth = firstDate.lengthOfMonth();

        // ごみ収集ルールをデータベースから取得
        GarbageRuleDAO dao = new GarbageRuleDAO();
        List<GarbageRule> rules;
        try {
            rules = dao.getRules();
        } catch (Exception e) {
            throw new ServletException("ルールの取得に失敗しました", e);
        }

        // 日付ごとのごみ収集内容を格納する Map
        Map<LocalDate, List<String>> scheduleMap = new HashMap<>();

        // 月の日数分ループして該当ルールに一致すればMapに追加
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            for (GarbageRule rule : rules) {
                if (isCollectDay(date, rule)) {
                    scheduleMap.computeIfAbsent(date, d -> new ArrayList<>())
                               .add(rule.getType());
                }
            }
        }

        // JSP に渡すデータをセット
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("firstDayOfWeek", firstDayOfWeek);
        request.setAttribute("daysInMonth", daysInMonth);
        request.setAttribute("scheduleMap", scheduleMap);

        // JSP へフォワード
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    /**
     * 指定日がごみ収集日かどうかを判定
     */
    private boolean isCollectDay(LocalDate date, GarbageRule rule) {
        String r = rule.getRule();

        // 【1】「毎週〇曜日」のルール
        if (r.startsWith("毎週")) {
            String jaDay = r.substring(2, 3);  // 曜日文字を抽出
            return date.getDayOfWeek() == convertJapaneseDayOfWeek(jaDay);
        }

        // 【2】「第n〇曜日」のルール（例：第2水曜）
        if (r.startsWith("第")) {
            int weekNum = Character.getNumericValue(r.charAt(1));  // 何週目か
            String jaDay = r.substring(2, 3);                      // 曜日
            if (date.getDayOfWeek() == convertJapaneseDayOfWeek(jaDay)) {
                int weekOfMonth = (date.getDayOfMonth() - 1) / 7 + 1;
                return weekOfMonth == weekNum;
            }
        }

        // 【3】「隔週〇曜日」のルール（基準月の1日から週数カウント）
        if (r.startsWith("隔週")) {
            String jaDay = r.substring(2, 3);
            if (date.getDayOfWeek() != convertJapaneseDayOfWeek(jaDay)) return false;

            LocalDate baseDate = LocalDate.of(date.getYear(), rule.getStartMonth(), 1);
            long weeksBetween = ChronoUnit.WEEKS.between(baseDate, date);
            return weeksBetween % 2 == 0; // 偶数週のみ収集
        }

        // 【4】ローテーションルール（例: 火曜:プラ→資源→なし）
        if (r.matches("^[日月火水木金土]曜:.*")) {
            String[] parts = r.split(":");
            String jaDay = parts[0].substring(0, 1);
            DayOfWeek targetDay = convertJapaneseDayOfWeek(jaDay);

            if (date.getDayOfWeek() != targetDay) return false;

            String[] pattern = parts[1].split("→"); // ローテーションパターン
            LocalDate baseDate = LocalDate.of(date.getYear(), rule.getStartMonth(), 1);
            long weeks = ChronoUnit.WEEKS.between(baseDate, date);
            int index = (int)(weeks % pattern.length);
            String currentType = pattern[index];

            // 「なし」の週は表示しない
            if ("なし".equals(currentType)) return false;

            // 現在の収集種別がこのruleのtypeと一致する場合だけ表示
            return rule.getType().equals(currentType);
        }

        // 【5】nか月ごとの収集（毎月1日で間隔指定）
        if (rule.getIntervalMonths() > 0) {
            int current = date.getYear() * 12 + date.getMonthValue();
            int start = date.getYear() * 12 + rule.getStartMonth();
            return date.getDayOfMonth() == 1 && (current - start) % rule.getIntervalMonths() == 0;
        }

        // 上記に該当しない場合は収集日ではない
        return false;
    }

    /**
     * 日本語の曜日文字（例: "月"）を DayOfWeek に変換
     */
    private DayOfWeek convertJapaneseDayOfWeek(String ja) {
        switch (ja) {
            case "日": return DayOfWeek.SUNDAY;
            case "月": return DayOfWeek.MONDAY;
            case "火": return DayOfWeek.TUESDAY;
            case "水": return DayOfWeek.WEDNESDAY;
            case "木": return DayOfWeek.THURSDAY;
            case "金": return DayOfWeek.FRIDAY;
            case "土": return DayOfWeek.SATURDAY;
            default: throw new IllegalArgumentException("曜日が不正です: " + ja);
        }
    }
}
