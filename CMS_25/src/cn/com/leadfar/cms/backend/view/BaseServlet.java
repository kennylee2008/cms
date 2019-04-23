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
		//System.out.println("调用了BaseServlet中的service方法");
		
		//this是多态的
		//System.out.println("this是："+this);
		
		//利用反射，调用this对象中的相关的setters方法！
		Method[] methods = this.getClass().getMethods();
		for(Method m:methods){
			if(m.getName().startsWith("set")){
				
				//ArticleDao
				String propertyName = m.getName().substring(3);
				
				StringBuffer sb = new StringBuffer(propertyName);
				sb.replace(0, 1, (propertyName.charAt(0)+"").toLowerCase());
				
				//articleDao
				propertyName = sb.toString();
				
				//约定：setters方法所决定的属性（property）名，与配置文件中相应的对象命名一致！
				Object bean = factory.getBean(propertyName);
				
				try {
					//将依赖对象注入客户端
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
		
		//执行父类的职责：根据请求是GET还是POST方法，调用doGet或doPost！
		super.service(arg0, arg1);
	}

}
