package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;
import cn.com.leadfar.cms.backend.dao.impl.ChannelDaoImpl;

public class BeanFactoryImplForMySql implements BeanFactory{

	public ArticleDao getArticleDao() {

		return new ArticleDaoImpl();
	}

	public ChannelDao getChannelDao() {
		return new ChannelDaoImpl();
	}

}
