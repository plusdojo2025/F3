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
		UsersDAO uDao = new UsersDAO();
		if (uDao.isLoginOK(new Users(0, 0, 0, 0, "", password, mail))) { // ログイン成功
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("id", uDao.getUserId(new Users(0, 0, 0, 0, "", password, mail)));
			System.out.println(uDao.getUserId(new Users(0, 0, 0, 0, "", password, mail)));
			// メニューサーブレットにリダイレクトする
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/HomeServlet");
			System.out.println("ログイン成功");
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			System.out.println("ログイン失敗");
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
