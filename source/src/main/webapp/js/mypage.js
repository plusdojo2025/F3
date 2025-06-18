'use strict';

//ハンバーガーメニュー
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

//モーダル関連
// モーダルを表示する関数
function openModal() {
  document.getElementById('modalOverlay').style.display = 'flex';
}

// モーダルを非表示にする関数
function closeModal() {
  document.getElementById('modalOverlay').style.display = 'none';
}

// DOM が読み込まれてから動作開始
document.addEventListener('DOMContentLoaded', function () {
  const images = document.querySelectorAll('.image-option');
  const hiddenInput = document.getElementById('selectedIconId');
  const previewImg = document.getElementById('previewIcon');

  images.forEach(img => {
    img.addEventListener('click', () => {
      // すべての選択を解除
      images.forEach(i => i.classList.remove('selected'));
      img.classList.add('selected');

      // アイコンIDをhiddenにセット
      const iconId = img.dataset.id;
      hiddenInput.value = iconId;

      // プレビュー画像を選択画像に変更
      if (previewImg) {
        previewImg.src = img.src;
      }

      closeModal();
    });
  });
});
