package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.utils.DBUtil;

public class DelChannelsServlet extends HttpServlet {

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

		//�ӽ������ID����
		String[] ids = request.getParameterValues("id");
		
		if(ids == null){
			//��ʾ����(forward������ҳ��)
			request.setAttribute("error", "�޷�ɾ��Ƶ����ID������Ϊ��");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
			return;
		}
		//�����ݿ���ɾ��������¼
		String sql = "delete from t_channel where id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			for(int i=0; i<ids.length; i++){
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(ids[i]));
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally{
			//DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}		
		
		//ת���б�ҳ��
		request.getRequestDispatcher("/backend/SearchChannelsServlet").forward(request, response);
	}

}
