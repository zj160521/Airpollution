package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IGknumberDao;
import com.xf.entity.gov.Gknumber;

@Service
public class GknumberService implements IGknumberDao {

	@Autowired
	private IGknumberDao dao;
	
	public void add(Gknumber obj) {
		dao.add(obj);
	}
	public void update(Gknumber obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Gknumber getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Gknumber getByField(Gknumber obj) {
		return dao.getByField(obj);
	}
	public List<Gknumber> getByYear(Gknumber obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void clearData(Gknumber obj) {
		dao.clearData(obj);
	}
}
