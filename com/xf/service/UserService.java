package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IUserDao;
import com.xf.entity.Permission;
import com.xf.entity.User;

@Service
public class UserService implements IUserDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private IUserDao userDao;

	public void add(User user) {
		userDao.add(user);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public User getById(int id) {
		return userDao.getById(id);
	}

	public User getByName(String name) {
		return userDao.getByName(name);
	}
	
	public List<User> getAll() {
		return userDao.getAll();
	}

	public List<Permission> getPermission(int id) {
		return userDao.getPermission(id);
	}

	public User getUserByRoleId(int roleId) {
		return userDao.getUserByRoleId(roleId);
	}

	public List<User> getByName2(String name) {
		return userDao.getByName2(name);
	}

}
