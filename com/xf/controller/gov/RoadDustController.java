package com.xf.controller.gov;

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

import com.xf.controller.ResultObj;
import com.xf.controller.WorkFlowController;
import com.xf.entity.Company;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.entity.gov.RoadDust;
import com.xf.service.gov.RoadDustService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/road/dust")
public class RoadDustController extends BaseController {

	@Autowired
	private RoadDustService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private RoadDust check_input(HttpServletRequest request) {
		RoadDust ret = new RoadDust();

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
		s = request.getParameter("ksPitch");
		if (s != null && Tools.isInteger(s)) ret.setKsPitch(Integer.parseInt(s));
		s = request.getParameter("ksCement");
		if (s != null && Tools.isInteger(s)) ret.setKsCement(Integer.parseInt(s));
		s = request.getParameter("ksNotShop");
		if (s != null && Tools.isInteger(s)) ret.setKsNotShop(Integer.parseInt(s));
		s = request.getParameter("zgPitch");
		if (s != null && Tools.isInteger(s)) ret.setZgPitch(Integer.parseInt(s));
		s = request.getParameter("zgCement");
		if (s != null && Tools.isInteger(s)) ret.setZgCement(Integer.parseInt(s));
		s = request.getParameter("zgNotShop");
		if (s != null && Tools.isInteger(s)) ret.setZgNotShop(Integer.parseInt(s));
		s = request.getParameter("cgPitch");
		if (s != null && Tools.isInteger(s)) ret.setCgPitch(Integer.parseInt(s));
		s = request.getParameter("cgCement");
		if (s != null && Tools.isInteger(s)) ret.setCgCement(Integer.parseInt(s));
		s = request.getParameter("cgNotShop");
		if (s != null && Tools.isInteger(s)) ret.setCgNotShop(Integer.parseInt(s));
		s = request.getParameter("zPitch");
		if (s != null && Tools.isInteger(s)) ret.setzPitch(Integer.parseInt(s));
		s = request.getParameter("zCement");
		if (s != null && Tools.isInteger(s)) ret.setzCement(Integer.parseInt(s));
		s = request.getParameter("zNotShop");
		if (s != null && Tools.isInteger(s)) ret.setzNotShop(Integer.parseInt(s));
		s = request.getParameter("rainDays");
		if (s != null && Tools.isInteger(s)) ret.setRainDays(Integer.parseInt(s));
		s = request.getParameter("controlType");
		if (s != null && Tools.isInteger(s)) ret.setControlType(Integer.parseInt(s));
		ret.setDepartment(request.getParameter("department"));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s)) ret.setStatus(Integer.parseInt(s));

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


	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			RoadDust input = check_input(request);
			if (input.getTown() <=0)
				return result.setStatus(-3, "no data.");
			
			input.setAccountid(companyid);
			List<RoadDust> search = theService.getByTown(input);
			if (search != null && !search.isEmpty())
				result.put("id", search.get(0).getId());

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

			RoadDust input = check_input(request);
			
			input.setAccountid(companyid);

			RoadDust search = null;
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

			RoadDust search = theService.getById(id);
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

			RoadDust input = new RoadDust();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<RoadDust> list = theService.getByYear(input);
			if (list != null)
				result.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	public void setService(){
		super.theService = theService;
	}
}
