<!-- ユーザーのトップページ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./header.html" %>
<%@include file="./toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>

<div style="text-align:center">
GoGoSearchにようこそ！＾＾<br>
<font size="3">登録映像情報</font><font size="5" color="deepskyblue">${mediaTotal}</font><font size="3">点！</font>　
<font size="3">登録レビュアー数</font><font size="5" color="deepskyblue">${totalReviewer}</font><font size="3">人！</font>　
<font size="3">投稿レビュー総数</font><font size="5" color="deepskyblue">${totalReviews}</font><font size="3">点！</font>　

</div>
<font color="red">${messageTM}${messageTRver}${messageTRvs}</font>

<!-- ここから↓石岡にて試し -->
<div class="menu-box">
  <input id="check-box" type="checkbox">
  <label class="menu-label-title" for="check-box">
    <span class="menu-title">カテゴリー索引(現状、作品がないカテゴリーある。)</span>
    <span></span>
    <span></span>
  </label><!-- .menu-label-title -->
	<c:forEach var="gl" items="${genreList}" >
	<c:choose>
		<c:when test="${gl.genre_code != 7}">
		<div class="menu-text">
		<a href="searchMedia/SearchCategory.action?category=${gl.genre_name}">${gl.genre_name}</a></div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	</c:forEach>

</div><!-- .menu-box -->

<!-- ここまで -->


<!-- トップにどーんと出したいやつ -->
<p>
	<div style="text-align:center"><h2> 最新登録情報！</h2></div>
	<center>
	<table>
		<c:forEach var="lml"  items="${latestMediaList}" >
		<tr class="sample">
			<th><a href="searchMedia/CheckMedia.action?${lml.mediaCode}">
			<img src="${pathContext}${lml.fileName}" alt="${lml.mediaTitle}" width ="340px" height="430px"></a></th>
			<td><h1>タイトル：</h1>
			<font size="5">${lml.mediaTitle}</font><br>
			<br>
			あらすじ：<br>
			${lml.mediaInfo}<br>
			</td>
		</tr>
		</c:forEach>
	</table>
	<font color="red">${messageSSEE1}${messageEE1}</font><br>
	</center>
</p>


<!-- ジャンル別とかで出したいやつ -->
<br>
<p>
	<div style="text-align:center"><h3>★★★ 洋画新作５選 ★★★</h3></div>
<center>
	<table>
		<tr class="sample">
		<c:forEach var="wml"  items="${WesternMovieList}" >
		<th>
		<a href="searchMedia/CheckMedia.action?${wml.mediaCode}">
		<img src="${pathContext}${wml.fileName}" alt="${wml.mediaTitle}" width ="136" height="173" align="left">
		</a></th>
		</c:forEach>
		</tr>
	</table>
	<font color="red">${messageSSEE2}${messageEE2}</font><br>
</center>
</p>
<br>
<p>
	<div style="text-align:center"><h3>★★★ 邦画新作５選 ★★★</h3></div>
<center>
	<table>
		<tr class="sample">
		<c:forEach var="jml"  items="${JpnMovieList}" >
		<th>
		<a href="searchMedia/CheckMedia.action?${jml.mediaCode}">
		<img src="${pathContext}${jml.fileName}" alt="${jml.mediaTitle}" width ="136" height="173" align="left">
		</a></th>
		</c:forEach>
		</tr>
	</table>
	<font color="red">${messageSSEE3}${messageEE3}</font><br>
</center>
</p>
<br>
<p>
	<div style="text-align:center"><h3>★★★ 過去一週間に投稿されたレビューからGoGo！ ★★★</h3></div>
<center>
リンク先映像情報のレビュー一覧、<font color="orange" size="4">その最上段で燦然とオレンジ色に輝いているのがその投稿レビュワー</font>だ！
<table>
	<tr class="sample">
	<c:forEach var="rdl"  items="${reviewDaysList}" >
	<th width="180">
	<a href="searchMedia/CheckMedia2.action?${rdl.mediaCode}+${rdl.accountCode}">${rdl.reviewContent}...</a></th>
	</c:forEach>
	</tr>
</table>
<font color="red">${messageSSEE4}${messageEE4}</font><br>
</center>
</p>



<%@include file="./footer.html" %>