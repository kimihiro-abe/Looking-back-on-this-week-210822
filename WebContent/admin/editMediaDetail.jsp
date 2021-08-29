<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数erdを作って、その中にmediaDetailを格納している -->
<form action="EditMediaDetailAdmin.action" method="post">
<c:forEach var="emd"  items="${editMediaDetail}" >

※メディア情報更新画面<br>
*印がついているものは入力必須項目です。<br>

<hr>
メディアコード：${emd.mediaCode}
<input type="hidden" name="mediaCode" value="${emd.mediaCode}">
<p>タイトル*</p>
<input type="text" name="mediaTitle" value="${emd.mediaTitle}" size="100" required>
<br><br>

<p>作品情報（あらすじ等、500字以内で記載。最低文字数30文字以上必須）*</p>
<textarea name="mediaInfo" rows="10" cols="100" rows="1" minlength="30" maxlength="400" required>${emd.mediaInfo}</textarea>
<br><br>

<p>メディアの種類（DVD/Blu-ray/VHS）*　　<font color="red">現在[ ${emd.mediaType} ]が設定されています。『手動で』再設定してください！</font></p>
<select name="mediaType" required>
	<option value="DVD">DVD</option>
	<option value="Blu-ray">Blu-ray</option>
	<option value="VHS">VHS</option>
	<!-- <input type="text" name="mediaType" size="20" required> -->
</select>
<br><br>

<p>発売日（YYYY-MM-DD）*
<br>例：1999-04-23　　全て半角で入力</p>
<input type="text" name="releaseDate" value="${emd.releaseDate}" size="20" required>
<br><br>

<p>監督*</p>
<input type="text" name="director" value="${emd.director}" size="50" required>
<br><br>

<p>主演*</p>
<input type="text" name="leadingActor" value="${emd.leadingActor}" size="50" required>
<br><br>

<p>音楽*</p>
<input type="text" name="music" value="${emd.music}" size="50" required>
<br><br>

<p>制作年（YYYY）*　　<font color="red">現在[ ${emd.productionYear} ]が設定されています。『手動で』再設定してください！</font></p>
<select name="productionYear" required>
	<c:forEach var="i" begin="1945" end="2025">
	<option value="${i}">${i}</option>
</c:forEach></select>
<!-- <input type="text" name="productionYear" size="20"  required> -->
<br><br>

<p>情報公開日時（YYYY-MM-DD HH:MM:SS）*<br>
例：1999-04-23 18:00:00（全て半角で入力。日と時間の間は『半角スペース』）<br>

DB登録した以降の日時で情報公開をしたい場合に使う項目</p>
<input type="text" name="deploymentDate" value="${emd.deploymentDate}" size="20" required>
<br><br>

<p>mediaGenre1*　　<font color="red">現在[ ${emd.genre1} ]が設定されています。『手動で』再設定してください！</font></p>
<%@include file="includeParts/mediaInsert_genre1.jsp" %>
<!-- <input type="text" name="genre1" size="50" required> -->
<br>

<p>mediaGenre2*　　<font color="red">現在[ ${emd.genre2} ]が設定されています。『手動で』再設定してください！</font></p>
<%@include file="includeParts/mediaInsert_genre2.jsp" %>
<br>

<p>mediaGenre3*　　<font color="red">現在[ ${emd.genre3} ]が設定されています。『手動で』再設定してください！</font></p>
<%@include file="includeParts/mediaInsert_genre3.jsp" %>
<br>

<p>mediaGenre4　上記１～３のジャンルセレクターにない特記事項がある場合は直接記載</p>
<input type="text" name="genre4" value="${emd.genre4}"  size="50">
<br>

<p>mediaGenre5　上記１～３のジャンルセレクターにない特記事項がある場合は直接記載</p>
<input type="text" name="genre5" value="${emd.genre5}"  size="50">
<br>

<p><input type="submit" value="情報登録"></p>
<hr>

</c:forEach>
<br>

</form>
<%@include file="../footer.html" %>