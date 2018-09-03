package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.MotorStandartDao;
import com.xf.entity.gov.MotorStat;
import com.xf.entity.gov.VehicleAction;
import com.xf.entity.gov.VehicleFactor;
import com.xf.entity.gov.VehicleStandard;
import com.xf.vo.MotorCount;

@Service
public class MotorStandartService implements MotorStandartDao{
	@Autowired
	private MotorStandartDao dao;

	public List<VehicleAction> getAllMotor(int fillyear) {
		return dao.getAllMotor(fillyear);
	}

	public void updateStandard(List<VehicleAction> list) {
		dao.updateStandard(list);
	}

	public List<VehicleStandard> getAllStandard() {
		return dao.getAllStandard();
	}

	public List<MotorCount> getMotorCount(int fillyear) {
		return dao.getMotorCount(fillyear);
	}

	public List<VehicleFactor> getMotorFactor() {
		return dao.getMotorFactor();
	}

	public void addMotorres(List<MotorStat> list) {
		dao.addMotorres(list);
	}

	public void deleteAll(int fillyear) {
		dao.deleteAll(fillyear);
	}

	public List<VehicleAction> getMotorByAccountid(int accountid,int fillyear) {
		return dao.getMotorByAccountid(accountid,fillyear);
	}

	public void deleteAll2(int fillyear) {
		dao.deleteAll2(fillyear);
	}

	public void addMotorres2(List<MotorStat> list) {
		dao.addMotorres2(list);
	}

}
