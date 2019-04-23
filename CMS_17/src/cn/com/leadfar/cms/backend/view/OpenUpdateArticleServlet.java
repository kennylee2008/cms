package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;
import cn.com.leadfar.cms.backend.model.Article;

public class OpenUpdateArticleServlet extends HttpServlet {

	/**
	 * �򿪸������µĽ���
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���մӽ��洫�ݹ�����ID
		String id = request.getParameter("id");
		
		ArticleDao articleDao = new ArticleDaoImpl();
		Article a = articleDao.findArticleById(Integer.parseInt(id));
		request.setAttribute("article", a);
		
		//forward������ҳ��
		request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request, response);
	}

}
