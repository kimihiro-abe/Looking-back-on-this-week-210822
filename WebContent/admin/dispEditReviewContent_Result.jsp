<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数erdを作って、その中にmediaDetailを格納している -->
<form action="EditReviewDetailAdmin.action" method="post">
<c:forEach var="erd"  items="${editReviewDetail}" >

※レビュー更新完了画面<br>
レビュー内容の記載を下記のように変更いたしました。<br>

<hr>
<br>
メディアコード：${erd.mediaCode}<br>
タイトル：<br>
${erd.mediaTitle}<br>
<br>
あらすじ：<br>
${erd.mediaInfo}<br>
<br>
<%-- 監督：${erd.director}<br>
主演：${erd.leadingActor}<br>
音楽：${erd.music}<br>
発売日：${erd.releaseDate}<br>
制作年：${erd.productionYear}<br> --%>

<br>
accountCode：${erd.accountCode}<br>
nickName：${erd.nickName}<br>
<br>
reviewCode：${erd.reviewCode}：<br>
reviewContent：<font color="red"><------ レビュー内容を更新しました！</font><br>
${erd.reviewContent}
<br>
<br>
reviewDate：${erd.reviewDate}<br>
netabare：${erd.netabare}<br>
Ghost化(1がGhost状態)：${erd.reviewGhost}<br>
<br>
<hr>

<p></p>
<br>
<a href="searchReviewAdmin.jsp">引き続きレビュー管理をする</a>　　<--- Click!!<br>
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
</form>
<%@include file="../footer.html" %>