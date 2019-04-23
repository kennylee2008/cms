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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int offset = 0;
		int pagesize = 5;
		
		//ϣ����request�л��offset����
		try{
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		}catch(Exception ignore){}
		
		//�����request���ݹ�����pagesize��������ô����Ҫ����http session�е�pagesize��ֵ
		if(request.getParameter("pagesize") != null){
			request.getSession().setAttribute("pagesize",
					Integer.parseInt(request.getParameter("pagesize"))
				);
		}
		
		//ϣ����http session�л��pagesize��ֵ�����û�У�������ȱʡֵΪ5
		Integer ps = (Integer)request.getSession().getAttribute("pagesize");
		if(ps == null){
			request.getSession().setAttribute("pagesize", pagesize);
		}else{
			pagesize = ps;
		}
		
		//�ӽ����л�ȡtitle����
		String title = request.getParameter("title");
		
		//��ѯ�����б�
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
			
			//��ѯ�ܼ�¼��
			pstmtForTotal = conn.prepareStatement(sqlForTotal);
			rsForTotal = pstmtForTotal.executeQuery();
			if(rsForTotal.next()){
				total = rsForTotal.getInt(1);
			}
			
			//��ѯ��ǰҳ������
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
		request.setAttribute("articles", articles);
		
		//���ܼ�¼�����ݵ�JSP
		request.setAttribute("total", total);
		
		//forward��article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}
}