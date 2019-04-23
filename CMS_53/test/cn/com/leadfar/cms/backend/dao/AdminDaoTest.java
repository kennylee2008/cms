package cn.com.leadfar.cms.backend.dao;

import java.util.Random;

import junit.framework.TestCase;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

public class AdminDaoTest extends TestCase {

	static PropertiesBeanFactory factory = new PropertiesBeanFactory("beans.properties");
	
	public void testAddAdmin() {
		AdminDao adminDao = (AdminDao)factory.getBean("adminDao");
		
		Admin a = new Admin();
		a.setUsername("≤‚ ‘”√ªß"+new Random().nextInt(9999));
		a.setPassword("≤‚ ‘√‹¬Î"+new Random().nextInt(9999));
		
		adminDao.addAdmin(a);
	}

	public void testFindAdminByUsername() {
		
		AdminDao adminDao = (AdminDao)factory.getBean("adminDao");
		
		Admin a = adminDao.findAdminByUsername("admin");
		System.out.println(a.getId()+","+a.getUsername()+","+a.getPassword());
	}

}
