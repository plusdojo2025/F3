/**
 * 
 */

const ua = navigator.userAgent;
const isMobile = /iPhone|Android.+Mobile/.test(ua);
const modal = document.querySelectorAll(".modal-open");
const garbageType = document.querySelectorAll(".garbageType");

garbageType.forEach(gt => {
	if (isMobile) {
		gt.classList.add("mobile");
		gt.setAttribute("data-device", "mobile");
		gt.hidden = true; // モバイルでは非表示

	} else {
		gt.classList.add("desktop");
		gt.setAttribute("data-device", "desktop");
		gt.hidden = false; // デスクトップでは表示
	}
});
modal.forEach(mo => {
	if (isMobile) {
		mo.classList.add("mobile");
		mo.setAttribute("data-device", "mobile");
		mo.addEventListener("click", () => {
			const target = event.target;
			if (target.tagName === 'TD') {
				let result = "";
				const value = target.textContent.trim();
				result = String(value).replace(/(	| |[0-9])/g, '');
				for (let i = 0; i < 3; i++) {
					result = result.replace('\n', '');
				}
				if(result === '') {
					result = 'ゴミ出し予定はありません。';
				}
				alert(result);
			}
		});
	}
});

