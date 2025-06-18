<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.Region" %>
<%@ page import="dto.Icon" %>
<%List<Region> regions = (List<Region>) request.getAttribute("regions");%>
<%List<Icon> icons = (List<Icon>)request.getAttribute("icon");%>
<!DOCTYPE html>
<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/mypage.css">
<script defer src="js/mypage.js"></script>
<meta charset="UTF-8">
<title>ポイポイ|マイページ</title>
</head>

<body>

<!-- 更新の成功/失敗のアラート -->
<c:if test="${success == 'true'}">
  <script>
    alert('更新が完了しました');
  </script>
</c:if>
<c:if test="${success == 'false'}">
  <script>
    alert('更新に失敗しました');
  </script>
</c:if>

<!-- モーダル -->
<div id="modalOverlay" class="modal-overlay">
  <div class="modal">
    <h3>画像を選択してください</h3>
    <div class="image-list">
    <% for (Icon icon : icons) { %>
    <img src="img/<%= icon.getIcon_name()%>" class="image-option" data-id="<%= icon.getIcon_id() %>">
    <% } %>
    <br><br>
    </div>
    <button onclick="closeModal()">キャンセル</button>
  </div>
</div>

<!-- ヘッダーここから -->
<div class="logo">
    <a href="<c:url value='/HomeServlet' />"><img src="img/logo.png"></a>
</div>


<div class="header">
    <button type="button" class="hamburger" name="humberger_menu" aria-label="メニュー" aria-controls="nav-menu" aria-expanded="false">
        <img id="hamburger-icon" src="img/hamburger_open.png">
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

<!-- フレームとフォーム -->
<div class="flame">
    <img class="flame-img" src="img/flame_maypage.png" >



<!-- 本体 -->
<c:set var="e" value="${mypage}" />
<!-- 保持ポイント、称号 -->
<div class="image-column">
	<div class="image-wrapper">
		<div class="image-label">保有pt:${e.point}</div>
		<img src="img/point_flame_white.png" alt="画像1" class="point">
	</div>
	
	<div class="image-wrapper">
		<div class="image-label">ランク:${e.degree_name}</div>
		<img src="img/point_flame_white.png" alt="画像2" class="rank">
	</div>
</div>

<!-- フォーム入力 -->
<div class="form-wrapper">
<form class="Form" id="registForm" method="POST" action="<c:url value='/MypageServlet' />">

<!-- 隠しユーザーID -->
<input type="hidden" name="user_id" value="${e.user_id}">

<!-- アイコンエリア -->
<div class="icon-area">

<img id="previewIcon" class="user-icon" src="img/${e.icon_name}" alt="サンプル">
<button type="button" id="icon-upload" class="icon-upload" onclick="openModal()">画像を選ぶ</button>
<input type="hidden" name="icon_id" id="selectedIconId"value="${e.icon_id}">
</div>

<!-- ユーザー名 -->
<div class="Form-Item">
<p class="Form-Item-Label">ユーザー名</p>
<input type="text" name="name_input" class="Form-Item-Input" value="${e.user_name}"><br>
</div>

<!-- 地域 -->
<div class="Form-Item">
	<p class="Form-Item-Label">居住地域</p>
		<select  id="regionSelect" name="region_input" class="Form-Item-Input">
					<option value="${e.region_id}" selected hidden>${e.region_name}</option>
				<% for (Region region : regions) { %>
					<option value="<%= region.getRegion_id() %>"><%= region.getRegion_name() %></option>
				<% } %>
		</select>
</div>
<!-- メール -->
<div class="Form-Item">
	<p class="Form-Item-Label">メールアドレス</p>
	<input type="email" name="mail_input" class="Form-Item-Input" value="${e.mail}">
</div>

<input type="submit" name="update_btn" class="update_btn" value="更新">
</form>
</div>
</div>
</body>
</html>