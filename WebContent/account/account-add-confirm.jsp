<%--ユーザー用：新規登録の意思確認ページ--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align:center">

<form action = "AccountAdd.action" method="post">
<h2>ユーザー新規登録確認</h2>
<p>
&emsp;ログインID：${conf.loginID }<br>
&emsp;パスワード：${conf.password }<br>
&emsp;ニックネーム：${conf.nickName}<br>
<br>
この内容で登録してもよろしいでしょうか？
&emsp;
<input type="submit" value="登録する"></p>

</form>

</div>
<%@include file="../footer.html" %>