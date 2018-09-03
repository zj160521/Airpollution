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

import com.xf.dao.IRoleDao;
import com.xf.entity.Role;
import com.xf.security.LoginManage;

@Scope("prototype")
@Controller
@RequestMapping(value = "/role")
public class RoleController {
	@Autowired
	private IRoleDao theDao;
	@Autowired
	private ResultObj result;
	@Autowired
	private LoginManage loginManage;

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request,@RequestBody Role role) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		
		try {
			if(role.getId()>0){
				
			    theDao.update(role);
			}else{
				
			    theDao.addRole(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "添加修改角色出错！");
		}
		
		return result.setStatus(0, "ok");
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		
		try {
			List<Role> list=theDao.getAllRole();
		    result.put("res", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "查询角色出错！");
		}
		
		return result.setStatus(0, "ok");
	}
	
	@RequestMapping(value = "/delRole", method = RequestMethod.POST)
	@ResponseBody
	public Object delRole(HttpServletRequest request,@RequestBody Role role) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		
		try {
			if(role.getId()>0){
				theDao.delRole(role.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "删除角色出错！");
		}
		
		return result.setStatus(0, "ok");
	}
}
