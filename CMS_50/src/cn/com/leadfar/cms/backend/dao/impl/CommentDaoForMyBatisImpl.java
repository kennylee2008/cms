package cn.com.leadfar.cms.backend.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.dao.CommentDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Comment;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class CommentDaoForMyBatisImpl extends BaseDao implements CommentDao {

	@Override
	public void addComment(Comment c) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			c.setCommentTime(new Date());
			session.insert(Comment.class.getName()+".add", c);
			
			//更新文章的留言数
			int leaveNumber = (Integer)session.selectOne(Article.class.getName()+".selectLeaveNumber", 
					c.getArticleId());
			Article a = new Article();
			a.setId(c.getArticleId());
			a.setLeaveNumber(leaveNumber + 1);
			session.update(Article.class.getName()+".updateLeaveNumber", a);
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

	/**
	 * 删除留言，也需要同时更新此留言对应的文章的留言数！
	 */
	@Override
	public void delComments(String[] ids) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			for(String id:ids){
				//首先查出此留言的信息
				Comment c = (Comment)session.selectOne(Comment.class.getName()+".findById", Integer.parseInt(id));
				
				//更新对应文章的留言数
				int leaveNumber = (Integer)session.selectOne(Article.class.getName()+".selectLeaveNumber", 
						c.getArticleId());
				Article a = new Article();
				a.setId(c.getArticleId());
				a.setLeaveNumber(leaveNumber - 1);
				session.update(Article.class.getName()+".updateLeaveNumber", a);
				
				//删除留言记录
				session.delete(Comment.class.getName()+".del", Integer.parseInt(id));
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
	public PagerVO findAllComments() {
		Map params = new HashMap();
		return findPaginated(Comment.class.getName()+".findPaginated", params);
	}

	@Override
	public Comment findCommentById(int id) {
		return (Comment)findById(Comment.class,id);
	}

	@Override
	public List findCommentsByArticleId(int articleId) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			
			return session.selectList(Comment.class.getName()+".findByArticleId", articleId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null;
	}

	@Override
	public void updateComment(Comment c) {
		update(c);
	}

}
