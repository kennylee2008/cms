package cn.com.leadfar.cms.backend.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.DBUtil;

public class SearchArticlesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//查询文章列表
		List articles = new ArrayList();
		
		String sql = "select * from t_article";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setContent(rs.getString("content"));
				a.setCreateTime(rs.getTimestamp("createtime"));
				
				articles.add(a);
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
		request.setAttribute("articles", articles);
		
		//forward到article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}

}
