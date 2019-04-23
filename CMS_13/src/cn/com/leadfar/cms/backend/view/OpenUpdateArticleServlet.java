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
	 * 打开更新文章的界面
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//接收从界面传递过来的ID
		String id = request.getParameter("id");
		
		//从数据库中根据ID，查询相应的文章信息
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
		
		//forward到更新页面
		request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request, response);
	}

}
