package cn.com.leadfar.cms.backend.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.DBUtil;

public class ArticleDaoImplForOracle extends ArticleDaoImpl{
	
	public void addArticle(Article a){
		String sql = "insert into t_article (title,source,content,createtime) values (?,?,?,?)";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getTitle());
			pstmt.setString(2, a.getSource());
			pstmt.setString(3, a.getContent());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
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
	}
}
