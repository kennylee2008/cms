<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
	<div class="right" >
		<div class="right_topic_1">
			相关文章
		</div>
		<div class="right_topic_2">
		</div>
		<div class="right_topic_3">
		<c:forEach items="${pv.datas}" var="c">
			· <a href="article.jsp?articleId=${c.id }">${c.title }</a> <br/>
		</c:forEach>
		</div>	
	</div>
	