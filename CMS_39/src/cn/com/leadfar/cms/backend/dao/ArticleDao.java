package cn.com.leadfar.cms.backend.dao;

import java.util.List;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public interface ArticleDao {
	public void addArticle(Article a);
	public void delArticles(String[] ids);
	public Article findArticleById(int id);
	public PagerVO findArticles(String title);
	public List findArticles(Channel c,int max);
	public List findHeadLine(int max);
	public List findRecommend(int max);
	public void updateArticle(Article a);
}
