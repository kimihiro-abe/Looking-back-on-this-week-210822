<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../header.html" %>
	<%@include file="/toppage/menu.jsp" %>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="5" bordercolor="black">
<div style="text-align:center">
<font color="red">※レビューは確認画面なしに削除されます。また一度削除したレビューは復元できません。</font></div>
<br>
<tr>
			<td align="center"  width="38%">映像情報名</td>
			<td align="center"  width="61%">レビュー内容</td>
			<td width="1%"></td>
       </tr>
<c:forEach var="rul" items="${reviewUserList}" >
    <tr>
       <td><a href="../searchMedia/CheckMedia.action?${rul.mediaCode}">${rul.mediaTitle}</a></td>
       <td>${rul.reviewContent}</td>
       <td><%-- ${rul.reviewCode} --%>
	<a href="ReviewRemove.action?${rul.reviewCode}+delete">削除</a></td>
    </tr>
</c:forEach>
</table>
<br>
<%@include file="../footer.html" %>

