package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.Condition;
import com.xf.entity.Devices;

public interface IDeviceDao {

	public void add(Devices dev);
	public void delete(int id);
	public void delBycomIdYear(int comId, int year);
	public void update(Devices dev);
	public Devices getById(@Param(value="id")int id,@Param(value="fillyear")int fillyear);
	public List<Devices> getByCompany(@Param(value="companyid")int companyid,@Param(value="fillyear")int fillyear);
	public List<Devices> getByStep(@Param(value="stepid")int stepid,@Param(value="fillyear")int fillyear);
	public List<Devices> yearList(int fillyear);
	public List<Devices> getByFacility(@Param(value="facilityid")int facilityid,@Param(value="fillyear")int fillyear);
	public List<Devices> getyearList(Condition con);
	public Devices getBydevId(int id);
	public List<Devices> getByStepId(int stepid);
	public List<Devices> getByCompanyId(int companyid);
	public List<Devices> getAll();
	public List<Devices> getByCompanyYear(Devices devices);
	public List<Devices> getByCompanyYear2(Devices devices);
	public List<Devices> getotalYear(int fillyear);
	public Devices getBydevId2(@Param(value="id")int id,@Param(value="fillyear")int fillyear);
}
