首先实现AdminDao接口

将LoginServlet、LogoutServlet、CheckCodeServlet这些与登录有关的Servlet，全部合成到LoginServlet中！
	- LoginServlet中的方法checkcode用于生成验证码
	- LoginServlet中的方法execute用于登录用户名和密码验证
	- LoginServlet中的方法quit用于退出后台系统
	
【
当重写init(ServletConfig)方法的时候，记得调用super.init(ServletConfig)
调用super.init(ServletConfig)的目的，主要是由于在父类（GenericServlet）中
有一个ServletConfig实例变量，super.init(ServletConfig)就是给这个实例变量复制。

这样，再后续的getServletContext()操作，才可以拿到ServletContext对象:
GenericServlet的部分源代码如下所示：
----------------------------------------------------
public abstract class GenericServlet 
    implements Servlet, ServletConfig, java.io.Serializable
{

    private transient ServletConfig config;
    
    public void init(ServletConfig config) throws ServletException {
		this.config = config;
		this.init();
    }
    public void init() throws ServletException {
    }    
    public ServletConfig getServletConfig() {
		return config;
    }
    public ServletContext getServletContext() {
		return getServletConfig().getServletContext();
    }
-----------------------------------------------------    
】