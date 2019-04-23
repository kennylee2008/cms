package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;

public interface BeanFactory {
	public ArticleDao getArticleDao();
	public ChannelDao getChannelDao();
	
}