package cn.com.leadfar.cms.backend.dao;

import java.util.List;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public interface ArticleDao {
	public void addArticle(Article a);
	public void delArticles(String[] ids);
	/**
	 * 根据附件ID，删除附件！
	 * @param attachmentId
	 */
	public void delAttachment(int attachmentId);
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
	
	/**
	 * 分页查询相关文章的列表
	 * @param keywords
	 * @return
	 */
	public PagerVO findArticlesByKeyword(String keyword);
	
	public void updateArticle(Article a);
	
	/**
	 * 更新点击量，即在原来点击量的基础上，增加一次点击
	 * @param articleId
	 */
	public int updateClickNumber(int articleId);
}
