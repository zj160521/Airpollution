package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.ICleanerDao;
import com.xf.entity.gov.Cleaner;

@Service
public class CleanerService extends BaseService implements ICleanerDao {

	@Autowired
	private ICleanerDao dao;
	
	public void add(Cleaner obj) {
		dao.add(obj);
	}
	public void update(Cleaner obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Cleaner getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Cleaner getByField(Cleaner obj) {
		return dao.getByField(obj);
	}
	public List<Cleaner> getByYear(Cleaner obj) {
		return dao.getByYear(obj);
	}
	public List<Cleaner> getByTown(Cleaner obj) {
		return dao.getByTown(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
		
	}
}
