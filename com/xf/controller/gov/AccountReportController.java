package com.xf.controller.gov;

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
import com.xf.service.ConfigService;
import com.xf.entity.gov.AccountReport2;
import com.xf.service.gov.AccountReportService2;

@Scope("prototype")
@Controller
@RequestMapping(value = "/accountreport")
public class AccountReportController {

	@Autowired
	private AccountReportService2 theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private AccountReport2 check_input(HttpServletRequest request) {
		AccountReport2 ret = new AccountReport2();

		String s = new String();
		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s)) ret.setId(Integer.parseInt(s));
		s = request.getParameter("typeid");
		if (s != null && Tools.isInteger(s)) ret.setTypeid(Integer.parseInt(s));
		ret.setAddress(request.getParameter("address"));
		ret.setReportname(request.getParameter("reportname"));
		ret.setReportdesp(request.getParameter("reportdesp"));
		s = request.getParameter("isimport");
		if (s != null && Tools.isInteger(s)) ret.setIsimport(Integer.parseInt(s));

		return ret;
	}

	// ==============================================================================

	int companyid;

	private HashMap<String, Object> check_account(HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (companyid <= 0)
				return result.setStatus(-2, "no company id.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}


	@RequestMapping(value = "/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int typeid, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			List<AccountReport2> list = theService.getByTypeId(typeid);
			if (list.size() < 1)
				return result.setStatus(-3, "No data.");

			result.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}


}
