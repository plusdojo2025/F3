package servlet;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDegree")
public class TestDegree extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        System.out.println(hour);
    	
        request.setAttribute("hour", hour);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CalendarServlet");
        dispatcher.forward(request, response);
    }
}
