<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ホーム画面</title>
  <link rel="stylesheet" href="<c:url value='/css/common.css' />">
  <link rel="stylesheet" href="<c:url value='/css/regist.css' />">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <style>
    img#trash_icon {
      width: 32px;
      height: auto;
    }
  </style>
<!--  <script src="${pageContext.request.contextPath}/js/home.js"></script> -->
</head>

<body>
<%@ include file="/common.jsp" %>

<div class="flame">

		<div class="rectangle-label">ホーム</div>
		<div class="rectangle-big">
			<div class="rectangle">
				
	<div class="left_box">
    <div class="rectangle_first"><%--① --%>
		<div class="rectangle2">
        <p>次のごみ出しは<br><span class="highlight">${home.types}</span></p>
        <div id="weekday" id ="week"></div>
        <p id="today">20日<p>
        </div>
    </div>
    </div>
	<div class="right_box">

        <div class="circle"><div class="circle_child"><p>現在のランクは<br><span class="highlight2">${home.degree_name}</span></p><%--② --%>
    </div></div>
    <div class="rounded-rectangle"><%--③ --%>
    	<c:choose>
    		<c:when test="${home.score <110}">
				<c:set var="scorePercent" value="${(home.score % 10) * 10}" />
				<div class="rank-bar">
			    <div class="rank-fill" style="width: ${scorePercent}%;"></div>
				</div>
<p>次のランクまで、あと <span class="highlight_point">${10 - (home.score % 10)}pt</span></p>

			</c:when>
			<c:otherwise>
				<p>現在最高ランクです。</p>
			</c:otherwise>
		</c:choose>
   	</div></div>
   	
   	
   	
   	</div>
   	</div>
	</div>
<!-- JavaScriptで1970-01-01かどうかチェックしてモーダル表示 -->
<c:set var="currentDate" value="${home.current}" />
<script>
  const currentDate = "${home.current}";
  const type = "<c:out value='${home.types}' default='' />";
</script>

<script src="${pageContext.request.contextPath}/js/home.js"></script>



<!-- モーダル構造 -->
<div class="modal" id="checkModal">
  <div class="modal-content">
    <button class="close-button" onclick="document.getElementById('checkModal').style.display='none'">×</button>
    <p>チェックしますか？</p>
    <form action="HomeServlet" method="post">
      <input type="hidden" name="check_id" value="1" />
      <input type="hidden" name="score" value="${home.score}" />
      <input type="hidden" name="point" value="${home.point}" />
      <button type="submit"><img src="${pageContext.request.contextPath}/img/trash.png" id="trash_icon" alt="チェック"></button>
    </form>
  </div>
</div>
</body>
</html>
