<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<div id="nav">
	<div id="menu">
		<a href="index.jsp">首页</a>
		<c:forEach items="${channels}" var="c">
		<a href="NavServlet?method=channelList&channelId=${c.id }">${c.name }</a>
		</c:forEach>
	</div>
</div>
