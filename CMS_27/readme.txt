完整实现了ArticleServlet、ChannelServlet、ArticleDao、ChannelDao
	- 将与Article有关的Servlet全部合成到ArticleServlet类中
	- 将与Channel有关的Servlet全部合成到ChannelServlet类中
	- 将与Article有关的数据库操作全部放到ArticleDao中
	- 将与Channel有关的数据库操作全部放到ChannelDao中

创建了Admin实体类，t_admin数据库表，并创建了AdminDao接口

准备：

将LoginServlet、LogoutServlet、CheckCodeServlet这些与登录有关的Servlet，全部合成到LoginServlet中！
	- LoginServlet中的方法checkcode用于生成验证码
	- LoginServlet中的方法execute用于登录用户名和密码验证
	- LoginServlet中的方法quit用于退出后台系统