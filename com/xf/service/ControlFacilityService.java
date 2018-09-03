package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IControlFacilityDao;
import com.xf.entity.Condition;
import com.xf.entity.ControlFacility;

@Service
public class ControlFacilityService implements IControlFacilityDao {

	@Autowired
	private IControlFacilityDao faciDao;
	
	public void add(ControlFacility dev) {
		faciDao.add(dev);
	}

	public void delete(int id) {
		faciDao.delete(id);
	}

	public void update(ControlFacility dev) {
		faciDao.update(dev);
	}

	public ControlFacility getById(int fillyear,int id) {
		return faciDao.getById(fillyear,id);
	}

	public List<ControlFacility> getByCompany(int companyid,int fillyear) {
		return faciDao.getByCompany(companyid,fillyear);
	}

	public void addStep(int facilityid, int stepid) {
		faciDao.addStep(facilityid, stepid);
	}

	public void delStep(int facilityid, int stepid) {
		faciDao.delStep(facilityid, stepid);
	}

	public List<ControlFacility> yearList() {
		return faciDao.yearList();
	}

	public void addDevice(int facilityid, int deviceid) {
		faciDao.addDevice(facilityid, deviceid);
	}

	public void delDevice(int facilityid, int deviceid) {
		faciDao.delDevice(facilityid,deviceid);
	}

	public void delStepfacid(int facilityid) {
		faciDao.delStepfacid(facilityid);
	}

	public void delDevicefacid(int facilityid) {
		faciDao.delDevicefacid(facilityid);
	}

	public List<ControlFacility> getBy(int enterid,int pollid,int tech1,int tech2,int outletid,String facilityModel) {
		
		try {
			List<ControlFacility> faci=faciDao.getBy(enterid,pollid,tech1,tech2,outletid,facilityModel);
			return faci;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ControlFacility> getyearList(Condition con) {
		return faciDao.getyearList(con);
	}

	public ControlFacility getfacById(int id) {
		return faciDao.getfacById(id);
	}

}
