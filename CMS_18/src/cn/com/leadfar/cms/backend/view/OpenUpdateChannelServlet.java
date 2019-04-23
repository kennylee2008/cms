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

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.utils.DBUtil;

public class OpenUpdateChannelServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���մӽ��洫�ݹ�����ID
		String id = request.getParameter("id");
		
		//�����ݿ��и���ID����ѯ��Ӧ��Ƶ����Ϣ
		String sql = "select * from t_channel where id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if(rs.next()){
				Channel c = new Channel();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				request.setAttribute("channel", c);
			}
			
			//conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//DBUtil.rollback(conn);
		} finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		//forward������ҳ��
		request.getRequestDispatcher("/backend/channel/update_channel.jsp").forward(request, response);
	}

}
