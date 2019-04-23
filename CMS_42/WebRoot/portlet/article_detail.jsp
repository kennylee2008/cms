<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
	<div id="left" style="text-align:left">
		首页 -&gt; [
		<c:forEach items="${article.channels}" var="c">
		<a href="channel.jsp?channelId=${c.id }">${c.name }</a>
		</c:forEach>
		]
		 -&gt; ${article.title }
		<hr>
<!-- 文章标题及简介 -->
<div class="h2title">${article.title }</div>
<div CLASS="atime">
作者：${article.author }
| 来源：${article.source }
| <fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm"/>
</div>
${article.content }
	</div>
	