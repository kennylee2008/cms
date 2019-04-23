为了减少Servlet的数量，我们设定从界面中传递一个method参数，根据这个参数的取值，利用反射机制，调用Servlet的方法
比如对于文章管理那些众多的Servlet:
AddArticleServlet、DelArticlesServlet、OpenUpdateArticleServlet、UpdateArticleServlet、SearchArticlesServlet

我们将这些Servlet合成为一个Servlet：ArticleServlet，ArticleServlet的基本结构如下：
public class ArticleServlet extends BaseServlet{
	//这个方法执行查询功能
	public void execute(HttpServletRequest request,HttpServletResponse response){...}
	
	//这个方法执行添加功能
	public void add(HttpServletRequest request,HttpServletResponse response){...}
	
	//这个方法执行删除功能
	public void del(HttpServletRequest request,HttpServletResponse response){...}
	
	//这个方法执行打开更新界面的功能
	public void updateInput(HttpServletRequest request,HttpServletResponse response){...}
	
	//这个方法执行更新功能
	public void update(HttpServletRequest request,HttpServletResponse response){...}
}

在BaseServlet中，根据界面传递过来的参数method的取值，利用反射机制调用不同的方法即可！