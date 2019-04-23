package cn.com.leadfar.cms.backend.dao;

import java.util.List;

import cn.com.leadfar.cms.backend.model.Comment;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public interface CommentDao {
	public void addComment(Comment c);
	public void updateComment(Comment c);
	public void delComments(String[] ids);
	public Comment findCommentById(int id);
	public List findCommentsByArticleId(int articleId);
	public PagerVO findAllComments();
}
