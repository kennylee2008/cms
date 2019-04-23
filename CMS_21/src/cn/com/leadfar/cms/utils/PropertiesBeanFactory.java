package cn.com.leadfar.cms.utils;

import java.io.IOException;
import java.util.Properties;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;

public class PropertiesBeanFactory implements BeanFactory {
	Properties props = new Properties();
	public PropertiesBeanFactory(){
		//读取配置文件，得到具体DAO的实现类名
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("beans.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArticleDao getArticleDao() {
		
		String className = props.getProperty("articleDao");
		
		try {
			Class clz = Class.forName(className);
			return (ArticleDao)clz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ChannelDao getChannelDao() {
		String className = props.getProperty("channelDao");
		
		try {
			Class clz = Class.forName(className);
			return (ChannelDao)clz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
