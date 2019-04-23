package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	private String filterPattern;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//requestURI = ?
		String requestURI = request.getRequestURI();
	
		String page = requestURI.substring(request.getContextPath().length());
		
		//判断HTTP SESSION中是否有LOGIN_ADMIN
		String loginAdmin = (String)request.getSession().getAttribute("LOGIN_ADMIN");

		if(page.matches(filterPattern)){
			
			if(loginAdmin == null && !page.equals("/backend/login.jsp") && !page.equals("/backend/LoginServlet")
					){
				//redirect到login.jsp
				response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
				return;
			}
		}
		//继续向下执行
		chain.doFilter(req, resp);
		
	}

	public void init(FilterConfig config) throws ServletException {
		filterPattern = config.getInitParameter("filterPattern");
	}

}
