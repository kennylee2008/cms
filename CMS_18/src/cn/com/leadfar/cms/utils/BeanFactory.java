package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;

public class BeanFactory {
	public static ArticleDao getArticleDao(){
		return new ArticleDaoImpl();
	}
}
