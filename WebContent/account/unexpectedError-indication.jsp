<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.html" %>
<%@include file="/toppage/menu.jsp" %>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<form action="${message3}" method="post">
<div style="text-align:center">

<h2><font color="red">${message1}</font></h2><br>
${message2}<br>
<br>
<meta http-equiv="refresh" content="10;URL=${message3}"><br>
<p><input type="submit" value="${message4}" ></p>
(10秒後に自動で遷移もします。）<br>
<br>
</div>
</form>

<%@include file="../footer.html" %>


