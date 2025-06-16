<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名刺管理</title>
</head>
<body>
<h1>検索結果（更新／削除）</h1>
<hr>

<c:forEach var="e" items="${IconList}" >
	<form method="POST" action="/webappAns/UpdateDeleteServlet">
	<input type="text" name="number" value="${e.Icon_id}">
	会社名<input type="text" name="company" value="${e.Icon_name}"><br>
	部署名<input type="text" name="department" value="${e.price}"><br>
	<input type="submit" name="submit" value="更新">
	</form>
	<hr>
</c:forEach>
<c:if test="${empty cardList}">
<p>指定された条件に一致するデータはありません。</p>
</c:if>
<a href="/webappAns/MenuServlet">メニューへ戻る</a>

</body>
</html>