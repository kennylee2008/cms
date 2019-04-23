<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>

<div class="comment">
<c:forEach items="${comments}" var="c">
<hr style="border:1px dashed gray" noshade/>
[${c.name }] <fmt:formatDate value="${c.commentTime}" pattern="yyyy-MM-dd HH:mm:ss"/> <br/>
${c.content }
</c:forEach>

<hr style="border:1px dashed gray" noshade/>
<form action="CommentServlet" method="post">
<input type="hidden" name="articleId" value="${param.articleId }">
<input type="hidden" name="method" value="add">
<input type="hidden" name="memberId" value="${LOGIN_MEMBER.id }">
<table>
	<tr>
		<td valign="top" align="right">姓名:</td>
		<td><input type="text" name="name"
		value="<c:out value="${LOGIN_MEMBER.name}" default="匿名网友"/>"
		></td>
		<td valign="top" align="right">E-Mail:</td>
		<td><input type="text" name="email"></td>
		<td valign="top" align="right">网址:</td>
		<td><input type="text" name="site"></td>
	</tr>
	<tr>
		<td valign="top" align="right">评论内容:</td>
		<td colspan="5">
		<textarea rows="5" cols="50" name="content"></textarea>
		<input type="submit" name="submit" value="发表评论">
		</td>
	</tr>
</table>
</form>
</div>
