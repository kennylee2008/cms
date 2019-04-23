package cn.com.leadfar.cms.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesBeanFactory implements BeanFactory {
	Map beans = new HashMap();
	public PropertiesBeanFactory(){
		//读取配置文件，得到具体DAO的实现类名
		try {
			Properties props = new Properties();
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("beans.properties"));
			
			//根据配置文件，初始化所有的DAO对象
			Set set = props.entrySet();
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String key = (String)entry.getKey(); //DAO的名称
				String className = (String)entry.getValue(); //全路径类名
				Class clz = Class.forName(className);
				Object bean = clz.newInstance(); //预先创建好的DAO对象
				beans.put(key, bean); //缓存DAO对象
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object getBean(String name){
		return beans.get(name);
	}
}
