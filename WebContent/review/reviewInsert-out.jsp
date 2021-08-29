<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>

<form action="../searchMedia/CheckMedia.action?${mediaCode}" method="post">
<meta http-equiv="refresh" content="5;URL=../searchMedia/CheckMedia.action?${mediaCode}">

<div style="text-align:center">
<p>レビュー登録が完了しました。</p>

<p><input type="submit" value="レビュー投稿をさっそく確認GoGo!!"></p>
<p>(５秒後に自動で遷移もします！）</p>


</div>
</form>


<%@include file="../footer.html" %>