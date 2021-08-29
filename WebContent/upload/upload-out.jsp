<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3><font color="red">${message}</font></h3><br>
<hr>

<h2>※映像情報詳細(画像つき)</h2>

<c:forEach var="mda"  items="${mediaDetailAdmin}" >
<c:choose>
	<c:when test="${mode==1}">
	<table>
	<tr>
		<th><img alt="${imagefilename}" src="${compImagePath}" width ="256px" height="324px"></th>
		<td>
		<br>
		<font size="5">タイトル：</font><br>
		${mda.mediaTitle}<br>
		<br>
		<font size="5">あらすじ：</font><br>
		${mda.mediaInfo}<br>
		<br>
		監督：${mda.director}<br>
		主演：${mda.leadingActor}<br>
		音楽：${mda.music}<br>
		制作年：${mda.productionYear}<br>
		<br>
		ジャンル：${mda.genre1}, ${mda.genre2}, ${mda.genre3}, ${mda.genre4}, ${mda.genre5}<br>
		<br>
		メディアタイプ：${mda.mediaType}<br>
		発売日：${mda.releaseDate}<br>
		<br>
		<br>
		</td>
	</tr>
	</table>
		メディアコード：${mda.mediaCode}<br>
		情報公開日時：${mda.deploymentDate}<br>
		Ghost化(1がGhost状態)：${mda.ghost}<br>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
</c:forEach>
<hr>

<br>
<a href="Search.action">画像アップロード画面に戻る</a>
<br>
<a href="../admin/mediaInsert.jsp">メディア情報の新規登録</a>
<br>
<a href="../admin/searchMediaAdmin.jsp">メディア情報の編集・削除・復元</a>
<br>
<a href="../mypage/mypage.jsp">管理者用マイページ</a>

<%@include file="../footer.html" %>
