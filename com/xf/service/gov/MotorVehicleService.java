package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IMotorVehicleDao;
import com.xf.entity.gov.MotorVehicle;
import com.xf.entity.gov.MotorVehicleDb;
import com.xf.entity.gov.VehicleFactorvo;

@Service
public class MotorVehicleService implements IMotorVehicleDao {

	@Autowired
	private IMotorVehicleDao dao;
	
	public void add(MotorVehicle obj) {
		dao.add(obj);
	}
	public void update(MotorVehicleDb obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Integer getMaxYear() {
		try {
			return dao.getMaxYear();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public MotorVehicle getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears() {
		try {
			return dao.getYears();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public MotorVehicle getByField(MotorVehicle obj) {
		return dao.getByField(obj);
	}
	public List<MotorVehicleDb> getByYear(int year) {
		try {
			return dao.getByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<MotorVehicleDb> getByThree(int districtId, int statyear, int standard) {
		return dao.getByThree(districtId, statyear, standard);
	}
	public void deleteByYear(int year) {
		dao.deleteByYear(year);
	}
	public void deleteAll() {
		dao.deleteAll();
		
	}
	public List<VehicleFactorvo> getVfactor() {
		return dao.getVfactor();
	}
	public List<MotorVehicleDb> getByYear2(int year) {
		return dao.getByYear2(year);
	}
	public void deleteByYear2(int year) {
		dao.deleteByYear2(year);
	}

}
