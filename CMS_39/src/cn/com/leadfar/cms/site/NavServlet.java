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
	
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//��ѯ����Ƶ���б�
		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
		request.setAttribute("channels",channelDao.findChannels().getDatas());
		
		//��ѯ��ҳͷ��2ƪ����
		request.setAttribute("headline", articleDao.findHeadLine(2));
		
		//��ѯ�Ƽ��Ķ������µ�10ƪ����
		request.setAttribute("recommend", articleDao.findRecommend(10));
		
		//��ѯ���·����10ƪ����
		SystemContext.setOffset(0);
		SystemContext.setPagesize(10);
		request.setAttribute("latest", articleDao.findArticles(null).getDatas());
		
		//��ѯƵ��idΪ1������10ƪ����
		Channel c = new Channel();
		c.setId(1);
		request.setAttribute("c1", articleDao.findArticles(c, 10));
		
		//��ѯƵ��idΪ2������10ƪ����
		c = new Channel();
		c.setId(2);
		request.setAttribute("c2", articleDao.findArticles(c, 10));
		
		//��ѯƵ��idΪ3������10ƪ����
		c = new Channel();
		c.setId(3);
		request.setAttribute("c3", articleDao.findArticles(c, 10));
		
		//��ѯƵ��idΪ4������10ƪ����
		c = new Channel();
		c.setId(4);
		request.setAttribute("c4", articleDao.findArticles(c, 10));
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	//����Ƶ������ҳ
	public void channelList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		//TODO ����Ƶ����ID����ѯ��Ƶ������������б����ݵ�JSP
		Channel c = new Channel();
		c.setId(Integer.parseInt(channelId));
		request.setAttribute("c1", articleDao.findArticles(c, 20));		
		
		request.getRequestDispatcher("/channel.jsp").forward(request, response);
	}	

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
