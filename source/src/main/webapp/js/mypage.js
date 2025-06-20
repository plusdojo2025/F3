'use strict';

// ハンバーガーメニュー
document.addEventListener('DOMContentLoaded', () => {
  const hamburger = document.querySelector('.hamburger');
  const nav = document.querySelector('.nav');
  const hamburgerIcon = document.getElementById('hamburger-icon');

  const hamburgerImgPath = 'img/hamburger_open.png';
  const closeImgPath = 'img/hamburger_close.png';

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

// モーダル関連
function openModal() {
  document.getElementById('modalOverlay').style.display = 'flex';
}

function closeModal() {
  document.getElementById('modalOverlay').style.display = 'none';
}

document.addEventListener('DOMContentLoaded', function () {
  const images = document.querySelectorAll('.image-option');
  const hiddenInput = document.getElementById('selectedIconId');
  const previewImg = document.getElementById('previewIcon');

  images.forEach(img => {
    img.addEventListener('click', () => {
      images.forEach(i => i.classList.remove('selected'));
      img.classList.add('selected');

      const iconId = img.dataset.id;
      hiddenInput.value = iconId;

      if (previewImg) {
        previewImg.src = img.src;
      }

      closeModal();
    });
  });
});

// バリデーションチェック
document.getElementById('registForm').addEventListener('submit', function(e) {
  const errors = [];

  // ユーザー名のチェック
  const nameInput = document.getElementById('nameInput');
  const name = nameInput.value.trim();
  const nameRegex = /^[\w!@#\$%\^&\*\(\)\-=\+_\[\]\{\},\.]{1,30}$/;

  if (name === '') {
    errors.push("・ユーザー名は必須です");
  } else if (!nameRegex.test(name)) {
    errors.push("・ユーザー名は全半角英数字と記号のみ、30文字以内で入力してください");
  }

  // メールアドレスのチェック
  const emailInput = document.getElementById('emailInput');
  const email = emailInput.value.trim();
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  // 全角文字チェック関数（ASCII以外を含むか）
  function containsZenkaku(str) {
    return /[^\u0020-\u007E]/.test(str);
  }

  if (email === '') {
    errors.push("・メールアドレスは必須です");
  } else if (!emailRegex.test(email)) {
    errors.push("・正しいメールアドレスの形式で入力してください");
  } else {
    const domain = email.split('@')[1];
    if (domain && containsZenkaku(domain)) {
      errors.push("・メールアドレスの @ 以降に全角文字は使用できません（半角英数字で入力してください）");
    }
  }

  // エラーがあれば送信を中止
  if (errors.length > 0) {
    alert("以下の内容を確認してください：\n" + errors.join("\n"));
    e.preventDefault();
  }
});
