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
		String success = (String) session.getAttribute("success");
		if (success != null) {
		    request.setAttribute("success", success);
		    session.removeAttribute("success");
		}
		//地域一覧格納
		MypageJoinDAO uDao = new MypageJoinDAO();
        List<Region> regions = uDao.getRegions();
        request.setAttribute("regions", regions);
        
        //マイページ情報取得
        //int uid = (int)session.getAttribute("id");
        MypageJoin mypage = uDao.mypageSelect(1);//仮で入力中
        request.setAttribute("mypage",mypage);
        
      //所持アイコン一覧取得
        List<Icon> icon = uDao.getIcons(mypage.getUser_id());
        
        request.setAttribute("icon", icon);
        
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/F3/LoginServlet");
//			return;
//		}
		
		//リクエストスコープ取得
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int region_id = Integer.parseInt(request.getParameter("region_input"));
		int icon_id = Integer.parseInt(request.getParameter("icon_id"));
		String name = request.getParameter("name_input");
		String mail = request.getParameter("mail_input");
		
		
		Users user = new Users();
		user.setUser_id(user_id);
		user.setIcon_id(icon_id);
		user.setMail(mail);
		user.setRegion_id(region_id);
		user.setUser_name(name);
		MypageJoinDAO users = new MypageJoinDAO();
		String contextPath = request.getContextPath();
		if (users.mypageUpdate(user)) {
		    System.out.println("更新成功");
		    session.setAttribute("success", "true");
		    response.sendRedirect(contextPath + "/MypageServlet");
		} else {
		    System.out.println("更新失敗");
		    session.setAttribute("success", "false");
		    response.sendRedirect(contextPath + "/MypageServlet");
		}
		
	}
	
	
}

