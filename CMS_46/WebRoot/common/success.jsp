<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/main.css">
<title>会员自服务系统</title>
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
		<!-- 导航条 -->
		<a href="MemberServlet">个人控制台首页</a>
		<a href="MemberServlet?method=updateInput">修改个人信息</a>
		<a href="MemberServlet?method=updatePasswordInput">修改个人密码</a>
		<hr>
		<!-- 主界面 -->
		<div>
		
<TABLE cellSpacing=0 cellPadding=0 width=540 align=center border=0>
  <TBODY>
  <TR>
    <TD vAlign=top height=270>
      <DIV align=center><BR><IMG height=211 src="common/success.gif" 
      width=329><BR><BR>
      <TABLE cellSpacing=0 cellPadding=0 width="80%" border=0>
        <TBODY>
        <TR>
          <TD><FONT class=p2>&nbsp;&nbsp;&nbsp; 以下操作成功：</FONT></TD></TR>
        <TR>
          <TD height=8></TD>
        <TR>
          <TD>
            <P>
			<FONT color=#00ff00><IMG 
            height=13 src="common/ok.gif" 
            width=12>&nbsp;${success}</FONT>            
      　</P></TD></TR></TBODY></TABLE></DIV></TD></TR>
  <TR>
    <TD height=5></TD>
  <TR>
    <TD align=middle>
      <CENTER>
      <TABLE cellSpacing=0 cellPadding=0 width=480 border=0>
        <TBODY>
        <TR>
          <TD width=6><IMG height=26 src="common/left.gif" 
width=7></TD>
          <TD background="common/bg.gif">
            <DIV align=center><FONT class=p6>
            <A href="javascript:history.go(-1)">返回上一页</A></FONT> </DIV></TD>
          <TD width=7><IMG 
      src="common/right.gif"></TD></TR></TBODY></TABLE></CENTER></TD></TR></TBODY></TABLE>		
		
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