package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IVehicleRepairingDao;
import com.xf.entity.gov.VehicleRepairing;

@Service
public class VehicleRepairingService extends BaseService implements IVehicleRepairingDao {

	@Autowired
	private IVehicleRepairingDao dao;
	
	public void add(VehicleRepairing obj) {
		dao.add(obj);
	}
	public void update(VehicleRepairing obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public VehicleRepairing getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public VehicleRepairing getByField(VehicleRepairing obj) {
		return dao.getByField(obj);
	}
	public List<VehicleRepairing> getByYear(VehicleRepairing obj) {
		return dao.getByYear(obj);
	}
	public List<VehicleRepairing> getByTown(VehicleRepairing obj) {
		return dao.getByTown(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<VehicleRepairing> getAll(int fillYear) {
		try {
			return dao.getAll(fillYear);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}
	public List<VehicleRepairing> getAll2(int fillYear) {
		return dao.getAll2(fillYear);
	}
}
