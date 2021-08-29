<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html" %>
<%@include file="../toppage/menu.jsp" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>

${form_action}
<div style="text-align:center">

${result}
${message1}
<%-- ${message2} --%>

${url}
${submit}

<br>
<p>権限ランク：${account.admin}（本番は消す）</p>

</div>
</form>
<%@include file="../footer.html" %>

