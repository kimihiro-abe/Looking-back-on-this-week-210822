<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数mdaを作って、その中にmediaDetailを格納している -->
<c:forEach var="mda"  items="${mediaDetailAdmin}" >
<p>※映像情報詳細新規追加分 or 映像情報詳細変更分の確認画面</p>
以下の情報を新規追加 or 変更いたしました。
<hr>

<p><h2>※映像情報詳細</h2></p>

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
<br>
<a href="../upload/Search.action?mediaCode=${mda.mediaCode}">続けて映像情報に付随する<font color="red">画像を登録</font></a>　　<--- <font color="red">情報詳細を新規追加した時は<font size="6">必ず</font>こちらを選択</font>してください。<br>
<br>
<a href="searchMediaAdmin.jsp">映像情報詳細を<font color="red">変更</font></a>　　<--- Click!!<br>
<br>
<a href="../mypage/mypage.jsp">管理者用マイページのトップへ戻る</a>　　<--- Click!!<br>
<br>

</c:forEach>
<br>

<%@include file="../footer.html" %>