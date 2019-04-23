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
		//����SqlSession�Ĺ�����
		SqlSessionFactory factory = null;
		try {
			//ͨ�������ļ���������������
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//��һ��session
		SqlSession session = factory.openSession();
		
		try {
			//����
			session.insert(Admin.class.getName()+".add", admin);
			
			//�ύ����
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			//�ر�session
			session.close();
		}

	}

	public Admin findAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
