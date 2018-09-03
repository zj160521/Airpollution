package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.VehicleAction;

public interface IVehicleActionDao {

	public void add(VehicleAction obj);
	public void delete(int id);
	public void update(VehicleAction obj);
	public VehicleAction getById(int id);
	public List<Integer> getYears(int accountid);
	public VehicleAction getByField(VehicleAction obj);
	public List<VehicleAction> getByYear(VehicleAction obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void clearData(VehicleAction obj);
}