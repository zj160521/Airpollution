package com.xf.dao;

import java.util.List;

import com.xf.entity.Condition;
import com.xf.entity.Elec;

public interface IElecDao {

	public void add(Elec elec);
	public void delete(int id);
	public void update(Elec elec);
	public Elec getById(int id);
	public List<Elec> getByCompany(int companyid);
	public List<Integer> getYears();
	public List<Elec> getByYear(Elec obj);
	public List<Elec> yearList();
	public List<Elec> getyearList(Condition con);
	public void setstatus(int status, int companyid,int fillyear);
	public void setstatus2(int status, int companyid, int orignalStatus);
	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear);
}
