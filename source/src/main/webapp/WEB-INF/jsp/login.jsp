<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ポイポイ｜ログイン</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/login.css' />">
<script defer src="<c:url value='/js/login.js' />"></script>
<script src="<c:url value='/js/common.js' />"></script>


</head>
<body>

<c:if test="${success eq 'true'}">
  <script>
    alert('ログイン成功しました。');
    window.location.href = "<c:url value='/HomeServlet' />"; 
  </script>
</c:if>
<c:if test="${success eq 'false'}">
  <script>
    alert('メールアドレス、もしくはパスワードが違います。');
  </script>
</c:if>

	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="<c:url value='/TopServlet' />"><img
			src="<c:url value='/img/logo.png'/>"></a>
	</div>
	
	<div class="flame"><img src="${pageContext.request.contextPath}/img/flame.png" >



		<div class="form-wrapper">
			<form class="Form" name="loginForm" method="POST" action="<c:url value='/LoginServlet' />" onsubmit="return validateForm()">
				<div class="Form-Item">
					<p class="Form-Item-Label">メールアドレス</p>
					<input type="text" name="email_input" id="email"
						class="Form-Item-Input">
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label">パスワード</p>
					<input type="password" name="pw_input" id="password"
						class="Form-Item-Input">
				</div>
				<input type="submit" name="login" class="login" value="ログイン">
			</form>
		
		</div>
	</div>
</body>
</html>