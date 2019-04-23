package cn.com.leadfar.cms.backend.dao.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Attachment;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.model.Comment;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class ArticleDaoForMyBatisImpl extends BaseDao implements ArticleDao {

	public void addArticle(Article a) {
		a.setCreateTime(new Date());
		//打开一个session
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			//插入
			session.insert(Article.class.getName()+".add", a);
			
			//考虑channel
			Set<Channel> channels = a.getChannels();
			if(channels != null){
				for(Channel c:channels){
					Map params = new HashMap();
					params.put("a", a);
					params.put("c", c);
					session.insert(Article.class.getName()+".insert_channel_article", params);
				}
			}
			
			//插入文章和关键字的关联表
			if(a.getKeyword() != null && !a.getKeyword().trim().equals("")){
				String keyword = a.getKeyword();
				String[] keywords = keyword.split(",| ");//按照空格或逗号隔开
				for(String kw:keywords){
					Map params = new HashMap();
					params.put("articleId", a.getId());
					params.put("keyword", kw);
					session.insert(Article.class.getName()+".insert_article_keyword", params);
				}
			}
			
			//插入文章的附件信息
			if(a.getAttachments() != null){
				List attachments = a.getAttachments();
				for (Iterator iterator = attachments.iterator(); iterator
						.hasNext();) {
					Attachment attachment = (Attachment) iterator.next();
					attachment.setArticleId(a.getId());
					session.insert(Article.class.getName()+".insert_attachment", attachment);
				}
			}
			
			//提交事务
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//关闭session
			session.close();
		}
	}

	public void delArticles(String[] ids) {
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			for(String id:ids){
				int articleId = Integer.parseInt(id);

				//删除文章和频道之间的关联信息
				session.delete(Article.class.getName()+".del_channel_article", articleId);
				
				//删除文章和关键字之间的关联信息
				session.delete(Article.class.getName()+".del_article_keyword", articleId);
				
				//删除本篇文章的所有对应的留言
				session.delete(Comment.class.getName()+".delCommentByArticleId", articleId);
				
				//删除本篇文章对应的所有附件
				Article a = (Article)session.selectOne(Article.class.getName()+".findById", articleId);
				List attachments = a.getAttachments();
				for (Iterator iterator = attachments.iterator(); iterator
						.hasNext();) {
					Attachment attachment = (Attachment) iterator.next();
					String realPath = Attachment.ATTACHMENT_DIR+attachment.getName();
					new File(realPath).delete(); //删除磁盘上的文件
				}
				//删除数据库中的相关记录
				session.delete(Article.class.getName()+".del_attachments_by_articleId", articleId);
				
				//删除文章
				session.delete(Article.class.getName()+".del", articleId);
				
			}
			//提交事务
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//关闭session
			session.close();
		}
	}

	@Override
	public void delAttachment(int attachmentId) {
		SqlSession session = MyBatisUtil.getSession();
		
		try {
			
			//首先查出Attachment对象
			Attachment attachment = (Attachment)session.selectOne(Article.class.getName()+".findAttachmentById", attachmentId);
			
			//删除磁盘上的文件
			String realPath = Attachment.ATTACHMENT_DIR+attachment.getName();
			new File(realPath).delete(); //删除磁盘上的文件
			
			//删除Attachemnt记录
			session.delete(Article.class.getName()+".del_attachment_by_Id", attachmentId);
			
			//提交事务
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//关闭session
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
			//关闭session
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
			//关闭session
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
			
			//先找出相关文章的ID列表
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
				//继续查文章列表
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
			//关闭session
			session.close();
		}
		return null;
	}

	public void updateArticle(Article a) {
		a.setUpdateTime(new Date());
		SqlSession session = MyBatisUtil.getSession();
		try {
			//更新文章的基本信息
			session.update(Article.class.getName()+".update", a);
			
			//删除文章和频道之间的关联
			session.delete(Article.class.getName()+".del_channel_article", a.getId());
			
			//插入文章和频道之间的关联
			Set<Channel> channels = a.getChannels();
			if(channels != null){
				for(Channel c:channels){
					Map params = new HashMap();
					params.put("a", a);
					params.put("c", c);
					session.insert(Article.class.getName()+".insert_channel_article", params);
				}
			}
			
			//删除文章和关键字之间的关联
			session.delete(Article.class.getName()+".del_article_keyword", a.getId());
			
			//插入新的文章和关键字之间的关联
			//插入文章和关键字的关联表
			if(a.getKeyword() != null && !a.getKeyword().trim().equals("")){
				String keyword = a.getKeyword();
				String[] keywords = keyword.split(",| ");//按照空格或逗号隔开
				for(String kw:keywords){
					Map params = new HashMap();
					params.put("articleId", a.getId());
					params.put("keyword", kw);
					session.insert(Article.class.getName()+".insert_article_keyword", params);
				}
			}
			
			//插入文章的附件信息
			if(a.getAttachments() != null){
				List attachments = a.getAttachments();
				for (Iterator iterator = attachments.iterator(); iterator
						.hasNext();) {
					Attachment attachment = (Attachment) iterator.next();
					attachment.setArticleId(a.getId());
					session.insert(Article.class.getName()+".insert_attachment", attachment);
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
