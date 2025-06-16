<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Region" %>
<%
    List<Region> regions = (List<Region>) request.getAttribute("regions");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ポイポイ｜新規登録</title>
</head>
<body>
<h1>新規登録</h1>
<hr>
<form method="POST" action="/F3/RegistServlet">
ユーザー名<input type="text" name="name_input"><br>
パスワード<input type="password" name="pw_input"><br>
パスワード（2回目）<input type="password" name="pw_re_input"><br>
<label for="region">居住地域</label>
    <select name="region_input" id="region_input">
        <% for (Region region : regions) { %>
            <option value="<%= region.getRegion_id() %>"><%= region.getRegion_name() %></option>
        <% } %>
    </select><br>
メールアドレス<input type="text" name="mail_input"><br>
<input type="submit" name="insert_btn" value="ログイン">
</form>
</body>
</html>