<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width-device-width, initial-scale=1">
<title>ポイポイ</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<script src="<c:url value='/js/common.js' />"></script>
</head>
<body>
	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="<c:url value='/HomeServlet' />"><img
			src="<c:url value='/img/logo.png'/>"></a>
	</div>
	<div class="header">
		<button class="hamburger" name="humberger_menu" aria-label="メニュー"
			aria-controls="nav-menu" aria-expanded="false">
			<img id="hamburger-icon" src="<c:url value='/img/hamburger_open.png'/>">
		</button>

		<nav id="nav-menu" class="nav" aria-hidden="true">
			<ul class="nav__list">
				<li class="nav__item"><a
					href="<c:url value='/HomeServlet' />" class="nav__link"
					name="home_link">ホーム</a></li>
				<li class="nav__item"><a
					href="<c:url value='/MypageServlet' />" class="nav__link"
					name="mypege_link">マイページ</a></li>
				<li class="nav__item"><a
					href="<c:url value='/CalendarServlet'/>"
					class="nav__link" name="calender_link">カレンダー</a></li>
				<li class="nav__item"><a
					href="<c:url value='/StoreServlet'/>" class="nav__link"
					name="store_link">ストア</a></li>
				<li class="nav__item"><a
					href="<c:url value='/HelpServlet'/>" class="nav__link"
					name="help_link">へルプ</a></li>
				<li class="nav__item"><a
					href="<c:url value='/LogoutServlet'/>" class="nav__link"
					name="logout_btn">ログアウト</a></li>
			</ul>
		</nav>
	</div>
	
	
</body>
</html>