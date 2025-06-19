<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ホーム画面</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
  <style>
    img#trash_icon {
      width: 32px;
      height: auto;
    }
  </style>
  <script src="${pageContext.request.contextPath}/js/home.js"></script>
</head>

<body>

<h6>ホーム画面</h6>
<p>チェック済み日：${home.current}</p>
<p>${hour}</p>
<a href="CalendarServlet"></a>
<a href="HomeServlet"></a>


    <div class="" id="garbage_type"><%--① --%>
        <p>次のごみ出しは<br><span class="highlight">${home.types}</span></p>
    </div>

    <div class="card"><%--② --%>
        <p>現在のランクは<br><span class="highlight">${home.degree_name}</span></p>
    </div>

    <div class="card"><%--③ --%>
		<c:set var="nextScore" value="${10 - (home.score % 10)}" />
		<p>次のランクまで、あと${nextScore}pt</p>
    </div>
<!-- JavaScriptで1970-01-01かどうかチェックしてモーダル表示 -->
<c:set var="currentDate" value="${home.current}" />
<script>
const currentDate = "${home.current}";
</script>


<script src="js/home.js"></script>


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
