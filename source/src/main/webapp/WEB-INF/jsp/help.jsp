<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ポイポイ</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/help.css' />">
<script src="<c:url value='/js/help.js' />"></script>
<script src="<c:url value='/js/common.js' />"></script>
</head>
<body>
	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="<c:url value='/HomeServlet' />">
			<img src="<c:url value='/img/logo.png'/>">
		</a>
	</div>

	<div class="header">
		<button class="hamburger" name="humberger_menu" aria-label="メニュー"
			aria-controls="nav-menu" aria-expanded="false">
			<img id="hamburger-icon" src="<c:url value='/img/hamburger_open.png'/>">
		</button>

		<nav id="nav-menu" class="nav" aria-hidden="true">
			<ul class="nav__list">
				<li class="nav__item"><a href="<c:url value='/HomeServlet' />" class="nav__link">ホーム</a></li>
				<li class="nav__item"><a href="<c:url value='/MypageServlet' />" class="nav__link">マイページ</a></li>
				<li class="nav__item"><a href="<c:url value='/CalendarServlet'/>" class="nav__link">カレンダー</a></li>
				<li class="nav__item"><a href="<c:url value='/StoreServlet'/>" class="nav__link">ストア</a></li>
				<li class="nav__item"><a href="<c:url value='/HelpServlet'/>" class="nav__link">ヘルプ</a></li>
				<li class="nav__item"><a href="<c:url value='/LogoutServlet'/>" class="nav__link" id="logout">ログアウト</a></li>
			</ul>
		</nav>
	</div>

	<!-- メイン -->
	<div class="flame">
		<div class="rectangle-label">ヘルプ</div>
		<div class="rectangle-big">
			<div class="rectangle">
				<div class="form-wrapper">
					<a href="${region}">
						<input type="submit" name="link" class="link help-button" value="ゴミの出し方について">
					</a>
					<input type="submit" name="pointinfo" class="pointinfo help-button" value="ポイントについて">
					<input type="submit" name="subject" class="subject help-button" value="お問い合わせ">

					<!-- 入力欄 -->
					<form class="Form">
						<div class="Form-Item">
							<p class="Form-Item-Label">件名</p>
							<input type="text" name="title" class="Form-Item-Input"required>
						</div>
						<div class="Form-Item">
							<p class="Form-Item-Label">内容</p>
							<textarea name="inquiry" class="Form-Item-Input2"required></textarea>
						</div>
						<input type="submit" name="sendinquiry" class="submit_btn" value="送信">
					</form>
				</div>
			</div>
		</div>
	</div>

	
	<div id="popup" class="popup">
		<div class="popup-content">
			<span class="popup-close" id="popup-close">&times;</span>
			<img src="<c:url value='/img/point_info.png'/>" alt="ポイント情報" class="popup-image">
		</div>
	</div>
<script>
'use strict';
var btn = document.getElementById('logout');
btn.addEventListener('click', function(event){
	var result = window.confirm('ログアウトしますか？');
	if(result){
		window.alert("ログアウトしました");
		window.location.href = 'LogoutServlet';
	}else{
		event.preventDefault();
	}
	
},false);
</script>
</body>
</html>
