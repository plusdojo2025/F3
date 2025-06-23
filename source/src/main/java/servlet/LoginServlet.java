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
import dto.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
//		//セッション削除
//		session.invalidate();
		String success = (String) session.getAttribute("success");
		if (success != null) {
		    request.setAttribute("success", success);
		    session.removeAttribute("success");
		}
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("email_input");
		String password = request.getParameter("pw_input");

		// ログイン処理を行う
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		UsersDAO uDao = new UsersDAO();
		if (uDao.isLoginOK(new Users(0, 0, 0, 0, "", password, mail))) { // ログイン成功
			// セッションスコープにIDを格納する
			
			session.setAttribute("id", uDao.getUserId(new Users(0, 0, 0, 0, "", password, mail)));
			System.out.println(uDao.getUserId(new Users(0, 0, 0, 0, "", password, mail)));
			// メニューサーブレットにリダイレクトする
			System.out.println("ログイン成功");
			session.setAttribute("success", "true");
			response.sendRedirect(contextPath+"/LoginServlet");//結果にいきたいな
			
		} else { // ログイン失敗
			System.out.println("ログイン失敗");
			session.setAttribute("success", "false");
			response.sendRedirect(contextPath+"/LoginServlet");
		}
	}
}
