package cn.com.leadfar.cms.backend.dao.impl;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class AdminDaoForMyBatisImpl implements AdminDao {

	public void addAdmin(Admin admin) {
		//打开一个session
		SqlSession session = MyBatisUtil.getSession();
		
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
		//打开一个session
		SqlSession session = MyBatisUtil.getSession();
		Admin admin = null;
		try {
			
			admin = (Admin)session.selectOne(Admin.class.getName()+".findAdminByUsername", username);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭session
			session.close();
		}
		return admin;
	}

}
