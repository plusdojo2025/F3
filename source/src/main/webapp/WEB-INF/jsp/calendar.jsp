<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.sql.Date, java.text.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    Map<Date, List<String>> calendarMap = (Map<Date, List<String>>) request.getAttribute("calendarMap");
    Calendar cal = Calendar.getInstance();
    cal.set(2025, Calendar.JUNE, 1);
    int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<html>
<head>
    <title>ごみカレンダー</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { width: 14.28%; height: 100px; vertical-align: top; border: 1px solid #999; padding: 6px; font-size: 13px; }
        th { background-color: #f0f0f0; }
        .checked { background-color: #e0f7fa; font-weight: bold; }
    </style>
</head>
<body>
    <h2>📅 2025年6月のごみ出し予定</h2>
    <table>
        <tr><th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th></tr>
<%
    int dayCounter = 1;
    boolean finished = false;
    while (!finished) {
        out.println("<tr>");
        for (int i = 1; i <= 7; i++) {
            if (dayCounter == 1 && i < startDayOfWeek) {
                out.println("<td></td>");
            } else if (dayCounter > daysInMonth) {
                out.println("<td></td>");
                finished = true;
            } else {
                cal.set(Calendar.DAY_OF_MONTH, dayCounter);
                Date currentDate = new Date(cal.getTimeInMillis());
                String currentStr = sdf.format(currentDate);

                // DBの日付と文字列で比較して types を取得
                List<String> types = null;
                boolean isChecked = false;
                if (calendarMap != null) {
                    for (Date key : calendarMap.keySet()) {
                        if (sdf.format(key).equals(currentStr)) {
                            types = calendarMap.get(key);
                            isChecked = types != null && !types.isEmpty() && "チェック済み".equals(types.get(0));
                            break;
                        }
                    }
                }

                out.print("<td" + (isChecked ? " class='checked'" : "") + ">");
                out.print("<strong>" + dayCounter + "</strong><br>");

                if (types != null) {
                    int from = isChecked ? 1 : 0;
                    for (int j = from; j < types.size(); j++) {
                        out.print(types.get(j) + "<br>");
                    }
                    if (isChecked) {
                        out.print("✅<br>");
                    }
                }
                out.println("</td>");
                dayCounter++;
            }
        }
        out.println("</tr>");
    }
%>
    </table>
</body>
</html>
