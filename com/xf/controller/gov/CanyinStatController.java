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
import com.xf.entity.gov.CanyinStat;
import com.xf.service.gov.CanyinStatService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/canyin/stat")
public class CanyinStatController {

	@Autowired
	private CanyinStatService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private CanyinStat check_input(HttpServletRequest request) {
		CanyinStat ret = new CanyinStat();

		String s = new String();
		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			ret.setId(Integer.parseInt(s));
		s = request.getParameter("accountid");
		if (s != null && Tools.isInteger(s))
			ret.setAccountid(Integer.parseInt(s));
		ret.setFillTime(request.getParameter("fillTime"));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			ret.setFillyear(Integer.parseInt(s));
		s = request.getParameter("certified");
		if (s != null && Tools.isInteger(s))
			ret.setCertified(Integer.parseInt(s));
		s = request.getParameter("province");
		if (s != null && Tools.isInteger(s))
			ret.setProvince(Integer.parseInt(s));
		s = request.getParameter("city");
		if (s != null && Tools.isInteger(s))
			ret.setCity(Integer.parseInt(s));
		s = request.getParameter("town");
		if (s != null && Tools.isInteger(s))
			ret.setTown(Integer.parseInt(s));
		s = request.getParameter("canguan_huge");
		if (s != null && Tools.isInteger(s))
			ret.setCanguan_huge(Integer.parseInt(s));
		s = request.getParameter("canguan_big");
		if (s != null && Tools.isInteger(s))
			ret.setCanguan_big(Integer.parseInt(s));
		s = request.getParameter("canguan_mid");
		if (s != null && Tools.isInteger(s))
			ret.setCanguan_mid(Integer.parseInt(s));
		s = request.getParameter("canguan_small");
		if (s != null && Tools.isInteger(s))
			ret.setCanguan_small(Integer.parseInt(s));
		s = request.getParameter("snack");
		if (s != null && Tools.isInteger(s))
			ret.setSnack(Integer.parseInt(s));
		s = request.getParameter("fastfood");
		if (s != null && Tools.isInteger(s))
			ret.setFastfood(Integer.parseInt(s));
		s = request.getParameter("milk");
		if (s != null && Tools.isInteger(s))
			ret.setMilk(Integer.parseInt(s));
		s = request.getParameter("drink");
		if (s != null && Tools.isInteger(s))
			ret.setDrink(Integer.parseInt(s));
		s = request.getParameter("shitang_shiye");
		if (s != null && Tools.isInteger(s))
			ret.setShitang_shiye(Integer.parseInt(s));
		s = request.getParameter("shitang_school");
		if (s != null && Tools.isInteger(s))
			ret.setShitang_school(Integer.parseInt(s));
		s = request.getParameter("shitang_gongdi");
		if (s != null && Tools.isInteger(s))
			ret.setShitang_gongdi(Integer.parseInt(s));
		int total;
		total = ret.getCanguan_huge() + ret.getCanguan_big()
				+ ret.getCanguan_mid() + ret.getCanguan_small()
				+ ret.getSnack() + ret.getFastfood() + ret.getMilk()
				+ ret.getDrink() + ret.getShitang_shiye()
				+ ret.getShitang_gongdi() + ret.getShitang_school();
		if (total > 0)
			ret.setTotal(total);
		s = request.getParameter("incoming");
		if (s != null && Tools.isNumeric(s))
			ret.setIncoming(Double.parseDouble(s));

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

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			CanyinStat input = check_input(request);

			input.setAccountid(companyid);
			CanyinStat search = theService.getByField(input);
			if (search == null)
				return result.setStatus(-3, "no data.");
			if (search != null && search.getId() > 0)
				result.put("id", search.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			CanyinStat search = theService.getById(id);
			if (null == search)
				return result.setStatus(-3, "No data.");

			result.put("data", search);

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

			CanyinStat input = check_input(request);

			input.setAccountid(companyid);

			CanyinStat search = null;
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

	@RequestMapping(value = "/get/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByYear(@PathVariable int year, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			CanyinStat input = new CanyinStat();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<CanyinStat> list = theService.getByYear(input);
			if (list != null)
				result.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/{cert}/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(@PathVariable int cert, @PathVariable int year,
			HttpServletRequest request) {

		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			CanyinStat cs = new CanyinStat();
			cs.setFillyear(year);
			cs.setCertified(cert);
			cs.setAccountid(companyid);
			List<CanyinStat> cList = theService.getFillTime(cs);

			result.put("data", cList);

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

			CanyinStat input = new CanyinStat();
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
