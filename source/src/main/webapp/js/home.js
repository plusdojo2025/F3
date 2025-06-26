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

const dayColors = [
	"crimson",     // 日曜日
	"deepskyblue", // 月曜日
	"limegreen",   // 火曜日
	"orange",      // 水曜日
	"rebeccapurple", // 木曜日
	"goldenrod",   // 金曜日
	"saddlebrown"  // 土曜日
];


const today_back = new Date().getDay(); // 0〜6（日曜〜土曜）
const rect = document.querySelector(".rectangle2");
rect.style.backgroundColor = dayColors[today_back];


document.getElementById("today").innerHTML = showtoday();
function showtoday() {
	const now = new Date();
	const month = now.getMonth() + 1;
	const date = now.getDate();
	const showtoday = month + "月" + date + "日";
	return showtoday;
}
