package com.xf.service.stat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.IComputeDao;
import com.xf.entity.gov.ConstructionDust;
import com.xf.entity.gov.Equipment;
import com.xf.entity.gov.EquipmentFarm;
import com.xf.entity.gov.Gasstation;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.HouseholdFuel;
import com.xf.entity.gov.Nfertilizer;
import com.xf.entity.gov.RoadDust;
import com.xf.vo.FuelFactor;
import com.xf.vo.Fuelres;
import com.xf.vo.ProdFactor;
import com.xf.vo.ProdFuelstat;
import com.xf.vo.Prodres;
import com.xf.vo.StatDevice;
import com.xf.vo.StatProd;

@Service
public class ComputeService implements IComputeDao{
	@Autowired
	private IComputeDao dao;

	public List<Fuelres> computefuel(int fillyear) {
		return dao.computefuel(fillyear);
	}

	public List<FuelFactor> findFuelfactor() {
		return dao.findFuelfactor();
	}

	public List<ProdFactor> findProdfactor() {
		return dao.findProdfactor();
	}

	public void addFuelres(List<StatDevice> list) {
		dao.addFuelres(list);
	}

	public void addProdres(List<StatProd> list) {
		dao.addProdres(list);
		
	}

	public List<ProdFuelstat> prodfuel(Map<String, String> map) {
		return dao.prodfuel(map);
	}

	public List<Gasstation> getAllGasStation(int fillyear) {
		return dao.getAllGasStation(fillyear);
	}

	public List<HouseholdFuel> getAllHouseholdFuel(int fillyear) {
		return dao.getAllHouseholdFuel(fillyear);
	}

	public List<Nfertilizer> getAllNfertilizer(int fillyear) {
		return dao.getAllNfertilizer(fillyear);
	}

	public List<GovStat> getAllFirewood(int fillyear) {
		return dao.getAllFirewood(fillyear);
	}

	public List<ConstructionDust> getAllConstructiondust(int fillyear) {
		return dao.getAllConstructiondust(fillyear);
	}

	public List<Equipment> getAllEquipment(int fillyear) {
		return dao.getAllEquipment(fillyear);
	}

	public List<EquipmentFarm> getAllEquipmentfarm(int fillyear) {
		return dao.getAllEquipmentfarm(fillyear);
	}

	public List<RoadDust> getAllRoaddust(int fillyear) {
		return dao.getAllRoaddust(fillyear);
	}

	public void deleteAll(int fillyear) {
		dao.deleteAll(fillyear);
	}

	public List<Nfertilizer> getSumNfertilizer(int fillyear) {
		return dao.getSumNfertilizer(fillyear);
	}

	public void deleteProdDevice(int fillyear) {
		dao.deleteProdDevice(fillyear);
	}

	public List<Prodres> computeprod1(int fillyear) {
		return dao.computeprod1(fillyear);
	}

	public List<Prodres> computeprod2(int fillyear) {
		return dao.computeprod2(fillyear);
	}

	public List<Prodres> computeprod3(int fillyear) {
		return dao.computeprod3(fillyear);
	}

	public List<Fuelres> computefuel1() {
		return dao.computefuel1();
	}

	public List<Gasstation> getAllGasStation2(int fillyear) {
		return dao.getAllGasStation2(fillyear);
	}

	public List<HouseholdFuel> getAllHouseholdFuel2(int fillyear) {
		return dao.getAllHouseholdFuel2(fillyear);
	}

	public List<Nfertilizer> getAllNfertilizer2(int fillyear) {
		return dao.getAllNfertilizer2(fillyear);
	}

	public List<Nfertilizer> getSumNfertilizer2(int fillyear) {
		return dao.getSumNfertilizer2(fillyear);
	}

	public List<GovStat> getAllFirewood2(int fillyear) {
		return dao.getAllFirewood2(fillyear);
	}

	public List<ConstructionDust> getAllConstructiondust2(int fillyear) {
		return dao.getAllConstructiondust2(fillyear);
	}

	public List<Equipment> getAllEquipment2(int fillyear) {
		return dao.getAllEquipment2(fillyear);
	}

	public List<EquipmentFarm> getAllEquipmentfarm2(int fillyear) {
		return dao.getAllEquipmentfarm2(fillyear);
	}

	public List<RoadDust> getAllRoaddust2(int fillyear) {
		return dao.getAllRoaddust2(fillyear);
	}

	public void deleteAll2(int fillyear) {
		dao.deleteAll2(fillyear);
	}
	
}
