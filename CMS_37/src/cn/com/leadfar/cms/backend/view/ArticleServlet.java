package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.RequestUtil;

public class ArticleServlet extends BaseServlet {

	private ArticleDao articleDao;
	private ChannelDao channelDao;
	
	// 在这个方法中执行查询工作
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int offset = 0;
		int pagesize = 5;

		// 希望从request中获得offset参数
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}

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

		// 从界面中获取title参数
		String title = request.getParameter("title");

		PagerVO pv = articleDao.findArticles(title, offset, pagesize);

		request.setAttribute("pv", pv);

		// forward到article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp")
				.forward(request, response);
	}

	//用来打开添加文章的界面
	public void addInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//查出所有的频道列表
		PagerVO pv = channelDao.findChannels(0, Integer.MAX_VALUE);
		request.setAttribute("channels", pv.getDatas());
		
		// forward到添加文章页面
		request
				.getRequestDispatcher(
						"/backend/article/add_article.jsp").forward(
						request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Article a = (Article)RequestUtil.copyParam(Article.class, request);

		articleDao.addArticle(a);

		// forward到成功页面
		request
				.getRequestDispatcher(
						"/backend/article/add_article_success.jsp").forward(
						request, response);
	}

	// 打开更新界面
	public void updateInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收从界面传递过来的ID
		String id = request.getParameter("id");

		Article a = articleDao.findArticleById(Integer.parseInt(id));

		
		request.setAttribute("article", a);

		//查出所有的频道列表
		PagerVO pv = channelDao.findChannels(0, Integer.MAX_VALUE);
		request.setAttribute("channels", pv.getDatas());		
		
		// forward到更新页面
		request.getRequestDispatcher("/backend/article/update_article.jsp")
				.forward(request, response);
	}

	// 执行更新操作
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Article a = (Article)RequestUtil.copyParam(Article.class, request);
		articleDao.updateArticle(a);

		// forward到更新成功的页面
		request.getRequestDispatcher(
				"/backend/article/update_article_success.jsp").forward(request,
				response);
	}

	// 执行删除操作
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 从界面接收ID参数
		String[] ids = request.getParameterValues("id");

		if (ids == null) {
			// 提示错误(forward到错误页面)
			request.setAttribute("error", "无法删除文章，ID不允许为空");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(
					request, response);
			return;
		}
		articleDao.delArticles(ids);

		// 转向列表页面
		// request.getRequestDispatcher("/backend/ArticleServlet").forward(request,
		// response);
		response.sendRedirect(request.getContextPath()
				+ "/backend/ArticleServlet");
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

}
