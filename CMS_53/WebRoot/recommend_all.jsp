<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/main.css">
<title>所有推荐阅读的文章列表</title>
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
	<jsp:include page="NavServlet?method=recommendIndex"></jsp:include>
	<!-- 首页右边 -->
	<jsp:include page="/portlet/login_form.jsp"></jsp:include>
	<%@include file="/portlet/search_form.jsp" %>
	<jsp:include page="NavServlet?method=latest"></jsp:include>
	<div style="clear:both"></div>
</div>
<!-- 首页下部，版权信息等等 -->
<%@include file="/portlet/footer.jsp" %>
</body>
</html>