package cn.com.leadfar.cms.backend.dao;

import java.util.List;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public interface ArticleDao {
	public void addArticle(Article a);
	public void delArticles(String[] ids);
	/**
	 * ���ݸ���ID��ɾ��������
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
	 * ��ҳ��ѯ���б��Ƽ��������б�
	 * @return
	 */
	public PagerVO findRecommend();
	
	/**
	 * ��ҳ��ѯ������µ��б�
	 * @param keywords
	 * @return
	 */
	public PagerVO findArticlesByKeyword(String keyword);
	
	public void updateArticle(Article a);
	
	/**
	 * ���µ����������ԭ��������Ļ����ϣ�����һ�ε��
	 * @param articleId
	 */
	public int updateClickNumber(int articleId);
}
