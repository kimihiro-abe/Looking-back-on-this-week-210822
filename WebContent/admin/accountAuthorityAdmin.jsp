<!-- ユーザー一覧画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>

<center><h2>※ユーザー・管理者の検索結果</h2></center>
<center><font color="red">変更・削除の必要がない場合、変更・削除ボタンをクリックせずにこのページから離脱して下さい。</font></center>
<br>
１、権限変更・アカウント削除は一度に一人しか出来ません。<br>
２、該当ユーザーの『削除』をクリックすると、直ちに削除されます（復帰も可能です）<br>
３、管理者権限の変更は『付与』『剥奪』を選択後に『変更』ボタンを押して下さい。<br>
４、権限は、１＝ユーザー、２＝管理者、３＝スーパー管理者となっています。
<hr>
<!-- <form action = "ユーザーのurl.java" method = "post"> -->
<br>
<table border="5" bordercolor="black">
	<tr>
        <td>　　ニックネーム　　</td>
        <td>　ユーザーID　</td>
        <td>　accountCode　</td>
        <td>権限</td>
		<td>管理者権限管理</td>
		<td>変更</td>
    </tr>
<c:forEach var="cac" items="${currentAc}" >
<form action="AccountAuthorityChange.action" method="post" >
		<input type="hidden" name="targetAccountCode" value="${cac.accountCode}">
		<input type="hidden" name="targetAdmin" value="${cac.admin}">

<c:choose>
	<c:when test="${account.admin==2}">
    <tr>
        <td>　${cac.nickName}　</td>
        <td>　${cac.loginID}　</td>
        <td align="center">${cac.accountCode}</td>
        <td align="center">${cac.admin}</td>
        <td>
        <c:choose>

        	<c:when test="${cac.admin==1}">
		        <input type="radio" name="mode" value="assignment">付与
				<!-- <input type="radio" name="mode" value="deprivation">剥奪 -->
			</c:when>
			<c:otherwise>
				<input type="radio" name="mode" value="deprivation">剥奪
			</c:otherwise>
		</c:choose>
		</td>
        <td><input type="submit" value="変更"></td>
	</tr>
	</c:when>
	<c:when test="${account.admin==3}">
	<tr>
	        <td>　${cac.nickName}　</td>
        <td>　${cac.loginID}　</td>
        <td align="center">${cac.accountCode}</td>
        <td align="center">${cac.admin}</td>
        <td>
        <c:choose>
        	<c:when test="${cac.admin==1}">
		        <input type="radio" name="mode" value="assignment">付与
				<!-- <input type="radio" name="mode" value="deprivation">剥奪 -->
			</c:when>
		  	<c:when test="${cac.admin==2}">
		        <input type="radio" name="mode" value="assignment">付与
				<input type="radio" name="mode" value="deprivation">剥奪
			</c:when>
			<c:otherwise>
				<input type="radio" name="mode" value="deprivation">剥奪
			</c:otherwise>
		</c:choose>
		</td>
        <td><input type="submit" value="変更"></td>
	</tr>
	</c:when>
</c:choose>

</form>
</c:forEach>
</table>
<!-- </form> -->
<br>
<div style="text-align:center">
<font color="red" size=5">${message}</font><br>
<br>
</div>
<%@include file="../footer.html" %>


