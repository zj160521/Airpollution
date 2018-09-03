package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IGasstationDao;
import com.xf.entity.gov.Gasstation;

@Service
public class GasstationService implements IGasstationDao {

	@Autowired
	private IGasstationDao dao;
	
	public void add(Gasstation obj) {
		dao.add(obj);
	}
	public void update(Gasstation obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Gasstation getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Gasstation getByField(Gasstation obj) {
		return dao.getByField(obj);
	}
	public List<Gasstation> getByYear(Gasstation obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void clearData(Gasstation obj) {
		dao.clearData(obj);
	}
}
