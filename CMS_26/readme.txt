Ϊ�˼���Servlet�������������趨�ӽ����д���һ��method�������������������ȡֵ�����÷�����ƣ�����Servlet�ķ���
����������¹�����Щ�ڶ��Servlet:
AddArticleServlet��DelArticlesServlet��OpenUpdateArticleServlet��UpdateArticleServlet��SearchArticlesServlet

���ǽ���ЩServlet�ϳ�Ϊһ��Servlet��ArticleServlet��ArticleServlet�Ļ����ṹ���£�
public class ArticleServlet extends BaseServlet{
	//�������ִ�в�ѯ����
	public void execute(HttpServletRequest request,HttpServletResponse response){...}
	
	//�������ִ����ӹ���
	public void add(HttpServletRequest request,HttpServletResponse response){...}
	
	//�������ִ��ɾ������
	public void del(HttpServletRequest request,HttpServletResponse response){...}
	
	//�������ִ�д򿪸��½���Ĺ���
	public void updateInput(HttpServletRequest request,HttpServletResponse response){...}
	
	//�������ִ�и��¹���
	public void update(HttpServletRequest request,HttpServletResponse response){...}
}

��BaseServlet�У����ݽ��洫�ݹ����Ĳ���method��ȡֵ�����÷�����Ƶ��ò�ͬ�ķ������ɣ�