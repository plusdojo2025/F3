package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;

@WebServlet("/TestDegree")
public class TestDegree extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Object obj = session.getAttribute("id");
    	int userId = (Integer) obj;
        System.out.println("user_id="+userId);
        //セッション送る
    	session.setAttribute("id", userId); // userId は int 型
    	
    	UsersDAO dao1 = new UsersDAO();
    	boolean inseDeg = dao1.UpDegree(userId);
    	if(inseDeg) {
    		System.out.println("称号アップ");
    	}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/CalendarServlet");
        dispatcher.forward(request, response);
    }
}
