<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width-device-width, initial-scale=1">
<title>ポイポイ</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/top.css">
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
					href="../../../servlet/StoreServlet.java" class="nav__link"
					name="store_link">ストア</a></li>
				<li class="nav__item"><a
					href="F3/HelpServlet.java" class="nav__link"
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
			<button onclick="location.href='/F3/LoginServlet'" class="login_btn"
				name="login_btn">ログイン</button>
			<button onclick="location.href='/F3/RegistServlet'" name="regist_btn"
				class="regist_btn">新規登録</button>
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
    <link rel="stylesheet" href="C:\Users\user\Documents\グループ開発\css\top.css">     
</head>

<body>
   
    <!-- ヘッダーここから -->
     <div class="logo">
            <a href="C:\Users\user\Documents\グループ開発\home.html"><img src="C:\Users\user\Documents\グループ開発\開発素材\logo.png"></a>
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
    <img src="C:\Users\user\Documents\グループ開発\開発素材\frame.png" class="flame-img" />

    <div class="form-wrapper">
      <a href="C:\Users\user\Documents\グループ開発\login.html" name="login_btn" class="login_btn">ログイン</a>
      <a href="C:\Users\user\Documents\グループ開発\regist.html" name="regist_btn" class="regist_btn">新規登録</a>
    </div>
  </div>     



<script>
'use strict';
// ハンバーガーメニュー
document.addEventListener('DOMContentLoaded', () => {
  const hamburger = document.querySelector('.hamburger');
  const nav = document.querySelector('.nav');
  const hamburgerIcon = document.getElementById('hamburger-icon');

  const hamburgerImgPath = '開発素材/hamburger_open.png';
  const closeImgPath = '開発素材/hamburger_close.png';

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
});
</script>
</body>
</html>
