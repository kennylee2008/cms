提供如下功能的参考实现：

编写了一个SiteFunction函数，查询频道的有关信息以及文章的有关信息
	- 请参考channel.jsp页面，其中的
<title>欢迎访问领航致远JAVA联盟【${cms:channel(pageContext,param.channelId).name }】</title>
	- 请参考article.jsp页面，其中的
<title>${cms:article(pageContext,param.articleId).title }</title>