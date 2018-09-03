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
import com.xf.entity.gov.AnimalsFarm;
import com.xf.service.gov.AnimalsFarmService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/animals/farm")
public class AnimalsFarmController {

	@Autowired
	private AnimalsFarmService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private AnimalsFarm check_input(HttpServletRequest request) {
		AnimalsFarm ret = new AnimalsFarm();

		String s = new String();
		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s)) ret.setId(Integer.parseInt(s));
		s = request.getParameter("accountid");
		if (s != null && Tools.isInteger(s)) ret.setAccountid(Integer.parseInt(s));
		ret.setFillTime(request.getParameter("fillTime"));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s)) ret.setFillyear(Integer.parseInt(s));
		ret.setFarmname(request.getParameter("farmname"));
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
		ret.setMethod(request.getParameter("method"));
		s = request.getParameter("beef");
		if (s != null && Tools.isInteger(s)) ret.setBeef(Integer.parseInt(s));
		s = request.getParameter("beefcycle");
		if (s != null && Tools.isInteger(s)) ret.setBeefcycle(Integer.parseInt(s));
		s = request.getParameter("cow");
		if (s != null && Tools.isInteger(s)) ret.setCow(Integer.parseInt(s));
		s = request.getParameter("cowcycle");
		if (s != null && Tools.isInteger(s)) ret.setCowcycle(Integer.parseInt(s));
		s = request.getParameter("goat");
		if (s != null && Tools.isInteger(s)) ret.setGoat(Integer.parseInt(s));
		s = request.getParameter("goatcycle");
		if (s != null && Tools.isInteger(s)) ret.setGoatcycle(Integer.parseInt(s));
		s = request.getParameter("sheep");
		if (s != null && Tools.isInteger(s)) ret.setSheep(Integer.parseInt(s));
		s = request.getParameter("sheepcycle");
		if (s != null && Tools.isInteger(s)) ret.setSheepcycle(Integer.parseInt(s));
		s = request.getParameter("pig");
		if (s != null && Tools.isInteger(s)) ret.setPig(Integer.parseInt(s));
		s = request.getParameter("pigcycle");
		if (s != null && Tools.isInteger(s)) ret.setPigcycle(Integer.parseInt(s));
		s = request.getParameter("chicken");
		if (s != null && Tools.isInteger(s)) ret.setChicken(Integer.parseInt(s));
		s = request.getParameter("chickencycle");
		if (s != null && Tools.isInteger(s)) ret.setChickencycle(Integer.parseInt(s));
		s = request.getParameter("duck");
		if (s != null && Tools.isInteger(s)) ret.setDuck(Integer.parseInt(s));
		s = request.getParameter("duckcycle");
		if (s != null && Tools.isInteger(s)) ret.setDuckcycle(Integer.parseInt(s));
		s = request.getParameter("goose");
		if (s != null && Tools.isInteger(s)) ret.setGoose(Integer.parseInt(s));
		s = request.getParameter("goosecycle");
		if (s != null && Tools.isInteger(s)) ret.setGoosecycle(Integer.parseInt(s));
		s = request.getParameter("hen");
		if (s != null && Tools.isInteger(s)) ret.setHen(Integer.parseInt(s));
		s = request.getParameter("layingduck");
		if (s != null && Tools.isInteger(s)) ret.setLayingduck(Integer.parseInt(s));
		s = request.getParameter("layinggoose");
		if (s != null && Tools.isInteger(s)) ret.setLayinggoose(Integer.parseInt(s));
		s = request.getParameter("sow");
		if (s != null && Tools.isInteger(s)) ret.setSow(Integer.parseInt(s));
		s = request.getParameter("rabbit");
		if (s != null && Tools.isInteger(s)) ret.setRabbit(Integer.parseInt(s));
		s = request.getParameter("horse");
		if (s != null && Tools.isInteger(s)) ret.setHorse(Integer.parseInt(s));

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

			AnimalsFarm input = check_input(request);
			if (input.getFarmname() == null)
				return result.setStatus(-3, "no data.");

			input.setAccountid(companyid);
			AnimalsFarm search = theService.getByField(input);
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

			AnimalsFarm input = check_input(request);
			
			input.setAccountid(companyid);

			AnimalsFarm search = null;
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

			AnimalsFarm search = theService.getById(id);
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
			AnimalsFarm input = new AnimalsFarm();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<AnimalsFarm> list = theService.getByYear(input);
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
			AnimalsFarm input = new AnimalsFarm();
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
