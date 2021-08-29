<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数rmdを作って、その中にmediaDetailを格納している -->
<c:forEach var="dad" items="${delresAccountDetail}" >
<h2>※アカウント管理　削除・復元の確認完了画面</h2>

	<c:choose>
		<c:when test="${dad.ghost==0}">
		※指定されたアカウントを復元しました。<br>
		Ghost化の値を「0」にしました。loginIDとnickNameのゴースト情報を削除しました。<br>
		<br>
		</c:when>

		<c:otherwise>
		※指定されたアカウントを削除（Ghost化）しました。<br>
		Ghost化の値を「1」にしました。loginIDとnickNameにゴースト情報を追記しました。<br>
		<br>
		</c:otherwise>
	</c:choose>

<h2>※映像情報詳細</h2>


accountCode：${dad.accountCode}<br>
<br>
loginID：${dad.loginID}　<font color="red"><--------- Check!!!!!!</font><br>
nickName：${dad.nickName}　<font color="red"><--------- Check!!!!!!</font><br>
Ghost化(1がGhost状態)：${dad.ghost}　<font color="red"><--------- Check!!!!!!</font><br>
<br>
registerDate：${dad.registerDate}<br>
admin：${dad.admin}<br>
<br>
<hr>

<p></p>
<br>
<a href="searchAccountAdmin.jsp">ユーザー・管理者の削除・復元・権限を変更する</a>　　<--- Click!!<br>
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