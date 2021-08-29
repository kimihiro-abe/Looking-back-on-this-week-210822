<!-- ユーザー一覧画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>

<center><h2>※ユーザー・管理者の検索結果</h2></center>
<center><font color="red">変更・削除の必要がない場合、変更・削除ボタンをクリックせずにこのページから離脱して下さい。</font></center>
<br>
１、権限変更・アカウント削除は『一度に一人』しか出来ません。<br>
２、該当ユーザーの『削除』をクリックすると、直ちに削除されますが復帰も可能です。<br>
３、管理者権限の変更は『付与』『剥奪』を選択後に『変更』ボタンを押して下さい。<br>
４、権限は、１＝ユーザー、２＝管理者、３＝スーパー管理者となっています。<br>
５、自身の権限以上のアカウントは検索結果に表示されません。
<hr>
<!-- <form action = "ユーザーのurl.java" method = "post"> -->


<table border="5" bordercolor="black">
	<tr>
        <td>　　ニックネーム　　</td>
        <td>　ユーザーID　</td>
        <td>　accountCode　</td>
        <td>権限</td>
        <td>削除</td>
		<td>権限変更</td>
    </tr>
<c:forEach var="ala" items="${accountListAdmin}" >
    <tr>
        <td>　${ala.nickName}　</td>
        <td>　${ala.loginID}　</td>
        <td align="center">${ala.accountCode}</td>
        <td align="center">${ala.admin}</td>
        <c:choose>
			<c:when test="${ala.ghost==0}">
	        	<td><a href="AccountUpdate.action?${ala.accountCode}+delete">削除</a></td>
        	</c:when>
        	<c:otherwise><!-- 削除（ゴースト化）したデータの復帰処理 -->
        		<td><a href="AccountUpdate.action?${ala.accountCode}+restore">復元</a></td>
        	</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ala.ghost==0}">
       			 <td align="center"><a href="AccountUpdate.action?${ala.accountCode}+change">変更</a></td>
       		</c:when>
       		<c:otherwise><!-- 削除（ゴースト化）したデータの復帰処理 -->
        		<td></td>
        	</c:otherwise>
       	</c:choose>

	</tr>
</c:forEach>
</table>
<!-- </form> -->


<%@include file="../footer.html" %>


