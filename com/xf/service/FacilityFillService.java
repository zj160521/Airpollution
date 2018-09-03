package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.ICFacilityFillDao;
import com.xf.entity.CFacilityFill;

@Service
public class FacilityFillService implements ICFacilityFillDao {

	@Autowired
	private ICFacilityFillDao ffillDao;
	
	public void add(CFacilityFill dev) {
		ffillDao.add(dev);
	}

	public void delete(int id) {
		ffillDao.delete(id);
	}

	public void update(CFacilityFill dev) {
		ffillDao.update(dev);
	}

	public CFacilityFill getById(int id) {
		return ffillDao.getById(id);
	}

	public List<CFacilityFill> getByFacility(int facilityid,int fillyear) {
		return ffillDao.getByFacility(facilityid,fillyear);
	}

	public void setstatus(int status, int companyid,int fillyear) {
		ffillDao.setstatus(status, companyid,fillyear);
	}

	public CFacilityFill getByCuryearFill(int facilityid,int fillyear) {
		return ffillDao.getByCuryearFill(facilityid,fillyear);
	}

	public void setstatus2(int status, int companyid, int orignalStatus) {
		ffillDao.setstatus2(status, companyid, orignalStatus);
	}

	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear) {
		ffillDao.setstatus3(status, companyid, orignalStatus,fillyear);
	}
	
	public void unpass(int status, int id, int orignalStatus) {
		ffillDao.unpass(status, id, orignalStatus);
		
	}

}
