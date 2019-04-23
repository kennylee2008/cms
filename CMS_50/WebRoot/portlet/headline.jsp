<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>

		<div style="width:100%;border:0px;text-align: left;">
			<img style="float:left;width:200px;height:200px;" src="images/earth.jpg">
			<c:forEach items="${headline}" var="h">
			<h4>${h.title }</h4>
			<p>${h.intro }</p>
			<a href="article.jsp?articleId=${h.id }">【阅读全文】</a>
			</c:forEach>
		</div>
		