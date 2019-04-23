package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

public class DelArticlesServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//�ӽ������ID����
		String[] ids = request.getParameterValues("id");
		
		if(ids == null){
			//��ʾ����(forward������ҳ��)
			request.setAttribute("error", "�޷�ɾ�����£�ID������Ϊ��");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		ArticleDao articleDao = (ArticleDao)new PropertiesBeanFactory().getBean("articleDao");
		articleDao.delArticles(ids);

		//ת���б�ҳ��
		request.getRequestDispatcher("/backend/SearchArticlesServlet").forward(request, response);
	}

}
