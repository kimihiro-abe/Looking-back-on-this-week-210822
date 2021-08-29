<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数rmdを作って、その中にmediaDetailを格納している -->
<c:forEach var="rmd"  items="${restoredMediaDetail}" >
<p>※映像情報詳細　削除・復元の確認完了画面</p>

<c:choose>
	<c:when test="${rmd.ghost==0}">
	※指定のレビューを復元しました。<br>
	Ghost化の値が「0」になっていることを確認してください。<br>
	<br>
	</c:when>

	<c:otherwise>
	※指定のレビューを削除（Ghost化）しました。<br>
	Ghost化の値が「1」になっていることを確認してください。<br>
	<br>
	</c:otherwise>
</c:choose>

<p>※映像情報詳細</p>


メディアコード：${rmd.mediaCode}<br>
情報公開日時：${rmd.deploymentDate}<br>
Ghost化(1がGhost状態)：${rmd.ghost}　<font color="red"><--------- Check!!!!!!</font><br>
<br>
タイトル：<br>
${rmd.mediaTitle}<br>
<br>
あらすじ：<br>
${rmd.mediaInfo}<br>
<br>
監督：${rmd.director}<br>
主演：${rmd.leadingActor}<br>
音楽：${rmd.music}<br>
制作年：${rmd.productionYear}<br>
<br>
ジャンル：${rmd.genre1}, ${rmd.genre2}, ${rmd.genre3}, ${rmd.genre4}, ${rmd.genre5}<br>
<br>
メディアタイプ：${rmd.mediaType}<br>
発売日：${rmd.releaseDate}<br>
<br>
<hr>

<p></p>
<br>
<a href="searchMediaAdmin.jsp">引き続き映像情報詳細を変更する</a>　　<--- Click!!<br>
<br>
<a href="../mypage/mypage.jsp">管理者用マイページのトップへ戻る</a>　　<--- Click!!<br>
<br>


</c:forEach>
<br>
<%-- [ユーザー・レビュー]<br>
<c:forEach var="ur"  items="${userReviewAll}" >

投稿者：${ur.nickName}<br>
投稿日時：${ur.reviewDate}<br>
レビュー：${ur.reviewContent}<br>
<br>
</c:forEach> --%>

<%@include file="../footer.html" %>