package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.gov.MotorVehicle;
import com.xf.entity.gov.MotorVehicleDb;
import com.xf.entity.gov.VehicleFactorvo;

public interface IMotorVehicleDao {

	public void add(MotorVehicle obj);
	public void delete(int id);
	public void deleteByYear(int year);
	public void deleteByYear2(int year);
	public void update(MotorVehicleDb obj);
	public MotorVehicle getById(int id);
	public List<Integer> getYears();
	public MotorVehicle getByField(MotorVehicle obj);
	public List<MotorVehicleDb> getByYear(int year);
	public List<MotorVehicleDb> getByYear2(int year);
	public List<MotorVehicleDb> getByThree(int districtId,int statyear,int standard);
	public Integer getMaxYear();
	public void deleteAll();
	public List<VehicleFactorvo> getVfactor();
}