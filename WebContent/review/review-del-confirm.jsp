<%--ユーザー用：レビュー削除の意思確認のページ--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<form action = "ReviewRemove.action?reviewCode=${reviewCode }" method="post">
<div style="text-align:center">

<h2>レビュー削除確認</h2>
<p>&emsp;

${account.nickName}さん
<br>
&emsp;
本当にレビューを削除しますか？<br><br><br>
戻るときはブラウザの戻るボタンで！<br>
<br>
&emsp;
<input type="submit" value="削除する"></p>

</div>
</form>

<%@include file="../footer.html" %>