package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.CFacilityFill;

public interface ICFacilityFillDao {

	public void add(CFacilityFill dev);
	public void delete(int id);
	public void update(CFacilityFill dev);
	public CFacilityFill getById(int id);
	public CFacilityFill getByCuryearFill(@Param(value="facilityid")int facilityid,@Param(value="fillyear")int fillyear);
	public List<CFacilityFill> getByFacility(@Param(value="facilityid")int facilityid,@Param(value="fillyear")int fillyear);
	public void setstatus(int status, int companyid,int fillyear);
	public void setstatus2(int status, int companyid, int orignalStatus);
	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear);
	public void unpass(int status, int id, int orignalStatus);
}
