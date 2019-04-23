package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.DBUtil;

public class OpenUpdateArticleServlet extends HttpServlet {

	/**
	 * �򿪸������µĽ���
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���մӽ��洫�ݹ�����ID
		String id = request.getParameter("id");
		
		//�����ݿ��и���ID����ѯ��Ӧ��������Ϣ
		String sql = "select * from t_article where id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if(rs.next()){
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setSource(rs.getString("source"));
				a.setTitle(rs.getString("title"));
				a.setContent(rs.getString("content"));
				request.setAttribute("article", a);
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
		request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request, response);
	}

}
