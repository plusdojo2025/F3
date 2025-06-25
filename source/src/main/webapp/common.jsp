<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 共通ヘッダーここから -->
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<script src="<c:url value='/js/common.js' />"></script>

<div class="logo">
    <a href="<c:url value='/HomeServlet' />">
        <img src="<c:url value='/img/logo.png'/>" alt="ロゴ">
    </a>
</div>

<div class="header">
    <button class="hamburger" name="humberger_menu" aria-label="メニュー"
        aria-controls="nav-menu" aria-expanded="false">
        <img id="hamburger-icon" src="<c:url value='/img/hamburger_open.png'/>" alt="メニュー">
    </button>

    <nav id="nav-menu" class="nav" aria-hidden="true">
        <ul class="nav__list">
            <li class="nav__item"><a href="<c:url value='/HomeServlet' />" class="nav__link">ホーム</a></li>
            <li class="nav__item"><a href="<c:url value='/MypageServlet' />" class="nav__link">マイページ</a></li>
            <li class="nav__item"><a href="<c:url value='/CalendarServlet'/>" class="nav__link">カレンダー</a></li>
            <li class="nav__item"><a href="<c:url value='/StoreServlet'/>" class="nav__link">ストア</a></li>
            <li class="nav__item"><a href="<c:url value='/HelpServlet'/>" class="nav__link">ヘルプ</a></li>
            <li class="nav__item"><a href="<c:url value='/LogoutServlet'/>" class="nav__link" id="logout">ログアウト</a></li>
        </ul>
    </nav>
</div>
<!-- 共通ヘッダーここまで -->
