package cn.com.leadfar.cms.backend.dao.impl;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.model.Admin;

public class AdminDaoForMyBatisImpl implements AdminDao {

	public void addAdmin(Admin admin) {
		//创建SqlSession的工厂！
		SqlSessionFactory factory = null;
		try {
			//通过配置文件，创建工厂对象
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//打开一个session
		SqlSession session = factory.openSession();
		
		try {
			//插入
			session.insert(Admin.class.getName()+".add", admin);
			
			//提交事务
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//关闭session
			session.close();
		}

	}

	public Admin findAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
