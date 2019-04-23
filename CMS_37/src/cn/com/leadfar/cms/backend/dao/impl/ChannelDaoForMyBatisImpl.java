package cn.com.leadfar.cms.backend.dao.impl;

import java.util.HashMap;
import java.util.Map;

import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public class ChannelDaoForMyBatisImpl extends BaseDao implements ChannelDao {

	public void addChannel(Channel c) {
		add(c);
	}

	public void delChannels(String[] ids) {
		del(Channel.class,ids);
	}

	public Channel findChannelById(int channelId) {
		return (Channel)findById(Channel.class,channelId);
	}

	public PagerVO findChannels(int offset, int pagesize) {
		
		Map params = new HashMap();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		
		return findPaginated(Channel.class.getName()+".findPaginated", params);
	}

	public void updateChannel(Channel c) {
		update(c);
	}

}
