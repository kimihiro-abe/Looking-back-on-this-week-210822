<%--ユーザー情報の変更完了ページ--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html"%>
<%@include file="/toppage/menu.jsp" %>

<form action="../mypage/mypage.jsp" method="post">
<meta http-equiv="refresh" content="5;URL=../mypage/mypage.jsp">

<div style="text-align:center">

<h4>ユーザー情報の変更が完了いたしました。</h4>


<p><input type="submit" value="マイページに戻る"></p>
<p>(５秒後に自動で遷移もします！）</p>

</div>
</form>
<%@include file="../footer.html"%>
