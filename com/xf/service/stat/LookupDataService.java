package com.xf.service.stat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.LookupDataDao;
import com.xf.entity.Company;
import com.xf.entity.Product;
import com.xf.vo.CityMaterialSumRes;
import com.xf.vo.CityProdSumRes;
import com.xf.vo.LookupCityDevicesRes;
import com.xf.vo.LookupCityFeulRes;
import com.xf.vo.LookupCityTradeAS;
import com.xf.vo.LookupCityTradeASRes;
import com.xf.vo.LookupCityTradeTechRes;
import com.xf.vo.LookupDeviceCityFeulSum;
import com.xf.vo.LookupDevicesRes;
import com.xf.vo.LookupFeulTypeRes;
import com.xf.vo.LookupMonthFeulRes;
import com.xf.vo.LookupMonthRes;
import com.xf.vo.LookupResult;
import com.xf.vo.LookupTradeDevicesRes;
import com.xf.vo.LookupTradeFeulRes;
import com.xf.vo.LookupTradeFeulSum;
import com.xf.vo.OutletTotal;
import com.xf.vo.StatDevice;
import com.xf.vo.StatProd;
import com.xf.vo.TradeCityProductSumRes;
import com.xf.vo.YearCostDisrateRes;

@Service
public class LookupDataService implements LookupDataDao{
	@Autowired
	private LookupDataDao dao;
	
	public List<Company> searchCompany(int city){
		return dao.searchCompany(city);
	}

	public List<StatDevice> searchStatDevice(Map<String,String> map) {
		return dao.searchStatDevice(map);
	}

	public List<StatProd> searchStatProduct(Map<String,String> map) {
		return dao.searchStatProduct(map);
	}

	public List<StatDevice> searchStatDevByYear(int year) {
		return dao.searchStatDevByYear(year);
	}

	public List<StatProd> searchStatProByYear(int year) {
		return dao.searchStatProByYear(year);
	}

	public String findPollutionName(int pollutionid) {
		return dao.findPollutionName(pollutionid);
	}

	public String findTradeName(int tradeid) {
		return dao.findTradeName(tradeid);
	}

	public List<LookupMonthRes> searchLookupMonthRes(int year,int issmall,int in) {
		return dao.searchLookupMonthRes(year,issmall,in);
	}

	public List<LookupDevicesRes> searchDevices(int year,int ismall,int in) {
		return dao.searchDevices(year,ismall,in);
	}

	public List<LookupCityDevicesRes> searchCityDevice(int issmall,int in) {
		return dao.searchCityDevice(issmall,in);
	}

	public List<LookupTradeDevicesRes> searchTradeDevice(int issmall,int tradeid,int in) {
		return dao.searchTradeDevice(issmall,tradeid,in);
	}

	public List<LookupCityFeulRes> searchCityFuel(int year,int cityid) {
		return dao.searchCityFuel(year,cityid);
	}

	public List<LookupFeulTypeRes> searchFeulType(int year) {
		return dao.searchFeulType(year);
	}

	public List<LookupTradeFeulRes> saerchTradeFeul(int year, int tradeid) {
		return dao.saerchTradeFeul(year, tradeid);
	}

	public List<LookupMonthFeulRes> searchMonthFeul(int year) {
		return dao.searchMonthFeul(year);
	}

	public List<LookupTradeFeulSum> searchTradeFeulSum(int year, int tradeid) {
		return dao.searchTradeFeulSum(year, tradeid);
	}

	public List<LookupCityTradeAS> searchCityTradeAS(int year, int tradeid,int cityid) {
		return dao.searchCityTradeAS(year, tradeid,cityid);
	}

	public List<LookupCityTradeASRes> searchCityTradeTon(int cityid,int tradeid,int issmall,int in) {
		return dao.searchCityTradeTon(cityid,tradeid,issmall,in);
	}

	public List<LookupTradeFeulRes> searchDeviceCount(int tradeid) {
		return dao.searchDeviceCount(tradeid);
	}

	public Integer deviceTonCount(int start, int end,int issmall,int in) {
		return dao.deviceTonCount(start, end, issmall,in);
	}

	public List<LookupCityDevicesRes> CityDeviceCount() {
		return dao.CityDeviceCount();
	}

	public List<LookupFeulTypeRes> searchFeulDeviceType(int year) {
		return dao.searchFeulDeviceType(year);
	}

	public List<LookupCityFeulRes> DeviceCityFuel(int year,int cityid) {
		return dao.DeviceCityFuel(year,cityid);
	}

	public List<LookupTradeFeulRes> DeviceTradeFeul(int year, int tradeid) {
		return dao.DeviceTradeFeul(year, tradeid);
	}

	public List<LookupCityTradeAS> DeviceCityTradeAS(int year, int tradeid,int cityid) {
		return dao.DeviceCityTradeAS(year, tradeid,cityid);
	}

	public List<LookupMonthFeulRes> DeviceMonthFeul(int year) {
		return dao.DeviceMonthFeul(year);
	}

	public List<LookupDeviceCityFeulSum> DeviceCityFeulSum(int year) {
		return dao.DeviceCityFeulSum(year);
	}

	public List<LookupTradeFeulSum> DeviceTradeFeulSum(int year, int tradeid) {
		return dao.DeviceTradeFeulSum(year, tradeid);
	}

	public List<LookupCityTradeTechRes> CityTradeTech(int tradeid) {
		return dao.CityTradeTech(tradeid);
	}

	public List<LookupCityTradeASRes> CityTradeElecSum(int cityid,int year, int tradeid) {
		return dao.CityTradeElecSum(cityid,year, tradeid);
	}

	public List<LookupCityTradeASRes> facilitiesYearCost(int year, int tradeid,
			int technique,int cityid) {
		return dao.facilitiesYearCost(year, tradeid, technique,cityid);
	}

	public List<YearCostDisrateRes> yearCostDisrate(int technique) {
		return dao.yearCostDisrate(technique);
	}

	public List<OutletTotal> getYearOuteltotal( int trade, int fillyear) {
		return dao.getYearOuteltotal( trade, fillyear);
	}

	public List<CityMaterialSumRes> CityMaterialSum(int pid, int year) {
		return dao.CityMaterialSum(pid, year);
	}

	public List<Product> getAllProd() {
		return dao.getAllProd();
	}

	public List<CityProdSumRes> CityProdSum(int pid, int year, int issmall, int in) {
		return dao.CityProdSum(pid, year, issmall,in);
	}

	public List<TradeCityProductSumRes> TradeCityProductSum(int cityid,int tradeid,
			int year, int issmall, int in) {
		return dao.TradeCityProductSum(cityid,tradeid, year,issmall,in);
	}

	public List<LookupResult> getResult(int cityid, int year,int tradeid,int issmall,int in) {
		return dao.getResult(cityid, year,tradeid,issmall,in);
	}

	public List<LookupResult> getResult1(int cityid, int year,int tradeid,int issmall,int in) {
		return dao.getResult1(cityid, year,tradeid,issmall,in);
	}

	public List<LookupDevicesRes> searchDevices2(int year) {
		return dao.searchDevices2(year);
	}

	public List<LookupCityTradeTechRes> getBycontion(int tradeid, int techniqueid) {
		return dao.getBycontion(tradeid, techniqueid);
	}

	public List<LookupCityTradeTechRes> getBycontion2(int tradeid,
			int techniqueid) {
		return dao.getBycontion2(tradeid, techniqueid);
	}

	public List<LookupTradeFeulRes> searchDeviceCount2(int tradeid) {
		return dao.searchDeviceCount2(tradeid);
	}

	public List<LookupCityFeulRes> searchCityVocs(int year, String type) {
		return dao.searchCityVocs(year, type);
	}

	public List<LookupCityFeulRes> searchCityVocs2(int year, String type) {
		return dao.searchCityVocs2(year, type);
	}

}
