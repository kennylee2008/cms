<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div id="nav">
	<div id="menu">
		<a href="#">首页</a>
		<a href="topic.jsp">Java SE</a>
		<a href="topic.jsp">Java EE</a>
		<a href="topic.jsp">JBPM</a>
		<a href="topic.jsp">Android</a>
		<a href="topic.jsp">OpenSources</a>
		<a href="topic.jsp">分析与设计</a>
		<a href="#">论坛</a>
	</div>
</div>
<!-- 首页中间 -->
<div id="mid">
	<!-- 首页左边 -->
	<div id="left">
		<!-- 首页图片及首页文章标题、简介 -->
		<div style="width:100%;border:0px;text-align: left;">
			<img style="float:left;width:200px;height:200px;" src="images/earth.jpg">
			<h4>JBPM实例介绍【之一】</h4>
			<p>
要知道为什么需要工作流引擎，我们首先要知道如果没有工作流引擎，那么究竟会发生什么事情？

设想一个典型的业务流程场景：公文流转。假设有一个请假流程，需要经过张三、李四、王五三个人的审批之后，才能结束，那么你作为程序员，你会如何做呢？

毫无疑问，首先第一步，你需要整理一下思路，看一看在这个请假模块中，你需要做哪些最基本的功能：

第一、   创建请假单

第二、   将请假单提交给张三

			</p>
<h4>JBPM实例介绍【之二】</h4>
			<p>
要知道为什么需要工作流引擎，我们首先要知道如果没有工作流引擎，那么究竟会发生什么事情？

设想一个典型的业务流程场景：公文流转。假设有一个请假流程，需要经过张三、李四、王五三个人的审批之后，才能结束，那么你作为程序员，你会如何做呢？

毫无疑问，首先第一步，你需要整理一下思路，看一看在这个请假模块中，你需要做哪些最基本的功能：

第一、   创建请假单

第二、   将请假单提交给张三

第三、   张三审批请假单之后，将请假单交给李四

第四、   李四审批请假单之后，将请假单交给王五

第五、   王五审批请假单之后，准假，结束

第二步，现在是不是得看一看这里面有哪些概念，以便于进一步的设计？用户、请假单，是这个需求中两个基本的概念。用户包括：用户名、用户密码属性；请假单包括：请假者、请假事项、请假开始时间、请假结束时间属性

			</p>			
		</div>
		<!-- 底下是专题或频道的文章标题列表 -->
		<div class="index_topic">
			<img src="images/jiantou.gif" style="float:left">
			<span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">领域模型</span>
			<a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
		</div>
		<div class="index_topic">
			<img src="images/jiantou.gif" style="float:left">
			<span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">领域模型</span>
			<a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>		
		</div>
		<div class="index_topic">
			<img src="images/jiantou.gif" style="float:left">
			<span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">领域模型</span>
			<a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>		
		</div>
		<div class="index_topic">
			<img src="images/jiantou.gif" style="float:left">
			<span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">领域模型</span>
			<a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>
			<div class="index_topic_list">
				<img src="images/h_article.gif" >
				<a href="#">概念模型 - 读《分析模式》之一</a>
				<span>[2010-07-18]</span>
			</div>		
		</div>
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
	
	<div class="right" style="height:200px">
		<div class="right_topic_1">
			推荐阅读
		</div>
		<div class="right_topic_2">
			<a href="#"><img src="images/more_red.gif" style="float:right;margin-top:10px;border:0px"></a>
		</div>
		<div class="right_topic_3">
			· <a href="#">概念模型 - 读《分析模式》之一</a>
		</div>
	</div>
	
	<div class="right" style="height:300px">
		<div class="right_topic_1">
			最新发表
		</div>
		<div class="right_topic_2">
			<a href="#"><img src="images/more_red.gif" style="float:right;margin-top:10px;border:0px"></a>
		</div>
		<div class="right_topic_3">
			· <a href="#">概念模型 - 读《分析模式》之一</a>
		</div>	
	</div>
	
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