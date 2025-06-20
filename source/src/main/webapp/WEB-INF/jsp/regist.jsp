<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.Region"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Region> regions = (List<Region>) request.getAttribute("regions");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ポイポイ</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/regist.css' />">
<script src="<c:url value='/js/regist.js' />"></script>
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
			<img id="hamburger-icon"
				src="<c:url value='/img/hamburger_open.png' />">
		</button>

		<nav id="nav-menu" class="nav" aria-hidden="true">
			<ul class="nav__list">
				<li class="nav__item"><a href="<c:url value='/HomeServlet' />"
					class="nav__link" name="home_link">ホーム</a></li>
				<li class="nav__item"><a
					href="<c:url value='/MypageServlet' />" class="nav__link"
					name="mypege_link">マイページ</a></li>
				<li class="nav__item"><a
					href="<c:url value='/CalendarServlet'/>" class="nav__link"
					name="calender_link">カレンダー</a></li>
				<li class="nav__item"><a href="<c:url value='/StoreServlet'/>"
					class="nav__link" name="store_link">ストア</a></li>
				<li class="nav__item"><a href="<c:url value='/HelpServlet'/>"
					class="nav__link" name="help_link">へルプ</a></li>
				<li class="nav__item"><a href="<c:url value='/LogoutServlet'/>"
					class="nav__link" name="logout_btn">ログアウト</a></li>
			</ul>
		</nav>
	</div>

	<div class="flame">
		<img src="<c:url value='/img/flame_regist.png'/>" class="flame-img" />

		<div class="form-wrapper">
			<form class="Form" id="registForm" name="registForm" method="POST"
				action="<c:url value='/RegistServlet' />"
				onsubmit="return validateForm()">
				<div class="Form-Item">
					<p class="Form-Item-Label">ユーザー名</p>
					<input type="text" name="name_input" class="Form-Item-Input"
						id="name_input" onKeyup="valiCheck()">
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label">パスワード</p>
					<input type="password" name="pw_input" class="Form-Item-Input"
						id="pw_input" onKeyup="valiCheck()">
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label" onKeyup="valiCheck()">
						パスワード<br>（2回目）
					</p>
					<input type="password" name="pw_re_input" class="Form-Item-Input"
						id="pw_re_input" onKeyup="valiCheck()">
				</div>
				<!-- ここはプルダウンの選択肢に -->
				<div class="Form-Item">
					<p class="Form-Item-Label">居住地域</p>
					<select id="region_input" name="region_input"
						class="Form-Item-Input">
						<option value=0 selected>選択してください</option>
						<c:forEach var="region" items="${regions}">
							<option value="${region.region_id}">${region.region_name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label">メールアドレス</p>
					<input type="email" name="mail_input" class="Form-Item-Input"
						onKeyup="valiCheck()">
				</div>
				<input type="submit" name="insert_btn" class="insert_btn" value="登録">
				<p id="error_msg" class="font-red"></p>
			</form>
		</div>
	</div>
</body>
</html>