package cn.com.leadfar.cms.backend.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PagerVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class BaseDao {
	public void add(Object entity){
		SqlSession session = MyBatisUtil.getSession();
		try {
			session.insert(entity.getClass().getName()+".add", entity);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}
	
	public void update(Object entity){
		SqlSession session = MyBatisUtil.getSession();
		try {
			session.update(entity.getClass().getName()+".update", entity);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}
	
	public void del(Class entityClass,int id){
		SqlSession session = MyBatisUtil.getSession();
		try {
			session.delete(entityClass.getName()+".del", id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}
	
	public void del(Class entityClass,int[] ids){
		SqlSession session = MyBatisUtil.getSession();
		try {
			for(int id:ids){
				session.delete(entityClass.getName()+".del", id);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}
	
	public void del(Class entityClass,String[] ids){
		SqlSession session = MyBatisUtil.getSession();
		try {
			for(String id:ids){
				session.delete(entityClass.getName()+".del", Integer.parseInt(id));
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}
	
	public Object findById(Class entityClass,int id){
		SqlSession session = MyBatisUtil.getSession();
		try {
			return session.selectOne(entityClass.getName()+".findById", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null;
	}
	
	public List findAll(Class entityClass){
		SqlSession session = MyBatisUtil.getSession();
		try {
			return session.selectList(entityClass.getName()+".findAll");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null;
	}
	
	public PagerVO findPaginated(String sqlId,Map params){
		SqlSession session = MyBatisUtil.getSession();
		List datas = null;
		int total = 0;
		try {
			datas = session.selectList(sqlId, params);
			total = (Integer)session.selectOne(sqlId+"-count", params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		PagerVO pv = new PagerVO();
		pv.setDatas(datas);
		pv.setTotal(total);
		return pv;
	}
}
