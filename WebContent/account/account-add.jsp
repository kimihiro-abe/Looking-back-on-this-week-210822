<%--ゲスト用：新規登録ページ--%>
<!-- 画面遷移的にはaccount-add-confirm.jspへ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>

<div style="text-align:center">

<h3><font color="red">${message}</font></h3>

<form action ="AccountChkid.action" method="post">
<h2>新規ユーザー登録</h2>
<h4>ログインID  (英数字 6～15文字)</h4>
<input type="text" style="height:30px;" name="loginID" minlength="6" maxlength="15" required>
<h4>パスワード (英数字 4～10文字)</h4>
<input type="password" style="height:30px;" name="password" minlength="4" maxlength="10" required>
<h4>ニックネーム（半角英数かなカナ漢字記号 4～16文字）</h4>
<input type="text" style="height:30px;" size="50" name="nickName" minlength="4" maxlength="16" required><br>
<p><input type="submit" value="ユーザー登録をする"></p>
</form>

</div>
<%@include file="../footer.html" %>