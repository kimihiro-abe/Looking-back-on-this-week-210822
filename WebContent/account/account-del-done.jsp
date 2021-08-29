<%--ゲスト用：ユーザー退会処理完了連絡ページ--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>

<form action="../index.jsp" method="post">
<meta http-equiv="refresh" content="5;URL=../index.jsp">


<div style="text-align:center">

<h4>退会処理が完了しました。
<br>
ご利用いただきありがとうございました。</h4>


<p><input type="submit" value="トップ画面に戻る"></p>
<p>(５秒後に自動で遷移もします！）</p>

</div>
<%@include file="../footer.html" %>