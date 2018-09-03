package com.xf.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.CFacilityFill;
import com.xf.entity.Company;
import com.xf.entity.ControlFacility;
import com.xf.entity.Devices;
import com.xf.entity.ProduceStep;
import com.xf.entity.Static;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.ConfigService;
import com.xf.service.ControlFacilityService;
import com.xf.service.DeviceService;
import com.xf.service.FacilityFillService;
import com.xf.service.ProduceStepService;
import com.xf.service.StaticService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/facility")
public class FacilityController {

	@Autowired
	private FacilityFillService faciFillService;
	@Autowired
	private ProduceStepService stepService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ControlFacilityService facilityService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	int facilityid = 0;
	int companyid = 0;
	int stepid = 0;
	int deviceid = 0;
	List<String> stepArray = new ArrayList<String>();
	List<String> deviceArray = new ArrayList<String>();

	private ControlFacility check(HttpServletRequest request) {

		String s = new String();
		ControlFacility faci = new ControlFacility();
		faci.setFacilityModel(request.getParameter("facilityModel"));
		faci.setRemark(request.getParameter("remark"));
		faci.setTechnique1Name(request.getParameter("technique1Name"));
		faci.setTechnique2Name(request.getParameter("technique2Name"));

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s)) {
			faci.setId(Integer.parseInt(s));
			facilityid = faci.getId();
		}
		s = request.getParameter("facilityid");
		if (s != null && Tools.isInteger(s))
			facilityid = Integer.parseInt(s);
		s = request.getParameter("enterpriceId");
		if (s != null && Tools.isInteger(s)) {
			faci.setEnterpriceId(Integer.parseInt(s));
			companyid = faci.getEnterpriceId();
		}
		s = request.getParameter("outletId");
		if (s != null && Tools.isInteger(s))
			faci.setOutletId(Integer.parseInt(s));
		s = request.getParameter("pollutantId");
		if (s != null && Tools.isInteger(s))
			faci.setPollutantId(Integer.parseInt(s));
		s = request.getParameter("technique1");
		if (s != null && Tools.isInteger(s))
			faci.setTechnique1(Integer.parseInt(s));
		s = request.getParameter("technique2");
		if (s != null && Tools.isInteger(s))
			faci.setTechnique2(Integer.parseInt(s));
		s = request.getParameter("enabled");
		if (s != null && Tools.isInteger(s))
			faci.setEnabled(Integer.parseInt(s));
		s = request.getParameter("stepid");
		if (s != null && Tools.isInteger(s))
			stepid = Integer.parseInt(s);
		s = request.getParameter("deviceid");
		if (s != null && Tools.isInteger(s))
			deviceid = Integer.parseInt(s);

		return faci;
	}

	private CFacilityFill check_fill(HttpServletRequest request) {

		String s = new String();
		CFacilityFill faci = new CFacilityFill();
		faci.setFillTime(request.getParameter("fillTime"));

		s = request.getParameter("facilityId");
		if (s != null && Tools.isInteger(s))
			faci.setFacilityId(Integer.parseInt(s));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			faci.setFillyear(Integer.parseInt(s));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			faci.setStatus(Integer.parseInt(s));
		s = request.getParameter("enterpriceId");
		if (s != null && Tools.isInteger(s)) {
			companyid = Integer.parseInt(s);
		}

		s = request.getParameter("collectRate");
		if (s != null && Tools.isNumeric(s))
			faci.setCollectRate(Double.parseDouble(s));
		s = request.getParameter("daysPerYear");
		if (s != null && Tools.isNumeric(s))
			faci.setDaysPerYear(Double.parseDouble(s));
		s = request.getParameter("disRate");
		if (s != null && Tools.isNumeric(s))
			faci.setDisRate(Double.parseDouble(s));
		s = request.getParameter("yearCost");
		if (s != null && Tools.isNumeric(s))
			faci.setYearCost(Double.parseDouble(s));
		s = request.getParameter("materialConsume");
		if (s != null && Tools.isNumeric(s))
			faci.setMaterialConsume(Double.parseDouble(s));
		s = request.getParameter("NH3Release");
		if (s != null && Tools.isNumeric(s))
			faci.setNH3Release(Double.parseDouble(s));
		s = request.getParameter("materialName");
		if (!s.equals("") && s != null)
			faci.setMaterialName(s);
		return faci;
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addFacility(HttpServletRequest request) {

		ControlFacility facility = check(request);
		if (facility == null)
			return result.setStatus(-3, "no facility.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (facility.getEnterpriceId() <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = facility.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			facility.setEnterpriceId(companyid);

			// 处理固定信息
			Static info = new Static();
			if (facility.getTechnique1() <= 0
					&& facility.getTechnique1Name() != null
					&& !facility.getTechnique1Name().isEmpty()) {
				info.setAccountid(facility.getEnterpriceId());
				info.setGroupid(8);
				info.setPid(0);
				info.setName(facility.getTechnique1Name());
				staticService.add(info);

				facility.setTechnique1(info.getId());
			}

			if (facility.getTechnique2() <= 0 && facility.getTechnique1() > 0
					&& facility.getTechnique2Name() != null
					&& !facility.getTechnique2Name().isEmpty()) {
				info.setAccountid(facility.getEnterpriceId());
				info.setGroupid(9);
				info.setPid(facility.getTechnique1());
				info.setName(facility.getTechnique2Name());
				staticService.add(info);

				facility.setTechnique2(info.getId());
			}

			if (facility.getId() > 0) {
				facilityService.update(facility);
			} else {
				List<ControlFacility> faci = facilityService.getBy(
						facility.getEnterpriceId(), facility.getPollutantId(),
						facility.getTechnique1(), facility.getTechnique2(),
						facility.getOutletId(), facility.getFacilityModel());
				if (faci.size() > 0) {
					facility.setSerial(faci.size() + 1);
					facilityService.add(facility);
				} else {
					facility.setSerial(1);
					facilityService.add(facility);
				}

				if (stepid > 0) {
					facilityService.addStep(facility.getId(), stepid);
				}
				if (deviceid > 0) {
					facilityService.addDevice(facility.getId(), deviceid);
				}
			}

			result.put("facilityid", facility.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "exception");
		}

		return result.setStatus(0, "");
	}

	private List<HashMap<String, Object>> getFacilities(int companyid,
			int facilityid, int fillyear) {

		ControlFacility f;
		List<ControlFacility> facilist;
		if (facilityid > 0) {
			f = facilityService.getById(fillyear, facilityid);
			facilist = new ArrayList<ControlFacility>();
			facilist.add(f);
		} else
			facilist = facilityService.getByCompany(companyid, fillyear);

		if (facilist == null)
			return null;

		List<HashMap<String, Object>> flist = new ArrayList<HashMap<String, Object>>();

		for (ControlFacility faci : facilist) {
			HashMap<String, Object> mFaci = new HashMap<String, Object>();
			List<HashMap<String, Object>> mList = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> dmList = new ArrayList<HashMap<String, Object>>();

			mFaci.put("facilityid", faci.getId());
			mFaci.put("pollutantId", faci.getPollutantId());
			mFaci.put("pollutantName", faci.getPollutantName());
			mFaci.put("technique1", faci.getTechnique1());
			mFaci.put("technique1Name", faci.getTechnique1Name());
			mFaci.put("technique2", faci.getTechnique2());
			mFaci.put("technique2Name", faci.getTechnique2Name());
			mFaci.put("outletId", faci.getOutletId());
			mFaci.put("outletSerial", faci.getOutletSerial());
			mFaci.put("facilityModel", faci.getFacilityModel());
			mFaci.put("status", faci.getStatus());
			mFaci.put("serial", faci.getSerial());
			mFaci.put("enabled", faci.getEnabled());
			mFaci.put("remark", faci.getRemark());

			List<ProduceStep> sList = stepService.getByFacility(faci.getId(),
					fillyear);
			if (sList != null) {
				for (ProduceStep ps : sList) {
					HashMap<String, Object> mStep = new HashMap<String, Object>();
					mStep.put("stepId", ps.getId());
					mStep.put("stepName", ps.getStepName());
					mStep.put("stepSerial", ps.getStepSerial());
					mList.add(mStep);
				}
				mFaci.put("steps", mList);
			}
			List<Devices> dList = deviceService.getByFacility(faci.getId(),
					fillyear);
			if (dList != null) {
				for (Devices ps : dList) {
					HashMap<String, Object> mDevice = new HashMap<String, Object>();
					mDevice.put("deviceId", ps.getId());
					mDevice.put("deviceTypeName1", ps.getDevicetypeName());
					mDevice.put("deviceTypeName2", ps.getDevicetypeName2());
					mDevice.put("deviceSerial", ps.getDeviceSerial());
					dmList.add(mDevice);
				}
				mFaci.put("devices", dmList);
			}

			flist.add(mFaci);
		}

		return flist;
	}

	@RequestMapping(value = "/all/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request, @PathVariable int fillyear) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (this.companyid <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = this.companyid;
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<HashMap<String, Object>> flist = getFacilities(companyid, 0,
					fillyear);

			result.put("facilities", flist);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {

		try {
			ControlFacility faci = facilityService.getfacById(id);
			if (faci == null)
				return result.setStatus(-3, "no facility.");

			int companyid;
			if (loginManage.isCompanyLogin(request)) {
				Company c = loginManage.getLoginCompany(request);
				companyid = c.getId();
				if (companyid != faci.getEnterpriceId())
					return result.setStatus(-4,
							"facility is not in your company.");
			} else if (loginManage.isUserLogin(request)) {
				if (this.companyid <= 0)
					return result.setStatus(-2, "no company id.");
				companyid = this.companyid;
			} else {
				return result.setStatus(-1, "No login.");
			}

			List<HashMap<String, Object>> flist = getFacilities(companyid, id,
					0);
			result.put("facilities", flist);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	private HashMap<String, Object> check_enterprise(HttpServletRequest request) {

		if (facilityid <= 0)
			return result.setStatus(-3, "no facility id.");

		ControlFacility facility = facilityService.getfacById(facilityid);
		if (facility == null)
			return result.setStatus(-4, "no facility.");

		if (stepid <= 0)
			return result.setStatus(-3, "no step id.");
		ProduceStep step = stepService.getById1(stepid);
		if (step == null)
			return result.setStatus(-4, "no step.");

		if (deviceid <= 0)
			return result.setStatus(-3, "no device id.");
		Devices device = deviceService.getBydevId(deviceid);
		if (device == null)
			return result.setStatus(-4, "no device.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
			if (companyid != facility.getEnterpriceId()
					|| companyid != step.getEnterpriceId())
				return result.setStatus(-4,
						"facility or step is not in your company.");
			if (companyid != facility.getEnterpriceId()
					|| companyid != device.getEnterpriceId())
				return result.setStatus(-4,
						"facility or device is not in your company.");
		} else if (loginManage.isUserLogin(request)) {
			if (this.companyid <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = this.companyid;
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	@RequestMapping(value = "/step/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addFaciStep(@RequestBody String favilityStep,
			HttpServletRequest request) {

		try {
			if (favilityStep != null) {
				JSONObject objson = JSONObject.fromObject(favilityStep);
				JSONObject datajson = (JSONObject) objson.get("data");
				List list = new ArrayList();
				try {
					list = (List<String>) datajson.get("stepid");
					String id = (String) datajson.get("facilityid");
					facilityid = Integer.parseInt(id);
				} catch (ClassCastException e) {
					list = (List<Integer>) datajson.get("stepid");
					facilityid = (Integer) datajson.get("facilityid");
				}
				for (int i = 0; i < list.size(); i++) {
					stepArray.add(list.get(i).toString());
				}

				facilityService.delStepfacid(facilityid);

				for (int i = 0; i < stepArray.size(); i++) {
					int stepid = Integer.parseInt(stepArray.get(i));
					facilityService.addStep(facilityid, stepid);
				}
			} else {
				HashMap<String, Object> ret = check_enterprise(request);
				if (ret != null)
					return ret;

				facilityService.addStep(facilityid, stepid);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/step/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delFaciStep(HttpServletRequest request) {

		try {
			HashMap<String, Object> ret = check_enterprise(request);
			if (ret != null)
				return ret;

			facilityService.delStep(facilityid, stepid);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/device/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addFaciDevice(@RequestBody String favilityStep,
			HttpServletRequest request) {

		try {

			if (favilityStep != null) {
				JSONObject objson = JSONObject.fromObject(favilityStep);
				JSONObject datajson = (JSONObject) objson.get("data");
				List list = new ArrayList();
				try {
					list = (List<String>) datajson.get("deviceid");
					String id = (String) datajson.get("facilityid");
					facilityid = Integer.parseInt(id);
				} catch (ClassCastException e) {
					list = (List<Integer>) datajson.get("deviceid");
					facilityid = (Integer) datajson.get("facilityid");
				}
				for (int i = 0; i < list.size(); i++) {
					deviceArray.add(list.get(i).toString());
				}

				facilityService.delDevicefacid(facilityid);

				for (int i = 0; i < deviceArray.size(); i++) {
					int deviceid = Integer.parseInt(deviceArray.get(i));
					facilityService.addDevice(facilityid, deviceid);
				}
			} else {
				HashMap<String, Object> ret = check_enterprise(request);
				if (ret != null)
					return ret;

				facilityService.addDevice(facilityid, deviceid);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/device/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delFaciDevice(HttpServletRequest request) {

		try {
			HashMap<String, Object> ret = check_enterprise(request);
			if (ret != null)
				return ret;

			facilityService.delDevice(facilityid, deviceid);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/company/{id}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByCompanyId(@PathVariable int id,
			@PathVariable int fillyear, HttpServletRequest request) {

		try {

			if (loginManage.isCompanyLogin(request)) {
				Company c = loginManage.getLoginCompany(request);
				if (c.getId() != id)
					return result.setStatus(-2, "not your company.");
			} else if (!loginManage.isUserLogin(request)) {
				return result.setStatus(-1, "No login.");
			}

			List<HashMap<String, Object>> flist = getFacilities(id, 0, fillyear);

			for (HashMap<String, Object> f : flist) {
				Integer fid = (Integer) f.get("facilityid");
				CFacilityFill cf = faciFillService.getByCuryearFill(fid,
						fillyear);
				if (cf != null) {
					f.put("cfill", cf);
				} else {
					CFacilityFill cff = new CFacilityFill();
					cff.setFacilityId(fid);
					f.put("cfill", cff);
				}
			}
			result.put("list", flist);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/fill/{fillyear}/{fid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCuryear(@PathVariable int fillyear, @PathVariable int fid,
			HttpServletRequest request) {

		try {
			ControlFacility facility = facilityService.getById(fillyear, fid);
			if (facility == null)
				return result.setStatus(-4, "no facility.");

			int companyid;
			if (loginManage.isCompanyLogin(request)) {
				Company c = loginManage.getLoginCompany(request);
				companyid = c.getId();

				if (companyid != facility.getEnterpriceId())
					return result.setStatus(-4,
							"facility is not in your company.");
			} else if (loginManage.isUserLogin(request)) {
				if (this.companyid <= 0)
					return result.setStatus(-2, "no company id.");
				companyid = this.companyid;
			} else {
				return result.setStatus(-1, "No login.");
			}

			result.put("facility", facility);

			List<ProduceStep> fSteps = stepService.getByFacility(fid, fillyear);
			if (fSteps != null) {
				result.put("steps", fSteps);
			}

			List<Devices> fDevices = deviceService.getByFacility(fid, fillyear);
			if (fDevices != null) {
				result.put("devices", fDevices);
			}

			CFacilityFill cfFound = null;
			List<CFacilityFill> flist = faciFillService.getByFacility(fid,
					fillyear);
			for (CFacilityFill faciFill : flist) {
				if (faciFill.getFillyear() == fillyear) {
					cfFound = faciFill;
					break;
				}
			}

			if (cfFound == null) {
				CFacilityFill faciFill = new CFacilityFill();
				faciFill.setFillyear(fillyear);
				faciFill.setFacilityId(fid);
				faciFillService.add(faciFill);
				cfFound = faciFill;
			}

			List<CFacilityFill> ret = new ArrayList<CFacilityFill>();
			ret.add(cfFound);

			result.put("fills", ret);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	// @RequestMapping(value = "/fill/history/{fid}", method =
	// RequestMethod.GET)
	// @ResponseBody
	// public Object getHistory(@PathVariable int fid, HttpServletRequest
	// request) {
	//
	// try {
	// ControlFacility facility = facilityService.getById(fid);
	// if (facility == null) return result.setStatus(-4, "no facility.");
	//
	// int companyid;
	// if (loginManage.isCompanyLogin(request)) {
	// Company c = loginManage.getLoginCompany(request);
	// companyid = c.getId();
	// if (companyid != facility.getEnterpriceId()) return result.setStatus(-4,
	// "facility is not in your company.");
	// }
	// else if (loginManage.isUserLogin(request)) {
	// if (this.companyid <= 0)
	// return result.setStatus(-2, "no company id.");
	// companyid = this.companyid;
	// }
	// else {
	// return result.setStatus(-1, "No login.");
	// }
	//
	// int curyear = configService.getLastYear();
	// result.put("facility", facility);
	//
	// List<ProduceStep> fSteps = stepService.getByFacility(fid);
	// if (fSteps != null) {
	// result.put("steps", fSteps);
	// }
	//
	// List<Devices> fDevices = deviceService.getByFacility(fid);
	// if (fDevices != null) {
	// result.put("devices", fDevices);
	// }
	//
	// List<CFacilityFill> flist = faciFillService.getByFacility(fid);
	// if (flist == null) {
	// CFacilityFill faciFill = new CFacilityFill();
	// faciFill.setFillyear(curyear);
	// faciFill.setFacilityId(fid);
	// faciFillService.add(faciFill);
	// flist = faciFillService.getByFacility(fid);
	// }
	//
	// result.put("fills", flist);
	//
	// } catch(Exception e) {
	// e.printStackTrace();
	// return result.setStatus(-2, "exception");
	// }
	//
	// return result.setStatus(0, "");
	// }

	@RequestMapping(value = "/fill", method = RequestMethod.POST)
	@ResponseBody
	public Object fill(HttpServletRequest request) {

		try {
			CFacilityFill faciFill = check_fill(request);
			if (faciFill.getFacilityId() <= 0)
				return result.setStatus(-4, "no facility id.");

			int companyid;
			if (loginManage.isCompanyLogin(request)) {
				Company c = loginManage.getLoginCompany(request);
				companyid = c.getId();
			} else if (loginManage.isUserLogin(request)) {
				if (this.companyid <= 0)
					return result.setStatus(-2, "no company id.");
				companyid = this.companyid;
			} else {
				return result.setStatus(-1, "No login.");
			}

			faciFill.setFillyear(faciFill.getFillyear());

			Calendar cal = Calendar.getInstance();
			faciFill.setFillTime(Tools.ToDateStr(cal));
			if (faciFill.getStatus() < 1)
				faciFill.setStatus(WorkFlowController.STATUS_FILL);

			List<CFacilityFill> flist = faciFillService.getByFacility(
					faciFill.getFacilityId(), faciFill.getFillyear());
			if (flist == null) {
				faciFillService.add(faciFill);
			} else {
				CFacilityFill hasFound = null;
				for (CFacilityFill f : flist) {
					if (f.getFillyear() == faciFill.getFillyear()) {
						hasFound = f;
						break;
					}
				}

				if (hasFound != null) {
					faciFill.setId(hasFound.getId());
					faciFillService.update(faciFill);
				} else
					faciFillService.add(faciFill);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "unpass", method = RequestMethod.POST)
	@ResponseBody
	public Object unpass(HttpServletRequest request) {
		String id = request.getParameter("id");

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		if (id != null && id != "") {
			int Id = Integer.parseInt(id);

			faciFillService.unpass(1, Id, 2);

			return result.setStatus(0, "");
		} else {
			return result.setStatus(-2, "无企业id");
		}

	}

	@RequestMapping(value = "/cfdel/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object delfacility(@PathVariable int id, HttpServletRequest request) {
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			ControlFacility cf = facilityService.getfacById(id);
			if (cf == null)
				return result.setStatus(-2, "无此记录!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(-2, "无此记录!");
		}

		facilityService.delete(id);
		return result.setStatus(0, "ok");
	}
}
