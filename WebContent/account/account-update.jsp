<!-- Account情報変更画面 -->
<!-- 画面遷移的にはaccount-update-confirm.jspへ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>

<form action="AccountUpdateChk.action?accountCode=${account.accountCode}" method="post">
<div style="text-align:center">

<h3><font color="red">${message}</font></h3>

<h2>ユーザー情報の変更</h2>
<%--&emsp;accountCode:${account.accountCode }<br> accountCode確認用。本番は削除--%>
<h4>ログインID  (英数字 6～15文字。変更しない場合はそのままで可)</h4>
<input type="text" style="height:30px;" name="loginID" value=${account.loginID } minlength="6" maxlength="15" required>
<h4>ニックネーム（半角英数かなカナ漢字記号 4～16文字。変更しない場合はそのままで可）</h4>
<input type="text" style="height:30px;" size="50" name="nickName" value=${account.nickName } minlength="4" maxlength="16" required>
<h4>パスワード (英数字 4～10文字。変更しない場合はそのままで可)</h4>
<input type="text" style="height:30px;" name="password" value=${account.password } minlength="4" maxlength="10" required>
	<p><input type="submit" value="ユーザー情報を変更する" name=""></p>

</div>
</form>

<%@include file="../footer.html" %>




