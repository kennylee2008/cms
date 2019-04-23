package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.MemberDao;

public class MemberAdminServlet extends BaseServlet {

	private MemberDao memberDao;
	
	//分页列出所有的会员
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("pv",memberDao.findAllMembers());
		
		request.getRequestDispatcher("/backend/member/member_list.jsp").forward(request, response);
	}
	
	//删除会员
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
		
		memberDao.delMembers(ids);
		
		response.sendRedirect(request.getContextPath()+"/backend/MemberAdminServlet");
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
