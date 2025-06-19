package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import dto.Region;
import dto.Users;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UsersDAO uDao = new UsersDAO();
        List<Region> regions = uDao.getRegions();

        request.setAttribute("regions", regions);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
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
		int region_id = Integer.parseInt(request.getParameter("region_input"));
		String user_name = request.getParameter("name_input");
		String password = request.getParameter("pw_input");
		String rePassword = request.getParameter("pw_re_input");
		String mail = request.getParameter("mail_input");
		System.out.println(region_id + user_name + password + rePassword + mail);
		// ログイン処理を行う
		UsersDAO uDao = new UsersDAO();
		if (uDao.createAccount(new Users(0, region_id, 1, 1, user_name, password, mail))) { 
			// ログイン成功
			System.out.println("登録成功");
			//request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/F3/LoginServlet"));
		} else { // 登録失敗
			System.out.println("登録失敗");
			//request.setAttribute("result", new Result("登録失敗！", "レコードを登録できませんでした。", "/F3/LoginServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

}
