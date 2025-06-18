<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width-device-width, initial-scale=1">
<title>ポイポイ｜ログイン</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/login.css">
<script src="/F3/js/login.js"></script>
<script src="/F3/js/common.js"></script>
</head>
<body>
	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="C:\Users\user\Documents\グループ開発\home.html"><img
			src="img/logo.png"></a>
	</div>
	<div class="header">
		<button class="hamburger" name="humberger_menu" aria-label="メニュー"
			aria-controls="nav-menu" aria-expanded="false">
			<img id="hamburger-icon" src="img/hamburger_open.png">
		</button>

		<nav id="nav-menu" class="nav" aria-hidden="true">
			<ul class="nav__list">
				<li class="nav__item"><a href="webapp/WEB-INF/jsp/calender.jsp"
					class="nav__link" name="home_link">ホーム</a></li>
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
	</div>
	<div class="flame">
		<img src="img/frame.png" class="flame-img" />

		<div class="form-wrapper">
			<form class="Form" name="loginForm" method="POST"
				action="/F3/LoginServlet" onsubmit="return validateForm()">
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

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale=1">
    <title>ポイポイ</title>
    <link rel="stylesheet" href="C:\Users\user\Documents\グループ開発\css\common.css">
    <link rel="stylesheet" href="C:\Users\user\Documents\グループ開発\css\login.css">     
</head>
<body>
       <!-- ヘッダーここから -->
    <div class="logo">
            <a href="C:\Users\user\Documents\グループ開発\home.html"><img src="img/logo.png"></a>
    </div>
      <div class="header">
       <button class="hamburger" name="humberger_menu" aria-label="メニュー" aria-controls="nav-menu" aria-expanded="false"><img id="hamburger-icon" src="C:\Users\user\Documents\グループ開発\開発素材\hamburger_open.png">
      </button>

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
    </div>
 <div class="flame">
  <img src="C:\Users\user\Documents\グループ開発\開発素材\frame.png"class="flame-img" />

  <div class="form-wrapper">
    <form  class="Form">
      <div class="Form-Item">
        <p class="Form-Item-Label">メールアドレス</p>
        <input type="text" name="email_input" id="email" class="Form-Item-Input">
      </div>
      <div class="Form-Item">
        <p class="Form-Item-Label">パスワード</p>
        <input type="password" name="pw_input" id="password" class="Form-Item-Input">
      </div>
      <input type="submit" name="login" class="login" value="ログイン">
    </form>
  </div>
</div>     


</body>

<script> 
'use strict';


// 未入力時アラート
document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('.Form');

  form.addEventListener('submit', function(event) {
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!email && !password) {
      alert('必要項目を入力してください');
      event.preventDefault();
    } else if (!email) {
      alert('メールアドレスを入力してください');
      event.preventDefault();
    } else if (!password) {
      alert('パスワードを入力してください');
      event.preventDefault();
    }
  });
});
</script>

</html>

