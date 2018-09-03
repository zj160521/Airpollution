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
import com.xf.entity.Company;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.entity.gov.DumpField;
import com.xf.service.gov.DumpFieldService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/dump/field")
public class DumpFieldController {

	@Autowired
	private DumpFieldService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private DumpField check_input(HttpServletRequest request) {
		DumpField ret = new DumpField();

		String s = new String();
		s = request.getParameter("id");
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
		ret.setCountry(request.getParameter("country"));
		ret.setStreet(request.getParameter("street"));
		ret.setFactoryname(request.getParameter("factoryname"));
		ret.setFactorytype(request.getParameter("factorytype"));
		s = request.getParameter("rubbish_burn");
		if (s != null && Tools.isNumeric(s)) ret.setRubbish_burn(Double.parseDouble(s));
		s = request.getParameter("rubbish_bury");
		if (s != null && Tools.isNumeric(s)) ret.setRubbish_bury(Double.parseDouble(s));
		s = request.getParameter("rubbish_hill");
		if (s != null && Tools.isNumeric(s)) ret.setRubbish_hill(Double.parseDouble(s));
		s = request.getParameter("rubbish_capability");
		if (s != null && Tools.isNumeric(s)) ret.setRubbish_capability(Double.parseDouble(s));
		s = request.getParameter("rubbish_used");
		if (s != null && Tools.isNumeric(s)) ret.setRubbish_used(Double.parseDouble(s));
		s = request.getParameter("sewerage_total");
		if (s != null && Tools.isNumeric(s)) ret.setSewerage_total(Double.parseDouble(s));

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

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			DumpField input = check_input(request);
			if (input.getTown() <= 0)
				return result.setStatus(-3, "no data.");

			DumpField search = theService.getByField(input);
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

			DumpField input = check_input(request);
			
			input.setAccountid(companyid);

			DumpField search = null;
			if (input.getId() > 0) {
				search = theService.getById(input.getId());
			}

			Calendar cal = Calendar.getInstance();
			input.setFillTime(Tools.ToDateStr(cal));

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

			DumpField search = theService.getById(id);
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

			DumpField input = new DumpField();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<DumpField> list = theService.getByYear(input);
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

			DumpField input = new DumpField();
			input.setFillyear(year);
			input.setAccountid(companyid);
			theService.clearData(input);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
}
