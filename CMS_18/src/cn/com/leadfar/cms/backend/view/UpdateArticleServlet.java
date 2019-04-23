package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.BeanFactory;

public class UpdateArticleServlet extends HttpServlet {

	/**
	 * �������µĹ���
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���ȣ��ӽ���������µĻ�����Ϣ��������ID������ȵȣ�
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String source = request.getParameter("source");
		String content = request.getParameter("content");
		
		Article a = new Article();
		a.setId(Integer.parseInt(id));
		a.setTitle(title);
		a.setSource(source);
		a.setContent(content);
		
		ArticleDao articleDao = BeanFactory.getArticleDao();
		articleDao.updateArticle(a);
		
		//forward�����³ɹ���ҳ��
		request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request, response);
	}

}
