package cn.com.leadfar.cms.site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.view.BaseServlet;
import cn.com.leadfar.cms.utils.RequestUtil;

public class MemberServlet extends BaseServlet {

	private MemberDao memberDao;
	
	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Member m = loginMember(request);
		if(m == null){
			request.setAttribute("error", "您尚未登录无法操作");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		//TODO 根据登录会员，查询会员的更加详细的信息，比如上次登录的时间等等
		
		request.setAttribute("member", m);
		
		request.getRequestDispatcher("/member/index.jsp").forward(request, response);
	}
	
	//打开会员注册页面
	public void regInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		request.getRequestDispatcher("/member/reg_input.jsp").forward(request, response);
	}	
	
	//会员注册
	public void reg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member)RequestUtil.copyParam(Member.class, request);
		
		try{
			memberDao.addMember(member);
		}catch(Exception e){
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/member/reg_input.jsp").forward(request, response);
			return;
		}
		request.setAttribute("success", "注册会员成功，请重新登录！");
		request.getRequestDispatcher("/common/success.jsp").forward(request, response);
	}
	
	//打开更新个人信息的界面
	public void updateInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Member m = loginMember(request);
		if(m == null){
			request.setAttribute("error", "您尚未登录无法操作");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		m = memberDao.findMemberById(m.getId());
		
		request.setAttribute("member", m);
		
		request.getRequestDispatcher("/member/update_input.jsp").forward(request, response);
	}
	
	//更新个人信息
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Member m = loginMember(request);
		if(m == null){
			request.setAttribute("error", "您尚未登录无法操作");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		
		Member member = (Member)RequestUtil.copyParam(Member.class, request);
		
		memberDao.updateMember(member);
		
		request.setAttribute("success", "更新个人信息成功！");
		
		//更新一下session中的member对象，以便在页面上能够马上看到当前登录用户显示姓名的改变
		m.setName(member.getName());
		
		request.getRequestDispatcher("/common/success.jsp").forward(request, response);
	}
	
	//打开更新个人密码界面
	public void updatePasswordInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Member m = loginMember(request);
		if(m == null){
			request.setAttribute("error", "您尚未登录无法操作");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		m = memberDao.findMemberById(m.getId());
		
		request.setAttribute("member", m);
		
		request.getRequestDispatcher("/member/update_password_input.jsp").forward(request, response);
	}
	
	public void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Member m = loginMember(request);
		if(m == null){
			request.setAttribute("error", "您尚未登录无法操作");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		
		String id = request.getParameter("id");
		String oldPass = request.getParameter("oldPassword");
		String newPass = request.getParameter("newPassword");
		
		try{
			memberDao.updatePassword(Integer.parseInt(id), oldPass, newPass);
		}catch(Exception e){
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		request.setAttribute("success", "更新密码成功！");
		request.getRequestDispatcher("/common/success.jsp").forward(request, response);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
