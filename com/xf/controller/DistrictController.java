package com.xf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Company;
import com.xf.entity.District;
import com.xf.security.LoginManage;
import com.xf.service.DistrictService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/district")
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	@Autowired
	private ResultObj result;
	@Autowired
	private LoginManage loginManage;
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable int id, HttpServletRequest request) {

		District district = districtService.getById(id);
		if (district == null) return result.setStatus(1, "not exist.");
		result.put("district", district);

	    return result.setStatus(0, "");
	}

	@RequestMapping(value = "/code/{no}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable String no, HttpServletRequest request) {

		District district = districtService.getByNo(no);
		if (district == null) return result.setStatus(1, "not exist.");
		result.put("district", district);

	    return result.setStatus(0, "");
	}

	@RequestMapping(value = "/parent/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByParent(@PathVariable int pid, HttpServletRequest request) {

		List<District> tList = districtService.getByParent(pid);
		
		District dst = districtService.getById(pid);
		Company c = loginManage.getLoginCompany(request);
		
		result.put("districtlist", tList);

	    return result.setStatus(0, "");
	}

	@RequestMapping(value = "/parent/code/{pno}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByParentNo(@PathVariable String pno, HttpServletRequest request) {

		List<District> tList = districtService.getByParentNo(pno);
		result.put("districtlist", tList);

	    return result.setStatus(0, "");
	}

	@RequestMapping(value = "/query/code/{part_no}", method = RequestMethod.GET)
	@ResponseBody
	public Object searchByNo(@PathVariable String part_no, HttpServletRequest request) {

		List<District> tList = districtService.searchByNo(part_no);
		result.put("districtlist", tList);

	    return result.setStatus(0, "");
	}

	@RequestMapping(value = "/query/name/{part_name}", method = RequestMethod.GET)
	@ResponseBody
	public Object searchByName(@PathVariable String part_name, HttpServletRequest request) {

		List<District> tList = districtService.searchByName(part_name);
		result.put("districtlist", tList);

	    return result.setStatus(0, "");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request) {

		List<HashMap<String,Object>> dlist=new ArrayList<HashMap<String,Object>>();
		List<District> tList = districtService.getByParent(0);
		for(District tli:tList){
			List<HashMap<String,Object>> clist=new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> dpany=new LinkedHashMap<String, Object>();
			dpany.put("value", tli.getId());
			dpany.put("label", tli.getDistrictName());
			List<District> tlist = districtService.getByParent(tli.getId());
			for(District li:tlist){
				List<HashMap<String,Object>> plist=new ArrayList<HashMap<String,Object>>();
				HashMap<String,Object> cpany=new LinkedHashMap<String, Object>();
				cpany.put("value", li.getId());
				cpany.put("label", li.getDistrictName());
				List<District> list = districtService.getByParent(li.getId());
				for(District i:list){
					HashMap<String,Object> ppany=new LinkedHashMap<String, Object>();
					ppany.put("value", i.getId());
					ppany.put("label", i.getDistrictName());
					plist.add(ppany);
				}
				cpany.put("children", plist);
				clist.add(cpany);
			}
			dpany.put("children", clist);
			dlist.add(dpany);
		}
		result.put("list", dlist);
	    return result.setStatus(0, "");
	}
}
