package com.xf.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Company;
import com.xf.entity.DevFill;
import com.xf.entity.Devices;
import com.xf.entity.Material;
import com.xf.entity.MaterialFill;
import com.xf.entity.ProduceStep;
import com.xf.entity.Product;
import com.xf.entity.ProductFill;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.ConfigService;
import com.xf.service.DeviceService;
import com.xf.service.MaterialFillService;
import com.xf.service.MaterialService;
import com.xf.service.ProduceStepService;
import com.xf.service.ProductFillService;
import com.xf.service.ProductService;
import com.xf.vo.IfNull;

@Scope("prototype")
@Controller
@RequestMapping(value = "/step")
public class StepFillController {

	@Autowired
	private MaterialService materialService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialFillService mfillService;
	@Autowired
	private ProductFillService pfillService;
	@Autowired
	private ProduceStepService stepService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private ProduceStep step = new ProduceStep();
	private Material material = new Material();
	private Product product = new Product();
	private MaterialFill mfill = new MaterialFill();
	private ProductFill pfill = new ProductFill();
	private IfNull ifnull = new IfNull();

	private int deviceid;

	private void check(HttpServletRequest request) {

		String s = new String();
		step.setStepSerial(request.getParameter("stepSerial"));
		step.setStepName(request.getParameter("stepName"));
		step.setTechChart(request.getParameter("techChart"));
		step.setTechDesp(request.getParameter("techDesp"));
		step.setMainDevices(request.getParameter("mainDevices"));
		step.setRemark(request.getParameter("stepRemark"));
		s = request.getParameter("stepId");
		if (s != null && Tools.isInteger(s)) {
			step.setId(Integer.parseInt(s));
			pfill.setProduceStepId(step.getId());
		}

		s = request.getParameter("enabled");
		if (s != null && Tools.isInteger(s))
			step.setEnabled(Integer.parseInt(s));

		s = request.getParameter("companyId");
		if (s != null && Tools.isInteger(s)) {
			step.setEnterpriceId(Integer.parseInt(s));
			material.setEnterpriceId(step.getEnterpriceId());
			product.setEnterpriceId(step.getEnterpriceId());
			pfill.setEnterpriceId(step.getEnterpriceId());
		}

		product.setProductSerial(request.getParameter("productSerial"));
		product.setProductName(request.getParameter("productName"));
		product.setRemark(request.getParameter("remark"));
		product.setUnit(request.getParameter("unit"));
		s = request.getParameter("productId");
		if (s != null && Tools.isInteger(s))
			product.setId(Integer.parseInt(s));
		s = request.getParameter("trade1");
		if (s != null && Tools.isInteger(s))
			product.setTrade1(Integer.parseInt(s));
		s = request.getParameter("trade2");
		if (s != null && Tools.isInteger(s))
			product.setTrade2(Integer.parseInt(s));
		s = request.getParameter("trade3");
		if (s != null && Tools.isInteger(s))
			product.setTrade3(Integer.parseInt(s));
		s = request.getParameter("trade4");
		if (s != null && Tools.isInteger(s))
			product.setTrade4(Integer.parseInt(s));
		s = request.getParameter("groupid");
		if (s != null && Tools.isInteger(s))
			product.setGroupid(Integer.parseInt(s));

		s = request.getParameter("deviceId");
		if (s != null && Tools.isInteger(s))
			deviceid = Integer.parseInt(s);

		material.setMaterialSerial(request.getParameter("materialSerial"));
		material.setMaterialName(request.getParameter("materialName"));
		material.setUnit(request.getParameter("unit"));
		material.setRemark(request.getParameter("remark"));
		s = request.getParameter("materialId");
		if (s != null && Tools.isInteger(s))
			material.setId(Integer.parseInt(s));

		pfill.setFillTime(request.getParameter("fillTime"));
		pfill.setFuelunit(request.getParameter("fuelunit"));
		s = request.getParameter("productId");
		if (s != null && Tools.isInteger(s))
			pfill.setProductId(Integer.parseInt(s));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			pfill.setFillyear(Integer.parseInt(s));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			pfill.setStatus(Integer.parseInt(s));
		s = request.getParameter("fuelId");
		if (s != null && Tools.isInteger(s))
			pfill.setFuelId(Integer.parseInt(s));
		s = request.getParameter("fuelValue");
		if (s != null && Tools.isNumeric(s))
			pfill.setFuelValue(Double.parseDouble(s));

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			pfill.setId(Integer.parseInt(s));
		s = request.getParameter("hoursPerDay");
		if (s != null && Tools.isNumeric(s))
			pfill.setHoursPerDay(Double.parseDouble(s));
		s = request.getParameter("daysPerYear");
		if (s != null && Tools.isNumeric(s))
			pfill.setDaysPerYear(Double.parseDouble(s));
		s = request.getParameter("designOutput");
		if (s != null && Tools.isNumeric(s))
			pfill.setDesignOutput(Double.parseDouble(s));
		s = request.getParameter("realOutput");
		if (s != null && Tools.isNumeric(s))
			pfill.setRealOutput(Double.parseDouble(s));
		s = request.getParameter("m1");
		if (s != null && Tools.isNumeric(s))
			pfill.setM1(Double.parseDouble(s));
		s = request.getParameter("m2");
		if (s != null && Tools.isNumeric(s))
			pfill.setM2(Double.parseDouble(s));
		s = request.getParameter("m3");
		if (s != null && Tools.isNumeric(s))
			pfill.setM3(Double.parseDouble(s));
		s = request.getParameter("m4");
		if (s != null && Tools.isNumeric(s))
			pfill.setM4(Double.parseDouble(s));
		s = request.getParameter("m5");
		if (s != null && Tools.isNumeric(s))
			pfill.setM5(Double.parseDouble(s));
		s = request.getParameter("m6");
		if (s != null && Tools.isNumeric(s))
			pfill.setM6(Double.parseDouble(s));
		s = request.getParameter("m7");
		if (s != null && Tools.isNumeric(s))
			pfill.setM7(Double.parseDouble(s));
		s = request.getParameter("m8");
		if (s != null && Tools.isNumeric(s))
			pfill.setM8(Double.parseDouble(s));
		s = request.getParameter("m9");
		if (s != null && Tools.isNumeric(s))
			pfill.setM9(Double.parseDouble(s));
		s = request.getParameter("m10");
		if (s != null && Tools.isNumeric(s))
			pfill.setM10(Double.parseDouble(s));
		s = request.getParameter("m11");
		if (s != null && Tools.isNumeric(s))
			pfill.setM11(Double.parseDouble(s));
		s = request.getParameter("m12");
		if (s != null && Tools.isNumeric(s))
			pfill.setM12(Double.parseDouble(s));

		if (pfill.getRealOutput() <= 0)
			pfill.setRealOutput(ifnull.ifNullDouble(pfill.getM1())
					+ ifnull.ifNullDouble(pfill.getM2())
					+ ifnull.ifNullDouble(pfill.getM3())
					+ ifnull.ifNullDouble(pfill.getM4())
					+ ifnull.ifNullDouble(pfill.getM5())
					+ ifnull.ifNullDouble(pfill.getM6())
					+ ifnull.ifNullDouble(pfill.getM7())
					+ ifnull.ifNullDouble(pfill.getM8())
					+ ifnull.ifNullDouble(pfill.getM9())
					+ ifnull.ifNullDouble(pfill.getM10())
					+ ifnull.ifNullDouble(pfill.getM11())
					+ ifnull.ifNullDouble(pfill.getM12()));

		mfill.setFillTime(pfill.getFillTime());
		mfill.setFillyear(pfill.getFillyear());
		mfill.setMaterialId(material.getId());
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			mfill.setStatus(Integer.parseInt(s));
		s = request.getParameter("consumeOfYear");
		if (s != null && Tools.isNumeric(s))
			mfill.setConsumeOfYear(Double.parseDouble(s));
		mfill.setUnit(request.getParameter("unit"));
	}

	@RequestMapping(value = "/get/{fillyear}/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getStep(HttpServletRequest request,
			@PathVariable int fillyear, @PathVariable int companyId) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (companyId <= 0)
				return result.setStatus(-2, "no company id.");
			companyid = companyId;
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<ProduceStep> slist = stepService.getByCompany(fillyear,
					companyid);

			List<HashMap<String, Object>> stepList = new ArrayList<HashMap<String, Object>>();

			for (ProduceStep step : slist) {
				HashMap<String, Object> mStep = new HashMap<String, Object>();
				List<HashMap<String, Object>> dList = new ArrayList<HashMap<String, Object>>();

				mStep.put("stepId", step.getId());
				mStep.put("enterpriceId", step.getEnterpriceId());
				mStep.put("stepSerial", step.getStepSerial());
				mStep.put("stepName", step.getStepName());
				mStep.put("enabled", step.getEnabled());
				mStep.put("mainDevices", step.getMainDevices());

				List<Devices> dl = deviceService.getByStepId(step.getId());
				if (dl != null) {
					for (Devices d : dl) {
						HashMap<String, Object> mDevice = new HashMap<String, Object>();
						mDevice.put("deviceid", d.getId());
						mDevice.put("deviceSerial", d.getDeviceSerial());
						mDevice.put("deviceName", d.getDeviceName());
						dList.add(mDevice);
					}
					mStep.put("devices", dList);
				}

				stepList.add(mStep);
			}

			result.put("steps", stepList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/product/get", method = RequestMethod.GET)
	@ResponseBody
	public Object getProducts(HttpServletRequest request) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			companyid = 0;
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<Product> slist;
			if (companyid == 0)
				slist = productService.getAll();
			else
				slist = productService.getByCompany(companyid);
			result.put("products", slist);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/material/get", method = RequestMethod.GET)
	@ResponseBody
	public Object getMaterial(HttpServletRequest request) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (step.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = step.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<Material> slist = materialService.getByCompany(companyid);
			result.put("material", slist);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addStep(HttpServletRequest request) {

		check(request);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			step.setEnterpriceId(c.getId());
		} else if (loginManage.isUserLogin(request)) {
			if (step.getId() <= 0)
				return result.setStatus(-3, "no step id.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			if (step.getId() > 0)
				stepService.update(step);
			else {
				stepService.add(step);
			}

			result.put("lastid", step.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "product/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addProduct(HttpServletRequest request) {

		check(request);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			product.setEnterpriceId(c.getId());
		} else if (loginManage.isUserLogin(request)) {
			if (product.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (product.getId() > 0)
				productService.update(product);
			else {
				List<Product> list=productService.getByName(product);
				if(list.size()==0){
					productService.add(product);
					productService.updatePro(product);
					result.put("id", product.getId());
				}else{
					return result.setStatus(-3, "添加产品名称重复！");
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "material/update", method = RequestMethod.POST)
	@ResponseBody
	public Object materialUpdate(HttpServletRequest request) {

		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-2, "请登陆！");
		}

		check(request);

		if (material.getId() > 0) {
			materialService.update(material);
			return result.setStatus(0, "ok");
		} else {
			return result.setStatus(-2, "无原料id");
		}

	}

	@RequestMapping(value = "material/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addMaterial(HttpServletRequest request) {

		check(request);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			material.setEnterpriceId(c.getId());
		} else if (loginManage.isUserLogin(request)) {
			if (material.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		if (material.getMaterialName().equals("")) {
			return result.setStatus(-2, "原辅料名称不能为空！");
		}
		try {

			if (materialService.getMaterial(material.getEnterpriceId(),
					material.getMaterialName()) != null) {
				materialService.update(material);
			} else {
				materialService.add(material);
				result.put("lastid", material.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "product/filladdup", method = RequestMethod.POST)
	@ResponseBody
	public Object fillProduct(HttpServletRequest request) {

		check(request);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			product.setEnterpriceId(c.getId());
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			Calendar cal = Calendar.getInstance();

			pfill.setEnterpriceId(product.getEnterpriceId());
			pfill.setProductId(product.getId());
			pfill.setProduceStepId(step.getId());
			pfill.setFillTime(Tools.ToDateStr(cal));

			if (product.getId() > 0 && step.getId() > 0) {
				List<ProductFill> pf = pfillService.getById(product.getId(),
						step.getId());
				if (!pf.isEmpty()) {
					for (ProductFill p : pf) {
						if (p.getFillyear() == pfill.getFillyear()
								&& p.getId() != pfill.getId()) {
							return result.setStatus(-6, "本工段对应产品的上报记录已存在");
						}
					}
				}
				product.setProductName(null);
				productService.update(product);
			}

			if (pfill.getId() > 0) {
				if (pfill.getStatus() < 1)
					pfill.setStatus(WorkFlowController.STATUS_FILL);
				pfillService.update(pfill);
			} else {
				pfillService.add(pfill);
			}
			result.put("id", pfill.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "error");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "material/filladdup", method = RequestMethod.POST)
	@ResponseBody
	public Object fillMaterial(HttpServletRequest request) {

		check(request);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			material.setEnterpriceId(c.getId());
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (pfill.getId() <= 0 || material.getId() <= 0)
				return result.setStatus(-4,
						"must have productfillid and materialid.");

			Calendar cal = Calendar.getInstance();
			MaterialFill mtfill = null;
			List<MaterialFill> mlist = mfillService.getByMaterialId(material
					.getId());
			for (MaterialFill mf : mlist) {
				if (mf.getProductFillId() == pfill.getId()) {
					mtfill = mf;
					mfill.setId(mf.getId());
					break;
				}
			}

			ProductFill p = pfillService.getById1(pfill.getId());
			mfill.setProductFillId(pfill.getId());
			mfill.setMaterialId(material.getId());
			mfill.setFillyear(p.getFillyear());
			mfill.setFillTime(Tools.ToDateStr(cal));
			if (mfill.getStatus() < 1)
				mfill.setStatus(WorkFlowController.STATUS_FILL);
			if (mtfill != null)
				mfillService.update(mfill);
			else
				mfillService.add(mfill);

			result.put("id", mfill.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "product/filldelete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteProduct(HttpServletRequest request) {

		check(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (product.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = product.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (pfill.getId() <= 0)
				return result.setStatus(-2, "must have product fill id.");

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "material/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteMaterial(HttpServletRequest request) {

		check(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (product.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = product.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (pfill.getId() <= 0 || material.getId() <= 0)
				return result.setStatus(-4,
						"must have materialid, product fill id.");

			MaterialFill mff = null;
			List<MaterialFill> mlist = mfillService.getByProductFillId(
					pfill.getId(), pfill.getFillyear());
			for (MaterialFill mf : mlist) {
				if (mf.getMaterialId() == material.getId()) {
					mff = mf;
					break;
				}
			}

			if (mff == null)
				return result.setStatus(-3, "no relation");

			mfillService.delete(mff);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "device/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addDevice(HttpServletRequest request) {

		check(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (product.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = product.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (deviceid <= 0 || pfill.getId() <= 0)
				return result.setStatus(-2,
						"must have deviceid and product fill id.");

			Devices device = deviceService.getBydevId(deviceid);
			if (device == null)
				return result.setStatus(-3, "device not exist.");

			if (device.getEnterpriceId() != companyid)
				return result.setStatus(-3,
						"deviceid are not belong your company.");

			device.setProduceStepId(pfill.getId());
			deviceService.update(device);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-1, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "device/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteDevice(HttpServletRequest request) {

		check(request);

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (product.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = product.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			if (deviceid <= 0 || pfill.getId() <= 0)
				return result.setStatus(-2,
						"must have deviceid and product fill id.");

			Devices device = deviceService.getBydevId(deviceid);
			if (device == null)
				return result.setStatus(-3, "device not exist.");

			if (device.getEnterpriceId() != companyid)
				return result.setStatus(-3,
						"deviceid are not belong your company.");

			device.setProduceStepId(0);
			deviceService.update(device);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/company/{id}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByCompanyId(@PathVariable int id,
			@PathVariable int fillyear, HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			if (c.getId() != id)
				return result.setStatus(-2, "not your company.");
		} else if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<HashMap<String, Object>> stepList = getCuryearCompany(
					fillyear, id);

			result.put("steplist", stepList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/fixed/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getFixed(HttpServletRequest request,
			@PathVariable int fillyear) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (material.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = material.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<HashMap<String, Object>> stepList = getCuryearCompany(
					fillyear, companyid);

			result.put("steplist", stepList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	private List<HashMap<String, Object>> getCuryearCompany(int fillyear,
			int companyid) {
		List<ProductFill> plist = pfillService.getByYear(fillyear, companyid);
		List<HashMap<String, Object>> stepList = new ArrayList<HashMap<String, Object>>();

		for (ProductFill pf : plist) {
			HashMap<String, Object> stepfill = setStepFill(pf, fillyear);
			
			stepList.add(stepfill);
		}
		return stepList;
	}

	@RequestMapping(value = "/fill/{fillyear}/{fillid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCurrentFill(@PathVariable int fillyear,
			@PathVariable int fillid, HttpServletRequest request) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (material.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = material.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			ProductFill pfill = pfillService.getById1(fillid);
			HashMap<String, Object> stepfill = setStepFill(pfill, fillyear);

			result.put("data", stepfill);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/fill/history/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getHistoryFill(@PathVariable int year,
			HttpServletRequest request) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (material.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = material.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {

			List<ProductFill> plist = pfillService.getByYear(year, companyid);
			List<HashMap<String, Object>> stepList = new ArrayList<HashMap<String, Object>>();

			for (ProductFill pf : plist) {
				HashMap<String, Object> stepfill = setStepFill(pf, year);
				stepList.add(stepfill);
			}

			result.put("historyfill", stepList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	private HashMap<String, Object> setStepFill(ProductFill pf, int fillyear) {

		DecimalFormat df = new DecimalFormat("#.00");
		ProduceStep step = stepService.getById(pf.getProduceStepId(), fillyear);

		HashMap<String, Object> mStep = new HashMap<String, Object>();
		HashMap<String, Object> mProduct = new HashMap<String, Object>();
		List<HashMap<String, Object>> mList = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> dList = new ArrayList<HashMap<String, Object>>();

		mStep.put("id", pf.getId());
		mStep.put("stepId", step.getId());
		mStep.put("enterpriceId", step.getEnterpriceId());
		mStep.put("stepSerial", step.getStepSerial());
		mStep.put("stepName", step.getStepName());
		mStep.put("techChart", step.getTechChart());
		mStep.put("techDesp", step.getTechDesp());
		mStep.put("remark", step.getRemark());
		mStep.put("status", pf.getStatus());
		mStep.put("mainDevices", step.getMainDevices());
		mStep.put("fillTime", pf.getFillTime());
		mStep.put("fillyear", pf.getFillyear());
		mStep.put("enable", step.getEnabled());
		mStep.put("fuleName", pf.getFuelName());
		mStep.put("fuleunit", pf.getFuelunit());
		mStep.put("fuleValue", pf.getFuelValue());

		Product p;
		if (pf.getProductId() > 0) {
			p = productService.getById(pf.getProductId());
		} else {
			p = new Product();
		}
		if (p != null) {
			mProduct.put("productId", p.getId());
			mProduct.put("productSerial", p.getProductSerial());
			mProduct.put("productName", p.getProductName());
			mProduct.put("unit", p.getUnit());
			mProduct.put("remark", p.getRemark());
		} else {
			mProduct.put("productId", null);
			mProduct.put("productSerial", null);
			mProduct.put("productName", null);
			mProduct.put("unit", null);
			mProduct.put("remark", null);
		}
		mProduct.put("hoursPerDay", pf.getHoursPerDay());
		mProduct.put("daysPerYear", pf.getDaysPerYear());
		mProduct.put("designOutput", pf.getDesignOutput());
		mProduct.put("realOutput", df.format(pf.getRealOutput()));
		mProduct.put("m1", pf.getM1());
		mProduct.put("m2", pf.getM2());
		mProduct.put("m3", pf.getM3());
		mProduct.put("m4", pf.getM4());
		mProduct.put("m5", pf.getM5());
		mProduct.put("m6", pf.getM6());
		mProduct.put("m7", pf.getM7());
		mProduct.put("m8", pf.getM8());
		mProduct.put("m9", pf.getM9());
		mProduct.put("m10", pf.getM10());
		mProduct.put("m11", pf.getM11());
		mProduct.put("m12", pf.getM12());
		mStep.put("product", mProduct);

		List<MaterialFill> ml = mfillService.getByProductFillId(pf.getId(),
				fillyear);
		if (ml != null) {
			for (MaterialFill mf : ml) {

				Material m = materialService.getById(mf.getMaterialId());
				if (m == null)
					continue;

				HashMap<String, Object> mMaterial = new HashMap<String, Object>();
				mMaterial.put("id", mf.getId());
				mMaterial.put("materialId", mf.getMaterialId());
				mMaterial.put("materialSerial", m.getMaterialSerial());
				mMaterial.put("materialName", m.getMaterialName());
				mMaterial.put("remark", m.getRemark());
				mMaterial.put("consumeOfYear", mf.getConsumeOfYear());
				mMaterial.put("unit", mf.getUnit());
				mMaterial.put("status", mf.getStatus());
				mList.add(mMaterial);
			}
			mStep.put("material", mList);
		}

		List<Devices> dl = deviceService.getByStep(step.getId(), fillyear);
		if (dl != null) {
			for (Devices d : dl) {
				HashMap<String, Object> mDevice = new HashMap<String, Object>();
				mDevice.put("deviceId", d.getId());
				mDevice.put("deviceSerial", d.getDeviceSerial());
				mDevice.put("deviceName", d.getDeviceName());
				dList.add(mDevice);
			}
			mStep.put("devices", dList);
		}

		return mStep;
	}

	@RequestMapping(value = "/deletegd/{stepid}/{pfillid}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable int stepid, @PathVariable int pfillid,
			HttpServletRequest request) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();

			if (stepid <= 0 || pfillid <= 0) {
				return result.setStatus(-1, "没有stepid或pfillid");
			}
		} else if (loginManage.isUserLogin(request)) {
			if (stepid <= 0 || pfillid <= 0) {
				return result.setStatus(-1, "没有stepid或pfillid");
			}
		} else {
			return result.setStatus(-1, "No login.");
		}

		if (stepid > 0 && pfillid > 0) {
			pfillService.deletepfill(pfillid);
			mfillService.deletepfill(pfillid);
		} else {
			return result.setStatus(-1, "没有stepid或pfillid");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/fill/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCurrent(@PathVariable int fillyear,
			HttpServletRequest request) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (material.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = material.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			List<HashMap<String, Object>> stepList = new ArrayList<HashMap<String, Object>>();
			List<ProductFill> pfill = pfillService.getByYear(fillyear,
					companyid);
			for (ProductFill pf : pfill) {
				HashMap<String, Object> mStep = new LinkedHashMap<String, Object>();
				mStep.put("id", pf.getId());
				Product p;
				if (pf.getProductId() > 0) {
					p = productService.getById(pf.getProductId());
				} else {
					p = new Product();
				}
				if (p != null) {
					mStep.put("productId", p.getId());
					mStep.put("productName", p.getProductName());
					mStep.put("productValue", pf.getRealOutput());
					mStep.put("unit", p.getUnit());
				} else {
					mStep.put("productId", null);
					mStep.put("productName", null);
					mStep.put("productValue", null);
					mStep.put("unit", null);
				}

				List<MaterialFill> ml = mfillService.getByProductFillId(
						pf.getId(), fillyear);

				Material m = new Material();
				if (ml.size() > 0)
					m = materialService.getById(ml.get(0).getMaterialId());
				if (m != null) {
					mStep.put("materialId", m.getId());
					mStep.put("materialName", m.getMaterialName());
				} else {
					mStep.put("materialId", null);
					mStep.put("materialName", null);
				}
				mStep.put("fuleName", pf.getFuelName());
				mStep.put("fuleunit", pf.getFuelunit());
				mStep.put("fuleValue", pf.getFuelValue());
				mStep.put("status", pf.getStatus());
				stepList.add(mStep);
			}
			result.put("data", stepList);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "small/filladdup", method = RequestMethod.POST)
	@ResponseBody
	public Object smallAdd(HttpServletRequest request) {

		check(request);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			product.setEnterpriceId(c.getId());
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			Calendar cal = Calendar.getInstance();
			pfill.setEnterpriceId(product.getEnterpriceId());
			pfill.setProductId(product.getId());
			pfill.setProduceStepId(step.getId());
			pfill.setFillTime(Tools.ToDateStr(cal));

			if (product.getId() > 0) {
				// List<ProductFill> pf = pfillService.getById(product.getId(),
				// step.getId());
				// if (!pf.isEmpty()) {
				// for (ProductFill p : pf) {
				// if (p.getFillyear() == pfill.getFillyear()
				// && p.getId() != pfill.getId()) {
				// return result.setStatus(-6, "本工段对应产品的上报记录已存在");
				// }
				// }
				// }
				product.setProductName(null);
				productService.update(product);
			}

			if (pfill.getStatus() < 1)
				pfill.setStatus(WorkFlowController.STATUS_FILL);

			if (pfill.getId() > 0) {
				pfillService.update(pfill);
			} else {
				pfillService.add(pfill);
			}

			if (pfill.getId() <= 0 || material.getId() <= 0)
				return result.setStatus(-4,
						"must have productfillid and materialid.");

			MaterialFill mtfill = null;
			List<MaterialFill> mlist = mfillService.getByProductFillId(
					pfill.getId(), pfill.getFillyear());
			if (mlist.size()>0) {
				mtfill = mlist.get(0);
				mfill.setId(mlist.get(0).getId());
			}

			ProductFill p = pfillService.getById1(pfill.getId());
			mfill.setProductFillId(pfill.getId());
			mfill.setMaterialId(material.getId());
			mfill.setFillyear(p.getFillyear());
			mfill.setFillTime(Tools.ToDateStr(cal));

			if (mfill.getStatus() < 1)
				mfill.setStatus(WorkFlowController.STATUS_FILL);

			if (mtfill != null)
				mfillService.update(mfill);
			else
				mfillService.add(mfill);

			result.put("id", pfill.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "error");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Object smalldelete(HttpServletRequest request) {

		check(request);

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			product.setEnterpriceId(c.getId());
		} else if (loginManage.isUserLogin(request)) {
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			pfillService.deletepfill(pfill.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "删除失败");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/fill/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getbyId(@PathVariable int id, HttpServletRequest request) {

		int companyid;
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			if (material.getEnterpriceId() <= 0)
				return result.setStatus(-3, "no company id.");
			companyid = material.getEnterpriceId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		try {
			// List<HashMap<String, Object>> stepList = new
			// ArrayList<HashMap<String, Object>>();
			ProductFill pf = pfillService.getById1(id);
			HashMap<String, Object> mStep = new LinkedHashMap<String, Object>();
			mStep.put("id", pf.getId());
			Product p;
			if (pf.getProductId() > 0) {
				p = productService.getById(pf.getProductId());
			} else {
				p = new Product();
			}
			if (p != null) {
				mStep.put("productId", p.getId());
				mStep.put("productName", p.getProductName());
				mStep.put("productValue", pf.getRealOutput());
				mStep.put("unit", p.getUnit());
			} else {
				mStep.put("productId", null);
				mStep.put("productName", null);
				mStep.put("productValue", null);
				mStep.put("unit", null);
			}

			List<MaterialFill> ml = mfillService.getByProductFillId(pf.getId(),
					pf.getFillyear());

			Material m = new Material();
			if (ml.size() > 0)
				m = materialService.getById(ml.get(0).getMaterialId());
			if (m != null) {
				mStep.put("materialId", m.getId());
				mStep.put("materialName", m.getMaterialName());
			} else {
				mStep.put("materialId", null);
				mStep.put("materialName", null);
			}
			mStep.put("fuleId", pf.getFuelId());
			mStep.put("fuleName", pf.getFuelName());
			mStep.put("fuleunit", pf.getFuelunit());
			mStep.put("fuleValue", pf.getFuelValue());
			mStep.put("status", pf.getStatus());
			// stepList.add(mStep);
			result.put("data", mStep);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
}
