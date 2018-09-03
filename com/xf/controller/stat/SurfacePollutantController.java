package com.xf.controller.stat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

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
import com.xf.entity.Pollutant;
import com.xf.entity.gov.Boat;
import com.xf.security.LoginManage;
import com.xf.service.DistrictService;
import com.xf.service.PollutantService;
import com.xf.service.stat.LookupData2Service;
import com.xf.service.stat.NumerationService;
import com.xf.service.stat.SurfacePollutantService;
import com.xf.vo.LookupCityFeulRes;
import com.xf.vo.LookupCityFeulRes1;
import com.xf.vo.LookupCityFeulResVo;
import com.xf.vo.LookupFeulTypeRes;
import com.xf.vo.PollutantStat;
import com.xf.vo.PollutantStat1;
import com.xf.vo.PollutantStatr;
import com.xf.vo.StatProd;

@Scope("prototype")
@Controller
@RequestMapping(value = "/surface")
public class SurfacePollutantController {
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private SurfacePollutantService theService;
	@Autowired
	private DistrictService disService;
	@Autowired
	private LookupData2Service lookupService;
	@Autowired
	private NumerationService numService;
	@Autowired
	private PollutantService pollService;

	private static final int TYPE_NFERTIGATION = 11;
	private static final int TYPE_ANIMAL = 24;
	private static final int TYPE_GARAGE = 27;
	private static final int POLLUTANT_TYPE_NH3 = 10;
	private static final int POLLUTANT_TYPE_VOCS = 9;
	private static final int GROUPID_FARM = 13;
	private static final int GROUPID_CIVIL_FUEL = 29;
	private static final String PM = "PM2.5-10";
	private static final String TRAVELLER_NUM = "旅客周转量";
	private static final String CARGO_NUM = "货物周转量";
	private static final int YX = 50; // 船舶油耗系数

	private Object checkAccount(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	@RequestMapping(value = "/cityPollutant/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCheck(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.getPollutantBycity2(fillyear,null);
			}else if(typeid==2){
				poll = theService.getPollutantBycity(fillyear,null);
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				if (str.equals(PM))
					continue;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setDistrictName(po.getDistrictName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityRestaurant/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getpollutant(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			HashSet<String> type = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
	        if(typeid==1){
	        	poll = theService.getRestaurant2(fillyear);
	        }else if(typeid==2){
	        	poll = theService.getRestaurant(fillyear);
	        }
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
				type.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);
				
				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getDistrictName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(Math.round(po.getStatvalue()*100)/100.0);
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			PollutantStat1 stlist = new PollutantStat1();
			stlist.setName("全省合计");
			
			List<PollutantStat> slist = new ArrayList<PollutantStat>();
			for (String str : type) {
				PollutantStat stat = new PollutantStat();
				stat.setPollutantName(str);
				double count=0;
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						count+=Math.round(po.getStatvalue()*100)/100.0;
					}
				}
				stat.setStatvalue(count);
				slist.add(stat);
			}
			stlist.setList(slist);
			slist1.add(stlist);
			
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityDfuel/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getDfuel(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			HashSet<String> type = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.getPollutantBycity2(fillyear,"dfuel");
			}else if(typeid==2){
				poll = theService.getPollutantBycity(fillyear,"dfuel");
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
				type.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(PM))
						continue;
					if (po.getDistrictName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			PollutantStat1 stlist = new PollutantStat1();
			stlist.setName("全省合计");
			
			List<PollutantStat> slist = new ArrayList<PollutantStat>();
			for (String str : type) {
				if (str.equals(PM)) continue;
				
				PollutantStat stat = new PollutantStat();
				stat.setPollutantName(str);
				double count=0;
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						count+=Math.round(po.getStatvalue()*100)/100.0;
					}
				}
				stat.setStatvalue(count);
				slist.add(stat);
			}
			stlist.setList(slist);
			slist1.add(stlist);
			
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityFirewood/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getFirewood(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll=null;
			if(typeid==1){
				poll = theService.getPollutantBycity2(fillyear,"firewood");
			}else if(typeid==2){
				poll = theService.getPollutantBycity(fillyear,"firewood");
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				if (str.equals(PM))
					continue;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				double count=0;
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setDistrictName(po.getDistrictName());
						stat.setStatvalue(Math.round(po.getStatvalue()*100)/100.0);
						count+=Math.round(po.getStatvalue()*100)/100.0;
						slist.add(stat);
					}
				}
				PollutantStat stat = new PollutantStat();
				stat.setDistrictName("全省合计");
				stat.setStatvalue(count);
				slist.add(stat);
				
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/firewoodType/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getFirewoodType(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			HashSet<String> district = new LinkedHashSet<String>();
			List<PollutantStat> poll = theService.getCityFirewoodType(GROUPID_FARM,fillyear,"firewood");
			if(typeid==1){
				
			}else if(typeid==2){
				poll = theService.getCityFirewoodType(GROUPID_FARM,fillyear,"firewood");
			}
			
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
				district.add(p.getDistrictName());
			}
			
			List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
			for(String dis:district){
				HashMap<String,Object> pany=new LinkedHashMap<String, Object>();
				pany.put("pollutantName", dis);
				List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
				for (String str : set) {
					if (str.equals(PM))
						continue; 
					PollutantStat1 stlist = new PollutantStat1();
					stlist.setName(str);
	
					List<PollutantStat> slist = new ArrayList<PollutantStat>();
					double count=0;
					for (PollutantStat po : poll) {
						if (po.getPollutantName().equals(str)&&po.getDistrictName().equals(dis)) {
							PollutantStat stat = new PollutantStat();
							stat.setFirewoodName(po.getFirewoodName());
							stat.setGroupName(po.getFirewoodName());
							stat.setStatvalue(Math.round(po.getStatvalue()*100)/100.0);
							count+=Math.round(po.getStatvalue()*100)/100.0;
							slist.add(stat);
						}
					}
					PollutantStat stat = new PollutantStat();
					stat.setFirewoodName("农作物合计");
					stat.setGroupName("农作物合计");
					stat.setStatvalue(count);
					slist.add(stat);
					
					stlist.setList(slist);
					slist1.add(stlist);
				}
				pany.put("list", slist1);
				list.add(pany);
			}
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/dfuelType/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getDfuelType(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.getCivilfuel2(fillyear,GROUPID_CIVIL_FUEL);
			}else if(typeid==2){
				poll = theService.getCivilfuel(fillyear,GROUPID_CIVIL_FUEL);
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				if (str.equals(PM))
					continue;
				double sum = 0;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						sum += po.getStatvalue();
					}
				}
				int remainder = 0;
				double count=0;
				for (PollutantStat po : poll) {
					double ratio = 0;
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setFirewoodName(po.getFirewoodName());
						if (sum > 0 && po.getStatvalue() > 0) {
							ratio = Math.round(po.getStatvalue() / sum
									* 1000000);
							if (po.getStatvalue() > 0 && ratio == 0) {
								ratio = 1;
							}
							remainder += ratio;
							if (remainder > 1000000) {
								int w = remainder - 1000000;
								ratio = ratio - w;
							}
						}
						stat.setStatvalue(ratio / 10000);
						stat.setStatvalue2(Math.round(po.getStatvalue()*100)/100.0);
						count+=Math.round(po.getStatvalue()*100)/100.0;
						slist.add(stat);
					}
				}
				PollutantStat stat = new PollutantStat();
				stat.setFirewoodName("燃料合计");
				stat.setStatvalue2(count);
				slist.add(stat);
				
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/nfertigation/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getNfertigation(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<PollutantStat> poll = theService.getNfertigation(fillyear);
			if(typeid==1){
				poll = theService.getNfertigation2(fillyear);
			}else if(typeid==2){
				poll = theService.getNfertigation(fillyear);
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			double count=0;
			for (PollutantStat str : poll) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str.getName());
				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getName().equals(str.getName())) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(Math.round(po.getStatvalue()*100)/100.0);
						count+=Math.round(po.getStatvalue()*100)/100.0;
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			PollutantStat1 stlist = new PollutantStat1();
			stlist.setName("全年");
			List<PollutantStat> slist = new ArrayList<PollutantStat>();
			PollutantStat stat = new PollutantStat();
			stat.setPollutantName("NH3");
			stat.setStatvalue(count);
			slist.add(stat);
			stlist.setList(slist);
			slist1.add(stlist);
			
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityNfertigation/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCitynfertiga(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = theService.cityType(TYPE_NFERTIGATION, POLLUTANT_TYPE_NH3, fillyear,"nfertigation");
			if(typeid==1){
				poll = theService.cityType2(TYPE_NFERTIGATION, POLLUTANT_TYPE_NH3, fillyear,"nfertigation");
			}else if(typeid==2){
				poll = theService.cityType(TYPE_NFERTIGATION, POLLUTANT_TYPE_NH3, fillyear,"nfertigation");
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				double sum = 0;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getDistrictName().equals(str)) {
						sum += po.getStatvalue();
					}
				}
				int remainder = 0;
				double sum2 = 0;
				for (PollutantStat po : poll) {
					double ratio = 0;
					if (po.getDistrictName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setNfertigaName(po.getNfertigaName());
						if (sum > 0 && po.getStatvalue() > 0) {
							ratio = Math.round(po.getStatvalue() / sum
									* 1000000);
							if (po.getStatvalue() > 0 && ratio == 0) {
								ratio = 1;
							}
							remainder += ratio;
							if (remainder > 1000000) {
								int w = remainder - 1000000;
								ratio = ratio - w;
							}
						}
						stat.setStatvalue(ratio / 10000);
						stat.setStatvalue2(po.getStatvalue());
						sum2 += po.getStatvalue();
						slist.add(stat);
					}
				}
				PollutantStat other = new PollutantStat();
				other.setPollutantName("NH3");
				other.setNfertigaName("合计");
				other.setStatvalue(Math.round((sum2/sum2)*100));
				other.setStatvalue2(sum2);
				slist.add(other);

				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityAnimal/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityanimal(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			HashSet<String> setty = new LinkedHashSet<String>();
			HashSet<String> setx = new LinkedHashSet<String>();
			List<PollutantStat> poll = theService.getCityNfertigation(TYPE_ANIMAL, POLLUTANT_TYPE_NH3, fillyear, "animal");
			if(typeid==1){
				poll = theService.getCityNfertigation2(TYPE_ANIMAL, POLLUTANT_TYPE_NH3, fillyear, "animal");
			}else if(typeid==2){
				poll = theService.getCityNfertigation(TYPE_ANIMAL, POLLUTANT_TYPE_NH3, fillyear, "animal");
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
				setx.add(p.getNfertigaName());
				if (p != null && p.getFirewoodName() != null)
					setty.add(p.getFirewoodName());
			}

			List<HashMap<String, Object>> sList = new ArrayList<HashMap<String, Object>>();
			for (String str : set) {
				List<HashMap<String, Object>> slist = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> pany = new LinkedHashMap<String, Object>();
				pany.put("name", str);
				for (String strty : setty) {
					List<HashMap<String, Object>> slistty = new ArrayList<HashMap<String, Object>>();
					HashMap<String, Object> panyty = new LinkedHashMap<String, Object>();
					panyty.put("typeName", strty);
					double count=0;
					for (PollutantStat po : poll) {
						if (po != null && po.getDistrictName().equals(str)
								&& po.getFirewoodName() != null
								&& po.getFirewoodName().equals(strty)) {
							HashMap<String, Object> panyli = new LinkedHashMap<String, Object>();
							panyli.put("pollutantName", po.getPollutantName());
							panyli.put("nfertigaName", po.getNfertigaName());
							panyli.put("statvalue",
									Math.round(po.getStatvalue() * 100) /100.0);
							count+=Math.round(po.getStatvalue() * 100) /100.0;
							slistty.add(panyli);
						}
					}
					HashMap<String, Object> panyli = new LinkedHashMap<String, Object>();
					panyli.put("pollutantName", "NH3");
					panyli.put("nfertigaName", "合计");
					panyli.put("statvalue",
							count);
					slistty.add(panyli);
					panyty.put("nferlist", slistty);
					slist.add(panyty);
				}

				for (int i = 1; i <= 1; i++) {
					List<HashMap<String, Object>> slistty = new ArrayList<HashMap<String, Object>>();
					HashMap<String, Object> panyty = new LinkedHashMap<String, Object>();
					panyty.put("typeName", "合计");
					for (String setxm : setx) {
						double num = 0;
						for (PollutantStat po : poll) {
							if (po != null && po.getDistrictName().equals(str)
									&& po.getNfertigaName().equals(setxm)) {
								num += po.getStatvalue();
							}
						}
						for (PollutantStat po : poll) {
							if (po != null && po.getDistrictName().equals(str)
									&& po.getNfertigaName().equals(setxm)) {
								HashMap<String, Object> panyli = new LinkedHashMap<String, Object>();
								panyli.put("pollutantName",
										po.getPollutantName());
								panyli.put("nfertigaName", po.getNfertigaName());
								panyli.put("statvalue",
										Math.round(num * 100) /100.0);
								slistty.add(panyli);
								break;
							}
						}
					}
					panyty.put("nferlist", slistty);
					slist.add(panyty);
				}
				pany.put("typelist", slist);
				sList.add(pany);
			}
			result.put("list", sList);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityGarage/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getcityGarage(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			HashSet<String> type = new LinkedHashSet<String>();
			List<PollutantStat> poll = theService.cityType(TYPE_GARAGE, POLLUTANT_TYPE_VOCS, fillyear, "garage");
			if(typeid==1){
				poll = theService.cityType2(TYPE_GARAGE, POLLUTANT_TYPE_VOCS, fillyear, "garage");
			}else if(typeid==2){
				poll = theService.cityType(TYPE_GARAGE, POLLUTANT_TYPE_VOCS, fillyear, "garage");
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
				type.add(p.getNfertigaName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getDistrictName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setNfertigaName(po.getNfertigaName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			PollutantStat1 stlist = new PollutantStat1();
			stlist.setName("全省合计");
			
			List<PollutantStat> slist = new ArrayList<PollutantStat>();
			for (String str : type) {
				PollutantStat stat = new PollutantStat();
				stat.setPollutantName(str);
				double count=0;
				for (PollutantStat po : poll) {
					if (po.getNfertigaName().equals(str)) {
						count+=Math.round(po.getStatvalue()*100)/100.0;
					}
				}
				stat.setStatvalue(count);
				slist.add(stat);
			}
			stlist.setList(slist);
			slist1.add(stlist);
			
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityTypefarming/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getTypefarming(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.typeOffarming2(POLLUTANT_TYPE_NH3, fillyear);
			}else if(typeid==2){
				poll = theService.typeOffarming(POLLUTANT_TYPE_NH3, fillyear);
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				double count=0;
				for (PollutantStat po : poll) {
					if (po.getDistrictName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setNfertigaName(po.getNfertigaName());
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(Math.round(po.getStatvalue()*100)/100.0);
						count+=Math.round(po.getStatvalue()*100)/100.0;
						slist.add(stat);
					}
				}
				PollutantStat stat = new PollutantStat();
				stat.setNfertigaName("合计");
				stat.setPollutantName("NH3");
				stat.setStatvalue(count);
				slist.add(stat);
				
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/sourceType/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getSourceType(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.sourceValue2(fillyear);
			}else if(typeid==2){
				poll = theService.sourceValue(fillyear);
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				double count=0;
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setGroupName(po.getGroupName());
						stat.setStatvalue(po.getStatvalue());
						count+=po.getStatvalue();
						slist.add(stat);
					}
				}
				PollutantStat stat = new PollutantStat();
				stat.setGroupName("污染源合计");
				stat.setStatvalue(count);
				slist.add(stat);
				
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/sourceTypeall/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getSourceTypeAll(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<Pollutant> plist = pollService.getTen();
			List<PollutantStat> poll = null;
			List<PollutantStat> stad = null;
			List<PollutantStat> stap = null;

			if(typeid==1){
				poll = theService.sourceValue2(fillyear);
				stad = theService.statDevice(fillyear);
				stap = theService.statProduct(fillyear);
			}else if(typeid==2){
				poll = theService.sourceValue(fillyear);
				stad = theService.statDevice(fillyear);
				stap = theService.statProduct(fillyear);
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (Pollutant str : plist) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str.getPollutantName());

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				double ratio = 0;
				
				double count=0;
				double sum=0;
				for (PollutantStat po : stap) {
					if (po.getPollutantName().equals(str.getPollutantName())) {
						ratio = po.getStatvalue();
					}
				}
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str.getPollutantName())) {
						PollutantStat stat = new PollutantStat();
						stat.setGroupName(po.getGroupName());
						stat.setStatvalue(po.getStatvalue());
						count+=po.getStatvalue();
						slist.add(stat);
					}
				}
				for (PollutantStat po : stad) {
					if (po.getPollutantName().equals(str.getPollutantName())) {
						PollutantStat stat = new PollutantStat();
						stat.setGroupName("工业源");
						stat.setStatvalue(po.getStatvalue() + ratio);
						sum+=(po.getStatvalue()+ ratio);
						slist.add(stat);
					}
				}
				PollutantStat stat = new PollutantStat();
				stat.setGroupName("污染源合计");
				stat.setStatvalue(count+sum);
				slist.add(stat);
				
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/sourceTypecity/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getSourceTypeCity(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<District> dlist = disService.getByLevel(1);
			List<Pollutant> plist = pollService.getTen();
			List<PollutantStat> poll = null;
			List<PollutantStat> stad = null;
			List<PollutantStat> stap = null;

			if(typeid==1){
				poll = theService.citySourceAll2(fillyear);
				stad = theService.cityStatDevice(fillyear);
				stap = theService.cityStatProduct(fillyear);
			}else if(typeid==2){
				poll = theService.citySourceAll(fillyear);
				stad = theService.cityStatDevice(fillyear);
				stap = theService.cityStatProduct(fillyear);
			}
			List<PollutantStatr> polist = new ArrayList<PollutantStatr>();
			for (Pollutant stp : plist) {
				PollutantStatr posta = new PollutantStatr();
				posta.setPollutantName(stp.getPollutantName());

				List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
				for (District str : dlist) {
					PollutantStat1 stlist = new PollutantStat1();
					stlist.setName(str.getDistrictName());
					List<PollutantStat> slist = new ArrayList<PollutantStat>();
					double ratio = 0;
					
					double count=0;
					double sum=0;
					for (PollutantStat po : stap) {
						if (po.getPollutantName()
								.equals(stp.getPollutantName())
								&& po.getDistrictName().equals(
										str.getDistrictName())) {
							ratio = po.getStatvalue();
						}
					}
					for (PollutantStat po : poll) {
						if (po.getPollutantName()
								.equals(stp.getPollutantName())
								&& po.getDistrictName().equals(
										str.getDistrictName())) {
							PollutantStat stat = new PollutantStat();
							stat.setGroupName(po.getGroupName());
							stat.setStatvalue(po.getStatvalue());
							count+=po.getStatvalue();
							slist.add(stat);
						}
					}
					for (PollutantStat po : stad) {
						if (po.getPollutantName()
								.equals(stp.getPollutantName())
								&& po.getDistrictName().equals(
										str.getDistrictName())) {
							PollutantStat stat = new PollutantStat();
							stat.setGroupName("工业源");
							stat.setStatvalue(po.getStatvalue() + ratio);
							sum+=(po.getStatvalue() + ratio);
							slist.add(stat);
						}
					}
					PollutantStat stat = new PollutantStat();
					stat.setGroupName("污染源合计");
					stat.setStatvalue(count+sum);
					slist.add(stat);
					
					stlist.setList(slist);
					slist1.add(stlist);
				}
				posta.setList(slist1);
				polist.add(posta);
			}
			result.put("list", polist);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/boatMonths/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getboatMonths(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.monthsPollutant2(fillyear,"boat");
			}else if(typeid==2){
				poll = theService.monthsPollutant(fillyear,"boat");
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (int i = 1; i <= 12; i++) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(Integer.toString(i));
				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(PM))
						continue;
					if (po.getName().equals(Integer.toString(i))) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityBoat/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityboat(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = theService.getPollutantBycity(fillyear,"boat");
			if(typeid==1){
				poll = theService.getPollutantBycity2(fillyear,"boat");
			}else if(typeid==2){
				poll = theService.getPollutantBycity(fillyear,"boat");
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				if (str.equals(PM))
					continue;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setDistrictName(po.getDistrictName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityAgricultural/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityAgricultural(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.getPollutantBycity2(fillyear,"agriculturemachine");
			}else if(typeid==2){
				poll = theService.getPollutantBycity(fillyear,"agriculturemachine");
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(PM))
						continue;
					if (po.getDistrictName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityConstruction/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityConstruction(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.getPollutantBycity2(fillyear,"constructionmachine");
			}else if(typeid==2){
				poll = theService.getPollutantBycity(fillyear,"constructionmachine");
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(PM))
						continue;
					if (po.getDistrictName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityRoaddust/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityroaddust(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = theService.cityPollutantPm(fillyear,"roaddust");
			if(typeid==1){
				poll = theService.cityPollutantPm2(fillyear,"roaddust");
			}else if(typeid==2){
				poll = theService.cityPollutantPm(fillyear,"roaddust");
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				if (str.equals(PM))
					continue;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setDistrictName(po.getDistrictName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/cityConstrucdust/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityconstruction(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.cityPollutantPm2(fillyear,"constructiondust");
			}else if(typeid==2){
				poll = theService.cityPollutantPm(fillyear,"constructiondust");
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				if (str.equals(PM))
					continue;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setDistrictName(po.getDistrictName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/roaddusttype/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getRoaddustByType(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityFeulRes> list = null;
		if(typeid==1){
			list = lookupService.getRoaddustBytype2(fillyear);
		}else if(typeid==2){
			list = lookupService.getRoaddustBytype(fillyear);
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
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/pollutantConstruction/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityconstruc(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.getFirewoodType2(fillyear,"constructionmachine", 20);
			}else if(typeid==2){
				poll = theService.getFirewoodType(fillyear,"constructionmachine", 20);
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				if (str.equals(PM))
					continue;
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);
				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setFirewoodName(po.getFirewoodName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/constructPollutant/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getConstruction(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.getFirewoodType2(fillyear,"constructionmachine", 20);
			}else if(typeid==2){
				poll = theService.getFirewoodType(fillyear,"constructionmachine", 20);
			}
			for (PollutantStat p : poll) {
				set.add(p.getFirewoodName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(PM))
						continue;
					if (po.getFirewoodName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/pollutantMachine1/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getPollutantmachine(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.pollutantMachine2(fillyear,"agriculturemachine");
			}else if(typeid==2){
				poll = theService.pollutantMachine(fillyear,"agriculturemachine");
			}
			for (PollutantStat p : poll) {
				set.add(p.getPollutantName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);
				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getPollutantName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setName(po.getFirewoodName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/machinePollutant/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getMachine(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.pollutantMachine2(fillyear,"agriculturemachine");
			}else if(typeid==2){
				poll = theService.pollutantMachine(fillyear,"agriculturemachine");
			}
			for (PollutantStat p : poll) {
				set.add(p.getFirewoodName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getFirewoodName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/roaddustPollutant/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getRoaddust(@PathVariable int fillyear,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			HashSet<String> ser = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.roaddustPollutant2(fillyear);
			}else if(typeid==2){
				poll = theService.roaddustPollutant(fillyear);
			}
			for (PollutantStat p : poll) {
				set.add(p.getDistrictName());
				ser.add(p.getPollutantName());
			}
			List<PollutantStatr> slistr = new ArrayList<PollutantStatr>();
			for (String str : set) {
				PollutantStatr stlist = new PollutantStatr();
				stlist.setDistrictName(str);
				List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
				for (String rst : ser) {
					if (rst.equals(PM))
						continue;
					double total = 0;
					PollutantStat1 stlis = new PollutantStat1();
					stlis.setName(rst);
					List<PollutantStat> slist = new ArrayList<PollutantStat>();
					for (PollutantStat po : poll) {
						if (str.equals(po.getDistrictName())
								&& rst.equals(po.getPollutantName())) {
							total += po.getStatvalue();
						}
					}
					for (PollutantStat po : poll) {
						if (str.equals(po.getDistrictName())
								&& rst.equals(po.getPollutantName())) {
							PollutantStat stat = new PollutantStat();
							stat.setName(po.getFirewoodName());
							stat.setFuelgroupname("合计");
							stat.setStatvalue(po.getStatvalue());
							stat.setStatvalue2(total);
							slist.add(stat);
						}
					}
					stlis.setList(slist);
					slist1.add(stlis);
				}
				stlist.setList(slist1);
				slistr.add(stlist);
			}
			result.put("list", slistr);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/planeType/getdata/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getPlane(@PathVariable int typeid,@PathVariable int fillyear,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			List<PollutantStat> poll = null;
			if(typeid==1){
				poll = theService.pollutantMachine2(fillyear,"airplane");
			}else if(typeid==2){
				poll = theService.pollutantMachine(fillyear,"airplane");
			}
			for (PollutantStat p : poll) {
				set.add(p.getFirewoodName());
			}
			List<PollutantStat1> slist1 = new ArrayList<PollutantStat1>();
			for (String str : set) {
				PollutantStat1 stlist = new PollutantStat1();
				stlist.setName(str);

				List<PollutantStat> slist = new ArrayList<PollutantStat>();
				for (PollutantStat po : poll) {
					if (po.getFirewoodName().equals(str)) {
						PollutantStat stat = new PollutantStat();
						stat.setPollutantName(po.getPollutantName());
						stat.setStatvalue(po.getStatvalue());
						slist.add(stat);
					}
				}
				stlist.setList(slist);
				slist1.add(stlist);
			}
			result.put("list", slist1);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/getyears", method = RequestMethod.GET)
	@ResponseBody
	public Object getYear(HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<Integer> list = new ArrayList<Integer>();
			List<StatProd> year = theService.getYear();
			for (StatProd li : year) {
				list.add(li.getFillyear());
			}
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/motorpollution/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getMotorpollutant(@PathVariable int year,@PathVariable int typeid,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupCityFeulRes> list = null;
		if(typeid==1){
			list = theService.getMotorpollutant2(year);
		}else if(typeid==2){
			list = theService.getMotorpollutant(year);
		}
		HashSet<String> set = new LinkedHashSet<String>();
		for (LookupCityFeulRes lcdr : list) {
			set.add(lcdr.getName());
		}
		List<LookupCityFeulResVo> volist = new ArrayList<LookupCityFeulResVo>();
		for (String s : set) {
			LookupCityFeulResVo vo = new LookupCityFeulResVo();
			vo.setName(s);
			List<LookupCityFeulRes1> reslist = new ArrayList<LookupCityFeulRes1>();
			for (LookupCityFeulRes lcdr : list) {
				if (lcdr.getName().equals(s)) {
					LookupCityFeulRes1 res1 = new LookupCityFeulRes1();
					res1.setFeulid(lcdr.getFeulid());
					res1.setName(lcdr.getDistrictName());
					res1.setSum(lcdr.getSum());
					reslist.add(res1);
				}
			}
			vo.setList(reslist);
			volist.add(vo);
		}
		result.put("list", volist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/boatCity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoatcity(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<Boat> boat = null;
			if(typeid==1){
				boat = numService.getBoatCity2(year);
			}else if(typeid==2){
				boat = numService.getBoatCity(year);
			}
			List<District> dlist = disService.getByLevel(1);
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			for (District dli : dlist) {
				HashMap<String, Object> pany = new HashMap<String, Object>();
				double zk = 0;
				double zh = 0;
				if (boat.size() > 0) {
					for (Boat bli : boat) {
						if (dli.getId() == bli.getCity()) {
							if (bli.getMeasuretype().equals(TRAVELLER_NUM)) {
								zk = bli.getTotal();
							}
							if (bli.getMeasuretype().equals(CARGO_NUM)) {
								zh = bli.getTotal();
							}
						}
					}
				}
				double E = (0.065 * zk + zh) * YX;
				pany.put("name", dli.getDistrictName());
				pany.put("value", E);
				pany.put("unit", "kg");
				list.add(pany);
			}
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/boatType/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoattype(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<Boat> boat = null;
			if(typeid==1){
				boat = numService.getBoatMonth2(year);
			}else if(typeid==2){
				boat = numService.getBoatMonth(year);
			}
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			for (Boat bty : boat) {
				double zk = 0;
				double zh = 0;
				if (boat.size() > 0) {
					for (Boat bli : boat) {
						if (bty.getMeasuretype().equals(bli.getMeasuretype())) {
							if (bli.getMeasuretype().equals(TRAVELLER_NUM)) {
								zk = bli.getTotal();
							}
							if (bli.getMeasuretype().equals(CARGO_NUM)) {
								zh = bli.getTotal();
							}
						}
					}
				}
				double E = (0.065 * zk + zh) * YX;
				if (bty.getMeasuretype().equals(CARGO_NUM)
						|| bty.getMeasuretype().equals(TRAVELLER_NUM)) {
					HashMap<String, Object> pany = new LinkedHashMap<String, Object>();
					String type = null;
					if (bty.getMeasuretype().equals(CARGO_NUM))
						type = "货运";
					else
						type = "客运";
					pany.put("name", type);
					pany.put("count", E);
					pany.put("unit", "kg");
					list.add(pany);
				}
			}
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/boatMonth/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoatMonth(@PathVariable int typeid,@PathVariable int year,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			List<Boat> boat = null;
			if(typeid==1){
				boat = numService.getBoatMonth2(year);
			}else if(typeid==2){
				boat = numService.getBoatMonth(year);
			}
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			Class<Boat> clazz = Boat.class;

			for (int i = 1; i <= 12; i++) {

				HashMap<String, Object> pany = new LinkedHashMap<String, Object>();
				double zk = 0;
				double zh = 0;
				if (boat.size() > 0) {
					for (Boat bli : boat) {
						Method getMx = clazz.getDeclaredMethod("getM" + i);
						double valMx = (Double) (getMx.invoke(bli));

						if (bli.getMeasuretype().equals(TRAVELLER_NUM)) {
							zk = valMx;
						}
						if (bli.getMeasuretype().equals(CARGO_NUM)) {
							zh = valMx;
						}
					}
					double E = (0.065 * zk + zh) * YX;
					pany.put("name", i + "月");
					pany.put("value", E);
					pany.put("unit", "kg");
					list.add(pany);
				}
			}
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/moving/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getmoving(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> dset = new LinkedHashSet<String>();
			List<PollutantStat> list = null;
			if(typeid==1){
				list = theService.getPollutantBycity2(year,"constructionmachine");
			}else if(typeid==2){
				list = theService.getPollutantBycity(year,"constructionmachine");
			}
			for (PollutantStat li : list) {
				dset.add(li.getDistrictName());
			}
			
			List<HashMap<String,Object>> mvList=new ArrayList<HashMap<String,Object>>();
			for(String str:dset){
				List<HashMap<String,Object>> mList=new ArrayList<HashMap<String,Object>>();
				HashMap<String,Object> pany=new LinkedHashMap<String, Object>();
				pany.put("name", str);
				for(PollutantStat li : list){
					if(str.equals(li.getDistrictName())&&!li.getPollutantName().equals(PM)){
						HashMap<String,Object> mpany=new LinkedHashMap<String, Object>();
						mpany.put("districtName", li.getPollutantName());
						mpany.put("statvalue", Math.round(li.getStatvalue()*100)/100.0);
						mList.add(mpany);
					}
				}
				pany.put("list", mList);
				mvList.add(pany);
			}
			result.put("list", mvList);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/agricult/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAgmoving(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			HashSet<String> dset = new LinkedHashSet<String>();
			List<PollutantStat> list = null;
			if(typeid==1){
				list = theService.getPollutantBycity2(year,"agriculturemachine");
			}else if(typeid==2){
				list = theService.getPollutantBycity(year,"agriculturemachine");
			}
			for (PollutantStat li : list) {
				dset.add(li.getDistrictName());
			}
			
			List<HashMap<String,Object>> mvList=new ArrayList<HashMap<String,Object>>();
			for(String str:dset){
				List<HashMap<String,Object>> mList=new ArrayList<HashMap<String,Object>>();
				HashMap<String,Object> pany=new LinkedHashMap<String, Object>();
				pany.put("name", str);
				for(PollutantStat li : list){
					if(str.equals(li.getDistrictName())&&!li.getPollutantName().equals(PM)){
						HashMap<String,Object> mpany=new LinkedHashMap<String, Object>();
						mpany.put("districtName", li.getPollutantName());
						mpany.put("statvalue", Math.round(li.getStatvalue()*100)/100.0);
						mList.add(mpany);
					}
				}
				pany.put("list", mList);
				mvList.add(pany);
			}
			result.put("list", mvList);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/pollutantMachine/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	private Object getNfertilizerByType(@PathVariable int year,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<LookupFeulTypeRes> list = lookupService.getNfertilizerByType(year);
		result.put("res", list);

		return result.setStatus(0, "");
	}
}
