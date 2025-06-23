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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<c:url value='css/common.css'/>">
  <link rel="stylesheet" href="<c:url value='css/store.css'/>">
  <script defer src="<c:url value='/js/store.js' />"></script>
</head>
<body>

  <!-- ロゴとハンバーガー -->
  <div class="logo">
    <a href="<c:url value='/HomeServlet' />">
      <img src="<c:url value='img/logo.png' />">
    </a>
  </div>

  <div class="header">
    <button class="hamburger" name="humberger_menu" aria-label="メニュー" aria-controls="nav-menu" aria-expanded="false">
      <img id="hamburger-icon" src="<c:url value='img/hamburger_open.png' />">
    </button>
    <nav id="nav-menu" class="nav" aria-hidden="true">
      <ul class="nav__list">
        <li class="nav__item"><a href="<c:url value='/HomeServlet' />" class="nav__link">ホーム</a></li>
        <li class="nav__item"><a href="<c:url value='/MypageServlet' />" class="nav__link">マイページ</a></li>
        <li class="nav__item"><a href="<c:url value='/CalendarServlet' />" class="nav__link">カレンダー</a></li>
        <li class="nav__item"><a href="<c:url value='/StoreServlet' />" class="nav__link">ストア</a></li>
        <li class="nav__item"><a href="<c:url value='/HelpServlet' />" class="nav__link">ヘルプ</a></li>
        <li class="nav__item"><a href="<c:url value='/LogoutServlet' />" class="nav__link">ログアウト</a></li>
      </ul>
    </nav>
  </div>

  <!-- フレーム -->
  <div class="flame">
    
    <!-- ラベル -->
    <div class="rectangle-label">ストア</div>

    <!-- 外枠 -->
    <div class="rectangle-big">
      <!-- 内枠 -->
      <div class="rectangle">
        
        <!-- アイテム一覧 -->
        <div class="store_item">
          <c:forEach var="e" items="${IconList}">
            <c:choose>
              <c:when test="${e.icon_id != 1}">
                <% boolean isAvailable = true; boolean isA = true; %>
                <div class="item-box">
                  <img src="<c:url value='/img/${e.icon_name}'/>" alt="item">
                  <p class="price price-50">${e.price}pt</p>
                  
                  <form class="exchange-form" action="<c:url value='/StoreServlet'/>" method="POST">
                    <input type="hidden" name="icon_id" value="${e.icon_id}">
                    <input type="hidden" name="price" value="${e.price}">
                    
                    <c:forEach var="x" items="${icon}">
                      <c:choose>
                        <c:when test="${e.icon_id == x.icon_id}">
                          <% isAvailable = false; %>
                        </c:when>
                        <c:when test="${e.price > point}">
                          <% isA = false; %>
                        </c:when>
                      </c:choose>
                    </c:forEach>
                    
                    <c:if test="${empty icon}">
                      <c:choose>
                        <c:when test="${e.price > point}">
                          <% isA = false; %>
                        </c:when>
                      </c:choose>
                    </c:if>

                    <%
                      if (!isAvailable) {
                    %>
                      <input type="submit" class="nswap_btn" disabled value="所有済み">
                    <%
                      } else if (!isA) {
                    %>
                      <input type="submit" class="nswap_btn" disabled value="ポイント不足">
                    <%
                      } else {
                    %>
                      <input type="submit" class="swap_btn" value="交換">
                    <%
                      }
                    %>
                  </form>
                </div>
              </c:when>
            </c:choose>
          </c:forEach>
        </div>
        
      </div>
    </div>

    <!-- ポイント表示：右上 -->
    <div class="point-box">
      <img src="<c:url value='/img/point_flame.png'/>" alt="ポイント画像" class="point-image">
      <span class="point-text">保有pt:${point}</span>
    </div>

  </div>

</body>
</html>
