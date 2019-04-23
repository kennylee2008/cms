package cn.com.leadfar.cms.backend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.leadfar.cms.utils.DBUtil;

public class FindPaginatedArticleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//要从第几条记录开始查询
		int offset = 5;
		
		//一次性最多查多少条记录（即每页最多显示多少行）
		int pagesize = 5;
		
		//进行分页处理的SQL语句
		String sql = "select * from t_article limit ?,?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, pagesize);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				System.out.println(id+","+title+","+content);
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
	}

}
