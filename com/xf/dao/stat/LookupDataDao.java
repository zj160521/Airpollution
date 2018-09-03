package com.xf.dao.stat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

public interface LookupDataDao {
	public List<Company> searchCompany(@Param("city")int city);
	public List<StatDevice> searchStatDevice(Map<String,String> map);
	public List<StatProd> searchStatProduct(Map<String,String> map);
	public String findPollutionName(int pollutionid);
	public String findTradeName(int tradeid);
	
	public List<StatDevice> searchStatDevByYear(int year);
	public List<StatProd> searchStatProByYear(int year);
	
	public List<LookupMonthRes> searchLookupMonthRes(int year,int issmall,int in);
	
	public List<LookupDevicesRes> searchDevices(@Param("year")int year,@Param("ismall")int ismall,@Param("temp")int temp);
	public List<LookupDevicesRes> searchDevices2(int year);
	public List<LookupCityDevicesRes> searchCityDevice(int issmall,int in);
	public List<LookupTradeDevicesRes> searchTradeDevice(int issmall,int tradeid,int in);
	
	public List<LookupCityFeulRes> searchCityFuel(@Param("year")int year,@Param("cityid")int cityid);
	public List<LookupCityFeulRes> searchCityVocs(@Param("year")int year,@Param("type")String type);
	public List<LookupCityFeulRes> searchCityVocs2(@Param("year")int year,@Param("type")String type);
	public List<LookupFeulTypeRes> searchFeulType(int year);
	public List<LookupTradeFeulRes> saerchTradeFeul(@Param("year")int year,@Param("tradeid")int tradeid);
	public List<LookupMonthFeulRes> searchMonthFeul(int year);
	public List<LookupTradeFeulSum> searchTradeFeulSum(@Param("year")int year,@Param("tradeid")int tradeid);
	public List<LookupCityTradeAS> searchCityTradeAS(@Param("year")int year,@Param("tradeid")int tradeid,@Param("cityid")int cityid);
	public List<LookupCityTradeASRes> searchCityTradeTon(@Param("cityid")int cityid,@Param("tradeid")int tradeid,@Param("issmall")int issmall,@Param("in")int in);
	
	public List<LookupTradeFeulRes> searchDeviceCount(int tradeid);
	public List<LookupTradeFeulRes> searchDeviceCount2(int tradeid);
	public Integer deviceTonCount(@Param("start")int start,@Param("end")int end,@Param("issmall")int issmall,@Param("in")int in);
	public List<LookupCityDevicesRes> CityDeviceCount();
	public List<LookupFeulTypeRes> searchFeulDeviceType(int year);
	public List<LookupCityFeulRes> DeviceCityFuel(@Param("year")int year,@Param("cityid")int cityid);
	public List<LookupTradeFeulRes> DeviceTradeFeul(@Param("year")int year,@Param("tradeid")int tradeid);
	public List<LookupCityTradeAS> DeviceCityTradeAS(@Param("year")int year,@Param("tradeid")int tradeid,@Param("cityid")int cityid);
	public List<LookupMonthFeulRes> DeviceMonthFeul(int year); 
	public List<LookupDeviceCityFeulSum> DeviceCityFeulSum(int year);
	public List<LookupTradeFeulSum> DeviceTradeFeulSum(@Param("year")int year,@Param("tradeid")int tradeid);
	public List<LookupCityTradeTechRes> CityTradeTech(@Param("tradeid")int tradeid);
	public List<LookupCityTradeASRes> CityTradeElecSum(@Param("cityid")int cityid,@Param("year")int year,@Param("tradeid")int tradeid);
	public List<LookupCityTradeASRes> facilitiesYearCost(@Param("year")int year,@Param("tradeid")int tradeid,@Param("technique")int technique,@Param("cityid")int cityid);
	public List<YearCostDisrateRes> yearCostDisrate(@Param("technique")int technique);
	public List<OutletTotal> getYearOuteltotal(@Param("trade")int trade,@Param("fillyear")int fillyear);
	public List<CityMaterialSumRes> CityMaterialSum(@Param("pid")int pid,@Param("year")int year);
	public List<Product> getAllProd();
	public List<CityProdSumRes> CityProdSum(@Param("pid")int pid,@Param("year")int year,@Param("issmall")int issmall,@Param("in")int in);
	public List<TradeCityProductSumRes> TradeCityProductSum(@Param("cityid")int cityid,@Param("tradeid")int tradeid,@Param("year")int year,@Param("issmall")int issmall,@Param("in")int in);
	public List<LookupResult> getResult(@Param("cityid")int cityid,@Param("year")int year,@Param("tradeid")int tradeid,@Param("issmall")int issmall,@Param("in")int in);
	public List<LookupResult> getResult1(@Param("cityid")int cityid,@Param("year")int year,@Param("tradeid")int tradeid,@Param("issmall")int issmall,@Param("in")int in);
	public List<LookupCityTradeTechRes> getBycontion(@Param("tradeid")int tradeid,@Param("techniqueid")int techniqueid);
	public List<LookupCityTradeTechRes> getBycontion2(@Param("tradeid")int tradeid,@Param("techniqueid")int techniqueid);
}
