const weeks = ['日曜日', '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日']
const date = new Date()
let year = date.getFullYear()
let month = date.getMonth() + 1

const flammable = '<p id = "flammable">可燃ごみ</p>';
const house = '<p id = "house">家庭ごみ</p>';
const plasticBottle = '<p id = "plasticBottle">ペットボトル</p>';
const plastic = '<p id = "plastic">プラごみ</p>';
const canBottle = '<p id = "canBottle">缶・ビン</p>';
const battery = '<p id = "battery">乾電池類</p>';
const paper = '<p id = "paper">紙ごみ</p>';


function showCalendar(year, month) {

    const calendarHtml = createCalendar(year, month)
    const sec = document.createElement('section')
    sec.innerHTML = calendarHtml
    document.querySelector('#calendar').appendChild(sec)

    month++
    if (month > 12) {
        year++
        month = 1
    }

}

function createCalendar(year, month) {
    const startDate = new Date(year, month - 1, 1) // 月の最初の日を取得
    const endDate = new Date(year, month, 0) // 月の最後の日を取得
    const endDayCount = endDate.getDate() // 月の末日
    const lastMonthEndDate = new Date(year, month - 1, 0) // 前月の最後の日の情報
    const lastMonthendDayCount = lastMonthEndDate.getDate() // 前月の末日
    const startDay = startDate.getDay() // 月の最初の日の曜日を取得
    let dayCount = 1 // 日にちのカウント
    let calendarHtml = '' // HTMLを組み立てる変数

    calendarHtml += '<h1>' + year + '/' + month + '</h1>'
    calendarHtml += '<table>'

    // 曜日の行を作成
    for (let i = 0; i < weeks.length; i++) {
        calendarHtml += '<td>' + weeks[i] + '</td>'
    }

    for (let w = 0; w < 6; w++) {
        calendarHtml += '<tr>'

        for (let d = 0; d < 7; d++) {
            if (w == 0 && d < startDay) {
                // 1行目で1日の曜日の前
                let num = lastMonthendDayCount - startDay + d + 1
                calendarHtml += '<td class="is-disabled">' + num + '</td>'
            } else if (dayCount > endDayCount) {
                // 末尾の日数を超えた
                let num = dayCount - endDayCount
                calendarHtml += '<td class="is-disabled">' + num + '</td>'
                dayCount++
            } else {

                calendarHtml += '<td>';
                if (d === 1) {
                    calendarHtml += dayCount + house;
                } else if (d === 2) {
                    calendarHtml += dayCount + plastic;
                } else if (d === 4) {
                    calendarHtml += dayCount + house + canBottle + plasticBottle + battery;
                } else if (d === 5 && w === 1) {
                    calendarHtml += dayCount + paper;
                } else {
                    calendarHtml += dayCount;
                }
                calendarHtml += '</td>';

                dayCount++
            }
        }
        calendarHtml += '</tr>'
    }
    calendarHtml += '</table>'

    return calendarHtml
}

function moveCalendar(e) {
    document.querySelector('#calendar').innerHTML = ''

    if (e.target.id === 'prev') {
        month--

        if (month < 1) {
            year--
            month = 12
        }
    }

    if (e.target.id === 'next') {
        month++

        if (month > 12) {
            year++
            month = 1
        }
    }

    showCalendar(year, month)
}

document.querySelector('#prev').addEventListener('click', moveCalendar)
document.querySelector('#next').addEventListener('click', moveCalendar)

showCalendar(year, month)