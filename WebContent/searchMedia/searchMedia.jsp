<!-- このjspはデザイン的にうまく配置することができず、結局menu.jspなどに検索用テキストボックスを入れてしまってます -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<!-- 映像検索部分 -->

<p>検索キーワードを入れてください</p>

<form action="SearchMedia.action" method="post">
<input type="text" name="keyword">
<input type="submit" value="GoGo">キーワードを入れてください
</form>

<%@include file="../footer.html" %>