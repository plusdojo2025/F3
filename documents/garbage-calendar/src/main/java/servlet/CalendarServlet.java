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

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        // パラメータから年・月指定があれば使用
        if (request.getParameter("year") != null) {
            year = Integer.parseInt(request.getParameter("year"));
        }
        if (request.getParameter("month") != null) {
            month = Integer.parseInt(request.getParameter("month"));
        }

        // 月初の曜日と月の日数
        LocalDate firstDate = LocalDate.of(year, month, 1);
        int firstDayOfWeek = firstDate.getDayOfWeek().getValue() % 7; // 日曜=0
        int daysInMonth = firstDate.lengthOfMonth();

        // ごみ収集ルール取得
        GarbageRuleDAO dao = new GarbageRuleDAO();
        List<GarbageRule> rules;
        try {
            rules = dao.getRules();
        } catch (Exception e) {
            throw new ServletException("ルールの取得に失敗しました", e);
        }

        // カレンダー上に収集情報を載せる Map<LocalDate, List<String>>
        Map<LocalDate, List<String>> scheduleMap = new HashMap<>();

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            for (GarbageRule rule : rules) {
                if (isCollectDay(date, rule)) {
                    scheduleMap.computeIfAbsent(date, d -> new ArrayList<>())
                               .add(rule.getType());
                }
            }
        }

        // JSP に渡す
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("firstDayOfWeek", firstDayOfWeek);
        request.setAttribute("daysInMonth", daysInMonth);
        request.setAttribute("scheduleMap", scheduleMap);

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    private boolean isCollectDay(LocalDate date, GarbageRule rule) {
        String r = rule.getRule();

        // 毎週〇曜日
        if (r.startsWith("毎週")) {
            String jaDay = r.substring(2, 3);
            return date.getDayOfWeek() == convertJapaneseDayOfWeek(jaDay);
        }

        // 第n〇曜日
        if (r.startsWith("第")) {
            int weekNum = Character.getNumericValue(r.charAt(1));
            String jaDay = r.substring(2, 3);
            if (date.getDayOfWeek() == convertJapaneseDayOfWeek(jaDay)) {
                int weekOfMonth = (date.getDayOfMonth() - 1) / 7 + 1;
                return weekOfMonth == weekNum;
            }
        }

        // 隔週〇曜日（開始月を基準に隔週カウント）
        if (r.startsWith("隔週")) {
            String jaDay = r.substring(2, 3);
            if (date.getDayOfWeek() != convertJapaneseDayOfWeek(jaDay)) return false;

            // 開始日からの週数が偶数の週に収集
            LocalDate baseDate = LocalDate.of(date.getYear(), rule.getStartMonth(), 1);
            long weeksBetween = ChronoUnit.WEEKS.between(baseDate, date);
            return weeksBetween % 2 == 0;
        }
        
     // ：曜日ローテーション（例: 火曜:プラ→資源→なし）
        if (r.matches("^[日月火水木金土]曜:.*")) {
            String[] parts = r.split(":");
            String jaDay = parts[0].substring(0, 1);
            DayOfWeek targetDay = convertJapaneseDayOfWeek(jaDay);

            if (date.getDayOfWeek() != targetDay) return false;

            String[] pattern = parts[1].split("→");
            LocalDate baseDate = LocalDate.of(date.getYear(), rule.getStartMonth(), 1);
            long weeks = ChronoUnit.WEEKS.between(baseDate, date);
            int index = (int)(weeks % pattern.length);
            String currentType = pattern[index];

            // 「なし」週は何も表示しないようにする
            if ("なし".equals(currentType)) return false;
            
            return rule.getType().equals(currentType);
        }


        // intervalMonths による収集（月単位の繰り返し）
        if (rule.getIntervalMonths() > 0) {
            int current = date.getYear() * 12 + date.getMonthValue();
            int start = date.getYear() * 12 + rule.getStartMonth();
            return date.getDayOfMonth() == 1 && (current - start) % rule.getIntervalMonths() == 0;
        }

        return false;
    }


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
