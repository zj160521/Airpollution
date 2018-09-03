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
import com.xf.entity.gov.ConstructionDust;
import com.xf.entity.gov.CountyArea;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.ConfigService;
import com.xf.service.gov.ConstructionDustService;
import com.xf.service.gov.CountyAreaService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/county/area")
public class CountyAreaController {

	@Autowired
	private CountyAreaService theService;
	@Autowired
	private ConstructionDustService cdService;

	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private CountyArea check_input(HttpServletRequest request) {
		CountyArea ret = new CountyArea();

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
		s = request.getParameter("countyConstructNumber");
		if (s != null && Tools.isInteger(s)) ret.setCountyConstructNumber(Integer.parseInt(s));
		s = request.getParameter("countyConstructArea");
		if (s != null && Tools.isNumeric(s)) ret.setCountyConstructArea(Double.parseDouble(s));

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

			CountyArea input = check_input(request);
			if (input.getTown() <=0)
				return result.setStatus(-3, "no data.");
			
			input.setAccountid(companyid);
			List<CountyArea> search = theService.getByTown(input);
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

			CountyArea input = check_input(request);
			
			input.setAccountid(companyid);

			CountyArea search = null;
			if (input.getId() > 0) {
				search = theService.getById(input.getId());
			}

			Calendar cal = Calendar.getInstance();
			input.setFillTime(Tools.ToDateStr(cal));

			// get construction dust id
			Company c = loginManage.getLoginCompany(request);
			int curyear = configService.getLastYear();
			ConstructionDust cd = new ConstructionDust();
			
			cd.setAccountid(c.getId());
			cd.setFillyear(curyear);
			cd.setCity(c.getCity());
			
			cd = cdService.getByField(cd);
			if (cd != null)
				input.setConsDustId(cd.getId());
			if (input.getStatus() < 1)
				input.setStatus(WorkFlowController.STATUS_FILL);
			if (search != null && search.getId() > 0) {
				theService.update(input);
			} else {
				input.setFillyear(curyear);
				input.setProvince(c.getProvince());
				input.setCity(c.getCity());
				
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

			CountyArea search = theService.getById(id);
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

			CountyArea input = new CountyArea();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<CountyArea> list = theService.getByYear(input);
			if (list != null)
				result.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

}
