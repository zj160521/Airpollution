package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IAccountypeDao;
import com.xf.entity.AccountReport;
import com.xf.entity.Accountype;

@Service
public class AccountypeService implements IAccountypeDao {

	@Autowired
	private IAccountypeDao dao;
	
	public void add(Accountype obj) {
		dao.add(obj);
	}
	public void update(Accountype obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Accountype getById(int id) {
		return dao.getById(id);
	}
	public List<Accountype> getAll(String type) {
		return dao.getAll(type);
	}
}
