package cn.com.leadfar.cms.backend.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
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
				session.delete(Article.class.getName()+".del", Integer.parseInt(id));
				session.delete(Article.class.getName()+".del_channel_article", Integer.parseInt(id));
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

	public PagerVO findArticles(String title, int offset, int pagesize) {

		Map params = new HashMap();
		if(title != null){
			params.put("title", "%"+title+"%");
		}
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		return findPaginated(Article.class.getName()+".findArticleByTitle", params);
	}

	public PagerVO findArticles(Channel c, int offset, int pagesize) {
		
		Map params = new HashMap();
		params.put("c", c);
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		
		return findPaginated(Article.class.getName()+".findArticleByChannel", params);
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
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

}
