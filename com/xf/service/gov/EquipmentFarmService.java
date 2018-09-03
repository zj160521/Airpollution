package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IEquipmentFarmDao;
import com.xf.entity.gov.EquipmentFarm;

@Service
public class EquipmentFarmService extends BaseService implements IEquipmentFarmDao {

	@Autowired
	private IEquipmentFarmDao dao;
	
	public void add(EquipmentFarm obj) {
		dao.add(obj);
	}
	public void update(EquipmentFarm obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public EquipmentFarm getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public EquipmentFarm getByField(EquipmentFarm obj) {
		return dao.getByField(obj);
	}
	public List<EquipmentFarm> getByYear(EquipmentFarm obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
		
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<EquipmentFarm> getThreevalue(int year) {
		return dao.getThreevalue(year);
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}
	public List<EquipmentFarm> getThreevalue2(int year) {
		return dao.getThreevalue2(year);
	}

}
