package cn.com.leadfar.cms.backend.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.DBUtil;

public class ArticleDaoImpl implements ArticleDao{

	public void addArticle(Article a){
		String sql = "insert into t_article (" +
				"title,source,content,author,keyword,intro,type,recommend,headline,topicId,createTime,updateTime,adminId) " +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlForChannel = "insert into t_channel_article (channelId,articleId) values (?,?)";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtForChannel = null;
		//ResultSet rs = null;
		try {
			//插入文章信息
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, a.getTitle());
			pstmt.setString(2, a.getSource());
			pstmt.setString(3, a.getContent());
			pstmt.setString(4, a.getAuthor());
			pstmt.setString(5, a.getKeyword());
			pstmt.setString(6, a.getIntro());
			pstmt.setString(7, a.getType());
			pstmt.setBoolean(8, a.isRecommend());
			pstmt.setBoolean(9, a.isHeadline());
			pstmt.setInt(10, a.getTopicId());
			pstmt.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(13, a.getAdminId());
			pstmt.executeUpdate();
			
			//获得刚刚插入的记录的ID
			ResultSet newId = pstmt.getGeneratedKeys();
			if(newId.next()){
				a.setId(newId.getInt(1));
			}
			
			//插入文章与频道之间的关联信息
			Set<Channel> channels = a.getChannels();
			if(channels != null){
				for(Channel c:channels){
					pstmtForChannel = conn.prepareStatement(sqlForChannel);
					pstmtForChannel.setInt(1, c.getId());
					pstmtForChannel.setInt(2, a.getId());
					pstmtForChannel.executeUpdate();
				}
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally{
			DBUtil.close(pstmtForChannel);
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

	public PagerVO findArticles(Channel c, int offset, int pagesize) {
		// TODO Auto-generated method stub
		return null;
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
