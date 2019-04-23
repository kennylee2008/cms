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
		
		//
		//System.out.println("������BaseServlet�е�service����");
		
		//this�Ƕ�̬��
		//System.out.println("this�ǣ�"+this);
		
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

}
