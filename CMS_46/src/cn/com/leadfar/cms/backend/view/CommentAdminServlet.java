package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.CommentDao;

public class CommentAdminServlet extends BaseServlet {

	private CommentDao commentDao;
	
	//查询出所有的评论
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pv",commentDao.findAllComments());
		request.getRequestDispatcher("/backend/comment/comment_list.jsp")
			.forward(request, response);
	}
	
	//删除文章评论
	public void del(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 从界面接收ID参数
		String[] ids = request.getParameterValues("id");

		if (ids == null) {
			// 提示错误(forward到错误页面)
			request.setAttribute("error", "无法删除文章，ID不允许为空");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(
					request, response);
			return;
		}
		
		commentDao.delComments(ids);
		
		response.sendRedirect(request.getContextPath()
				+ "/backend/CommentAdminServlet");
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}	

}
