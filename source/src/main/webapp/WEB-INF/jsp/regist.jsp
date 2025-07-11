<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.Region"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Region> regions = (List<Region>) request.getAttribute("regions");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ポイポイ</title>
<c:if test="${result eq 'true'}">
<script>
    alert("登録成功しました！");
    window.location.href = "<c:url value='/LoginServlet' />";
</script>
</c:if>
<c:if test="${result eq 'false'}">
<script>
    alert("登録に失敗しました。おそらく既に存在しているメールアドレスです。");
    window.location.href = "<c:url value='/RegistServlet' />";
</script>
</c:if>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/regist.css' />">
<script src="<c:url value='/js/regist.js' />"></script>
<script src="<c:url value='/js/common.js' />"></script>
</head>
<body>


	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="<c:url value='/TopServlet' />"><img
			src="<c:url value='/img/logo.png'/>"></a>
	</div>
	<div class="flame">
	<div class="rectangle-label">新規登録</div> <!-- ← ラベルを追加 -->
	<div class="rectangle-big">
	<div class="rectangle">

		<div class="form-wrapper">
			<form class="Form" id="registForm" name="registForm" method="POST"
				action="<c:url value='/RegistServlet' />"
				onsubmit="return validateForm()">
				<div class="Form-Item">
					<p class="Form-Item-Label">ユーザー名</p>
					<input type="text" name="name_input" class="Form-Item-Input"
						id="name_input" onKeyup="valiCheck()">
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label">パスワード</p>
					<input type="password" name="pw_input" class="Form-Item-Input"
						id="pw_input" onKeyup="valiCheck()">
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label" onKeyup="valiCheck()">
						パスワード<br>（2回目）
					</p>
					<input type="password" name="pw_re_input" class="Form-Item-Input"
						id="pw_re_input" onKeyup="valiCheck()">
				</div>
				<!-- ここはプルダウンの選択肢に -->
				<div class="Form-Item">
					<p class="Form-Item-Label">居住地域</p>
					<select id="region_input" name="region_input"
						class="Form-Item-Input">
						<option value=0 class="Pull-Down" selected>選択してください</option>
						<c:forEach var="region" items="${regions}">
							<option value="${region.region_id}">${region.region_name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="Form-Item">
					<p class="Form-Item-Label">メールアドレス</p>
					<input type="text" name="mail_input" class="Form-Item-Input"
						onKeyup="valiCheck()">
				</div>
				<input type="submit" name="insert_btn" class="insert_btn" value="登録">
				<p id="error_msg" class="font-red"></p>
			</form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>