package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IIndustryDao;
import com.xf.entity.gov.Industry;

@Service
public class IndustryService implements IIndustryDao {

	@Autowired
	private IIndustryDao dao;
	
	public void add(Industry obj) {
		dao.add(obj);
	}
	public void update(Industry obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Industry getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Industry getByField(Industry obj) {
		return dao.getByField(obj);
	}
	public List<Industry> getByYear(Industry obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void clearData(Industry obj) {
		dao.clearData(obj);
	}
}
