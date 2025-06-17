package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MypageJoinDAO;
import dto.Icon;
import dto.LoginUsers;
import dto.MypageJoin;
import dto.Region;
import dto.Users;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッション取得/ログインへ戻す
		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/F3/LoginServlet");
//			return;
//		}
		
		//地域一覧格納
		MypageJoinDAO uDao = new MypageJoinDAO();
        List<Region> regions = uDao.getRegions();
        request.setAttribute("regions", regions);
        
        //所持アイコン一覧取得
        List<Icon> icon = uDao.getIcons();
        request.setAttribute("icon", icon);
        
        //マイページ情報取得
        LoginUsers user = (LoginUsers)session.getAttribute("id");
        MypageJoin mypage = uDao.mypageSelect("taro@example.com");
        request.setAttribute("mypage",mypage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/F3/LoginServlet");
//			return;
//		}
		
		request.setCharacterEncoding("UTF-8");
		//リクエストスコープ取得
		int region_id = Integer.parseInt(request.getParameter("region_id"));
		int icon_id = Integer.parseInt(request.getParameter("icon_id"));
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		
		Users user = new Users();
		user.setIcon_id(icon_id);
		user.setMail(mail);
		user.setRegion_id(region_id);
		user.setUser_name(name);
		MypageJoinDAO users = new MypageJoinDAO();
		if(users.mypageUpdate(user)) {
			//response.sendRedirect("/servlet/MypageServlet");
			System.out.println("更新成功");
		}else {
			System.out.println("更新失敗");
		}
		
	}
	
	
}

