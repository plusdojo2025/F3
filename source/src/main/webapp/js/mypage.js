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
