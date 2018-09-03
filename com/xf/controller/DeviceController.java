package com.xf.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import com.xf.entity.DevFill;
import com.xf.entity.Devices;
import com.xf.entity.Static;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.DevFillService;
import com.xf.service.DeviceService;
import com.xf.service.StaticService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/device")
public class DeviceController {

	@Autowired
	private DevFillService devfillService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private Devices check(HttpServletRequest request) {

		String s = new String();
		Devices device = new Devices();
		device.setDeviceName(request.getParameter("deviceName"));
		device.setDeviceSerial(request.getParameter("deviceSerial"));
		device.setDeviceModel(request.getParameter("deviceModel"));
		device.setDevicetypeName(request.getParameter("devicetypeName"));
		device.setDevicetypeName2(request.getParameter("devicetypeName2"));
		device.setFueltypeName(request.getParameter("fueltypeName"));
		device.setFueltypeName2(request.getParameter("fueltypeName2"));
		device.setServiceLife(request.getParameter("serviceLife"));
		device.setRemark(request.getParameter("remark"));

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			device.setId(Integer.parseInt(s));
		s = request.getParameter("enterpriceId");
		if (s != null && Tools.isInteger(s))
			device.setEnterpriceId(Integer.parseInt(s));
		s = request.getParameter("produceStepId");
		if (s != null && Tools.isInteger(s))
			device.setProduceStepId(Integer.parseInt(s));
		s = request.getParameter("shippingTon");
		if (s != null && Tools.isNumeric(s))
			device.setShippingTon(Double.parseDouble(s));
		s = request.getParameter("enabled");
		if (s != null && Tools.isInteger(s))
			device.setEnabled(Integer.parseInt(s));
		s = request.getParameter("deviceTypeId");
		if (s != null && Tools.isInteger(s))
			device.setDeviceTypeId(Integer.parseInt(s));
		s = request.getParameter("fuelTypeId");
		if (s != null && Tools.isInteger(s))
			device.setFuelTypeId(Integer.parseInt(s));
		s = request.getParameter("deviceTypeId2");
		if (s != null && Tools.isInteger(s))
			device.setDeviceTypeId2(Integer.parseInt(s));
		s = request.getParameter("fuelTypeId2");
		if (s != null && Tools.isInteger(s))
			device.setFuelTypeId2(Integer.parseInt(s));
		s = request.getParameter("devClass");
		if (s != null && Tools.isInteger(s))
			device.setDevClass(Integer.parseInt(s));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			device.setFillyear(Integer.parseInt(s));
		return device;
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addCompany(HttpServletRequest request) {

		Devices device = check(request);
		Company c = null;
		if (loginManage.isCompanyLogin(request)) {
			c = loginManage.getLoginCompany(request);
			device.setEnterpriceId(c.getId());
			if (c.getIsmall() == 1) {
				if (request.getParameter("unit") != null) {
					device.setUnit(request.getParameter("unit"));
				}
				if (request.getParameter("fuel") != null) {
					device.setFuel(Integer.parseInt(request.getParameter("fuel")));
				}
				if (request.getParameter("fuelcost") != null) {
					device.setFuelcost(Double.parseDouble(request.getParameter("fuelcost")));
				}
			}
		} else if (loginManage.isUserLogin(request)) {
			if (device.getEnterpriceId() <= 0)
				return result.setStatus(-2, "user login need companyid.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			// 处理固定信息
			Static info = new Static();
			int pid = 0;
			if (device.getDeviceTypeId() <= 0 && device.getDevicetypeName() != null
					&& !device.getDevicetypeName().isEmpty()) {

				if (device.getDevClass() == 0) { // 锅炉
					// 先添加燃料类型
					info.setAccountid(device.getEnterpriceId());
					info.setGroupid(2);
					info.setPid(0);
					info.setName(device.getDevicetypeName());

					// 防止重名
					List<String> names = staticService.getAllNames();
					for (String name : names) {
						if (name.equals(info.getName())) {
							return result.setStatus(-3, "已存在该名称的记录");
						}
					}
					staticService.add(info);

					// 添加锅炉类型
					pid = info.getId();
					info.setGroupid(1);
					info.setPid(pid);
					info.setName(device.getDevicetypeName() + "锅炉");

					// 防止重名
					for (String name : names) {
						if (name.equals(info.getName())) {
							return result.setStatus(-3, "已存在该名称的记录");
						}
					}
					staticService.add(info);

					device.setDeviceTypeId(info.getId());
				}

				if (device.getDevClass() == 1) { // 窑炉
					info.setAccountid(device.getEnterpriceId());
					info.setGroupid(5);
					info.setPid(0);
					info.setName(device.getDevicetypeName());
					// 防止重名
					List<String> names = staticService.getAllNames();
					for (String name : names) {
						if (name.equals(info.getName())) {
							return result.setStatus(-3, "已存在该名称的记录");
						}
					}

					staticService.add(info);
					device.setDeviceTypeId(info.getId());
					pid = info.getId();
				}
			}

			if (device.getDeviceTypeId2() <= 0 && device.getDevicetypeName2() != null
					&& !device.getDevicetypeName2().isEmpty()) {
				if (device.getDevClass() == 0) { // 锅炉
					info.setAccountid(device.getEnterpriceId());
					info.setGroupid(4);
					info.setPid(1001);
					info.setName(device.getDevicetypeName2());
					// 防止重名
					List<String> names = staticService.getAllNames();
					for (String name : names) {
						if (name.equals(info.getName())) {
							return result.setStatus(-3, "已存在该名称的记录");
						}
					}
					staticService.add(info);

					device.setDeviceTypeId2(info.getId());
				}

				if (device.getDevClass() == 1) { // 窑炉
					info.setAccountid(device.getEnterpriceId());
					info.setGroupid(6);
					info.setPid(device.getDeviceTypeId());
					info.setName(device.getDevicetypeName2());
					// 防止重名
					List<String> names = staticService.getAllNames();

					for (String name : names) {
						if (name.equals(info.getName())) {
							return result.setStatus(-3, "已存在该名称的记录");
						}
					}
					staticService.add(info);
					device.setDeviceTypeId2(info.getId());
				}
			}

			if (device.getId() <= 0) {
				deviceService.add(device);

				DevFill fill = new DevFill();
				fill.setFillyear(device.getFillyear());
				fill.setDeviceId(device.getId());
				fill.setFuelcost(device.getFuelcost());
				fill.setStatus(1);
				devfillService.add(fill);

			} else {
				deviceService.update(device);

				List<DevFill> dfill = devfillService.getByDevId(device.getId(), device.getFillyear());
				if(dfill.size()>0){
					DevFill fill = new DevFill();
					fill.setId(dfill.get(0).getId());
					fill.setFillyear(device.getFillyear());
					fill.setDeviceId(device.getId());
					fill.setFuelcost(device.getFuelcost());
					fill.setStatus(1);
					devfillService.update(fill);
				}else{
					DevFill fill = new DevFill();
					fill.setFillyear(device.getFillyear());
					fill.setDeviceId(device.getId());
					fill.setFuelcost(device.getFuelcost());
					fill.setStatus(1);
					devfillService.add(fill);
				}
				

			}
			result.put("device", device.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {

		Devices d = deviceService.getBydevId(id);
		if (d == null)
			return result.setStatus(1, "device is not exist.");

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			if (c.getId() != d.getEnterpriceId())
				return result.setStatus(-2, "this device is not in your company.");
		} else if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}

		result.put("device", d);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/{id}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id,@PathVariable int fillyear, HttpServletRequest request) {

		Devices d = deviceService.getBydevId2(id,fillyear);
		if (d == null)
			return result.setStatus(1, "device is not exist.");

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			if (c.getId() != d.getEnterpriceId())
				return result.setStatus(-2, "this device is not in your company.");
		} else if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}

		result.put("device", d);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/company/{id}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByCompanyId(@PathVariable int id, @PathVariable int fillyear, HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			if (c.getId() != id)
				return result.setStatus(-2, "not your company.");
		} else if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}

		List<Devices> cList = deviceService.getByCompany(id, fillyear);
		for (Devices d : cList) {
			DevFill df = devfillService.getCuryearFill(d.getId(), fillyear);
			if (df != null)
				d.devfill = df;
			else {
				d.devfill = new DevFill();
				d.devfill.setDeviceId(d.getId());
			}
		}
		result.put("devicelist", cList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/all/{enterpriceId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request, @PathVariable int enterpriceId) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (enterpriceId <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = enterpriceId;
		} else {
			return result.setStatus(-1, "No login.");
		}

		List<Devices> cList = deviceService.getByCompanyId(companyid);
		result.put("devicelist", cList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/all/{classid}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(@PathVariable int classid, @PathVariable int fillyear, HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request))
			return result.setStatus(-1, "Company not login.");

		Company c = loginManage.getLoginCompany(request);
		if (null == c)
			result.setStatus(-1, "login status error.");

		List<Devices> cList = deviceService.getByCompany(c.getId(), fillyear);
		List<Integer> arr = new ArrayList<Integer>();
		List<Devices> rlist = new ArrayList<Devices>();
		for (Devices d : cList) {
			if (d.getDevClass() == classid) {
				rlist.add(d);
				if (d.getDeviceSerial() != null && d.getDeviceSerial() != "")
					arr.add(Integer.parseInt(d.getDeviceSerial().substring(2)));
			}
		}

		int max = 0;
		if (arr.size() > 0) {
			max = Collections.max(arr);
		}
		result.put("max", max);
		result.put("devicelist", rlist);

		return result.setStatus(0, "");
	}

	// restFul风格来写delete
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable int id, HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request))
			return result.setStatus(-1, "Company not login.");

//		List<DevFill> list = devfillService.getByDevId1(id);
//		if (list.size() > 0) {
//			System.out.println("被删除设备有填报记录，不能删除！");
//			return result.setStatus(-2, "被删除设备有填报记录，不能删除！");
//		}
		deviceService.delete(id);

		return result.setStatus(0, "delete device ok");
	}

}
