package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.gov.HouseholdFuel;

public interface IHouseholdFuelDao {

	public void add(HouseholdFuel obj);
	public void delete(int id);
	public void update(HouseholdFuel obj);
	public HouseholdFuel getById(int id);
	public List<Integer> getYears(int accountid);
	public HouseholdFuel getByField(HouseholdFuel obj);
	public List<HouseholdFuel> getByYear(HouseholdFuel obj);
	public List<HouseholdFuel> getByTown(HouseholdFuel obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
}