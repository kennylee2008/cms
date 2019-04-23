package cn.com.leadfar.cms.backend.dao;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.utils.BeanFactory;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

public class AticleDaoTest extends TestCase{
	
	public void testAddArticle(){
		
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		
		Random r = new Random();
		
		Article a = new Article();
		a.setTitle("测试文章"+r.nextInt(99999));
		a.setContent("测试文章的内容"+r.nextInt(99999));
		a.setHeadline(true);
		a.setType("原创");
		
		articleDao.addArticle(a);
		
	}
	
	//测试把文章放到某个频道下面
	public void testAddArticle02(){
		
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		
		Random r = new Random();
		
		Article a = new Article();
		a.setTitle("测试文章"+r.nextInt(99999));
		a.setContent("测试文章的内容"+r.nextInt(99999));
		a.setHeadline(true);
		a.setType("原创");
		
		//设置文章属于哪些频道
		Set channels = new HashSet();
		Channel c = new Channel();
		c.setId(1);
		channels.add(c);
		
		a.setChannels(channels);
		
		articleDao.addArticle(a);
		
	}	
	
	//测试把文章放到多个频道下面
	public void testAddArticle03(){
		
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		
		Random r = new Random();
		
		Article a = new Article();
		a.setTitle("测试文章"+r.nextInt(99999));
		a.setContent("测试文章的内容"+r.nextInt(99999));
		a.setHeadline(true);
		a.setType("原创");
		
		//设置文章属于哪些频道
		Set channels = new HashSet();
		Channel c = new Channel();
		c.setId(1);
		channels.add(c);
		
		Channel c2 = new Channel();
		c2.setId(3);
		channels.add(c2);
		
		a.setChannels(channels);
		
		articleDao.addArticle(a);
		
	}		
	
	public void testSearchArticles(){
		System.out.println("testSearchArticles");
	}
}
