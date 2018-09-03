package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.DevFill;
import com.xf.entity.Static;

public interface IDevFillDao {

	public void add(DevFill dev);
	public void update(DevFill dev);
	public List<DevFill> getByDevId(@Param(value="devid")int devid,@Param(value="fillyear")int fillyear);
	public List<DevFill> getByDevId1(int devid);
	public DevFill getCuryearFill(@Param(value="devid")int devid,@Param(value="fillyear")int fillyear);
	public void setstatus(int status, int companyid,int fillyear);
	public void setstatus2(int status, int companyid, int orignalStatus);
	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear);
	public void unpass(int status, int id, int orignalStatus);
	public List<DevFill> getfuelByname(String name);
	public DevFill getByfuelId(int fuelId,int productid);
	public DevFill getById(int id);
	public void smallSetstatus(int status, int id,int fillyear);
	public void smallSetstatus3(int status, int id, int orignalStatus,int fillyear);
	public List<DevFill> getByCompanyId(@Param(value="companyid")int companyid,@Param(value="fillyear")int fillyear,@Param(value="status")int status);
}
