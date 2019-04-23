package cn.com.leadfar.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.MemberDao;

public class MemberAdminServlet extends BaseServlet {

	private MemberDao memberDao;
	
	//��ҳ�г����еĻ�Ա
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("pv",memberDao.findAllMembers());
		
		request.getRequestDispatcher("/backend/member/member_list.jsp").forward(request, response);
	}
	
	//ɾ����Ա
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
		
		memberDao.delMembers(ids);
		
		response.sendRedirect(request.getContextPath()+"/backend/MemberAdminServlet");
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
