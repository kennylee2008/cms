<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/main.css">
<title>欢迎访问领航致远JAVA联盟</title>
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
	<div id="left">
		<!-- 首页图片及首页文章标题、简介 -->
		<jsp:include page="NavServlet?method=headline"></jsp:include>
		<!-- 底下是专题或频道的文章标题列表 -->
		<jsp:include page="NavServlet?method=indexChannelArticleList&channelId=1"/>
		<jsp:include page="NavServlet?method=indexChannelArticleList&channelId=2"/>
		<jsp:include page="NavServlet?method=indexChannelArticleList&channelId=3"/>
		<jsp:include page="NavServlet?method=indexChannelArticleList&channelId=4"/>
	</div>
	<!-- 首页右边 -->
	<jsp:include page="/portlet/login_form.jsp"></jsp:include>
	<%@include file="/portlet/search_form.jsp" %>
	
	<jsp:include page="NavServlet?method=recommend"></jsp:include>
	
	<jsp:include page="NavServlet?method=latest"></jsp:include>
	
	<div class="right" style="height:340px">
		<div class="right_topic_1">
			技术专题
		</div>
		<div class="right_topic_2">
			<a href="#"><img src="images/more_red.gif" style="float:right;margin-top:10px;border:0px"></a>
		</div>
		<div class="right_topic_3">
			· <a href="#">Hibernate性能调整</a> <br/>
			· <a href="#">XPath入门</a>
		</div>	
	</div>
	<div style="clear:both"></div>
</div>
<!-- 首页下部，版权信息等等 -->
<%@include file="/portlet/footer.jsp" %>
</body>
</html>