package cn.com.leadfar.cms.backend.dao.impl;

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

public class ArticleDaoForMyBatisImpl implements ArticleDao {

	public void addArticle(Article a) {
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
		SqlSession session = MyBatisUtil.getSession();
		Article a = null;
		try {
			
			a = (Article)session.selectOne(Article.class.getName()+".findById", id);
			
			//�ύ����
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
		return a;
	}

	public PagerVO findArticles(String title, int offset, int pagesize) {

		SqlSession session = MyBatisUtil.getSession();
		List datas = null;
		int total = 0;
		try {
			
			Map params = new HashMap();
			if(title != null){
				params.put("title", "%"+title+"%");
			}
			params.put("offset", offset);
			params.put("pagesize", pagesize);
			
			//��ѯ��ǰҳ����
			datas = session.selectList(Article.class.getName()+".findArticleByTitle", params);
			
			//��ѯ�ܼ�¼��
			total = (Integer)session.selectOne(Article.class.getName()+".findArticleByTitle-count", params);
			
			//�ύ����
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
		
		PagerVO pv = new PagerVO();
		pv.setDatas(datas);
		pv.setTotal(total);
		
		return pv;
	}

	public PagerVO findArticles(Channel c, int offset, int pagesize) {
		SqlSession session = MyBatisUtil.getSession();
		List datas = null;
		int total = 0;
		try {
			
			Map params = new HashMap();
			params.put("c", c);
			params.put("offset", offset);
			params.put("pagesize", pagesize);
			
			//��ѯ��ǰҳ����
			datas = session.selectList(Article.class.getName()+".findArticleByChannel", params);
			
			//��ѯ�ܼ�¼��
			total = (Integer)session.selectOne(Article.class.getName()+".findArticleByChannel-count", params);
			
			//�ύ����
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}
		
		PagerVO pv = new PagerVO();
		pv.setDatas(datas);
		pv.setTotal(total);
		
		return pv;
	}

	public void updateArticle(Article a) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			session.update(Article.class.getName()+".update", a);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

}
