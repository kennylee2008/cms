package cn.com.leadfar.cms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConn(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2.创建数据库连接
		//url:数据库连接协议
		
		String url="jdbc:mysql://localhost/cms";
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(url, "root", "leadfar");
			conn.setAutoCommit(false);//表示关闭事务自动提交
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static  void close(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt){
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
