package cn.com.leadfar.cms.site;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;
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
	
	//查询出与某篇文章相关的其它文章
	public void keywords(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String articleId = request.getParameter("articleId");
		
		Article a = articleDao.findArticleById(Integer.parseInt(articleId));
		
		request.setAttribute("pv", articleDao.findArticlesByKeyword(a.getKeyword()));
		
		request.getRequestDispatcher("/portlet/keywords.jsp").include(request, response);
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
	
	//更新文章点击量记录，在一个小时之内同一个IP的连续点击，将不重复计算点击量！
	public void click(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String articleId = request.getParameter("articleId");
		
		//首先，从ServletContext中取出一个Map
		Map visitors = (Map)getServletContext().getAttribute("visitors");
		if(visitors == null){
			visitors = new ConcurrentHashMap();
			getServletContext().setAttribute("visitors", visitors);
		}
		
		//得到客户端IP地址
		String clickIp = request.getRemoteAddr();
		
		String key = articleId+"_"+clickIp; //以文章ID和用户IP为键
		
		Date lastVisitTime = (Date)visitors.get(key);
		Article a = articleDao.findArticleById(Integer.parseInt(articleId));
		int clickNumber = a.getClickNumber(); //旧的点击量
		/**
		 * 没有访问记录、或最后一次访问在一个小时之前，需再次记录访问量
		 * 否则，无需再次记录访问量
		 */
		if(lastVisitTime == null || !withinOneHour(lastVisitTime)){
			//更新点击量
			clickNumber = articleDao.updateClickNumber(Integer.parseInt(articleId));
			visitors.put(key, new Date());
		}

		response.setContentType("text/javascript");
		response.getWriter().println("document.write('"+clickNumber+"')");
	}
	
	/**
	 * 将lastVisitTime和现在的时间相比，判断其是否在一个小时之内
	 * @param lastVisitTime
	 * @return
	 */
	private boolean withinOneHour(Date lastVisitTime){
		//现在的时间
		Calendar now = Calendar.getInstance();
		
		//上次访问时间
		Calendar last = Calendar.getInstance();
		last.setTime(lastVisitTime);
		
		last.add(Calendar.HOUR_OF_DAY, 1);
		
		if(last.before(now)){
			return false;
		}
		return true;
	}
	

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
