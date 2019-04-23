package cn.com.leadfar.cms.backend.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.model.Comment;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class ArticleDaoForMyBatisImpl extends BaseDao implements ArticleDao {

	public void addArticle(Article a) {
		a.setCreateTime(new Date());
		//��һ��session
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			//����
			session.insert(Article.class.getName()+".add", a);
			
			//����channel
			Set<Channel> channels = a.getChannels();
			if(channels != null){
				for(Channel c:channels){
					Map params = new HashMap();
					params.put("a", a);
					params.put("c", c);
					session.insert(Article.class.getName()+".insert_channel_article", params);
				}
			}
			
			//�������º͹ؼ��ֵĹ�����
			if(a.getKeyword() != null && !a.getKeyword().trim().equals("")){
				String keyword = a.getKeyword();
				String[] keywords = keyword.split(",| ");//���տո�򶺺Ÿ���
				for(String kw:keywords){
					Map params = new HashMap();
					params.put("articleId", a.getId());
					params.put("keyword", kw);
					session.insert(Article.class.getName()+".insert_article_keyword", params);
				}
			}
			
			//�ύ����
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
	}

	public void delArticles(String[] ids) {
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			for(String id:ids){
				//ɾ������
				session.delete(Article.class.getName()+".del", Integer.parseInt(id));
				
				//ɾ�����º�Ƶ��֮��Ĺ�����Ϣ
				session.delete(Article.class.getName()+".del_channel_article", Integer.parseInt(id));
				
				//ɾ�����º͹ؼ���֮��Ĺ�����Ϣ
				session.delete(Article.class.getName()+".del_article_keyword", Integer.parseInt(id));
				
				//ɾ����ƪ���µ����ж�Ӧ������
				session.delete(Comment.class.getName()+".delCommentByArticleId", Integer.parseInt(id));
			}
			//�ύ����
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
	}

	public Article findArticleById(int id) {
		return (Article)findById(Article.class,id);
	}

	public PagerVO findArticles(String title) {

		Map params = new HashMap();
		if(title != null){
			params.put("title", "%"+title+"%");
		}
		return findPaginated(Article.class.getName()+".findArticleByTitle", params);
	}
	
	public PagerVO findArticles(Channel c) {
		Map params = new HashMap();
		params.put("c", c);
		return findPaginated(Article.class.getName()+".findArticleByChannel", params);
	}

	public List findArticles(Channel c, int max) {
		
		Map params = new HashMap();
		params.put("c", c);
		SystemContext.setOffset(0);
		SystemContext.setPagesize(max);
		PagerVO vo = findPaginated(Article.class.getName()+".findArticleByChannel", params);
		return vo.getDatas();
	}

	public List findHeadLine(int max) {
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			return session.selectList(Article.class.getName()+".findHeadline", max);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
		return null;
	}

	public List findRecommend(int max) {
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			return session.selectList(Article.class.getName()+".findRecommend", max);
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
		return null;
	}

	@Override
	public PagerVO findRecommend() {
		Map params = new HashMap();
		return findPaginated(Article.class.getName()+".findAllRecommend", params);
	}

	@Override
	public PagerVO findArticlesByKeyword(String keyword) {
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			
			if(keyword == null || keyword.trim().equals("")){
				return null;
			}
			
			String[] keywords = keyword.split(",| ");
			
			//���ҳ�������µ�ID�б�
			if(keywords != null && keywords.length > 0){
				StringBuffer sb = new StringBuffer();
				for(int i=0; i<keywords.length; i++){
					if(i != 0){
						sb.append(",");
					}
					sb.append("'"+keywords[i]+"'");
				}
				Map params = new HashMap();
				params.put("keywords", sb.toString());
				List articleIds = session.selectList(Article.class.getName()+".findArticlesIdByKeyword", 
						params);
				
				StringBuffer ids = new StringBuffer();
				for(int i=0; i<articleIds.size(); i++){
					if(i != 0){
						ids.append(",");
					}
					ids.append(articleIds.get(i));
				}
				
				params = new HashMap();
				params.put("ids", ids.toString());
				params.put("offset", 0);
				params.put("pagesize", Integer.MAX_VALUE);
				//�����������б�
				List articles = session.selectList(Article.class.getName()+".findArticlesByIds", 
						params);
				
				PagerVO pv = new PagerVO();
				pv.setDatas(articles);
				pv.setTotal(articleIds.size());
				return pv;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
		return null;
	}

	public void updateArticle(Article a) {
		a.setUpdateTime(new Date());
		SqlSession session = MyBatisUtil.getSession();
		try {
			//�������µĻ�����Ϣ
			session.update(Article.class.getName()+".update", a);
			
			//ɾ�����º�Ƶ��֮��Ĺ���
			session.delete(Article.class.getName()+".del_channel_article", a.getId());
			
			//�������º�Ƶ��֮��Ĺ���
			Set<Channel> channels = a.getChannels();
			if(channels != null){
				for(Channel c:channels){
					Map params = new HashMap();
					params.put("a", a);
					params.put("c", c);
					session.insert(Article.class.getName()+".insert_channel_article", params);
				}
			}
			
			//ɾ�����º͹ؼ���֮��Ĺ���
			session.delete(Article.class.getName()+".del_article_keyword", a.getId());
			
			//�����µ����º͹ؼ���֮��Ĺ���
			//�������º͹ؼ��ֵĹ�����
			if(a.getKeyword() != null && !a.getKeyword().trim().equals("")){
				String keyword = a.getKeyword();
				String[] keywords = keyword.split(",| ");//���տո�򶺺Ÿ���
				for(String kw:keywords){
					Map params = new HashMap();
					params.put("articleId", a.getId());
					params.put("keyword", kw);
					session.insert(Article.class.getName()+".insert_article_keyword", params);
				}
			}			
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

	@Override
	public int updateClickNumber(int articleId) {
		SqlSession session = MyBatisUtil.getSession();
		int clickNumber = 0;
		try {
			
			clickNumber = (Integer)session.selectOne(Article.class.getName()+".selectClickNumber", articleId);
			clickNumber = clickNumber + 1;
			Article a = new Article();
			a.setId(articleId);
			a.setClickNumber(clickNumber);
			session.update(Article.class.getName()+".updateClickNumber", a);
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
		return clickNumber;
	}

}
