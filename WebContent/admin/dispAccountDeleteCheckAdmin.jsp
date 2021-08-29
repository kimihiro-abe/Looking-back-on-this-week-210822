<!-- ユーザー一覧画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 管理者（権限2or3）が自分一人の場合には、削除は出来ない仕様なのでDBに問い合わせて確認する -->

<form action="../mypage/mypage.jsp" method="post">
<div style="text-align:center">

<h2><font color="red">※退会処理不可能通知</font></h2>


<br>
<%-- ${account.accountCode }--%>
<font color="deepskyblue">${account.nickName}</font>さんが退会されると、<br>
&emsp;
<font color="red">管理者権限<font size="6">${account.admin}</font>の管理者数がゼロになってしまう</font>ので、<br>
退会処理を行うことが出来ません。<br>
上位の管理者権限を持つ方に指示を仰いでください。<br><br>


<p>
<input type="submit" value="管理者mypageへ戻る "></p><br>

</div>
</form>

<%@include file="../footer.html" %>


