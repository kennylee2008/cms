<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/main.css">
<title>会员自服务系统</title>
<style type="text/css">
<!--

h3 {
	font-size:14px;
	font-weight:bold;
}

pre,p {
	color:#1E7ACE;
	margin:4px;
}
input, select,textarea {
	padding:1px;
	margin:2px;
	font-size:12px;
}
.buttom{
	padding:1px 10px;
	font-size:12px;
	border:1px #1E7ACE solid;
	background:#D0F0FF;
}
#formwrapper {
	width:95%;
	margin:15px auto;
	padding:20px;
	text-align:left;
}

fieldset {
	padding:10px;
	margin-top:5px;
	border:0px solid #1E7ACE;
	background:#fff;
}

fieldset legend {
	color:#1E7ACE;
	font-weight:bold;
	background:#fff;
}

fieldset label {
	float:left;
	width:120px;
	text-align:right;
	padding:4px;
	margin:1px;
}

fieldset div {
	clear:left;
	margin-bottom:2px;
}

.enter{ text-align:center;}
.clear {
	clear:both;
}

-->
</style>
<script type="text/javascript">
function validate(form){
	if(form.newPassword.value != form.newPassword2.value){
		alert("您两次输入的新密码不一致，请重新输入！");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<!-- 网站的logo，其它背景，首页横幅广告等等 -->
<div id="top">
	<img src="images/logo.gif" class="logo" title="领航致远JAVA联盟">
	<div class="bg">
	</div>
</div>
<!-- 首页的导航条 -->
<jsp:include page="/NavServlet?method=navList"></jsp:include>
<!-- 首页中间 -->
<div id="mid">
	<!-- 首页左边 -->
<div id="left" style="text-align:left">
		<a href="MemberServlet">个人控制台首页</a>
		<a href="MemberServlet?method=updateInput">修改个人信息</a>
		<a href="MemberServlet?method=updatePasswordInput">修改个人密码</a>
		<hr>
<!-- 主界面 -->
<div class="h2title">修改个人密码</div>
	<div id="formwrapper">
		<form action="MemberServlet" method="post" onsubmit="return validate(this);">
		<input type="hidden" name="method" value="updatePassword">
		<input type="hidden" name="id" value="${member.id }">
		<fieldset>	
			<div>
				<label for="oldPassword">请输入您的旧密码</label>
				<input type="text" name="oldPassword" id="oldPassword" value="" size="30" maxlength="200" /> 
				<br />	
			</div>
			<div>
				<label for="newPassword">请输入新密码</label>
				<input type="text" name="newPassword" id="newPassword" value="" size="30" maxlength="100" /> 
				<br />	
			</div>
			<div>
				<label for="newPassword2">请再次输入新密码</label>
				<input type="text" name="newPassword2" id="newPassword2" value="" size="30" maxlength="100" /> 
			</div>
			
			<div class="enter">
			    <input name="submit" type="submit" class="buttom" value="提交" />
			    <input name="reset" type="reset" class="buttom" value="重置" />
			</div>
		</fieldset>
		</form>
	</div>
</div>
	<!-- 首页右边 -->
	<jsp:include page="/portlet/login_form.jsp"></jsp:include>
	<%@include file="/portlet/search_form.jsp" %>
	<div style="clear:both"></div>
</div>
<!-- 首页下部，版权信息等等 -->
<%@include file="/portlet/footer.jsp" %>
</body>
</html>