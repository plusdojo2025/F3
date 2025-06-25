<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ごみカレンダー</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>
  <%@ include file="/common.jsp" %>

  <div class="flame">
    <div class="rectangle-label">カレンダー</div>
    <div class="rectangle-big">
      <div class="rectangle">
        <div class="main-wrapper">
          <div class="calendar-container">

            <div class="calendar-header">
            	<c:choose>
                    <c:when test="${code == '1'}"><%--前月 --%>
              <div class="calendar-title-wrapper">
                <h2 class="calendar-title">${displayYear}年 ${displayMonth}月のごみ出し予定</h2>
              </div>
              <div class="calendar-button-right">
                <form action="CalendarServlet" method="get">
                  <input type="hidden" name="code" value="">
                  <button class="month-btn">翌月 &gt;&gt;</button>
                </form>
              </div>
              </c:when>
              
                                  <c:when test="${code == '2'}">
              <div class="calendar-button-left">
                <form action="CalendarServlet" method="get">
                  <input type="hidden" name="code" value="">
                  <button class="month-btn">&lt;&lt; 先月</button>
                </form>
              </div>
              <div class="calendar-title-wrapper">
                <h2 class="calendar-title">${displayYear}年 ${displayMonth}月のごみ出し予定</h2>
              </div>
              </c:when>
              
               <c:otherwise>
              <div class="calendar-button-left">
                <form action="CalendarServlet" method="get">
                  <input type="hidden" name="code" value="1">
                  <button class="month-btn">&lt;&lt; 先月</button>
                </form>
              </div>
              <div class="calendar-title-wrapper">
                <h2 class="calendar-title">${displayYear}年 ${displayMonth}月のごみ出し予定</h2>
              </div>
              <div class="calendar-button-right">
                <form action="CalendarServlet" method="get">
                  <input type="hidden" name="code" value="2">
                  <button class="month-btn">翌月 &gt;&gt;</button>
                </form>
              </div>
              </c:otherwise>
              </c:choose>
            </div>

            <table border="1">
              <thead class="weekday-header">
                <tr>
                  <th class="sunday">日</th>
                  <th class="weekday">月</th>
                  <th class="weekday">火</th>
                  <th class="weekday">水</th>
                  <th class="weekday">木</th>
                  <th class="weekday">金</th>
                  <th class="saturday">土</th>
                </tr>
              </thead>
              <tbody>
                <c:set var="day" value="1" />
                <c:forEach var="week" begin="1" end="6">
                  <tr>
                    <c:forEach var="d" begin="1" end="7">
                      <c:choose>
                        <c:when test="${week == 1 && d < calendarStartDay}">
                          <td></td>
                        </c:when>
                        <c:when test="${day <= daysInMonth}">
                          <c:set var="dayStr" value="${day lt 10 ? '0' : ''}${day}" />
                          <c:set var="monthStr" value="${displayMonth lt 10 ? '0' : ''}${displayMonth}" />
                          <c:set var="dateKey" value="${displayYear}-${monthStr}-${dayStr}" />
                          <c:set var="cell" value="${calendarViewMap[dateKey]}" />
                          <td class="${cell.isChecked ? 'checked' : ''}">
                            <strong>${day}</strong><br/>
                            <c:forEach var="type" items="${cell.types}">
                              ${type}<br/>
                            </c:forEach>
                            <c:if test="${cell.isChecked}">✅</c:if>
                          </td>
                          <c:set var="day" value="${day + 1}" />
                        </c:when>
                        <c:otherwise>
                          <td></td>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </tr>
                </c:forEach>
              </tbody>
            </table>

            <div class="link-button-wrapper">
              <c:forEach var="link" items="${links}">
                <button class="link-btn" onclick="location.href='${link.link}'">✙詳しい分別はこちら</button>
              </c:forEach>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
