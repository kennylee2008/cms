package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;

public interface ChannelDao {
	public void addChannel(Channel c);
	public void delChannels(String[] ids);
	public Channel findChannelById(int channelId);
	public void updateChannel(Channel c);
	public PagerVO findChannels();
}
