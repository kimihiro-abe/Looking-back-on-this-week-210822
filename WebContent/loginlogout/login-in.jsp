<%--ゲスト用：ログインページ--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>

<form action="Login.action" method="post">
<div style="text-align:center">

<h4>ログインID  (英数字 6～15文字)</h4>
<input type="text" style="height:30px;" name="loginID" minlength="6" maxlength="15" required>
<h4>パスワード (英数字 4～10文字)</h4>
<input type="password" style="height:30px;" name="password" minlength="4" maxlength="10" required>
<p><input type="submit" value="ログイン"  align="center"></p>
</div>
</form>

<%@include file="../footer.html" %>