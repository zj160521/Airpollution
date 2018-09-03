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
import com.xf.entity.gov.Nfertilizer;
import com.xf.service.gov.NfertilizerService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/nfertilizer")
public class NfertilizerController extends BaseController {

	@Autowired
	private NfertilizerService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private Nfertilizer check_input(HttpServletRequest request) {
		Nfertilizer ret = new Nfertilizer();

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
		s = request.getParameter("layingType");
		if (s != null && Tools.isInteger(s)) ret.setLayingType(Integer.parseInt(s));
		s = request.getParameter("ferType");
		if (s != null && Tools.isInteger(s)) ret.setFerType(Integer.parseInt(s));
		s = request.getParameter("amountTotal");
		if (s != null && Tools.isNumeric(s)) ret.setAmountTotal(Double.parseDouble(s));
		s = request.getParameter("amountMu");
		if (s != null && Tools.isNumeric(s)) ret.setAmountMu(Double.parseDouble(s));
		s = request.getParameter("m1");
		if (s != null && Tools.isNumeric(s)) ret.setM1(Double.parseDouble(s));
		s = request.getParameter("m2");
		if (s != null && Tools.isNumeric(s)) ret.setM2(Double.parseDouble(s));
		s = request.getParameter("m3");
		if (s != null && Tools.isNumeric(s)) ret.setM3(Double.parseDouble(s));
		s = request.getParameter("m4");
		if (s != null && Tools.isNumeric(s)) ret.setM4(Double.parseDouble(s));
		s = request.getParameter("m5");
		if (s != null && Tools.isNumeric(s)) ret.setM5(Double.parseDouble(s));
		s = request.getParameter("m6");
		if (s != null && Tools.isNumeric(s)) ret.setM6(Double.parseDouble(s));
		s = request.getParameter("m7");
		if (s != null && Tools.isNumeric(s)) ret.setM7(Double.parseDouble(s));
		s = request.getParameter("m8");
		if (s != null && Tools.isNumeric(s)) ret.setM8(Double.parseDouble(s));
		s = request.getParameter("m9");
		if (s != null && Tools.isNumeric(s)) ret.setM9(Double.parseDouble(s));
		s = request.getParameter("m10");
		if (s != null && Tools.isNumeric(s)) ret.setM10(Double.parseDouble(s));
		s = request.getParameter("m11");
		if (s != null && Tools.isNumeric(s)) ret.setM11(Double.parseDouble(s));
		s = request.getParameter("m12");
		if (s != null && Tools.isNumeric(s)) ret.setM12(Double.parseDouble(s));
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

			Nfertilizer input = check_input(request);
			if (input.getFerType()<=0 || input.getLayingType()<=0)
				return result.setStatus(-3, "no data.");

			input.setAccountid(companyid);
			
			Nfertilizer search = theService.getByContion(input);
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

			Nfertilizer input = check_input(request);
			
			input.setAccountid(companyid);

			Nfertilizer search = null;
			if (input.getId() > 0) {
				search = theService.getById(input.getId());
			}

			Calendar cal = Calendar.getInstance();
			input.setFillTime(Tools.ToDateStr(cal));
			
			//给total赋值
			Double total=(double) 0;
			if(input.getId() > 0){
				Nfertilizer d= theService.getById(input.getId());
				
				if(input.getM1() != null){
					total+=input.getM1();
				} else if(d.getM1() != null){
					total+=d.getM1();
				}
				
				if(input.getM2() != null){
					total+=input.getM2();
				} else if(d.getM2() != null){
					total+=d.getM2();
				}
				
				if(input.getM3() != null){
					total+=input.getM3();
				} else if(d.getM3() != null){
					total+=d.getM3();
				}
				
				if(input.getM4() != null){
					total+=input.getM4();
				} else if(d.getM4() != null){
					total+=d.getM4();
				}
				if(input.getM5() != null){
					total+=input.getM5();
				} else if(d.getM5() != null){
					total+=d.getM5();
				}
				
				if(input.getM6() != null){
					total+=input.getM6();
				} else if(d.getM6() != null){
					total+=d.getM6();
				}
				
				if(input.getM7() != null){
					total+=input.getM7();
				} else if(d.getM7() != null){
					total+=d.getM7();
				}
				
				if(input.getM8() != null){
					total+=input.getM8();
				} else if(d.getM8() != null){
					total+=d.getM8();
				}
				
				if(input.getM9() != null){
					total+=input.getM9();
				} else if(d.getM9() != null){
					total+=d.getM9();
				}
				
				if(input.getM10() != null){
					total+=input.getM10();
				} else if(d.getM10() != null){
					total+=d.getM10();
				}
				
				if(input.getM11() != null){
					total+=input.getM11();
				} else if(d.getM11() != null){
					total+=d.getM11();
				}
				
				if(input.getM12() != null){
					total+=input.getM12();
				} else if(d.getM12() != null){
					total+=d.getM12();
				}
				
				input.setAmountTotal(total);
			}else{
				if(input.getM1() != null) total+=input.getM1();
				if(input.getM2() != null) total+=input.getM2();
				if(input.getM3() != null) total+=input.getM3();
				if(input.getM4() != null) total+=input.getM4();
				if(input.getM5() != null) total+=input.getM5();
				if(input.getM6() != null) total+=input.getM6();
				if(input.getM7() != null) total+=input.getM7();
				if(input.getM8() != null) total+=input.getM8();
				if(input.getM9() != null) total+=input.getM9();
				if(input.getM10() != null) total+=input.getM10();
				if(input.getM11() != null) total+=input.getM11();
				if(input.getM12() != null) total+=input.getM12();
				
				input.setAmountTotal(total);
				
			}
//			String s = request.getParameter("amountTotal");
//			if (s != null && Tools.isNumeric(s)) {
//				if(Double.parseDouble(s)>0){
//					input.setAmountTotal(Double.parseDouble(s));
//				}
//			}
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

			Nfertilizer search = theService.getById(id);
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

			Nfertilizer input = new Nfertilizer();
			input.setFillyear(year);
			input.setAccountid(companyid);
			List<Nfertilizer> list = theService.getByYear(input);
			if (list != null)
				result.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/get/{ferType}/{layingType}/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(@PathVariable int ferType,@PathVariable int layingType, @PathVariable int year, HttpServletRequest request) {

		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null) return ret;
		
			Nfertilizer cs = new Nfertilizer();
			cs.setFillyear(year);
			cs.setFerType(ferType);
			cs.setLayingType(layingType);
			cs.setAccountid(companyid);
			List<Nfertilizer> cList = theService.getByFerType(cs);

			result.put("data", cList);
			
		} catch(Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		
		return result.setStatus(0, "");
		}
	
	public void setService(){
		super.theService = theService;
	}
}
