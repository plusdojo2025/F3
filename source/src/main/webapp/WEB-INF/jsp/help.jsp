<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale=1">
    <title>ポイポイ</title>
    <link rel="stylesheet" href="C:\Users\user\Documents\グループ開発\css\common.css">
    <link rel="stylesheet" href="C:\Users\user\Documents\グループ開発\css\help.css">    
</head>
<body>
   
    <!-- ヘッダーここから -->
        <div class="logo">
            <a href="C:\Users\user\Documents\グループ開発\home.html"><img src="C:\Users\user\Documents\グループ開発\開発素材\logo.png"></a>
        </div>

<!-- index.html -->
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
    
<div class="flame">
  <img src="C:\Users\user\Documents\グループ開発\開発素材\flame_help.png"class="flame-img" />

  <div class="form-wrapper">

    <input type="submit" name="link" class="link help-button" value="ゴミの出し方について">
    <input type="submit" name="pointinfo" class="pointinfo help-button" value="ポイントについて">
    <input type="submit" name="subject" class="subject help-button" value="お問い合わせ">

    <!-- モーダルポップアップ -->
      <div id="popup" class="popup">
        <div class="popup-content">
          <span class="popup-close" id="popup-close">&times;</span>
          <img src="C:\Users\user\Documents\グループ開発\開発素材\point_info.png" alt="ポイント情報" class="popup-image">
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

    </form>
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

// お問い合わせボタンでフォーム表示
document.addEventListener('DOMContentLoaded', () => {
  const subjectBtn = document.querySelector('.subject');
  const form = document.querySelector('.Form');
  const flameImg = document.querySelector('.flame-img');

  subjectBtn.addEventListener('click', () => {
    const isVisible = form.style.display === 'flex';

    // トグル表示
    form.style.display = isVisible ? 'none' : 'flex';

    // flame-imgの伸縮アニメーション
    flameImg.classList.toggle('expanded', !isVisible);
  });
});

// 送信時ウインドウアラート
document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('.Form');

  form.addEventListener('submit', function(event) {
    const result = confirm("この内容で送信しますか？");
    if (!result) {
      event.preventDefault(); // ❌ キャンセルなら送信中止
    }
  });
});

// ポイント使い方

document.addEventListener('DOMContentLoaded', () => {
  const pointinfoButton = document.querySelector('.pointinfo');
  const popup = document.getElementById('popup');
  const popupClose = document.getElementById('popup-close');

  pointinfoButton.addEventListener('click', () => {
    popup.style.display = 'flex'; // ポップアップを表示
  });

  popupClose.addEventListener('click', () => {
    popup.style.display = 'none'; // 閉じる
  });

  // 背景クリックで閉じる
  popup.addEventListener('click', (e) => {
    if (e.target === popup) {
      popup.style.display = 'none';
    }
  });
});
</script>
</body>
</html>
