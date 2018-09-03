package com.xf.controller.gov;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.MyDispatcherServlet;

import com.xf.controller.ResultObj;
import com.xf.dao.IImportExcelDao;
import com.xf.entity.Company;
import com.xf.entity.District;
import com.xf.entity.Pollutant;
import com.xf.entity.Static;
import com.xf.entity.gov.AnimalsFarm;
import com.xf.entity.gov.AnimalsWild;
import com.xf.entity.gov.CanyinCertified;
import com.xf.entity.gov.CanyinNocert;
import com.xf.entity.gov.CanyinStat;
import com.xf.entity.gov.DumpField;
import com.xf.entity.gov.FileUpload;
import com.xf.entity.gov.Gasstation;
import com.xf.entity.gov.Gknumber;
import com.xf.entity.gov.Industry;
import com.xf.entity.gov.MotorVehicle;
import com.xf.entity.gov.MotorVehicleDb;
import com.xf.entity.gov.Oildepot;
import com.xf.entity.gov.Plane;
import com.xf.entity.gov.VehicleAction;
import com.xf.entity.gov.VehicleFactor;
import com.xf.entity.gov.VehicleStandard;
import com.xf.readexcel.ReadExcelAnimalsFarm;
import com.xf.readexcel.ReadExcelAnimalsWild;
import com.xf.readexcel.ReadExcelCanyinStat;
import com.xf.readexcel.ReadExcelCert;
import com.xf.readexcel.ReadExcelGasstation;
import com.xf.readexcel.ReadExcelGknumber;
import com.xf.readexcel.ReadExcelIndustry;
import com.xf.readexcel.ReadExcelLandfill;
import com.xf.readexcel.ReadExcelMotor;
import com.xf.readexcel.ReadExcelNocert;
import com.xf.readexcel.ReadExcelOildepot;
import com.xf.readexcel.ReadExcelPlane;
import com.xf.readexcel.ReadExcelSmallCompany;
import com.xf.readexcel.ReadExcelSmallCompanyThread;
import com.xf.readexcel.ReadExcelVehicl;
import com.xf.readexcel.ReadExcelVehiclfactor;
import com.xf.security.LoginManage;
import com.xf.security.SqlSwitch;
import com.xf.security.Tools;
import com.xf.service.CompanyService;
import com.xf.service.ConfigService;
import com.xf.service.DistrictService;
import com.xf.service.PollutantService;
import com.xf.service.StaticService;
import com.xf.service.gov.FileUploadService;
import com.xf.service.gov.MotorVehicleService;
import com.xf.service.stat.MotorStandartService;
import com.xf.vo.StatProd;

@Scope("prototype")
@Controller
@RequestMapping(value = "/excelup")
public class FileUploadController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private FileUploadService theService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private IImportExcelDao excelService;
	@Autowired
	private DistrictService disservice;
	@Autowired
	private PollutantService pollutantService;
	@Autowired
	private MotorVehicleService mvservice;
	@Autowired
	private MotorStandartService motorService;
	@Autowired
	private ReadExcelSmallCompany readExcelSmallCompany;

	ReadExcelGknumber readGknumber = new ReadExcelGknumber();
	ReadExcelIndustry readIndustry = new ReadExcelIndustry();
	ReadExcelAnimalsFarm readAnimalsFarm = new ReadExcelAnimalsFarm();
	ReadExcelOildepot readOildepot = new ReadExcelOildepot();
	ReadExcelGasstation readGasstation = new ReadExcelGasstation();
	ReadExcelLandfill readExcelLandfill = new ReadExcelLandfill();
	ReadExcelVehiclfactor readeFactor = new ReadExcelVehiclfactor();
	ReadExcelMotor readmoto = new ReadExcelMotor();

	FileUpload fileupload = new FileUpload();

	private int accountid;

	private Object checkd(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	private Object checkAccount(HttpServletRequest request) {
		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			accountid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			String s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s)) {
				accountid = Integer.parseInt(s);
			} else
				return result.setStatus(-1, "need accountid.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	public Map<String, Integer> getmap(int citynum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<District> list = excelService.findByCity(citynum);
		for (District dis : list) {
			map.put(dis.getDistrictName(), dis.getId());
		}
		return map;
	}

	public Map<String, Integer> getType() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Static> list = staticService.getType();
		for (Static sta : list) {
			map.put(sta.getName(), sta.getId());
		}
		return map;
	}

	@RequestMapping(value = "/upload1", method = RequestMethod.POST)
	@ResponseBody
	private Object upload1(HttpServletRequest request,
			HttpServletResponse response) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			path += "/checked/";
			createPath(path);

			String fillyear = request.getParameter("fillyear");
			if (fillyear != null && Tools.isInteger(fillyear))
				fileupload.setFillyear(Integer.parseInt(fillyear));
			String connect = request.getParameter("food");
			String tabletype = request.getParameter("tabletype");
			if (tabletype == null || tabletype.isEmpty())
				return result.setStatus(-3, "need tabletype");

			fileupload.setAccountid(accountid);
			fileupload.setTabletype(tabletype);
			fileupload = theService.findFile(fileupload);
			String fileName = getfileName(request, "filename");
			if (fileName.contains(".xls") || fileName.contains(".xlsx")) {
				fileUpload(request, path, "filename", accountid, fillyear);
				if (fileupload != null) {
					if (!fileName.equals(fileupload.getCheckFile())) {
						deleteFile(path, fillyear + "_" + accountid + "_" + fileupload.getCheckFile());
					}
					fileupload.setImported(0);// 未下载标记为0
					fileupload.setImportFile(null);
					fileupload.setCheckFile(fileName);
					theService.updatecheck(fileupload);
				} else {
					FileUpload fileupload = new FileUpload();
					fileupload.setFillyear(Integer.parseInt(fillyear));
					fileupload.setCheckFile(fileName);
					fileupload.setAccountid(accountid);
					fileupload.setImported(0);// 未下载标记为0
					fileupload.setTabletype(tabletype);
					theService.add(fileupload);
				}
			}
			response.sendRedirect(connect);
		} catch (Exception e) {
			String connect = request.getParameter("food");
			try {
				response.sendRedirect(connect);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	@ResponseBody
	private Object upload2(HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		String fileName = "";

		try {
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			path += "/import/";
			createPath(path);

			String year = request.getParameter("fillyear");
			int fillyear = Integer.parseInt(year);
			String tabletype = request.getParameter("tabletype");
			if (tabletype == null || tabletype.isEmpty())
				return result.setStatus(-3, "need tabletype");
			fileUpload(request, path, "import", accountid, year);
			fileName = getfileName(request, "import");
			String importExcel = path + fillyear + "_" + accountid + "_"
					+ fileName;
			// 开始读取Excel表

			Company company = companyService.getById(accountid);
			int citynum = company.getCity();
			if (tabletype.equals("ap_canyin_nocert")) {
				// 先删除数据，再做插入

				List<CanyinNocert> list = ReadExcelNocert
						.readExcel(importExcel);
				if(citynum==0){
					for (CanyinNocert canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getShopaddr()));
						if(getCity(canyin.getShopaddr())==0){
							canyin.setCity(getCity(canyin.getHomeaddr()));
						}
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
					}
				}else{
					for (CanyinNocert canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setCity(citynum);
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
					}
				}
				
				excelService.deleteCanyinNoCert(accountid, fillyear);
				int count = 0;
				List<CanyinNocert> newlist = new ArrayList<CanyinNocert>();
				for (CanyinNocert sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addCanyinNocert(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addCanyinNocert(newlist);
				}
			}
			if (tabletype.equals("ap_canyin_certified")) {
				// 先删除数据，再做插入
				excelService.deleteCanyinCert(accountid, fillyear);
				List<CanyinCertified> list = ReadExcelCert
						.readExcel(importExcel);
				if(citynum==0){
					for (CanyinCertified canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getStoreaddr()));
						if(getCity(canyin.getStoreaddr())==0){
							canyin.setCity(getCity(canyin.getCertifiedGov()));
						}
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
					}
				}else{
					for (CanyinCertified canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setCity(citynum);
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
					}
				}
				
				excelService.deleteCanyinCert(accountid, fillyear);
				int count = 0;
				List<CanyinCertified> newlist = new ArrayList<CanyinCertified>();
				for (CanyinCertified sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addCanyinCert(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addCanyinCert(newlist);
				}
			}

			if (tabletype.equals("ap_canyin_stat")) {
				// 先删除数据，再做插入

				List<CanyinStat> list = ReadExcelCanyinStat
						.readExcel(importExcel);
				if(citynum==0){
					for (CanyinStat canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getCityName()));
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(getCity(canyin.getCityName()));
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}else{
					for (CanyinStat canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setCity(citynum);
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(citynum);
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteCanyinStat(accountid, fillyear);
				int count = 0;
				List<CanyinStat> newlist = new ArrayList<CanyinStat>();
				for (CanyinStat sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addCanyinStat(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addCanyinStat(newlist);
				}
			}

			if (tabletype.equals("ap_animals_wild")) {
				// 先删除数据，再做插入

				List<AnimalsWild> list = ReadExcelAnimalsWild
						.readExcel(importExcel);
				if(citynum==0){
					for (AnimalsWild canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getCityName()));
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(getCity(canyin.getCityName()));
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}else{
					for (AnimalsWild canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setCity(citynum);
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(citynum);
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteAnimalsWild(accountid, fillyear);
				int count = 0;
				List<AnimalsWild> newlist = new ArrayList<AnimalsWild>();
				for (AnimalsWild sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addAnimalsWild(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addAnimalsWild(newlist);
				}
			}

			if (tabletype.equals("ap_animals_farm")) {
				// 先删除数据，再做插入

				List<AnimalsFarm> list = readAnimalsFarm.readExcel(importExcel,
						citynum);
				if(citynum==0){
					for (AnimalsFarm canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getCityName()));
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(getCity(canyin.getCityName()));
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}else{
					for (AnimalsFarm canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(citynum);
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteAnimalsFarm(accountid, fillyear);
				int count = 0;
				List<AnimalsFarm> newlist = new ArrayList<AnimalsFarm>();
				for (AnimalsFarm sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addAnimalsFarm(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addAnimalsFarm(newlist);
				}
			}

			if (tabletype.equals("ap_industry")) {
				// 先删除数据，再做插入

				List<Industry> list = readIndustry.readExcel(importExcel,
						citynum);
				if(citynum==0){
					for (Industry canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getCityName()));
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(getCity(canyin.getCityName()));
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}else{
					for (Industry canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(citynum);
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteIndustry(accountid, fillyear);
				int count = 0;
				List<Industry> newlist = new ArrayList<Industry>();
				for (Industry sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addInbatch(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addInbatch(newlist);
				}
			}

			if (tabletype.equals("ap_gknumber")) {
				// 先删除数据，再做插入

				List<Gknumber> list = readGknumber.readExcel(importExcel,
						citynum);
				if(citynum==0){
					for (Gknumber canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getCityName()));
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);

						String town = canyin.getTownName();
						String gktype = canyin.getGktypeName();
						String fuelType = canyin.getFuelTypeName();

						Map<String, Integer> sta = getType();
						Map<String, Integer> map = getmap(getCity(canyin.getCityName()));

						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}

						for (Map.Entry<String, Integer> entry : sta.entrySet()) {
							if (entry.getKey().equals(gktype)) {
								canyin.setGktype(entry.getValue());
							}
							if (entry.getKey().equals(fuelType)) {
								canyin.setFuelType(entry.getValue());
							}
						}
					}
				}else{
					for (Gknumber canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);

						String town = canyin.getTownName();
						String gktype = canyin.getGktypeName();
						String fuelType = canyin.getFuelTypeName();

						Map<String, Integer> sta = getType();
						Map<String, Integer> map = getmap(citynum);

						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}

						for (Map.Entry<String, Integer> entry : sta.entrySet()) {
							if (entry.getKey().equals(gktype)) {
								canyin.setGktype(entry.getValue());
							}
							if (entry.getKey().equals(fuelType)) {
								canyin.setFuelType(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteGknumber(accountid, fillyear);
				int count = 0;
				List<Gknumber> newlist = new ArrayList<Gknumber>();
				for (Gknumber sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addGkbatch(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addGkbatch(newlist);
				}
			}

			if (tabletype.equals("ap_gasstation")) {
				// 先删除数据，再做插入

				List<Gasstation> list = readGasstation.readExcel(importExcel,
						citynum);
				if(citynum==0){
					for (Gasstation canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getCityName()));
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(getCity(canyin.getCityName()));
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}else{
					for (Gasstation canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(citynum);
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteGasstation(accountid, fillyear);
				int count = 0;
				List<Gasstation> newlist = new ArrayList<Gasstation>();
				for (Gasstation sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addGasstation(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addGasstation(newlist);
				}
			}

			if (tabletype.equals("ap_oildepot")) {
				// 先删除数据，再做插入

				List<Oildepot> list = readOildepot.readExcel(importExcel,
						citynum);
				if(citynum==0){
					for (Oildepot canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(1);
						canyin.setCity(getCity(canyin.getCityName()));
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(getCity(canyin.getCityName()));
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}else{
					for (Oildepot canyin : list) {
						canyin.setAccountid(accountid);
						canyin.setProvince(company.getProvince());
						canyin.setFillyear(fillyear);
						canyin.setStatus(3);
						canyin.setImportflag(2);
						String town = canyin.getTownName();
						Map<String, Integer> map = getmap(citynum);
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								canyin.setTown(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteOilepot(accountid, fillyear);
				int count = 0;
				List<Oildepot> newlist = new ArrayList<Oildepot>();
				for (Oildepot sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addOildepot(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addOildepot(newlist);
				}
			}

			if (tabletype.equals("ap_plane")) {
				// 先删除数据，再做插入

				List<Plane> list = ReadExcelPlane.readExcel(importExcel);
				for (Plane plane : list) {
					plane.setAccountid(accountid);
					plane.setProvince(company.getProvince());
					plane.setFillyear(fillyear);
					plane.setStatus(3);
					plane.setImportflag(2);
				}
				excelService.deletePlane(accountid, fillyear);
				int count = 0;
				List<Plane> newlist = new ArrayList<Plane>();
				for (Plane sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addPlane(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addPlane(newlist);
				}
			}

			if (tabletype.equals("ap_vehicle_action")) {
				// 先删除数据，再做插入
				List<VehicleAction> list = ReadExcelVehicl
						.readExcel(importExcel);
				for (VehicleAction vehi : list) {
					vehi.setAccountid(accountid);
					vehi.setProvince(company.getProvince());
					vehi.setCity(company.getCity());
					vehi.setTown(company.getTown());
					vehi.setFillyear(fillyear);
					vehi.setStatus(3);
					vehi.setImportflag(2);
				}
				excelService.deleteVehicl(accountid, fillyear);
				int count = 0;
				List<VehicleAction> newlist = new ArrayList<VehicleAction>();
				for (VehicleAction sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addVehicl(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addVehicl(newlist);
				}

				// 更新刚刚插入的数据
				List<VehicleAction> reslist = motorService.getMotorByAccountid(
						accountid, fillyear);
				List<VehicleStandard> statlist = motorService.getAllStandard();

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					for (VehicleAction va : reslist) {
						Date dt1 = df.parse(va.getRegisterdate());
						Date dt2 = df.parse(va.getCheckdate());

						long mills = dt2.getTime() - dt1.getTime();
						double day = mills / (1000 * 60 * 60 * 24);
						double years = day / 365;
						double mile = 0;
						if (years > 0) {
							mile = va.getMileage() / years;
						}
						va.setAvgmile(mile);
						for (VehicleStandard vs : statlist) {
							Date dt3 = df.parse(vs.getStartdate());
							Date dt4 = df.parse(vs.getEnddate());
							if (va.getVehiclemodel() == vs.getVehiclemodel()
									&& dt1.getTime() <= dt4.getTime()
									&& dt1.getTime() >= dt3.getTime()
									&& va.getGastype().equals(vs.getGastype())) {
								va.setStandard(vs.getStandard());
							}
						}
					}
					int count2 = 0;
					List<VehicleAction> newlist2 = new ArrayList<VehicleAction>();
					for (VehicleAction sp : reslist) {
						if (count2 > 0 && count2 % 500 == 0) {
							newlist2.add(sp);
							motorService.updateStandard(newlist2);
							newlist2.clear();
						} else {
							newlist2.add(sp);
						}
						count2++;
					}
					if (newlist2.size() > 0) {
						motorService.updateStandard(newlist2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (tabletype.equals("ap_dump_field")) {
				// 先删除数据，再做插入

				List<DumpField> list = ReadExcelLandfill.readExcel(importExcel);
				if(citynum==0){
					for (DumpField dump : list) {
						dump.setAccountid(accountid);
						dump.setProvince(1);
						dump.setCity(getCity(dump.getCityName()));
						dump.setFillyear(fillyear);
						dump.setStatus(3);
						dump.setImportflag(2);
						dump.setCity(citynum);
						String town = dump.getTownName();
						Map<String, Integer> map = getmap(getCity(dump.getCityName()));
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								dump.setTown(entry.getValue());
							}
						}
					}
				}else{
					for (DumpField dump : list) {
						dump.setAccountid(accountid);
						dump.setProvince(company.getProvince());
						dump.setFillyear(fillyear);
						dump.setStatus(3);
						dump.setImportflag(2);
						dump.setCity(citynum);
						String town = dump.getTownName();
						Map<String, Integer> map = getmap(citynum);
						for (Map.Entry<String, Integer> entry : map.entrySet()) {
							if (entry.getKey().equals(town)) {
								dump.setTown(entry.getValue());
							}
						}
					}
				}
				
				excelService.deleteDumpField(accountid, fillyear);
				int count = 0;
				List<DumpField> newlist = new ArrayList<DumpField>();
				for (DumpField sp : list) {
					if (count > 0 && count % 500 == 0) {
						newlist.add(sp);
						excelService.addDumpField(newlist);
						newlist.clear();
					} else {
						newlist.add(sp);
					}
					count++;
				}
				if (newlist.size() > 0) {
					excelService.addDumpField(newlist);
				}
			}

			fileupload.setImportFile(fileName);
			fileupload.setAccountid(accountid);
			fileupload.setTabletype(tabletype);
			fileupload.setFillyear(fillyear);
			fileupload.setImported(2);// 已导入标记为2
			theService.update(fileupload);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, e.getMessage());
		}

		return result.setStatus(0, fileName);

	}

	@RequestMapping(value = "/findFile", method = RequestMethod.POST)
	@ResponseBody
	private Object Fileuploadfind(HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			String s = request.getParameter("fillyear");
			int fillyear = Integer.parseInt(s);
			String tabletype = request.getParameter("tabletype");

			fileupload.setAccountid(accountid);
			fileupload.setTabletype(tabletype);
			fileupload.setFillyear(fillyear);
			FileUpload find = theService.findFile(fileupload);
			result.put("findname", find);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/fileName/{name}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	private Object Fileupfind(@PathVariable String name,
			@PathVariable int fillyear, HttpServletRequest request) {
		Object ret = checkd(request);
		if (ret != null)
			return ret;

		try {
			accountid = 0;
			fileupload.setAccountid(accountid);
			fileupload.setTabletype(name);
			fileupload.setFillyear(fillyear);
			FileUpload find = theService.findFile(fileupload);
			result.put("findname", find);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/vehicleFactor", method = RequestMethod.POST)
	@ResponseBody
	private Object sdsds(HttpServletRequest request,
			HttpServletResponse response) {

		Object ret = checkAccount(request);
		try {
			String fileName = "";
//			accountid = 0;
			String s = request.getParameter("fillyear");
			int fillyear = Integer.parseInt(s);
//			Calendar now = Calendar.getInstance();
//			int fillyear =now.get(Calendar.YEAR);
//			int fillyear =0;
			String connect = request.getParameter("motor");
			String tabletype = request.getParameter("tabletype");
			if (tabletype == null || tabletype.isEmpty())
				return result.setStatus(-3, "need tabletype");

			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			path += "/vehicle/";
			createPath(path);
			fileUpload(request, path, "vehicle", accountid, fillyear+"");
			fileName = getfileName(request, "vehicle");
			String importExcel = path + fillyear + "_" + accountid + "_"
					+ fileName;

			if (tabletype.equals("ap_vehicle_factor")) {
				HashSet<String> set = new HashSet<String>();
				List<Static> ser = staticService.getBygroupid(38);
				List<VehicleFactor> list = readeFactor.readExcel(importExcel);
				if (list != null) {
					for (VehicleFactor vlist : list) {
						set.add(vlist.getPollutant());
					}
				}
				List<VehicleFactor> velist = new ArrayList<VehicleFactor>();
				for (String str : set) {
					for (Static srr : ser) {
						if (list != null) {
							for (VehicleFactor vlist : list) {
								if (str.equals(vlist.getPollutant())
										&& srr.getName().equals(
												vlist.getStandard())) {
									Pollutant poll = pollutantService
											.getByName(str);
									int pollutantid = 0;
									if (poll != null) {
										pollutantid = poll.getId();
									}
									int staticId = srr.getId();
									for (int i = 0; i < 34; i++) {
										VehicleFactor vehi = new VehicleFactor();
										vehi.setPollutantId(pollutantid);
										vehi.setStandardId(staticId);
										if (i == 0) {
											vehi.setVehiclemodel(29001);
											vehi.setFactor(vlist
													.getGuestmini_rentgas());
										}
										if (i == 1) {
											vehi.setVehiclemodel(29002);
											vehi.setFactor(vlist
													.getGuestmini_rentrest());
										}
										if (i == 2) {
											vehi.setVehiclemodel(29003);
											vehi.setFactor(vlist
													.getGuestmini_restgas());
										}
										if (i == 3) {
											vehi.setVehiclemodel(29004);
											vehi.setFactor(vlist
													.getGuestmini_restrest());
										}
										if (i == 4) {
											vehi.setVehiclemodel(29005);
											vehi.setFactor(vlist
													.getGuestsmall_rentgas());
										}
										if (i == 5) {
											vehi.setVehiclemodel(29006);
											vehi.setFactor(vlist
													.getGuestsmall_rentdiesel());
										}
										if (i == 6) {
											vehi.setVehiclemodel(29007);
											vehi.setFactor(vlist
													.getGuestsmall_rentrest());
										}
										if (i == 7) {
											vehi.setVehiclemodel(29008);
											vehi.setFactor(vlist
													.getGuestsmall_restgas());
										}
										if (i == 8) {
											vehi.setVehiclemodel(29009);
											vehi.setFactor(vlist
													.getGuestsmall_restdiesel());
										}
										if (i == 9) {
											vehi.setVehiclemodel(29010);
											vehi.setFactor(vlist
													.getGuestsmall_restrest());
										}
										if (i == 10) {
											vehi.setVehiclemodel(29011);
											vehi.setFactor(vlist
													.getGuestmiddle_busgas());
										}
										if (i == 11) {
											vehi.setVehiclemodel(29012);
											vehi.setFactor(vlist
													.getGuestmiddle_busdiesel());
										}
										if (i == 12) {
											vehi.setVehiclemodel(29013);
											vehi.setFactor(vlist
													.getGuestmiddle_busrest());
										}
										if (i == 13) {
											vehi.setVehiclemodel(29014);
											vehi.setFactor(vlist
													.getGuestmiddle_restgas());
										}
										if (i == 14) {
											vehi.setVehiclemodel(29015);
											vehi.setFactor(vlist
													.getGuestmiddle_restdiesel());
										}
										if (i == 15) {
											vehi.setVehiclemodel(29016);
											vehi.setFactor(vlist
													.getGuestmiddle_restrest());
										}
										if (i == 16) {
											vehi.setVehiclemodel(29017);
											vehi.setFactor(vlist
													.getGuestlarge_busgas());
										}
										if (i == 17) {
											vehi.setVehiclemodel(29018);
											vehi.setFactor(vlist
													.getGuestlarge_busdiesel());
										}
										if (i == 18) {
											vehi.setVehiclemodel(29019);
											vehi.setFactor(vlist
													.getGuestlarge_busrest());
										}
										if (i == 19) {
											vehi.setVehiclemodel(29020);
											vehi.setFactor(vlist
													.getGuestlarge_restgas());
										}
										if (i == 20) {
											vehi.setVehiclemodel(29021);
											vehi.setFactor(vlist
													.getGuestlarge_restdiesel());
										}
										if (i == 21) {
											vehi.setVehiclemodel(29022);
											vehi.setFactor(vlist
													.getGuestlarge_restrest());
										}
										if (i == 22) {
											vehi.setVehiclemodel(29023);
											vehi.setFactor(vlist
													.getGoodsmini_gas());
										}
										if (i == 23) {
											vehi.setVehiclemodel(29024);
											vehi.setFactor(vlist
													.getGoodsmini_diesel());
										}
										if (i == 24) {
											vehi.setVehiclemodel(29025);
											vehi.setFactor(vlist
													.getGoodssmall_gas());
										}
										if (i == 25) {
											vehi.setVehiclemodel(29026);
											vehi.setFactor(vlist
													.getGoodssmall_diesel());
										}
										if (i == 26) {
											vehi.setVehiclemodel(29027);
											vehi.setFactor(vlist
													.getGoodsmiddle_gas());
										}
										if (i == 27) {
											vehi.setVehiclemodel(29028);
											vehi.setFactor(vlist
													.getGoodsmiddle_diesel());
										}
										if (i == 28) {
											vehi.setVehiclemodel(29029);
											vehi.setFactor(vlist
													.getGoodslarge_gas());
										}
										if (i == 29) {
											vehi.setVehiclemodel(29030);
											vehi.setFactor(vlist
													.getGoodslarge_diesel());
										}
										if (i == 30) {
											vehi.setVehiclemodel(29031);
											vehi.setFactor(vlist.getTricycle());
										}
										if (i == 31) {
											vehi.setVehiclemodel(29032);
											vehi.setFactor(vlist.getGoodsslow());
										}
										if (i == 32) {
											vehi.setVehiclemodel(29033);
											vehi.setFactor(vlist
													.getMotorcycle_ordinary());
										}
										if (i == 33) {
											vehi.setVehiclemodel(29034);
											vehi.setFactor(vlist
													.getMotorcycle_light());
										}
										velist.add(vehi);
									}
								}
							}
						}
					}
				}

				excelService.deleteFactor();
				if (velist.size() > 5000) {
					int num = (velist.size() / 5000) + 1;
					for (int i = 0; i < num; i++) {
						if (i != (num - 1)) {
							List<VehicleFactor> list1 = velist.subList(
									5000 * i, 5000 * (i + 1) - 1);
							excelService.addFactor(list1);
						} else {
							List<VehicleFactor> list1 = velist.subList(
									5000 * i, velist.size() - 1);
							excelService.addFactor(list1);
						}
					}
				} else {
					excelService.addFactor(velist);
				}
			}

			if (tabletype.equals("ap_motor_vehicle")) {
				Company c=companyService.getById(accountid);
				String msg="";
				if(c!=null){
					if(c.getProvince()>0){
						msg=moto(importExcel,0);
					}else{
						msg=moto(importExcel,2);
					}
				}
				if(!msg.equals("ok")){
					return result.setStatus(-2, msg);
				}
				fileupload.setImportFile(fileName);
				fileupload.setAccountid(accountid);
				fileupload.setTabletype(tabletype);
				fileupload.setFillyear(fillyear);
				fileupload.setImported(2);// 已导入标记为2
				theService.update(fileupload);
				return result.setStatus(0, "");
			}

			if (tabletype.equals("ap_vehicle_update")) {
				String msg=addmoto(importExcel);
				if(!msg.equals("ok")){
					return result.setStatus(-2, msg);
				}
				fileupload.setImportFile(fileName);
				fileupload.setAccountid(accountid);
				fileupload.setTabletype(tabletype);
				fileupload.setFillyear(fillyear);
				fileupload.setImported(2);// 已导入标记为2
				theService.update(fileupload);
				return result.setStatus(0, "");
			}

			fileupload.setAccountid(accountid);
			fileupload.setTabletype(tabletype);
			fileupload.setImportFile(fileName);
			fileupload.setImported(2);
			fileupload.setFillyear(fillyear);

			FileUpload find = theService.findFile(fileupload);
			if (find != null)
				theService.updatecheck(fileupload);
			else
				theService.add(fileupload);

			result.put("findname", theService.findFile(fileupload));
			response.sendRedirect(connect);
		} catch (Exception e) {
			e.printStackTrace();
			String connect = request.getParameter("motor");
			try {
				response.sendRedirect(connect);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@SuppressWarnings("static-access")
	private String moto(String path,int tabletype) {
		try {
			List<MotorVehicle> list = readmoto.readExcel(path);
			if (!list.get(0).getVariation().equals("保有量"))
				return "导入表格不是基础数据表，请重新导入！";
			mvservice.deleteAll();
			List<MotorVehicleDb> listdb = new ArrayList<MotorVehicleDb>();
			for (MotorVehicle mv : list) {
				if(mv.getCityName() == null || mv.getCityName() == ""){
					break;
				}
				int cityid = 0;
				int standardid = 0;

				if (mv.getCityName().equals("成都市")) {
					cityid = 2;
				} else if (mv.getCityName().equals("自贡市")) {
					cityid = 338;
				} else if (mv.getCityName().equals("攀枝花市")) {
					cityid = 454;
				} else if (mv.getCityName().equals("泸州市")) {
					cityid = 520;
				} else if (mv.getCityName().equals("德阳市")) {
					cityid = 670;
				} else if (mv.getCityName().equals("绵阳市")) {
					cityid = 804;
				} else if (mv.getCityName().equals("广元市")) {
					cityid = 1109;
				} else if (mv.getCityName().equals("遂宁市")) {
					cityid = 1357;
				} else if (mv.getCityName().equals("内江市")) {
					cityid = 1489;
				} else if (mv.getCityName().equals("乐山市")) {
					cityid = 1616;
				} else if (mv.getCityName().equals("南充市")) {
					cityid = 1846;
				} else if (mv.getCityName().equals("眉山市")) {
					cityid = 2277;
				} else if (mv.getCityName().equals("宜宾市")) {
					cityid = 2415;
				} else if (mv.getCityName().equals("广安市")) {
					cityid = 2611;
				} else if (mv.getCityName().equals("达州市")) {
					cityid = 2798;
				} else if (mv.getCityName().equals("雅安市")) {
					cityid = 3119;
				} else if (mv.getCityName().equals("巴中市")) {
					cityid = 3281;
				} else if (mv.getCityName().equals("资阳市")) {
					cityid = 3481;
				} else if (mv.getCityName().equals("阿坝藏族羌族自治州")) {
					cityid = 3661;
				} else if (mv.getCityName().equals("甘孜藏族自治州")) {
					cityid = 3898;
				} else if (mv.getCityName().equals("凉山彝族自治州")) {
					cityid = 4243;
				}else{
					return "行政区划名称填写错误！";
				}

				if (mv.getStandard().equals("国0")) {
					standardid = 30001;
				} else if (mv.getStandard().equals("国1")) {
					standardid = 30002;
				} else if (mv.getStandard().equals("国2")) {
					standardid = 30003;
				} else if (mv.getStandard().equals("国3")) {
					standardid = 30004;
				} else if (mv.getStandard().equals("国4")) {
					standardid = 30005;
				} else if (mv.getStandard().equals("国5")) {
					standardid = 30006;
				}else{
					return "排放标准填写错误！";
				}
				for (int i = 0; i < 34; i++) {
					MotorVehicleDb mvdb = new MotorVehicleDb();
					mvdb.setDistrictId(mv.getAdministrationID());
					mvdb.setCity(cityid);
					mvdb.setCityName(mv.getCityName());
					mvdb.setStatyear(mv.getFillyear());
					mvdb.setStandard(standardid);
					mvdb.setVariation(mv.getVariation());
					if (i == 0) {
						mvdb.setVehiclemodel(29001);
						mvdb.setHoldings(mv.getGuestmini_rentgas());
					} else if (i == 1) {
						mvdb.setVehiclemodel(29002);
						mvdb.setHoldings(mv.getGuestmini_rentrest());
					} else if (i == 2) {
						mvdb.setVehiclemodel(29003);
						mvdb.setHoldings(mv.getGuestmini_restgas());
					} else if (i == 3) {
						mvdb.setVehiclemodel(29004);
						mvdb.setHoldings(mv.getGuestmini_restrest());
					} else if (i == 4) {
						mvdb.setVehiclemodel(29005);
						mvdb.setHoldings(mv.getGuestsmall_rentgas());
					} else if (i == 5) {
						mvdb.setVehiclemodel(29006);
						mvdb.setHoldings(mv.getGuestsmall_rentdiesel());
					} else if (i == 6) {
						mvdb.setVehiclemodel(29007);
						mvdb.setHoldings(mv.getGuestsmall_rentrest());
					} else if (i == 7) {
						mvdb.setVehiclemodel(29008);
						mvdb.setHoldings(mv.getGuestsmall_restgas());
					} else if (i == 8) {
						mvdb.setVehiclemodel(29009);
						mvdb.setHoldings(mv.getGuestsmall_restdiesel());
					} else if (i == 9) {
						mvdb.setVehiclemodel(29010);
						mvdb.setHoldings(mv.getGuestsmall_restrest());
					} else if (i == 10) {
						mvdb.setVehiclemodel(29011);
						mvdb.setHoldings(mv.getGuestmiddle_busgas());
					} else if (i == 11) {
						mvdb.setVehiclemodel(29012);
						mvdb.setHoldings(mv.getGuestmiddle_busdiesel());
					} else if (i == 12) {
						mvdb.setVehiclemodel(29013);
						mvdb.setHoldings(mv.getGuestmiddle_busrest());
					} else if (i == 13) {
						mvdb.setVehiclemodel(29014);
						mvdb.setHoldings(mv.getGuestmiddle_restgas());
					} else if (i == 14) {
						mvdb.setVehiclemodel(29015);
						mvdb.setHoldings(mv.getGuestmiddle_restdiesel());
					} else if (i == 15) {
						mvdb.setVehiclemodel(29016);
						mvdb.setHoldings(mv.getGuestmiddle_restrest());
					} else if (i == 16) {
						mvdb.setVehiclemodel(29017);
						mvdb.setHoldings(mv.getGuestlarge_busgas());
					} else if (i == 17) {
						mvdb.setVehiclemodel(29018);
						mvdb.setHoldings(mv.getGuestlarge_busdiesel());
					} else if (i == 18) {
						mvdb.setVehiclemodel(29019);
						mvdb.setHoldings(mv.getGuestlarge_busrest());
					} else if (i == 19) {
						mvdb.setVehiclemodel(29020);
						mvdb.setHoldings(mv.getGuestlarge_restgas());
					} else if (i == 20) {
						mvdb.setVehiclemodel(29021);
						mvdb.setHoldings(mv.getGuestlarge_restdiesel());
					} else if (i == 21) {
						mvdb.setVehiclemodel(29022);
						mvdb.setHoldings(mv.getGuestlarge_restrest());
					} else if (i == 22) {
						mvdb.setVehiclemodel(29023);
						mvdb.setHoldings(mv.getGoodsmini_gas());
					} else if (i == 23) {
						mvdb.setVehiclemodel(29024);
						mvdb.setHoldings(mv.getGoodsmini_diesel());
					} else if (i == 24) {
						mvdb.setVehiclemodel(29025);
						mvdb.setHoldings(mv.getGoodssmall_gas());
					} else if (i == 25) {
						mvdb.setVehiclemodel(29026);
						mvdb.setHoldings(mv.getGoodssmall_diesel());
					} else if (i == 26) {
						mvdb.setVehiclemodel(29027);
						mvdb.setHoldings(mv.getGoodsmiddle_gas());
					} else if (i == 27) {
						mvdb.setVehiclemodel(29028);
						mvdb.setHoldings(mv.getGoodsmiddle_diesel());
					} else if (i == 28) {
						mvdb.setVehiclemodel(29029);
						mvdb.setHoldings(mv.getGoodslarge_gas());
					} else if (i == 29) {
						mvdb.setVehiclemodel(29030);
						mvdb.setHoldings(mv.getGoodslarge_diesel());
					} else if (i == 30) {
						mvdb.setVehiclemodel(29031);
						mvdb.setHoldings(mv.getTricycle());
					} else if (i == 31) {
						mvdb.setVehiclemodel(29032);
						mvdb.setHoldings(mv.getGoodsslow());
					} else if (i == 32) {
						mvdb.setVehiclemodel(29033);
						mvdb.setHoldings(mv.getMotorcycle_ordinary());
					} else if (i == 33) {
						mvdb.setVehiclemodel(29034);
						mvdb.setHoldings(mv.getMotorcycle_light());
					}
					listdb.add(mvdb);

				}
			}
			if (listdb.size() > 5000) {
				int num = (listdb.size() / 5000) + 1;
				for (int i = 0; i < num; i++) {
					if (i != (num - 1)) {
						List<MotorVehicleDb> list1 = listdb.subList(5000 * i,
								5000 * (i + 1) - 1);
						if(tabletype==0){
							excelService.addMotor(list1);
						}else if(tabletype==2){
							excelService.addMotor2(list1);
						}
						
					} else {
						List<MotorVehicleDb> list1 = listdb.subList(5000 * i,
								listdb.size() - 1);
						if(tabletype==0){
							excelService.addMotor(list1);
						}else if(tabletype==2){
							excelService.addMotor2(list1);
						}
					}
				}
			} else {
				if(tabletype==0){
					excelService.addMotor(listdb);
				}else if(tabletype==2){
					excelService.addMotor2(listdb);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}

		return "ok";
	}

	private String addmoto(String path) {
		try {
			List<MotorVehicle> list = readmoto.readExcel(path);
			if (list.get(0).getVariation().equals("保有量"))
				return "导入表格不是更新数据表，请重新导入！";
			List<MotorVehicleDb> listdb = new ArrayList<MotorVehicleDb>();
			List<Integer> listyear = mvservice.getYears();
			if (listyear != null) {
				for (Integer yea : listyear) {
					if (yea == list.get(0).getFillyear())
						mvservice.deleteByYear(yea);
				}
			}

			int yearnow = list.get(0).getFillyear();
			int year = yearnow - 1;
			List<MotorVehicleDb> dblist = mvservice.getByYear(year);

			if (dblist.size() <= 0)
				return year + "年无数据，请先导入" + year + "年数据";

			for (MotorVehicleDb db : dblist) {

				String standardname = null;
				String cityname = null;

				if (db.getCity() == 2) {
					cityname = "成都市";
				} else if (db.getCity() == 338) {
					cityname = "自贡市";
				} else if (db.getCity() == 454) {
					cityname = "攀枝花市";
				} else if (db.getCity() == 520) {
					cityname = "泸州市";
				} else if (db.getCity() == 670) {
					cityname = "德阳市";
				} else if (db.getCity() == 804) {
					cityname = "绵阳市";
				} else if (db.getCity() == 1109) {
					cityname = "广元市";
				} else if (db.getCity() == 1357) {
					cityname = "遂宁市";
				} else if (db.getCity() == 1489) {
					cityname = "内江市";
				} else if (db.getCity() == 1616) {
					cityname = "乐山市";
				} else if (db.getCity() == 1846) {
					cityname = "南充市";
				} else if (db.getCity() == 2277) {
					cityname = "眉山市";
				} else if (db.getCity() == 2415) {
					cityname = "宜宾市";
				} else if (db.getCity() == 2611) {
					cityname = "广安市";
				} else if (db.getCity() == 2798) {
					cityname = "达州市";
				} else if (db.getCity() == 3119) {
					cityname = "雅安市";
				} else if (db.getCity() == 3281) {
					cityname = "巴中市";
				} else if (db.getCity() == 3481) {
					cityname = "资阳市";
				} else if (db.getCity() == 3661) {
					cityname = "阿坝藏族羌族自治州";
				} else if (db.getCity() == 3898) {
					cityname = "甘孜藏族自治州";
				} else if (db.getCity() == 4243) {
					cityname = "凉山彝族自治州";
				}

				if (db.getStandard() == 30001) {
					standardname = "国0";
				} else if (db.getStandard() == 30002) {
					standardname = "国1";
				} else if (db.getStandard() == 30003) {
					standardname = "国2";
				} else if (db.getStandard() == 30004) {
					standardname = "国3";
				} else if (db.getStandard() == 30005) {
					standardname = "国4";
				} else if (db.getStandard() == 30006) {
					standardname = "国5";
				}

				db.setStatyear(list.get(0).getFillyear());

				for (MotorVehicle mv : list) {
					if (cityname.equals(mv.getCityName())
							&& standardname.equals(mv.getStandard())) {
						if (mv.getGuestmini_rentgas() > 0
								&& db.getVehiclemodel() == 29001) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmini_rentgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmini_rentgas());

						} else if (mv.getGuestmini_rentrest() > 0
								&& db.getVehiclemodel() == 29002) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmini_rentrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmini_rentrest());

						} else if (mv.getGuestmini_restgas() > 0
								&& db.getVehiclemodel() == 29003) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmini_restgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmini_restgas());

						} else if (mv.getGuestmini_restrest() > 0
								&& db.getVehiclemodel() == 29004) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmini_restrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmini_restrest());

						} else if (mv.getGuestsmall_rentgas() > 0
								&& db.getVehiclemodel() == 29005) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestsmall_rentgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestsmall_rentgas());

						} else if (mv.getGuestsmall_rentdiesel() > 0
								&& db.getVehiclemodel() == 29006) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestsmall_rentdiesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestsmall_rentdiesel());

						} else if (mv.getGuestsmall_rentrest() > 0
								&& db.getVehiclemodel() == 29007) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestsmall_rentrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestsmall_rentrest());

						} else if (mv.getGuestsmall_restgas() > 0
								&& db.getVehiclemodel() == 29008) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestsmall_restgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestsmall_restgas());

						} else if (mv.getGuestsmall_restdiesel() > 0
								&& db.getVehiclemodel() == 29009) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestsmall_restdiesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestsmall_restdiesel());

						} else if (mv.getGuestsmall_restrest() > 0
								&& db.getVehiclemodel() == 29010) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestsmall_restrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestsmall_restrest());

						} else if (mv.getGuestmiddle_busgas() > 0
								&& db.getVehiclemodel() == 29011) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmiddle_busgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmiddle_busgas());

						} else if (mv.getGuestmiddle_busdiesel() > 0
								&& db.getVehiclemodel() == 29012) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmiddle_busdiesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmiddle_busdiesel());

						} else if (mv.getGuestmiddle_busrest() > 0
								&& db.getVehiclemodel() == 29013) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmiddle_busrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmiddle_busrest());

						} else if (mv.getGuestmiddle_restgas() > 0
								&& db.getVehiclemodel() == 29014) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmiddle_restgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmiddle_restgas());

						} else if (mv.getGuestmiddle_restdiesel() > 0
								&& db.getVehiclemodel() == 29015) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmiddle_restdiesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmiddle_restdiesel());

						} else if (mv.getGuestmiddle_restrest() > 0
								&& db.getVehiclemodel() == 29016) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestmiddle_restrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestmiddle_restrest());

						} else if (mv.getGuestlarge_busgas() > 0
								&& db.getVehiclemodel() == 29017) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestlarge_busgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestlarge_busgas());

						} else if (mv.getGuestlarge_busdiesel() > 0
								&& db.getVehiclemodel() == 29018) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestlarge_busdiesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestlarge_busdiesel());

						} else if (mv.getGuestlarge_busrest() > 0
								&& db.getVehiclemodel() == 29019) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestlarge_busrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestlarge_busrest());

						} else if (mv.getGuestlarge_restgas() > 0
								&& db.getVehiclemodel() == 29020) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestlarge_restgas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestlarge_restgas());

						} else if (mv.getGuestlarge_restdiesel() > 0
								&& db.getVehiclemodel() == 29021) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestlarge_restdiesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestlarge_restdiesel());

						} else if (mv.getGuestlarge_restrest() > 0
								&& db.getVehiclemodel() == 29022) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGuestlarge_restrest());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGuestlarge_restrest());

						} else if (mv.getGoodsmini_gas() > 0
								&& db.getVehiclemodel() == 29023) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodsmini_gas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodsmini_gas());

						} else if (mv.getGoodsmini_diesel() > 0
								&& db.getVehiclemodel() == 29024) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodsmini_diesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodsmini_diesel());

						} else if (mv.getGoodssmall_gas() > 0
								&& db.getVehiclemodel() == 29025) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodssmall_gas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodssmall_gas());

						} else if (mv.getGoodssmall_diesel() > 0
								&& db.getVehiclemodel() == 29026) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodssmall_diesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodssmall_diesel());

						} else if (mv.getGoodsmiddle_gas() > 0
								&& db.getVehiclemodel() == 29027) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodsmiddle_gas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodsmiddle_gas());

						} else if (mv.getGoodsmiddle_diesel() > 0
								&& db.getVehiclemodel() == 29028) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodsmiddle_diesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodsmiddle_diesel());

						} else if (mv.getGoodslarge_gas() > 0
								&& db.getVehiclemodel() == 29029) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodslarge_gas());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodslarge_gas());

						} else if (mv.getGoodslarge_diesel() > 0
								&& db.getVehiclemodel() == 29030) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodslarge_diesel());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodslarge_diesel());

						} else if (mv.getTricycle() > 0
								&& db.getVehiclemodel() == 29031) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getTricycle());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getTricycle());

						} else if (mv.getGoodsslow() > 0
								&& db.getVehiclemodel() == 29032) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getGoodsslow());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getGoodsslow());

						} else if (mv.getMotorcycle_ordinary() > 0
								&& db.getVehiclemodel() == 29033) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getMotorcycle_ordinary());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getMotorcycle_ordinary());

						} else if (mv.getMotorcycle_light() > 0
								&& db.getVehiclemodel() == 29034) {

							if (mv.getVariation().equals("新注册")
									|| mv.getVariation().equals("转入"))
								db.setHoldings(db.getHoldings()
										+ mv.getMotorcycle_light());
							if (mv.getVariation().equals("注销")
									|| mv.getVariation().equals("转出"))
								db.setHoldings(db.getHoldings()
										- mv.getMotorcycle_light());

						}
						// if (db.getHoldings() < 0) {
						// Static sta = staticService
						// .getById(db.getStandard());
						// Static sta2 = staticService.getById(db
						// .getVehiclemodel());
						// return result.setStatus(-2, db.getCityName() + " "
						// + sta2.getName() + " " + sta.getRemark()
						// + " 保有量已小于0");
						// }

						if (db.getHoldings() < 0) {
							db.setHoldings(0);
						}
					}
				}
				listdb.add(db);
			}

			if (listdb.size() > 5000) {
				int num = (listdb.size() / 5000) + 1;
				for (int i = 0; i < num; i++) {
					if (i != (num - 1)) {
						List<MotorVehicleDb> list1 = listdb.subList(5000 * i,
								5000 * (i + 1) - 1);
						excelService.addMotor(list1);
					} else {
						List<MotorVehicleDb> list1 = listdb.subList(5000 * i,
								listdb.size() - 1);
						excelService.addMotor(list1);
					}
				}
			} else {
				excelService.addMotor(listdb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}

		return "ok";

	}

	private static void createPath(String path) {
		File fp = new File(path);
		// 创建目录
		if (!fp.exists()) {
			fp.mkdirs();// 目录不存在的情况下，创建目录。
		}
	}

	private static void deleteFile(String path, String filename) {
		File file = new File(path);
		File Array[] = file.listFiles();
		for (File f : Array) {
			if (f.isFile()) {// 如果是文件
				if (f.getName().equals(filename)) {
					f.delete();
				}
			}
		}
	}

	private void fileUpload(HttpServletRequest request, String path,
			String name, int accountid, String fillyear)
			throws IllegalStateException, IOException {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile excel = ((MultipartHttpServletRequest) request)
					.getFile(name);
			String originFileName = excel.getOriginalFilename();
			if (excel != null && originFileName != null
					&& originFileName.length() > 0) {
				File file = new File(path + fillyear + "_" + accountid + "_"
						+ originFileName);
				excel.transferTo(file);
			}
		}
	}

	private String getfileName(HttpServletRequest request, String name) {
		MultipartFile excel = ((MultipartHttpServletRequest) request)
				.getFile(name);
		String originFileName = excel.getOriginalFilename();
		return originFileName;
	}

	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	@ResponseBody
	public Object clearData(HttpServletRequest request) {
		try {
			Object ret = checkAccount(request);
			if (ret != null)
				return ret;

			FileUpload file = new FileUpload();

			String tabletype = request.getParameter("tabletype");
			String year = request.getParameter("fillyear");
			int fillyear = Integer.parseInt(year);

			file.setAccountid(accountid);
			file.setFillyear(fillyear);
			file.setTabletype(tabletype);
			file.setImportFile(null);
			file.setImported(0);
			theService.update(file);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/smallCompany", method = RequestMethod.POST)
	@ResponseBody
	public Object smallCompany(HttpServletRequest request) {
		
		String fileName = "";
		try {
			
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			path += "/import/";
			createPath(path);

			String year = request.getParameter("fillyear");
			int fillyear = Integer.parseInt(year);
			System.out.println(fillyear);
			fileUpload(request, path, "import", 0, year);
			fileName = getfileName(request, "import");
			String importExcel = path + fillyear + "_" + 0 + "_"
					+ fileName;
			System.out.println(importExcel + "  " + fillyear);

			ReadExcelSmallCompanyThread thread = ReadExcelSmallCompanyThread
					.getRead(readExcelSmallCompany, importExcel, fillyear);

			 thread.call();
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, e.getMessage());
		}

		return result.setStatus(0,fileName);
	}
	
	public Integer getCity(String city){
		List<District> list=disservice.getByLevel(1);
		for(District d:list){
			if(d.getDistrictName().equals(city)||d.getDistrictName().contains(city)||city.contains(d.getDistrictName())){
				return d.getId();
			}
		}
		return 0;
	}
}
