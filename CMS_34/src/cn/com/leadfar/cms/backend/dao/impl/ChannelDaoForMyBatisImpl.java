package cn.com.leadfar.cms.backend.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class ChannelDaoForMyBatisImpl implements ChannelDao {

	public void addChannel(Channel c) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			session.insert(Channel.class.getName()+".add", c);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

	public void delChannels(String[] ids) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			for(String id:ids){
				session.delete(Channel.class.getName()+".del", Integer.parseInt(id));
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

	public Channel findChannelById(int channelId) {
		SqlSession session = MyBatisUtil.getSession();
		Channel c = null;
		try {
			c = (Channel)session.selectOne(Channel.class.getName()+".findById", channelId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return c;
	}

	public PagerVO findChannels(int offset, int pagesize) {
		SqlSession session = MyBatisUtil.getSession();
		List datas = null;
		int total = 0;
		try {
			Map map = new HashMap();
			map.put("offset", offset);
			map.put("pagesize", pagesize);
			datas = session.selectList(Channel.class.getName()+".findPaginated", map);
			
			total = (Integer)session.selectOne(Channel.class.getName()+".findPaginated-count", map);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		PagerVO pv = new PagerVO();
		pv.setDatas(datas);
		pv.setTotal(total);
		return pv;
	}

	public void updateChannel(Channel c) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			session.update(Channel.class.getName()+".update", c);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

}
