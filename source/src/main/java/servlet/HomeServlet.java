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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String check_id = request.getParameter("check_id");
    	String score_str = request.getParameter("score");
    	String point_str = request.getParameter("point");
    	int score = Integer.parseInt(score_str);
    	int point = Integer.parseInt(point_str);
    	
    	
    	System.out.println(check_id);
    	System.out.println(score);
    	System.out.println(point);
    	
    	if(check_id==null) {
    		System.out.println("空です。");
    	}
    	//ユーザーID仮で1を入れています。
    	int user_id = 1;
    	
    	//日付取得
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new java.util.Date());
    	java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
    	
    	//dao処理
    	HomeJoinDAO dao = new HomeJoinDAO();
    	boolean check = dao.insertCal(user_id, today, score, point);
    	if(check) {
    	    System.out.println("カレンダー登録成功！");
    	    response.sendRedirect("HomeServlet");  // ←これで doGet() に自然遷移
    	}
    } 
}
