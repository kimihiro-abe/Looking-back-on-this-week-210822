<!-- 検索結果一覧表示　ユーザー画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
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


<hr>
※画像情報を登録しないと表示されません。
<center>
<table>
<c:forEach var="cl"  items="${categoryList}" >
	<th class="sample">
	<a href="CheckMedia.action?${cl.mediaCode}">
	<img src="${pathContext}${cl.fileName}" alt="${cl.mediaTitle}" width ="136" height="173" align="left"></a>
	</th>
</c:forEach>
</table>

</center>

<!-- ★★★ここまでテーブル★★★ -->

<br>
</div>

<%@include file="../footer.html" %>