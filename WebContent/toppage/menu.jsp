<!-- admin用menu -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@include file="../header.html" %>

<header>

<!-- ロゴ画像からトップページ遷移 -->
<c:choose>
	<c:when test="${account.admin>=2}">
		<a href="/GoGoSeach/index.jsp"><img src="/GoGoSeach/image/logo (2).png" alt="トップページへ" width="50%" height="30%"></a>
		ニックネーム : <font color="deepskyblue">${account.nickName}</font>さん。
		<div align="right"><form action="/GoGoSeach/searchMedia/SearchMedia.action" method="post">
		<input type="text" name="keyword" placeholder="キーワードを入れ検索GOGO！" size="30">
		<input type="submit" value="GoGo">
		<a href="/GoGoSeach/mypage/mypage.jsp">管理者マイページ</a>
		<a href="/GoGoSeach/loginlogout/logout-in.jsp">ログアウト</a>
		</form></div>
	</c:when>

	<c:when test="${account.admin==1}">
		<a href="/GoGoSeach/index.jsp"><img src="/GoGoSeach/image/logo (2).png" alt="トップページへ" width="50%" height="30%"></a>
		ニックネーム : <font color="deepskyblue">${account.nickName}</font>さん。
		<div align="right">

		<form action="/GoGoSeach/searchMedia/SearchMedia.action" method="post">
		<input type="text" name="keyword" placeholder="キーワードを入れ検索GOGO！" size="30">

		<input type="submit" value="GoGo">
		<a href="/GoGoSeach/mypage/mypage.jsp">ユーザーマイページ</a>
		<a href="/GoGoSeach/loginlogout/logout-in.jsp">ログアウト</a>
		</form></div>
	</c:when>

	<c:otherwise>
		<a href="/GoGoSeach/index.jsp"><img src="/GoGoSeach/image/logo (2).png" alt="トップページへ" width="50%" height="30%"></a>
		こんにちは、ゲストさん。
		<div align="right">
		<form action="/GoGoSeach/searchMedia/SearchMedia.action" method="post">
		<input type="text" name="keyword" placeholder="キーワードを入れ検索GOGO！" size="30">

		<input type="submit" value="GoGo">
		<a href="/GoGoSeach/account/account-add.jsp">新規登録はここから！</a>
		<a href="/GoGoSeach/loginlogout/login-in.jsp">ログイン</a>
	</form></div>
	</c:otherwise>

</c:choose>
<!-- <input type="text" name="keyword">
<input type="submit" value="検索"> -->


<hr>
</header>

<main role"main">