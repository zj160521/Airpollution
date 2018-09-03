package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IDeviceDao;
import com.xf.entity.Condition;
import com.xf.entity.Devices;

@Service
public class DeviceService implements IDeviceDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private IDeviceDao deviceDao;

	public void add(Devices device) {
		deviceDao.add(device);
	}

	public void delete(int id) {
		deviceDao.delete(id);
	}

	public void update(Devices device) {
		deviceDao.update(device);
	}

	public Devices getById(int id,int fillyear) {
		return deviceDao.getById(id,fillyear);
	}

	public List<Devices> getByCompany(int companyid,int fillyear) {
		return deviceDao.getByCompany(companyid,fillyear);
	}

	public List<Devices> getByStep(int stepid,int fillyear) {
		return deviceDao.getByStep(stepid,fillyear);
	}

	public List<Devices> yearList(int fillyear) {
		return deviceDao.yearList(fillyear);
	}

	public List<Devices> getByFacility(int facilityid,int fillyear) {
		return deviceDao.getByFacility(facilityid,fillyear);
	}

	public List<Devices> getyearList(Condition con) {
		return deviceDao.getyearList(con);
	}

	public Devices getBydevId(int id) {
		return deviceDao.getBydevId(id);
	}

	public List<Devices> getByStepId(int stepid) {
		return deviceDao.getByStepId(stepid);
	}

	public List<Devices> getByCompanyId(int companyid) {
		return deviceDao.getByCompanyId(companyid);
	}

	public List<Devices> getAll() {
		return deviceDao.getAll();
	}

	public List<Devices> getByCompanyYear(Devices devices) {
		return deviceDao.getByCompanyYear(devices);
	}
	
	public List<Devices> getByCompanyYear2(Devices devices) {
		return deviceDao.getByCompanyYear2(devices);
	}

	public List<Devices> getotalYear(int fillyear) {
		return deviceDao.getotalYear(fillyear);
	}

	public Devices getBydevId2(int id, int fillyear) {
		return deviceDao.getBydevId2(id, fillyear);
	}

	public void delBycomIdYear(int comId, int year) {
		deviceDao.delBycomIdYear(comId,year);
	}

}
