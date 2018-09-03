package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.entity.District;
import com.xf.entity.gov.EquipmentFarm;
import com.xf.entity.gov.Gknumber;
import com.xf.entity.gov.RoadDust;
import com.xf.security.LoginManage;
import com.xf.service.DistrictService;
import com.xf.service.gov.EquipmentFarmService;
import com.xf.service.stat.LookupData2Service;
import com.xf.vo.CityNumber;
import com.xf.vo.CityPitchType;
import com.xf.vo.CityProdSumRes;
import com.xf.vo.CityResVo;
import com.xf.vo.FourValues;
import com.xf.vo.GasStationByCity;
import com.xf.vo.LookupCityFeulRes;
import com.xf.vo.LookupCityFeulRes1;
import com.xf.vo.LookupCityFeulResVo;
import com.xf.vo.LookupCityTradeASRes;
import com.xf.vo.LookupCityTradeTechRes1;
import com.xf.vo.LookupCityTradeTechVo;
import com.xf.vo.LookupCityTradeTechVo1;
import com.xf.vo.LookupFeulTypeRes;
import com.xf.vo.LookupMotorStatRes;
import com.xf.vo.NameDoubleValue;
import com.xf.vo.NameFirewood;
import com.xf.vo.NameValue;
import com.xf.vo.NameValue2;
import com.xf.vo.NfertilizerByCityRes;
import com.xf.vo.PollutantStat;
import com.xf.vo.PollutantStat1;
import com.xf.vo.YearCostDisrateRes1;
import com.xf.vo.YearCostDisrateVo;

@Scope("prototype")
@Controller
@RequestMapping(value = "/surface")
public class LookupData2Controller {
	@Autowired
	private LookupData2Service lookupService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private DistrictService disService;
	@Autowired
	private EquipmentFarmService farmService;

	private Object checkAccount(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	@RequestMapping(value = "/Nfertilizer/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getNfertilizerByType(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<LookupFeulTypeRes> list = null;
        if(typeid==1){
        	list = lookupService.getNfertilizerByType2(year);
        }else if(typeid==2){
        	list = lookupService.getNfertilizerByType(year);
        }
		
		result.put("list", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/NfertilizerByMonth/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getNfertilizerByMonth(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<CityProdSumRes> list = null;
        if(typeid==1){
        	list = lookupService.getNfertilizerByMonth2(year);
        }else if(typeid==2){
        	list = lookupService.getNfertilizerByMonth(year);
        }
		
		List<YearCostDisrateVo> volist = new ArrayList<YearCostDisrateVo>();
		for (CityProdSumRes res : list) {
			YearCostDisrateVo vo = new YearCostDisrateVo();
			vo.setId(res.getId());
			vo.setName(res.getDistrictName());

			List<Object> list1 = new ArrayList<Object>();
			YearCostDisrateRes1 res1 = new YearCostDisrateRes1();
			res1.setName("1月");
			res1.setValue(res.getM1());
			res1.setUnit(res.getUnit());
			list1.add(res1);
			YearCostDisrateRes1 res2 = new YearCostDisrateRes1();
			res2.setName("2月");
			res2.setValue(res.getM2());
			res2.setUnit(res.getUnit());
			list1.add(res2);
			YearCostDisrateRes1 res3 = new YearCostDisrateRes1();
			res3.setName("3月");
			res3.setValue(res.getM3());
			res3.setUnit(res.getUnit());
			list1.add(res3);
			YearCostDisrateRes1 res4 = new YearCostDisrateRes1();
			res4.setName("4月");
			res4.setValue(res.getM4());
			res4.setUnit(res.getUnit());
			list1.add(res4);
			YearCostDisrateRes1 res5 = new YearCostDisrateRes1();
			res5.setName("5月");
			res5.setValue(res.getM5());
			res5.setUnit(res.getUnit());
			list1.add(res5);
			YearCostDisrateRes1 res6 = new YearCostDisrateRes1();
			res6.setName("6月");
			res6.setValue(res.getM6());
			res6.setUnit(res.getUnit());
			list1.add(res6);
			YearCostDisrateRes1 res7 = new YearCostDisrateRes1();
			res7.setName("7月");
			res7.setValue(res.getM7());
			res7.setUnit(res.getUnit());
			list1.add(res7);
			YearCostDisrateRes1 res8 = new YearCostDisrateRes1();
			res8.setName("8月");
			res8.setValue(res.getM8());
			res8.setUnit(res.getUnit());
			list1.add(res8);
			YearCostDisrateRes1 res9 = new YearCostDisrateRes1();
			res9.setName("9月");
			res9.setValue(res.getM9());
			res9.setUnit(res.getUnit());
			list1.add(res9);
			YearCostDisrateRes1 res10 = new YearCostDisrateRes1();
			res10.setName("10月");
			res10.setValue(res.getM10());
			res10.setUnit(res.getUnit());
			list1.add(res10);
			YearCostDisrateRes1 res11 = new YearCostDisrateRes1();
			res11.setName("11月");
			res11.setValue(res.getM11());
			res11.setUnit(res.getUnit());
			list1.add(res11);
			YearCostDisrateRes1 res12 = new YearCostDisrateRes1();
			res12.setName("12月");
			res12.setValue(res.getM12());
			res12.setUnit(res.getUnit());
			list1.add(res12);

			vo.setList(list1);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/GasStationByCity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getGasStationByCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<GasStationByCity> list = null;
		if(typeid==1){
			list = lookupService.getGasStationByCity2(year);
		}else if(typeid==2){
			list = lookupService.getGasStationByCity(year);
		}
		
		List<String> dset = new ArrayList<String>();
		dset.add("柴油年吞吐量");
		dset.add("汽油年吞吐量");
		dset.add("燃气年吞吐量");
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (String s : dset) {
			CityResVo vo = new CityResVo();
			vo.setName(s);
			List<Object> volist = new ArrayList<Object>();
			for (GasStationByCity res : list) {
				if (s.equals("柴油年吞吐量")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getDieselGross());
					volist.add(nv1);
				} else if (s.equals("汽油年吞吐量")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getGasolineGross());
					volist.add(nv1);
				} else if (s.equals("燃气年吞吐量")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getNatgasGross());
					volist.add(nv1);
				}
			}
			vo.setList(volist);
			reslist.add(vo);
		}
		
		result.put("list", reslist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/NfertilizerByCity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getNfertilizerByCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<NfertilizerByCityRes> list = null;
		if(typeid==1){
			list = lookupService.getNfertilizerByCity2(year);
		}else if(typeid==2){
			list = lookupService.getNfertilizerByCity(year);
		}
		
		Set<String> set = new LinkedHashSet<String>();
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (NfertilizerByCityRes res : list) {
			set.add(res.getDistrictName());
		}

		for (String s : set) {
			CityResVo vo = new CityResVo();
			vo.setDistrictName(s);
			vo.setName(s);
			List<Object> volist = new ArrayList<Object>();
			for (NfertilizerByCityRes res : list) {
				if (s.equals(res.getDistrictName())) {
					NameDoubleValue nd = new NameDoubleValue();
					nd.setName(res.getName());
					nd.setSumName("施用量");
					nd.setAvgName("每亩耕地施用量(kg/亩)");
					nd.setSumValue(res.getSumValue());
					nd.setAvgValue(res.getAvgValue());
					volist.add(nd);
				}
			}
			vo.setList(volist);
			reslist.add(vo);
		}
		result.put("list", reslist);
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/OildepotByCity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getOildepotByCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<GasStationByCity> list = null;
		if(typeid==1){
			list = lookupService.getOildepot2(year);
		}else if(typeid==2){
			list = lookupService.getOildepot(year);
		}
		
		List<String> dset = new ArrayList<String>();
		dset.add("柴油年吞吐量");
		dset.add("汽油年吞吐量");
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		
		for (String s : dset) {
			CityResVo vo = new CityResVo();
			vo.setName(s);
			List<Object> volist = new ArrayList<Object>();
			for (GasStationByCity res : list) {
				if (s.equals("柴油年吞吐量")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getDieselGross());
					volist.add(nv1);
				} else if (s.equals("汽油年吞吐量")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getGasolineGross());
					volist.add(nv1);
				} 
			}
			vo.setList(volist);
			reslist.add(vo);
		}
		
		result.put("list", reslist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/boilerton/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getGknumber(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<Gknumber> list = null;
        if(typeid==1){
        	list = lookupService.getGknumber2(year);
        }else if(typeid==2){
        	list = lookupService.getGknumber(year);
        }
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		for (Gknumber g : list) {
			if (g.getShippingTon() < 4) {
				count1++;
			}
			if (g.getShippingTon() >= 4 && g.getShippingTon() < 10) {
				count2++;
			}
			if (g.getShippingTon() >= 10 && g.getShippingTon() < 20) {
				count3++;
			}
			if (g.getShippingTon() >= 20 && g.getShippingTon() < 35) {
				count4++;
			}
			if (g.getShippingTon() >= 35) {
				count5++;
			}
		}
		List<NameValue> reslist = new ArrayList<NameValue>();
		NameValue nv1 = new NameValue();
		nv1.setName("小于4蒸吨");
		nv1.setValue(count1);
		nv1.setCount(count1);
		reslist.add(nv1);
		NameValue nv2 = new NameValue();
		nv2.setName("4(含)~10蒸吨");
		nv2.setValue(count2);
		nv2.setCount(count2);
		reslist.add(nv2);
		NameValue nv3 = new NameValue();
		nv3.setName("10(含)~20蒸吨");
		nv3.setValue(count3);
		nv3.setCount(count3);
		reslist.add(nv3);
		NameValue nv4 = new NameValue();
		nv4.setName("20(含)~30蒸吨");
		nv4.setValue(count4);
		reslist.add(nv4);
		nv4.setCount(count4);
		NameValue nv5 = new NameValue();
		nv5.setName("大于等于30蒸吨");
		nv5.setValue(count5);
		nv5.setCount(count5);
		reslist.add(nv5);

		result.put("list", reslist);
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/boilertype/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object getGknumberBytype(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<Gknumber> list1 = lookupService.getGknumberBytype(year, 0, 4);
		List<Gknumber> list2 = lookupService.getGknumberBytype(year, 4, 10);
		List<Gknumber> list3 = lookupService.getGknumberBytype(year, 10, 20);
		List<Gknumber> list4 = lookupService.getGknumberBytype(year, 20, 35);
		List<Gknumber> list5 = lookupService.getGknumberBytype(year, 35, 1000);
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		List<Object> reslist = new ArrayList<Object>();
		if (list1 != null) {
			for (Gknumber g : list1) {
				if (g.getFuelType() == 2001)
					count1++;
				if (g.getFuelType() == 2002)
					count2++;
				if (g.getFuelType() == 2003)
					count3++;
				if (g.getFuelType() == 2004)
					count4++;
				if (g.getFuelType() == 2005)
					count5++;
				if (g.getFuelType() == 2006)
					count6++;
			}
			NameValue nv1 = new NameValue();
			nv1.setName("燃煤");
			nv1.setValue(count1);
			reslist.add(nv1);
			NameValue nv2 = new NameValue();
			nv2.setName("燃油");
			nv2.setValue(count2);
			reslist.add(nv2);
			NameValue nv3 = new NameValue();
			nv3.setName("燃气");
			nv3.setValue(count3);
			reslist.add(nv3);
			NameValue nv4 = new NameValue();
			nv4.setName("生物质");
			nv4.setValue(count4);
			reslist.add(nv4);
			NameValue nv5 = new NameValue();
			nv5.setName("垃圾");
			nv5.setValue(count5);
			reslist.add(nv5);
			NameValue nv6 = new NameValue();
			nv6.setName("电");
			nv6.setValue(count6);
			reslist.add(nv6);
		}

		int count7 = 0;
		int count8 = 0;
		int count9 = 0;
		int count10 = 0;
		int count11 = 0;
		int count12 = 0;
		List<Object> reslist1 = new ArrayList<Object>();
		if (list2 != null) {
			for (Gknumber g : list2) {
				if (g.getFuelType() == 2001)
					count7++;
				if (g.getFuelType() == 2002)
					count8++;
				if (g.getFuelType() == 2003)
					count9++;
				if (g.getFuelType() == 2004)
					count10++;
				if (g.getFuelType() == 2005)
					count11++;
				if (g.getFuelType() == 2006)
					count12++;
			}
			NameValue nv1 = new NameValue();
			nv1.setName("燃煤");
			nv1.setValue(count7);
			reslist1.add(nv1);
			NameValue nv2 = new NameValue();
			nv2.setName("燃油");
			nv2.setValue(count8);
			reslist1.add(nv2);
			NameValue nv3 = new NameValue();
			nv3.setName("燃气");
			nv3.setValue(count9);
			reslist1.add(nv3);
			NameValue nv4 = new NameValue();
			nv4.setName("生物质");
			nv4.setValue(count10);
			reslist1.add(nv4);
			NameValue nv5 = new NameValue();
			nv5.setName("垃圾");
			nv5.setValue(count11);
			reslist1.add(nv5);
			NameValue nv6 = new NameValue();
			nv6.setName("电");
			nv6.setValue(count12);
			reslist1.add(nv6);
		}

		int count13 = 0;
		int count14 = 0;
		int count15 = 0;
		int count16 = 0;
		int count17 = 0;
		int count18 = 0;
		List<Object> reslist2 = new ArrayList<Object>();
		if (list3 != null) {
			for (Gknumber g : list3) {
				if (g.getFuelType() == 2001)
					count13++;
				if (g.getFuelType() == 2002)
					count14++;
				if (g.getFuelType() == 2003)
					count15++;
				if (g.getFuelType() == 2004)
					count16++;
				if (g.getFuelType() == 2005)
					count17++;
				if (g.getFuelType() == 2006)
					count18++;
			}
			NameValue nv1 = new NameValue();
			nv1.setName("燃煤");
			nv1.setValue(count13);
			reslist2.add(nv1);
			NameValue nv2 = new NameValue();
			nv2.setName("燃油");
			nv2.setValue(count14);
			reslist2.add(nv2);
			NameValue nv3 = new NameValue();
			nv3.setName("燃气");
			nv3.setValue(count15);
			reslist2.add(nv3);
			NameValue nv4 = new NameValue();
			nv4.setName("生物质");
			nv4.setValue(count16);
			reslist2.add(nv4);
			NameValue nv5 = new NameValue();
			nv5.setName("垃圾");
			nv5.setValue(count17);
			reslist2.add(nv5);
			NameValue nv6 = new NameValue();
			nv6.setName("电");
			nv6.setValue(count18);
			reslist2.add(nv6);
		}

		int count19 = 0;
		int count20 = 0;
		int count21 = 0;
		int count22 = 0;
		int count23 = 0;
		int count24 = 0;
		List<Object> reslist3 = new ArrayList<Object>();
		if (list4 != null) {
			for (Gknumber g : list4) {
				if (g.getFuelType() == 2001)
					count19++;
				if (g.getFuelType() == 2002)
					count20++;
				if (g.getFuelType() == 2003)
					count21++;
				if (g.getFuelType() == 2004)
					count22++;
				if (g.getFuelType() == 2005)
					count23++;
				if (g.getFuelType() == 2006)
					count24++;
			}
			NameValue nv1 = new NameValue();
			nv1.setName("燃煤");
			nv1.setValue(count19);
			reslist3.add(nv1);
			NameValue nv2 = new NameValue();
			nv2.setName("燃油");
			nv2.setValue(count20);
			reslist3.add(nv2);
			NameValue nv3 = new NameValue();
			nv3.setName("燃气");
			nv3.setValue(count21);
			reslist3.add(nv3);
			NameValue nv4 = new NameValue();
			nv4.setName("生物质");
			nv4.setValue(count22);
			reslist3.add(nv4);
			NameValue nv5 = new NameValue();
			nv5.setName("垃圾");
			nv5.setValue(count23);
			reslist3.add(nv5);
			NameValue nv6 = new NameValue();
			nv6.setName("电");
			nv6.setValue(count24);
			reslist3.add(nv6);
		}

		int count25 = 0;
		int count26 = 0;
		int count27 = 0;
		int count28 = 0;
		int count29 = 0;
		int count30 = 0;
		List<Object> reslist4 = new ArrayList<Object>();
		if (list5 != null) {
			for (Gknumber g : list5) {
				if (g.getFuelType() == 2001)
					count25++;
				if (g.getFuelType() == 2002)
					count26++;
				if (g.getFuelType() == 2003)
					count27++;
				if (g.getFuelType() == 2004)
					count28++;
				if (g.getFuelType() == 2005)
					count29++;
				if (g.getFuelType() == 2006)
					count30++;
			}
			NameValue nv1 = new NameValue();
			nv1.setName("燃煤");
			nv1.setValue(count25);
			reslist4.add(nv1);
			NameValue nv2 = new NameValue();
			nv2.setName("燃油");
			nv2.setValue(count26);
			reslist4.add(nv2);
			NameValue nv3 = new NameValue();
			nv3.setName("燃气");
			nv3.setValue(count27);
			reslist4.add(nv3);
			NameValue nv4 = new NameValue();
			nv4.setName("生物质");
			nv4.setValue(count28);
			reslist4.add(nv4);
			NameValue nv5 = new NameValue();
			nv5.setName("垃圾");
			nv5.setValue(count29);
			reslist4.add(nv5);
			NameValue nv6 = new NameValue();
			nv6.setName("电");
			nv6.setValue(count30);
			reslist4.add(nv6);
		}
		List<CityResVo> list = new ArrayList<CityResVo>();
		CityResVo vo1 = new CityResVo();
		vo1.setDistrictName("小于4蒸吨");
		vo1.setList(reslist);
		list.add(vo1);
		CityResVo vo2 = new CityResVo();
		vo2.setDistrictName("4(含)~10蒸吨");
		vo2.setList(reslist1);
		list.add(vo2);
		CityResVo vo3 = new CityResVo();
		vo3.setDistrictName("10(含)~20蒸吨");
		vo3.setList(reslist2);
		list.add(vo3);
		CityResVo vo4 = new CityResVo();
		vo4.setDistrictName("20(含)~30蒸吨");
		vo4.setList(reslist3);
		list.add(vo4);
		CityResVo vo5 = new CityResVo();
		vo5.setDistrictName("大于等于30蒸吨");
		vo5.setList(reslist4);
		list.add(vo5);

		result.put("res", list);
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/boilercity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getGknumberByCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<NameValue> list1 = null;
		List<NameValue> list2 = null;
		List<NameValue> list3 = null;
		List<NameValue> list4 = null;
		List<NameValue> list5 = null;
        if(typeid==1){
        	list1 = lookupService.getGknumberByCity2(year, 0, 4);
    		list2 = lookupService.getGknumberByCity2(year, 4, 10);
    		list3 = lookupService.getGknumberByCity2(year, 10, 20);
    		list4 = lookupService.getGknumberByCity2(year, 20, 35);
    		list5 = lookupService.getGknumberByCity2(year, 35, 1000);
        }else if(typeid==2){
        	list1 = lookupService.getGknumberByCity(year, 0, 4);
    		list2 = lookupService.getGknumberByCity(year, 4, 10);
    		list3 = lookupService.getGknumberByCity(year, 10, 20);
    		list4 = lookupService.getGknumberByCity(year, 20, 35);
    		list5 = lookupService.getGknumberByCity(year, 35, 1000);
        }
		
		List<String> dset = new ArrayList<String>();
		dset.add("小于4蒸吨");
		dset.add("4(含)~10蒸吨");
		dset.add("10(含)~20蒸吨");
		dset.add("20(含)~30蒸吨");
		dset.add("大于等于30蒸吨");
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (String s : dset) {
			CityResVo vo = new CityResVo();
			vo.setName(s);
			List<Object> volist = new ArrayList<Object>();
			for (NameValue nv1 : list1) {
				if (s.equals("小于4蒸吨")) {
					NameValue v = new NameValue();
					v.setName(nv1.getName());
					v.setValue(nv1.getValue());
					volist.add(v);
				}
			}
			for (NameValue nv1 : list2) {
				if (s.equals("4(含)~10蒸吨")) {
					NameValue v = new NameValue();
					v.setName(nv1.getName());
					v.setValue(nv1.getValue());
					volist.add(v);
				}
			}
			for (NameValue nv1 : list3) {
				if (s.equals("10(含)~20蒸吨")) {
					NameValue v = new NameValue();
					v.setName(nv1.getName());
					v.setValue(nv1.getValue());
					volist.add(v);
				}
			}
			for (NameValue nv1 : list4) {
				if (s.equals("20(含)~30蒸吨")) {
					NameValue v = new NameValue();
					v.setName(nv1.getName());
					v.setValue(nv1.getValue());
					volist.add(v);
				}
			}
			for (NameValue nv1 : list5) {
				if (s.equals("大于等于30蒸吨")) {
					NameValue v = new NameValue();
					v.setName(nv1.getName());
					v.setValue(nv1.getValue());
					volist.add(v);
				}
			}
			vo.setList(volist);
			reslist.add(vo);
		}
		
		result.put("list", reslist);
		
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cateringcity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getCanyinByCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		
		List<CityNumber> list = null;
        if(typeid==1){
        	list = lookupService.getCanyinByCity2(year);
        }else if(typeid==2){
        	list = lookupService.getCanyinByCity(year);
        }
		List<String> dset = new ArrayList<String>();
		dset.add("超大型餐馆");
		dset.add("大型餐馆");
		dset.add("中型餐馆");
		dset.add("小型餐馆");
		dset.add("食堂");
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (String s : dset) {
			CityResVo vo = new CityResVo();
			vo.setName(s);
			List<Object> volist = new ArrayList<Object>();
			for (CityNumber res : list) {
				if (s.equals("超大型餐馆")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getHuge());
					volist.add(nv1);
				} else if (s.equals("大型餐馆")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getBig());
					volist.add(nv1);
				} else if (s.equals("中型餐馆")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getMid());
					volist.add(nv1);
				}else if (s.equals("小型餐馆")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getSmall());
					volist.add(nv1);
				}else if (s.equals("食堂")) {
					NameValue nv1 = new NameValue();
					nv1.setName(res.getDistrictName());
					nv1.setValue(res.getShitang());
					volist.add(nv1);
				}
			}
			vo.setList(volist);
			reslist.add(vo);
		}
		
		result.put("list", reslist);

		
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/firewoodType/getdata/{year}/{city}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getWoodByType(@PathVariable int typeid,@PathVariable int year,
			@PathVariable int city, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<NameFirewood> list = null;
		if(typeid==1){
			list = lookupService.getWoodByType2(year, city);
		}else if(typeid==2){
			list = lookupService.getWoodByType(year, city);
		}
		
		List<CityResVo> reslist = new ArrayList<CityResVo>();

		Set<String> set = new LinkedHashSet<String>();
		for (NameFirewood res : list) {
			set.add(res.getDistrictName());
		}

		for (String s : set) {
			CityResVo vo = new CityResVo();
			vo.setDistrictName(s);
			List<Object> volist = new ArrayList<Object>();
			for (NameFirewood nf : list) {
				if (s.equals(nf.getDistrictName())) {
					vo.setId(nf.getDistrictId());
					CityResVo vo1 = new CityResVo();
					vo1.setId(nf.getId());
					vo1.setDistrictName(nf.getName());
					List<Object> volist1 = new ArrayList<Object>();
					NameValue v1 = new NameValue();
					v1.setName("产量(吨)");
					v1.setValue(nf.getYield());
					volist1.add(v1);
					NameValue v2 = new NameValue();
					v2.setName("秸秆综合利用效率(%)");
					v2.setValue(nf.getUtilizeRatio());
					volist1.add(v2);
					vo1.setList(volist1);
					volist.add(vo1);
				}
			}
			vo.setList(volist);
			reslist.add(vo);
		}

		result.put("res", reslist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/roaddustingtype/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getRoaddustByCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<NameValue> list = null;
        if(typeid==1){
        	list = lookupService.getRoaddustByCity2(year);
        }else if(typeid==2){
        	list = lookupService.getRoaddustByCity(year);
        }
		
		result.put("list", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/roadtype/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getRoaddustByType(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<FourValues> list = null;
		if(typeid==1){
			list = lookupService.getRoadType2(year);
		}else if(typeid==2){
			list = lookupService.getRoadType(year);
		}
		
		List<NameValue> reslist = new ArrayList<NameValue>();
		for (FourValues fv : list) {
			NameValue nv1 = new NameValue();
			nv1.setName("快速路");
			nv1.setValue(fv.getKssum());
			reslist.add(nv1);

			NameValue nv2 = new NameValue();
			nv2.setName("主干道");
			nv2.setValue(fv.getZgsum());
			reslist.add(nv2);

			NameValue nv3 = new NameValue();
			nv3.setName("次干道");
			nv3.setValue(fv.getCgsum());
			reslist.add(nv3);

			NameValue nv4 = new NameValue();
			nv4.setName("支路");
			nv4.setValue(fv.getZsum());
			reslist.add(nv4);
		}
		result.put("list", reslist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/roadcity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getRoadCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<RoadDust> list = null;
		if(typeid==1){
			list = lookupService.getRoadCity2(year);
		}else if(typeid==2){
			list = lookupService.getRoadCity(year);
		}
		
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (RoadDust rd : list) {
			CityResVo vo = new CityResVo();
			vo.setId(rd.getId());
			vo.setDistrictName(rd.getAddressStr());
			List<Object> volist = new ArrayList<Object>();
			Map map = new HashMap();
			List<NameValue> nv1 = new ArrayList<NameValue>();
			map.put("name", "快速路");
			NameValue v1 = new NameValue();
			v1.setName("水泥铺装");
			v1.setValue(rd.getKsCement());
			nv1.add(v1);
			NameValue v2 = new NameValue();
			v2.setName("沥青铺装");
			v2.setValue(rd.getKsPitch());
			nv1.add(v2);
			map.put("value", nv1);
			volist.add(map);
			Map map2 = new HashMap();
			List<NameValue> nv2 = new ArrayList<NameValue>();
			map2.put("name", "主干道");
			NameValue v3 = new NameValue();
			v3.setName("水泥铺装");
			v3.setValue(rd.getZgCement());
			nv2.add(v3);
			NameValue v4 = new NameValue();
			v4.setName("沥青铺装");
			v4.setValue(rd.getZgPitch());
			nv2.add(v4);
			map2.put("value", nv2);
			volist.add(map2);
			Map map3 = new HashMap();
			List<NameValue> nv3 = new ArrayList<NameValue>();
			map3.put("name", "次干道");
			NameValue v5 = new NameValue();
			v5.setName("水泥铺装");
			v5.setValue(rd.getCgCement());
			nv3.add(v5);
			NameValue v6 = new NameValue();
			v6.setName("沥青铺装");
			v6.setValue(rd.getKsPitch());
			nv3.add(v6);
			map3.put("value", nv3);
			volist.add(map3);
			Map map4 = new HashMap();
			List<NameValue> nv4 = new ArrayList<NameValue>();
			map4.put("name", "支路");
			NameValue v7 = new NameValue();
			v7.setName("水泥铺装");
			v7.setValue(rd.getzCement());
			nv4.add(v7);
			NameValue v8 = new NameValue();
			v8.setName("沥青铺装");
			v8.setValue(rd.getzPitch());
			nv4.add(v8);
			map4.put("value", nv4);
			volist.add(map4);
			NameValue v9 = new NameValue();
			v9.setName("未铺装道路");
			v9.setValue(rd.getKsNotShop());
			volist.add(v9);
			vo.setList(volist);
			reslist.add(vo);
		}
		result.put("res", reslist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/roadbycity/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object getRoadByCity(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<RoadDust> list = lookupService.getRoadByCity(year);
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (RoadDust rd : list) {
			CityResVo vo = new CityResVo();
			vo.setId(rd.getId());
			vo.setDistrictName(rd.getAddressStr());
			List<Object> volist = new ArrayList<Object>();
			NameValue v1 = new NameValue();
			v1.setName("快速路");
			v1.setValue(rd.getKsPitch());
			volist.add(v1);
			NameValue v2 = new NameValue();
			v2.setName("主干道");
			v2.setValue(rd.getZgPitch());
			volist.add(v2);
			NameValue v3 = new NameValue();
			v3.setName("次干道");
			v3.setValue(rd.getCgPitch());
			volist.add(v3);
			NameValue v4 = new NameValue();
			v4.setName("支路");
			v4.setValue(rd.getzPitch());
			volist.add(v4);
			vo.setList(volist);
			reslist.add(vo);
		}
		result.put("res", reslist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityroadtype/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object getRoaddustByPitch(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<CityPitchType> list = lookupService.getRoaddustByPitch(year);
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (CityPitchType nf : list) {
			CityResVo vo = new CityResVo();
			vo.setId(nf.getId());
			vo.setDistrictName(nf.getName());
			List<Object> volist = new ArrayList<Object>();
			NameValue v1 = new NameValue();
			v1.setName("快速路");
			v1.setValue(nf.getKsPitch());
			volist.add(v1);
			NameValue v2 = new NameValue();
			v2.setName("主干道");
			v2.setValue(nf.getZgPitch());
			volist.add(v2);
			NameValue v3 = new NameValue();
			v3.setName("次干道");
			v3.setValue(nf.getCgPitch());
			volist.add(v3);
			NameValue v4 = new NameValue();
			v4.setName("支路");
			v4.setValue(nf.getzPitch());
			volist.add(v4);
			vo.setList(volist);
			reslist.add(vo);
		}
		result.put("list", reslist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/constructioncity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getConstructionByCity(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<NameValue2> list = null;
		if(typeid==1){
			list = lookupService.getConstructionByCity_pc(year);
		}else if(typeid==2){
			list = lookupService.getConstructionByCity(year);
		}
		
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (NameValue2 nv : list) {
			CityResVo vo = new CityResVo();
			vo.setId(nv.getId());
			vo.setDistrictName(nv.getName());
			List<Object> volist = new ArrayList<Object>();
			NameValue v1 = new NameValue();
			v1.setName("施工面积");
			v1.setValue(nv.getValue1());
			volist.add(v1);
			NameValue v2 = new NameValue();
			v2.setName("建筑面积");
			v2.setValue(nv.getValue2());
			volist.add(v2);
			vo.setList(volist);
			reslist.add(vo);
		}

		result.put("res", reslist);
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/construction2city/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object getConstructionByCity2(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<NameValue2> list = lookupService.getConstructionByCity2(year);
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (NameValue2 nv : list) {
			CityResVo vo = new CityResVo();
			vo.setId(nv.getId());
			vo.setDistrictName(nv.getName());
			List<Object> volist = new ArrayList<Object>();
			NameValue v1 = new NameValue();
			v1.setName("已开工面积");
			v1.setValue(nv.getValue1());
			volist.add(v1);
			NameValue v2 = new NameValue();
			v2.setName("未开工面积");
			v2.setValue(nv.getValue2());
			volist.add(v2);
			vo.setList(volist);
			reslist.add(vo);
		}

		result.put("res", reslist);
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/construction3city/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object getConstructionByCity3(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<NameValue2> list = lookupService.getConstructionByCity3(year);
		List<CityResVo> reslist = new ArrayList<CityResVo>();
		for (NameValue2 nv : list) {
			CityResVo vo = new CityResVo();
			vo.setId(nv.getId());
			vo.setDistrictName(nv.getName());
			List<Object> volist = new ArrayList<Object>();
			NameValue v1 = new NameValue();
			v1.setName("已开工工地数");
			v1.setValue(nv.getValue1());
			volist.add(v1);
			NameValue v2 = new NameValue();
			v2.setName("未开工工地数");
			v2.setValue(nv.getValue2());
			volist.add(v2);
			vo.setList(volist);
			reslist.add(vo);
		}

		result.put("res", reslist);
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/getplaneType/getdata/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	private Object getPlaneByType(@PathVariable int fillyear,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<NameValue> list = lookupService.getPlaneByType(fillyear);
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/farming/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getFarmingByType(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<NameValue> list = null;
		if(typeid==1){
			list = lookupService.getFarmingByType_pc(fillyear);
		}else if(typeid==2){
			list = lookupService.getFarmingByType(fillyear);
		}
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/farming2/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getFarmingByType2(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<NameValue> list = null;
		if(typeid==1){
			list = lookupService.getFarmingByType2_pc(fillyear);
		}else if(typeid==2){
			list = lookupService.getFarmingByType2(fillyear);
		}
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/farming3/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getFarmingByType3(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<NameValue> list = lookupService.getFarmingByType3(fillyear);
		if(typeid==1){
			list = lookupService.getFarmingByType3_pc(fillyear);
		}else if(typeid==2){
			list = lookupService.getFarmingByType3(fillyear);
		}
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/motorcity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getMotorcity(@PathVariable int typeid,@PathVariable int year,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> polist = null;
			//机动车活动水平省级与市级使用同一份数据
			if(typeid==1){
				polist = lookupService.motorCity(year);
			}else if(typeid==2){
				polist = lookupService.motorCity(year);
			}
			List<PollutantStat> poll = new ArrayList<PollutantStat>();
			List<District> dlist = disService.getByLevel(1);

			for (District dl : dlist) {
				if (polist.size() > 0) {
					for (PollutantStat pl : polist) {
						if (pl.getDistrictName().equals(dl.getDistrictName())) {
							poll.add(pl);
						}
					}
				}
			}

			for (PollutantStat p : poll) {
				if (p != null && p.getName() != null && !p.getName().equals("")) {
					set.add(p.getName());
				}
			}

//			for (District dl : dlist) {
//				double yyk = 0;
//				double yyh = 0;
//				for (PollutantStat po : poll) {
//					if (dl.getDistrictName().equals(po.getDistrictName())) {
//						if (po.getName().equals("小型载客车"))
//							yyk = po.getValue();
//						if (po.getName().equals("轻型载货车"))
//							yyh = po.getValue();
//					}
//				}
//
//				for (PollutantStat po : poll) {
//					if (dl.getDistrictName().equals(po.getDistrictName())) {
//						if (po.getName().equals("微型载客车"))
//							po.setValue(yyk);
//						if (po.getName().equals("微型载货车"))
//							po.setValue(yyh);
//					}
//				}
//			}

			List<PollutantStat1> value = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (str.equals(po.getName())) {
						PollutantStat stat = new PollutantStat();
						stat.setName(po.getDistrictName());
						stat.setValue(po.getValue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				value.add(stlist);
			}
			result.put("list", value);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/motortype/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getMotortype(@PathVariable int typeid,@PathVariable int year,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			double yyk = 0;
			double yyh = 0;
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = lookupService.motorType(year);
			}else if(typeid==2){
				poll = lookupService.motorType(year);
			}
			List<PollutantStat> slist = new ArrayList<PollutantStat>();

			for (PollutantStat po : poll) {
				if (po.getName().equals("小型载客车"))
					yyk = po.getValue();
				if (po.getName().equals("轻型载货车"))
					yyh = po.getValue();
			}

			for (PollutantStat po : poll) {
				PollutantStat stat = new PollutantStat();
				stat.setDistrictName(po.getName());
				stat.setValue(po.getValue());
				stat.setName(po.getName());

				if (po.getName().equals("微型载客车"))
					stat.setValue(yyk);
				if (po.getName().equals("微型载货车"))
					stat.setValue(yyh);

				slist.add(stat);
			}
			result.put("list", slist);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/motorstat/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getMotorstat(@PathVariable int year,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupMotorStatRes> list = lookupService.getMotorStat(year);
		// 所有的车型
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupMotorStatRes res : list) {
			set.add(res.getName());
		}
		// 所有的污染物
		HashSet<String> set2 = new LinkedHashSet<String>();
		for (LookupMotorStatRes res : list) {
			set2.add(res.getPollutantName());
		}
		List<LookupCityTradeTechVo> volist = new ArrayList<LookupCityTradeTechVo>();
		for (String s : set) {
			// 第一层
			LookupCityTradeTechVo vo = new LookupCityTradeTechVo();
			vo.setDistrictName(s);
			List<LookupCityTradeTechVo1> reslist = new ArrayList<LookupCityTradeTechVo1>();
			for (String s2 : set2) {
				// 第二层
				LookupCityTradeTechVo1 vo1 = new LookupCityTradeTechVo1();
				vo1.setTradeName(s2);
				List<LookupCityTradeTechRes1> volist1 = new ArrayList<LookupCityTradeTechRes1>();
				for (LookupMotorStatRes res : list) {
					if (res.getPollutantName().equals(s2)
							&& res.getName().equals(s)) {
						// 第三层
						LookupCityTradeTechRes1 res1 = new LookupCityTradeTechRes1();
						res1.setName(res.getStandard());
						res1.setCount(res.getStatvalue());
						volist1.add(res1);
					}
				}
				vo1.setList(volist1);
				reslist.add(vo1);
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/MotorstatByGas/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getMotorstatByGas(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityFeulRes> list = lookupService.getMotorstatByGas(year);
		if(typeid==1){
			list = lookupService.getMotorstatByGas2(year);
		}else if(typeid==2){
			list = lookupService.getMotorstatByGas(year);
		}
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityFeulRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityFeulResVo> volist = new ArrayList<LookupCityFeulResVo>();
		for (String s : set) {
			LookupCityFeulResVo vo = new LookupCityFeulResVo();
			vo.setName(s);
			List<LookupCityFeulRes1> reslist = new ArrayList<LookupCityFeulRes1>();
			for (LookupCityFeulRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityFeulRes1 res1 = new LookupCityFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setStatvalue(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/MotorstatByMotorType/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getMotorstatByMotorType(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		List<District> dlist = disService.getByLevel(1);
		List<LookupCityTradeASRes> list = null;
		if(typeid==1){
			list = lookupService.getMotorstatByMotorType2(year);
		}else if(typeid==2){
			list = lookupService.getMotorstatByMotorType(year);
		}
		HashSet<String> set = new LinkedHashSet<String>();
		if (list.size() > 0) {
			for (LookupCityTradeASRes lok : list) {
				set.add(lok.getPollutantName());
			}
		}

		List<HashMap<String, Object>> volist = new ArrayList<HashMap<String, Object>>();
		for (District s : dlist) {
			List<HashMap<String, Object>> polist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> pany = new LinkedHashMap<String, Object>();
			pany.put("districtName", s.getDistrictName());
			pany.put("pollutantName", s.getDistrictName());

			for (String poll : set) {
				List<HashMap<String, Object>> sumlist = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> panypoll = new LinkedHashMap<String, Object>();
				panypoll.put("name", poll);

				for (LookupCityTradeASRes lcdr : list) {
					if (lcdr.getDistrictName().equals(s.getDistrictName())
							&& lcdr.getPollutantName().equals(poll)) {
						HashMap<String, Object> panynum = new LinkedHashMap<String, Object>();
						panynum.put("groupName", lcdr.getTradeName());
						panynum.put("statvalue",
								Math.round(lcdr.getSum() * 100) * 0.01d);
						sumlist.add(panynum);
					}
				}
				panypoll.put("list", sumlist);
				polist.add(panypoll);
			}
			pany.put("list", polist);
			volist.add(pany);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/farm/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getfarm(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<HashMap<String, Object>> polist = new ArrayList<HashMap<String, Object>>();
			List<EquipmentFarm> list = null;
			if(typeid==1){
				list = farmService.getThreevalue2(year);
			}else if(typeid==2){
				list = farmService.getThreevalue(year);
			}
			for (EquipmentFarm li : list) {
				HashMap<String, Object> pany = new LinkedHashMap<String, Object>();
				pany.put("name", li.getAddressStr());
				pany.put("number", li.getEnumber());
				pany.put("kw", li.getEkw());
				pany.put("fuelConsume", li.getFuelconsume());
				polist.add(pany);
			}
			result.put("list", polist);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "");
	}
}
