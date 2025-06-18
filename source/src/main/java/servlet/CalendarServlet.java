package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.servlet.http.HttpSession;

import dao.CalendarJoinDAO;
import dto.CalendarJoin;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	
    	//セッションを取得
    	HttpSession session = request.getSession();
    	Object obj = session.getAttribute("id");
    	int userId = (Integer) obj;
        System.out.println("user_id="+userId);
    	
    	String code = request.getParameter("code");

    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new java.util.Date()); // 今の日付を基に
    	cal.set(Calendar.DAY_OF_MONTH, 1); // 月初に固定

    	if ("1".equals(code)) {
    	    cal.add(Calendar.MONTH, -1); // 前月
    	} else if ("2".equals(code)) {
    	    cal.add(Calendar.MONTH, 1); // 次月
    	} // code が null またはその他 → 今月のまま

    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH) + 1; // ← これらが必要！
    	int calendarStartDay = cal.get(Calendar.DAY_OF_WEEK);
    	int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    	// カレンダー表示に必要なデータをセット
    	request.setAttribute("displayYear", year);
    	request.setAttribute("displayMonth", month);
    	request.setAttribute("calendarStartDay", calendarStartDay);
    	request.setAttribute("daysInMonth", daysInMonth);
    	request.setAttribute("code", code);
    	//セッション送る
    	session.setAttribute("id", userId); // userId は int 型
    	
        try {

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
