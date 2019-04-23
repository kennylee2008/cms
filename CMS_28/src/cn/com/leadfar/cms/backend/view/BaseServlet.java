package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.utils.BeanFactory;

public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {

		BeanFactory factory = (BeanFactory)getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);

		//���÷��䣬����this�����е���ص�setters������
		Method[] methods = this.getClass().getMethods();
		for(Method m:methods){
			if(m.getName().startsWith("set")){
				
				//ArticleDao
				String propertyName = m.getName().substring(3);
				
				StringBuffer sb = new StringBuffer(propertyName);
				sb.replace(0, 1, (propertyName.charAt(0)+"").toLowerCase());
				
				//articleDao
				propertyName = sb.toString();
				
				//Լ����setters���������������ԣ�property�������������ļ�����Ӧ�Ķ�������һ�£�
				Object bean = factory.getBean(propertyName);
				
				try {
					//����������ע��ͻ���
					m.invoke(this, bean);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		//ִ�и����ְ�𣺸���������GET����POST����������doGet��doPost��
		super.service(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
		//���Ȼ�ȡ��������
		String method = request.getParameter("method");
		
		//����ͻ��˲�����method��������Ĭ�ϵ���execute()����
		if(method == null || method.trim().equals("")){
			execute(request,response);
		}else{
			//����method������ȡֵ��������صķ���
			try {
				Method m = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				
				//������ת������Ӧ�ķ�����
				m.invoke(this, request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		//ʲôҲ������
	}

}
