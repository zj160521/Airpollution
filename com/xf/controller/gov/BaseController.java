package com.xf.controller.gov;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.security.LoginManage;
import com.xf.service.gov.BaseService;

public abstract class BaseController {

    BaseService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	public abstract void setService();

	@RequestMapping(value = "/checked", method = RequestMethod.POST)
	@ResponseBody
	public Object checked(HttpServletRequest request){
	
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		setService();
		
		String enterpriceid = request.getParameter("accountid");
		String typ = request.getParameter("type");
		String year = request.getParameter("fillyear");
		int accountid = Integer.parseInt(enterpriceid);
		int type = Integer.parseInt(typ);
		int fillyear = Integer.parseInt(year);
		
			if(type == 0){				
				theService.setstatus3(2, accountid, 1, fillyear);	
				theService.setstatus3(3, accountid, 2, fillyear);
				return result.setStatus(0, "ok");
			}else if(type == 1){
				theService.setstatus3(2, accountid, 3, fillyear);
				theService.setstatus3(1, accountid, 2, fillyear);
				return result.setStatus(0, "ok");
			}else{
				return result.setStatus(-2, "typeid值错误");
			}	
	}
}
