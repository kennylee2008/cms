package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

public class AddArticleServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��request�л�ȡ����
		String title = request.getParameter("title");
		String source = request.getParameter("source");
		String content = request.getParameter("content");
		
		//�����ݲ�������
		ArticleDao articleDao = new PropertiesBeanFactory().getArticleDao();
		Article a = new Article();
		a.setTitle(title);
		a.setSource(source);
		a.setContent(content);
		articleDao.addArticle(a);
		
		//forward���ɹ�ҳ��
		request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request, response);
	}

}
