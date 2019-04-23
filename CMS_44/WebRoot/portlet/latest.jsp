<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>

	<div class="right" style="height:300px">
		<div class="right_topic_1">
			最新发表
		</div>
		<div class="right_topic_2">
			
		</div>
		<div class="right_topic_3">
		<c:forEach items="${latest}" var="c">
			· <a href="article.jsp?articleId=${c.id }">${c.title }</a> <br/>
		</c:forEach>			
		</div>	
	</div>
	