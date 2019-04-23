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
		String checkcode = request.getParameter("checkcode"); //用户输入的验证码
		
		//系统判断验证码是否正确
		//刚刚生成的验证码串
		String sessionCodes = (String)request.getSession().getAttribute("codes");
		
//		if(!sessionCodes.equalsIgnoreCase(checkcode)){
//			//重定向[forward]到登录页面
//			request.setAttribute("error", "验证码错误");
//			request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
//			return;
//		} 
		//系统判断用户名是否存在，判断密码是否正确
		String sql = "select * from t_admin where username = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//判断密码是否正确
				String pass = rs.getString("password");
				if(!password.equals(pass)){
					//forward到long.jsp，并且提示“用户密码不正确”
					request.setAttribute("error", "用户【"+username+"】的密码不正确，请重试");
					request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
					return;
				}
			}else{
				//forward到long.jsp，并且提示“用户名不存在”
				request.setAttribute("error", "用户【"+username+"】不存在");
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
		
		//需要把登录用户的信息存入HTTP SESSION
		request.getSession().setAttribute("LOGIN_ADMIN", username);
		
		//判断都通过了，转向后台管理主页面
		response.sendRedirect(request.getContextPath()+"/backend/main.jsp");
		
	}

}
