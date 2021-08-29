<!-- このjspはデザイン的にうまく配置することができず、結局menu.jspなどに検索用テキストボックスを入れてしまってます -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<!-- 映像検索部分 -->

<h2>※管理者専用：ユーザー・管理者検索（DB上の全てのデータを検索可能）</h2>
<font color="red">アカウントの削除・復元、権限の付与を行うには、その対象となるアカウントをまず検索してください。</font><br>
<br>
検索キーワードを入力してください。文字列の一部でも検索出来ます。<br>
nickNameにのみ対応しています。<br>
<form action="SearchAccountAdmin.action" method="post">
<p><input type="text" name="keyword" size="50"><font color="red">　注）自分のアカウントは検索結果に表示されません！</font><br></p>
<br>
<input type="radio" name="select" value="1" checked>ユーザーのみを検索（削除アカウント含まず）　　※削除＝Ghost化<br>
<input type="radio" name="select" value="2" >管理者のみを検索（削除アカウント含まず）　<br>
<input type="radio" name="select" value="3" >削除済みアカウント（Ghost化アカ）のみを検索　<br>

<br>
<div style="text-align:center">
<h2><font color="red"></font></h2>
</div>
<input type="submit" value="GoGo"> <--- 押下で検索結果表示<br>
<br>
</form>

<%@include file="../footer.html" %>