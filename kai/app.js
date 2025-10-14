document.addEventListener('DOMContentLoaded', () => {

  const today = new Date();
  // Month is zero-indexed!
  if (today.getDate() === 28 && today.getMonth() === 7 && today.getFullYear() <= 2069) {
    document.querySelector('header').textContent = 'Happy birthday, Jack!';
  }

  const video = document.querySelector('video');
  if (!video) return;

  video.addEventListener('ended', () => {
    window.close();
  });
});

document.addEventListener('keydown', (e) => {

  const volumeManager = navigator.volumeManager;
  if (!volumeManager) return;

  switch (e.key) {
    case 'ArrowUp':
      volumeManager.requestUp();
      break;
    case 'ArrowDown':
      volumeManager.requestDown();
      break;
    case 'Enter':
      volumeManager.requestShow();
      break;
  }
});

document.addEventListener('visibilitychange', () => {
  if (document.visibilityState !== 'hidden') return;
  const video = document.querySelector('video');
  if (!video) return;
  video.currentTime = 0;
});
