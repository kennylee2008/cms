package cn.com.leadfar.cms.backend.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.DBUtil;

public class ChannelDaoImpl implements ChannelDao {

	public void addChannel(Channel c) {
		//�����ݲ�������
		String sql = "insert into t_channel (name,description) values (?,?)";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getDescription());
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

	public void delChannels(String[] ids) {
		//�����ݿ���ɾ��������¼
		String sql = "delete from t_channel where id = ?";
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

	public Channel findChannelById(int channelId) {
		//�����ݿ��и���ID����ѯ��Ӧ��Ƶ����Ϣ
		String sql = "select * from t_channel where id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Channel c = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, channelId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				c = new Channel();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
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
		return c;
	}

	public PagerVO findChannels(int offset, int pagesize) {

		//�ܼ�¼��
		int total = 0;
		
		//��ǰҳ������
		List channels = new ArrayList();
		
		String sql = "select * from t_channel limit ?,?";
		String sqlForTotal = "select count(*) from t_channel";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtForTotal = null;
		ResultSet rs = null;
		ResultSet rsForTotal = null;
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
				Channel c = new Channel();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				channels.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(rsForTotal);
			DBUtil.close(pstmtForTotal);
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		PagerVO pv = new PagerVO();
		pv.setDatas(channels);
		pv.setTotal(total);
		return pv;
	}

	public void updateChannel(Channel c) {
		//�����������SQL�����¶�Ӧ��Ƶ��
		//update t_channel set name=?,description=? where id=?
		String sql = "update t_channel set name=?,description=? where id=?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getDescription());
			pstmt.setInt(3, c.getId());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally{
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}		
	}

}
