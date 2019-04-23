package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public interface ArticleDao {
	public void addArticle(Article a);
	public void delArticles(String[] ids);
	public Article findArticleById(int id);
	public PagerVO findArticles(String title,int offset,int pagesize);
	public PagerVO findArticles(Channel c,int offset,int pagesize);
	public void updateArticle(Article a);
}
