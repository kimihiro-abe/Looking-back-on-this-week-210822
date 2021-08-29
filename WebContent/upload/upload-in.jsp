<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p>mediaCodeを<font color="red">半角の数字</font>で入力してください。<br>
既に画像が登録されている場合、下記に画像が表示されます。</p>
<form action="Search.action" method="post">
<input type="text" name="mediaCode">
<input type="submit" value="検索">
</form>
<hr>

<!-- <table style="border-collapse:separate;border-spacing:10px;" > -->
<table style="border-collapse:separate;border-spacing:3px;" >

<tr>
	<td>DB上のid</td>
	<td>mediaCode</td>
	<td>画像ファイル</td>
	<td>ファイル名</td>
	<td>ダウンロード</a></td>
</tr>

<h2 style="text-align:center">※画像挿入対象の映像情報詳細</h2>

<c:forEach var="mda" items="${mediaDetailAdmin}">
<font color="orange">mediaCode：</font>${mediaCode}<br>
<font color="orange">mediaCode：</font>${mda.mediaCode}<br>
<font color="orange">Title：</font>${mda.mediaTitle}<br>
<font color="orange">mediaInfo：</font>${mda.mediaInfo}<br>
<font color="orange">登録済みか判定(登録数)：</font>${checkIfPosted}<br>
<br>
	<c:forEach var="upload" items="${imageList}">
		<!-- ↓ファイルネームは後で使いたいので変数にしておく -->
		<c:set var="filename" value="${upload.filename}" />
		<tr>
		<td>${upload.id}</td>
		<td>${upload.mediaCode}</td>
		<td><img src="images/${upload.filename}" height="96"></td>
		<td>${upload.filename}</td>
		<td><a href="download?filename=${upload.filename}">ダウンロード</a></td>
		<!-- ダウンロード時に「FileNotFoundException」が出ることある -->
		</tr>
	</c:forEach>
		</table>

		<hr>
			<c:choose>
			<%-- <c:forEach var="mda" items="${mediaDetailAdmin}"> --%>
				<c:when test="${checkIfPosted == 0}">
					<font color="red" size="5">※挿入する画像の命名規則について</font><br>
					テキトーなファイル名はトラブルの元になりますので、<br>
					<font color="red">『映像情報の展開日(YYYYMMDD書式)_mediaCode』</font>となるように命名してください。<br>
					下記に例を記載しておきます。<br>
					<br>
					　　　　例：20201030_1.jpg　(YYYYMMDD_mediaCode.jpg)　<--半角英数推奨<br>
					<br>
					<p>アップロードする画像ファイルを<font color="red">確認のうえ選択</font>し、<font color="red">[アップロード]ボタン</font>を押してください。</p>
					<form action="Upload.action" method="post" enctype="multipart/form-data">
					<input type="hidden" name="mediaCodeUpload" value="${mda.mediaCode}">
					<input type="hidden" name="mediaTitleUpload" value="${mda.mediaTitle}">
					<input type="file" name="uploadfile"><br><br>
					<input type="submit" value="アップロード"><br>
					</form>
				</c:when>

				<c:otherwise>
					<font color="red" size="5">※挿入する画像の命名規則について</font><br>
					テキトーなファイル名はトラブルの元になりますので、<br>
					<font color="red">『映像情報の展開日(YYYYMMDD書式)_mediaCode』</font>となるように命名してください。<br>
					<br>
					　　　　例：20201030_1.jpg　(YYYYMMDD_mediaCode.jpg)　<--半角英数推奨<br>
					<br>
					<p>更新する画像ファイルを<font color="red">確認のうえ選択</font>し、<font color="red">[画像更新]ボタン<</font>を押してください。</p>
					<form action="Update.action" method="post" enctype="multipart/form-data">
					<input type="hidden" name="mediaCodeBC" value="${mda.mediaCode}">
					<input type="hidden" name="filenameBC" value="${filename}">
					<input type="file" name="uploadfile"><br><br>
					<input type="submit" value="画像更新">　まだ、更新はつくっていない
					</form>
				</c:otherwise>
			<%-- </c:forEach> --%>

			</c:choose>
</c:forEach>

<%@include file="../footer.html" %>
