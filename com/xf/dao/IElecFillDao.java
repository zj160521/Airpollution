package com.xf.dao;

import java.util.List;

import com.xf.entity.ElecFill;

public interface IElecFillDao {

	public void add(ElecFill dev);
	public void delete(ElecFill dev);
	public void update(ElecFill dev);
	public ElecFill getById(int id);
	public List<ElecFill> getByElec(int elecid);
	public void setstatus(int status,int companyid,int fillyear);
	public void setstatus2(int status,int companyid,int originStatus);
	public void setstatus3(int status,int companyid,int originStatus,int fiilyear);
	public ElecFill getef(int companyId,int devId,int fillyear);
	public void updateForUser(ElecFill dev);
}
