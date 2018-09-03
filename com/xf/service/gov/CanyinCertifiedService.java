package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.ICanyinCertifiedDao;
import com.xf.entity.gov.CanyinCertified;

@Service
public class CanyinCertifiedService implements ICanyinCertifiedDao {

	@Autowired
	private ICanyinCertifiedDao dao;
	
	public void add(CanyinCertified obj) {
		dao.add(obj);
	}
	public void update(CanyinCertified obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public CanyinCertified getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public CanyinCertified getByField(CanyinCertified obj) {
		return dao.getByField(obj);
	}
	public List<CanyinCertified> getByYear(CanyinCertified obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void clearData(CanyinCertified obj) {
		dao.clearData(obj);
	}
}
