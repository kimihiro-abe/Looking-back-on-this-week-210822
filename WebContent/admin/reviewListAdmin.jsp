<!-- 検索結果一覧表示　ユーザー画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>

<!-- <form action="CheckMedia.action" method="post"> -->

<!-- ↓変数mlを作って、その中にmediaListを格納している -->
<!--  ↓のメディアコードをどうにかして使いたい　-->
<center>削除ボタンを押すと『問答無用で削除（Ghost化）されます』ので、</center>
<center>レビューの削除に当たっては細心の注意を払い対処をお願いします。</center>
<center>なお、編集ボタンは自身が投稿したレビューにしか表示されません。</center>
<br>

<!-- <form action="ReviewDetailedAdmin.action" method="post"> -->
<table border="5" bordercolor="black">
       <tr>
			<td align="center"  width="10%">投稿者nickName</td>
			<td align="center"  width="20%">映像情報名</td>
			<td align="center"  width="65%">レビュー内容</td>
			<td width="1%"></td>
			<td width="1%"></td>
       </tr>
<c:forEach var="rla"  items="${reviewListAdmin}" >
	<c:choose>
		<c:when test="${rla.reviewGhost==0}">
		        <tr>
		       		<td>${rla.nickName}</td>
		            <td>${rla.mediaTitle}</td>
		            <td>${rla.reviewContent}</td>
		            <td>${rla.reviewCode}
					<a href="ReviewDetailAdmin.action?${rla.reviewCode}+delete">削除</a></td>
					<!-- <button type='submit' name='reviewCode' value=''>削除</button></td> -->
						<c:choose>
							<c:when test="${rla.accountCode == account.accountCode}">
								<td>${rla.reviewCode}
								<a href="ReviewDetailAdmin.action?${rla.reviewCode}+edit">編集</a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
		        </tr>
		</c:when>

		<c:otherwise><!--  Ghost化したレビューを表示させる  -->
		        <tr>
		     		<td>${rla.nickName}</td>
		            <td>${rla.mediaTitle}</td>
		            <td>${rla.reviewContent}</td>
		            <td>${rla.reviewCode}
					<a href="ReviewDetailAdmin.action?${rla.reviewCode}+restore">復元</a></td>
					<!-- <button type='submit' name='reviewCode value=''>復元</button></td> -->
						<c:choose>
							<c:when test="${rla.accountCode == account.accountCode}">
								<td>${rla.reviewCode}
								<a href="ReviewDetailAdmin.action?${rla.reviewCode}+edit">編集</a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
		        </tr>
		</c:otherwise>
	</c:choose>

</c:forEach>
</table>
<!-- </form> -->

<!-- ★★★ここまでテーブル★★★ -->
<!-- </form> -->
<br>
<%@include file="../footer.html" %>