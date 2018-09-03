package com.xf.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Company;
import com.xf.entity.Elec;
import com.xf.entity.ElecFill;
import com.xf.entity.Static;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.ConfigService;
import com.xf.service.ElecFillService;
import com.xf.service.ElecService;
import com.xf.service.StaticService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/elec")
public class ElecController {

	@Autowired
	private ElecService elecService;
	@Autowired
	private ElecFillService elecfillService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private int companyid = 0;
	private boolean flag = false;

	private ElecFill check(HttpServletRequest request) {

		String s = new String();
		ElecFill elecfill = new ElecFill();

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			elecfill.setId(Integer.parseInt(s));
		s = request.getParameter("elecDeviceId");
		if (s != null && Tools.isInteger(s))
			elecfill.setElecDeviceId(Integer.parseInt(s));
		s = request.getParameter("elecId");
		if (s != null && Tools.isInteger(s))
			elecfill.setElecId(Integer.parseInt(s));
		s = request.getParameter("companyid");
		if (s != null && Tools.isInteger(s))
			companyid = Integer.parseInt(s);

		s = request.getParameter("elecPerDay");
		if (s != null && Tools.isNumeric(s))
			elecfill.setElecPerDay(Double.parseDouble(s));
		s = request.getParameter("elecPerYear");
		if (s != null && Tools.isNumeric(s))
			elecfill.setElecPerYear(Double.parseDouble(s));

		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			elecfill.setStatus(Integer.parseInt(s));
		return elecfill;
	}

	private Object check_company(HttpServletRequest request) {

		String s = new String();
		s = request.getParameter("companyid");
		if (s != null && Tools.isInteger(s))
			companyid = Integer.parseInt(s);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			flag = true;
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	@RequestMapping(value = "/years", method = RequestMethod.GET)
	@ResponseBody
	public Object getYears(HttpServletRequest request) {
		try {

			List<Integer> fillyear = elecService.getYears();

			result.put("data", fillyear);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/fill/{fillyear}", method = RequestMethod.POST)
	@ResponseBody
	public Object fillElec(HttpServletRequest request,@PathVariable int fillyear) {

		try {
			Object ret = check_company(request);
			if (ret != null)
				return ret;

			ElecFill efill = check(request);
			
			if(efill.getId()>0){
				elecfillService.updateForUser(efill);
				return result.setStatus(0, "ok");
			}

			if (efill.getElecDeviceId() <= 0)
				return result.setStatus(-3, "must have device id.");

			if (flag == true) {
				elecfillService.updateForUser(efill);
			} else {
				ElecFill efill1 = elecfillService.getef(companyid,
						efill.getElecDeviceId(),fillyear);
				if (efill1 != null && efill1.getStatus() > 1) {
					return result.setStatus(-2, "不能修改已经提交的设备信息");
				}

				List<Elec> elecList = elecService.getByCompany(companyid);

				Elec curElec = null;
				for (Elec e : elecList) {
					if (e.getFillyear() == fillyear) {
						curElec = e;
						break;
					}
				}

				if (curElec == null) {
					curElec = new Elec();
					setElec(fillyear, curElec);
					curElec.setStatus(WorkFlowController.STATUS_FILL);
					elecService.add(curElec);
				} else {
					setElec(fillyear, curElec);
					if (curElec.getStatus() < 1)
						curElec.setStatus(WorkFlowController.STATUS_FILL);
					elecService.update(curElec);
				}

				efill.setElecId(curElec.getId());

				boolean hasFill = false;
				List<ElecFill> efillList = elecfillService.getByElec(curElec
						.getId());
				if (efillList != null) {
					for (ElecFill ef : efillList) {
						if (ef.getElecDeviceId() == efill.getElecDeviceId()) {
							efill.setId(ef.getId());
							elecfillService.update(efill);
							hasFill = true;
							break;
						}
					}
				}

				elecService.update(curElec);

				if (hasFill == false) {
					efill.setElecId(curElec.getId());
					elecfillService.add(efill);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	private void setElec(int curyear, Elec curElec) {
		curElec.setEnterpriceId(companyid);
		Calendar cal = Calendar.getInstance();
		curElec.setFillTime(Tools.ToDateStr(cal));
		curElec.setFillyear(curyear);
	}

	private Object getElec(int companyid, int curyear) {

		List<Elec> elecList = elecService.getByCompany(companyid);
		List<HashMap<String, Object>> elist = new ArrayList<HashMap<String, Object>>();

		boolean flag=false;
		for (Elec e : elecList) {
			if(curyear > 0){
				if(curyear == e.getFillyear()){
					flag=true;
				}
			}
		}
		//为true就是有填报数据,为false为没有数据
		if(flag==false){
			HashMap<String, Object> elec = new HashMap<String, Object>();

			elec.put("id", 0);
			elec.put("enterpriceId", companyid);
			elec.put("fillTime", curyear);
			elec.put("fillyear", curyear);
			elec.put("yearTotal", 0);
			elec.put("status", 0);

			List<ElecFill> efillList = new ArrayList<ElecFill>();
			if (efillList != null) {
				elec.put("devices", efillList);
			}
			elist.add(elec);
		}else{
			for (Elec e : elecList) {

				if (curyear > 0) {
					if (curyear != e.getFillyear()){			
						continue;
					}
				}

				HashMap<String, Object> elec = new HashMap<String, Object>();

				elec.put("id", e.getId());
				elec.put("enterpriceId", e.getEnterpriceId());
				elec.put("fillTime", e.getFillTime());
				elec.put("fillyear", e.getFillyear());
				elec.put("yearTotal", e.getYearTotal());
				elec.put("status", e.getStatus());

				List<ElecFill> efillList = elecfillService.getByElec(e.getId());
				if (efillList != null) {
					elec.put("devices", efillList);
				}
				elist.add(elec);
			}
		}
		
		

		return elist;
	}

	@RequestMapping(value = "/company/{id}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByCompanyId(@PathVariable int id,@PathVariable int fillyear,HttpServletRequest request) {
		try {

			companyid = id;
			Object ret = check_company(request);
			if (ret != null)
				return ret;

			ret = getElec(id, fillyear);
			result.put("fills", ret);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCuryear(HttpServletRequest request,@PathVariable int fillyear) {

		try {

			Object ret = check_company(request);
			if (ret != null)
				return ret;

			ret = getElec(companyid, fillyear);
			result.put("fills", ret);
			result.put("curyear", fillyear);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

//	@RequestMapping(value = "/get/history", method = RequestMethod.GET)
//	@ResponseBody
//	public Object getHistory(HttpServletRequest request) {
//
//		try {
//
//			Object ret = check_company(request);
//			if (ret != null)
//				return ret;
//
//			ret = getElec(companyid, 0);
//			result.put("fills", ret);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return result.setStatus(-2, "exception");
//		}
//
//		return result.setStatus(0, "");
//	}

	@RequestMapping(value = "/get/devices", method = RequestMethod.GET)
	@ResponseBody
	public Object getDevices(HttpServletRequest request) {

		try {

			Static s = new Static();
			s.setGroupid(10);
			s.setPid(0);
			List<Static> slist = staticService.getGroupPid(s);
			result.put("devices", slist);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");

	}

	@RequestMapping(value = "/add/devices/{dname}", method = RequestMethod.POST)
	@ResponseBody
	public Object getDevices(@PathVariable String dname,
			HttpServletRequest request) {

		try {

			int maxid = staticService.getMaxId(10);
			Static s = staticService.getById(6001);
			s.setId(maxid);
			s.setName(dname);

			staticService.add(s);
			result.put("lastid", s.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable int id, HttpServletRequest request) {
		
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
		}
		else if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		
		ElecFill elf=elecfillService.getById(id);
		if(elf.getId() >0){
			elecfillService.delete(elf);
			Elec el=elecService.getById(elf.getElecId());
			el.setYearTotal(el.getYearTotal()-elf.getElecPerYear());
			elecService.update(el);
			return result.setStatus(0, "ok");
		}else {
			return result.setStatus(-2, "无该记录！");
		}
		
	}

	@RequestMapping(value = "/checked", method = RequestMethod.POST)
	@ResponseBody
	public Object checked(HttpServletRequest request) {

		String enterpriceid = request.getParameter("enterpriceId");
		String type = request.getParameter("type");
		String fillyear = request.getParameter("fillyear");

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		int typ = Integer.parseInt(type);
		int enterpriceId = Integer.parseInt(enterpriceid);
		int curyear=Integer.parseInt(fillyear);
		
		try {
			List<Elec> ele = elecService.getByCompany(enterpriceId);
			if (typ == 0) {
				for (Elec elec : ele) {
					if (curyear == elec.getFillyear()) {
						elecService.setstatus3(2, enterpriceId, 1, curyear);
						elecfillService.setstatus2(2, enterpriceId, 1);
						elecService.setstatus3(3, enterpriceId, 2, curyear);
						elecfillService.setstatus2(3, enterpriceId, 2);
						return result.setStatus(0, "");
					}
				}
			} else if (typ == 1) {
				for (Elec elec : ele) {
					if (curyear == elec.getFillyear()) {
						elecService.setstatus3(2, enterpriceId, 3, curyear);
						elecfillService.setstatus2(2, enterpriceId, 3);
						elecService.setstatus3(1, enterpriceId, 2, curyear);
						elecfillService.setstatus2(1, enterpriceId, 2);
						return result.setStatus(0, "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "无该用电记录");
		}
		return result.setStatus(-2, "typeid值错误");
	}
	
	
}
