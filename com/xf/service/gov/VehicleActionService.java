package com.xf.service.gov;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IVehicleActionDao;
import com.xf.entity.gov.VehicleAction;

@Service
public class VehicleActionService implements IVehicleActionDao {

	@Autowired
	private IVehicleActionDao dao;
	
	public void add(VehicleAction obj) {
		dao.add(obj);
	}
	public void update(VehicleAction obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public VehicleAction getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public VehicleAction getByField(VehicleAction obj) {
		return dao.getByField(obj);
	}
	public List<VehicleAction> getByYear(VehicleAction obj) {
		
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
		
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void clearData(VehicleAction obj) {
		dao.clearData(obj);
	}

}
