package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.utils.BeanFactory;

public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BeanFactory factory = (BeanFactory)getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);

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
		
		try{
			//取出分页参数，设置到ThreadLocal中
			SystemContext.setOffset(getOffset(request));
			SystemContext.setPagesize(getPagesize(request));
			
			//执行父类的职责：根据请求是GET还是POST方法，调用doGet或doPost！
			super.service(request, response);
			
		}finally{
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
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
	
		//首先获取方法参数
		String method = request.getParameter("method");
		
		//如果客户端不传递method参数，则默认调用execute()方法
		if(method == null || method.trim().equals("")){
			execute(request,response);
		}else{
			//根据method参数的取值，调用相关的方法
			try {
				Method m = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				
				//将请求转发到相应的方法中
				m.invoke(this, request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		//什么也不做！
	}
	
	protected int getOffset(HttpServletRequest request){
		int offset = 0;
		// 希望从request中获得offset参数
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}
		return offset;
	}
	
	protected int getPagesize(HttpServletRequest request){
		int pagesize = 5;

		// 如果从request传递过来了pagesize参数，那么就需要更新http session中的pagesize的值
		if (request.getParameter("pagesize") != null) {
			request.getSession().setAttribute("pagesize",
					Integer.parseInt(request.getParameter("pagesize")));
		}

		// 希望从http session中获得pagesize的值，如果没有，则设置缺省值为5
		Integer ps = (Integer) request.getSession().getAttribute("pagesize");
		if (ps == null) {
			request.getSession().setAttribute("pagesize", pagesize);
		} else {
			pagesize = ps;
		}
		return pagesize;
	}
	
	protected Member loginMember(HttpServletRequest request){
		return (Member)request.getSession().getAttribute("LOGIN_MEMBER");
	}
}
