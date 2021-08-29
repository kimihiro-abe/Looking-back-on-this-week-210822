<%--ユーザー用：登録内容変更の意思確認ページ--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<form action = "AccountUpdate.action" method="post">
<div style="text-align:center">

<h2>ユーザー情報の変更内容確認</h2>
<p>
<%--&emsp;accountCode:${account.accountCode }<br>確認用。本番は削除--%>

<!-- AccountUpdateActionのgetParameterで値を取れるようにしてあげる必要ある -->
&emsp;ログインID：${conf.loginID }<input type="hidden" name="loginID" value="${conf.loginID }"><br>
&emsp;ニックネーム：${conf.nickName}<input type="hidden" name="nickName" value="${conf.nickName}"><br>
&emsp;パスワード：${conf.password }<input type="hidden" name="password" value="${conf.password }"><br>
<br>
この内容で変更してもよろしいでしょうか？
&emsp;
<br>
<br>
<input type="submit" value="変更する"></p>

</div>
</form>

<%@include file="../footer.html" %>
