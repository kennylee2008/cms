package cn.com.leadfar.cms.site;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.CommentDao;
import cn.com.leadfar.cms.backend.model.Comment;
import cn.com.leadfar.cms.backend.view.BaseServlet;
import cn.com.leadfar.cms.utils.RequestUtil;

/**
 * 文章评论留言管理
 * @author Lee
 *
 */
public class CommentServlet extends BaseServlet {

	private CommentDao commentDao;
	
	//列出某篇文章的评论列表
	public void comments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String articleId = request.getParameter("articleId");
		List comments = commentDao.findCommentsByArticleId(Integer.parseInt(articleId));
		request.setAttribute("comments", comments);
		
		request.getRequestDispatcher("/portlet/comment_list.jsp").include(request, response);
	}

	//添加某篇文章的评论
	public void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Comment c = (Comment)RequestUtil.copyParam(Comment.class, request);
		
		commentDao.addComment(c);
		
		String ref = request.getHeader("referer");
		
		response.sendRedirect(ref);
	}
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
}
