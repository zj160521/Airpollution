package com.xf.dao.stat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.gov.MotorStat;
import com.xf.entity.gov.VehicleAction;
import com.xf.entity.gov.VehicleFactor;
import com.xf.entity.gov.VehicleStandard;
import com.xf.vo.MotorCount;

public interface MotorStandartDao {
	public List<VehicleAction> getAllMotor(int fillyear);
	public List<VehicleAction> getMotorByAccountid(@Param(value="accountid")int accountid,@Param(value="fillyear")int fillyear);
	public List<VehicleStandard> getAllStandard();
	public void updateStandard(@Param("list")List<VehicleAction> list);
	public List<MotorCount> getMotorCount(int fillyear);
	public List<VehicleFactor> getMotorFactor();
	public void addMotorres(List<MotorStat> list);
	public void addMotorres2(List<MotorStat> list);
	public void deleteAll(int fillyear);
	public void deleteAll2(int fillyear);
}
