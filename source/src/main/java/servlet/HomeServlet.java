package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HomeJoinDAO;
import dto.HomeJoin;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	int userId = 1;
    	String check = "ホーム画面です！";
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new java.util.Date());

    	java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());

    	HomeJoinDAO dao = new HomeJoinDAO();
    	HomeJoin homejoin = null;
		try {
			homejoin = dao.homeGet(userId, today);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    	request.setAttribute("home", homejoin); // あとで使うならこれもセットしておきましょう
    	request.setAttribute("check", check);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        dispatcher.forward(request, response);
    }
}
