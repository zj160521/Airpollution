package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.ICountyAreaDao;
import com.xf.entity.gov.CountyArea;

@Service
public class CountyAreaService implements ICountyAreaDao {

	@Autowired
	private ICountyAreaDao dao;
	
	public void add(CountyArea obj) {
		dao.add(obj);
	}
	public void update(CountyArea obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public CountyArea getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public CountyArea getByField(CountyArea obj) {
		return dao.getByField(obj);
	}
	public List<CountyArea> getByYear(CountyArea obj) {
		return dao.getByYear(obj);
	}
	public List<CountyArea> getByTown(CountyArea obj) {
		// TODO Auto-generated method stub
		return dao.getByTown(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
}
