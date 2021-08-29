<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数mdaを作って、その中にmediaDetailを格納している -->
<c:forEach var="mda"  items="${mediaDetailAdmin}" >
<h2>※映像情報詳細</h2>
<hr>


メディアコード：${mda.mediaCode}<br>
情報公開日時：${mda.deploymentDate}<br>
Ghost化(1がGhost状態)：${mda.ghost}<br>
<br>
タイトル：<br>
${mda.mediaTitle}<br>
<br>
あらすじ：<br>
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
<hr>

<p></p>
上記詳細情報の編集・削除を行う場合には、以下の『編集する』『削除する』をクリックしてください。<br>
編集・削除を行わない場合はブラウザの戻るボタン等で戻ってください。<br>
<br>
	<a href="CheckEditMediaDetailAdmin.action?${mda.mediaCode}">編集する</a>
	：クリック後に即編集画面に遷移します。<br>
	<br>

<c:choose>
	<c:when test="${mda.ghost==0}">
	<a href="MediaDetailDelete.action?${mda.mediaCode}">削除する</a>
	：クリック後に即削除が実行され、ユーザーの検索結果に表示されなくなります(※)。<br>
	　　　　　また、『削除＝Ghost化』なので復元は可能です。<br>
	　　　　　（※情報公開日が現在含め過去に設定されたものに限る。）<br>
	</c:when>

	<c:otherwise>
	<a href="MediaDetailRestore.action?${mda.mediaCode}">復元する</a>
	：クリック後にGhost化は解除され、ユーザーの検索結果に表示されるようになります(※)。<br>
	　　　　　（※情報公開日が現在含め過去に設定されたものに限る。）<br>
	</c:otherwise>
</c:choose>

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