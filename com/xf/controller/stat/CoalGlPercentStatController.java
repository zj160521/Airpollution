package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.xf.security.LoginManage;
import com.xf.service.stat.CoalGlPercentService;
import com.xf.vo.CoalGlPercent;

@Scope("prototype")
@Controller
@RequestMapping(value = "surface/CoalGl")
public class CoalGlPercentStatController {
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private CoalGlPercentService service;
	
	
	@RequestMapping(value = "/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object stat(@PathVariable int year,HttpServletRequest request){
		
		 if (loginManage.isUserLogin(request)) {
				
		 } else {
		 return result.setStatus(-1, "No login.");
		 }
		 
		 List<CoalGlPercent> data=service.getData(year);
		 if(data != null){
			 result.put("list", data);
			 return result.setStatus(0, "ok");
		 }else{
			 return result.setStatus(-1, "无数据");
		 }
		
	}

	@RequestMapping(value = "/getyears", method = RequestMethod.GET)
	@ResponseBody
	public Object years(HttpServletRequest request) {
		
		if (loginManage.isUserLogin(request)) {
			
		 } else {
		 return result.setStatus(-1, "No login.");
		 }
		
		int startyear = 2015;
		
		Calendar cal=Calendar.getInstance();
		int curyear=cal.get(cal.YEAR);
		
		int num=curyear-startyear;
		
		List<Integer> list=new ArrayList<Integer>();
		
		for(int i=0;i <= num;i++){
			
			int years=2015+i;
			
			list.add(years);
			
		}
		
		result.put("list", list);
		
		return result.setStatus(0, "ok");
		
	}
}
