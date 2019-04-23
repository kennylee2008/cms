package cn.com.leadfar.cms.backend.dao;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import cn.com.leadfar.cms.backend.model.Article;
import junit.framework.TestCase;

public class BeanUtilsTest extends TestCase {
	public void testBeanUtils01() throws Exception{
		Article a = new Article();
		
		BeanUtils.copyProperty(a, "title", "这是标题");
		
		System.out.println(a.getTitle());
	}
	
	public void testBeanUtils02() throws Exception{
		Article a = new Article();
		
		BeanUtils.copyProperty(a, "leaveNumber", "30");
		
		System.out.println(a.getLeaveNumber());
	}
	
	public void testBeanUtils03() throws Exception{
		Article a = new Article();
		
		BeanUtils.copyProperty(a, "recommend", "true");
		
		System.out.println(a.isRecommend());
	}
	
	public void testBeanUtils04() throws Exception{
		Article a = new Article();
		
		ConvertUtils.register(new DateConverter(), Date.class);
		
		BeanUtils.copyProperty(a, "createTime", "2009-10-01");
		
		System.out.println(a.getCreateTime());
	}
}
