window.addEventListener("DOMContentLoaded", () => {
	const hour_time = new Date().getHours();
	if ((type.trim().length > 0) && currentDate === "1970-01-01" && hour_time < 18) {
		document.getElementById("checkModal").style.display = "block";
	}
});

const weekDays = [
	{ name: '日', class: 'sunday' },
	{ name: '月', class: 'monday' },
	{ name: '火', class: 'tuesday' },
	{ name: '水', class: 'wednesday' },
	{ name: '木', class: 'thursday' },
	{ name: '金', class: 'friday' },
	{ name: '土', class: 'saturday' }
];

const today = new Date().getDay(); // 0〜6を返す（日曜〜土曜）
const todayInfo = weekDays[today];

const weekdayElement = document.getElementById('weekday');
weekdayElement.textContent = todayInfo.name;
weekdayElement.classList.add(todayInfo.class);

const weekBack = [
	{ name: '日', class: 'sunday_back' },
	{ name: '月', class: 'monday_back' },
	{ name: '火', class: 'tuesday_back' },
	{ name: '水', class: 'wednesday_back' },
	{ name: '木', class: 'thursday_back' },
	{ name: '金', class: 'friday_back' },
	{ name: '土', class: 'saturday_back' }
];


const backInfo = weekDays[today];

const weekBackElement = document.getElementById('weekback');
weekBackElement.classList.add(todayInfo.class);
