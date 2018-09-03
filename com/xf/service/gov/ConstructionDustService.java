package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IConstructionDustDao;
import com.xf.entity.gov.ConstructionDust;

@Service
public class ConstructionDustService extends BaseService implements IConstructionDustDao {

	@Autowired
	private IConstructionDustDao dao;
	
	public void add(ConstructionDust obj) {
		dao.add(obj);
	}
	public void update(ConstructionDust obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public List<ConstructionDust> getById(int accountid) {
		return dao.getById(accountid);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public ConstructionDust getByField(ConstructionDust obj) {
		return dao.getByField(obj);
	}
	public List<ConstructionDust> getByYear(ConstructionDust obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public ConstructionDust getConstructionDust(ConstructionDust obj){
		return dao.getConstructionDust(obj);
		
	}
	public List<ConstructionDust> statAll(int year) {
		try {
			return dao.statAll(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);		
	}
	public List<ConstructionDust> statAll2(int year) {
		return dao.statAll2(year);
	}
}
