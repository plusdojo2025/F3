/**
 * 
 */

 'use strict';

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


