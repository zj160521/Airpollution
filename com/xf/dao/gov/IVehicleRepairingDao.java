package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.gov.VehicleRepairing;

public interface IVehicleRepairingDao {

	public void add(VehicleRepairing obj);
	public void delete(int id);
	public void update(VehicleRepairing obj);
	public VehicleRepairing getById(int id);
	public List<Integer> getYears(int accountid);
	public VehicleRepairing getByField(VehicleRepairing obj);
	public List<VehicleRepairing> getByYear(VehicleRepairing obj);
	public List<VehicleRepairing> getByTown(VehicleRepairing obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<VehicleRepairing> getAll(int fillYear);
	public List<VehicleRepairing> getAll2(int fillYear);
}