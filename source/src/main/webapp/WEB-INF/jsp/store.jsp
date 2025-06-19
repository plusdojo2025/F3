<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.IconStatus" %>
<%@ page import="dto.Icon" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ストア</title>
    <link rel="stylesheet" href="css\common.css">
    <link rel="stylesheet" href="css\store.css">
</head>
<body>
    <!-- ヘッダーここから -->
    <div class="logo">
        <a href="img\home.html">
            <img src="img\logo.png">
        </a>
        
    </div>

    <div class="header">
        <button class="hamburger" name="humberger_menu" aria-label="メニュー" aria-controls="nav-menu" aria-expanded="false">
            <img id="hamburger-icon" src="img\hamburger_open.png">
        </button>

        <nav id="nav-menu" class="nav" aria-hidden="true">
            <ul class="nav__list">
                <li class="nav__item"><a href="<c:url value='/HomeServlet' />" class="nav__link" name="home_link">ホーム</a></li>
	        <li class="nav__item"><a href="<c:url value='/MypageServlet' />" class="nav__link" name="mypege_link">マイページ</a></li>
	        <li class="nav__item"><a href="<c:url value='/CalenderServlet' />" class="nav__link" name="calender_link">カレンダー</a></li>
	        <li class="nav__item"><a href="<c:url value='/StoreServlet' />" class="nav__link" name="store_link">ストア</a></li>
	        <li class="nav__item"><a href="<c:url value='/HeplServlet' />" class="nav__link" name="help_link">ヘルプ</a></li>
	        <li class="nav__item"><a href="<c:url value='/LogoutServlet' />" class="nav__link" name="logout_btn">ログアウト</a></li>
            </ul>
        </nav>
    </div>
    

<div class="flame-wrapper">
        <div class="flame">
          <div class="point-box">
           <img src="img\point_flame.png" alt="重ね画像" class="point-image" name="point">
            <span class="point-text">保有pt:${point}</span>
          </div>
            <div class="store_item">
              <!-- 遷移先を記入 -->
              <c:forEach var="e" items="${IconList}" >
				<% 
				boolean isAvailable = true;
				boolean isA = true;
				%>
                <div class="item-box">
                    <img src="img/${e.icon_name}" alt="item1">
                    <p class="price price-50" name="price">${e.price}pt</p>
                    <form class="exchange-form" action="/F3/StoreServlet" method="POST">
                        <input type="hidden" name="icon_id" value="${e.icon_id}">
                        <input type="hidden" name="price" value="${e.price}">
                        <c:forEach var="x" items="${icon}" >
			 		    <!-- ボタン状態を条件で切り替え -->
				            <c:choose>
				                <c:when test="${e.icon_id == x.icon_id}">
		 							<% 
						    		isAvailable = false; //所持済みの場合
									%>
				                </c:when>
				                <c:when test="${e.price > point}">
        							<% 
						    		isA = false; //ポイント不足の場合
									%>
    							</c:when>
				            </c:choose>
        				</c:forEach>
        				<!-- iconが空のときの処理 -->
        				<c:if test="${empty icon}">
							<c:choose>
				                <c:when test="${e.price > point}">
        							<% 
						    		isA = false; //ポイント不足の場合
									%>
    							</c:when>
				            </c:choose>
						</c:if>
        					<%
        						if (isAvailable==false) {
        					%>
        					    <input type="submit" class="nswap_btn"  disabled value="所有済み">
        					<%
        					    } else if(isA == false) {
        					%>
        					    <input type="submit" class="nswap_btn" disabled value="ポイント不足">
        					<%
        					    }else{
        					%>
        						<input type="submit" class="swap_btn" value="交換">
        					<%
        					    }
        					%>

                    </form>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>

   <script>

'use strict';



document.addEventListener('DOMContentLoaded', () => {
  // ハンバーガーメニュー
  const hamburger = document.querySelector('.hamburger');
  const nav = document.querySelector('.nav');
  const hamburgerIcon = document.getElementById('hamburger-icon');

  const hamburgerImgPath = 'img/hamburger_open.png';
  const closeImgPath = 'img/hamburger_close.png';

  hamburger.addEventListener('click', () => {
    hamburger.classList.toggle('active');
    nav.classList.toggle('active');

    const isOpen = hamburger.classList.contains('active');
    hamburger.setAttribute('aria-expanded', isOpen);
    nav.setAttribute('aria-hidden', !isOpen);

    hamburgerIcon.src = isOpen ? closeImgPath : hamburgerImgPath;
  });

  document.addEventListener('click', (e) => {
    if (!e.target.closest('.nav') && !e.target.closest('.hamburger') && nav.classList.contains('active')) {
      hamburger.classList.remove('active');
      nav.classList.remove('active');
      hamburger.setAttribute('aria-expanded', false);
      nav.setAttribute('aria-hidden', true);
      hamburgerIcon.src = hamburgerImgPath;
    }
  });

  // フォーム送信時の確認
  const forms = document.querySelectorAll('.exchange-form');
  forms.forEach(form => {
    form.addEventListener('submit', (e) => {
      const confirmed = confirm("ポイントとアイコンを交換します、よろしいですか？");
      if (!confirmed) {
        e.preventDefault(); // キャンセルされた場合、送信中止
      }else {
          alert("交換が完了しました！");
      }
    });
  });
});
</script>
</body>
</html>