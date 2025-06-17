package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MypageJoinDAO;
import dto.Icon;
import dto.Region;

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
		//ユーザー情報取得
		
		
		//地域一覧格納
		MypageJoinDAO uDao = new MypageJoinDAO();
        List<Region> regions = uDao.getRegions();
        request.setAttribute("regions", regions);
        //所持アイコン一覧取得
        List<Icon> icon = uDao.getIcons();
        request.setAttribute("icon", icon);
        //
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	
}

