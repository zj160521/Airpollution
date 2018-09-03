package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.District;
import com.xf.entity.gov.AnimalsFarm;
import com.xf.entity.gov.AnimalsWild;
import com.xf.entity.gov.CanyinCertified;
import com.xf.entity.gov.CanyinNocert;
import com.xf.entity.gov.CanyinStat;
import com.xf.entity.gov.DumpField;
import com.xf.entity.gov.Gasstation;
import com.xf.entity.gov.Gknumber;
import com.xf.entity.gov.Industry;
import com.xf.entity.gov.MotorVehicle;
import com.xf.entity.gov.MotorVehicleDb;
import com.xf.entity.gov.Oildepot;
import com.xf.entity.gov.Plane;
import com.xf.entity.gov.VehicleAction;
import com.xf.entity.gov.VehicleFactor;

public interface IImportExcelDao {
	public void addGkbatch(List<Gknumber> list);
	public void addInbatch(List<Industry> list);
	public void addCanyinCert(List<CanyinCertified> list);
	public void addCanyinNocert(List<CanyinNocert> list);
	public void addAnimalsFarm(List<AnimalsFarm> list);
	public void addAnimalsWild(List<AnimalsWild> list);
	public void addCanyinStat(List<CanyinStat> list);
	public void addGasstation(List<Gasstation> list);
	public void addOildepot(List<Oildepot> list);
	public void addPlane(List<Plane> list);
	public void addVehicl(List<VehicleAction> list);
	public void addDumpField(List<DumpField> list);
	public void addFactor(List<VehicleFactor> list);
	public void addMotor(List<MotorVehicleDb> list);
	public void addMotor2(List<MotorVehicleDb> list);
	
	public void deleteCanyinCert(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteCanyinNoCert(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteCanyinStat(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteOilepot(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteGasstation(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteIndustry(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteAnimalsFarm(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteAnimalsWild(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteGknumber(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deletePlane(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteVehicl(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteDumpField(@Param("accountid")int accountid, @Param("fillyear")int fillyear);
	public void deleteFactor();
	public void deleteMoto();
	
	public List<District> findByCity(int citynum);
}
