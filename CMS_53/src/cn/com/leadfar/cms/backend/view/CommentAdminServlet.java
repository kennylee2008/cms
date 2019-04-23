package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.CommentDao;

public class CommentAdminServlet extends BaseServlet {

	private CommentDao commentDao;
	
	//��ѯ�����е�����
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pv",commentDao.findAllComments());
		request.getRequestDispatcher("/backend/comment/comment_list.jsp")
			.forward(request, response);
	}
	
	//ɾ����������
	public void del(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// �ӽ������ID����
		String[] ids = request.getParameterValues("id");

		if (ids == null) {
			// ��ʾ����(forward������ҳ��)
			request.setAttribute("error", "�޷�ɾ�����£�ID������Ϊ��");
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
