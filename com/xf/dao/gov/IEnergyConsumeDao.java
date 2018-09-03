package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.EnergyConsume;

public interface IEnergyConsumeDao {

	public void add(EnergyConsume obj);
	public void delete(int id);
	public void update(EnergyConsume obj);
	public EnergyConsume getById(int id);
	public List<Integer> getYears(int accountid);
	public EnergyConsume getByField(EnergyConsume obj);
	public List<EnergyConsume> getByYear(EnergyConsume obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<EnergyConsume> getByTown(int id,int accountid, int fillyear);
}