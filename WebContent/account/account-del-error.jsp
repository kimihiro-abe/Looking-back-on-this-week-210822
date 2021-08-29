<%--ユーザー用：退会処理中のエラーページ--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html"%>
<%@include file="/toppage/menu.jsp" %>

<form action="../mypage/mypage.jsp" method="post">
<meta http-equiv="refresh" content="5;URL=../mypage/mypage.jsp">

<div style="text-align:center">

<p>退会処理中にエラーが発生しました。</p>


<p><input type="submit" value="マイページに戻る"></p>
<p>(５秒後に自動で遷移もします！）</p>


</div>
<%@include file="../footer.html"%>
