package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.utils.DBUtil;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode"); //�û��������֤��
		
		//ϵͳ�ж���֤���Ƿ���ȷ
		//�ո����ɵ���֤�봮
		String sessionCodes = (String)request.getSession().getAttribute("codes");
		
//		if(!sessionCodes.equalsIgnoreCase(checkcode)){
//			//�ض���[forward]����¼ҳ��
//			request.setAttribute("error", "��֤�����");
//			request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
//			return;
//		} 
		//ϵͳ�ж��û����Ƿ���ڣ��ж������Ƿ���ȷ
		String sql = "select * from t_admin where username = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//�ж������Ƿ���ȷ
				String pass = rs.getString("password");
				if(!password.equals(pass)){
					//forward��long.jsp��������ʾ���û����벻��ȷ��
					request.setAttribute("error", "�û���"+username+"�������벻��ȷ��������");
					request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
					return;
				}
			}else{
				//forward��long.jsp��������ʾ���û��������ڡ�
				request.setAttribute("error", "�û���"+username+"��������");
				request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		//��Ҫ�ѵ�¼�û�����Ϣ����HTTP SESSION
		request.getSession().setAttribute("LOGIN_ADMIN", username);
		
		//�ж϶�ͨ���ˣ�ת���̨������ҳ��
		response.sendRedirect(request.getContextPath()+"/backend/main.jsp");
		
	}

}
