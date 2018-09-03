package com.xf.controller.gov;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import com.xf.entity.District;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.CompanyService;
import com.xf.service.ConfigService;
import com.xf.service.DistrictService;
import com.xf.service.StaticService;
import com.xf.entity.gov.FileUpload;
import com.xf.entity.gov.MotorVehicleDb;
import com.xf.entity.gov.VehicleAction;
import com.xf.entity.gov.VehicleFactorvo;
import com.xf.service.gov.FileUploadService;
import com.xf.service.gov.MotorVehicleService;
import com.xf.service.gov.VehicleActionService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/vehicle/action")
public class VehicleActionController {

	@Autowired
	private FileUploadService fuService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private VehicleActionService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private MotorVehicleService mvservice;
	@Autowired
	private DistrictService disservice;

	private VehicleAction check_input(HttpServletRequest request)
			throws ParseException {
		VehicleAction ret = new VehicleAction();
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
		s = request.getParameter("serial");
		if (s != null && Tools.isInteger(s))
			ret.setSerial(Integer.parseInt(s));
		ret.setVehicletype(request.getParameter("vehicletype"));
		ret.setPlatescolor(request.getParameter("platescolor"));
		ret.setRegisterdate(request.getParameter("registerdate"));
		ret.setCheckdate(request.getParameter("checkdate"));
		s = request.getParameter("mileage");
		if (s != null && Tools.isNumeric(s))
			ret.setMileage(Double.parseDouble(s));
		ret.setGastype(request.getParameter("gastype"));

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
			if (s != null && Tools.isInteger(s))
				companyid = Integer.parseInt(s);
			if (companyid <= 0)
				return result.setStatus(-2, "no company id.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	// ==============================================================================

	@RequestMapping(value = "/years", method = RequestMethod.GET)
	@ResponseBody
	public Object getYears(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;
			List<Integer> fillyear = theService.getYears(companyid);
			if (fillyear.isEmpty()) {
			}
			result.put("data", fillyear);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/deletelast", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}
		String year = request.getParameter("fillyear");
		int maxyear=Integer.parseInt(year);
		String account = request.getParameter("accountid");
		int accountid = Integer.parseInt(account);
		FileUpload file = new FileUpload();
		try {
//			maxyear = mvservice.getMaxYear();
			if(accountid>0&&maxyear>0){
				Company c=companyService.getById(accountid);
				if(c.getProvince()>0){
					mvservice.deleteByYear(maxyear);
					file.setAccountid(accountid);
					file.setFillyear(maxyear);
					file.setTabletype("ap_motor_vehicle");
					file.setImportFile(null);
					file.setImported(0);
					fuService.update(file);
				}else{
					mvservice.deleteByYear2(maxyear);
					file.setAccountid(accountid);
					file.setFillyear(maxyear);
					file.setTabletype("ap_motor_vehicle");
					file.setImportFile(null);
					file.setImported(0);
					fuService.update(file);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "无数据可删");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			VehicleAction input = check_input(request);

			input.setAccountid(companyid);

			VehicleAction search = null;
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
				theService.add(input);
			}

			result.put("id", input.getId());

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

			VehicleAction search = theService.getById(id);
			if (null == search)
				return result.setStatus(-3, "No data.");

			result.put("data", search);

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
			VehicleAction input = new VehicleAction();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<VehicleAction> list = theService.getByYear(input);
			if (list != null)
				result.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/clear/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object clearData(@PathVariable int year, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;
			VehicleAction input = new VehicleAction();
			input.setFillyear(year);
			input.setAccountid(companyid);
			theService.clearData(input);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "moteryears", method = RequestMethod.GET)
	@ResponseBody
	public Object getMotorYears(HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}

		List<Integer> listyear = mvservice.getYears();
		result.put("data", listyear);
		return result.setStatus(0, "ok");

	}

	@RequestMapping(value = "/getbyyear/{year}/{accountid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getMotor(@PathVariable int year,@PathVariable int accountid, HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}
		List<MotorVehicleDb> listmv =null;
		if(accountid>0){
			Company c=companyService.getById(accountid);
			if(c.getProvince()>0){
				listmv = mvservice.getByYear(year);
			}else{
				listmv = mvservice.getByYear2(year);
			}
		}
		

		if (listmv.size() <= 0)
			return result.setStatus(-2, "无数据");

		List<Map> list = new ArrayList<Map>();

		List<District> listdis = disservice.getByParent(1);

		for (District dis : listdis) {
			Map map = new HashMap();
			map.put("cityid", dis.getId());
			map.put("cityname", dis.getDistrictName());
			map.put("year", year);
			List<Map> listmap = new ArrayList<Map>();

			for (int i = 0; i < 6; i++) {
				Map ma = new HashMap();
				if (i == 0) {
					ma.put("排放标准id", 30001);
					ma.put("standard", "国0");
					List<Map> listvm = new ArrayList<Map>();
					for (MotorVehicleDb mv : listmv) {
						if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29001) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29001);
							mapmv.put("车型", "载客_微型_出租_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29002) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29002);
							mapmv.put("车型", "载客_微型_出租_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29003) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29003);
							mapmv.put("车型", "载客_微型_其他_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29004) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29004);
							mapmv.put("车型", "载客_微型_其他_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29005) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29005);
							mapmv.put("车型", "载客_小型-出租-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29006) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29006);
							mapmv.put("车型", "载客_小型-出租-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29007) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29007);
							mapmv.put("车型", "载客_小型-出租-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29008) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29008);
							mapmv.put("车型", "载客_小型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29009) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29009);
							mapmv.put("车型", "载客_小型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29010) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29010);
							mapmv.put("车型", "载客_小型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29011) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29011);
							mapmv.put("车型", "载客_中型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29012) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29012);
							mapmv.put("车型", "载客_中型-公交-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29013) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29013);
							mapmv.put("车型", "载客_中型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29014) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29014);
							mapmv.put("车型", "载客_中型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29015) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29015);
							mapmv.put("车型", "载客_中型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29016) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29016);
							mapmv.put("车型", "载客_中型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29017) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29017);
							mapmv.put("车型", "载客_大型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29018) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29018);
							mapmv.put("车型", "载客_大型-公交-柴油注册量（辆");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29019) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29019);
							mapmv.put("车型", "载客_大型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29020) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29020);
							mapmv.put("车型", "载客_大型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29021) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29021);
							mapmv.put("车型", "载客_大型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29022) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29022);
							mapmv.put("车型", "载客_大型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29023) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29023);
							mapmv.put("车型", "载货-微型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29024) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29024);
							mapmv.put("车型", "载货-微型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29025) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29025);
							mapmv.put("车型", "载货-轻型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29026) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29026);
							mapmv.put("车型", "载货-轻型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29027) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29027);
							mapmv.put("车型", "载货-中型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29028) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29028);
							mapmv.put("车型", "载货-中型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29029) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29029);
							mapmv.put("车型", "载货-重型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29030) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29030);
							mapmv.put("车型", "载货-重型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29031) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29031);
							mapmv.put("车型", "低速-三轮注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29032) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29032);
							mapmv.put("车型", "低速-低速载货注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29033) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29033);
							mapmv.put("车型", "摩托车_普通注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30001
								&& mv.getVehiclemodel() == 29034) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29034);
							mapmv.put("车型", "摩托车_轻便注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						}
					}
					ma.put("list", listvm);
				} else if (i == 1) {
					ma.put("排放标准id", 30002);
					ma.put("standard", "国1");
					List<Map> listvm = new ArrayList<Map>();
					for (MotorVehicleDb mv : listmv) {
						if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29001) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29001);
							mapmv.put("车型", "载客_微型_出租_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29002) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29002);
							mapmv.put("车型", "载客_微型_出租_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29003) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29003);
							mapmv.put("车型", "载客_微型_其他_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29004) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29004);
							mapmv.put("车型", "载客_微型_其他_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29005) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29005);
							mapmv.put("车型", "载客_小型-出租-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29006) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29006);
							mapmv.put("车型", "载客_小型-出租-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29007) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29007);
							mapmv.put("车型", "载客_小型-出租-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29008) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29008);
							mapmv.put("车型", "载客_小型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29009) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29009);
							mapmv.put("车型", "载客_小型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29010) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29010);
							mapmv.put("车型", "载客_小型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29011) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29011);
							mapmv.put("车型", "载客_中型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29012) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29012);
							mapmv.put("车型", "载客_中型-公交-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29013) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29013);
							mapmv.put("车型", "载客_中型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29014) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29014);
							mapmv.put("车型", "载客_中型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29015) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29015);
							mapmv.put("车型", "载客_中型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29016) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29016);
							mapmv.put("车型", "载客_中型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29017) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29017);
							mapmv.put("车型", "载客_大型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29018) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29018);
							mapmv.put("车型", "载客_大型-公交-柴油注册量（辆");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29019) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29019);
							mapmv.put("车型", "载客_大型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29020) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29020);
							mapmv.put("车型", "载客_大型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29021) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29021);
							mapmv.put("车型", "载客_大型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29022) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29022);
							mapmv.put("车型", "载客_大型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29023) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29023);
							mapmv.put("车型", "载货-微型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29024) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29024);
							mapmv.put("车型", "载货-微型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29025) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29025);
							mapmv.put("车型", "载货-轻型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29026) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29026);
							mapmv.put("车型", "载货-轻型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29027) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29027);
							mapmv.put("车型", "载货-中型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29028) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29028);
							mapmv.put("车型", "载货-中型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29029) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29029);
							mapmv.put("车型", "载货-重型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29030) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29030);
							mapmv.put("车型", "载货-重型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29031) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29031);
							mapmv.put("车型", "低速-三轮注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29032) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29032);
							mapmv.put("车型", "低速-低速载货注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29033) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29033);
							mapmv.put("车型", "摩托车_普通注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30002
								&& mv.getVehiclemodel() == 29034) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29034);
							mapmv.put("车型", "摩托车_轻便注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						}
					}
					ma.put("list", listvm);
				} else if (i == 2) {
					ma.put("排放标准id", 30003);
					ma.put("standard", "国2");
					List<Map> listvm = new ArrayList<Map>();
					for (MotorVehicleDb mv : listmv) {
						if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29001) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29001);
							mapmv.put("车型", "载客_微型_出租_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29002) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29002);
							mapmv.put("车型", "载客_微型_出租_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29003) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29003);
							mapmv.put("车型", "载客_微型_其他_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29004) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29004);
							mapmv.put("车型", "载客_微型_其他_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29005) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29005);
							mapmv.put("车型", "载客_小型-出租-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29006) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29006);
							mapmv.put("车型", "载客_小型-出租-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29007) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29007);
							mapmv.put("车型", "载客_小型-出租-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29008) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29008);
							mapmv.put("车型", "载客_小型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29009) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29009);
							mapmv.put("车型", "载客_小型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29010) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29010);
							mapmv.put("车型", "载客_小型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29011) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29011);
							mapmv.put("车型", "载客_中型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29012) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29012);
							mapmv.put("车型", "载客_中型-公交-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29013) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29013);
							mapmv.put("车型", "载客_中型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29014) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29014);
							mapmv.put("车型", "载客_中型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29015) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29015);
							mapmv.put("车型", "载客_中型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29016) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29016);
							mapmv.put("车型", "载客_中型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29017) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29017);
							mapmv.put("车型", "载客_大型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29018) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29018);
							mapmv.put("车型", "载客_大型-公交-柴油注册量（辆");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29019) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29019);
							mapmv.put("车型", "载客_大型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29020) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29020);
							mapmv.put("车型", "载客_大型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29021) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29021);
							mapmv.put("车型", "载客_大型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29022) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29022);
							mapmv.put("车型", "载客_大型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29023) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29023);
							mapmv.put("车型", "载货-微型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29024) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29024);
							mapmv.put("车型", "载货-微型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29025) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29025);
							mapmv.put("车型", "载货-轻型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29026) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29026);
							mapmv.put("车型", "载货-轻型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29027) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29027);
							mapmv.put("车型", "载货-中型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29028) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29028);
							mapmv.put("车型", "载货-中型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29029) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29029);
							mapmv.put("车型", "载货-重型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29030) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29030);
							mapmv.put("车型", "载货-重型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29031) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29031);
							mapmv.put("车型", "低速-三轮注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29032) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29032);
							mapmv.put("车型", "低速-低速载货注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29033) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29033);
							mapmv.put("车型", "摩托车_普通注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30003
								&& mv.getVehiclemodel() == 29034) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29034);
							mapmv.put("车型", "摩托车_轻便注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						}
					}
					ma.put("list", listvm);
				} else if (i == 3) {
					ma.put("排放标准id", 30004);
					ma.put("standard", "国3");
					List<Map> listvm = new ArrayList<Map>();
					for (MotorVehicleDb mv : listmv) {
						if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29001) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29001);
							mapmv.put("车型", "载客_微型_出租_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29002) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29002);
							mapmv.put("车型", "载客_微型_出租_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29003) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29003);
							mapmv.put("车型", "载客_微型_其他_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29004) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29004);
							mapmv.put("车型", "载客_微型_其他_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29005) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29005);
							mapmv.put("车型", "载客_小型-出租-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29006) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29006);
							mapmv.put("车型", "载客_小型-出租-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29007) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29007);
							mapmv.put("车型", "载客_小型-出租-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29008) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29008);
							mapmv.put("车型", "载客_小型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29009) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29009);
							mapmv.put("车型", "载客_小型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29010) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29010);
							mapmv.put("车型", "载客_小型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29011) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29011);
							mapmv.put("车型", "载客_中型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29012) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29012);
							mapmv.put("车型", "载客_中型-公交-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29013) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29013);
							mapmv.put("车型", "载客_中型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29014) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29014);
							mapmv.put("车型", "载客_中型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29015) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29015);
							mapmv.put("车型", "载客_中型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29016) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29016);
							mapmv.put("车型", "载客_中型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29017) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29017);
							mapmv.put("车型", "载客_大型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29018) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29018);
							mapmv.put("车型", "载客_大型-公交-柴油注册量（辆");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29019) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29019);
							mapmv.put("车型", "载客_大型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29020) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29020);
							mapmv.put("车型", "载客_大型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29021) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29021);
							mapmv.put("车型", "载客_大型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29022) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29022);
							mapmv.put("车型", "载客_大型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29023) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29023);
							mapmv.put("车型", "载货-微型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29024) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29024);
							mapmv.put("车型", "载货-微型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29025) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29025);
							mapmv.put("车型", "载货-轻型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29026) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29026);
							mapmv.put("车型", "载货-轻型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29027) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29027);
							mapmv.put("车型", "载货-中型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29028) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29028);
							mapmv.put("车型", "载货-中型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29029) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29029);
							mapmv.put("车型", "载货-重型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29030) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29030);
							mapmv.put("车型", "载货-重型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29031) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29031);
							mapmv.put("车型", "低速-三轮注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29032) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29032);
							mapmv.put("车型", "低速-低速载货注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29033) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29033);
							mapmv.put("车型", "摩托车_普通注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30004
								&& mv.getVehiclemodel() == 29034) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29034);
							mapmv.put("车型", "摩托车_轻便注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						}
					}
					ma.put("list", listvm);
				} else if (i == 4) {
					ma.put("排放标准id", 30005);
					ma.put("standard", "国4");
					List<Map> listvm = new ArrayList<Map>();
					for (MotorVehicleDb mv : listmv) {
						if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29001) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29001);
							mapmv.put("车型", "载客_微型_出租_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29002) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29002);
							mapmv.put("车型", "载客_微型_出租_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29003) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29003);
							mapmv.put("车型", "载客_微型_其他_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29004) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29004);
							mapmv.put("车型", "载客_微型_其他_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29005) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29005);
							mapmv.put("车型", "载客_小型-出租-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29006) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29006);
							mapmv.put("车型", "载客_小型-出租-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29007) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29007);
							mapmv.put("车型", "载客_小型-出租-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29008) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29008);
							mapmv.put("车型", "载客_小型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29009) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29009);
							mapmv.put("车型", "载客_小型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29010) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29010);
							mapmv.put("车型", "载客_小型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29011) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29011);
							mapmv.put("车型", "载客_中型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29012) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29012);
							mapmv.put("车型", "载客_中型-公交-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29013) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29013);
							mapmv.put("车型", "载客_中型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29014) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29014);
							mapmv.put("车型", "载客_中型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29015) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29015);
							mapmv.put("车型", "载客_中型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29016) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29016);
							mapmv.put("车型", "载客_中型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29017) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29017);
							mapmv.put("车型", "载客_大型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29018) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29018);
							mapmv.put("车型", "载客_大型-公交-柴油注册量（辆");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29019) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29019);
							mapmv.put("车型", "载客_大型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29020) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29020);
							mapmv.put("车型", "载客_大型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29021) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29021);
							mapmv.put("车型", "载客_大型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29022) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29022);
							mapmv.put("车型", "载客_大型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29023) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29023);
							mapmv.put("车型", "载货-微型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29024) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29024);
							mapmv.put("车型", "载货-微型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29025) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29025);
							mapmv.put("车型", "载货-轻型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29026) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29026);
							mapmv.put("车型", "载货-轻型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29027) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29027);
							mapmv.put("车型", "载货-中型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29028) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29028);
							mapmv.put("车型", "载货-中型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29029) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29029);
							mapmv.put("车型", "载货-重型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29030) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29030);
							mapmv.put("车型", "载货-重型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29031) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29031);
							mapmv.put("车型", "低速-三轮注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29032) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29032);
							mapmv.put("车型", "低速-低速载货注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29033) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29033);
							mapmv.put("车型", "摩托车_普通注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30005
								&& mv.getVehiclemodel() == 29034) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29034);
							mapmv.put("车型", "摩托车_轻便注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						}
					}
					ma.put("list", listvm);
				} else if (i == 5) {
					ma.put("排放标准id", 30006);
					ma.put("standard", "国5");
					List<Map> listvm = new ArrayList<Map>();
					for (MotorVehicleDb mv : listmv) {
						if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29001) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29001);
							mapmv.put("车型", "载客_微型_出租_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29002) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29002);
							mapmv.put("车型", "载客_微型_出租_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29003) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29003);
							mapmv.put("车型", "载客_微型_其他_汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29004) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29004);
							mapmv.put("车型", "载客_微型_其他_其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29005) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29005);
							mapmv.put("车型", "载客_小型-出租-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29006) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29006);
							mapmv.put("车型", "载客_小型-出租-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29007) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29007);
							mapmv.put("车型", "载客_小型-出租-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29008) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29008);
							mapmv.put("车型", "载客_小型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29009) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29009);
							mapmv.put("车型", "载客_小型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29010) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29010);
							mapmv.put("车型", "载客_小型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29011) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29011);
							mapmv.put("车型", "载客_中型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29012) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29012);
							mapmv.put("车型", "载客_中型-公交-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29013) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29013);
							mapmv.put("车型", "载客_中型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29014) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29014);
							mapmv.put("车型", "载客_中型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29015) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29015);
							mapmv.put("车型", "载客_中型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29016) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29016);
							mapmv.put("车型", "载客_中型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29017) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29017);
							mapmv.put("车型", "载客_大型-公交-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29018) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29018);
							mapmv.put("车型", "载客_大型-公交-柴油注册量（辆");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29019) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29019);
							mapmv.put("车型", "载客_大型-公交-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29020) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29020);
							mapmv.put("车型", "载客_大型-其他-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29021) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29021);
							mapmv.put("车型", "载客_大型-其他-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29022) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29022);
							mapmv.put("车型", "载客_大型-其他-其他注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29023) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29023);
							mapmv.put("车型", "载货-微型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29024) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29024);
							mapmv.put("车型", "载货-微型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29025) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29025);
							mapmv.put("车型", "载货-轻型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29026) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29026);
							mapmv.put("车型", "载货-轻型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29027) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29027);
							mapmv.put("车型", "载货-中型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29028) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29028);
							mapmv.put("车型", "载货-中型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29029) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29029);
							mapmv.put("车型", "载货-重型-汽油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29030) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29030);
							mapmv.put("车型", "载货-重型-柴油注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29031) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29031);
							mapmv.put("车型", "低速-三轮注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29032) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29032);
							mapmv.put("车型", "低速-低速载货注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29033) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29033);
							mapmv.put("车型", "摩托车_普通注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						} else if (mv.getCity() == dis.getId()
								&& mv.getStandard() == 30006
								&& mv.getVehiclemodel() == 29034) {
							Map mapmv = new HashMap();
							mapmv.put("车型id", 29034);
							mapmv.put("车型", "摩托车_轻便注册量（辆）");
							mapmv.put("value", mv.getHoldings());
							listvm.add(mapmv);
						}
					}
					ma.put("list", listvm);
				}

				listmap.add(ma);
			}
			map.put("list", listmap);
			list.add(map);
		}

		result.put("data", list);
		return result.setStatus(0, "");

	}

	@RequestMapping(value = "/getFactor", method = RequestMethod.GET)
	@ResponseBody
	private Object getFactor(HttpServletRequest request) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		try {
			HashSet<String> set = new LinkedHashSet<String>();
			HashSet<String> sev = new LinkedHashSet<String>();

			List<VehicleFactorvo> flist = mvservice.getVfactor();
			if (flist.size() > 0) {
				for (VehicleFactorvo fli : flist) {
					if(fli!=null&&fli.getPollutantName()!=null)
					set.add(fli.getPollutantName());
					if(fli!=null&&fli.getVehiclemodel()!=null)
					sev.add(fli.getVehiclemodel());
				}
			}

			List<HashMap<String, Object>> fList = new ArrayList<HashMap<String, Object>>();
			if (set.size() > 0) {
				for (String sevelc : sev) {
					List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
					HashMap<String, Object> pany=new HashMap<String, Object>();
					pany.put("vehiclemodel", sevelc);
					for (String sepoll : set) {
						List<HashMap<String, Object>> polist = new ArrayList<HashMap<String, Object>>();
						HashMap<String, Object> panypo=new HashMap<String, Object>();
						panypo.put("pollutantName", sepoll);
						for (VehicleFactorvo vf : flist) {
							if(vf.getVehiclemodel()!=null&&vf.getVehiclemodel().equals(sevelc)&&vf.getPollutantName()!=null&&vf.getPollutantName().equals(sepoll)){
							HashMap<String, Object> panyv=new HashMap<String, Object>();
								panyv.put("standard", vf.getStandard());
								panyv.put("factor", vf.getFactor());
								polist.add(panyv);
							}
						}
						panypo.put("polist", polist);
						list.add(panypo);
					}
					pany.put("pany", list);
					fList.add(pany);
				}
			}
			result.put("flist", fList);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "ok");
	}
}
