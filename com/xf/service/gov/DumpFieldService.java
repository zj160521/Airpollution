package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IDumpFieldDao;
import com.xf.entity.gov.DumpField;

@Service
public class DumpFieldService implements IDumpFieldDao {

	@Autowired
	private IDumpFieldDao dao;
	
	public void add(DumpField obj) {
		dao.add(obj);
	}
	public void update(DumpField obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public DumpField getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int companyid) {
		return dao.getYears(companyid);
	}
	public DumpField getByField(DumpField obj) {
		return dao.getByField(obj);
	}
	public List<DumpField> getByYear(DumpField obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int orignalStatus) {
		dao.setstatus2(status, accountid, orignalStatus);
	}
	public void clearData(DumpField obj) {
		dao.clearData(obj);
	}
	
}
