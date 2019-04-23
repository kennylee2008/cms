package cn.com.leadfar.cms.backend.service;

import java.util.List;

import cn.com.leadfar.cms.backend.model.Article;

public interface SpiderService {
	public List<Article> collect(String url,String[] channelIds);
}
