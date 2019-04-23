<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>CMS 后台管理工作平台</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">文章管理</a>
      <ul>
        <li><a href="#" target="rightFrame">查询文章</a></li>
        <li><a href="#" target="rightFrame">添加文章</a></li>
        <li><a href="#" target="rightFrame">留言管理</a></li>
      </ul>
    </li>
    <li> <a class="head">频道管理</a>
      <ul>
        <li><a href="#" target="rightFrame">查询频道</a></li>
        <li><a href="#" target="rightFrame">添加频道</a></li>
      </ul>
    </li>
    <li> <a class="head">爬虫管理</a>
      <ul>
        <li><a href="#" target="rightFrame">设定爬虫规则</a></li>
        <li><a href="#" target="rightFrame">爬虫文章管理</a></li>
      </ul>
    </li>
    <li> <a class="head">会员管理</a>
      <ul>
        <li><a href="#" target="rightFrame">会员管理</a></li>
      </ul>
    </li>
    <li> <a class="head">系统管理</a>
      <ul>
        <li><a href="#" target="rightFrame">修改管理员密码</a></li>
        <li><a href="#" target="rightFrame">修改数据库配置</a></li>
        <li><a href="#" target="rightFrame">备份网站内容</a></li>
        <li><a href="#" target="rightFrame">导入网站内容</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>

