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
import com.xf.service.ConfigService;
import com.xf.entity.gov.Gasstation;
import com.xf.service.gov.GasstationService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/gasstation")
public class GasstationController {

	@Autowired
	private GasstationService theService;

	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private Gasstation check_input(HttpServletRequest request) {
		Gasstation ret = new Gasstation();

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
		ret.setGasStationName(request.getParameter("gasStationName"));
		s = request.getParameter("gasolineGross");
		if (s != null && Tools.isNumeric(s)) ret.setGasolineGross(Double.parseDouble(s));
		s = request.getParameter("dieselGross");
		if (s != null && Tools.isNumeric(s)) ret.setDieselGross(Double.parseDouble(s));
		s = request.getParameter("natgasGross");
		if (s != null && Tools.isNumeric(s)) ret.setNatgasGross(Double.parseDouble(s));
		s = request.getParameter("gasolineSellMonth");
		if (s != null && Tools.isNumeric(s)) ret.setGasolineSellMonth(Double.parseDouble(s));
		s = request.getParameter("dieselSellMonth");
		if (s != null && Tools.isNumeric(s)) ret.setDieselSellMonth(Double.parseDouble(s));
		s = request.getParameter("natgasSellMonth");
		if (s != null && Tools.isNumeric(s)) ret.setNatgasSellMonth(Double.parseDouble(s));
		s = request.getParameter("gasolineSpear");
		if (s != null && Tools.isInteger(s)) ret.setGasolineSpear(Integer.parseInt(s));
		s = request.getParameter("dieselSpear");
		if (s != null && Tools.isInteger(s)) ret.setDieselSpear(Integer.parseInt(s));
		s = request.getParameter("natgasSpear");
		if (s != null && Tools.isInteger(s)) ret.setNatgasSpear(Integer.parseInt(s));
		s = request.getParameter("recycleDevice");
		if (s != null && Tools.isInteger(s)) ret.setRecycleDevice(Integer.parseInt(s));
		s = request.getParameter("recovery");
		if (s != null && Tools.isNumeric(s)) ret.setRecovery(Double.parseDouble(s));

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

	@RequestMapping(value = "/years", method = RequestMethod.GET)
	@ResponseBody
	public Object getYears(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			List<Integer> fillyear = theService.getYears(companyid);
			if (fillyear.isEmpty()) {
				int curyear = configService.getLastYear();
				fillyear.add(curyear);
			}
			result.put("data", fillyear);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			int curyear = configService.getLastYear();
			Gasstation input = check_input(request);
			if (input.getGasStationName() == null)
				return result.setStatus(-3, "no data.");
			
			input.setAccountid(companyid);
			input.setFillyear(curyear);
			Gasstation search = theService.getByField(input);
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

			Gasstation input = check_input(request);
			
			input.setAccountid(companyid);

			Gasstation search = null;
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
				int curyear = configService.getLastYear();
				input.setFillyear(curyear);
				
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

			Gasstation search = theService.getById(id);
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

			Gasstation input = new Gasstation();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<Gasstation> list = theService.getByYear(input);
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

			Gasstation input = new Gasstation();
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
