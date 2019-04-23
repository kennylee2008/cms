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
	public PagerVO findArticles(Channel c);
	public List findArticles(Channel c,int max);
	public List findHeadLine(int max);
	
	public List findRecommend(int max);
	
	/**
	 * 分页查询所有被推荐的文章列表 
	 * @return
	 */
	public PagerVO findRecommend();
	
	public void updateArticle(Article a);
}
