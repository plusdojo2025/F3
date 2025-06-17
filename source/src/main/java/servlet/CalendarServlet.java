package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CalendarJoinDAO;
import dto.CalendarJoin;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            int userId = 1;
            CalendarJoinDAO dao = new CalendarJoinDAO();
            Map<Date, List<CalendarJoin>> calendarData = dao.getCalendarJoinMapByUserId(userId);
            Map<Date, List<CalendarJoin>> sortedMap = new TreeMap<>(calendarData);

            Map<String, Map<String, Object>> viewMap = new LinkedHashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (Map.Entry<Date, List<CalendarJoin>> entry : sortedMap.entrySet()) {
                Date date = entry.getKey();
                String dateStr = sdf.format(date);
                List<CalendarJoin> beans = entry.getValue();

                boolean isChecked = !beans.isEmpty() && "チェック済み".equals(beans.get(0).getTypes());
                List<String> types = new ArrayList<>();
                for (int i = isChecked ? 1 : 0; i < beans.size(); i++) {
                    types.add(beans.get(i).getTypes());
                }

                Map<String, Object> cell = new HashMap<>();
                cell.put("isChecked", isChecked);
                cell.put("types", types);
                viewMap.put(dateStr, cell);
            }

            request.setAttribute("calendarViewMap", viewMap);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "カレンダー情報の取得に失敗しました。");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
        dispatcher.forward(request, response);
    }
}
