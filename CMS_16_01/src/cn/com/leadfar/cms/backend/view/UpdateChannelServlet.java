package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.utils.DBUtil;

public class UpdateChannelServlet extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//首先，从界面接收频道的基本信息（包括：ID、名称、描述）
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		//用类似下面的SQL语句更新对应的频道
		//update t_channel set name=?,description=? where id=?
		String sql = "update t_channel set name=?,description=? where id=?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setInt(3, Integer.parseInt(id));
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally{
			//DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}		
		
		//forward到更新成功的页面
		request.getRequestDispatcher("/backend/channel/update_channel_success.jsp").forward(request, response);
	}

}
