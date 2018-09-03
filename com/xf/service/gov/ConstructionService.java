package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IConstructionDao;
import com.xf.entity.gov.Construction;

@Service
public class ConstructionService extends BaseService  implements IConstructionDao {

	@Autowired
	private IConstructionDao dao;
	
	public void add(Construction obj) {
		dao.add(obj);
	}
	public void update(Construction obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Construction getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int companyid) {
		return dao.getYears(companyid);
	}
	public Construction getByField(Construction obj) {
		return dao.getByField(obj);
	}
	public List<Construction> getByYear(Construction obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int orignalStatus) {
		dao.setstatus2(status, accountid, orignalStatus);
	}
	public List<Construction> getByTown(int id, int fillYear,int accountid) {
		try {
			return dao.getByTown(id,fillYear,accountid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);		
	}

}
