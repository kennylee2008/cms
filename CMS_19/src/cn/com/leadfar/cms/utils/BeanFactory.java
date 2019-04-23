package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;

public interface BeanFactory {
	public ArticleDao getArticleDao();
}
