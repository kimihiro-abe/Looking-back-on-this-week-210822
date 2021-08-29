<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="MediaInsert.action" method="post">
<h2>※メディア情報の新規登録</h2>
*印がついているものは入力必須項目です。

<p>タイトル*</p>
<input type="text" name="mediaTitle" size="100" required>
<br><br>

<p>作品情報（あらすじ等を40文字以上～400文字以内で記載）*</p>
<textarea name="mediaInfo" rows="10" cols="100" rows="1" minlength="40" maxlength="400" required></textarea>
<br><br>

<p>メディアの種類（DVD/Blu-ray/VHS）*</p>
<select name="mediaType" required>
	<option value="DVD">DVD</option>
	<option value="Blu-ray">Blu-ray</option>
	<option value="VHS">VHS</option>
	<!-- <input type="text" name="mediaType" size="20" required> -->
</select>
<br><br>

<p>発売日（YYYY-MM-DD）*
<br>例：1999-04-23　　全て半角で入力</p>
<input type="text" name="releaseDate" size="20" required>
<br><br>

<p>監督*</p>
<input type="text" name="director" size="50" required>
<br><br>

<p>主演*</p>
<input type="text" name="leadingActor" size="50" required>
<br><br>

<p>音楽*</p>
<input type="text" name="music" size="50" required>
<br><br>

<p>制作年（YYYY）*</p>
<select name="productionYear" required>
	<c:forEach var="i" begin="1945" end="2025">
	<option value="${i}">${i}</option>
</c:forEach></select>
<!-- <input type="text" name="productionYear" size="20"  required> -->
<br><br>

<p>情報公開日時（YYYY-MM-DD HH:MM:SS）*<br>
例：1999-04-23 18:00:00（全て半角で入力。日と時間の間は『半角スペース』）<br>

DB登録した以降の日時で情報公開をしたい場合に使う項目</p>
<input type="text" name="deploymentDate" size="20" required>
<br><br>

<p>mediaGenre1*</p>
<%@include file="includeParts/mediaInsert_genre1.jsp" %>
<!-- <input type="text" name="genre1" size="50" required> -->
<br>

<p>mediaGenre2*</p>
<%@include file="includeParts/mediaInsert_genre2.jsp" %>
<br>

<p>mediaGenre3*</p>
<%@include file="includeParts/mediaInsert_genre3.jsp" %>
<br>

<p>mediaGenre4　上記１～３のジャンルセレクターにない特記事項がある場合は直接記載</p>
<input type="text" name="genre4" size="50">
<br>

<p>mediaGenre5　上記１～３のジャンルセレクターにない特記事項がある場合は直接記載</p>
<input type="text" name="genre5" size="50">
<br>

<p><input type="submit" value="情報登録"></p>
</form>

<%@include file="../footer.html" %>