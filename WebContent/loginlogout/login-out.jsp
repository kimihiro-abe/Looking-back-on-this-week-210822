<%--ユーザー用：ログイン完了後ページ--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>

<form action="../index.jsp">
<div style="text-align:center">

<p>こんにちは、<font color="deepskyblue">${account.nickName}</font>さん。</p>
<br>
新作が入荷されたので早速チェックへGoGo！<br>

<meta http-equiv="refresh" content="3;URL=../index.jsp">
<p><input type="submit" value="Top画面へGoGo!!" ></p>
(３秒後に自動で遷移もします。）<br>



権限ランク：${account.admin}（本番は消す）</p>
</div>
</form>
<%@include file="../footer.html" %>