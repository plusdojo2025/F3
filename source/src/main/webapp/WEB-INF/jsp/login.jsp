<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ポイポイ｜ログイン</title>
<script src="/F3/js/login.js"></script>
</head>
<body>
<h1>ログイン</h1>
<hr>
<form name="loginForm" method="POST" action="/F3/LoginServlet" onsubmit="return validateForm()">
    メールアドレス<input type="text" name="email_input"><br>
    パスワード<input type="password" name="pw_input"><br>
    <input type="submit" name="login" value="ログイン">
</form>
</body>
</html>