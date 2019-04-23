package cn.com.leadfar.cms.site;

import javax.servlet.jsp.PageContext;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.view.InitBeanFactoryServlet;
import cn.com.leadfar.cms.utils.BeanFactory;

public class SiteFunction {
	
	/**
	 * 根据频道ID得到频道
	 * @param pc
	 * @param channelId
	 * @return
	 */
	public static Channel findChannelById(PageContext pc,String channelId){
		BeanFactory factory = (BeanFactory)pc.getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
		ChannelDao cd = (ChannelDao)factory.getBean("channelDao");
		return cd.findChannelById(Integer.parseInt(channelId));
	}
	
	/**
	 * 根据文章ID得到Article对象
	 * @param pc
	 * @param articleId
	 * @return
	 */
	public static Article findArticleById(PageContext pc,String articleId){
		BeanFactory factory = (BeanFactory)pc.getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		return articleDao.findArticleById(Integer.parseInt(articleId));
	}
}
