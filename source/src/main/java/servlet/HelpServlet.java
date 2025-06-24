package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HelpJoinDAO;
import dto.HelpJoin;

/**
 * Servlet implementation class HelpServlet
 */
@WebServlet("/HelpServlet")
public class HelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//セッションを取得
    	HttpSession session = request.getSession();
    	Object obj = session.getAttribute("id");
    	int userId = (Integer) obj;
        System.out.println("user_id="+userId);
        
        if(session.getAttribute("id")==null) {
        	String contextPath = request.getContextPath();
    		response.sendRedirect(contextPath + "/TopServlet");
        	return;
        }
		
		HelpJoinDAO hDAO = new HelpJoinDAO();
		HelpJoin region = new HelpJoin();
		region = hDAO.getGarbageLink(userId);
		System.out.println("servlet check");
		String help_link = region.getLink();
		request.setAttribute("region", help_link);
		System.out.println(region);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/help.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
