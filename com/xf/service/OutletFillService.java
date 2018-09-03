package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IOutletFillDao;
import com.xf.entity.OutletFill;

@Service
public class OutletFillService implements IOutletFillDao {

	@Autowired
	private IOutletFillDao ofillDao;
	
	public void add(OutletFill o) {
		ofillDao.add(o);
	}

	public void delete(int id) {
		ofillDao.delete(id);
	}

	public void update(OutletFill o) {
		ofillDao.update(o);
	}

	public OutletFill getById(int id) {
		return ofillDao.getById(id);
	}

	public List<OutletFill> getByOutlet(int outletid) {
		return ofillDao.getByOutlet(outletid);
	}

	public void setstatus(int status, int companyid,int fillyear) {
		ofillDao.setstatus(status, companyid,fillyear);
	}

	public OutletFill getCuryearFill(int outletid,int fillyear) {
		return ofillDao.getCuryearFill(outletid,fillyear);
	}

	public void setstatus2(int status, int companyid, int orignalStatus) {
		ofillDao.setstatus2(status, companyid, orignalStatus);
	}

	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear) {
		ofillDao.setstatus3(status, companyid, orignalStatus,fillyear);
	}
	
	public void unpass(int status, int id, int orignalStatus) {
		ofillDao.unpass(status, id, orignalStatus);
		
	}

}
