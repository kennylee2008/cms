package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.utils.DBUtil;

public class UpdateArticleServlet extends HttpServlet {

	/**
	 * 更新文章的功能
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//首先，从界面接收文章的基本信息（包括：ID、标题等等）
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String source = request.getParameter("source");
		String content = request.getParameter("content");
		
		//用类似下面的SQL语句更新对应的文章
		//update t_article set title=?,source=?,content=?,updateTime=? where id=?
		String sql = "update t_article set title=?,source=?,content=?,updateTime=? where id=?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, source);
			pstmt.setString(3, content);
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(5, Integer.parseInt(id));
			
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
		request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request, response);
	}

}
