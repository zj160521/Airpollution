package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IOildepotDao;
import com.xf.entity.gov.Oildepot;

@Service
public class OildepotService implements IOildepotDao {

	@Autowired
	private IOildepotDao dao;
	
	public void add(Oildepot obj) {
		dao.add(obj);
	}
	public void update(Oildepot obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Oildepot getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Oildepot getByField(Oildepot obj) {
		return dao.getByField(obj);
	}
	public List<Oildepot> getByYear(Oildepot obj) {
		return dao.getByYear(obj);
	}
	public List<Oildepot> getByName(Oildepot obj) {
		// TODO Auto-generated method stub
		return dao.getByName(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void clearData(Oildepot obj) {
		dao.clearData(obj);
	}
}
