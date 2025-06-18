<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
<link rel="stylesheet" href="css/home.css">
</head>
<body>
<p>チェック済み日：${home.current}</p>

<!-- JavaScriptで1970-01-01かどうかチェックしてモーダル表示 -->
<c:set var="currentDate" value="${home.current}" />
<script>
  const currentDate = "${home.current}";
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
      <button type="submit"><img src="img/tokei.png" alt="チェック"></button>
    </form>
  </div>
</div>
</body>
</html>
