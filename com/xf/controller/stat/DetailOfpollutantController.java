package com.xf.controller.stat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.entity.Condition;
import com.xf.security.LoginManage;
import com.xf.service.ConfigService;
import com.xf.service.stat.DetailOfpollutantService;
import com.xf.vo.Conditions;
import com.xf.vo.Details;

@Scope("prototype")
@Controller
@RequestMapping(value = "/details")
public class DetailOfpollutantController {
	@Autowired
	private DetailOfpollutantService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private ConfigService configService;

	private Object checkAccount(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	@RequestMapping(value = "/getdetails", method = RequestMethod.POST)
	@ResponseBody
	private Object getDetails(@RequestBody Conditions conditions,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			Condition condition = new Condition();
			if (conditions != null) {
				condition.setProvince(conditions.getDistrictId1());
				condition.setCityid(conditions.getDistrictId2());
				condition.setTownid(conditions.getDistrictId3());
				condition.setTradeid1(conditions.getTradeId1());
				condition.setTradeid2(conditions.getTradeId2());
				condition.setTradeid3(conditions.getTradeId3());
				condition.setTradeid4(conditions.getTradeId4());
				if (conditions.getCompanyName()!=null)
					condition.setCompanyName(conditions.getCompanyName());
				if (conditions.getDeviceSerial()!=null)
					condition.setDeviceSerial(conditions.getDeviceSerial());
				if (conditions.getProductName()!=null)
					condition.setProductName(conditions.getProductName());
				condition.setPollutantId(conditions.getPollutantId());
				condition.setFillyear(conditions.getFillyear());
				condition.setMonth(conditions.getMonth());
			}

			List<HashMap<String, Object>> detaiList = new ArrayList<HashMap<String, Object>>();
			List<Details> dlist = new ArrayList<Details>();

			HashMap<String, Object> detaPany = new HashMap<String, Object>();
			if (condition != null) {
				if ((condition.getProductName() != null && condition
						.getDeviceSerial() == null)
						|| (condition.getProductName() != null && condition
								.getDeviceSerial() != null)
						|| (condition.getProductName() == null && condition
								.getDeviceSerial() == null)) {
					List<HashMap<String, Object>> prList = new ArrayList<HashMap<String, Object>>();
					List<Details> depro = theService
							.getDetailProduct(condition);
					if (depro.size() > 0) {
						for (Details dep : depro) {
							HashMap<String, Object> depPany = new HashMap<String, Object>();
							depPany.put("districtName2", dep.getDistrictName1()+" "+dep.getDistrictName2()+" "+dep.getDistrictName3());
							depPany.put("tradeName", dep.getTradeName());
							depPany.put("companyName", dep.getCompanyName());
							depPany.put("productName", dep.getProductName());
							depPany.put("pollutantName", dep.getPollutantName());
							depPany.put("fuelName", dep.getFuelName());
							depPany.put("materialName", dep.getMaterialName());
							depPany.put("month", dep.getMonth());
							depPany.put("statvalue", dep.getStatvalue());
							depPany.put("exp", dep.getExp());
							depPany.put("factor", dep.getFactor());
							depPany.put("valtype", dep.getValtype());
							depPany.put("value", dep.getValue());
							depPany.put("dsrate", dep.getDsrate());
							prList.add(depPany);
							dlist.add(dep);
						}
					}
					detaPany.put("products", prList);
				}
				if ((condition.getProductName() == null && condition
						.getDeviceSerial() != null)
						|| (condition.getProductName() != null && condition
								.getDeviceSerial() != null)
						|| (condition.getProductName() == null && condition
								.getDeviceSerial() == null)) {
					List<HashMap<String, Object>> deList = new ArrayList<HashMap<String, Object>>();
					List<Details> depoev = theService
							.getDetailDevice(condition);
					if (depoev.size() > 0) {
						for (Details depo : depoev) {
							HashMap<String, Object> depoPany = new HashMap<String, Object>();
							depoPany.put("districtName2",
									depo.getDistrictName1()+" "+depo.getDistrictName2()+" "+depo.getDistrictName3());
							depoPany.put("tradeName", depo.getTradeName());
							depoPany.put("companyName", depo.getCompanyName());
							depoPany.put("deviceName", depo.getDeviceName());
							depoPany.put("pollutantName",
									depo.getPollutantName());
							depoPany.put("fuelName", depo.getFuelName());
							depoPany.put("month", depo.getMonth());
							depoPany.put("statvalue", depo.getStatvalue());
							depoPany.put("exp", depo.getExp());
							depoPany.put("factor", depo.getFactor());
							depoPany.put("valtype", depo.getValtype());
							depoPany.put("value", depo.getValue());
							depoPany.put("dsrate", depo.getDsrate());
							deList.add(depoPany);
							dlist.add(depo);
						}
					} 
					detaPany.put("devices", deList);
				}
			}
			detaiList.add(detaPany);
			export(dlist);
			result.put("details", detaiList);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	@SuppressWarnings("deprecation")
	public void export(List<Details> list) {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序列号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("地址(省、市、州)");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("地址(市、州、盟)");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("地址(区、市、旗)");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("行业");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("行业代码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("公司");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("经度");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("纬度");
		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("产品");
		cell.setCellStyle(style);
		cell = row.createCell((short) 10);
		cell.setCellValue("设备");
		cell.setCellStyle(style);
		cell = row.createCell((short) 11);
		cell.setCellValue("燃料");
		cell.setCellStyle(style);
		cell = row.createCell((short) 12);
		cell.setCellValue("原料");
		cell.setCellStyle(style);
		cell = row.createCell((short) 13);
		cell.setCellValue("污染物类型");
		cell.setCellStyle(style);
		cell = row.createCell((short) 14);
		cell.setCellValue("污染物排放量");
		cell.setCellStyle(style);
		cell = row.createCell((short) 15);
		cell.setCellValue("单位");
		cell.setCellStyle(style);
		cell = row.createCell((short) 16);
		cell.setCellValue("年份");
		cell.setCellStyle(style);
		cell = row.createCell((short) 17);
		cell.setCellValue("月份");
		cell.setCellStyle(style);

		String s = new String();
		int j = 0;
		double b=0;
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Details stu = list.get(i);
			row.createCell((short) 0).setCellValue(i + 1);
			s = stu.getDistrictName1();
			if (s != null)
				row.createCell((short) 1).setCellValue(s);
			s = stu.getDistrictName2();
			if (s != null)
				row.createCell((short) 2).setCellValue(s);
			s = stu.getDistrictName3();
			if (s != null)
				row.createCell((short) 3).setCellValue(s);
			s = stu.getTradeName();
			if (s != null)
				row.createCell((short) 4).setCellValue(s);
			s = stu.getTradeNo();
			if (s != null)
				row.createCell((short) 5).setCellValue(s);
			s = stu.getCompanyName();
			if (s != null)
				row.createCell((short) 6).setCellValue(s);
			b = stu.getE_point();
				row.createCell((short) 7).setCellValue(b);
			b = stu.getN_point();
				row.createCell((short) 8).setCellValue(b);
			s = stu.getProductName();
			if (s != null)
				row.createCell((short) 9).setCellValue(s);
			s = stu.getDeviceName();
			if (s != null)
				row.createCell((short) 10).setCellValue(s);
			s = stu.getFuelName();
			if (s != null)
				row.createCell((short) 11).setCellValue(s);
			s = stu.getMaterialName();
			if (s != null)
				row.createCell((short) 12).setCellValue(s);
			s = stu.getPollutantName();
			if (s != null)
				row.createCell((short) 13).setCellValue(s);
			b = stu.getStatvalue();
			if (b != 0) {
				row.createCell((short) 14).setCellValue(b);
				row.createCell((short) 15).setCellValue("吨");
			}
			j = stu.getFillyear();
			if (j != 0)
				row.createCell((short) 16).setCellValue(j);
			j = stu.getMonth();
			if (j != 0)
				row.createCell((short) 17).setCellValue(j);
		}

		try {
			System.out.println(System.getProperty("user.dir"));
			String fileName = "污染物排放详细信息统计表.xls";
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			File fp = new File(path);
			if (!fp.exists()) {
				fp.mkdirs();// 目录不存在的情况下，创建目录。
			}
			
			path +="/"+fileName;
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/excelport")
	@ResponseBody
	private ResponseEntity<byte[]> download() {
		try {
			String fileName = "污染物排放详细信息统计表.xls";
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			path +="/"+fileName;
			
			File file = new File(path);
			HttpHeaders headers = new HttpHeaders();
			String dfileName = new String(fileName.getBytes("UTF-8"),
					"iso-8859-1");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", dfileName);
			return new ResponseEntity(FileUtils.readFileToByteArray(file),
					headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
