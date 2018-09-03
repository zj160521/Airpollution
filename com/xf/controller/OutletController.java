package com.xf.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Company;
import com.xf.entity.Outlet;
import com.xf.entity.OutletFill;
import com.xf.entity.OutletPollutant;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.ConfigService;
import com.xf.service.OutletFillService;
import com.xf.service.OutletPollutantService;
import com.xf.service.OutletService;
import com.xf.service.PollutantService;
import com.xf.vo.OFVo;

@Scope("prototype")
@Controller
@RequestMapping(value = "/outlet")
public class OutletController {

	@Autowired
	private OutletService outletService;
	@Autowired
	private OutletFillService ofillService;
	@Autowired
	private OutletPollutantService opfillService;
	@Autowired
	private PollutantService pollutantService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	private int companyid = 0;

	private Outlet check_outlet(HttpServletRequest request) {

		String s = new String();
		Outlet outlet = new Outlet();
		outlet.setOutletSerial(request.getParameter("outletSerial"));
		outlet.setOutletTypeName(request.getParameter("outletTypeName"));
		outlet.setRemark(request.getParameter("remark"));

		s = request.getParameter("id"); 
		if (s != null && Tools.isInteger(s))
			outlet.setId(Integer.parseInt(s));
		s = request.getParameter("enterpriceId");
		if (s != null && Tools.isInteger(s))
			outlet.setEnterpriceId(Integer.parseInt(s));
		s = request.getParameter("outletTypeId");
		if (s != null && Tools.isInteger(s))
			outlet.setOutletTypeId(Integer.parseInt(s));
		s = request.getParameter("enabled");
		if (s != null && Tools.isInteger(s))
			outlet.setEnabled(Integer.parseInt(s));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			outlet.setStatus(Integer.parseInt(s));

		s = request.getParameter("e_outlet");
		if (s != null && Tools.isNumeric(s))
			outlet.setE_outlet(Double.parseDouble(s));
		s = request.getParameter("n_outlet");
		if (s != null && Tools.isNumeric(s))
			outlet.setN_outlet(Double.parseDouble(s));
		s = request.getParameter("outletDiameter");
		if (s != null && Tools.isNumeric(s))
			outlet.setOutletDiameter(Double.parseDouble(s));
		s = request.getParameter("outletHeight");
		if (s != null && Tools.isNumeric(s))
			outlet.setOutletHeight(Double.parseDouble(s));

		return outlet;
	}

	private OutletFill check_ofill(HttpServletRequest request) {
		String s = new String();
		OutletFill ofill = new OutletFill();

		ofill.setDetectDevice(request.getParameter("detectDevice"));
		ofill.setFillTime(request.getParameter("fillTime"));
		ofill.setRemark(request.getParameter("remark"));

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			ofill.setId(Integer.parseInt(s));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			ofill.setFillyear(Integer.parseInt(s));
		s = request.getParameter("outletId");
		if (s != null && Tools.isInteger(s))
			ofill.setOutletId(Integer.parseInt(s));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			ofill.setStatus(Integer.parseInt(s));

		s = request.getParameter("outletFlow");
		if (s != null && Tools.isNumeric(s))
			ofill.setOutletFlow(Double.parseDouble(s));
		s = request.getParameter("outletTemperature");
		if (s != null && Tools.isNumeric(s))
			ofill.setOutletTemperature(Double.parseDouble(s));
		s = request.getParameter("outletTotal");
		if (s != null && Tools.isNumeric(s))
			ofill.setOutletTotal(Double.parseDouble(s));
		s = request.getParameter("outletVelocity");
		if (s != null && Tools.isNumeric(s))
			ofill.setOutletVelocity(Double.parseDouble(s));

		return ofill;
	}

	private OutletPollutant check_opfill(HttpServletRequest request) {
		String s = new String();
		OutletPollutant opfill = new OutletPollutant();

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			opfill.setId(Integer.parseInt(s));
		s = request.getParameter("outletfillId");
		if (s != null && Tools.isInteger(s))
			opfill.setOutletfillId(Integer.parseInt(s));
		s = request.getParameter("pollutantId");
		if (s != null && Tools.isInteger(s))
			opfill.setPollutantId(Integer.parseInt(s));

		s = request.getParameter("s1Nongdu");
		if (s != null && Tools.isNumeric(s))
			opfill.setS1Nongdu(Double.parseDouble(s));
		s = request.getParameter("s2Nongdu");
		if (s != null && Tools.isNumeric(s))
			opfill.setS2Nongdu(Double.parseDouble(s));
		s = request.getParameter("s3Nongdu");
		if (s != null && Tools.isNumeric(s))
			opfill.setS3Nongdu(Double.parseDouble(s));
		s = request.getParameter("s4Nongdu");
		if (s != null && Tools.isNumeric(s))
			opfill.setS4Nongdu(Double.parseDouble(s));
		s = request.getParameter("totalAmount");
		if (s != null && Tools.isNumeric(s))
			opfill.setTotalAmount(Double.parseDouble(s));
		s = request.getParameter("yearNongdu");
		if (s != null && Tools.isNumeric(s))
			opfill.setYearNongdu(Double.parseDouble(s));

		return opfill;
	}

	
	@RequestMapping(value = "/deleteoutlet/{outletid}", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteOutlet(@PathVariable int outletid,HttpServletRequest request) {
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			
		} else {
			return result.setStatus(-1, "No login.");
		}
		if(outletid>0){
			outletService.delete(outletid);
		}else{
			return result.setStatus(-2, "排放口id错误");
		}
		
		return result.setStatus(0, "删除成功");
		
	}
	
	
	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addOutlet(HttpServletRequest request) {

		Outlet outlet = check_outlet(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (outlet.getEnterpriceId() <= 0)
				return result.setStatus(-1, "no company id.");
			companyid = outlet.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			outlet.setEnterpriceId(companyid);

			if (outlet.getId() > 0)
				outletService.update(outlet);
			else {
				outletService.add(outlet);
				result.put("id", outlet.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/all/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getOutlets(HttpServletRequest request,@PathVariable int fillyear) {

		Outlet outlet = check_outlet(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (outlet.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = outlet.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			List<Outlet> olist = outletService.getByCompany(companyid,fillyear);
			if (olist == null)
				result.setStatus(-4, "can not get outlet list.");
			
			List<Integer> arr=new ArrayList<Integer>();
			for(Outlet oli:olist){
				if(oli.getOutletSerial()!=null&&oli.getOutletSerial()!="")
				arr.add(Integer.parseInt(oli.getOutletSerial().substring(2)));
			}
			
			int max=0;
			if(arr.size()>0){
				max=Collections.max(arr);
			}
			result.put("max",max);
			result.put("outlets", olist);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/company/{id}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByCompanyId(@PathVariable int id,@PathVariable int fillyear,
			HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			id = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		List<Outlet> cList = outletService.getByCompany(id,fillyear);
		if (cList != null) {
			for (Outlet outli : cList) {
				OutletFill oflist = getOfillList(outli.getId(), fillyear);
				outli.setOfill(oflist);
			}
		}
		result.put("list", cList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object delOutlet(@PathVariable int id, HttpServletRequest request) {

		Outlet outlet = outletService.getById(id);
		if (outlet == null)
			return result.setStatus(-3, "outlet is not exist.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
			if (outlet.getEnterpriceId() != companyid)
				return result.setStatus(-2, "outlet is not in your company.");
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<OutletFill> ofilllist = ofillService.getByOutlet(id);
			if (ofilllist != null) {
				outlet.setEnabled(0);
				outletService.update(outlet);
			} else
				outletService.delete(id);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/fill/history/{oid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getFillHistory(@PathVariable int oid,
			HttpServletRequest request) {

		Outlet outlet = outletService.getById(oid);
		if (outlet == null)
			return result.setStatus(-3, "outlet is not exist.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
			if (outlet.getEnterpriceId() != companyid)
				return result.setStatus(-2, "outlet is not in your company.");
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		result.put("outlet", outlet);

		OutletFill olist = getOfillList(oid, 0);
		List<OutletFill> o =new ArrayList<OutletFill>();
		o.add(olist);
		result.put("fill_list", o);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/fill/{fillyear}/{oid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getFillCuryear(@PathVariable int fillyear,@PathVariable int oid,
			HttpServletRequest request) {

		Outlet outlet = outletService.getById(oid);
		if (outlet == null)
			return result.setStatus(-3, "outlet is not exist.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
			if (outlet.getEnterpriceId() != companyid)
				return result.setStatus(-2, "outlet is not in your company.");
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		result.put("outlet", outlet);

		OutletFill olist = getOfillList(oid, fillyear);
		List<OutletFill> o =new ArrayList<OutletFill>();
		o.add(olist);
		result.put("fill_list", o);

		return result.setStatus(0, "");
	}

	private OutletFill getOfillList(int oid, int curyear) {
		OutletFill ofilllist = ofillService.getCuryearFill(oid, curyear);
		if (ofilllist == null) {
			OutletFill ofill = new OutletFill();
			Calendar cal = Calendar.getInstance();
			ofill.setOutletId(oid);
			ofill.setFillTime(Tools.ToDateStr(cal));
			ofill.setFillyear(curyear);
			ofillService.add(ofill);

			ofilllist = ofillService.getCuryearFill(oid, curyear);
		}
		if (ofilllist != null) {
				List<OutletPollutant> oplist = opfillService
						.getByOutletFill(ofilllist.getId());
				if(oplist!=null)
					ofilllist.setPollutants(oplist);
		}
		return ofilllist;
	}

	@RequestMapping(value = "/fill", method = RequestMethod.POST)
	@ResponseBody
	public Object setOutletFill(HttpServletRequest request) {

		OutletFill ofill = check_ofill(request);
		if (ofill == null)
			return result.setStatus(-3, "outlet fill is not exist.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			if (ofill.getOutletId() <= 0)
				return result.setStatus(-4, "outletId is not exist.");

			List<OutletFill> ofilllist = ofillService.getByOutlet(ofill
					.getOutletId());
			OutletFill ofFound = null;
			for (OutletFill of : ofilllist) {
				if (of.getFillyear() == ofill.getFillyear()) {
					ofFound = of;
					break;
				}
			}

			Calendar cal = Calendar.getInstance();
			ofill.setFillTime(Tools.ToDateStr(cal));
			ofill.setFillyear(ofill.getFillyear());
			if (ofill.getStatus() < 1)
				ofill.setStatus(WorkFlowController.STATUS_FILL);

			if (ofFound == null) {
				ofillService.add(ofill);
			} else {
				ofill.setId(ofFound.getId());
				ofillService.update(ofill);
			}

			int ofill_id = ofill.getId();
			result.put("outletfillid", ofill_id);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/pollutant/fill", method = RequestMethod.POST)
	@ResponseBody
	public Object setOutletPollutantFill(HttpServletRequest request) {

		OutletPollutant opfill = check_opfill(request);
		if (opfill == null)
			return result.setStatus(-3, "outlet pollutant is not exist.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (opfill.getOutletfillId() <= 0)
				return result.setStatus(-4, "outletfillId is not exist.");

			if (opfill.getPollutantId() <= 0)
				return result.setStatus(-5, "pollutantId is not exist.");

			OutletPollutant opFound = null;
			List<OutletPollutant> opList = opfillService.getByOutletFill(opfill
					.getOutletfillId());
			for (OutletPollutant op : opList) {
				if (op.getPollutantId() == opfill.getPollutantId()) {
					opFound = op;
					break;
				}
			}

			if (opFound == null)
				opfillService.add(opfill);
			else {
				opfill.setId(opFound.getId());
				opfillService.update(opfill);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		result.put("outletFlow", request.getParameter("outletFlow"));
		result.put("outletTemperature",
				request.getParameter("outletTemperature"));
		result.put("outletTotal", request.getParameter("outletTotal"));
		result.put("outletVelocity", request.getParameter("outletVelocity"));
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/pollutant/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object OutletPollutantDel(HttpServletRequest request) {

		OutletPollutant opfill = check_opfill(request);
		if (opfill == null)
			return result.setStatus(-3, "outlet pollutant is not exist.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (opfill.getOutletfillId() <= 0)
				return result.setStatus(-4, "outletfillId is not exist.");

			if (opfill.getPollutantId() <= 0)
				return result.setStatus(-5, "pollutantId is not exist.");
			opfillService.deleteBy(opfill);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/pollutant/{fillid}", method = RequestMethod.POST)
	@ResponseBody
	public Object setOutletPollutant(@PathVariable int fillid,
			HttpServletRequest request) {

		OutletPollutant opfill = check_opfill(request);
		if (opfill == null)
			return result.setStatus(-3, "outlet pollutant is not exist.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<OutletPollutant> oplist = opfillService
					.getByOutletFill(fillid);
			if (oplist != null) {
				List<HashMap<String, Object>> pList = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> op = new HashMap<String, Object>();

				for (OutletPollutant p : oplist) {
					op.put("pollutantId", p.getId());
					op.put("pollutantName", p.getPollutantName());
					op.put("pollutantType", p.getPollutantType());
					op.put("s1Nongdu", p.getS1Nongdu());
					op.put("s2Nongdu", p.getS2Nongdu());
					op.put("s3Nongdu", p.getS3Nongdu());
					op.put("s4Nongdu", p.getS4Nongdu());
					op.put("yearNongdu", p.getYearNongdu());
					op.put("totalAmount", p.getTotalAmount());
					pList.add(op);
				}

				result.put("pollutants", pList);
			} else {
				return result.setStatus(-4, "exception");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "unpass", method = RequestMethod.POST)
	@ResponseBody
	public Object unpass(HttpServletRequest request) {
		String id = request.getParameter("id");

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		if (id != null && id != "") {
			int Id = Integer.parseInt(id);

			ofillService.unpass(1, Id, 2);

			return result.setStatus(0, "");
		} else {
			return result.setStatus(-2, "无企业id");
		}
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object check(HttpServletRequest request) {
		String outletSerial = request.getParameter("outletSerial");

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		if (outletSerial != null && outletSerial != "") {
			Outlet o=new Outlet();
			o.setEnterpriceId(companyid);
			o.setOutletSerial(outletSerial);
			outletService.check(o);
			
			return result.setStatus(0, "");
		} else {
			return result.setStatus(-2, "无企业id");
		}
	}
	
	@RequestMapping(value = "/fillUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object fillUpdate(HttpServletRequest request
			,@RequestBody OFVo ofvo) {
		
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}
		
		try {
			for(OutletPollutant ofv : ofvo.getPara()){
				if(ofv.getId() > 0)
					opfillService.update(ofv);
			}
			return result.setStatus(0, "OK");
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "出错啦！");
		}
	}
}
