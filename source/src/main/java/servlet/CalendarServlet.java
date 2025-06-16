package servlet;

import java.io.IOException;
import java.sql.Date;
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

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CalendarJoinDAO dao = new CalendarJoinDAO();
            int userId = 1;

            // Map<Date, List<String>> を取得（types + "チェック済み" 含む）
            Map<Date, List<String>> calendarData = dao.getGarbageTypesByUserId(userId);

            // TreeMapで日付順にソート（JSPで出力順を保証するため）
            Map<Date, List<String>> sortedMap = new TreeMap<>(calendarData);

            String check = "チェック用";
            request.setAttribute("check", check);
            
            // JSP に渡す
            request.setAttribute("calendarMap", sortedMap);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "カレンダー情報の取得に失敗しました。");
        }

        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
        dispatcher.forward(request, response);
    }
}
