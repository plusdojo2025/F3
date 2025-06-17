/**
 * 
 */

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

  images.forEach(img => {
    img.addEventListener('click', () => {
      // すべての選択を解除
      images.forEach(i => i.classList.remove('selected'));
      img.classList.add('selected');

      // data-id からIDを取得して hidden input に代入
      const iconId = img.dataset.id;
      hiddenInput.value = iconId;

      // デバッグ用：選んだIDを表示
      alert("選択されたアイコンID: " + iconId);

      closeModal();
    });
  });
});