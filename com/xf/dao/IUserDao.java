package com.xf.dao;

import java.util.List;

import com.xf.entity.Permission;
import com.xf.entity.User;

public interface IUserDao {
	// 添加方法
	public void add(User user);

	// 删除方法
	public void delete(int id);

	// 修改
	public void update(User user);

	// 根据id来查询全部
	public User getById(int id);
	
	public User getByName(String name);
	public List<User> getByName2(String name);

	// 查询所有
	public List<User> getAll();
	
	//获取权限
    public List<Permission> getPermission(int id);
    
    public User getUserByRoleId(int roleId);
}
