<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<script type="text/javascript">
function reloadcheckcode(img){
	img.src = "MemberLoginServlet?method=checkcode&"+Math.random();
}
if(window.parent != window){
	window.parent.location = window.location;
}
</script>
	<!-- 右边 -->
	<div class="right">
		<div class="right_topic_1">
		<c:if test="${LOGIN_MEMBER != null}">
		会员信息
		</c:if>
		<c:if test="${LOGIN_MEMBER == null}">
		会员登录
		</c:if>
		</div>
		<div class="right_topic_2">
		</div>
		<div class="right_topic_3">
			<c:if test="${LOGIN_MEMBER == null}">
			<form action="MemberLoginServlet" method="POST">
			&nbsp;
			帐号：<input type="text" style="width:100px" name="nickname"> <br/>
			&nbsp;
			密码：<input type="password" style="width:100px" name="password">
			<img src="MemberLoginServlet?method=checkcode" id="safecode" onclick="reloadcheckcode(this)" title="如果看不清，请点击本图片换一张"/>
			<br/>
			&nbsp;<input type="submit" value="登录"> <br/>
			</form>
			</c:if>
			<c:if test="${LOGIN_MEMBER != null}">
			&nbsp;欢迎 ${LOGIN_MEMBER.name } 来到领航致远JAVA联盟 <br/>
			<a href="MemberServlet">个人控制台</a>
			<a href="MemberLoginServlet?method=quit">退出登录</a>
			</c:if>
		</div>
	</div>
	