package cn.com.leadfar.cms.backend.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.DBUtil;

public class ArticleDaoImpl implements ArticleDao{

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

	public void delArticles(String[] ids) {
		//从数据库中删除这条记录
		String sql = "delete from t_article where id = ?";
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
	}

	public Article findArticleById(int id) {
		//从数据库中根据ID，查询相应的文章信息
		String sql = "select * from t_article where id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article a = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				a = new Article();
				a.setId(rs.getInt("id"));
				a.setSource(rs.getString("source"));
				a.setTitle(rs.getString("title"));
				a.setContent(rs.getString("content"));
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
		return a;
	}

	public PagerVO findArticles(String title, int offset, int pagesize) {
		
		//查询文章列表
		List articles = new ArrayList();
		String sql = "select * from t_article limit ?,?";
		if(title != null){
			sql = "select * from t_article where title like '%"+title+"%' limit ?,?";
		}
		
		String sqlForTotal = "select count(*) from t_article";
		if(title != null){
			sqlForTotal = "select count(*) from t_article where title like '%"+title+"%'";
		}
		
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtForTotal = null;
		ResultSet rs = null;
		ResultSet rsForTotal = null;
		int total = 0;
		try {
			
			//查询总记录数
			pstmtForTotal = conn.prepareStatement(sqlForTotal);
			rsForTotal = pstmtForTotal.executeQuery();
			if(rsForTotal.next()){
				total = rsForTotal.getInt(1);
			}
			
			//查询当前页的数据
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, pagesize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setContent(rs.getString("content"));
				a.setCreateTime(rs.getTimestamp("createtime"));
				a.setUpdateTime(rs.getTimestamp("updatetime"));
				a.setDeployTime(rs.getTimestamp("deploytime"));
				articles.add(a);
			}
			
			//conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//DBUtil.rollback(conn);
		} finally{
			DBUtil.close(rsForTotal);
			DBUtil.close(pstmtForTotal);
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		PagerVO pv = new PagerVO();
		pv.setTotal(total);
		pv.setDatas(articles);
		
		return pv;
	}

	public void updateArticle(Article a) {
		//用类似下面的SQL语句更新对应的文章
		//update t_article set title=?,source=?,content=?,updateTime=? where id=?
		String sql = "update t_article set title=?,source=?,content=?,updateTime=? where id=?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getTitle());
			pstmt.setString(2, a.getSource());
			pstmt.setString(3, a.getContent());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(5, a.getId());
			
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
