<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- 個別の作品詳細のページとしてつくる --%>

<!-- ↓変数aacを作って、その中にmediaDetailを格納している -->
<div style="text-align:center">
<c:forEach var="aac"  items="${AccountAfterChange}" >

<h2>※アカウントの管理権限変更　完了画面</h2>
<br>

<br>

<hr>
<br>
accountCode： ${aac.accountCode}<br>
<br>
loginID： ${aac.loginID}<br>
nickName： ${aac.nickName}<br>
<br>
アカウントの権限値：<font color="red" size="6">${aac.admin}</font><br>
ghost：${aac.ghost}（１がGhost状態）<br>
<br>
<font color="red" size="6">${message}</font><br>
<br>
<hr>

<p></p>
<br>
<a href="searchAccountAdmin.jsp">引き続きアカウント管理を行う</a>　　<--- Click!!<br>
<br>
<a href="../mypage/mypage.jsp">管理者用マイページのトップへ戻る</a>　　<--- Click!!<br>
<br>

</c:forEach>
<br>

</div>
<%@include file="../footer.html" %>

