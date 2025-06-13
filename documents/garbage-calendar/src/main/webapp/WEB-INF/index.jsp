<%@ page import="java.util.*, java.time.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    int year = (int) request.getAttribute("year");
    int month = (int) request.getAttribute("month");

    int prevMonth = (month == 1) ? 12 : month - 1;
    int prevYear = (month == 1) ? year - 1 : year;
    int nextMonth = (month == 12) ? 1 : month + 1;
    int nextYear = (month == 12) ? year + 1 : year;

    int firstDay = (int) request.getAttribute("firstDayOfWeek");
    int daysInMonth = (int) request.getAttribute("daysInMonth");
    Map<LocalDate, List<String>> schedule = (Map<LocalDate, List<String>>) request.getAttribute("scheduleMap");
%>
<html>
<head>
    <title>ごみ収集カレンダー</title>
    <style>
        body {
            font-family: sans-serif;
            background: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .calendar-container {
            max-width: 900px;
            margin: auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .nav {
            text-align: center;
            margin-bottom: 20px;
        }

        .nav a {
            margin: 0 20px;
            text-decoration: none;
            font-weight: bold;
            color: #007BFF;
        }

        h2 {
            text-align: center;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            table-layout: fixed;
        }

        th, td {
            border: 1px solid #ccc;
            text-align: left;
            vertical-align: top;
            height: 100px;
            padding: 6px;
            box-sizing: border-box;
        }

        th {
            background-color: #007BFF;
            color: white;
            text-align: center;
        }

        .garbage {
            font-size: 0.9em;
            color: #444;
            margin-top: 6px;
        }
    </style>
</head>
<body>
    <div class="calendar-container">
        <div class="nav">
            <a href="?year=<%= prevYear %>&month=<%= prevMonth %>">← 前月</a>
            <a href="?year=<%= nextYear %>&month=<%= nextMonth %>">次月 →</a>
        </div>

        <h2><%= year %>年 <%= month %>月 ごみ収集カレンダー</h2>

        <table>
            <tr>
                <th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th>
            </tr>
            <tr>
            <% int dayCounter = 1;
               for (int i = 0; i < firstDay; i++) { %>
                <td></td>
            <% } %>

            <% for (int i = firstDay; i < 7; i++) {
                   LocalDate date = LocalDate.of(year, month, dayCounter);
            %>
                <td><strong><%= dayCounter %></strong>
                    <div class="garbage">
                        <% List<String> types = schedule.get(date);
                           if (types != null) {
                               for (String type : types) {
                                   out.println(type + "<br>");
                               }
                           }
                        %>
                    </div>
                </td>
            <% dayCounter++; } %>
            </tr>

            <% while (dayCounter <= daysInMonth) { %>
            <tr>
                <% for (int i = 0; i < 7; i++) {
                       if (dayCounter > daysInMonth) { %>
                           <td></td>
                       <% } else {
                           LocalDate date = LocalDate.of(year, month, dayCounter);
                       %>
                           <td><strong><%= dayCounter %></strong>
                               <div class="garbage">
                                   <% List<String> types = schedule.get(date);
                                      if (types != null) {
                                          for (String type : types) {
                                              out.println(type + "<br>");
                                          }
                                      }
                                   %>
                               </div>
                           </td>
                       <% dayCounter++;
                       }
                   } %>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
