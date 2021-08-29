<%--ユーザー用：退会処理の意思確認のページ--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align:center">

<%--<font size="5">GoGoSearch</font>--%>
<form action = "/GoGoSeach/account/AccountDel.action?accountCode=${account.accountCode }" method="post"><%--退会者のaccountCodeを次の処理へ送る--%>
<h2>GoGo Search退会の確認</h2>
<p>&emsp;
<%-- ${account.accountCode }--%>
${account.nickName}さん
<br>
&emsp;
本当に退会しますか？
&emsp;<br>
<br>
<input type="submit" value="退会する"></p>

</form>

</div>
<%@include file="../footer.html" %>