package com.xf.controller.stat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.xf.entity.gov.VehicleAction;
import com.xf.entity.gov.VehicleStandard;
import com.xf.security.LoginManage;
import com.xf.service.stat.MotorStandartService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/motorstandard")
public class MotorStandartController {
	@Autowired
	private MotorStandartService motorService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private Object checkAccount(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	@RequestMapping(value = "/updateStandant/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	private Object updateStandant(HttpServletRequest request,@PathVariable int fillyear) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		List<VehicleAction> list = motorService.getAllMotor(fillyear);
		List<VehicleStandard> statlist = motorService.getAllStandard();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			for (VehicleAction va : list) {
				Date dt1 = df.parse(va.getRegisterdate());
				Calendar c1 = Calendar.getInstance();
				c1.setTime(dt1);

				Date dt2 = df.parse(va.getCheckdate());
				Calendar c2 = Calendar.getInstance();
				c2.setTime(dt2);

				long mills=dt2.getTime()-dt1.getTime();
				double day=mills/(1000*60*60*24);
				double years=day/365;
				double mile = 0;
				if (years > 0) {
					mile = va.getMileage() / years;
				}
				va.setAvgmile(mile);
				for (VehicleStandard vs : statlist) {
					Date dt3 = df.parse(vs.getStartdate());
					Date dt4 = df.parse(vs.getEnddate());
					if (va.getVehiclemodel() == vs.getVehiclemodel()
							&& dt1.getTime()<=dt4.getTime()
							&& dt1.getTime()>=dt3.getTime()
							&& va.getGastype().equals(vs.getGastype())) {
						va.setStandard(vs.getStandard());
					}
				}
			}
			int count=0;
			List<VehicleAction> newlist=new ArrayList<VehicleAction>();
			for(VehicleAction sp:list) {
				if(count > 0 && count % 500 == 0) {
					newlist.add(sp);
					motorService.updateStandard(newlist);
					newlist.clear();
				}
				else {
					newlist.add(sp);
				}
				count++;
			}
			if(newlist.size() > 0){
				motorService.updateStandard(newlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.setStatus(0, "");
	}
}
