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

		//���Http Session�е��������ݣ�����HTTP SESSION����
		//�����Ự
		request.getSession().invalidate();
		
		//ת���¼ҳ��
		response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
	}

}
