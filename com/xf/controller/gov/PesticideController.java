package com.xf.controller.gov;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.controller.WorkFlowController;
import com.xf.entity.Company;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.DistrictService;
import com.xf.entity.gov.ConstructionDust;
import com.xf.entity.gov.Pesticide;
import com.xf.service.gov.PesticideService;
import com.xf.vo.PesticideVo;

@Scope("prototype")
@Controller
@RequestMapping(value = "/pesticide")
public class PesticideController extends BaseController {

	@Autowired
	private PesticideService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private DistrictService disservice;

	private List<Pesticide> check_input(HttpServletRequest request) {
		List<Pesticide> list=new ArrayList<Pesticide>();
		for(int i=0;i<7;i++){
			
			Pesticide ret = new Pesticide();

			String s = new String();
			s = request.getParameter("crops["+i+"][id]");
			if (s != null && Tools.isInteger(s)) ret.setId(Integer.parseInt(s));
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s)) ret.setAccountid(Integer.parseInt(s));
			s = request.getParameter("status");
			if (s != null && Tools.isInteger(s)) ret.setStatus(Integer.parseInt(s));
			s = request.getParameter("importflag");
			if (s != null && Tools.isInteger(s)) ret.setImportflag(Integer.parseInt(s));
			ret.setFillTime(request.getParameter("fillTime"));
			s = request.getParameter("fillyear");
			if (s != null && Tools.isInteger(s)) ret.setFillyear(Integer.parseInt(s));
			s = request.getParameter("province");
			if (s != null && Tools.isInteger(s)) ret.setProvince(Integer.parseInt(s));
			s = request.getParameter("city");
			if (s != null && Tools.isInteger(s)) ret.setCity(Integer.parseInt(s));
			s = request.getParameter("town");
			if (s != null && Tools.isInteger(s)) ret.setTown(Integer.parseInt(s));
			s = request.getParameter("country");
			if (s != null && Tools.isInteger(s)) ret.setCountry(Integer.parseInt(s));
			s = request.getParameter("street");
			if (s != null && Tools.isInteger(s)) ret.setStreet(Integer.parseInt(s));
			ret.setCrop(request.getParameter("crops["+i+"][cropname]"));
			s = request.getParameter("crops["+i+"][dd]");
			if (s != null && Tools.isNumeric(s)) ret.setWorm_ddt(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][omethoate]");
			if (s != null && Tools.isNumeric(s)) ret.setWorm_leguo(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][cypermethrin]");
			if (s != null && Tools.isNumeric(s)) ret.setWorm_juzhi(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][worm_total]");
			if (s != null && Tools.isNumeric(s)) ret.setWorm_total(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][paraquat]");
			if (s != null && Tools.isNumeric(s)) ret.setGrass_baicao(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][carbendazim]");
			if (s != null && Tools.isNumeric(s)) ret.setGerm_duojun(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][glyphosate]");
			if (s != null && Tools.isNumeric(s)) ret.setGrass_ganlin(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][grass_total]");
			if (s != null && Tools.isNumeric(s)) ret.setGrass_total(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][daowen]");
			if (s != null && Tools.isNumeric(s)) ret.setGerm_daowen(Double.parseDouble(s));
			s = request.getParameter("crops["+i+"][fungicide]");
			if (s != null && Tools.isNumeric(s)) ret.setGerm_total(Double.parseDouble(s));
			list.add(ret);
		}
		return list;
	}

	// ==============================================================================

	int companyid;

	private HashMap<String, Object> check_account(HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			String s = new String();
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s)) companyid = Integer.parseInt(s);
			if (companyid <= 0)
				return result.setStatus(-2, "no company id.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			int town = 0;
			int fillyear = 0;
			String s = request.getParameter("town");
			String year = request.getParameter("fillyear");
			if(s!=null && year!=null){
				town = Integer.parseInt(s);
				fillyear = Integer.parseInt(year);
			}
			
			List<Pesticide> list = theService.getCheck(town,companyid,fillyear);
			if(list.size() > 0){
				result.put("id", list.get(0).getTown());
			}else{
				return result.setStatus(0, "无数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			List<Pesticide> list = check_input(request);
			
			for(Pesticide input:list){
				input.setAccountid(companyid);

				Pesticide search = null;
				if (input.getId() > 0) {
					search = theService.getById(input.getId());
				}

				Calendar cal = Calendar.getInstance();
				input.setFillTime(Tools.ToDateStr(cal));
				if (input.getStatus() < 1)
					input.setStatus(WorkFlowController.STATUS_FILL);
				if (search != null && search.getId() > 0) {
					theService.update(input);
				} else {
					if(input.getTown()>0||input.getCity()>0)
					theService.add(input);
				}

				result.put("id", input.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Pesticide search = theService.getById(id);
			if (null == search)
				return result.setStatus(-3, "No data.");

			result.put("data", search);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "getbytown/{id}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByTown(@PathVariable int id,@PathVariable int fillyear, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			List<Pesticide> list = theService.getByTown(id, fillyear);
			if (null == list)
				return result.setStatus(-3, "No data.");
			String townname="";
			int townid=0;
			if(list.get(0).getTown()>0){
				townid=list.get(0).getTown();
				townname=disservice.getById(list.get(0).getTown()).getDistrictName();
			}else{
				townid=list.get(0).getCity();
				townname=disservice.getById(list.get(0).getCity()).getDistrictName();
			}
			
				result.put("data", list);
				result.put("townname", townname);
				result.put("townid", townid);


		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByYear(@PathVariable int year, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;
			Company c=null;
			if (loginManage.isCompanyLogin(request)) {
				c = loginManage.getLoginCompany(request);
			}
			Pesticide input = new Pesticide();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<Pesticide> list = theService.getByYear(input);
			
			List listvo=new ArrayList();
			List<String> listcrop=new ArrayList<String>();
			listcrop.add("谷类");
			listcrop.add("小麦");
			listcrop.add("玉米");
			listcrop.add("薯类");
			listcrop.add("蔬菜");
			listcrop.add("果树");
			listcrop.add("其他谷物");
			listcrop.add("合计");
			List<Integer> set=null;
			if(c!=null){
				if(c.getProvince()==0){
					set=theService.getCitys(companyid, year);
				}else{
					set=theService.getTowns(companyid, year);
				}
			}else{
				set=theService.getTowns(companyid, year);
				if(set.size()==1&&set.get(0)==0){
					set=theService.getCitys(companyid, year);
				}
			}
			if (list .size()>0){
				for(int dis:set){
					PesticideVo vo=new PesticideVo();
					vo.setTownid(dis);
					vo.setTown(disservice.getById(dis).getDistrictName());
					vo.setFillyear(year);
					List volist=new ArrayList();
					double insectotal=0;
					double grasstotal=0;
					double gremtotal=0;
					
					double ddtotal=0;
					double omethoatetotal=0;
					double cypermethrintotal=0;
					double wormtotaltotal=0;
					double paraquattotal=0;
					double carbendazimtotal=0;
					double glyphosatetotal=0;
					double grasstotaltotal=0;
					double fungicidetotal=0;
					double daowentotal=0;
					
					for(String crop:listcrop){
						if(!crop.equals("合计")){
							for(Pesticide pes:list){
								if((pes.getTown()==dis||pes.getCity()==dis) && pes.getCrop().equals(crop)){
									vo.setStatus(pes.getStatus());
									Map map=new HashMap();
									map.put("id", pes.getId());
									map.put("crop", crop);
									map.put("dd", pes.getWorm_ddt());
									map.put("omethoate", pes.getWorm_leguo());
									map.put("cypermethrin", pes.getWorm_juzhi());
									map.put("wormtotal", pes.getWorm_total());
									double total=add(add(pes.getWorm_total(),pes.getWorm_juzhi()),add(pes.getWorm_leguo(),pes.getWorm_ddt()));
									map.put("total",total);
									map.put("paraquat", pes.getGrass_baicao());
									map.put("carbendazim", pes.getGerm_duojun());
									map.put("glyphosate", pes.getGrass_ganlin());
									map.put("grasstotal", pes.getGrass_total());
									double total2=add(add(pes.getGrass_total(),pes.getGrass_ganlin()),pes.getGrass_baicao());
									map.put("total2",total2);
									map.put("daowen", pes.getGerm_daowen());
									map.put("fungicide", pes.getGerm_total());
									double total3=add(add(pes.getGerm_total(),pes.getGerm_daowen()),pes.getGerm_duojun());
									map.put("total3",total3);
									volist.add(map);
									insectotal=add(add(add(insectotal,pes.getWorm_total()),pes.getWorm_juzhi()),add(pes.getWorm_leguo(),pes.getWorm_ddt()));
									grasstotal=add(add(grasstotal,pes.getGrass_total()),add(pes.getGrass_ganlin(),pes.getGrass_baicao()));
									gremtotal=add(add(gremtotal,pes.getGerm_total()),add(pes.getGerm_daowen(),pes.getGerm_duojun()));
									
									ddtotal = add(ddtotal,pes.getWorm_ddt());
									omethoatetotal = add(omethoatetotal,pes.getWorm_leguo());
									cypermethrintotal = add(cypermethrintotal,pes.getWorm_juzhi());
									wormtotaltotal = add(wormtotaltotal,pes.getWorm_total());
									paraquattotal = add(paraquattotal,pes.getGrass_baicao());
									carbendazimtotal = add(carbendazimtotal,pes.getGerm_duojun());
									glyphosatetotal = add(glyphosatetotal,pes.getGrass_ganlin());
									grasstotaltotal = add(grasstotaltotal,pes.getGrass_total());
									daowentotal = add(daowentotal,pes.getGerm_daowen());
									fungicidetotal = add(fungicidetotal,pes.getGerm_total());
								}

							}
						}else{
							Map map=new HashMap();
							map.put("insectotal", insectotal);
							map.put("grasstotal", grasstotal);
							map.put("gremtotal", gremtotal);
							
							map.put("ddtotal", ddtotal);
							map.put("omethoatetotal", omethoatetotal);
							map.put("cypermethrintotal",cypermethrintotal);
							map.put("wormtotaltotal",wormtotaltotal);
							map.put("paraquattotal",paraquattotal);
							map.put("carbendazimtotal",carbendazimtotal);
							map.put("glyphosatetotal",glyphosatetotal);
							map.put("grasstotaltotal",grasstotaltotal);
							map.put("daowentotal",daowentotal);
							map.put("fungicidetotal",fungicidetotal);
							volist.add(map);
							
						}

					}

					vo.setCrops(volist);

					listvo.add(vo);
				}
				
				result.put("data", listvo);
			}else{
				return result.setStatus(0, "无数据");
			}


		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "ok");
	}

	public void setService(){
		super.theService = theService;
	}
	
	public double add(double num1, double num2){ 
		BigDecimal b1 = new BigDecimal(String.valueOf(num1)); 
		BigDecimal b2 = new BigDecimal(String.valueOf(num2)); 
		return b1.add(b2).doubleValue(); 
	}
}
