<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/main.css">
<title>如何在JBPM的Action中访问Spring中的JavaBean</title>
</head>
<body>
<!-- 网站的logo，其它背景，首页横幅广告等等 -->
<div id="top">
	<img src="images/logo.gif" class="logo" title="领航致远JAVA联盟">
	<div class="bg">
	</div>
</div>
<!-- 首页的导航条 -->
<jsp:include page="NavServlet?method=navList"></jsp:include>
<!-- 首页中间 -->
<div id="mid">
	<!-- 首页左边 -->
	<jsp:include page="NavServlet?method=articleDetail"></jsp:include>
	<!-- 首页右边 -->
	<div class="right">
		<div class="right_topic_1">
			本站搜索
		</div>
		<div class="right_topic_2">
		</div>
		<div class="right_topic_3">
			<form>
			&nbsp;&nbsp;<input type="text">
			<input type="submit" value="搜索"> <br/>
			<input type="radio" name="type" value="baidu">百度
			<input type="radio" name="type" value="google">谷歌
			<input type="radio" name="type" value="local">本站
			</form>
		</div>
	</div>
	
	<jsp:include page="NavServlet?method=keywords"></jsp:include>
	
	<div style="clear:both"></div>
</div>
<!-- 首页下部，版权信息等等 -->
<%@include file="/portlet/footer.jsp" %>
</body>
</html>