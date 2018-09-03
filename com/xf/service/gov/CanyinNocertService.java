package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.ICanyinNocertDao;
import com.xf.entity.gov.CanyinNocert;

@Service
public class CanyinNocertService implements ICanyinNocertDao {

	@Autowired
	private ICanyinNocertDao dao;
	
	public void add(CanyinNocert obj) {
		dao.add(obj);
	}
	public void update(CanyinNocert obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public CanyinNocert getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public CanyinNocert getByField(CanyinNocert obj) {
		return dao.getByField(obj);
	}
	public List<CanyinNocert> getByYear(CanyinNocert obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void clearData(CanyinNocert obj) {
		dao.clearData(obj);
	}
}
