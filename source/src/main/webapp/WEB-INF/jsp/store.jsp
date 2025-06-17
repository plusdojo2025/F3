<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ストア</title>
</head>
<body>
<h1>一覧</h1>
<hr>

<c:forEach var="e" items="${IconList}" >
	<form method="POST" action="/F3/StoreServlet">
	※実際は見えないid<input type="text" name="icon_id" value="${e.icon_id}">
	<img src="img/${e.icon_name}" width="270" height="150" alt="サンプル">
	price<input type="text" name="price" value="${e.price}"><br>
	<input type="submit" name="submit" value="交換">
	</form>
	<hr>
</c:forEach>
<c:if test="${empty IconList}">
<p>指定された条件に一致するデータはありません。</p>
</c:if>

</body>
</html>