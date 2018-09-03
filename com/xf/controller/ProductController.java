package com.xf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Company;
import com.xf.security.LoginManage;
import com.xf.service.ProductFillService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/productfill")
public class ProductController {
	
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private ProductFillService productFillService;
	
	int companyid = 0;
	
	@RequestMapping(value = "unpass", method = RequestMethod.POST)
	@ResponseBody
	public Object unpass(HttpServletRequest request){
          String id=request.getParameter("id");
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		}
		else if (loginManage.isUserLogin(request)) {
		}
		else {
			return result.setStatus(-1, "No login.");
		}
		

			if(id!=null && id!=""){
			int Id=Integer.parseInt(id);
			
			productFillService.unpass(1, Id, 2);
			
			return result.setStatus(0, "");
			}else{
				return result.setStatus(-2, "无企业id");
			}

	}

}
