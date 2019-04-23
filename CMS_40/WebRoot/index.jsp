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
<div id="bottom">
	<hr style="border:1px dashed gray; height:1px;width:500px"> 
	<a href="#">关于本站</a> | 
	<a href="#">版权声明</a> | 
	<a href="#">联系方式</a> |
	<a href="#">在线留言</a>
	<br>
	&copy;www.topxp.org, all rights reserved.
	<strong><a href="http://www.miibeian.gov.cn">京ICP备06046340号</a></strong>
</div>
</body>
</html>