package com.xf.controller.gov;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import com.xf.entity.User;
import com.xf.entity.gov.Vgovimport;
import com.xf.security.LoginManage;
import com.xf.service.ConfigService;
import com.xf.service.gov.VgovimportService;
import com.xf.vo.Vgovimportl;

@Scope("prototype")
@Controller
@RequestMapping(value ="/workflow")
public class VgovimportController {
	@Autowired
	private VgovimportService  vgovimportService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	
	@RequestMapping(value ="/gov/import/{fillyear}",method = RequestMethod.GET)
	@ResponseBody
	public Object getAall(HttpServletRequest request,@PathVariable int fillyear){
		if (!loginManage.isUserLogin(request)) return result.setStatus(-1, "No login.");
		
		User u = loginManage.getLoginUser(request);
		
		List<Vgovimport> list;
		
		List<String> list1;
		try {
			if (u.getTypeid() > 2 && u.getCity() > 0)
				list=vgovimportService.getByCity(u.getCity(),fillyear);
			else
				list=vgovimportService.getAll(fillyear);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "no data");
		}
		list1 = new ArrayList<String>();
		for(Vgovimport vgovimport:list){
			list1.add(vgovimport.getName());
		}
		HashSet<String> hs = new HashSet<String>(list1);
		List<Vgovimportl> list2=new ArrayList<Vgovimportl>();
		for(String s:hs){
			Vgovimportl vgovimportl=new Vgovimportl();
			List<Map> listm=new ArrayList<Map>();
			for(Vgovimport vgovimport:list){
				if(s.equals(vgovimport.getName())){
					Map<String,String> map=new HashMap<String,String>();
					vgovimportl.setId(vgovimport.getId());
					vgovimportl.setName(vgovimport.getName());
					vgovimportl.setProvinceName(vgovimport.getProvinceName());
					vgovimportl.setCityName(vgovimport.getCityName());
					vgovimportl.setGovname(vgovimport.getGovname());
					vgovimportl.setContact(vgovimport.getContact());
					vgovimportl.setContactNo(vgovimport.getContactNo());
					map.put("fileid", vgovimport.getFileid()+"");
					map.put("reportname", vgovimport.getReportname());
					map.put("checkfile", vgovimport.getCheckfile());
					map.put("importfile", vgovimport.getImportfile());
					map.put("reportDesp",vgovimport.getReportDesp() );
					map.put("status", vgovimport.getStatus()+"");
					map.put("fillyear", vgovimport.getFillyear()+"");
			        listm.add(map);
				}
			}
			vgovimportl.setAllFile(listm);
			list2.add(vgovimportl);
		}
		result.put("data",list2 );
		return result.setStatus(0, "");
		
	}
	
	@RequestMapping(value ="/gov/imports",method = RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request){
		if (!loginManage.isUserLogin(request)) return result.setStatus(-1, "No login.");
		
		User u = loginManage.getLoginUser(request);
		String name=request.getParameter("name");
		String year=request.getParameter("fillyear");
		int fillyear=Integer.parseInt(year);
		List<Vgovimport> list;
		
		List<String> list1;
		try {
			if (u.getTypeid() > 2 && u.getCity() > 0)
				list=vgovimportService.cityBymorename(u.getCity(),fillyear,name);
			else
				list=vgovimportService.getBymorename(fillyear,name);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "no data");
		}
		list1 = new ArrayList<String>();
		for(Vgovimport vgovimport:list){
			list1.add(vgovimport.getName());
		}
		HashSet<String> hs = new HashSet<String>(list1);
		List<Vgovimportl> list2=new ArrayList<Vgovimportl>();
		for(String s:hs){
			Vgovimportl vgovimportl=new Vgovimportl();
			List<Map> listm=new ArrayList<Map>();
			for(Vgovimport vgovimport:list){
				if(s.equals(vgovimport.getName())){
					Map<String,String> map=new HashMap<String,String>();
					vgovimportl.setId(vgovimport.getId());
					vgovimportl.setName(vgovimport.getName());
					vgovimportl.setProvinceName(vgovimport.getProvinceName());
					vgovimportl.setCityName(vgovimport.getCityName());
					vgovimportl.setGovname(vgovimport.getGovname());
					vgovimportl.setContact(vgovimport.getContact());
					vgovimportl.setContactNo(vgovimport.getContactNo());
					map.put("fileid", vgovimport.getFileid()+"");
					map.put("reportname", vgovimport.getReportname());
					map.put("checkfile", vgovimport.getCheckfile());
					map.put("importfile", vgovimport.getImportfile());
					map.put("reportDesp",vgovimport.getReportDesp() );
					map.put("status", vgovimport.getStatus()+"");
					map.put("fillyear", vgovimport.getFillyear()+"");
			        listm.add(map);				
				}
			}
			vgovimportl.setAllFile(listm);
			list2.add(vgovimportl);
		}
		result.put("data",list2 );
		return result.setStatus(0, "");
		
	}

}
