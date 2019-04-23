package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImplForOracle;
import cn.com.leadfar.cms.backend.dao.impl.ChannelDaoImplForOracle;

public class BeanFactoryImplForOracle implements BeanFactory{

	public ArticleDao getArticleDao() {
		return new ArticleDaoImplForOracle();
	}

	public ChannelDao getChannelDao() {
		return new ChannelDaoImplForOracle();
	}

}
