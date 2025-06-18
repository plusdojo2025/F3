<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Region" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.Region"%>
<%
    List<Region> regions = (List<Region>) request.getAttribute("regions");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width-device-width, initial-scale=1">
<title>ポイポイ</title>
<link rel="stylesheet"
	href="css/common.css">
<link rel="stylesheet"
	href="css/regist.css">
<script src="/F3/js/regist.js"></script>
<script src="/F3/js/common.js"></script>
</head>
<body>

</form>
	<!-- ヘッダーここから -->
	<div class="logo">
		<a href="C:\Users\user\Documents\グループ開発\home.html"><img
			src="img/logo.png"> </a>
	</div>
	<div class="header">
		<button class="hamburger" name="humberger_menu" aria-label="メニュー"
			aria-controls="nav-menu" aria-expanded="false">
			<img id="hamburger-icon" src="img/hamburger_open.png">
		</button>

		<nav id="nav-menu" class="nav" aria-hidden="true">
			<ul class="nav__list">
				<li class="nav__item"><a
					href="<c:url value='/HomeServlet' />" class="nav__link"
					name="home_link">ホーム</a></li>
				<li class="nav__item"><a
					href="<c:url value='/MypageServlet' />" class="nav__link"
					name="mypege_link">マイページ</a></li>
				<li class="nav__item"><a
					href="<c:url value='/CalendarServlet'/>"
					class="nav__link" name="calender_link">カレンダー</a></li>
				<li class="nav__item"><a
					href="<c:url value='/StoreServlet'/>" class="nav__link"
					name="store_link">ストア</a></li>
				<li class="nav__item"><a
					href="<c:url value='/HelpServlet'/>" class="nav__link"
					name="help_link">へルプ</a></li>
				<li class="nav__item"><a
					href="<c:url value='/LogoutServlet'/>" class="nav__link"
					name="logout_btn">ログアウト</a></li>
			</ul>
		</nav>
	</div>
	
	<div class="flame">
		<img src="<c:url value='/img/flame_regist.png'/>"
			class="flame-img" />
				
			<div class="form-wrapper" >
				<form class="Form" id="registForm" name="registForm" method="POST" action="/F3/RegistServlet"
			onsubmit="return validateForm()" >
					<div class="Form-Item">
						<p class="Form-Item-Label">ユーザー名</p>
						<input type="text" name="name_input" class="Form-Item-Input" id="name_input">
					</div>
					<div class="Form-Item">
						<p class="Form-Item-Label">パスワード</p>
						<input type="password" name="pw_input" class="Form-Item-Input" id="pw_input">
					</div>
					<div class="Form-Item">
						<p class="Form-Item-Label">
							パスワード<br>（2回目）
						</p>
						<input type="password" name="pw_re_input" class="Form-Item-Input" id="pw_re_input">
					</div>
					<!-- ここはプルダウンの選択肢に -->
					<div class="Form-Item">
						<p class="Form-Item-Label">居住地域</p>
						<select id="region_input" name="region_input"
							class="Form-Item-Input">
							<option value="">選択してください</option>
							<!-- 東北 -->
							<option value="北海道">北海道</option>
							<option value="青森">青森県</option>
							<option value="岩手">岩手県</option>
							<option value="宮城">宮城県</option>
							<option value="秋田">秋田県</option>
							<option value="山形">山形県</option>
							<option value="福島">福島県</option>
							<!-- 関東 -->
							<option value="茨城">茨城県</option>
							<option value="栃木">栃木県</option>
							<option value="群馬">群馬県</option>
							<option value="埼玉">埼玉県</option>
							<option value="千葉">千葉県</option>
							<option value="東京">東京都</option>
							<option value="神奈川">神奈川県</option>
							<!-- 中部 -->
							<option value="新潟">新潟県</option>
							<option value="富山">富山県</option>
							<option value="石川">石川県</option>
							<option value="福井">福井県</option>
							<option value="山梨">山梨県</option>
							<option value="長野">長野県</option>
							<option value="岐阜">岐阜県</option>
							<option value="静岡">静岡県</option>
							<option value="愛知">愛知県</option>
							<!-- 近畿 -->
							<option value="三重">三重県</option>
							<option value="滋賀">滋賀県</option>
							<option value="京都">京都府</option>
							<option value="大阪">大阪府</option>
							<option value="兵庫">兵庫県</option>
							<option value="奈良">奈良県</option>
							<option value="和歌山">和歌山県</option>
							<!-- 中国 -->
							<option value="島根">島根県</option>
							<option value="鳥取">鳥取県</option>
							<option value="岡山">岡山県</option>
							<option value="広島">広島県</option>
							<option value="山口">山口県</option>
							<!-- 四国 -->
							<option value="徳島">徳島県</option>
							<option value="香川">香川県</option>
							<option value="愛媛">愛媛県</option>
							<option value="高知">高知県</option>
							<!-- 九州 -->
							<option value="福岡">福岡県</option>
							<option value="佐賀">佐賀県</option>
							<option value="長崎">長崎県</option>
							<option value="熊本">熊本県</option>
							<option value="大分">大分県</option>
							<option value="宮崎">宮崎県</option>
							<option value="鹿児島">鹿児島県</option>
							<option value="沖縄">沖縄県</option>
						</select>
						<!-- 市区町村セレクト -->
						<select id="citySelect" name="city_input" class="Form-Item-Input">
							<option value="">-----</option>
						</select>
					</div>
					
						<label for="region">居住地域</label> <select name="region_input"
				id="region_input">
				<%
				for (Region region : regions) {
				%>
				<option value="<%=region.getRegion_id()%>"><%=region.getRegion_name()%></option>
				<%
				}
				%>
					<div class="Form-Item">
						<p class="Form-Item-Label">メールアドレス</p>
						<input type="email" name="mail_input" class="Form-Item-Input">
					</div>
					<input type="submit" name="insert_btn" class="insert_btn"
						value="登録">
					<p id="error_msg" class="font-red"></p>
				</form>
			</div>
	</div>



</body>
</html>