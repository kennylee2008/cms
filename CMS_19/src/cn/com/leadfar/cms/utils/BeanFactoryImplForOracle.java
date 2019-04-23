package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImplForOracle;

public class BeanFactoryImplForOracle implements BeanFactory{

	public ArticleDao getArticleDao() {
		return new ArticleDaoImplForOracle();
	}

}
