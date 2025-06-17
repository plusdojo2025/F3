<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.Region" %>
<%@ page import="dto.Icon" %>
<%List<Region> regions = (List<Region>) request.getAttribute("regions");%>
<%List<Icon> icons = (List<Icon>)request.getAttribute("icon");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ポイポイ|マイページ</title>
</head>
<body>
<form method="POST" action="/F3/MypageServlet">
<c:set var="e" value="${mypage}" />
アイコン<br>

<!-- ユーザー名 -->
ユーザー名<br>
<input type="text" name="name" id = "name" placeholder="Name"value="${e.name}">
<!-- 地域 -->
地域<br>
<select name="region_id" id="region_id">
		<option value="${e.region_id}" selected hidden>"${e.region_name}"</option>
        <% for (Region region : regions) { %>
            <option value="<%= region.getRegion_id() %>"><%= region.getRegion_name() %></option>
        <% } %>
</select>
<!-- メール -->
メールアドレス<br><input type="email" name="mail" id = "mail" placeholder="Name"value="${e.mail}">
<input type="submit" name="insert_btn" value="更新">
</form>
</body>
</html>