package com.xf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Permission;
import com.xf.security.LoginManage;
import com.xf.service.PermissionService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
	@Autowired
	private PermissionService theService;
	@Autowired
	private ResultObj result;
	@Autowired
	private LoginManage loginManage;

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request,@RequestBody Permission permission) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		
//		Object per = loginManage.checkPermission(request,395);
//	    if (per != null) return per;
		
	    try {
			theService.addupPermissionByRole(permission);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "编辑权限出错！");
		}
		
		return result.setStatus(0, "ok");
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request,@RequestBody Permission permission) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		
//		Object per = loginManage.checkPermission(request,296);
//	    if (per != null) return per;
		
	    List<Permission> all=theService.getAllPermission();
	    List<Permission> list=theService.getAllPermissionByRole(permission.getRoleid());
	    for(Permission p:all){
			for(Permission p1:list){
				if(p.getId()==p1.getId()){
					p.setEnable(true);
					break;
				}
			}
		}
	    Permission p=loginManage.recursiveTree(10,all);
	    result.put("res", p);
		return result.setStatus(0, "ok");
	}
	
}
