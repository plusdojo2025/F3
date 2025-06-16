package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IconDAO;
import dto.Icon;

@WebServlet("/StoreServletServlet")
public class StoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ログインチェック
//        HttpSession session = request.getSession();
//        if (session.getAttribute("id") == null) {
//            response.sendRedirect("/F3/LoginServlet");
//            return;
//        }

        // DAOを使用してデータベースの情報を取得
        IconDAO IconDAO = new IconDAO();
        List<Icon> IconList = IconDAO.getAllIcon();

        // JSPに渡す
        request.setAttribute("IconList", IconList);

        // ストアページへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/store.jsp");
        dispatcher.forward(request, response);
    }
}
