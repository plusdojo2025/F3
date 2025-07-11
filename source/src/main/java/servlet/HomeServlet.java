package servlet;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HomeJoinDAO;
import dao.UsersDAO;
import dto.HomeJoin;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	//セッションを取得
    	HttpSession session = request.getSession();
    	if(session == null || session.getAttribute("id")==null) {
        	String contextPath = request.getContextPath();
    		response.sendRedirect(contextPath + "/TopServlet");
        	return;
        }
    	Object obj = session.getAttribute("id");
    	int userId = (Integer) obj;
        System.out.println("user_id="+userId);
        
        
        
        //セッション送る
    	session.setAttribute("id", userId); // userId は int 型
        
    	//時間を送るため
    	LocalTime now = LocalTime.now();
        int hour = now.getHour();
        System.out.println(hour);
    	
        request.setAttribute("hour", hour);
    	
    	
    	String check = "ホーム画面です！";
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new java.util.Date());

    	java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());

    	HomeJoinDAO dao = new HomeJoinDAO();
    	HomeJoin homejoin = null;
		try {
			homejoin = dao.homeGet(userId, today);
			System.out.println("homejoinのtypes="+homejoin.getTypes());
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
    	String contextPath = request.getContextPath();
    	//セッションを取得
    	HttpSession session = request.getSession();
    	Object obj = session.getAttribute("id");
    	int userId = (Integer) obj;
        System.out.println("user_id="+userId);
        //セッション送る
    	session.setAttribute("id", userId); // userId は int 型
        
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
    	
    	//日付取得
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new java.util.Date());
    	java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
    	try {   		
    	//dao処理
    	HomeJoinDAO dao = new HomeJoinDAO();
    	boolean check = dao.insertCal(userId, today, score, point);    	

    	if(score<109&&(score%10)==9) {
        	UsersDAO dao1 = new UsersDAO();
        	boolean inseDeg = dao1.UpDegree(userId);
        	if(inseDeg) {
        		System.out.println("称号アップ");
        	}
    	}
    	
    	if(check) {
    	    System.out.println("カレンダー登録成功！");
    	    response.sendRedirect(contextPath +"/HomeServlet");  // ←これで doGet() に自然遷移
    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    } 
}
