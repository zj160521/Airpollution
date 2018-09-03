package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.entity.Product;
import com.xf.security.LoginManage;
import com.xf.service.stat.LookupDataService;
import com.xf.vo.CityMaterialSumRes;
import com.xf.vo.CityProdSumRes;
import com.xf.vo.LookupCityDevicesRes;
import com.xf.vo.LookupCityDevicesRes1;
import com.xf.vo.LookupCityDevicesResVo;
import com.xf.vo.LookupCityFeulRes;
import com.xf.vo.LookupCityFeulRes1;
import com.xf.vo.LookupCityFeulResVo;
import com.xf.vo.LookupCityTradeAS;
import com.xf.vo.LookupCityTradeAS1;
import com.xf.vo.LookupCityTradeASRes;
import com.xf.vo.LookupCityTradeASRes1;
import com.xf.vo.LookupCityTradeASVVo;
import com.xf.vo.LookupCityTradeASVo;
import com.xf.vo.LookupCityTradeTechRes;
import com.xf.vo.LookupCityTradeTechRes1;
import com.xf.vo.LookupCityTradeTechVo;
import com.xf.vo.LookupCityTradeTechVo1;
import com.xf.vo.LookupDeviceCityFeulSum;
import com.xf.vo.LookupDeviceTonCount;
import com.xf.vo.LookupDevicesRes;
import com.xf.vo.LookupFeulTypeRes;
import com.xf.vo.LookupMonthFeulRes;
import com.xf.vo.LookupMonthFeulRes1;
import com.xf.vo.LookupMonthRes;
import com.xf.vo.LookupMonthRes1;
import com.xf.vo.LookupMonthVO;
import com.xf.vo.LookupResult;
import com.xf.vo.LookupTradeDeviceRes1;
import com.xf.vo.LookupTradeDeviceVo;
import com.xf.vo.LookupTradeDevicesRes;
import com.xf.vo.LookupTradeFeulRes;
import com.xf.vo.LookupTradeFeulRes1;
import com.xf.vo.LookupTradeFeulSum;
import com.xf.vo.LookupTradeFeulVo;
import com.xf.vo.OutletTotal;
import com.xf.vo.OutletTotalRes;
import com.xf.vo.TradeCityProductSumRes;
import com.xf.vo.TradeCityProductSumRes1;
import com.xf.vo.TradeCityProductSumVo;
import com.xf.vo.YearCostDisrateRes;
import com.xf.vo.YearCostDisrateRes1;
import com.xf.vo.YearCostDisrateVo;

@Scope("prototype")
@Controller
@RequestMapping(value = "/surface")
public class LookupDataController {
	@Autowired
	private LookupDataService lookupService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private Object checkAccount(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	@RequestMapping(value = "/result/{issmall}/{cityid}/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object searchByCityTrade(@PathVariable int issmall,@PathVariable int cityid,
			@PathVariable int year, @PathVariable int tradeid,
			HttpServletRequest request) {
//		Object ret = checkAccount(request);
//		if (ret != null)
//			return ret;

		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<LookupResult> list = lookupService
				.getResult(cityid, year, tradeid,issmall,in);
		List<LookupResult> list1 = lookupService.getResult1(cityid, year,
				tradeid,issmall,in);
		List<LookupResult> nlist = new ArrayList<LookupResult>();
		for (LookupResult res : list) {
			for (LookupResult res1 : list1) {
				if (res.getCityid() == res1.getCityid()
						&& res.getTradeid() == res1.getTradeid()
						&& res.getPollutantid() == res1.getPollutantid()) {
					LookupResult lr = new LookupResult();
					lr.setCityid(res.getCityid());
					lr.setDistrictName(res.getDistrictName());
					lr.setTradeid(res.getTradeid());
					lr.setTradeName(res.getTradeName());
					lr.setPollutantid(res.getPollutantid());
					lr.setPollutantName(res.getPollutantName());
					lr.setSum(res.getSum() + res1.getSum());
					nlist.add(lr);
				}
			}
		}

		// 所有的地区
		HashMap<Integer, String> set = new HashMap<Integer, String>();
		for (LookupResult res : nlist) {
			set.put(res.getCityid(), res.getDistrictName());
		}
		// 所有的行业
		HashMap<Integer, String> set2 = new HashMap<Integer, String>();
		for (LookupResult res : nlist) {
			set2.put(res.getTradeid(), res.getTradeName());
		}
		List<LookupCityTradeTechVo> volist = new ArrayList<LookupCityTradeTechVo>();
		for (Entry<Integer, String> s : set.entrySet()) {
			// 第一层
			LookupCityTradeTechVo vo = new LookupCityTradeTechVo();
			vo.setDistrictName(s.getValue());
			vo.setCityid(s.getKey());
			List<LookupCityTradeTechVo1> reslist = new ArrayList<LookupCityTradeTechVo1>();
			for (Entry<Integer, String> s2 : set2.entrySet()) {
				// 第二层
				LookupCityTradeTechVo1 vo1 = new LookupCityTradeTechVo1();
				vo1.setTradeName(s2.getValue());
				vo1.setTradeid(s2.getKey());
				List<LookupCityTradeTechRes1> volist1 = new ArrayList<LookupCityTradeTechRes1>();
				for (LookupResult res : nlist) {
					if (res.getCityid() == s.getKey()
							&& res.getTradeid() == s2.getKey()) {
						// 第三层
						LookupCityTradeTechRes1 res1 = new LookupCityTradeTechRes1();
						res1.setTechniqueid(res.getPollutantid());
						res1.setName(res.getPollutantName());
						res1.setCount(res.getSum());
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

	@RequestMapping(value = "/month/{issmall}/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchByCityMonth(@PathVariable int issmall,@PathVariable int year,HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		// 查询结果表
		List<LookupMonthRes> reslist = lookupService.searchLookupMonthRes(year,issmall,in);
		ArrayList<LookupMonthRes1> monthList1 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList2 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList3 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList4 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList5 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList6 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList7 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList8 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList9 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList10 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList11 = new ArrayList<LookupMonthRes1>();
		ArrayList<LookupMonthRes1> monthList12 = new ArrayList<LookupMonthRes1>();
		for (LookupMonthRes res : reslist) {
			if (res.getMonth() == 1) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList1.add(res1);
			}
			if (res.getMonth() == 2) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList2.add(res1);
			}
			if (res.getMonth() == 3) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList3.add(res1);
			}
			if (res.getMonth() == 4) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList4.add(res1);
			}
			if (res.getMonth() == 5) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList5.add(res1);
			}
			if (res.getMonth() == 6) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList6.add(res1);
			}
			if (res.getMonth() == 7) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList7.add(res1);
			}
			if (res.getMonth() == 8) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList8.add(res1);
			}
			if (res.getMonth() == 9) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList9.add(res1);
			}
			if (res.getMonth() == 10) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList10.add(res1);
			}
			if (res.getMonth() == 11) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList11.add(res1);
			}
			if (res.getMonth() == 12) {
				LookupMonthRes1 res1 = new LookupMonthRes1();
				res1.setPollutionid(res.getPollutionid());
				res1.setPollutionName(res.getPollutantName());
				res1.setTotal(res.getTotal());
				monthList12.add(res1);
			}
		}
		ArrayList<LookupMonthVO> f = new ArrayList<LookupMonthVO>();

		LookupMonthVO vo1 = new LookupMonthVO();
		vo1.setMonth(1);
		vo1.setName(1);
		vo1.setList(monthList1);
		f.add(vo1);

		LookupMonthVO vo2 = new LookupMonthVO();
		vo2.setMonth(2);
		vo2.setName(2);
		vo2.setList(monthList2);
		f.add(vo2);

		LookupMonthVO vo3 = new LookupMonthVO();
		vo3.setMonth(3);
		vo3.setName(3);
		vo3.setList(monthList3);
		f.add(vo3);

		LookupMonthVO vo4 = new LookupMonthVO();
		vo4.setMonth(4);
		vo4.setName(4);
		vo4.setList(monthList4);
		f.add(vo4);

		LookupMonthVO vo5 = new LookupMonthVO();
		vo5.setMonth(5);
		vo5.setName(5);
		vo5.setList(monthList5);
		f.add(vo5);

		LookupMonthVO vo6 = new LookupMonthVO();
		vo6.setMonth(6);
		vo6.setName(6);
		vo6.setList(monthList6);
		f.add(vo6);

		LookupMonthVO vo7 = new LookupMonthVO();
		vo7.setMonth(7);
		vo7.setName(7);
		vo7.setList(monthList7);
		f.add(vo7);

		LookupMonthVO vo8 = new LookupMonthVO();
		vo8.setMonth(8);
		vo8.setName(8);
		vo8.setList(monthList8);
		f.add(vo8);

		LookupMonthVO vo9 = new LookupMonthVO();
		vo9.setMonth(9);
		vo9.setName(9);
		vo9.setList(monthList9);
		f.add(vo9);

		LookupMonthVO vo10 = new LookupMonthVO();
		vo10.setMonth(10);
		vo10.setName(10);
		vo10.setList(monthList10);
		f.add(vo10);

		LookupMonthVO vo11 = new LookupMonthVO();
		vo11.setMonth(11);
		vo11.setName(11);
		vo11.setList(monthList11);
		f.add(vo11);

		LookupMonthVO vo12 = new LookupMonthVO();
		vo12.setMonth(12);
		vo12.setName(12);
		vo12.setList(monthList12);
		f.add(vo12);

		result.put("list", f);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "{issmall}/devices/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDevices(@PathVariable int year,@PathVariable int issmall,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		
		List<LookupDevicesRes> list = lookupService.searchDevices(year,issmall,in);

		List<LookupDevicesRes> newlist = new ArrayList<LookupDevicesRes>();
		int count = 0;
		for (LookupDevicesRes res1 : list) {
			if (res1.getName().equals("燃煤锅炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else if (res1.getName().equals("燃油锅炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else if (res1.getName().equals("燃气锅炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else if (res1.getName().equals("生物质锅炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else if (res1.getName().equals("垃圾锅炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else if (res1.getName().equals("电锅炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else {
				count = count + res1.getCount();
			}
		}
		LookupDevicesRes res2 = new LookupDevicesRes();
		res2.setName("其它");
		res2.setCount(count);
		newlist.add(res2);

		result.put("list", newlist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/devices2/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDevices2(@PathVariable int year,HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupDevicesRes> list = lookupService.searchDevices2(year);

		List<LookupDevicesRes> newlist = new ArrayList<LookupDevicesRes>();
		int count = 0;
		for (LookupDevicesRes res1 : list) {
			if (res1.getName().equals("层燃炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else if (res1.getName().equals("煤粉炉")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else if (res1.getName().equals("循环流化床")) {
				LookupDevicesRes res2 = new LookupDevicesRes();
				res2.setName(res1.getName());
				res2.setCount(res1.getCount());
				newlist.add(res2);
			} else {
				count = count + res1.getCount();
			}
		}
		LookupDevicesRes res2 = new LookupDevicesRes();
		res2.setName("其它");
		res2.setCount(count);
		newlist.add(res2);

		result.put("res", newlist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/CityDevices/{issmall}/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityDevices(@PathVariable int issmall,HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<LookupCityDevicesRes> list = lookupService.searchCityDevice(issmall,in);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityDevicesRes lcdr : list) {
			set.add(lcdr.getName());
		}
		List<LookupCityDevicesResVo> volist = new ArrayList<LookupCityDevicesResVo>();
		for (String s : set) {
			LookupCityDevicesResVo vo = new LookupCityDevicesResVo();
			vo.setName(s);
			List<LookupCityDevicesRes1> reslist = new ArrayList<LookupCityDevicesRes1>();
			for (LookupCityDevicesRes lcdr : list) {
				if (lcdr.getName().equals(s)) {
					LookupCityDevicesRes1 res1 = new LookupCityDevicesRes1();
					res1.setDeviceTypeid(lcdr.getCityid());
					res1.setName(lcdr.getDistrictName());
					res1.setCount(lcdr.getCount());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		//数据过滤
		List<LookupCityDevicesResVo> volist1 = new ArrayList<LookupCityDevicesResVo>();
		LookupCityDevicesResVo vo1=new LookupCityDevicesResVo();
		vo1.setName("其它");
		List<LookupCityDevicesRes1> newlist1 = new ArrayList<LookupCityDevicesRes1>();
		for (LookupCityDevicesResVo vo : volist) {
			
			if (vo.getName().equals("燃煤锅炉")) {
				volist1.add(vo);
			} else if (vo.getName().equals("燃油锅炉")) {
				volist1.add(vo);
			} else if (vo.getName().equals("燃气锅炉")) {
				volist1.add(vo);
			} else if (vo.getName().equals("生物质锅炉")) {
				volist1.add(vo);
			} else if (vo.getName().equals("垃圾锅炉")) {
				volist1.add(vo);
			} else if (vo.getName().equals("电锅炉")) {
				volist1.add(vo);
			} else {
				for(LookupCityDevicesRes1 res1:vo.getList()){
					newlist1.add(res1);
				}
			}
		}
		HashSet<String> dset = new LinkedHashSet<String>();
		for (LookupCityDevicesRes lcdr : list) {
			dset.add(lcdr.getDistrictName());
		}
		List<LookupCityDevicesRes1> newlist2 = new ArrayList<LookupCityDevicesRes1>();
		for(String s:dset){
			LookupCityDevicesRes1 rr=new LookupCityDevicesRes1();
			rr.setName(s);
			int count=0;
		    for(LookupCityDevicesRes1 r:newlist1){
			    if(r.getName().equals(s)){
			    	count=r.getCount()+count;
			    }
		    }
		    rr.setCount(count);
		    newlist2.add(rr);
		}
		vo1.setList(newlist2);
		volist1.add(vo1);

		//全省合计
		for(LookupCityDevicesResVo vo:volist1){
			LookupCityDevicesRes1 nr=new LookupCityDevicesRes1();
			nr.setName("全省");
			int count=0;
			for(LookupCityDevicesRes1 r:vo.getList()){
				count=r.getCount()+count;
			}
			nr.setCount(count);
			vo.getList().add(nr);
		}
		result.put("list", volist1);
		
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/TradeDevices/{issmall}/{cityid}/{year}/{industryid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchTradeDevices(@PathVariable int issmall,@PathVariable int industryid,
			HttpServletRequest request) {
		
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<LookupTradeDevicesRes> reslist = lookupService
				.searchTradeDevice(issmall,industryid,in);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupTradeDevicesRes ltdr : reslist) {
			set.add(ltdr.getTradeName());
		}

		List<LookupTradeDeviceVo> listvo = new ArrayList<LookupTradeDeviceVo>();
		for (String s : set) {
			LookupTradeDeviceVo vo = new LookupTradeDeviceVo();
			vo.setTradeName(s);
			List<LookupTradeDeviceRes1> list = new ArrayList<LookupTradeDeviceRes1>();
			for (LookupTradeDevicesRes res : reslist) {
				if (s.equals(res.getTradeName())) {
					LookupTradeDeviceRes1 res1 = new LookupTradeDeviceRes1();
					res1.setName(res.getName());
					res1.setDeviceTypeid(res.getDeviceTypeid());
					res1.setCount(res.getCount());
					res1.setTradeName(s);
					list.add(res1);
				}
			}
			vo.setList(list);
			listvo.add(vo);
		}

		for (LookupTradeDeviceVo vo : listvo) {
			List<LookupTradeDeviceRes1> newlist = new ArrayList<LookupTradeDeviceRes1>();
			int count = 0;
			for (LookupTradeDeviceRes1 res1 : vo.getList()) {
				if (res1.getName().equals("燃煤锅炉")) {
					LookupTradeDeviceRes1 res2 = new LookupTradeDeviceRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					res2.setTradeName(res1.getTradeName());
					newlist.add(res2);
				} else if (res1.getName().equals("燃油锅炉")) {
					LookupTradeDeviceRes1 res2 = new LookupTradeDeviceRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					res2.setTradeName(res1.getTradeName());
					newlist.add(res2);
				} else if (res1.getName().equals("燃气锅炉")) {
					LookupTradeDeviceRes1 res2 = new LookupTradeDeviceRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					res2.setTradeName(res1.getTradeName());
					newlist.add(res2);
				} else if (res1.getName().equals("生物质锅炉")) {
					LookupTradeDeviceRes1 res2 = new LookupTradeDeviceRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					res2.setTradeName(res1.getTradeName());
					newlist.add(res2);
				} else if (res1.getName().equals("垃圾锅炉")) {
					LookupTradeDeviceRes1 res2 = new LookupTradeDeviceRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					res2.setTradeName(res1.getTradeName());
					newlist.add(res2);
				} else if (res1.getName().equals("电锅炉")) {
					LookupTradeDeviceRes1 res2 = new LookupTradeDeviceRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					res2.setTradeName(res1.getTradeName());
					newlist.add(res2);
				} else {
					count = count + res1.getCount();
				}
			}
			LookupTradeDeviceRes1 res2 = new LookupTradeDeviceRes1();
			res2.setDeviceTypeid(0);
			res2.setName("其它");
			res2.setCount(count);
			res2.setTradeName(vo.getTradeName());
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("res", listvo);

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/{type}/CityVocs/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityVocs(@PathVariable int year,@PathVariable int typeid,@PathVariable String type,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		
		List<LookupCityFeulRes> list = null;
		if(typeid==1){
			list = lookupService.searchCityVocs2(year, type);
		}else if(typeid==2){
			list = lookupService.searchCityVocs(year, type);
		}
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityFeulRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityFeulResVo> volist = new ArrayList<LookupCityFeulResVo>();
		for (String s : set) {
			LookupCityFeulResVo vo = new LookupCityFeulResVo();
			vo.setDistrictName(s);
			vo.setName(s);
			List<LookupCityFeulRes1> reslist = new ArrayList<LookupCityFeulRes1>();
			for (LookupCityFeulRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityFeulRes1 res1 = new LookupCityFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);
		
		return result.setStatus(0, "ok");
	}
	
	@RequestMapping(value = "/CityFuel/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityFuel(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityFeulRes> list = lookupService.searchCityFuel(year,1);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityFeulRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityFeulResVo> volist = new ArrayList<LookupCityFeulResVo>();
		for (String s : set) {
			LookupCityFeulResVo vo = new LookupCityFeulResVo();
			vo.setDistrictName(s);
			vo.setName(s);
			List<LookupCityFeulRes1> reslist = new ArrayList<LookupCityFeulRes1>();
			for (LookupCityFeulRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityFeulRes1 res1 = new LookupCityFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		for (LookupCityFeulResVo vo : volist) {
			List<LookupCityFeulRes1> newlist = new ArrayList<LookupCityFeulRes1>();
			double count = 0;
			for (LookupCityFeulRes1 res1 : vo.getList()) {
				if (res1.getName().equals("燃煤")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃油")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃气")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("生物质")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("垃圾")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("电")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else {
					count = count + res1.getSum();
				}
			}
			LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
			res2.setFeulid(0);
			res2.setName("其它");
			res2.setSum(count);
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityFuel2/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityFuel2(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityFeulRes> list = lookupService.searchCityFuel(year,0);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityFeulRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityFeulResVo> volist = new ArrayList<LookupCityFeulResVo>();
		for (String s : set) {
			LookupCityFeulResVo vo = new LookupCityFeulResVo();
			vo.setDistrictName(s);
			List<LookupCityFeulRes1> reslist = new ArrayList<LookupCityFeulRes1>();
			for (LookupCityFeulRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityFeulRes1 res1 = new LookupCityFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		for (LookupCityFeulResVo vo : volist) {
			List<LookupCityFeulRes1> newlist = new ArrayList<LookupCityFeulRes1>();
			double count = 0;
			for (LookupCityFeulRes1 res1 : vo.getList()) {
				if (res1.getName().equals("燃煤")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃油")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃气")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("生物质")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("垃圾")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("电")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else {
					count = count + res1.getSum();
				}
			}
			LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
			res2.setFeulid(0);
			res2.setName("其它");
			res2.setSum(count);
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("res", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/FeulType/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchFuelType(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupFeulTypeRes> list = lookupService.searchFeulType(year);

		// List<LookupFeulTypeRes> newlist=new ArrayList<LookupFeulTypeRes>();
		// double count=0;
		// for(LookupFeulTypeRes res1:list){
		// if(res1.getName().equals("燃煤")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("燃油")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("燃气")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("生物质")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("垃圾")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("电")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else{
		// count=count+res1.getSum();
		// }
		// }
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(0);
		// res2.setName("其它");
		// res2.setSum(count);
		// newlist.add(res2);

		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/TradeFeul/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchTradeFeul(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupTradeFeulRes> list = lookupService.saerchTradeFeul(year,
				tradeid);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupTradeFeulRes lcdr : list) {
			set.add(lcdr.getTradeName());
		}
		List<LookupTradeFeulVo> volist = new ArrayList<LookupTradeFeulVo>();
		for (String s : set) {
			LookupTradeFeulVo vo = new LookupTradeFeulVo();
			vo.setTradeName(s);
			List<LookupTradeFeulRes1> reslist = new ArrayList<LookupTradeFeulRes1>();
			for (LookupTradeFeulRes lcdr : list) {
				if (lcdr.getTradeName().equals(s)) {
					LookupTradeFeulRes1 res1 = new LookupTradeFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		for (LookupTradeFeulVo vo : volist) {
			List<LookupTradeFeulRes1> newlist = new ArrayList<LookupTradeFeulRes1>();
			double count = 0;
			for (LookupTradeFeulRes1 res1 : vo.getList()) {
				if (res1.getName().equals("燃煤")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃油")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃气")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("生物质")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("垃圾")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("电")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else {
					count = count + res1.getSum();
				}
			}
			LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
			res2.setFeulid(0);
			res2.setName("其它");
			res2.setSum(count);
			;
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("res", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/MonthFeul/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchMonthFeul(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupMonthFeulRes> res = lookupService.searchMonthFeul(year);
		List<LookupMonthFeulRes1> list = new ArrayList<LookupMonthFeulRes1>();
		for (LookupMonthFeulRes look : res) {
			LookupMonthFeulRes1 res1 = new LookupMonthFeulRes1();
			res1.setName("1月");
			res1.setCount(look.getM1());
			list.add(res1);
			LookupMonthFeulRes1 res2 = new LookupMonthFeulRes1();
			res2.setName("2月");
			res2.setCount(look.getM2());
			list.add(res2);
			LookupMonthFeulRes1 res3 = new LookupMonthFeulRes1();
			res3.setName("3月");
			res3.setCount(look.getM3());
			list.add(res3);
			LookupMonthFeulRes1 res4 = new LookupMonthFeulRes1();
			res4.setName("4月");
			res4.setCount(look.getM4());
			list.add(res4);
			LookupMonthFeulRes1 res5 = new LookupMonthFeulRes1();
			res5.setName("5月");
			res5.setCount(look.getM5());
			list.add(res5);
			LookupMonthFeulRes1 res6 = new LookupMonthFeulRes1();
			res6.setName("6月");
			res6.setCount(look.getM6());
			list.add(res6);
			LookupMonthFeulRes1 res7 = new LookupMonthFeulRes1();
			res7.setName("7月");
			res7.setCount(look.getM7());
			list.add(res7);
			LookupMonthFeulRes1 res8 = new LookupMonthFeulRes1();
			res8.setName("8月");
			res8.setCount(look.getM8());
			list.add(res8);
			LookupMonthFeulRes1 res9 = new LookupMonthFeulRes1();
			res9.setName("9月");
			res9.setCount(look.getM9());
			list.add(res9);
			LookupMonthFeulRes1 res10 = new LookupMonthFeulRes1();
			res10.setName("10月");
			res10.setCount(look.getM10());
			list.add(res10);
			LookupMonthFeulRes1 res11 = new LookupMonthFeulRes1();
			res11.setName("11月");
			res11.setCount(look.getM11());
			list.add(res11);
			LookupMonthFeulRes1 res12 = new LookupMonthFeulRes1();
			res12.setName("12月");
			res12.setCount(look.getM12());
			list.add(res12);
		}
		result.put("list", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/TradeFeulSum/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchTradeFeulSum(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupTradeFeulSum> list = lookupService.searchTradeFeulSum(year,
				tradeid);
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityTradeAS/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityTradeAS(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeAS> list = lookupService.searchCityTradeAS(year,
				tradeid,1);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeAS lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVVo> volist = new ArrayList<LookupCityTradeASVVo>();
		for (String s : set) {
			LookupCityTradeASVVo vo = new LookupCityTradeASVVo();
			vo.setDistrictName(s);
			List<LookupCityTradeAS1> reslist = new ArrayList<LookupCityTradeAS1>();
			for (LookupCityTradeAS lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeAS1 res1 = new LookupCityTradeAS1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setAvgAsh(lcdr.getAvgAsh());
					res1.setAvgS(lcdr.getAvgS());
					res1.setAvgVoc(lcdr.getAvgVoc());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityTradeAS2/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityTradeAS2(@PathVariable int year,@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeAS> list = lookupService.searchCityTradeAS(year,tradeid,0);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeAS lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVVo> volist = new ArrayList<LookupCityTradeASVVo>();
		for (String s : set) {
			LookupCityTradeASVVo vo = new LookupCityTradeASVVo();
			vo.setDistrictName(s);
			List<LookupCityTradeAS1> reslist = new ArrayList<LookupCityTradeAS1>();
			for (LookupCityTradeAS lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeAS1 res1 = new LookupCityTradeAS1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setAvgAsh(lcdr.getAvgAsh());
					res1.setAvgS(lcdr.getAvgS());
					res1.setAvgVoc(lcdr.getAvgVoc());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/CityTradeTon/getdata/{issmall}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityTradeTon(@PathVariable int issmall,@PathVariable int tradeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		
		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<LookupCityTradeASRes> list = lookupService.searchCityTradeTon(1,tradeid,issmall,in);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeASRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVo> volist = new ArrayList<LookupCityTradeASVo>();
		for (String s : set) {
			LookupCityTradeASVo vo = new LookupCityTradeASVo();
			vo.setDistrictName(s);
			List<LookupCityTradeASRes1> reslist = new ArrayList<LookupCityTradeASRes1>();
			for (LookupCityTradeASRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeASRes1 res1 = new LookupCityTradeASRes1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}
	@RequestMapping(value = "/CityTradeTon2/getdata/{issmall}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityTradeTon2(@PathVariable int issmall,@PathVariable int tradeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<LookupCityTradeASRes> list = lookupService.searchCityTradeTon(0,tradeid,issmall,in);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeASRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVo> volist = new ArrayList<LookupCityTradeASVo>();
		for (String s : set) {
			LookupCityTradeASVo vo = new LookupCityTradeASVo();
			vo.setDistrictName(s);
			List<LookupCityTradeASRes1> reslist = new ArrayList<LookupCityTradeASRes1>();
			for (LookupCityTradeASRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeASRes1 res1 = new LookupCityTradeASRes1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/DeviceCount/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceCount(@PathVariable int tradeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupTradeFeulRes> list = lookupService.searchDeviceCount(tradeid);
		List<LookupTradeFeulRes> list2 = lookupService.searchDeviceCount2(tradeid);
		list.addAll(list2);
		
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupTradeFeulRes lcdr : list) {
			set.add(lcdr.getTradeName());
		}
		List<LookupTradeFeulVo> volist = new ArrayList<LookupTradeFeulVo>();
		for (String s : set) {
			LookupTradeFeulVo vo = new LookupTradeFeulVo();
			vo.setTradeName(s);
			for (LookupTradeFeulRes data : list) {
				if (data.getTradeName().equals(s)) {
					vo.setTradeId(data.getTradeid());
					break;
				}
			}
			List<LookupTradeFeulRes1> reslist = new ArrayList<LookupTradeFeulRes1>();
			for (LookupTradeFeulRes lcdr : list) {
				if (lcdr.getTradeName().equals(s)) {
					LookupTradeFeulRes1 res1 = new LookupTradeFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		for (LookupTradeFeulVo vo : volist) {
			List<LookupTradeFeulRes1> newlist = new ArrayList<LookupTradeFeulRes1>();
			double count = 0;
			for (LookupTradeFeulRes1 res1 : vo.getList()) {
				if (res1.getName().equals("水泥窑")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("石灰窑")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("砖瓦窑")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("陶瓷窑")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("玻璃窑")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("烧结")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("球团")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("炼铁")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("炼钢")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("铁合金冶炼")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("有色金属冶炼")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("铸造")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("金属压延加工")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else if (res1.getName().equals("化工")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					newlist.add(res2);
				} else {
					count = count + res1.getSum();
				}
			}
			LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
			res2.setFeulid(0);
			res2.setName("其它");
			res2.setSum(count);
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("res", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/{issmall}/DeviceTonCount/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceTonCount(@PathVariable int issmall,HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
	    
		int count1 = lookupService.deviceTonCount(0, 4, issmall,in);
		int count2 = lookupService.deviceTonCount(4, 10, issmall,in);
		int count3 = lookupService.deviceTonCount(10, 20, issmall,in);
		int count4 = lookupService.deviceTonCount(20, 35, issmall,in);
		int count5 = lookupService.deviceTonCount(35, 65, issmall,in);
		int count6 = lookupService.deviceTonCount(65, 200, issmall,in);
		int count7 = lookupService.deviceTonCount(200, 10000, issmall,in);

		List<LookupDeviceTonCount> list = new ArrayList<LookupDeviceTonCount>();
		LookupDeviceTonCount tc1 = new LookupDeviceTonCount();
		tc1.setName("0-4");
		tc1.setCount(count1);
		list.add(tc1);
		LookupDeviceTonCount tc2 = new LookupDeviceTonCount();
		tc2.setName("4-10");
		tc2.setCount(count2);
		list.add(tc2);
		LookupDeviceTonCount tc3 = new LookupDeviceTonCount();
		tc3.setName("10-20");
		tc3.setCount(count3);
		list.add(tc3);
		LookupDeviceTonCount tc4 = new LookupDeviceTonCount();
		tc4.setName("20-35");
		tc4.setCount(count4);
		list.add(tc4);
		LookupDeviceTonCount tc5 = new LookupDeviceTonCount();
		tc5.setName("35-65");
		tc5.setCount(count5);
		list.add(tc5);
		LookupDeviceTonCount tc6 = new LookupDeviceTonCount();
		tc6.setName("65-200");
		tc6.setCount(count6);
		list.add(tc6);
		LookupDeviceTonCount tc7 = new LookupDeviceTonCount();
		tc7.setName(">200");
		tc7.setCount(count7);
		list.add(tc7);

		result.put("list", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityDeviceCount/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityDeviceCount(HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityDevicesRes> list = lookupService.CityDeviceCount();
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityDevicesRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityDevicesResVo> volist = new ArrayList<LookupCityDevicesResVo>();
		for (String s : set) {
			LookupCityDevicesResVo vo = new LookupCityDevicesResVo();
			vo.setName(s);
			List<LookupCityDevicesRes1> reslist = new ArrayList<LookupCityDevicesRes1>();
			for (LookupCityDevicesRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityDevicesRes1 res1 = new LookupCityDevicesRes1();
					res1.setDeviceTypeid(lcdr.getDeviceTypeid());
					res1.setName(lcdr.getName());
					res1.setCount(lcdr.getCount());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
        //数据过滤
		for (LookupCityDevicesResVo vo : volist) {
			List<LookupCityDevicesRes1> newlist = new ArrayList<LookupCityDevicesRes1>();
			int count = 0;
			for (LookupCityDevicesRes1 res1 : vo.getList()) {
				if (res1.getName().equals("水泥窑")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("石灰窑")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("砖瓦窑")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("陶瓷窑")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("玻璃窑")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("烧结")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("球团")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("炼铁")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("炼钢")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("铁合金冶炼")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("有色金属冶炼")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("铸造")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("金属压延加工")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else if (res1.getName().equals("化工")) {
					LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
					res2.setDeviceTypeid(res1.getDeviceTypeid());
					res2.setName(res1.getName());
					res2.setCount(res1.getCount());
					newlist.add(res2);
				} else {
					count = count + res1.getCount();
				}
			}
			LookupCityDevicesRes1 res2 = new LookupCityDevicesRes1();
			res2.setDeviceTypeid(0);
			res2.setName("其它");
			res2.setCount(count);
			newlist.add(res2);
			vo.setList(newlist);
		}
		//全省合计
		LookupCityDevicesResVo total=new LookupCityDevicesResVo();
		total.setName("全省");
		List<LookupCityDevicesRes1> newlist = new ArrayList<LookupCityDevicesRes1>();
		int count1=0;
		int count2=0;
		int count3=0;
		int count4=0;
		int count5=0;
		int count6=0;
		int count7=0;
		int count8=0;
		int count9=0;
		int count10=0;
		int count11=0;
		int count12=0;
		int count13=0;
		int count14=0;
		int count15=0;
		for(LookupCityDevicesResVo vo : volist){
			for(LookupCityDevicesRes1 res1 : vo.getList()){
				if (res1.getName().equals("水泥窑")) {
					count1=count1+res1.getCount();
				} else if (res1.getName().equals("石灰窑")) {
					count2=count2+res1.getCount();
				} else if (res1.getName().equals("砖瓦窑")) {
					count3=count3+res1.getCount();
				} else if (res1.getName().equals("陶瓷窑")) {
					count4=count4+res1.getCount();
				} else if (res1.getName().equals("玻璃窑")) {
					count5=count5+res1.getCount();
				} else if (res1.getName().equals("烧结")) {
					count6=count6+res1.getCount();
				} else if (res1.getName().equals("球团")) {
					count7=count7+res1.getCount();
				} else if (res1.getName().equals("炼铁")) {
					count8=count8+res1.getCount();
				} else if (res1.getName().equals("炼钢")) {
					count9=count9+res1.getCount();
				} else if (res1.getName().equals("铁合金冶炼")) {
					count10=count10+res1.getCount();
				} else if (res1.getName().equals("有色金属冶炼")) {
					count11=count11+res1.getCount();
				} else if (res1.getName().equals("铸造")) {
					count12=count12+res1.getCount();
				} else if (res1.getName().equals("金属压延加工")) {
					count13=count13+res1.getCount();
				} else if (res1.getName().equals("化工")) {
					count14=count14+res1.getCount();
				} else {
					count15 = count15 + res1.getCount();
				}
			}
		}
		LookupCityDevicesRes1 res1=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res2=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res3=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res4=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res5=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res6=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res7=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res8=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res9=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res10=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res11=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res12=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res13=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res14=new LookupCityDevicesRes1();
		LookupCityDevicesRes1 res15=new LookupCityDevicesRes1();
		res1.setName("水泥窑");
		res2.setName("石灰窑");
		res3.setName("砖瓦窑");
		res4.setName("陶瓷窑");
		res5.setName("玻璃窑");
		res6.setName("烧结");
		res7.setName("球团");
		res8.setName("炼铁");
		res9.setName("炼钢");
		res10.setName("铁合金冶炼");
		res11.setName("有色金属冶炼");
		res12.setName("铸造");
		res13.setName("金属压延加工");
		res14.setName("化工");
		res15.setName("其它");
		res1.setCount(count1);
		res2.setCount(count2);
		res3.setCount(count3);
		res4.setCount(count4);
		res5.setCount(count5);
		res6.setCount(count6);
		res7.setCount(count7);
		res8.setCount(count8);
		res9.setCount(count9);
		res10.setCount(count10);
		res11.setCount(count11);
		res12.setCount(count12);
		res13.setCount(count13);
		res14.setCount(count14);
		res15.setCount(count15);
		newlist.add(res14);
		newlist.add(res11);
		newlist.add(res1);
		newlist.add(res9);
		newlist.add(res8);
		newlist.add(res6);
		newlist.add(res5);
		newlist.add(res7);
		newlist.add(res2);
		newlist.add(res3);
		newlist.add(res13);
		newlist.add(res10);
		newlist.add(res12);
		newlist.add(res4);
		newlist.add(res15);
		total.setList(newlist);
		volist.add(total);
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/FeulDeviceType/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchFeulDeviceType(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupFeulTypeRes> list = lookupService.searchFeulDeviceType(year);

		// List<LookupFeulTypeRes> newlist=new ArrayList<LookupFeulTypeRes>();
		// double count=0;
		// for(LookupFeulTypeRes res1:list){
		// if(res1.getName().equals("燃煤")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("燃油")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("燃气")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("生物质")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("垃圾")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else if(res1.getName().equals("电")){
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(res1.getId());
		// res2.setName(res1.getName());
		// res2.setSum(res1.getSum());;
		// newlist.add(res2);
		// }else{
		// count=count+res1.getSum();
		// }
		// }
		// LookupFeulTypeRes res2 = new LookupFeulTypeRes();
		// res2.setId(0);
		// res2.setName("其它");
		// res2.setSum(count);
		// newlist.add(res2);

		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/DeviceCityFuel/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceCityFuel(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityFeulRes> list = lookupService.DeviceCityFuel(year,1);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityFeulRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityFeulResVo> volist = new ArrayList<LookupCityFeulResVo>();
		for (String s : set) {
			LookupCityFeulResVo vo = new LookupCityFeulResVo();
			vo.setDistrictName(s);
			vo.setName(s);
			List<LookupCityFeulRes1> reslist = new ArrayList<LookupCityFeulRes1>();
			for (LookupCityFeulRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityFeulRes1 res1 = new LookupCityFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		for (LookupCityFeulResVo vo : volist) {
			List<LookupCityFeulRes1> newlist = new ArrayList<LookupCityFeulRes1>();
			double count = 0;
			for (LookupCityFeulRes1 res1 : vo.getList()) {
				if (res1.getName().equals("燃煤")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃油")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃气")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("生物质")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("垃圾")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("电")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else {
					count = count + res1.getSum();
				}
			}
			LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
			res2.setFeulid(0);
			res2.setName("其它");
			res2.setSum(count);
			;
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/DeviceCityFuel2/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceCityFuel2(@PathVariable int year,HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityFeulRes> list = lookupService.DeviceCityFuel(year,0);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityFeulRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityFeulResVo> volist = new ArrayList<LookupCityFeulResVo>();
		for (String s : set) {
			LookupCityFeulResVo vo = new LookupCityFeulResVo();
			vo.setDistrictName(s);
			List<LookupCityFeulRes1> reslist = new ArrayList<LookupCityFeulRes1>();
			for (LookupCityFeulRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityFeulRes1 res1 = new LookupCityFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		for (LookupCityFeulResVo vo : volist) {
			List<LookupCityFeulRes1> newlist = new ArrayList<LookupCityFeulRes1>();
			double count = 0;
			for (LookupCityFeulRes1 res1 : vo.getList()) {
				if (res1.getName().equals("燃煤")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃油")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃气")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("生物质")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("垃圾")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("电")) {
					LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else {
					count = count + res1.getSum();
				}
			}
			LookupCityFeulRes1 res2 = new LookupCityFeulRes1();
			res2.setFeulid(0);
			res2.setName("其它");
			res2.setSum(count);
			;
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("res", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/DeviceTradeFeul/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceTradeFeul(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupTradeFeulRes> list = lookupService.DeviceTradeFeul(year,
				tradeid);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupTradeFeulRes lcdr : list) {
			set.add(lcdr.getTradeName());
		}
		List<LookupTradeFeulVo> volist = new ArrayList<LookupTradeFeulVo>();
		for (String s : set) {
			LookupTradeFeulVo vo = new LookupTradeFeulVo();
			vo.setTradeName(s);
			List<LookupTradeFeulRes1> reslist = new ArrayList<LookupTradeFeulRes1>();
			for (LookupTradeFeulRes lcdr : list) {
				if (lcdr.getTradeName().equals(s)) {
					LookupTradeFeulRes1 res1 = new LookupTradeFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}

		for (LookupTradeFeulVo vo : volist) {
			List<LookupTradeFeulRes1> newlist = new ArrayList<LookupTradeFeulRes1>();
			double count = 0;
			for (LookupTradeFeulRes1 res1 : vo.getList()) {
				if (res1.getName().equals("燃煤")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃油")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("燃气")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("生物质")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("垃圾")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else if (res1.getName().equals("电")) {
					LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
					res2.setFeulid(res1.getFeulid());
					res2.setName(res1.getName());
					res2.setSum(res1.getSum());
					;
					newlist.add(res2);
				} else {
					count = count + res1.getSum();
				}
			}
			LookupTradeFeulRes1 res2 = new LookupTradeFeulRes1();
			res2.setFeulid(0);
			res2.setName("其它");
			res2.setSum(count);
			;
			newlist.add(res2);
			vo.setList(newlist);
		}

		result.put("res", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/DeviceCityTradeAS/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceCityTradeAS(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeAS> list = lookupService.DeviceCityTradeAS(year,tradeid,1);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeAS lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVVo> volist = new ArrayList<LookupCityTradeASVVo>();
		for (String s : set) {
			LookupCityTradeASVVo vo = new LookupCityTradeASVVo();
			vo.setDistrictName(s);
			List<LookupCityTradeAS1> reslist = new ArrayList<LookupCityTradeAS1>();
			for (LookupCityTradeAS lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeAS1 res1 = new LookupCityTradeAS1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setAvgAsh(lcdr.getAvgAsh());
					res1.setAvgS(lcdr.getAvgS());
					res1.setAvgVoc(lcdr.getAvgVoc());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/DeviceCityTradeAS2/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceCityTradeAS2(@PathVariable int year,@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeAS> list = lookupService.DeviceCityTradeAS(year,tradeid,0);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeAS lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVVo> volist = new ArrayList<LookupCityTradeASVVo>();
		for (String s : set) {
			LookupCityTradeASVVo vo = new LookupCityTradeASVVo();
			vo.setDistrictName(s);
			List<LookupCityTradeAS1> reslist = new ArrayList<LookupCityTradeAS1>();
			for (LookupCityTradeAS lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeAS1 res1 = new LookupCityTradeAS1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setAvgAsh(lcdr.getAvgAsh());
					res1.setAvgS(lcdr.getAvgS());
					res1.setAvgVoc(lcdr.getAvgVoc());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/DeviceMonthFeul/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceMonthFeul(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupMonthFeulRes> res = lookupService.DeviceMonthFeul(year);
		List<LookupMonthFeulRes1> list = new ArrayList<LookupMonthFeulRes1>();
		for (LookupMonthFeulRes look : res) {
			LookupMonthFeulRes1 res1 = new LookupMonthFeulRes1();
			res1.setName("1月");
			res1.setCount(look.getM1());
			list.add(res1);
			LookupMonthFeulRes1 res2 = new LookupMonthFeulRes1();
			res2.setName("2月");
			res2.setCount(look.getM2());
			list.add(res2);
			LookupMonthFeulRes1 res3 = new LookupMonthFeulRes1();
			res3.setName("3月");
			res3.setCount(look.getM3());
			list.add(res3);
			LookupMonthFeulRes1 res4 = new LookupMonthFeulRes1();
			res4.setName("4月");
			res4.setCount(look.getM4());
			list.add(res4);
			LookupMonthFeulRes1 res5 = new LookupMonthFeulRes1();
			res5.setName("5月");
			res5.setCount(look.getM5());
			list.add(res5);
			LookupMonthFeulRes1 res6 = new LookupMonthFeulRes1();
			res6.setName("6月");
			res6.setCount(look.getM6());
			list.add(res6);
			LookupMonthFeulRes1 res7 = new LookupMonthFeulRes1();
			res7.setName("7月");
			res7.setCount(look.getM7());
			list.add(res7);
			LookupMonthFeulRes1 res8 = new LookupMonthFeulRes1();
			res8.setName("8月");
			res8.setCount(look.getM8());
			list.add(res8);
			LookupMonthFeulRes1 res9 = new LookupMonthFeulRes1();
			res9.setName("9月");
			res9.setCount(look.getM9());
			list.add(res9);
			LookupMonthFeulRes1 res10 = new LookupMonthFeulRes1();
			res10.setName("10月");
			res10.setCount(look.getM10());
			list.add(res10);
			LookupMonthFeulRes1 res11 = new LookupMonthFeulRes1();
			res11.setName("11月");
			res11.setCount(look.getM11());
			list.add(res11);
			LookupMonthFeulRes1 res12 = new LookupMonthFeulRes1();
			res12.setName("12月");
			res12.setCount(look.getM12());
			list.add(res12);
		}
		result.put("list", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/DeviceCityFeulSum/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceCityFeulSum(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupDeviceCityFeulSum> list = lookupService
				.DeviceCityFeulSum(year);
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/DeviceCityFeulSum/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchDeviceTradeFeulSum(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupTradeFeulSum> list = lookupService.DeviceTradeFeulSum(year,
				tradeid);
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityTradeTech/getdata/{tradeid}/{techniqueid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityTradeTech(@PathVariable int tradeid,@PathVariable int techniqueid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeTechRes> list = lookupService
				.getBycontion(tradeid, techniqueid);
		List<LookupCityTradeTechRes> list2 = lookupService
				.getBycontion2(tradeid, techniqueid);
		list.addAll(list2);
		
		// 所有的地区
		HashMap<Integer, String> set = new HashMap<Integer, String>();
		for (LookupCityTradeTechRes res : list) {
			set.put(res.getCityid(), res.getDistrictName());
		}
		// 所有的行业
		HashMap<Integer, String> set2 = new HashMap<Integer, String>();
		for (LookupCityTradeTechRes res : list) {
			set2.put(res.getTradeid(), res.getTradeName());
		}
		//得到选择的行业
		
		List<LookupCityTradeTechVo> volist = new ArrayList<LookupCityTradeTechVo>();
		for (Entry<Integer, String> s : set.entrySet()) {
			// 第一层
			LookupCityTradeTechVo vo = new LookupCityTradeTechVo();
			vo.setDistrictName(s.getValue());
			vo.setCityid(s.getKey());
			List<LookupCityTradeTechVo1> reslist = new ArrayList<LookupCityTradeTechVo1>();
			for (Entry<Integer, String> s2 : set2.entrySet()) {
				// 第二层
				LookupCityTradeTechVo1 vo1 = new LookupCityTradeTechVo1();
				vo1.setTradeName(s2.getValue());
				vo1.setTradeid(s2.getKey());
				List<LookupCityTradeTechRes1> volist1 = new ArrayList<LookupCityTradeTechRes1>();
				for (LookupCityTradeTechRes res : list) {
					if (res.getCityid() == s.getKey()
							&& res.getTradeid() == s2.getKey()) {
						// 第三层
						LookupCityTradeTechRes1 res1 = new LookupCityTradeTechRes1();
						res1.setName(res.getName());
						res1.setCount(res.getCount());
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

	@RequestMapping(value = "/CityTradeElecSum/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityTradeElecSum(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeASRes> list = lookupService.CityTradeElecSum(1,year,
				tradeid);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeASRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVo> volist = new ArrayList<LookupCityTradeASVo>();
		for (String s : set) {
			LookupCityTradeASVo vo = new LookupCityTradeASVo();
			vo.setDistrictName(s);
			List<LookupCityTradeASRes1> reslist = new ArrayList<LookupCityTradeASRes1>();
			for (LookupCityTradeASRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeASRes1 res1 = new LookupCityTradeASRes1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityTradeElecSum2/getdata/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityTradeElecSum2(@PathVariable int year,
			@PathVariable int tradeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeASRes> list = lookupService.CityTradeElecSum(0,year,
				tradeid);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeASRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVo> volist = new ArrayList<LookupCityTradeASVo>();
		for (String s : set) {
			LookupCityTradeASVo vo = new LookupCityTradeASVo();
			vo.setDistrictName(s);
			List<LookupCityTradeASRes1> reslist = new ArrayList<LookupCityTradeASRes1>();
			for (LookupCityTradeASRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeASRes1 res1 = new LookupCityTradeASRes1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/facilitiesYearCost/{year}/{tradeid}/{techniqueid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchfacilitiesYearCost(@PathVariable int year,
			@PathVariable int tradeid, @PathVariable int techniqueid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeASRes> list = lookupService.facilitiesYearCost(
				year, tradeid, techniqueid,1);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeASRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVo> volist = new ArrayList<LookupCityTradeASVo>();
		for (String s : set) {
			LookupCityTradeASVo vo = new LookupCityTradeASVo();
			vo.setDistrictName(s);
			List<LookupCityTradeASRes1> reslist = new ArrayList<LookupCityTradeASRes1>();
			for (LookupCityTradeASRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeASRes1 res1 = new LookupCityTradeASRes1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/facilitiesYearCost2/{year}/{tradeid}/{techniqueid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchfacilitiesYearCost2(@PathVariable int year,
			@PathVariable int tradeid, @PathVariable int techniqueid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityTradeASRes> list = lookupService.facilitiesYearCost(
				year, tradeid, techniqueid,0);
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityTradeASRes lcdr : list) {
			set.add(lcdr.getDistrictName());
		}
		List<LookupCityTradeASVo> volist = new ArrayList<LookupCityTradeASVo>();
		for (String s : set) {
			LookupCityTradeASVo vo = new LookupCityTradeASVo();
			vo.setDistrictName(s);
			List<LookupCityTradeASRes1> reslist = new ArrayList<LookupCityTradeASRes1>();
			for (LookupCityTradeASRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					LookupCityTradeASRes1 res1 = new LookupCityTradeASRes1();
					res1.setTradeName(lcdr.getTradeName());
					res1.setTradeid(lcdr.getTradeid());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/yearCostDisrate/{techniqueid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchyearCostDisrate(@PathVariable int techniqueid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<YearCostDisrateRes> list = lookupService
				.yearCostDisrate(techniqueid);

		List<YearCostDisrateVo> volist = new ArrayList<YearCostDisrateVo>();
		for (YearCostDisrateRes res : list) {
			YearCostDisrateVo vo = new YearCostDisrateVo();
			vo.setId(res.getId());
			vo.setName(res.getName());
			List<Object> list1 = new ArrayList<Object>();
			YearCostDisrateRes1 res1 = new YearCostDisrateRes1();
			res1.setName("运行费用");
			res1.setValue(res.getYearCost());
			list1.add(res1);
			YearCostDisrateRes1 res2 = new YearCostDisrateRes1();
			res2.setName("去除效率");
			res2.setValue(res.getDisRate());
			list1.add(res2);
			vo.setList(list1);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/getYearTotal/{trade}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	private Object getYearTotal(@PathVariable int trade,
			@PathVariable int fillyear, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		try {
			List<OutletTotal> list = lookupService.getYearOuteltotal(trade,
					fillyear);
			HashSet<String> set = new LinkedHashSet<String>();
			for (OutletTotal lcdr : list) {
				set.add(lcdr.getDistrictName());
			}

			List<LookupCityTradeASVo> volist = new ArrayList<LookupCityTradeASVo>();
			for (String s : set) {
				LookupCityTradeASVo vo = new LookupCityTradeASVo();
				vo.setDistrictName(s);
				List<OutletTotalRes> reslist = new ArrayList<OutletTotalRes>();
				for (OutletTotal lcdr : list) {
					if (lcdr.getDistrictName().equals(s)) {
						OutletTotalRes res1 = new OutletTotalRes();
						res1.setTradeName(lcdr.getTradeName());
						res1.setOutletTotal(lcdr.getOutletTotal());
						reslist.add(res1);
					}
				}
				vo.setOlist(reslist);
				volist.add(vo);
			}

			result.put("res", volist);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityMaterialSum/{pid}/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityMaterialSum(@PathVariable int pid,
			@PathVariable int year, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<CityMaterialSumRes> list = lookupService
				.CityMaterialSum(pid, year);
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/getAllProd", method = RequestMethod.GET)
	@ResponseBody
	private Object searchgetAllProd(HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<Product> list = lookupService.getAllProd();
		result.put("res", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CityProdSum/{issmall}/{pid}/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchCityProdSum(@PathVariable int issmall,@PathVariable int pid,
			@PathVariable int year, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		
		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<CityProdSumRes> list = lookupService.CityProdSum(pid, year, issmall,in);

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

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/facilitiesYearCost/{issmall}/{cityid}/{year}/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchTradeCityProductSum(@PathVariable int tradeid,@PathVariable int year, 
			@PathVariable int issmall,HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<TradeCityProductSumRes> list = lookupService.TradeCityProductSum(1,
				tradeid, year, issmall, in);
		HashSet<String> set = new LinkedHashSet<String>();
		for (TradeCityProductSumRes res : list) {
			set.add(res.getDistrictName());
		}
		List<TradeCityProductSumVo> volist = new ArrayList<TradeCityProductSumVo>();
		for (String s : set) {
			TradeCityProductSumVo vo = new TradeCityProductSumVo();
			vo.setDistrictName(s);
			List<TradeCityProductSumRes1> reslist = new ArrayList<TradeCityProductSumRes1>();
			for (TradeCityProductSumRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					String sp = lcdr.getProductName();
					if (sp.contains("白酒") || sp.contains("发电量") || sp.contains("生铁")
							|| sp.contains("粗钢") || sp.contains("水泥")
							|| sp.contains("平板玻璃") || sp.contains("硫酸")
							|| sp.contains("合成氨")) {
						TradeCityProductSumRes1 res1 = new TradeCityProductSumRes1();
						res1.setProductid(lcdr.getProductid());
						res1.setProductName(lcdr.getProductName());
						res1.setUnit(lcdr.getUnit());
						res1.setSum(lcdr.getSum());
						reslist.add(res1);
					}
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/facilitiesYearCost2/{issmall}/getdata/{tradeid}/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object searchTradeCityProductSum2(@PathVariable int tradeid,@PathVariable int year,
			@PathVariable int issmall,HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		int in = 0;
	    if(issmall < 2){
	    	in = 0;
	    }else if(issmall == 2){
	    	in = 1;
	    }
		List<TradeCityProductSumRes> list = lookupService.TradeCityProductSum(0,
				tradeid, year,issmall,in);
		HashSet<String> set = new LinkedHashSet<String>();
		for (TradeCityProductSumRes res : list) {
			set.add(res.getDistrictName());
		}
		List<TradeCityProductSumVo> volist = new ArrayList<TradeCityProductSumVo>();
		for (String s : set) {
			TradeCityProductSumVo vo = new TradeCityProductSumVo();
			vo.setDistrictName(s);
			List<TradeCityProductSumRes1> reslist = new ArrayList<TradeCityProductSumRes1>();
			for (TradeCityProductSumRes lcdr : list) {
				if (lcdr.getDistrictName().equals(s)) {
					String sp = lcdr.getProductName();
					if (sp.contains("白酒") || sp.contains("发电量") || sp.contains("生铁")
							|| sp.contains("粗钢") || sp.contains("水泥")
							|| sp.contains("平板玻璃") || sp.contains("硫酸")
							|| sp.contains("合成氨")) {
						TradeCityProductSumRes1 res1 = new TradeCityProductSumRes1();
						res1.setProductid(lcdr.getProductid());
						res1.setProductName(lcdr.getProductName());
						res1.setUnit(lcdr.getUnit());
						res1.setSum(lcdr.getSum());
						reslist.add(res1);
					}
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("res", volist);

		return result.setStatus(0, "");
	}
}
