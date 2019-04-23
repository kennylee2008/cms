package cn.com.leadfar.cms.backend.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;
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
		a.setCreateTime(new Date());
		
		articleDao.addArticle(a);
		System.out.println(a.getId());
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
	
	public void testDelArticle(){
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		articleDao.delArticles(new String[]{"5","6","7","8"});
	}
	
	public void testFindArticleById(){
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		Article a = articleDao.findArticleById(15);
		System.out.println(a.getId()+","+a.getTitle());
	}
	
	public void testSearchArticlesByTitle(){
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		PagerVO pv = articleDao.findArticles("1", 0, 5);
		System.out.println("总记录数："+pv.getTotal());
		List datas = pv.getDatas();
		for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
			Article a = (Article) iterator.next();
			System.out.println(a.getId()+","+a.getTitle());
		}
	}
	
	public void testSearchArticlesByChannel01(){
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
		
		Channel c = new Channel();
		c.setId(1);
		
		PagerVO pv = articleDao.findArticles(c, 0, 5);
		System.out.println("总记录数："+pv.getTotal());
		List datas = pv.getDatas();
		for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
			Article a = (Article) iterator.next();
			System.out.println(a.getId()+","+a.getTitle());
		}
	}
	
	public void testSearchArticlesByChannel02(){
		BeanFactory factory = new PropertiesBeanFactory("beans.properties");
		ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
	
		PagerVO pv = articleDao.findArticles((Channel)null, 0, 5);
		System.out.println("总记录数："+pv.getTotal());
		List datas = pv.getDatas();
		for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
			Article a = (Article) iterator.next();
			System.out.println(a.getId()+","+a.getTitle());
		}
	}
}
