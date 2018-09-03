package com.xf.controller;

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

import com.xf.entity.Company;
import com.xf.entity.DevFill;
import com.xf.entity.Devices;
import com.xf.entity.Material;
import com.xf.entity.Product;
import com.xf.entity.Static;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.ConfigService;
import com.xf.service.DevFillService;
import com.xf.service.DeviceService;
import com.xf.service.MaterialService;
import com.xf.service.ProductService;
import com.xf.service.StaticService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/devfill")
public class DevFillController {

	@Autowired
	private DevFillService devfillService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ProductService productService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private ConfigService configService;
	@Autowired
	private MaterialService materialService;
	
	private int companyid = 0;
	
	private DevFill check(HttpServletRequest request) {
		
		String s = new String();
		DevFill devfill = new DevFill();
		devfill.setFuelPlace(request.getParameter("fuelPlace"));
		devfill.setFuelName(request.getParameter("fuelName"));
		devfill.setFuelPname(request.getParameter("fuelPname"));
		devfill.setFuelUnit(request.getParameter("fuelUnit"));
		devfill.setFillTime(request.getParameter("fillTime"));
		devfill.setRemark(request.getParameter("remark"));
		devfill.setMaterialName(request.getParameter("materialName"));
		devfill.setProductName(request.getParameter("productName"));
		devfill.setMaterialUnit(request.getParameter("materialUnit"));
		devfill.setProductUnit(request.getParameter("productUnit"));
		
		s = request.getParameter("id");
		if (s!=null && Tools.isInteger(s)) devfill.setId(Integer.parseInt(s));
		s = request.getParameter("deviceId");
		if (s!=null && Tools.isInteger(s)) devfill.setDeviceId(Integer.parseInt(s));
		s = request.getParameter("fuelId");
		if (s!=null && Tools.isInteger(s)) devfill.setFuelId(Integer.parseInt(s));
		s = request.getParameter("fuelPid");
		if (s!=null && Tools.isInteger(s)) devfill.setFuelPid(Integer.parseInt(s));
		s = request.getParameter("fillyear");
		if (s!=null && Tools.isInteger(s)) devfill.setFillyear(Integer.parseInt(s));
		s = request.getParameter("status");
		if (s!=null && Tools.isInteger(s)) devfill.setStatus(Integer.parseInt(s));
		s = request.getParameter("productId");
		if (s!=null && Tools.isInteger(s)) devfill.setProductId(Integer.parseInt(s));

		s = request.getParameter("scontent");
		if (s!=null && Tools.isNumeric(s)) devfill.setSContent(Double.parseDouble(s));
		s = request.getParameter("ashContent");
		if (s!=null && Tools.isNumeric(s)) devfill.setAshContent(Double.parseDouble(s));
		s = request.getParameter("vocContent");
		if (s!=null && Tools.isNumeric(s)) devfill.setVocContent(Double.parseDouble(s));
		s = request.getParameter("hoursPerDay");
		if (s!=null && Tools.isNumeric(s)) devfill.setHoursPerDay(Double.parseDouble(s));
		s = request.getParameter("daysPerYear");
		if (s!=null && Tools.isNumeric(s)) devfill.setDaysPerYear(Double.parseDouble(s));
		s = request.getParameter("materialConsume");
		if (s!=null && Tools.isNumeric(s)) devfill.setMaterialConsume(Double.parseDouble(s));
		s = request.getParameter("productTotalYear");
		if (s!=null && Tools.isNumeric(s)) devfill.setProductTotalYear(Double.parseDouble(s));
		s = request.getParameter("mTotalYear");
		if (s!=null && Tools.isNumeric(s)) devfill.setmTotalYear(Double.parseDouble(s));
		s = request.getParameter("m1");
		if (s!=null && Tools.isNumeric(s)) devfill.setM1(Double.parseDouble(s));
		s = request.getParameter("m2");
		if (s!=null && Tools.isNumeric(s)) devfill.setM2(Double.parseDouble(s));
		s = request.getParameter("m3");
		if (s!=null && Tools.isNumeric(s)) devfill.setM3(Double.parseDouble(s));
		s = request.getParameter("m4");
		if (s!=null && Tools.isNumeric(s)) devfill.setM4(Double.parseDouble(s));
		s = request.getParameter("m5");
		if (s!=null && Tools.isNumeric(s)) devfill.setM5(Double.parseDouble(s));
		s = request.getParameter("m6");
		if (s!=null && Tools.isNumeric(s)) devfill.setM6(Double.parseDouble(s));
		s = request.getParameter("m7");
		if (s!=null && Tools.isNumeric(s)) devfill.setM7(Double.parseDouble(s));
		s = request.getParameter("m8");
		if (s!=null && Tools.isNumeric(s)) devfill.setM8(Double.parseDouble(s));
		s = request.getParameter("m9");
		if (s!=null && Tools.isNumeric(s)) devfill.setM9(Double.parseDouble(s));
		s = request.getParameter("m10");
		if (s!=null && Tools.isNumeric(s)) devfill.setM10(Double.parseDouble(s));
		s = request.getParameter("m11");
		if (s!=null && Tools.isNumeric(s)) devfill.setM11(Double.parseDouble(s));
		s = request.getParameter("m12");
		if (s!=null && Tools.isNumeric(s)) devfill.setM12(Double.parseDouble(s));
		s = request.getParameter("materialId");
		if (s!=null && Tools.isInteger(s)) devfill.setMaterialId(Integer.parseInt(s));
		
		return devfill;
	}

	@RequestMapping(value = "/fill", method = RequestMethod.POST)
	@ResponseBody
	public Object addCompany(HttpServletRequest request) {
		
		DevFill devfill = check(request);
		int year=devfill.getFillyear();
		Company c = null;
		Devices d = null;

		if (loginManage.isCompanyLogin(request)) {
			
			//检查请求上报的设备是否属于当前登录的公司
			c = loginManage.getLoginCompany(request);
			d = deviceService.getBydevId(devfill.getDeviceId());
			if (null == d) return result.setStatus(-2, "Wrong device id.");
			if (d.getEnterpriceId() != c.getId()) return result.setStatus(-2, "Is not your company's device.");
		}
		else if (loginManage.isUserLogin(request)) {
			
			d = deviceService.getBydevId(devfill.getDeviceId());
			if (null == d) return result.setStatus(-2, "Wrong device id.");
		}
		else {
			return result.setStatus(-1, "No login.");
		}
		
		try {
			
			//检查有无当年上报记录，没有则添加，有则更新
			boolean hasCurYear = false;
			Calendar cal = Calendar.getInstance();

			List<DevFill> flist = devfillService.getByDevId1(devfill.getDeviceId());
			if (flist != null && !flist.isEmpty()) {
				
				for(DevFill df:flist){
					if (df.getFillyear() == year) {
						hasCurYear = true;
					}
				}
				
			}
			
			//处理固定信息
			Static info = new Static();
			if (devfill.getFuelPid() <= 0 && devfill.getFuelPname() != null && !devfill.getFuelPname().isEmpty()) {
				//先添加燃料类型
				info.setAccountid(d.getEnterpriceId());
				info.setGroupid(2);
				info.setPid(0);
				info.setName(devfill.getFuelPname());
				staticService.add(info);
				
				devfill.setFuelPid(info.getId());
			}

			if (devfill.getFuelId() <= 0 && devfill.getFuelPid() > 0 && 
				devfill.getFuelName() != null && !devfill.getFuelName().isEmpty()) {
				//再添加燃料名称
				Static sta=staticService.getByFuelName(devfill.getFuelName());
				if(sta==null){
				info.setAccountid(d.getEnterpriceId());
				info.setGroupid(3);
				info.setPid(devfill.getFuelPid());
				info.setName(devfill.getFuelName());
				info.setUnit(devfill.getFuelUnit());
				staticService.add(info);
				}else{
					return result.setStatus(-3, "该燃料已存在于基本燃料类型中");
				}
				
				devfill.setFuelId(info.getId());
			}
			
			if (devfill.getProductId() == 0 && !devfill.getProductName().isEmpty()) {
				Product product = new Product();
				product.setId(devfill.getProductId());
				product.setProductName(devfill.getProductName());
				product.setEnterpriceId(d.getEnterpriceId());
				product.setUnit(devfill.getProductUnit());
				productService.add(product);
				productService.updatePro(product);
				devfill.setProductId(product.getId());
			}
			
			if (devfill.getMaterialId() == 0 && !devfill.getMaterialName().isEmpty()) {
				Material m = new Material();
				m.setId(devfill.getMaterialId());
				m.setMaterialName(devfill.getMaterialName());
				m.setEnterpriceId(d.getEnterpriceId());
				m.setUnit(devfill.getMaterialUnit());
				materialService.add(m);
				devfill.setMaterialId(m.getId());
			}

			devfill.setFillTime(Tools.ToDateStr(cal));
			if (devfill.getStatus() < 1)
				devfill.setStatus(WorkFlowController.STATUS_FILL);
			if (hasCurYear == false) {
				devfill.setFillyear(year);
				devfillService.add(devfill);
				return result.setStatus(0, "add device fill ok");
			}
			else {
				devfillService.update(devfill);
				return result.setStatus(0, "update device fill ok");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "device fill error");
		}
	}

	@RequestMapping(value = "/{devid}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByDevId(@PathVariable int devid,@PathVariable int fillyear, HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			//检查设备是否属于当前登录的公司
			Company c = loginManage.getLoginCompany(request);
			Devices d = deviceService.getBydevId(devid);
			if (null == d) return result.setStatus(-4, "Wrong device id.");
			if (d.getEnterpriceId() != c.getId()) return result.setStatus(-2, "Is not your company's device.");
		}
		else if (loginManage.isUserLogin(request)) {
			Devices d = deviceService.getBydevId(devid);
			if (null == d) return result.setStatus(-3, "Wrong device id.");
		}
		else {
			return result.setStatus(-1, "No login.");
		}

		//检查有无当年上报记录，没有则添加一条空记录返回
		boolean hasCurYear = false;
		Calendar cal = Calendar.getInstance();

		List<DevFill> flist = devfillService.getByDevId(devid,fillyear);
		if (flist == null) return result.setStatus(1, "devfill is not exist.");

		if (!flist.isEmpty()) {
			DevFill curfill = flist.get(0);
			
			if (curfill.getFillyear() >= fillyear) {
				hasCurYear = true;
			}
		}
		
		if (hasCurYear == false) {
			DevFill curfill = new DevFill();
			curfill.setDeviceId(devid);
			curfill.setFillyear(fillyear);
			curfill.setFillTime(Tools.ToDateStr(cal));
			flist.add(0, curfill);
		}

		result.put("devfilllist", flist);

	    return result.setStatus(0, "");
	}

	//燃料类型
	@RequestMapping(value = "/add/fueltype/{dname}", method = RequestMethod.POST)
	@ResponseBody
	public Object getFuelType(@PathVariable String dname, HttpServletRequest request) {
		
		try {
			int maxid = staticService.getMaxId(2);
			Static s = staticService.getById(2001);
			s.setId(maxid);
			s.setName(dname);
			
			staticService.add(s);
			result.put("lastid", s.getId());

		} catch(Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		
		return result.setStatus(0, "");
	}
	
	//燃料名称
	@RequestMapping(value = "/add/fuelname/{dname}", method = RequestMethod.POST)
	@ResponseBody
	public Object getFuelName(@PathVariable String dname, HttpServletRequest request) {
		
		try {
			int maxid = staticService.getMaxId(3);
			Static s = staticService.getById(2101);
			s.setId(maxid);
			s.setName(dname);
			
			staticService.add(s);
			result.put("lastid", s.getId());

		} catch(Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		
		return result.setStatus(0, "");
	}
	
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
			int deviceid=Integer.parseInt(id);

			devfillService.unpass(1, deviceid, 2);
			
			return result.setStatus(0, "");
			}else{
				return result.setStatus(-2, "无企业id");
			}

	}
	
}
