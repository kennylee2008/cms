package cn.com.leadfar.cms.backend.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.service.Spider;
import cn.com.leadfar.cms.backend.service.SpiderService;

public class SpiderServiceImpl implements SpiderService {

	private ArticleDao articleDao;
	
	@Override
	public List<Article> collect(String url, String[] channelIds) {

		Spider spider = Spider.getInstance(url);
		
		List<Article> articles = spider.collect(url);
		
		if(articles != null){
			
			//����Ƶ��ID�б�����Ƶ�����󣬲����õ�article������
			Set channels = new HashSet();
			if(channelIds != null){
				for(String channelId:channelIds){
					Channel c = new Channel();
					c.setId(Integer.parseInt(channelId));
					channels.add(c);
				}
			}
			
			for(Article a : articles){
				a.setChannels(channels);
				a.setCreateTime(new Date());
				a.setType("ת��");
				articleDao.addArticle(a);
			}
		}
		
		return articles;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
