package cn.com.leadfar.cms.site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.view.BaseServlet;

public class NavServlet extends BaseServlet {

	private ChannelDao channelDao;
	private ArticleDao articleDao;

	//查询某个频道下面的文章列表，显示在首页上面
	public void indexChannelArticleList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		//根据频道的ID，查询出频道下面的文章列表，传递到JSP
		Channel c = channelDao.findChannelById(Integer.parseInt(channelId));
		request.setAttribute("channel", c);
		request.setAttribute("articles", articleDao.findArticles(c, 10));		
		
		request.getRequestDispatcher("/portlet/index_channel_article_list.jsp").include(request, response);
	}	
	
	//查询出所有频道，作为网页头部的导航条
	public void navList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
		request.setAttribute("channels",channelDao.findChannels().getDatas());		
		
		request.getRequestDispatcher("/portlet/channel_list.jsp").include(request, response);
	}	
	
	//查询在网站首页上显示的头条文章
	public void headline(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//查询首页头条2篇文章
		request.setAttribute("headline", articleDao.findHeadLine(2));
		
		request.getRequestDispatcher("/portlet/headline.jsp").include(request, response);
	}	
	
	//查询推荐阅读的文章列表
	public void recommend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//查询推荐阅读的最新的10篇文章
		request.setAttribute("recommend", articleDao.findRecommend(10));
		
		request.getRequestDispatcher("/portlet/recommend.jsp").include(request, response);
	}
	
	//查询最新发表的文章列表
	public void latest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		Channel c = null;
		if(channelId != null){
			c = new Channel();
			c.setId(Integer.parseInt(channelId));
		}
		
		//查询最新发表的10篇文章
		request.setAttribute("latest", articleDao.findArticles(c,10));	
		
		request.getRequestDispatcher("/portlet/latest.jsp").include(request, response);
	}
	
	//查询出某个频道上的所有文章，并分页显示
	public void channelIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		Channel c = null;
		if(channelId != null){
			c = channelDao.findChannelById(Integer.parseInt(channelId));
			request.setAttribute("channel", c);
		}
		
		//查询最新发表的文章
		request.setAttribute("pv", articleDao.findArticles(c));	
		
		request.getRequestDispatcher("/portlet/channel_index.jsp").include(request, response);
	}
	
	//分页查询所有被推荐的文章列表
	public void recommendIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//查询最新发表的文章
		request.setAttribute("pv", articleDao.findRecommend());	
		
		request.getRequestDispatcher("/portlet/recommend_index.jsp").include(request, response);
	}	
	
	//根据文章的ID，查询出某篇文章来
	public void articleDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String articleId = request.getParameter("articleId");
		
		request.setAttribute("article",
				articleDao.findArticleById(Integer.parseInt(articleId)));
		
		request.getRequestDispatcher("/portlet/article_detail.jsp").include(request, response);
	}	

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
