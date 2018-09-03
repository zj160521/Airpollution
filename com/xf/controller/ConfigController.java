package com.xf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Config;
import com.xf.security.LoginManage;
import com.xf.service.ConfigService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/config")
public class ConfigController { 
	@Autowired
	private ConfigService configService;
	@Autowired
	private ResultObj result;
	@Autowired
	private LoginManage loginManage;
	
//  更新
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object configUpdate(HttpServletRequest request){
		if (!loginManage.isUserLogin(request)){
			return result.setStatus(-1, "user not login.");
			}
		String k=request.getParameter("key");
		String v=request.getParameter("value");
		Config config=new Config();
		if(k!=null && k!="" && v!=null && v!=""){
			config.setK(k);
			config.setV(v);
			configService.update(config);
		    return result.setStatus(0, "update config ok");
		}else{
			return result.setStatus(-3, "update config error.");
		}
		
	}
	
//  添加
	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addConfig(HttpServletRequest request){
		if (!loginManage.isUserLogin(request)){
			return result.setStatus(-1, "user not login.");
			}
		int id=Integer.parseInt(request.getParameter("id"));
		String k=request.getParameter("key");
		String v=request.getParameter("value");
		Config config=null;
		if(id!=0&&k!=null && k!=""&&v!=null && v!=""){
			config.setId(id);
			config.setK(k);
			config.setV(v);
			configService.add(config);
		    return result.setStatus(0, "add config ok");
		}else{
			return result.setStatus(-3, "add config error.");	
		}
		
	}
	
//  删除
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteConfig(HttpServletRequest request){
		if (!loginManage.isUserLogin(request)){
			return result.setStatus(-1, "user not login.");
			}
		String k=request.getParameter("key");
		if(k!=null&&k!=""){
			configService.delete(k);
		    return result.setStatus(0, "delete config ok");
		}else{
			return result.setStatus(-3, "delete config error.");	
		}
		
	}
	
//  查询值
	@RequestMapping(value = "/getvalue", method = RequestMethod.GET)
	@ResponseBody
	public Object getValue(HttpServletRequest request){
		if (!loginManage.isUserLogin(request)){
			return result.setStatus(-1, "user not login.");
			}
		
		if(configService.get(request.getParameter("key"))!=null&&
				configService.get(request.getParameter("key"))!=""){
			String v=configService.get(request.getParameter("key"));
			result.put("value", v);
		    return result.setStatus(0, "getvalue ok");
		}else{
			return result.setStatus(-3, "getvalue error.");
		}
	}
	
	@RequestMapping(value = "/getYears", method = RequestMethod.GET)
	@ResponseBody
	public Object getDate(HttpServletRequest request){
		if (!loginManage.isUserLogin(request)){
			return result.setStatus(-1, "user not login.");
			}
			
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String d=df.format(new Date());
		int date=Integer.parseInt(d);
		int year=Integer.parseInt(d);
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		for(int i=0;i<date-2010;i++){
			list.add(year--);
		}
		if(list.size()>0){
			result.put("value", list);
		    return result.setStatus(0, "getvalue ok");
		}else{
			return result.setStatus(-3, "getvalue error.");
		}
	}

}
