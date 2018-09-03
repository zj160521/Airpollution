package com.xf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Pollutant;
import com.xf.entity.Static;
import com.xf.entity.gov.Factor;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.PollutantService;
import com.xf.service.StaticService;
import com.xf.service.gov.FactorService;
import com.xf.vo.IntString;
import com.xf.vo.PollutantCount;
import com.xf.vo.PollutantCountM;
import com.xf.vo.SecondLevelFuelFactorVo;

@Scope("prototype")
@Controller
@RequestMapping(value = "/pollutant")
public class PollutantController {

	@Autowired
	private PollutantService puService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private FactorService factorService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private ResultObj result;
	private static final String STATIC_NAME = "其它";

	private Pollutant check(HttpServletRequest request) {

		String s = new String();
		Pollutant pu = new Pollutant();
		pu.setPollutantName(request.getParameter("pollutantName").trim());
		pu.setPollutantType(request.getParameter("pollutantType"));
		pu.setRemark(request.getParameter("remark"));
		pu.setGroupname(request.getParameter("groupname").trim());

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			pu.setId(Integer.parseInt(s));
		s = request.getParameter("pollutantId");
		if (s != null && Tools.isInteger(s))
			pu.setId(Integer.parseInt(s));

		return pu;
	}

	private List<Factor> factorCheck(HttpServletRequest request) {
		String s = new String();
		List<Factor> list = new ArrayList<Factor>();

		List<Pollutant> list1 = puService.getAll();

		int sid = 0;
		int fid = 0;
		int formulaid = 0;
		s = request.getParameter("fId");
		if (s != null && Tools.isInteger(s)) {
			fid = Integer.parseInt(s);
		}
		s = request.getParameter("sId");
		if (s != null && Tools.isInteger(s)) {
			sid = Integer.parseInt(s);
		}
		
		for (int i = 0; i < list1.size(); i++) {
			Factor pu = new Factor();
			pu.setfId(fid);
			s = request.getParameter("pollutant[" + i + "][formulaId]");
			if (s != null && Tools.isInteger(s)) {
				formulaid = Integer.parseInt(s);
			}
			pu.setFormulaId(formulaid);
			pu.setTypeid(1);
			s = request.getParameter("pollutant[" + i + "][pollutantId]");
			if (s != null && Tools.isInteger(s))
				pu.setPollutantId(Integer.parseInt(s));
			pu.setMaterialId(0);
			String factor = request
					.getParameter("pollutant[" + i + "][factor]");
			if (factor != null && Tools.isNumeric(factor)) {
				pu.setFactor(Double.parseDouble(factor));
			} else {
				pu.setFactor(-1);
			}
			s = request.getParameter("industryId");
			if (s != null && Tools.isInteger(s)) {
				int tradeid = Integer.parseInt(s);
				if (tradeid == 1)
					pu.setTradeid(132);
				if (tradeid == 2)
					pu.setTradeid(809);
			}
			
			if (sid > 0 && factor != null && factor != "") {
				pu.setProductId(sid);

				list.add(pu);
			} else if (sid == -1 && factor != null && factor != "") {
				pu.setProductId(fid);

				list.add(pu);
			}

		}
		return list;
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addCompany(HttpServletRequest request) {

		Pollutant pu = check(request);

		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "not login.");

		try {

			if (pu.getPollutantName() == null
					|| pu.getPollutantName().isEmpty())
				return result.setStatus(-3, "请填写污染物名称！");
			
			if (pu.getGroupname() == null
					|| pu.getGroupname().isEmpty())
				return result.setStatus(-3, "请填写污染物类型！");

			Pollutant pollu = puService.getByGrpName(pu.getGroupname());
			
			if(pollu != null)
				pu.setGroupid(pollu.getGroupid());
			else{
				Integer maxGroupId = puService.getMaxGroupId();
				pu.setGroupid(maxGroupId);
			}
			
			Pollutant p = null;

			if (pu.getId() > 0){
				p = puService.getById(pu.getId());
			}else
				p = puService.getByName(pu.getPollutantName());


			if (p != null && p.getId() > 0) {
				puService.update(pu);
				result.put("lastid", p.getId());
			} else {
				puService.add2(pu);
				Pollutant po = puService.getByName(pu.getPollutantName());
				result.put("lastid", po.getGroupid());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "not login.");

		List<Pollutant> cList = puService.getAll();
		if (cList == null)
			return result.setStatus(-2, "no pollutant.");

		result.put("pollutant", cList);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/get/ten", method = RequestMethod.GET)
	@ResponseBody
	public Object getten(HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "not login.");

		List<Pollutant> cList = puService.getTen();
		if (cList == null)
			return result.setStatus(-2, "no pollutant.");

		result.put("pollutant", cList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/allgroup", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllGroup(HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "not login.");

		List<IntString> cList = puService.getAllGroup();
		if (cList == null)
			return result.setStatus(-2, "no pollutant.");

		result.put("pollutant", cList);
		return result.setStatus(0, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getfactor/{tradeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getFactors(@PathVariable int tradeid,
			HttpServletRequest request) {
		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "not login.");

		try {
			CopyOnWriteArrayList<IntString> lists= puService.getFNames();// 获取一级燃料ID及name
			// PollutantCountM中List<Map>的map中装个list
			List<PollutantCountM> listm = new ArrayList<PollutantCountM>();
			//燃煤配置需细化到每种锅炉
			for(IntString li : lists){
				if(li.getIt() == 2001){
					List<Static> list = staticService.getByPid(1001);
					for(int i = 0; i < list.size(); i++ ){
						Static stc = list.get(i);
						IntString intStr = getIntStr(2001, "燃煤-"+stc.getName(), stc.getId());
						lists.add(i, intStr);
					}
				}
			}
			// 获取污染物对应因子的记录列表
			List<PollutantCount> list = null;
			for (IntString li : lists) {
				if(li.getStr().equals("燃煤")) continue;
				if(li.getIt() == 2001){
					list = puService.getFactors(tradeid, li.getSecondId());
				} else{
					list = puService.getFactors(tradeid,0);
				}
				PollutantCountM pm = new PollutantCountM();
				pm.setfId(li.getIt());
				pm.setfName(li.getStr());
//				if(li.getStr().equals("燃煤-层燃炉")){
//					pm.setcId(1);
//				}else if(li.getStr().equals("燃煤-煤粉炉")){
//					pm.setcId(2);
//				}else if(li.getStr().equals("燃煤-循环流化床")){
//					pm.setcId(3);
//				}else if(li.getStr().equals("燃煤-其他")){
//					pm.setcId(4);
//				}
				List<Map> lm = new ArrayList<Map>();
				List<IntString> listsname = puService.getSNames(li.getIt());// 获取二级燃料ID及name

				// 任意因子设置，其productId采用燃料id
				Map map1 = new HashMap();
				map1.put("fId", -1);
				map1.put("fName", "任意");
				List<PollutantCount> list0 = null;
				if(li.getIt() == 2001){
					list0 =  puService.getFactors2(tradeid,li.getSecondId());
				} else {
					list0 =  puService.getFactors2(tradeid,0);
				}
				
				if(list0 != null && list0.size() > 0){
					List<Map> lm1 = new ArrayList<Map>();
					for (PollutantCount pc : list0) {
						if("PM10".equals(pc.getPollutantName()))
							continue;
						if(li.getIt() == 2001){
							if ("燃煤".equals(pc.getfName())) {
								Map m = new HashMap();
								m.put("pollutantId", pc.getPollutantId());
								m.put("pollutantName", pc.getPollutantName());
								m.put("productId", pc.getProductId());
								m.put("factor", pc.getFactor());
								if(li.getIt() == 2001){
									m.put("formulaId", li.getSecondId());
								} 
								lm1.add(m);
							}
						} else{
							if (li.getStr().equals(pc.getfName())) {
								Map m = new HashMap();
								m.put("pollutantId", pc.getPollutantId());
								m.put("pollutantName", pc.getPollutantName());
								m.put("productId", pc.getProductId());
								m.put("factor", pc.getFactor());
								lm1.add(m);
							}
						}
					}
					map1.put("map", lm1);
				}
				lm.add(map1);

				// 二级燃料因子查询
				for (IntString sname : listsname) {
					Map map = new HashMap();
					map.put("fId", sname.getIt());
					map.put("fName", sname.getStr());
					List<Map> listmap = new ArrayList<Map>();
					for (PollutantCount pc : list) {
						if("PM10".equals(pc.getPollutantName()))
							continue;
						if(li.getIt() == 2001){
							if ("燃煤".equals(pc.getfName()) && pc.getsName().equals(sname.getStr())) {
								Map m = new HashMap();
								m.put("pollutantId", pc.getPollutantId());
								m.put("pollutantName", pc.getPollutantName());
								m.put("productId", pc.getProductId());
								m.put("factor", pc.getFactor());
//								if(li.getStr().equals("燃煤-层燃炉")){
//									m.put("formulaId", 1101);
//								} else if(li.getStr().equals("燃煤-煤粉炉")){
//									m.put("formulaId", 1102);
//								} else if(li.getStr().equals("燃煤-循环流化床")){
//									m.put("formulaId", 1103);
//								} else if(li.getStr().equals("燃煤-其他")){
//									m.put("formulaId", 1104);
//								}
								if(li.getIt() == 2001){
									m.put("formulaId", li.getSecondId());
								}
								listmap.add(m);
							}
						} else{
							if (sname.getStr().equals(pc.getsName()) && li.getIt() == pc.getfId()) {
								Map m = new HashMap();
								m.put("pollutantId", pc.getPollutantId());
								m.put("pollutantName", pc.getPollutantName());
								m.put("productId", pc.getProductId());
								m.put("factor", pc.getFactor());
								listmap.add(m);
							}
						}							
					}
					map.put("map", listmap);
					lm.add(map);
				}
				pm.setMap(lm);
				listm.add(pm);
			}
			result.put("pollutantcount", listm);
			return result.setStatus(0, "");
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "没有记录");
		}
	}
	
	public IntString getIntStr(int id, String str,int secondId){
		IntString is = new IntString();
		is.setIt(id);
		is.setStr(str);
		is.setSecondId(secondId);
		return is;
	}

	private boolean isChange = false;
	private List<Integer> idList;
	private List<SecondLevelFuelFactorVo> fuleList;
	private int formulaId;
	@RequestMapping(value = "/setfactor", method = RequestMethod.POST)
	@ResponseBody
	public Object setFactors(HttpServletRequest request) {
		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "not login.");

		List<Factor> factorList = factorCheck(request);
		if (factorList.size() > 0) {
			isChange = false;
			idList = new ArrayList<Integer>();
			fuleList = null;
			for (int i = 0; i < factorList.size(); i++) {
				Factor factor = factorList.get(i);
				if (factor.getFactor() == -1)
					return result.setStatus(-1, "因子应为非空数字");
				
				if (factor.getPollutantId() > 0 && factor.getProductId() > 0
						&& factor.getTradeid() > 0 && factor.getFactor() >= 0) {
					setFollow(factor);
					addUpFactor(factor, i);
				}
			}
			if(isChange){
				for (int id : idList) {
					Factor ftr = new Factor();
					ftr.setId(id);
					ftr.setFactor(-1);
					ftr.setIsFollow(0);
					factorService.updateIsFollow(ftr);
				}
			}
		} else {
			return result.setStatus(-2, "factor全为0，无有效数据");
		}
		return result.setStatus(0, "ok");
	}
	
	public boolean setFollow(Factor factor){
		if(fuleList == null){
//			if(factor.getProductId() == 2002 || factor.getProductId() == 2003
//					|| factor.getProductId() == 2004 || factor.getProductId() == 2005){
//				fuleList = staticService.getByPidAndIdIn2000(factor.getTradeid(),factor.getProductId(),0);
//			}else 
			if(factor.getProductId() == 2001){
				fuleList = staticService.getByPidAndIdIn2000(factor.getTradeid(),factor.getProductId(),factor.getFormulaId());
				formulaId = factor.getFormulaId();
			}else{
				fuleList = staticService.getByPidAndIdIn2000(factor.getTradeid(),factor.getProductId(),0);
			}
		}
		if(fuleList != null && fuleList.size() > 0){
			for(SecondLevelFuelFactorVo vo :fuleList){
				if(vo.getFactorId() == null){
					Factor setFactor = setFactor(factor, vo, formulaId);
					factorService.add(setFactor);
				}else{
					if(vo.getIsFollow() == null){
						Factor ftr = new Factor();
						ftr.setId(vo.getFactorId());
						ftr.setFactor(-1);
						ftr.setIsFollow(0);
						factorService.updateIsFollow(ftr);
					}else if(vo.getIsFollow() == 1 && factor.getPollutantId() == vo.getPollutantId()
							&& factor.getTradeid() == vo.getTradeid()){
						Factor ftr = new Factor();
						ftr.setId(vo.getFactorId());
						ftr.setFactor(factor.getFactor());
						factorService.updateIsFollow(ftr);
					}
				}
			}
		}
		return true;
	}
	
	public Factor setFactor(Factor factor, SecondLevelFuelFactorVo vo, int formulaId){
		Factor secondLevelFactor = new Factor();
		secondLevelFactor.setTypeid(1);
		secondLevelFactor.setPollutantId(factor.getPollutantId());
		secondLevelFactor.setProductId(vo.getProductId());
		secondLevelFactor.setFactor(factor.getFactor());
		secondLevelFactor.setFormulaId(formulaId);
		secondLevelFactor.setTradeid(factor.getTradeid());
		secondLevelFactor.setIsFollow(1);
		return secondLevelFactor;
	}
	
	public void addUpFactor(Factor factor, int i){
		if(factor.getfId() != 2001){
			factor.setFormulaId(0);
		}
		factor.setIsFollow(0);
		Factor ft = factorService.getByIdId(factor);
		if (ft != null && ft.getId() > 0) {
			if(ft.getFactor() != factor.getFactor()){
				isChange = true;
				factor.setId(ft.getId());
				factorService.update(factor);
				result.put("lastid" + i, ft.getId());
			}
			idList.add(ft.getId());
		} else {
			factorService.add(factor);
			result.put("lastid" + i, factor.getId());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "not login.");

		Pollutant up = check(request);
		int id = up.getId();

		try {
			Pollutant plist = puService.getByName(STATIC_NAME);
			Pollutant poll = puService.getById(id);

			if (poll != null && !poll.getPollutantName().equals(STATIC_NAME)) {
				int i = 0;
				puService.delete(id);
				List<Pollutant> po = puService.getBygroupid(poll.getGroupid());

				if (plist == null) {
					i = 1;
				}

				if (i == 0) {
					poll.setPollutantName(STATIC_NAME);
					poll.setGroupname(STATIC_NAME);
					puService.add(poll);

					plist = puService.getByName(STATIC_NAME);
					poll = puService.getById(id);
				}

				if (plist != null) {
					int puid = plist.getId();

					if (po.size() <= 0) {
						puService.updateCofac(plist.getGroupid(),
								poll.getGroupid());
					}
					puService.updateFac(puid, id);
					puService.updateOutpoll(puid, id);
					puService.updateVehifac(puid, id);
					puService.deleteStatdev(id);
					puService.deleteStatgov(id);
					puService.deleteStatmotor(id);
					puService.deleteStatpro(id);
				}
			} else {
				return result.setStatus(-3, "Can't delete this");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}
}
