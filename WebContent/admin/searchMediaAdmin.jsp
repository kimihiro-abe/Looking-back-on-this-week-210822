<!-- このjspはデザイン的にうまく配置することができず、結局menu.jspなどに検索用テキストボックスを入れてしまってます -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<!-- 映像検索部分 -->

<h2>※管理者専用：メディア情報検索（DB上の全てのデータを検索可能）</h2>
<br>
検索キーワードを入力してください。文字列の一部でも検索出来ます。<br>
mediaCode,　mediaTitle,　mediaInfo,　director,　leadingActor,　musicに対応しています。
<form action="SearchMediaAdmin.action" method="post">
<p><input type="text" name="keyword" size="50"></p>
<input type="radio" name="select" value="1" checked>全検索　　
<input type="radio" name="select" value="2" >配信中情報の検索　　
<input type="radio" name="select" value="3" >未配信情報の検索(配信日が未来)　　
<input type="radio" name="select" value="4" >Ghost化情報の検索　　
<br><br>
<p><input type="submit" value="GoGo"> <--- 押下で検索結果表示</p>
</form>

<%@include file="../footer.html" %>