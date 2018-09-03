package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.EquipmentFarm;

public interface IEquipmentFarmDao {

	public void add(EquipmentFarm obj);
	public void delete(int id);
	public void update(EquipmentFarm obj);
	public EquipmentFarm getById(int id);
	public List<Integer> getYears(int accountid);
	public EquipmentFarm getByField(EquipmentFarm obj);
	public List<EquipmentFarm> getByYear(EquipmentFarm obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<EquipmentFarm> getThreevalue(int year);
	public List<EquipmentFarm> getThreevalue2(int year);
}