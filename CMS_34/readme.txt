关注文章管理的添加界面
- 因为在添加文章的界面上，需要显示所有的频道列表以供选择，所以需要定义一个Servlet，在Servlet中
  查询到频道列表之后，转向JSP，在JSP中显示出频道列表来
   × 在ArticleServlet中增加了一个addInput方法，用来查询频道列表，并转向add_article.jsp页面
   * 在add_article.jsp页面上用JSTL标签库把频道列表显示出来
- 提交文章的基本信息时，除了往t_article表添加一条文章的记录之外，如果选择了多个频道，则还需要往
  t_channel_article表插入文章与频道之间的关联记录