package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IElecFillDao;
import com.xf.entity.ElecFill;

@Service
public class ElecFillService implements IElecFillDao {

	@Autowired
	private IElecFillDao efillDao;
	
	public void add(ElecFill dev) {
		efillDao.add(dev);
	}

	public void update(ElecFill dev) {
		efillDao.update(dev);
	}

	public ElecFill getById(int id) {
		return efillDao.getById(id);
	}

	public List<ElecFill> getByElec(int elecid) {
		return efillDao.getByElec(elecid);
	}

	public void delete(ElecFill dev) {
		efillDao.delete(dev);
	}

	public void setstatus(int status, int companyid,int fillyear) {
		efillDao.setstatus(status, companyid,fillyear);
	}

	public void setstatus2(int status, int companyid, int originStatus) {
		efillDao.setstatus2(status, companyid, originStatus);
	}
	
	public void setstatus3(int status, int companyid, int originStatus,int fillyear) {
		efillDao.setstatus3(status, companyid, originStatus,fillyear);
	}
	
	public ElecFill getef(int companyId,int devId,int fillyear){
		return efillDao.getef(companyId, devId,fillyear);
	}

	public void updateForUser(ElecFill dev) {
		efillDao.updateForUser(dev);
	}

}
