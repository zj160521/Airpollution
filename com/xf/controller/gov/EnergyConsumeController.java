package com.xf.controller.gov;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xf.service.DistrictService;
import com.xf.entity.gov.EnergyConsume;
import com.xf.service.gov.EnergyConsumeService;
import com.xf.vo.EnergyConsumeVo;

@Scope("prototype")
@Controller
@RequestMapping(value = "/energy/consume")
public class EnergyConsumeController extends BaseController {

	@Autowired
	private EnergyConsumeService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private DistrictService disservice;

	private List<EnergyConsume> check_input(HttpServletRequest request) {
		List<EnergyConsume> list = new ArrayList<EnergyConsume>();
		for (int i = 0; i < 2; i++) {
			if(request.getParameter("energy[" + i + "][typename]")!=null){
			EnergyConsume ret = new EnergyConsume();
			String s = new String();
			s = request.getParameter("energy[" + i + "][id]");
			if (s != null && Tools.isInteger(s))
				ret.setId(Integer.parseInt(s));
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s))
				ret.setAccountid(Integer.parseInt(s));
			s = request.getParameter("energy[" + i + "][status]");
			if (s != null && Tools.isInteger(s))
				ret.setStatus(Integer.parseInt(s));
			s = request.getParameter("importflag");
			if (s != null && Tools.isInteger(s))
				ret.setImportflag(Integer.parseInt(s));
			ret.setFillTime(request.getParameter("fillTime"));
			s = request.getParameter("fillyear");
			if (s != null && Tools.isInteger(s))
				ret.setFillyear(Integer.parseInt(s));
			s = request.getParameter("province");
			if (s != null && Tools.isInteger(s))
				ret.setProvince(Integer.parseInt(s));
			s = request.getParameter("city");
			if (s != null && Tools.isInteger(s))
				ret.setCity(Integer.parseInt(s));
			s = request.getParameter("town");
			if (s != null && Tools.isInteger(s))
				ret.setTown(Integer.parseInt(s));
			s = request.getParameter("country");
			if (s != null && Tools.isInteger(s))
				ret.setCountry(Integer.parseInt(s));
			s = request.getParameter("street");
			if (s != null && Tools.isInteger(s))
				ret.setStreet(Integer.parseInt(s));
			ret.setPurpose(request.getParameter("energy[" + i + "][typename]"));
			s = request.getParameter("energy[" + i + "][coal]");
			if (s != null && Tools.isNumeric(s))
				ret.setCoal(Double.parseDouble(s));
			s = request.getParameter("energy[" + i + "][gas]");
			if (s != null && Tools.isNumeric(s))
				ret.setGas(Double.parseDouble(s));
			s = request.getParameter("energy[" + i + "][liquefied]");
			if (s != null && Tools.isNumeric(s))
				ret.setLiqgas(Double.parseDouble(s));
			s = request.getParameter("energy[" + i + "][fueloil]");
			if (s != null && Tools.isNumeric(s))
				ret.setOil(Double.parseDouble(s));
			s = request.getParameter("energy[" + i + "][electricity]");
			if (s != null && Tools.isNumeric(s))
				ret.setElec(Double.parseDouble(s));
			list.add(ret);
		}
		}
		return list;
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

			int town = 0;
			int fillYear = 0;
			String s = request.getParameter("town");
			String year = request.getParameter("fillyear");
			if(s != null && year != null){
				town = Integer.parseInt(s);
				fillYear = Integer.parseInt(year);
			}
			
			
			List<EnergyConsume> list = theService.getByTown(town, companyid, fillYear);
			if(list.size()>0){
				result.put("id", list.get(0).getTown());
			}else{
				return result.setStatus(-2, "无数据");
			}
			
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

			List<EnergyConsume> list = check_input(request);
			for (EnergyConsume input : list) {
				input.setAccountid(companyid);

				EnergyConsume search = null;
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
			}
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

			EnergyConsume search = theService.getById(id);
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

			EnergyConsume input = new EnergyConsume();
			input.setFillyear(year);
			input.setAccountid(companyid);

			List<EnergyConsume> list = theService.getByYear(input);
			List listvo=new ArrayList();
			
			if(list.size()>0){
				for(int i=0;i<2;i++){
					EnergyConsumeVo vo=new EnergyConsumeVo();
					if(i ==0)vo.setPurpose("工业使用");
					if(i ==1)vo.setPurpose("民用");
					List volist=new ArrayList();
					for(EnergyConsume ec:list){
						if(vo.getPurpose().equals(ec.getPurpose())){
							vo.setStatus(ec.getStatus());
							Map map=new HashMap();
							map.put("id",ec.getId() );
							map.put("town", ec.getTown());
							map.put("townname",disservice.getById(ec.getTown()).getDistrictName());
							map.put("status",ec.getStatus());
							map.put("coal", ec.getCoal());
							map.put("gas",ec.getGas());
							map.put("liquefied",ec.getLiqgas());
							map.put("fueloil",ec.getOil());
							map.put("electricity",ec.getElec());
							volist.add(map);
	
						}
					}
					vo.setEnergy(volist);
					listvo.add(vo);
				}
				result.put("data", listvo);
			}else{
				return result.setStatus(0, "无数据");
			}

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
