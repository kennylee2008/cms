package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.service.impl.SpiderServiceImpl;
import cn.com.leadfar.cms.backend.vo.PagerVO;



public class SpiderServlet extends BaseServlet {

	private ArticleDao articleDao;
	private ChannelDao channelDao;
	
	//�����������ռ�����
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ������е�Ƶ���б�
		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
		PagerVO pv = channelDao.findChannels();
		request.setAttribute("channels", pv.getDatas());
		
		request.getRequestDispatcher("/backend/spider/index.jsp").forward(request, response);
	}
	
	//�������µ��ռ�
	public void collect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//����URL��ַ����Ƶ��ID�б�
		String url = request.getParameter("url");
		String[] channelIds = (String[])request.getParameterMap().get("channelIds");
		
		SpiderServiceImpl ssi = new SpiderServiceImpl();
		ssi.setArticleDao(articleDao);
		
		List<Article> articles = ssi.collect(url, channelIds);
		
		request.setAttribute("articles", articles);
		
		request.getRequestDispatcher("/backend/spider/spider_result.jsp").forward(request, response);
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
}
