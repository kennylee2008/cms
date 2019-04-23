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

	//��ѯĳ��Ƶ������������б���ʾ����ҳ����
	public void indexChannelArticleList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		//����Ƶ����ID����ѯ��Ƶ������������б����ݵ�JSP
		Channel c = channelDao.findChannelById(Integer.parseInt(channelId));
		request.setAttribute("channel", c);
		request.setAttribute("articles", articleDao.findArticles(c, 10));		
		
		request.getRequestDispatcher("/portlet/index_channel_article_list.jsp").include(request, response);
	}	
	
	//��ѯ������Ƶ������Ϊ��ҳͷ���ĵ�����
	public void navList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
		request.setAttribute("channels",channelDao.findChannels().getDatas());		
		
		request.getRequestDispatcher("/portlet/channel_list.jsp").include(request, response);
	}	
	
	//��ѯ����վ��ҳ����ʾ��ͷ������
	public void headline(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//��ѯ��ҳͷ��2ƪ����
		request.setAttribute("headline", articleDao.findHeadLine(2));
		
		request.getRequestDispatcher("/portlet/headline.jsp").include(request, response);
	}	
	
	//��ѯ�Ƽ��Ķ��������б�
	public void recommend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//��ѯ�Ƽ��Ķ������µ�10ƪ����
		request.setAttribute("recommend", articleDao.findRecommend(10));
		
		request.getRequestDispatcher("/portlet/recommend.jsp").include(request, response);
	}
	
	//��ѯ���·���������б�
	public void latest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		Channel c = null;
		if(channelId != null){
			c = new Channel();
			c.setId(Integer.parseInt(channelId));
		}
		
		//��ѯ���·����10ƪ����
		request.setAttribute("latest", articleDao.findArticles(c,10));	
		
		request.getRequestDispatcher("/portlet/latest.jsp").include(request, response);
	}
	
	//��ѯ��ĳ��Ƶ���ϵ��������£�����ҳ��ʾ
	public void channelIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		Channel c = null;
		if(channelId != null){
			c = channelDao.findChannelById(Integer.parseInt(channelId));
			request.setAttribute("channel", c);
		}
		
		//��ѯ���·��������
		request.setAttribute("pv", articleDao.findArticles(c));	
		
		request.getRequestDispatcher("/portlet/channel_index.jsp").include(request, response);
	}
	
	//��ҳ��ѯ���б��Ƽ��������б�
	public void recommendIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//��ѯ���·��������
		request.setAttribute("pv", articleDao.findRecommend());	
		
		request.getRequestDispatcher("/portlet/recommend_index.jsp").include(request, response);
	}	
	
	//�������µ�ID����ѯ��ĳƪ������
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
