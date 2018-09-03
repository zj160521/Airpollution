package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.OutletFill;


public interface IOutletFillDao {

	public void add(OutletFill o);
	public void delete(int id);
	public void update(OutletFill o);
	public OutletFill getById(int id);
	public OutletFill getCuryearFill(@Param("outletid")int outletid,@Param("fillyear")int fillyear);
	public List<OutletFill> getByOutlet(int outletid);
	public void setstatus(int status, int companyid,int fillyear);
	public void setstatus2(int status, int companyid, int orignalStatus);
	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear);
	public void unpass(int status, int id, int orignalStatus);
	
}
