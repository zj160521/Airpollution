package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.ConstructionDust;

public interface IConstructionDustDao {

	public void add(ConstructionDust obj);
	public void delete(int id);
	public void update(ConstructionDust obj);
	public List<ConstructionDust> getById(int accountid);
	public List<Integer> getYears(int accountid);
	public ConstructionDust getByField(ConstructionDust obj);
	public List<ConstructionDust> getByYear(ConstructionDust obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public ConstructionDust getConstructionDust(ConstructionDust obj);
	public List<ConstructionDust> statAll(int year);
	public List<ConstructionDust> statAll2(int year);
}