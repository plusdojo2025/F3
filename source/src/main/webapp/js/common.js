/**
 * 
 */
 
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