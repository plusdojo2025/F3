<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ポイポイ</title>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/top.css' />">


</head>
<body>
<div class="circle"></div>

	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="<c:url value='/TopServlet' />"><img src="<c:url value='/img/logo.png'/>"></a>
	</div>

	<div class="rectangle">
	<div class="rectangle2">
	<div class="flame">

		<div class="form-wrapper">

			<button onclick="location.href='<c:url value='/LoginServlet' />'"
				class="login_btn" name="login_btn">ログイン</button>
			<button onclick="location.href='<c:url value='/RegistServlet' />'"
				name="regist_btn" class="regist_btn">新規登録</button>

		</div></div>
	</div>
	</div>
</body>
</html>
