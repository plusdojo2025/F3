<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Region" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<Region> regions = (List<Region>) request.getAttribute("regions");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ポイポイ｜新規登録</title>
<script src="/F3/js/regist.js"></script>
</head>
<body>
<h1>新規登録</h1>
<hr>
<form name="registForm" method="POST" action="/F3/RegistServlet" onsubmit="return validateForm()">
ユーザー名<input type="text" name="name_input" id="name_input"><br>
パスワード<input type="password" id="pw_input" name="pw_input"><br>
パスワード（2回目）<input type="password" id="pw_re_input" name="pw_re_input"><br>
<label for="region">居住地域</label>
    <select name="region_input" id="region_input">
    <c:forEach var="region" items="${regions}">
        <option value="${region.region_id}">${region.region_name}</option>
    </c:forEach>
</select><br>
メールアドレス<input type="text" name="mail_input"><br>
<input type="submit" name="insert_btn" value="登録">
</form>
</body>
</html>