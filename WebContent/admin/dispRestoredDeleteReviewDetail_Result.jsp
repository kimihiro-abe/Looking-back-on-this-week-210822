<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数drdを作って、その中にmediaDetailを格納している -->
<c:forEach var="drd"  items="${delresReviewDetail}" >
<c:choose>
	<c:when test="${drd.reviewGhost==0}">
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

メディアコード：${drd.mediaCode}<br>
タイトル：<br>
${drd.mediaTitle}<br>
<br>
あらすじ：<br>
${drd.mediaInfo}<br>
<br>
<%-- 監督：${drd.director}<br>
主演：${drd.leadingActor}<br>
音楽：${drd.music}<br>
発売日：${drd.releaseDate}<br>
制作年：${drd.productionYear}<br> --%>

<br>
accountCode：${drd.accountCode}<br>
nickName：${drd.nickName}<br>
<br>
reviewCode：${drd.reviewCode}：<br>
reviewContent：<br>
${drd.reviewContent}<br>
<br>
reviewDate：${drd.reviewDate}<br>
netabare：${drd.netabare}<br>
Ghost化(1がGhost状態)：${drd.reviewGhost}　<font color="red"><--------- Check!!!!!!</font><br>
<br>
<hr>

<p></p>
<br>
<a href="searchReviewAdmin.jsp">引き続きレビュー管理を行う</a>　　<--- Click!!<br>
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