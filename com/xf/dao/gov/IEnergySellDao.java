package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.gov.EnergySell;

public interface IEnergySellDao {

	public void add(EnergySell obj);
	public void delete(int id);
	public void update(EnergySell obj);
	public EnergySell getById(int id);
	public List<Integer> getYears(int accountid);
	public EnergySell getByField(EnergySell obj);
	public List<EnergySell> getByYear(EnergySell obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<EnergySell> getByTown(int id,int accountid,int fillyear);
}