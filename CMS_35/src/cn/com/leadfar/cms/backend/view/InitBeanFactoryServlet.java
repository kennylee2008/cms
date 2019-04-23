package cn.com.leadfar.cms.backend.view;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.com.leadfar.cms.utils.BeanFactory;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

public class InitBeanFactoryServlet extends HttpServlet {
	
	public static final String INIT_FACTORY_NAME = "_my_bean_factory";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		BeanFactory factory = null;
		String configLocation = config.getInitParameter("configLocation");
		if(configLocation == null){
			factory = new PropertiesBeanFactory();
		}else{
			factory = new PropertiesBeanFactory(configLocation);
		}
		System.out.println("≥ı ºªØBeanFactory......");
		getServletContext().setAttribute(INIT_FACTORY_NAME, factory);
	}

}
