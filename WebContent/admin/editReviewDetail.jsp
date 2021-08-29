<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数erdを作って、その中にmediaDetailを格納している -->
<form action="EditReviewDetailAdmin.action" method="post">
<c:forEach var="erd"  items="${editReviewDetail}" >

※レビュー更新画面<br>
更新できるのはレビュー文章のみです。<br>

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
<input type="hidden" name="reviewCode" value="${erd.reviewCode}">
reviewContent：　<font color="red"><--- 変更出来るのはこの項目のみ。30字以上、400字以内で修正してください。</font><br>
<textarea name="reviewContent" rows="10" cols="100" minlength="40" maxlength="400" required>${erd.reviewContent}</textarea>
<br>
<font color="red" size="5">${SQLeroor_message}</font><br>
<br>
reviewDate：${erd.reviewDate}<br>
netabare：${erd.netabare}<br>
Ghost化(1がGhost状態)：${erd.reviewGhost}<br>
<br>
<input type="submit" value="更新">　<--- 更新する時にはこのボタンを押して下さい。
<hr>

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