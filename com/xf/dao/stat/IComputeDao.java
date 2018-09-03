package com.xf.dao.stat;

import java.util.List;
import java.util.Map;

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

public interface IComputeDao {
	public List<Fuelres> computefuel(int fillyear);
	public List<Fuelres> computefuel1();
	public List<Prodres> computeprod1(int fillyear);
	public List<Prodres> computeprod2(int fillyear);
	public List<Prodres> computeprod3(int fillyear);
	public List<FuelFactor> findFuelfactor();
	public List<ProdFactor> findProdfactor();
	public void addFuelres(List<StatDevice> list);
	public void addProdres(List<StatProd> list);
	public List<ProdFuelstat> prodfuel(Map<String,String> map);
	public List<Gasstation> getAllGasStation(int fillyear);
	public List<HouseholdFuel> getAllHouseholdFuel(int fillyear);
	public List<Nfertilizer> getAllNfertilizer(int fillyear);
	public List<Nfertilizer> getSumNfertilizer(int fillyear);
	public List<GovStat> getAllFirewood(int fillyear);
	public List<ConstructionDust> getAllConstructiondust(int fillyear);
	public List<Equipment> getAllEquipment(int fillyear);
	public List<EquipmentFarm> getAllEquipmentfarm(int fillyear);
	public List<RoadDust> getAllRoaddust(int fillyear);
	public void deleteAll(int fillyear);
	public List<Gasstation> getAllGasStation2(int fillyear);
	public List<HouseholdFuel> getAllHouseholdFuel2(int fillyear);
	public List<Nfertilizer> getAllNfertilizer2(int fillyear);
	public List<Nfertilizer> getSumNfertilizer2(int fillyear);
	public List<GovStat> getAllFirewood2(int fillyear);
	public List<ConstructionDust> getAllConstructiondust2(int fillyear);
	public List<Equipment> getAllEquipment2(int fillyear);
	public List<EquipmentFarm> getAllEquipmentfarm2(int fillyear);
	public List<RoadDust> getAllRoaddust2(int fillyear);
	public void deleteAll2(int fillyear);
	public void deleteProdDevice(int fillyear);
}
