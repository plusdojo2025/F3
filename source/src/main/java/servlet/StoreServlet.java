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

import dao.IconDAO;
import dao.StoreJoinDAO;
import dto.Icon;
import dto.IconStatus;
import dto.StoreJoin;

@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //ページ遷移・画面表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ログインチェック
    	HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		if (session.getAttribute("id") == null) {
			response.sendRedirect(contextPath + "/LoginServlet");
			return;
		}
		//sessionIdにセッションIDを代入
        Object userIdObj = session.getAttribute("id");
        int sessionId = Integer.parseInt(userIdObj.toString());
        
        
        // DAOを使用してデータベースの情報を取得
    	//int sessionId = 5;
        IconDAO IconDAO = new IconDAO();
        List<Icon> IconList = IconDAO.getAllIcon();
        
        //ポイント取得
        StoreJoinDAO sjDAO = new StoreJoinDAO();
        int point = sjDAO.getpoint(new StoreJoin(sessionId, 0,"",0,0));
        
        //所持アイコン一覧取得
        StoreJoinDAO uDao = new StoreJoinDAO();
        List<IconStatus> icon = uDao.getIcons(sessionId);

        // JSPに渡す
        request.setAttribute("IconList", IconList);
        request.setAttribute("point", point);
        request.setAttribute("icon", icon);

        // ストアページへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/store.jsp");
        dispatcher.forward(request, response);
    }
  //ページ遷移・画面表示終わり
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String contextPath = request.getContextPath();
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
    	HttpSession session = request.getSession();
    	Object obj = session.getAttribute("id");
    	int sessionId = (Integer) obj;
        System.out.println("user_id="+sessionId);
		if (session.getAttribute("id") == null) {
			response.sendRedirect(contextPath + "/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String icon_idStr = request.getParameter("icon_id");
		int icon_id = Integer.parseInt(icon_idStr);// 数値型に変換					/* アイコンID */
		String priceStr = request.getParameter("price");
		int price = Integer.parseInt(priceStr);/* 価格 */

		
		// ポイントの更新＆保持アイコン登録処理を行う
		StoreJoinDAO sjDAO = new StoreJoinDAO();
		sjDAO.update(new StoreJoin(sessionId, icon_id,"",price,0)); 

		response.sendRedirect(contextPath + "/StoreServlet");

	}
}
