package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Admin;

public interface AdminDao {
	public void addAdmin(Admin admin);
	public Admin findAdminByUsername(String username);
}