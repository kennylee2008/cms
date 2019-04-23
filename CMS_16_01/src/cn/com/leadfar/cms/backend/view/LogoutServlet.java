package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//清空Http Session中的所有数据，销毁HTTP SESSION对象
		//结束会话
		request.getSession().invalidate();
		
		//转向登录页面
		response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
	}

}
