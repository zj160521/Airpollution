package com.xf.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.dao.ISfacilityDao;
import com.xf.entity.Company;
import com.xf.entity.SmallFacility;
import com.xf.entity.Static;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.StaticService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/smallFacility")
public class SFacilityController {
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private StaticService staticService;
	@Autowired
	private ISfacilityDao theDao;

	private SmallFacility check(HttpServletRequest request) {
		String s = new String();
		SmallFacility faci = new SmallFacility();
		faci.setTechniqueName1(request.getParameter("techniqueName1"));
		faci.setTechniqueName2(request.getParameter("techniqueName2"));
		faci.setTechniqueName3(request.getParameter("techniqueName3"));
		faci.setTechniqueName4(request.getParameter("techniqueName4"));

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			faci.setId(Integer.parseInt(s));
		s = request.getParameter("enterpriceId");
		if (s != null && Tools.isInteger(s))
			faci.setEnterpriceId(Integer.parseInt(s));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			faci.setFillyear(Integer.parseInt(s));
		s = request.getParameter("technique1");
		if (s != null && Tools.isInteger(s))
			faci.setTechnique1(Integer.parseInt(s));
		s = request.getParameter("technique2");
		if (s != null && Tools.isInteger(s))
			faci.setTechnique2(Integer.parseInt(s));
		s = request.getParameter("technique3");
		if (s != null && Tools.isInteger(s))
			faci.setTechnique3(Integer.parseInt(s));
		s = request.getParameter("technique4");
		if (s != null && Tools.isInteger(s))
			faci.setTechnique4(Integer.parseInt(s));

		return faci;
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object smallFacility(HttpServletRequest request) {

		SmallFacility facility = check(request);
		if (facility == null)
			return result.setStatus(-3, "no facility.");

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (facility.getEnterpriceId() <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = facility.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			Class<SmallFacility> clazz = SmallFacility.class;
			facility.setEnterpriceId(companyid);

			// 处理固定信息
			for (int i = 1; i < 5; i++) {
				int value = 5000 + i;
				if (i == 4)
					value = 5005;

				Method getTechx = clazz.getDeclaredMethod("getTechnique" + i);
				Method getNamex = clazz.getDeclaredMethod("getTechniqueName"
						+ i);
				Integer valTechx = (Integer) (getTechx.invoke(facility));
				String valNamex = (String) (getNamex.invoke(facility));

				Static info = new Static();
				if (valTechx <= 0 && valNamex != null && !valNamex.isEmpty()) {
					info.setGroupid(9);
					info.setPid(value);
					info.setName(valNamex);
					staticService.add(info);

					if (i == 1)
						facility.setTechnique1(info.getId());
					else if (i == 2)
						facility.setTechnique2(info.getId());
					else if (i == 3)
						facility.setTechnique3(info.getId());
					else
						facility.setTechnique4(info.getId());
				}
			}
			
			facility.setEnterpriceId(companyid);
			if (facility.getStatus() < 1) {
				facility.setStatus(WorkFlowController.STATUS_FILL);
			}

			if (facility.getId() > 0)
				theDao.update(facility);
			else
				theDao.add(facility);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "添加失败");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/all/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request,@PathVariable int fillyear) {

		SmallFacility facility = check(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (facility.getEnterpriceId() <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = facility.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			facility.setFillyear(fillyear);
			facility.setEnterpriceId(companyid);
			List<SmallFacility> list = theDao.getAll(facility);
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "获取失败");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByid(HttpServletRequest request, @PathVariable int id) {

		SmallFacility facility = check(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (facility.getEnterpriceId() <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = facility.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			SmallFacility fac = theDao.getByid(id);
			result.put("data", fac);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "获取失败");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request) {

		SmallFacility facility = check(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (facility.getEnterpriceId() <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = facility.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			theDao.delete(facility.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "删除失败");
		}
		return result.setStatus(0, "ok");
	}
}
