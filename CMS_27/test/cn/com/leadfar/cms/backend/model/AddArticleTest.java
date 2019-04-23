package cn.com.leadfar.cms.backend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import cn.com.leadfar.cms.utils.DBUtil;

public class AddArticleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sql = "insert into t_article (title,content,createtime) values (?,?,?)";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			for(int i=0; i<100; i++){
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "测试文章"+Math.random());
				pstmt.setString(2, "这是文章的内容。。。。。。。。"+i);
				pstmt.setTimestamp(3, new java.sql.Timestamp( new Date().getTime()));
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

}
