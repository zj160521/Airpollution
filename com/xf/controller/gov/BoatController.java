package com.xf.controller.gov;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
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
import com.xf.controller.WorkFlowController;
import com.xf.entity.Company;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.entity.gov.Boat;
import com.xf.entity.gov.ConstructionDust;
import com.xf.vo.BoatM;
import com.xf.service.CompanyService;
import com.xf.service.DistrictService;
import com.xf.service.gov.BoatService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/boat")
public class BoatController extends BaseController {

	@Autowired
	private BoatService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DistrictService districtService;
	
	public void setService() {
		super.theService = theService;
	}

	private List<Boat> check_input(HttpServletRequest request) {

		List<Boat> li = new ArrayList<Boat>();
		for (int i = 0; i < 12; i++) {
			Boat ret = new Boat();

			String s = new String();
			s = request.getParameter("id");
			if (s != null && Tools.isInteger(s))
				ret.setId(Integer.parseInt(s));
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s))
				ret.setAccountid(Integer.parseInt(s));
			s = request.getParameter("status");
			if (s != null && Tools.isInteger(s))
				ret.setStatus(Integer.parseInt(s));
			ret.setFillTime(request.getParameter("fillTime"));
			s = request.getParameter("fillyear");
			if (s != null && Tools.isInteger(s))
				ret.setFillyear(Integer.parseInt(s));
			s = request.getParameter("province");
			if (s != null && Tools.isInteger(s))
				ret.setProvince(Integer.parseInt(s));
			s = request.getParameter("city");
			if (s != null && Tools.isInteger(s))
				ret.setCity(Integer.parseInt(s));
			s = request.getParameter("town");
			if (s != null && Tools.isInteger(s))
				ret.setTown(Integer.parseInt(s));
			s = request.getParameter("country");
			if (s != null && Tools.isInteger(s))
				ret.setCountry(Integer.parseInt(s));
			s = request.getParameter("street");
			if (s != null && Tools.isInteger(s))
				ret.setStreet(Integer.parseInt(s));
			ret.setBoattype(request.getParameter("boat[" + i + "][boattype]"));
			ret.setMeasuretype(request.getParameter("boat[" + i
					+ "][measuretype]"));
			ret.setUnit(request.getParameter("boat[" + i + "][unit]"));
			s = request.getParameter("boat[" + i + "][m1]");
			if (s != null && Tools.isNumeric(s))
				ret.setM1(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m2]");
			if (s != null && Tools.isNumeric(s))
				ret.setM2(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m3]");
			if (s != null && Tools.isNumeric(s))
				ret.setM3(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m4]");
			if (s != null && Tools.isNumeric(s))
				ret.setM4(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m5]");
			if (s != null && Tools.isNumeric(s))
				ret.setM5(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m6]");
			if (s != null && Tools.isNumeric(s))
				ret.setM6(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m7]");
			if (s != null && Tools.isNumeric(s))
				ret.setM7(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m8]");
			if (s != null && Tools.isNumeric(s))
				ret.setM8(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m9]");
			if (s != null && Tools.isNumeric(s))
				ret.setM9(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m10]");
			if (s != null && Tools.isNumeric(s))
				ret.setM10(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m11]");
			if (s != null && Tools.isNumeric(s))
				ret.setM11(Double.parseDouble(s));
			s = request.getParameter("boat[" + i + "][m12]");
			if (s != null && Tools.isNumeric(s))
				ret.setM12(Double.parseDouble(s));

			li.add(ret);
		}

		return li;
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
			if (s != null && Tools.isInteger(s))
				companyid = Integer.parseInt(s);
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

			List<Boat> input = check_input(request);
			if (input.get(0).getBoattype() == null
					&& input.get(0).getBoattype() == ""
					&& input.get(0).getMeasuretype() == null
					&& input.get(0).getMeasuretype() == "")
				return result.setStatus(-3, "no data.");

			Boat search = theService.getByField(input.get(0));
			if (search != null && search.getId() > 0)
				result.put("id", search.getId());

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

			String s=request.getParameter("city");
			int city=0;
			if(s!=null&&!s.equals("")){
				city=Integer.parseInt(s);
			}
			List<Boat> input = check_input(request);
			for (int i = 0; i < input.size(); i++) {
				input.get(i).setAccountid(companyid);

				Boat search = null;
				if (input.get(i).getAccountid() > 0) {
					search = theService.getBoat(input.get(i));
				}

				Calendar cal = Calendar.getInstance();
				input.get(i).setFillTime(Tools.ToDateStr(cal));
				if (input.get(i).getStatus() < 1)
					input.get(i).setStatus(WorkFlowController.STATUS_FILL);
				if (search != null && search.getId() > 0) {
					theService.update(input.get(i));
				} else {
					Company c = companyService.getById(companyid);
					if(c.getProvince()==0){
						input.get(i).setProvince(1);
						input.get(i).setCity(city);
					}else{
						input.get(i).setProvince(c.getProvince());
						input.get(i).setCity(c.getCity());
						input.get(i).setTown(c.getTown());
					}
					theService.add(input.get(i));
				}

				result.put("id" + i, input.get(i).getId());
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

			Boat search = theService.getById(id);
			if (null == search)
				return result.setStatus(-3, "No data.");

			result.put("data", search);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	public Boat getBoat(Boat obj) {
		Boat search = theService.getBoat(obj);
		if (search != null)
			return search;

		return null;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/get/{year}/{city}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByYear(@PathVariable int year, @PathVariable int city,
			HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Boat input = new Boat();
			input.setFillyear(year);
			input.setAccountid(companyid);

			List<Boat> list0 = theService.getByYear(input);
			List<Boat> list=new ArrayList<Boat>();
			if(city>0){
				for(Boat cd:list0){
					if(cd.getCity()==city){
						list.add(cd);
					}
				}
			}else{
				list.addAll(list0);
			}
			Set<Integer> set=new HashSet<Integer>();
			for(Boat b:list){
				set.add(b.getCity());
			}
			
			if (list.size() > 0) {
				List<BoatM> reslist=new ArrayList<BoatM>();
				for(Integer i:set){
					List<Map<Object, Object>> listm = new ArrayList<Map<Object, Object>>();
					for(Boat search : list){
						if(search.getCity()==i){
							Map<Object, Object> map = new HashMap<Object, Object>();
							map.put("id", search.getId());
							map.put("boattype", search.getBoattype());
							map.put("measuretype", search.getMeasuretype());
							map.put("unit", search.getUnit());
							map.put("m1", search.getM1());
							map.put("m2", search.getM2());
							map.put("m3", search.getM3());
							map.put("m4", search.getM4());
							map.put("m5", search.getM5());
							map.put("m6", search.getM6());
							map.put("m7", search.getM7());
							map.put("m8", search.getM8());
							map.put("m9", search.getM9());
							map.put("m10", search.getM10());
							map.put("m11", search.getM11());
							map.put("m12", search.getM12());
							listm.add(map);
						}
					}
					Boat search = list.get(0);
					BoatM m = new BoatM();
					m.setId(search.getId());
					m.setStatus(search.getStatus());
					m.setAccountid(search.getAccountid());
					m.setFillTime(search.getFillTime());
					m.setFillyear(search.getFillyear());
					m.setProvince(search.getProvince());
					m.setCity(i);
					m.setTown(search.getTown());
					m.setCountry(search.getCountry());
					m.setStreet(search.getStreet());
					m.setBoattype(listm);
					m.setCityName(districtService.getById(i).getDistrictName());
					reslist.add(m);
				}
				
				result.put("data", reslist);
				
			} else {
				return result.setStatus(0, "无记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

}
