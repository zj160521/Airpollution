package com.xf.controller.gov;

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
import com.xf.entity.gov.Firewood;
import com.xf.service.gov.FirewoodService;
import com.xf.vo.FirewoodM;
import com.xf.vo.IntString;

@Scope("prototype")
@Controller
@RequestMapping(value = "/firewood")
public class FirewoodController extends BaseController {

	@Autowired
	private FirewoodService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	
	private Map<String, String[]> crops;

	private Firewood check_input(HttpServletRequest request) {
		Firewood ret = new Firewood();

		String s = new String();
		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s)) ret.setId(Integer.parseInt(s));
		s = request.getParameter("accountid");
		if (s != null && Tools.isInteger(s)) ret.setAccountid(Integer.parseInt(s));
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
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s)) ret.setStatus(Integer.parseInt(s));
		crops = request.getParameterMap();
		
		return ret;
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

	// ==============================================================================

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Firewood input = check_input(request);
			if (input.getTown() <=0)
				return result.setStatus(-3, "no data.");

			input.setAccountid(companyid);
			List<Firewood> search = theService.getByTown(input);
			if (search != null && !search.isEmpty())
				result.put("id", search.get(0).getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
	
	// ==============================================================================


	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Firewood input = check_input(request);
			
			input.setAccountid(companyid);

			Calendar cal = Calendar.getInstance();
			input.setFillTime(Tools.ToDateStr(cal));

			List<Integer> idlist = new ArrayList<Integer>();
			
			String key = new String();
			for(int i = 0; i < 14; i++) {

				key = "crops[" + i + "][cropstypeid]";
				String[] s = crops.get(key);
				if (s == null || !Tools.isInteger(s[0])) continue;
				input.setCropType(Integer.parseInt(s[0]));
				
				key = "crops[" + i + "][area]";
				s = crops.get(key);
				if (s == null || !Tools.isNumeric(s[0])){
					input.setCultivatedArea((double) 0);
				}else{
				input.setCultivatedArea(Double.parseDouble(s[0]));
				}
				
				key = "crops[" + i + "][yield]";
				s = crops.get(key);
				if (s == null || !Tools.isNumeric(s[0])){
					input.setYield((double) 0);
				}else{
				input.setYield(Double.parseDouble(s[0]));
				}
				
				key = "crops[" + i + "][utilization]";
				s = crops.get(key);
				if (s == null || !Tools.isNumeric(s[0])){
					input.setUtilizeRatio((double) 0);
				}else{
				input.setUtilizeRatio(Double.parseDouble(s[0]));
				}
				
				key = "crops[" + i + "][status]";
				s = crops.get(key);
				if (s != null && !Tools.isNumeric(s[0]))
				input.setStatus(Integer.parseInt(s[0]));
				
				
				Firewood search = null;
				search = theService.getByField(input);
				if (input.getStatus() < 1)
					input.setStatus(WorkFlowController.STATUS_FILL);
				if (search != null && search.getId() > 0) {
					input.setId(search.getId());
					theService.update(input);
				} else {
					if(input.getCultivatedArea() == 0 && input.getUtilizeRatio() == 0 && input.getYield() == 0){
						continue;
					}else{
						theService.add(input);
				    }
					
				}
				idlist.add(input.getId());
			}

			result.put("id", idlist);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	// ==============================================================================

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Firewood search = theService.getById(id);
			if (null == search)
				return result.setStatus(-3, "No data.");

			result.put("data", search);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	// ==============================================================================

	@RequestMapping(value = "/get/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByYear(@PathVariable int year, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Firewood input = new Firewood();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<Firewood> list = null;
			FirewoodM fwm = null;
			try {
				list = theService.getByYear(input);
				List<IntString> towns;
				towns=theService.getTowns(input);
				towns.remove(null);
				if(towns.size()==0){
					towns=theService.getCitys(input);
				}
				List<Map> listl=new ArrayList<Map>();
				fwm = new FirewoodM();
				for(IntString town:towns){
					Map mp=new HashMap();
					mp.put("town", town.getIt());
					mp.put("townname", town.getStr());
					int sta=0;
					List<Map> listm=new ArrayList<Map>();
					for(int i=0;i<list.size();i++){
						if(town.getIt()==list.get(i).getTown()||town.getIt()==list.get(i).getCity()){
							
						Map map=new HashMap();
						map.put("cropstypeid", list.get(i).getCropType());
						int t=list.get(i).getCropType();
						if(t==8101) map.put("cropName", "水稻");
						if(t==8102) map.put("cropName", "小麦");
						if(t==8103) map.put("cropName", "玉米");
						if(t==8104) map.put("cropName", "豆类");
						if(t==8105) map.put("cropName", "薯类");
						if(t==8106) map.put("cropName", "花生");
						if(t==8107) map.put("cropName", "油菜");
						if(t==8108) map.put("cropName", "高粱");
						if(t==8109) map.put("cropName", "甘蔗");
						if(t==8110) map.put("cropName", "麻类");
						if(t==8111) map.put("cropName", "烟叶");
						if(t==8112) map.put("cropName", "蔬菜");
						if(t==8113) map.put("cropName", "棉花");
						if(t==8114) map.put("cropName", "其他谷物");
						map.put("area", list.get(i).getCultivatedArea());
						map.put("yield", list.get(i).getYield());
						map.put("utilization", list.get(i).getUtilizeRatio());
						map.put("status", list.get(i).getStatus());
						
						if(list.get(i).getStatus() ==3 ) {
							fwm.setStatus(3);
							sta=3;
						}
						if(list.get(i).getStatus() ==1 ) {
							fwm.setStatus(1);
							sta=1;	
						}
						listm.add(map);
						}
					}
					mp.put("status", sta);
					mp.put("crops", listm);
				    listl.add(mp);
				}
				Firewood fw=list.get(0);

				fwm.setAccountid(fw.getAccountid());
				fwm.setFillTime(fw.getFillTime());
				fwm.setFillyear(fw.getFillyear());
				fwm.setProvince(fw.getProvince());
				fwm.setCity(fw.getCity());
				fwm.setMap(listl);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				return result.setStatus(-3, "无记录");
			}
			
			if (list != null)
				result.put("data", fwm);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
	
	// ==============================================================================

	@RequestMapping(value = "/get/town/{townid}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByTown(@PathVariable int townid, @PathVariable int fillyear, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Firewood input = new Firewood();
			input.setFillyear(fillyear);
			input.setAccountid(companyid);
			input.setTown(townid);
			List<Firewood> list = theService.getByTown(input);
			if (list != null)
				result.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
	
	// ==============================================================================

	public void setService(){
		super.theService = theService;
	}
}
