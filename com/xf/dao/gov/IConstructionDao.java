package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Construction;

public interface IConstructionDao {

	public void add(Construction obj);
	public void delete(int id);
	public void update(Construction obj);
	public Construction getById(int id);
	public List<Integer> getYears(int companyid);
	public Construction getByField(Construction obj);
	public List<Construction> getByYear(Construction obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<Construction> getByTown(int id, int fillYear,int accountid);
}