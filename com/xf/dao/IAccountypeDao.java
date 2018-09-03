package com.xf.dao;

import java.util.List;

import com.xf.entity.AccountReport;
import com.xf.entity.Accountype;

public interface IAccountypeDao {

	public void add(Accountype obj);
	public void delete(int id);
	public void update(Accountype obj);
	public Accountype getById(int id);
	public List<Accountype> getAll(String type);
}