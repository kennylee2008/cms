����ʵ��AdminDao�ӿ�

��LoginServlet��LogoutServlet��CheckCodeServlet��Щ���¼�йص�Servlet��ȫ���ϳɵ�LoginServlet�У�
	- LoginServlet�еķ���checkcode����������֤��
	- LoginServlet�еķ���execute���ڵ�¼�û�����������֤
	- LoginServlet�еķ���quit�����˳���̨ϵͳ
	
��
����дinit(ServletConfig)������ʱ�򣬼ǵõ���super.init(ServletConfig)
����super.init(ServletConfig)��Ŀ�ģ���Ҫ�������ڸ��ࣨGenericServlet����
��һ��ServletConfigʵ��������super.init(ServletConfig)���Ǹ����ʵ���������ơ�

�������ٺ�����getServletContext()�������ſ����õ�ServletContext����:
GenericServlet�Ĳ���Դ����������ʾ��
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
��