package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IPlaneDao;
import com.xf.entity.gov.Plane;

@Service
public class PlaneService implements IPlaneDao {

	@Autowired
	private IPlaneDao dao;
	
	public void add(Plane obj) {
		dao.add(obj);
	}
	public void update(Plane obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Plane getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Plane getByField(Plane obj) {
		return dao.getByField(obj);
	}
	public List<Plane> getByYear(Plane obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
		
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<Plane> getAll(int fillYear) {
		try {
			return dao.getAll(fillYear);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void clearData(Plane obj) {
		dao.clearData(obj);
	}
	public List<Plane> getAll2(int fillYear) {
		return dao.getAll2(fillYear);
	}

}
