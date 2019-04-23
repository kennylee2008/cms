package cn.com.leadfar.cms.backend.dao.impl;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class AdminDaoForMyBatisImpl implements AdminDao {

	public void addAdmin(Admin admin) {
		//��һ��session
		SqlSession session = MyBatisUtil.getSession();
		
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
		//��һ��session
		SqlSession session = MyBatisUtil.getSession();
		Admin admin = null;
		try {
			
			admin = (Admin)session.selectOne(Admin.class.getName()+".findAdminByUsername", username);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//�ر�session
			session.close();
		}
		return admin;
	}

}
