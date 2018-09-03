package com.xf.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IImportExcelDao;
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


@Service("excelService")
public class ImportExcelService implements IImportExcelDao{
	@Autowired
	private IImportExcelDao dao;
	
	//有证餐饮添加和删除
	@Transactional
	public void daCanyinCert(List<CanyinCertified> list,int accountid,int fillyear){
		deleteCanyinCert(accountid,fillyear);
		addCanyinCert(list);
	}
	
	@Transactional
	public void daCanyinNoCert(List<CanyinNocert> list,int accountid,int fillyear){
		deleteCanyinNoCert(accountid,fillyear);
		dao.addCanyinNocert(list);
	}
	
	@Transactional
	public void daCanyinStat(List<CanyinStat> list,int accountid,int fillyear){
		deleteCanyinStat(accountid,fillyear);
		dao.addCanyinStat(list);
	}
	
	@Transactional
	public void daGkbatch(List<Gknumber> list,int accountid,int fillyear){
		deleteGknumber(accountid,fillyear);
		dao.addGkbatch(list);
	}
	
	@Transactional
	public void daInbatch(List<Industry> list,int accountid,int fillyear){
		deleteIndustry(accountid,fillyear);
		dao.addInbatch(list);
	}
	
	@Transactional
	public void daOilepot(List<Oildepot> list,int accountid,int fillyear){
		deleteOilepot(accountid,fillyear);
		dao.addOildepot(list);
	}
	
	@Transactional
	public void daGasstation(List<Gasstation> list,int accountid,int fillyear){
		deleteGasstation(accountid,fillyear);
		dao.addGasstation(list);
	}
	
	@Transactional
	public void daAnimalsFarm(List<AnimalsFarm> list,int accountid,int fillyear){
		deleteAnimalsFarm(accountid,fillyear);
		dao.addAnimalsFarm(list);
	}
	
	@Transactional
	public void daAnimalsWild(List<AnimalsWild> list,int accountid,int fillyear){
		deleteAnimalsWild(accountid,fillyear);
		dao.addAnimalsWild(list);
	}
	
	@Transactional
	public void daPlane(List<Plane> list,int accountid,int fillyear){
		deletePlane(accountid,fillyear);
		dao.addPlane(list);
	}
	
	@Transactional
	public void daVehicl(List<VehicleAction> list,int accountid,int fillyear){
		deleteVehicl(accountid,fillyear);
		dao.addVehicl(list);
	}
	
	public void addGkbatch(List<Gknumber> list) {
		dao.addGkbatch(list);
	}
	
	public void addInbatch(List<Industry> list) {
		dao.addInbatch(list);
	}
	
	public void addCanyinCert(List<CanyinCertified> list) {
		dao.addCanyinCert(list);
	}
	
	public void deleteCanyinCert(int accountid,int fillyear) {
		dao.deleteCanyinCert(accountid,fillyear);
	}
	
	public void deleteCanyinNoCert(int accountid,int fillyear) {
		dao.deleteCanyinNoCert(accountid,fillyear);
		
	}
	
	public void deleteCanyinStat(int accountid,int fillyear) {
		dao.deleteCanyinStat(accountid,fillyear);
		
	}
	
	public void deleteOilepot(int accountid,int fillyear) {
		dao.deleteOilepot(accountid,fillyear);
		
	}
	
	public void deleteGasstation(int accountid,int fillyear) {
		dao.deleteGasstation(accountid,fillyear);
		
	}
	
	public void deleteIndustry(int accountid,int fillyear) {
		dao.deleteIndustry(accountid,fillyear);
		
	}
	
	public void deleteAnimalsFarm(int accountid,int fillyear) {
		dao.deleteAnimalsFarm(accountid,fillyear);
		
	}
	
	public void deleteAnimalsWild(int accountid,int fillyear) {
		dao.deleteAnimalsWild(accountid,fillyear);
		
	}
	
	public void deleteGknumber(int accountid,int fillyear) {
		dao.deleteGknumber(accountid,fillyear);
		
	}
	
	public void addCanyinNocert(List<CanyinNocert> list) {
		dao.addCanyinNocert(list);
		
	}
	
	public void addAnimalsFarm(List<AnimalsFarm> list) {
		dao.addAnimalsFarm(list);
		
	}
	
	public void addAnimalsWild(List<AnimalsWild> list) {
		dao.addAnimalsWild(list);
		
	}
	
	public void addCanyinStat(List<CanyinStat> list) {
		dao.addCanyinStat(list);
		
	}
	
	public void addGasstation(List<Gasstation> list) {
		dao.addGasstation(list);
	}
	
	public void addOildepot(List<Oildepot> list) {
		dao.addOildepot(list);
	}

	public List<District> findByCity(int citynum) {
		return dao.findByCity(citynum);
	}
	
	public void addPlane(List<Plane> list) {
		dao.addPlane(list);
	}
	
	public void addVehicl(List<VehicleAction> list) {
		dao.addVehicl(list);
	}
	
	public void deletePlane(int accountid,int fillyear) {
		dao.deletePlane(accountid,fillyear);
	}
	
	public void deleteVehicl(int accountid,int fillyear) {
		dao.deleteVehicl(accountid,fillyear);
	}

	public void deleteDumpField(int accountid, int fillyear) {
		dao.deleteDumpField(accountid, fillyear);
	}

	public void addDumpField(List<DumpField> list) {
		dao.addDumpField(list);
		
	}

	public void addFactor(List<VehicleFactor> list) {
		dao.addFactor(list);
		
	}

	public void deleteFactor() {
		dao.deleteFactor();
	}

	public void addMotor(List<MotorVehicleDb> list) {
		dao.addMotor(list);
		
	}

	public void deleteMoto() {
		dao.deleteMoto();
		
	}

	public void addMotor2(List<MotorVehicleDb> list) {
		dao.addMotor2(list);
	}

}
