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
<script defer src="js/mypage.js"></script>
<meta charset="UTF-8">
<title>ポイポイ|マイページ</title>
<style>
.modal-overlay {
  display: none;
  position: fixed;
  top:0; left:0; width: 100%; height: 100%;
  background: rgba(0,0,0,0.5);
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
}
</style>

</head>
<body>

<!-- モーダル -->
<div id="modalOverlay" class="modal-overlay">
  <div class="modal">
    <h3>画像を選択してください</h3>
    <% for (Icon icon : icons) { %>
    <img src="img/<%= icon.getIcon_name()%>" class="image-option" data-id="<%= icon.getIcon_id() %>">
    <% } %>
    <br><br>
    <button onclick="closeModal()">キャンセル</button>
  </div>
</div>
<c:set var="e" value="${mypage}" />
称号:${e.degree_name}
<br>
ポイント:${e.point}
<br>
<form method="POST" action="/F3/MypageServlet">

アイコン<br>
<img src="img/${e.icon_name}" alt="サンプル">
<button type="button" onclick="openModal()">画像を選ぶ</button>
<input type="hidden" name="icon_id" id="selectedIconId"value="${e.icon_id}">
<br><br>
<!-- ユーザー名 -->
ユーザー名<br>
<input type="text" name="name" id="name" placeholder="Name"value="${e.user_name}"><br>
<!-- 地域 -->
地域<br>
<select name="region_id" id="region_id">
		<option value="${e.region_id}" selected hidden>${e.region_name}</option>
        <% for (Region region : regions) { %>
            <option value="<%= region.getRegion_id() %>"><%= region.getRegion_name() %></option>
        <% } %>
</select><br>
<!-- メール -->
メールアドレス<br><input type="email" name="mail" id="mail" value="${e.mail}"><br>
<input type="submit" name="insert_btn" value="更新">
</form>
</body>
</html>