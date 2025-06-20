<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <!-- ↓ カレンダー全体を中央に配置するためのラッパー -->
    <div class="main-wrapper">
        <div class="calendar-container">
            <div class="calendar-header">
                <c:choose>
                    <c:when test="${code == '1'}">
                        <h2>📅 ${displayYear}年 ${displayMonth}月のごみ出し予定</h2>
                        <form action="CalendarServlet" method="get">
                        <input type="hidden" name="code" value="">
                        <button>前</button>
                        </form>
                    </c:when>
                    <c:when test="${code == '2'}">
                    	<form action="CalendarServlet" method="get">
                        <input type="hidden" name="code" value="">
                        <button>次</button>
                        </form>
                        <h2>📅 ${displayYear}年 ${displayMonth}月のごみ出し予定</h2>
                    </c:when>
                    <c:otherwise>
                    	<form action="CalendarServlet" method="get">
                        <input type="hidden" name="code" value="1">
                        <button>前</button>
                        </form>
                        <h2>📅 ${displayYear}年 ${displayMonth}月のごみ出し予定</h2>
                        <form action="CalendarServlet" method="get">
                        <input type="hidden" name="code" value="2">
                        <button>次</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>

            <table border="1">
                <tr>
                    <th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th>
                </tr>
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
            </table>

            <div style="margin-top: 20px;">
                <c:forEach var="link" items="${links}">
                    <a href="${link.link}">泉</a>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
