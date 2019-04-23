<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>

	<!-- 首页左边 -->
	<div id="left" style="text-align:left">
		首页 -&gt; ${channel.name }
		<hr>
		<c:forEach items="${pv.datas}" var="a">
		<!-- 文章标题及简介 -->
		<div class="h2title">${a.title }</div>
		<div CLASS="atime">
		作者：${a.author }
		| 来源：${a.source }
		| <fmt:formatDate value="${a.createTime}" pattern="yyyy-MM-dd HH:mm"/>
		</div>
		${a.intro }
		<br/>
		<a href="article.jsp?articleId=${a.id }">【阅读全文】</a>
		</c:forEach>
		<jsp:include page="/backend/common/pager.jsp">
			<jsp:param name="url" value="channel.jsp"/>
			<jsp:param name="params" value="channelId"/>
		</jsp:include>
	</div>
	
