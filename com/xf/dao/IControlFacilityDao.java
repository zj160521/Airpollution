package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.Condition;
import com.xf.entity.ControlFacility;

public interface IControlFacilityDao {

	public void add(ControlFacility dev);

	public void delete(int id);

	public void update(ControlFacility dev);

	public ControlFacility getById(@Param("fillyear")int fillyear,@Param("id")int id);
	public ControlFacility getfacById(int id);
	public List<ControlFacility> getByCompany(@Param("companyid")int companyid,@Param("fillyear")int fillyear);
	
	public void addStep(int facilityid, int stepid);

	public void delStep(int facilityid, int deviceid);

	public List<ControlFacility> yearList();

	public List<ControlFacility> getyearList(Condition con);

	public void addDevice(int facilityid, int deviceid);

	public void delDevice(int facilityid, int deviceid);

	public void delStepfacid(int facilityid);

	public void delDevicefacid(int facilityid);

	public List<ControlFacility> getBy(@Param("enterpriceId") int enterid,
			@Param("pollutantId") int pollid, @Param("technique1") int tech1,
			@Param("technique2") int tech2, @Param("outletId") int outletid,
			@Param("facilityModel") String facilityModel);
}
