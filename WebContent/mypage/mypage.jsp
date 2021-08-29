<!-- ユーザー用マイページ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<!-- メニューは管理者とユーザーは別にしてみたよ -->
<%@include file="/toppage/menu.jsp" %>


<c:choose>
	<c:when test="${account.admin>=2}">
		<center><h2>管理者用マイページ</h2></center>
		${message}<br>

		<table>
			<tr><td><a href="../admin/mediaInsert.jsp">メディア情報の新規登録</a></td></tr>
			<tr><td><a href="../admin/searchMediaAdmin.jsp">メディア情報の編集・削除・復元</a></td></tr>
			<tr><td><a href="../upload/Search.action">メディア情報への画像登録系</a></td></tr>
			<tr><td><a href="../admin/searchReviewAdmin.jsp">レビューの編集・削除・復元</a></td></tr>
			<tr><td><a href="../admin/searchAccountAdmin.jsp">全アカウントを対象とした管理（削除・復元・権限の付与剥奪）</a></td></tr>
			<tr><td><a href="../account/account-update.jsp">アカウント情報の変更</a></td></tr>
			<tr><td><a href="../admin/AccountDeleteCheckAdmin.action">退会する</a></td></tr>
		</table>
		<br>
	</c:when>
	<c:when test="${account.admin==1}">
		<center><h2>ユーザーマイページ</h2></center>
		<table>
			<tr><td><a href="../review/SearchReviewUser.action">自分のレビュー投稿一覧・削除</a></td></tr>
			<tr><td><a href="../account/account-update.jsp">アカウント情報の変更</a></td></tr>
			<tr><td><a href="../account/account-del-confirm.jsp">退会する</a></td></tr>
		</table>
		<br>
	</c:when>
	<c:otherwise>
		<meta http-equiv="refresh" content="0;URL=../index.jsp">
	</c:otherwise>
</c:choose>

<%@include file="../footer.html" %>

<!-- accountDeleteCheckAdmin.action
account/account-del-confirm.jsp -->