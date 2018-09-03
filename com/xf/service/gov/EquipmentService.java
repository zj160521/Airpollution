package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IEquipmentDao;
import com.xf.entity.gov.Equipment;

@Service
public class EquipmentService extends BaseService implements IEquipmentDao {

	@Autowired
	private IEquipmentDao dao;
	
	public void add(Equipment obj) {
		dao.add(obj);
	}
	public void update(Equipment obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Equipment getById(int id) {
		try {
			return dao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Equipment getByField(Equipment obj) {
		return dao.getByField(obj);
	}
	public List<Equipment> getByYear(Equipment obj) {
		return dao.getByYear(obj);
	}
	public List<Equipment> getByYear2(Equipment obj) {
		return dao.getByYear2(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
		
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	
	public Equipment getEquipment(Equipment obj) {
		return dao.getEquipment(obj);
	}
	public List<Equipment> statAll(int year) {
		try {
			return dao.statAll(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void update2(Equipment obj) {
		dao.update2(obj);
		
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}
	public List<Equipment> statAll2(int year) {
		return dao.statAll2(year);
	}

}
