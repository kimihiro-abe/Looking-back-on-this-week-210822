<!-- 検索結果一覧表示　ユーザー画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>

<!-- <form action="CheckMedia.action" method="post"> -->

<!-- ↓変数mlを作って、その中にmediaListを格納している -->
<!--  ↓のメディアコードをどうにかして使いたい　-->
<br>
<center><h2>検索結果一覧</h2></center>
<br>

<c:forEach var="mla"  items="${mediaListAdmin}" >
<a href="CheckMediaAdmin.action?${mla.mediaCode}">${mla.mediaTitle}</a><br>
</c:forEach>
<!-- </form> -->



<!-- ★★★ここまでテーブル★★★ -->

<br>
<%@include file="../footer.html" %>