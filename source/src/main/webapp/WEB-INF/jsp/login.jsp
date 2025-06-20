<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ポイポイ｜ログイン</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/login.css' />">
<script defer src="<c:url value='/js/login.js' />"></script>
<script src="<c:url value='/js/common.js' />"></script>


</head>
<body>
	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="<c:url value='/HomeServlet' />"><img
			src="img/logo.png"></a>
	</div>
	<div class="header">
		<button class="hamburger" name="humberger_menu" aria-label="メニュー"
			aria-controls="nav-menu" aria-expanded="false">
			<img src="<c:url value='/img/logo.png' />">
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
	
	<div class="flame">
		<img src="<c:url value='/img/flame.png'/>" class="flame-img" />

		<div class="form-wrapper">
			<form class="Form" name="loginForm" method="POST"
				action="action="<c:url value='/LoginServlet' /> onsubmit="return validateForm()">
				<div class="Form-Item">
					<p class="Form-Item-Label">メールアドレス</p>
					<input type="text" name="email_input" id="email"
						class="Form-Item-Input">
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label">パスワード</p>
					<input type="password" name="pw_input" id="password"
						class="Form-Item-Input">
				</div>
				<input type="submit" name="login" class="login" value="ログイン">
			</form>
		</div>
	</div>
</body>
</html>