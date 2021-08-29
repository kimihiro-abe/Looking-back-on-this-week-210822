<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%-- 個別の作品詳細のページとしてつくる --%>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>


<style>
    .fade{
        animation: fadeIn 1.1s ease 0.0s 1 normal;
    }
    @keyframes fadeIn { /*animation-nameで設定した値を書く*/
        0% {opacity: 0} /*アニメーション開始時は不透明度0%*/
        100% {opacity: 1} /*アニメーション終了時は不透明度100%*/
    }
</style>
<div class="fade">


<form action="../review/ReviewInsert.action" method="post">

<!-- ↓変数mdを作って、その中にmediaDetailを格納している -->
<c:forEach var="md"  items="${mediaDetail}" >
<input type="hidden" name="mediaCode" value="${md.mediaCode}">
<div style="text-align:center"><h2>※映像情報詳細</h2></div>
	<table>
	<tr>
		<th><img alt="${imagefilename}" src="${compImagePath}" width ="256px" height="324px"><br>
		<font color = "red">${messageNPE}</font></th>
		<td>
		<br>
		<font size="3">タイトル：</font><br>
		<font size="5">
		${md.mediaTitle}</font><br>
		<br>
		あらすじ：<br>
		${md.mediaInfo}<br>
		<br>
		監督：${md.director}<br>
		主演：${md.leadingActor}<br>
		音楽：${md.music}<br>
		制作年：${md.productionYear}<br>
		<br>
		ジャンル：<br>
		${md.genre1}, ${md.genre2}, ${md.genre3}, ${md.genre4}, ${md.genre5}<br>
		<br>
		メディアタイプ：${md.mediaType}<br>
		発売日：${md.releaseDate}<br>
		</td>
	</tr>
	</table>


<br>

<hr>
<div style="text-align:center"><h3>[ユーザー・レビュー]</h3><font color="red" size="5">${message}</font></div>
<c:forEach var="ur"  items="${userReviewAll}" >
<c:choose>
	<c:when test="${account.accountCode == ur.accountCode && ur.ghost==0}">
<!-- 	<input type="hidden" name="" autofocus/> -->
	投稿者：&#127775;<font color="deepskyblue" size="4" autofocus>${ur.nickName}</font>　　
<%-- 	<input type="text" name="reviewCode" value="${ur.reviewCode}"> --%>
	<a href="../review/ReviewRemove.action?${ur.reviewCode}+${md.mediaCode}">削除</a><font color="red">　※確認画面なしに削除されます</font><br>
	投稿日時：${ur.reviewDate}<br>
	${ur.reviewContent}<br>
	<br>
	</c:when>
	<c:when test="${ur.ghost==1}">
	投稿者：<font color="grey">退会済みユーザー&#x1f47b;</font><br>
	投稿日時：${ur.reviewDate}<br>
	${ur.reviewContent}<br>
	<br>
	</c:when>
	<c:otherwise>
	投稿者：${ur.nickName}<br>
	投稿日時：${ur.reviewDate}<br>
	${ur.reviewContent}<br>
	<br>
	</c:otherwise>
</c:choose>
</c:forEach>

<%-- <% int cif = (int)request.getAttribute("CheckIfPosted");%>
<c:set var="CheckIfPosted" value="<%=cif%>" /> --%>

<c:choose>
	<c:when test="${account.admin>=1 && CheckIfPosted==0}">
		<p>新たにレビューを投稿GoGo!（40～400字以内）</p>
		<textarea type="text" cols="50" rows="10" name="reviewContent" minlength="40" maxlenght="400" placeholder="レビュー内容を記載してください。" required></textarea>
		<p><input type="submit" value="投稿" ></p>
	</c:when>
	<c:when test="${account.admin>=1 && CheckIfPosted>=1}">
		<p><font color="red">この作品へのレビューは既に投稿済みです。</font></p>
		<p><font color="red">※上記表記があるのに御自身のレビューが表示されない場合、<br>
		運営側で悪質レビューと判断し処理させていただいたとご理解ください。</font></p>
	</c:when>
	<c:otherwise>
	<div style="text-align:center"><p>レビュー投稿ご希望の方は会員登録またはログインしてください。</div></p>
	<br>
	</c:otherwise>
</c:choose>
</c:forEach>
</form>
</div>

<%@include file="../footer.html" %>