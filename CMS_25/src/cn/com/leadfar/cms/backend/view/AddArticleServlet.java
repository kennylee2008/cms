package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.BeanFactory;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

public class AddArticleServlet extends BaseServlet {

	private ArticleDao articleDao;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//从request中获取参数
		String title = request.getParameter("title");
		String source = request.getParameter("source");
		String content = request.getParameter("content");
		
		//将数据插入数据库
		Article a = new Article();
		a.setTitle(title);
		a.setSource(source);
		a.setContent(content);
		articleDao.addArticle(a);
		
		//forward到成功页面
		request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request, response);
	}

	//本setters方法，定义了一个articleDao这样的一个Property
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
}
