package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;

public class OpenUpdateArticleServlet extends BaseServlet {

	private ArticleDao articleDao;
	
	/**
	 * 打开更新文章的界面
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//接收从界面传递过来的ID
		String id = request.getParameter("id");
		
		Article a = articleDao.findArticleById(Integer.parseInt(id));
		request.setAttribute("article", a);
		
		//forward到更新页面
		request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request, response);
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
