package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IHouseholdFuelDao;
import com.xf.entity.gov.HouseholdFuel;

@Service
public class HouseholdFuelService extends BaseService implements IHouseholdFuelDao {

	@Autowired
	private IHouseholdFuelDao dao;
	
	public void add(HouseholdFuel obj) {
		dao.add(obj);
	}
	public void update(HouseholdFuel obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public HouseholdFuel getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public HouseholdFuel getByField(HouseholdFuel obj) {
		return dao.getByField(obj);
	}
	public List<HouseholdFuel> getByYear(HouseholdFuel obj) {
		return dao.getByYear(obj);
	}
	public List<HouseholdFuel> getByTown(HouseholdFuel obj) {
		return dao.getByTown(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}
}
