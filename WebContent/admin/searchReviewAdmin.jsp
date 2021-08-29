<!-- このjspはデザイン的にうまく配置することができず、結局menu.jspなどに検索用テキストボックスを入れてしまってます -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<!-- 映像検索部分 -->

<h2>※管理者専用：レビュー検索（DB上の全てのデータを検索可能）</h2>
<br>
検索キーワードを下記テキストボックスに入力してください。<br>
nickName,reviewContentに対応しています。文字列の一部でも検索出来ます。<br>
<form action="SearchReviewAdmin.action" method="post">
<p><input type="text" name="keyword" size="50">（自身が投稿したレビューもここから確認してください）</p>
[検索フィルター]<br>
<input type="radio" name="select" value="1" checked>全レビューから検索(Ghost化したものも含む)<br>
<input type="radio" name="select" value="2" >ゲスト・ユーザーが閲覧可能なレビューからの検索(Ghost化したものは除外)<br>
<input type="radio" name="select" value="3" >削除済みレビューの検索(削除＝Ghost化)<br>

<p><input type="submit" value="GoGo"> <--- 押下で検索結果表示</p>
<br>
※このレビュー検索の使い方<br>
基本的には、nickName検索を使うと的確な場合が多いと思います。<br>
<br>
また、reviewContent検索については『文言が明確にわかっている場合』<br>
あるいは『NGワード集があり定期的にレビュー清掃を行いたい場合』<br>
などに効果が発揮できると思います。<br>

</form>

<%@include file="../footer.html" %>