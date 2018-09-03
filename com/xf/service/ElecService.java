package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IElecDao;
import com.xf.entity.Condition;
import com.xf.entity.Elec;

@Service
public class ElecService implements IElecDao {

	@Autowired
	private IElecDao elecDao;

	public void add(Elec elec) {
		elecDao.add(elec);
	}

	public void delete(int id) {
		elecDao.delete(id);
	}

	public void update(Elec elec) {
		elecDao.update(elec);
	}

	public Elec getById(int id) {
		try {
			return elecDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Elec> getByCompany(int companyid) {
		return elecDao.getByCompany(companyid);
	}

	public List<Integer> getYears() {
		
		return elecDao.getYears();
	}

	public List<Elec> getByYear(Elec obj) {
		return elecDao.getByYear(obj);
	}

	public List<Elec> yearList() {
		return elecDao.yearList();
	}

	public void setstatus(int status, int companyid,int fillyear) {
		elecDao.setstatus(status, companyid, fillyear);
	}

	public void setstatus2(int status, int companyid, int orignalStatus) {
		elecDao.setstatus2(status, companyid, orignalStatus);
	}

	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear) {
		elecDao.setstatus3(status, companyid, orignalStatus,fillyear);
	}
	
	public List<Elec> getyearList(Condition con) {
		return elecDao.getyearList(con);
	}
	
	
}
