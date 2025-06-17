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
	<form method="POST" action="/webappAns/UpdateDeleteServlet">
	id<input type="text" name="number" value="${e.icon_id}">
	<img src="img/${e.icon_name}" alt="サンプル">
	price<input type="text" name="department" value="${e.price}"><br>
	<input type="submit" name="submit" value="交換">
	</form>
	<hr>
</c:forEach>
<c:if test="${empty IconList}">
<p>指定された条件に一致するデータはありません。</p>
</c:if>

</body>
</html>