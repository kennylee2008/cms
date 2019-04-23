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
	
	public Object getBean(String name){
		String className = props.getProperty(name);
		try {
			Class clz = Class.forName(className);
			return clz.newInstance();
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
