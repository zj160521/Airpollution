package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.dao.IEnterpriseDao;
import com.xf.dao.stat.GisStatDao;
import com.xf.entity.District;
import com.xf.entity.Enterprise;
import com.xf.security.LoginManage;
import com.xf.service.DistrictService;
import com.xf.service.PollutantService;
import com.xf.service.stat.GisStatService;
import com.xf.vo.Gis;
import com.xf.vo.GisBoiler;
import com.xf.vo.GisCompany;
import com.xf.vo.GisStatistic;
import com.xf.vo.GisTrade;

@Scope("prototype")
@Controller
@RequestMapping(value = "/gisstat")
public class GisStatController {

	@Autowired
	private GisStatService service;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private PollutantService pollutantService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private IEnterpriseDao enterpriseDao;
	@Autowired
	private GisStatDao gisStatDao;
	
	private Object checkUserAccount(HttpServletRequest request){
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	//市级地区工业源污染物行业组成
	@RequestMapping(value = "/makeUpByTrade", method = RequestMethod.POST)
	@ResponseBody
	public Object getCityTrade(
			@RequestBody Gis gis, 
			HttpServletRequest request) {
		
		Object checkAccount = checkUserAccount(request);
		
		if(checkAccount != null)
			return checkAccount;

		//测试数据
//		Gis gis = new Gis();
//		gis.setCityId(1616);
//		gis.setPollutantid(1);
//		gis.setTradeid(0);
		List<GisTrade> makeUpByTrade = gisStatDao.makeUpByTrade(gis);
		
		@SuppressWarnings("unused")
		HashMap<String, Object> rm = makeUpByTrade.size() > 0 ? result.put("data", makeUpByTrade) : result.put("data", "no data");
		
		return result.setStatus(0, "ok");
	}
	
	//污染企业位置图
	@RequestMapping(value = "/getCompanyGisByTradeAndDistrict", method = RequestMethod.POST)
	@ResponseBody
	public Object getCompanyGisByTradeAndDistrict(
			@RequestBody Gis gis,
			HttpServletRequest request) {
		
		Object checkAccount = checkUserAccount(request);
		
		if(checkAccount != null)
			return checkAccount;

		//测试数据
//		Gis gis = new Gis();
//		gis.setCityId(1);
//		gis.setTradeid(0);
		
		List<GisCompany> companyGisByTradeAndDistrict = gisStatDao.getCompanyGisByTradeAndDistrict(gis);
		
		@SuppressWarnings("unused")
		HashMap<String, Object> rm = companyGisByTradeAndDistrict.size() > 0 ? result.put("data", companyGisByTradeAndDistrict):result.put("data", "no data");
		
		return result.setStatus(0, "ok");
	}
	
	//锅炉位置gis图
	@RequestMapping(value = "/getBoilerGisByDistrict", method = RequestMethod.POST)
	@ResponseBody
	public Object getBoilerGisByDistrict(
			@RequestBody Gis gis,
			HttpServletRequest request) {
		
		Object checkAccount = checkUserAccount(request);
		
		if(checkAccount != null)
			return checkAccount;

		//测试数据
//		Gis gis = new Gis();
//		gis.setCityId(1);
//		gis.setShippingTon(20);
		
		List<GisBoiler> boilerGisByDistrict = gisStatDao.getBoilerGisByDistrict(gis);
		
		@SuppressWarnings("unused")
		HashMap<String, Object> rm = boilerGisByDistrict.size() > 0 ? result.put("data", boilerGisByDistrict):result.put("data", "no data");
		
		return result.setStatus(0, "ok");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getgisdata", method = RequestMethod.POST)
	@ResponseBody
	public Object getGisData(@RequestBody Gis gis, HttpServletRequest request) {

		Object checkAccount = checkUserAccount(request);
		
		if(checkAccount != null)
			return checkAccount;

		int in = 0;
		if (gis.getIssmall() < 2) {
			in = 0;
		} else if (gis.getIssmall() == 2) {
			in = 1;
		}
		int d_year = gis.getEndyear() - gis.getStartyear();

		List<Map> data = new ArrayList<Map>();

		List<District> listCity = districtService.getByLevel(1);
		List<District> listTown = districtService.getByLevel(2);
		List<Enterprise> listEnterprise = enterpriseDao.getAll(
				gis.getIssmall(), in);
		
		List<GisStatistic> listdata = null;
		List<GisStatistic> listdata2 = null;
		listdata = gisStatDao.getNewData(gis.getStartyear(),
					gis.getPollutantid(), gis.getTradeid());


		if (gis.getType().equals("month")) {
			for (int i = 1; i <= 12; i++) {
				Map monthMap = new HashMap();
				monthMap.put("type", "month");
				String s = i < 10 ? gis.getStartyear() + "-0" + i : gis.getStartyear() + "-" + i;
				monthMap.put("value", s);
				
				List<Map> citys = new ArrayList<Map>();
				for (District city : listCity) {
					Map cityMap = new HashMap();
					cityMap.put("type", "city");
					cityMap.put("cityId", city.getId());
					cityMap.put("name", city.getDistrictName());
					cityMap.put("e_point", city.getE_point());
					cityMap.put("n_point", city.getN_point());
					
					double cityData = 0;
					List<Map> towns = new ArrayList<Map>();
					List<Map> pointList = new ArrayList<Map>();
					for (District town : listTown) {
						if (town.getParentId() == city.getId()) {
							Map townMap = new HashMap();
							townMap.put("type", "town");
							townMap.put("name", town.getDistrictName());
							townMap.put("e_point", town.getE_point());
							townMap.put("n_point", town.getN_point());
							double townData = 0;
							for (Enterprise enterprise : listEnterprise) {
								if (enterprise.getTown() == town.getId()) {
									for (GisStatistic gisdata : listdata) {
										if (gisdata.getId() == enterprise
												.getId()
												&& gisdata.getStatmonth() == i) {
											Map enterpriseMap = new HashMap();
											enterpriseMap.put("enterprise",
													enterprise.getId());
											enterpriseMap
													.put("enterpriseName",
															gisdata.getEnterpriceName());
											enterpriseMap.put("e_point",
													enterprise.getE_point());
											enterpriseMap.put("n_point",
													enterprise.getN_point());
											enterpriseMap.put("pollutantName",
													gisdata.getPollutantName());
											enterpriseMap.put("value",
													gisdata.getTotal());
											pointList.add(enterpriseMap);
											townData += gisdata.getTotal();
											cityData += gisdata.getTotal();
										}
									}

								}

							}
							townMap.put("value", townData);
							towns.add(townMap);
						}
					}
					cityMap.put("pointList", pointList);
					cityMap.put("list", towns);
					cityMap.put("value", cityData);
					citys.add(cityMap);
				}
				monthMap.put("list", citys);
				data.add(monthMap);
			}

			result.put("list", data);
		} else if (gis.getType().equals("season")) {

			for (int i = 1; i <= 4; i++) {
				Map<String, Object> monthMap = new HashMap<String, Object>();
				monthMap.put("type", "season");
				if (i < 4) {
					String mon = "0" + ((i - 1) * 3 + 1) + "-01";
					monthMap.put("value", gis.getStartyear() + "-" + mon);
				} else {
					monthMap.put("value", gis.getStartyear() + "-10-01");
				}
				List<Map> citys = new ArrayList<Map>();
				for (District city : listCity) {
					Map<String, Object> cityMap = new HashMap<String, Object>();
					cityMap.put("type", "city");
					cityMap.put("cityId", city.getId());
					cityMap.put("name", city.getDistrictName());
					cityMap.put("e_point", city.getE_point());
					cityMap.put("n_point", city.getN_point());
					double cityData = 0;
					List<Map> towns = new ArrayList<Map>();
					List<Map> pointList = new ArrayList<Map>();
					for (District town : listTown) {
						if (town.getParentId() == city.getId()) {
							Map townMap = new HashMap();
							townMap.put("type", "town");
							townMap.put("name", town.getDistrictName());
							townMap.put("e_point", town.getE_point());
							townMap.put("n_point", town.getN_point());
							double townData = 0;
							for (Enterprise enterprise : listEnterprise) {
								if (enterprise.getTown() == town.getId()) {
									double total = 0;
									Map enterpriseMap = new HashMap();
									for (GisStatistic gisdata : listdata) {
										if (gisdata.getId() == enterprise
												.getId()
												&& gisdata.getStatmonth() > (i - 1) * 3
												&& gisdata.getStatmonth() <= i * 3) {
											total += gisdata.getTotal();
											enterpriseMap.put("enterprise",
													enterprise.getId());
											enterpriseMap
													.put("enterpriseName",
															gisdata.getEnterpriceName());
											enterpriseMap.put("e_point",
													enterprise.getE_point());
											enterpriseMap.put("n_point",
													enterprise.getN_point());
											enterpriseMap.put("pollutantName",
													gisdata.getPollutantName());
											enterpriseMap.put("value", total);
											townData += gisdata.getTotal();
											cityData += gisdata.getTotal();
										}
									}
									if (enterpriseMap.get("value") != null) {
										pointList.add(enterpriseMap);
									}

								}

							}
							townMap.put("value", townData);
							towns.add(townMap);
						}
					}
					cityMap.put("pointList", pointList);
					cityMap.put("list", towns);
					cityMap.put("value", cityData);
					citys.add(cityMap);
				}
				monthMap.put("list", citys);
				data.add(monthMap);
			}

			result.put("list", data);

		} else if (gis.getType().equals("year")) {
			for (int i = 0; i <= d_year; i++) {
				
				listdata2 = gisStatDao.getNewData(gis.getStartyear() + i, gis.getPollutantid(), gis.getTradeid());
				
				Map monthMap = new HashMap();
				monthMap.put("type", "year");
				monthMap.put("value", gis.getStartyear() + i + "-01-01");
				List<Map> citys = new ArrayList<Map>();
				
				for (District city : listCity) {
					Map cityMap = new HashMap();
					cityMap.put("type", "city");
					cityMap.put("cityId", city.getId());
					cityMap.put("name", city.getDistrictName());
					cityMap.put("e_point", city.getE_point());
					cityMap.put("n_point", city.getN_point());
					double cityData = 0;
					List<Map> towns = new ArrayList<Map>();
					List<Map> pointList = new ArrayList<Map>();
					for (District town : listTown) {
						if (town.getParentId() == city.getId()) {
							Map townMap = new HashMap();
							townMap.put("type", "town");
							townMap.put("name", town.getDistrictName());
							townMap.put("e_point", town.getE_point());
							townMap.put("n_point", town.getN_point());
							double townData = 0;
							for (Enterprise enterprise : listEnterprise) {
								if (enterprise.getTown() == town.getId()) {
									Map enterpriseMap = new HashMap();
									double total = 0;
									for (GisStatistic gisdata : listdata2) {
										if (gisdata.getId() == enterprise
												.getId()) {
											total += gisdata.getTotal();
											enterpriseMap.put("enterprise",
													enterprise.getId());
											enterpriseMap
													.put("enterpriseName",
															gisdata.getEnterpriceName());
											enterpriseMap.put("e_point",
													enterprise.getE_point());
											enterpriseMap.put("n_point",
													enterprise.getN_point());
											enterpriseMap.put("pollutantName",
													gisdata.getPollutantName());
											enterpriseMap.put("value", total);
											townData += gisdata.getTotal();
											cityData += gisdata.getTotal();
										}
									}
									if (enterpriseMap.get("value") != null) {
										pointList.add(enterpriseMap);
									}
								}
							}
							townMap.put("value", townData);
							towns.add(townMap);
						}
					}
					cityMap.put("pointList", pointList);
					cityMap.put("list", towns);
					cityMap.put("value", cityData);
					citys.add(cityMap);
				}
				monthMap.put("list", citys);
				data.add(monthMap);
			}

			result.put("list", data);
		}

		return result.setStatus(0, "ok");

	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getyears", method = RequestMethod.GET)
	@ResponseBody
	public Object years(HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}

		int startyear = 2015;

		Calendar cal = Calendar.getInstance();
		int curyear = cal.get(cal.YEAR);

		int num = curyear - startyear;

		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i <= num; i++) {

			int years = 2015 + i;

			list.add(years);

		}

		result.put("list", list);

		return result.setStatus(0, "ok");

	}

}
