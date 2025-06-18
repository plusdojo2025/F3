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
import dao.StoreJoinDAO;
import dto.Icon;
import dto.StoreJoin;

@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //ページ遷移・画面表示
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
        
        StoreJoinDAO sjDAO = new StoreJoinDAO();
        int point = sjDAO.getpoint(new StoreJoin(0, 0,"",0,0));
 

        // JSPに渡す
        request.setAttribute("IconList", IconList);
        request.setAttribute("point", point);
        

        // ストアページへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/store.jsp");
        dispatcher.forward(request, response);
    }
  //ページ遷移・画面表示終わり
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/F3/LoginServlet");
//			return;
//		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String icon_idStr = request.getParameter("icon_id");
		int icon_id = Integer.parseInt(icon_idStr);// 数値型に変換					/* アイコンID */
		String priceStr = request.getParameter("price");
		int price = Integer.parseInt(priceStr);/* 価格 */

		
		// ポイントの更新＆保持アイコン登録処理を行う
		StoreJoinDAO sjDAO = new StoreJoinDAO();
		boolean result = sjDAO.update(new StoreJoin(0, icon_id,"",price,0)); 
			

		// 検索結果をリクエストスコープに格納する
		//request.setAttribute("cardList", cardList);
		
		


//		// 結果ページにフォワードする
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
//		dispatcher.forward(request, response);
	}
}
