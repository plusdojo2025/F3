<<<<<<< Updated upstream
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Region" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<Region> region = (List<Region>) request.getAttribute("region");
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.Region"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
List<Region> region = (List<Region>) request.getAttribute("region");
>>>>>>> Stashed changes
%>
<!DOCTYPE html>
<html>
<head>
<<<<<<< Updated upstream
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale=1">
    <title>ポイポイ</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/help.css">    
=======
<meta charset="UTF-8">
<meta name="viewport" content="width-device-width, initial-scale=1">
<title>ポイポイ</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/help.css">
<script src="/F3/js/help.js"></script>
<script src="/F3/js/common.js"></script>

>>>>>>> Stashed changes
</head>
<body>
	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="C:\Users\user\Documents\グループ開発\home.html"><img
			src="img/logo.png"></a>
	</div>

	<!-- index.html -->
	<div class="header">
		<button class="hamburger" name="humberger_menu" aria-label="メニュー"
			aria-controls="nav-menu" aria-expanded="false">
			<img id="hamburger-icon" src="img/hamburger_open.png">
		</button>

<<<<<<< Updated upstream
    <nav id="nav-menu" class="nav" aria-hidden="true">
            <ul class="nav__list">
            <li class="nav__item"><a href="C:\Users\user\Documents\グループ開発\home.html" class="nav__link" name="home_link" >ホーム</a></li>
            <li class="nav__item"><a href="C:\Users\user\Documents\グループ開発\mypage.html" class="nav__link" name="mypege_link">マイページ</a></li>
            <li class="nav__item"><a href="C:\Users\user\Documents\グループ開発\calender.html" class="nav__link" name="calender_link">カレンダー</a></li>
            <li class="nav__item"><a href="C:\Users\user\Documents\グループ開発\store.html" class="nav__link" name="store_link">ストア</a></li>
            <li class="nav__item"><a href="C:\Users\user\Documents\グループ開発\help.html" class="nav__link" name="help_link">へルプ</a></li>
            <li class="nav__item"><a href="C:\Users\user\Documents\グループ開発\top.html" class="nav__link" name="logout_btn">ログアウト</a></li>
            </ul>
        </nav>
    </div>
    
<div class="flame">
  <img src="img/flame_help.png"class="flame-img" />
=======
		<nav id="nav-menu" class="nav" aria-hidden="true">
			<ul class="nav__list">
				<li class="nav__item"><a
					href="C:\Users\user\Documents\グループ開発\home.html" class="nav__link"
					name="home_link">ホーム</a></li>
				<li class="nav__item"><a
					href="C:\Users\user\Documents\グループ開発\mypage.html" class="nav__link"
					name="mypege_link">マイページ</a></li>
				<li class="nav__item"><a
					href="C:\Users\user\Documents\グループ開発\calender.html"
					class="nav__link" name="calender_link">カレンダー</a></li>
				<li class="nav__item"><a
					href="C:\Users\user\Documents\グループ開発\store.html" class="nav__link"
					name="store_link">ストア</a></li>
				<li class="nav__item"><a
					href="C:\Users\user\Documents\グループ開発\help.html" class="nav__link"
					name="help_link">へルプ</a></li>
				<li class="nav__item"><a
					href="C:\Users\user\Documents\グループ開発\top.html" class="nav__link"
					name="logout_btn">ログアウト</a></li>
			</ul>
		</nav>
	</div>
>>>>>>> Stashed changes

	<div class="flame">
		<img src="img/flame_help.png" class="flame-img" />

<<<<<<< Updated upstream
    <a href=${region[0].link}$ ><input type="submit" name="link" class="link help-button" value="ゴミの出し方について"></a>
    <input type="submit" name="pointinfo" class="pointinfo help-button" value="ポイントについて">
    <input type="submit" name="subject" class="subject help-button" value="お問い合わせ">

    <!-- モーダルポップアップ -->
      <div id="popup" class="popup">
        <div class="popup-content">
          <span class="popup-close" id="popup-close">&times;</span>
          <img src="img/point_info.png" alt="ポイント情報" class="popup-image">
        </div>
      </div>
<!-- 入力欄 -->
    <form  class="Form">
      <div class="Form-Item">
        <p class="Form-Item-Label">件名</p>
        <input type="text" name="title" class="Form-Item-Input">
      </div>
      <div class="Form-Item">
        <p class="Form-Item-Label">内容</p>
        <textarea name="inquiry" class="Form-Item-Input2"></textarea>
      </div>
      <input type="submit" name="sendinquiry" class="submit_btn" value="送信">
=======
		<div class="form-wrapper">

			<a href=${region[0].link}><input type="submit" name="link"
				class="link help-button" value="ゴミの出し方について"></a> <input
				type="submit" name="pointinfo" class="pointinfo help-button"
				value="ポイントについて"> <input type="submit" name="subject"
				class="subject help-button" value="お問い合わせ">
>>>>>>> Stashed changes


<<<<<<< Updated upstream
<script>



</script>
=======
			<!-- モーダルポップアップ -->
			<div id="popup" class="popup">
				<div class="popup-content">
					<span class="popup-close" id="popup-close">&times;</span> <img
						src="img/point_info.png" alt="ポイント情報" class="popup-image">
				</div>
			</div>
			<!-- 入力欄 -->
			<form class="Form">
				<div class="Form-Item">
					<p class="Form-Item-Label">件名</p>
					<input type="text" name="title" class="Form-Item-Input">
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label">内容</p>
					<textarea name="inquiry" class="Form-Item-Input2"></textarea>
				</div>
				<input type="submit" name="sendinquiry" class="submit_btn"
					value="送信">

			</form>
		</div>
	</div>

>>>>>>> Stashed changes
</body>
</html>