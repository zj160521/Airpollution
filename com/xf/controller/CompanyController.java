package com.xf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Company;
import com.xf.entity.CompanyFill;
import com.xf.entity.CompanyVo;
import com.xf.entity.District;
import com.xf.entity.Role;
import com.xf.entity.Searchform;
import com.xf.entity.Trade;
import com.xf.entity.SysArea;
import com.xf.entity.User;
import com.xf.entity.VocsTrade;
import com.xf.security.LoginManage;
import com.xf.security.SeperateTable;
import com.xf.security.Tools;
import com.xf.service.CompanyFillService;
import com.xf.service.CompanyService;
import com.xf.service.ConfigService;
import com.xf.service.DistrictService;
import com.xf.service.InvitationCodeService;
import com.xf.service.TradeService;
import com.xf.vo.ListInteger;

@Scope("prototype")
@Controller
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyFillService cfillService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private InvitationCodeService codeService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private TradeService tradeService;
	private final String dis = "中国/";
	private Company checkCompany(HttpServletRequest request) {

		String s = new String();
		Company company = new Company();
		company.setName(request.getParameter("name"));
		company.setPassword(request.getParameter("password"));
		company.setCompanyName(request.getParameter("companyName"));
		company.setCompanySerial(request.getParameter("companySerial"));
		company.setContact(request.getParameter("contact"));
		company.setContactNo(request.getParameter("contactNo"));
		company.setLegalPerson(request.getParameter("legalPerson"));
		company.setLegalPersonPhone(request.getParameter("legalPersonPhone"));
		company.setCompletedTime(request.getParameter("completedTime"));
		company.setAddress(request.getParameter("address"));
		company.setRemark(request.getParameter("remark"));
		company.setCode(request.getParameter("code"));
		company.setCompanyCode(request.getParameter("companyCode"));
		company.setIndustrialPark(request.getParameter("industrialPark"));
		company.setBuildTime(request.getParameter("buildTime"));
		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			company.setId(Integer.parseInt(s));
		s = request.getParameter("trade1");
		if (s != null && Tools.isInteger(s))
			company.setTrade1(Integer.parseInt(s));
		s = request.getParameter("trade2");
		if (s != null && Tools.isInteger(s))
			company.setTrade2(Integer.parseInt(s));
		s = request.getParameter("trade3");
		if (s != null && Tools.isInteger(s))
			company.setTrade3(Integer.parseInt(s));
		s = request.getParameter("trade4");
		if (s != null && Tools.isInteger(s))
			company.setTrade4(Integer.parseInt(s));
		s = request.getParameter("province");
		if (s != null && Tools.isInteger(s))
			company.setProvince(Integer.parseInt(s));
		s = request.getParameter("city");
		if (s != null && Tools.isInteger(s))
			company.setCity(Integer.parseInt(s));
		s = request.getParameter("town");
		if (s != null && Tools.isInteger(s))
			company.setTown(Integer.parseInt(s));
		s = request.getParameter("country");
		if (s != null && Tools.isInteger(s))
			company.setCountry(Integer.parseInt(s));
		s = request.getParameter("street");
		if (s != null && Tools.isInteger(s))
			company.setStreet(Integer.parseInt(s));

		s = request.getParameter("e_point");
		if (s != null && Tools.isNumeric(s))
			company.setE_point(Double.parseDouble(s));
		s = request.getParameter("n_point");
		if (s != null && Tools.isNumeric(s))
			company.setN_point(Double.parseDouble(s));
		s = request.getParameter("typeid");
		if (s != null && Tools.isNumeric(s))
			company.setTypeid(Integer.parseInt(s));
		return company;
	}

	private CompanyFill check_fill(HttpServletRequest request) {
		String s = new String();
		CompanyFill cfill = new CompanyFill();
		cfill.setFillTime(request.getParameter("fillTime"));

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			cfill.setId(Integer.parseInt(s));
		s = request.getParameter("enterpriceId");
		if (s != null && Tools.isInteger(s))
			cfill.setEnterpriceId(Integer.parseInt(s));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			cfill.setFillyear(Integer.parseInt(s));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			cfill.setStatus(Integer.parseInt(s));

		s = request.getParameter("gdp");
		if (s != null && Tools.isNumeric(s))
			cfill.setGdp(Double.parseDouble(s));
		s = request.getParameter("daysOfWork");
		if (s != null && Tools.isNumeric(s))
			cfill.setDaysOfWork(Double.parseDouble(s));
		s = request.getParameter("hoursOfDay");
		if (s != null && Tools.isNumeric(s))
			cfill.setHoursOfDay(Double.parseDouble(s));
		s = request.getParameter("totalHour");
		if (s != null && Tools.isNumeric(s))
			cfill.setTotalHour(Double.parseDouble(s));
		s = request.getParameter("totalElec");
		if (s != null && Tools.isNumeric(s))
			cfill.setTotalElec(Double.parseDouble(s));
		return cfill;
	}

	@RequestMapping(value = "/isexist", method = RequestMethod.POST)
	@ResponseBody
	public Object isExist(@RequestBody Role role, HttpServletRequest request) {
		Company byName = companyService.getByName(role.getName());
		if(byName != null)
			return result.setStatus(1, "存在该用户！");
		else
			return result.setStatus(0, "不存在该用户！");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addCompany(HttpServletRequest request) {

		Company company = checkCompany(request);

		if (company.getName() == null || company.getName().isEmpty()
				|| company.getPassword() == null
				|| company.getPassword().isEmpty()
				|| company.getCompanyName() == null
				|| company.getCompanyName().isEmpty())
			return result.setStatus(-3, "请输入帐号名称、登录密码和公司名称");

		try {

			Company c = companyService.getByName(company.getName());
			if (c != null && c.getId() > 0) {
				return result.setStatus(-4, "帐号名已存在");
			}

			String concode = configService.get("invitation_code");
			String smallcode = configService.get("small_code");
			String province = configService.get("province");

			if (company.getCode() != null && !company.getCode().isEmpty()) {
				if (smallcode.equals(company.getCode())){
					company.setIsmall(1);
				}else if(province.equals(company.getCode())){
					if(company.getCity()>0){
						return result.setStatus(-3, "请点击注册省级帐号！");
					}
					if(company.getTypeid()>30){
						List<Company> list=companyService.getProvince(company.getTypeid());
						if(list.size()>0){
							return result.setStatus(-3, "该机构帐号已注册,帐号为：" + list.get(0).getName()+"。");
						}
					}
					company.setProvince(0);
					company.setCity(0);
				}else if(!province.equals(company.getCode())&&company.getCity()==0){
					return result.setStatus(-3, "邀请码错误");
				}
					
				if (!concode.equals(company.getCode())
						&& !smallcode.equals(company.getCode()) && !province.equals(company.getCode())) {
					return result.setStatus(-3, "邀请码错误");
				}
			} else {
				return result.setStatus(-3, "请输入邀请码");
			}

			if (company.getTypeid() < 3) {
				company.setTypeid(2);
			}
			company.setPassword(LoginManage.md5sum(company.getPassword()));
			companyService.add(company);
			result.put("code", company.getCode());

//			if(company.getTypeid()==2){
//				if(company.getProvince()>0&&(company.getCity()>0||company.getTown()>0)){
//					//地区查询
//					List<District> dislist=districtService.getAll();
//					String areaName="中国/";
//					if(company.getCity()>0&&company.getTown()>0){
//						
//						for(District d:dislist){
//							if(d.getId()==company.getProvince()){
//								areaName=areaName+d.getDistrictName()+"/";
//							}else if(d.getId()==company.getCity()){
//								areaName=areaName+d.getDistrictName()+"/";
//							}else if(d.getId()==company.getTown()){
//								areaName=areaName+d.getDistrictName()+"/";
//							}
//						}
//						
//						result.put("areaName", areaName);
//						System.out.println("areaName="+areaName);
//						result.put("area_id", getAreaId(areaName));
//					}else if(company.getCity()>0&&company.getTown()==0){
//						for(District d:dislist){
//							if(d.getId()==company.getProvince()){
//								areaName=areaName+d.getDistrictName()+"/";
//							}else if(d.getId()==company.getCity()){
//								areaName=areaName+d.getDistrictName()+"/";
//							}
//						}
//						result.put("areaName", areaName);
//						System.out.println("areaName="+areaName);
//						result.put("area_id",getAreaId(areaName));
//					}
//				}
//				
//				if(company.getTrade1()>0){
//					String tradeName="";
//					// 行业查询
//					List<Trade> tradelist = tradeService.getAll();
//					for(Trade t:tradelist){
//						if(company.getTrade1()==t.getId()){
//							tradeName=tradeName+t.getTradeName()+"/";
//						}
//					}
//					if(company.getTrade2()>0){
//						for(Trade t:tradelist){
//							if(company.getTrade2()==t.getId()){
//								tradeName=tradeName+t.getTradeName()+"/";
//							}
//						}
//						if(company.getTrade3()>0){
//							for(Trade t:tradelist){
//								if(company.getTrade3()==t.getId()){
//									tradeName=tradeName+t.getTradeName()+"/";
//								}
//							}
//							if(company.getTrade4()>0){
//								for(Trade t:tradelist){
//									if(company.getTrade4()==t.getId()){
//										tradeName=tradeName+t.getTradeName()+"/";
//									}
//								}
//							}
//						}
//					}
//					result.put("tradeName", tradeName);
//					System.out.println("tradeName="+tradeName);
//					result.put("trade_id", getTraId(tradeName));
//				}
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "add company account ok");

	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByName(@PathVariable String name,
			HttpServletRequest request) {

		try {

			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");

			Company c = companyService.getByName(name);
			if (c == null)
				return result.setStatus(1, "company not exist.");
			result.put("company", c);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {

		try {

			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");

			Company c = companyService.getById(id);
			if (c == null)
				return result.setStatus(1, "company not exist.");
			result.put("company", c);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public Object getAll(HttpServletRequest request
			,@RequestBody Searchform searchform) {

		try {
			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");
			
			User loginUser = loginManage.getLoginUser(request);
			
			Integer totalRecs = 0;
			List<CompanyVo> cList = new ArrayList<CompanyVo>();
			
			searchform.setId((searchform.getCur_page() - 1) * searchform.getPage_rows());
			if(loginUser.getRole_id() != 1){
				int area  = 0;
				if(loginUser.getTown() > 0)
					area = loginUser.getTown();
				else if(loginUser.getCity() > 0)
					area = loginUser.getCity();
				
				searchform.setAreaId(area);
			}
			
			totalRecs = companyService.getTotalRecs(searchform);
			cList = companyService.getAll2(searchform);
			
			for (CompanyVo c : cList) {
				c.setPassword("******");
				if(c.getCompletedTime() != null){
					String bTime = c.getCompletedTime().substring(0, 19);
					c.setCompletedTime(bTime);
				}
			}
			
			result.put("companylist", cList);
			result.put("totalRecs", totalRecs);
			result.put("Cur_page", searchform.getCur_page());
			result.put("Page_rows", searchform.getPage_rows());
			

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}
	
	@RequestMapping(value = "/getByAreaId", method = RequestMethod.POST)
	@ResponseBody
	public Object getByTown(@RequestBody Role role,HttpServletRequest request) {
		try {
			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");
			
			User loginUser = loginManage.getLoginUser(request);
			int area  = 0;
			if(loginUser.getTown() > 0)
				area = loginUser.getTown();
			else if(loginUser.getCity() > 0)
				area = loginUser.getCity();

			List<CompanyVo> list = new ArrayList<CompanyVo>();
			List<Company> list2 = new ArrayList<Company>();
			
			if(role.getId() > 0 && role.getName() != null && role.getName() != ""){
				List<CompanyVo> cList = companyService.getBoth(role);
				
				if(loginUser.getRole_id() != 1){
					for(CompanyVo cv : cList){
						cv.setPassword("******");
						if(cv.getCompletedTime() != null){
							String bTime = cv.getCompletedTime().substring(0, 19);
							cv.setCompletedTime(bTime);
						}
						
						if(cv.getCity() == area || cv.getTown() == area)
							list.add(cv);
					}
					cList = list;
				}else{
					for (CompanyVo c : cList) {
						c.setPassword("******");
						if(c.getCompletedTime() != null){
							String bTime = c.getCompletedTime().substring(0, 19);
							c.setCompletedTime(bTime);
						}
					}
				}
				result.put("companylist", cList);
			}else if(role.getId() > 0){
				List<CompanyVo> cList = companyService.getByAreaId(role.getId());
				
				if(loginUser.getRole_id() != 1){
					for(CompanyVo cv : cList){
						cv.setPassword("******");
						if(cv.getCompletedTime() != null){
							String bTime = cv.getCompletedTime().substring(0, 19);
							cv.setCompletedTime(bTime);
						}
						
						if(cv.getCity() == area || cv.getTown() == area)
							list.add(cv);
					}
					cList = list;
				}else{
					for (CompanyVo c : cList) {
						c.setPassword("******");
						if(c.getCompletedTime() != null){
							String bTime = c.getCompletedTime().substring(0, 19);
							c.setCompletedTime(bTime);
						}
					}
				}
				result.put("companylist", cList);
			}else if(role.getName() != null && role.getName() != ""){
				List<Company> cList2 = companyService.getByantistop(role.getName());
				
				if(loginUser.getRole_id() != 1){
					for(Company cv : cList2){
						cv.setPassword("******");
						if(cv.getCompletedTime() != null){
							String bTime = cv.getCompletedTime().substring(0, 19);
							cv.setCompletedTime(bTime);
						}
						
						if(cv.getCity() == area || cv.getTown() == area)
							list2.add(cv);
					}
					cList2 = list2;
				}else{
					for (Company c : cList2) {
						c.setPassword("******");
						if(c.getCompletedTime() != null){
							String bTime = c.getCompletedTime().substring(0, 19);
							c.setCompletedTime(bTime);
						}
					}
				}
				result.put("companylist", cList2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "ok");
	}
	
	@RequestMapping(value = "/updateByArea", method = RequestMethod.POST)
	@ResponseBody
	public Object updateByArea(@RequestBody ListInteger li,HttpServletRequest request) {
		try {
			if(li.getList() == null || li.getList().isEmpty())
				return result.setStatus(-2, "请选择账号！");
			li.setPwd(LoginManage.md5sum(li.getPwd()));
			companyService.updatePwd(li);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/antistop/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAntistop(HttpServletRequest request,
			@PathVariable String name) {

		try {

			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");

			List<Company> cList = companyService.getByantistop(name);
			for (Company c : cList) {
				c.setPassword("******");
			}
			result.put("companylist", cList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/gov", method = RequestMethod.GET)
	@ResponseBody
	public Object getGov(HttpServletRequest request) {

		try {

			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");

			List<Company> cList = companyService.getGov();
			for (Company c : cList) {
				c.setPassword("******");
			}
			result.put("govlist", cList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/gov/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object getGovby(HttpServletRequest request, @PathVariable String name) {

		try {

			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");

			List<Company> cList = companyService.getGovby(name);
			for (Company c : cList) {
				c.setPassword("******");
			}
			result.put("govlist", cList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/myself", method = RequestMethod.GET)
	@ResponseBody
	public Object getSelf(HttpServletRequest request) {

		try {

			if (!loginManage.isCompanyLogin(request))
				return result.setStatus(-1, "No login.");

			Company c = loginManage.getLoginCompany(request);
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			Company com = companyService.getById(c.getId());
			
//			if(c.getTypeid() == 2){
//				switchTradeArea(com);
//				com.setArea_id(getAreaId(com.getAreaName()));
//				com.setTrade_id(getTraId(com.getTradeName()));
//			}
			
			if (com != null && com.getBuildTime() != null) {
				Date date = simple.parse(com.getBuildTime());
				String d = simple.format(date);
				com.setBuildTime(d);
			}
			if (com != null && com.getIsmall() > 0)
				com.setGroupname("smallcompany");

			result.put("company", com);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}
	
	public int getAreaId(String s){
		SysArea disByPath = companyService.getDisByPath(s);
		return disByPath.getId();
	}
	
	public int getTraId(String s){
		VocsTrade traByPath = companyService.getTraByPath(s);
		return traByPath.getId();
	}
	
	public void switchTradeArea(Company com){
		if(com.getTown() > 0) {
			District byId = districtService.getById(com.getProvince());
			District byId2 = districtService.getById(com.getCity());
			District byId3 = districtService.getById(com.getTown());
			com.setAreaName(dis.concat(byId.getDistrictName().concat("/")).concat(byId2.getDistrictName().concat("/"))
					.concat(byId3.getDistrictName().concat("/")));
		}else{
			District byId = districtService.getById(com.getProvince());
			District byId2 = districtService.getById(com.getCity());
			com.setAreaName(dis.concat(byId.getDistrictName().concat("/")).concat(byId2.getDistrictName().concat("/")));
		}
		
		if(com.getTrade2() == 0){
			Trade byId = tradeService.getById(com.getTrade1());
			com.setTradeName(byId.getTradeName().concat("/"));
		}else if(com.getTrade3() == 0){
			Trade byId = tradeService.getById(com.getTrade1());
			Trade byId2 = tradeService.getById(com.getTrade2());
			com.setTradeName(byId.getTradeName().concat("/").concat(byId2.getTradeName().concat("/")));
		}else if(com.getTrade4() == 0){
			Trade byId = tradeService.getById(com.getTrade1());
			Trade byId2 = tradeService.getById(com.getTrade2());
			Trade byId3 = tradeService.getById(com.getTrade3());
			com.setTradeName(byId.getTradeName().concat("/").concat(byId2.getTradeName().concat("/"))
					.concat(byId3.getTradeName().concat("/")));
		}else if(com.getTrade4() > 0){
			Trade byId = tradeService.getById(com.getTrade1());
			Trade byId2 = tradeService.getById(com.getTrade2());
			Trade byId3 = tradeService.getById(com.getTrade3());
			Trade byId4 = tradeService.getById(com.getTrade4());
			com.setTradeName(byId.getTradeName().concat("/").concat(byId2.getTradeName().concat("/"))
					.concat(byId3.getTradeName().concat("/")).concat(byId4.getTradeName().concat("/")));
		}
			
	}

	// restFul风格来写delete
	@RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable String name, HttpServletRequest request) {

		try {

			if (!loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");

			Company c = companyService.getByName(name);
			if (c == null)
				return result.setStatus(1, "company not exist.");
			companyService.delete(c.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	// restuFUL来写,此处是跳转到修改页面
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request) {

		try {

			if (!loginManage.isCompanyLogin(request)
					&& !loginManage.isUserLogin(request))
				return result.setStatus(-1, "No login.");

			Company company = checkCompany(request);

			if (company.getId() <= 0)
				return result.setStatus(-3, "No Id.");

			String pwd = company.getPassword();
			if (pwd != null && pwd.length() > 0 && pwd.length() != 32)
				company.setPassword(LoginManage.md5sum(company.getPassword()));
			companyService.update(company);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	// 登录方法
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {

		try {

			Company company = checkCompany(request);

			if (company.getName() == null || company.getPassword() == null
					|| company.getName().equals("")
					|| company.getPassword().equals("")) {
				return result.setStatus(-3, "No user and password.");
			}

			String ret = loginManage.login(request, company);
			if (ret.equals("ok")) {
				Company c = loginManage.getLoginCompany(request);
				result.put("groupname", c.getGroupname());

				ArrayList<String> years = SeperateTable.instance().getYears();
				result.put("years", years);

				HttpSession session = request.getSession();
				session.setAttribute("curyear", Integer.parseInt(years.get(0)));

				result.setStatus(0, "");
			} else
				result.setStatus(-1, ret);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.getResult();
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public Object loginOut(HttpServletRequest request) {
		loginManage.logout(request);
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/islogin", method = RequestMethod.GET)
	@ResponseBody
	public Object isLogin(HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			result.setStatus(0, "");
		} else {
			result.setStatus(-1, "not login.");
		}
		return result.getResult();
	}

	@RequestMapping(value = "/userupdatefill", method = RequestMethod.POST)
	@ResponseBody
	public Object userUpdateFill(HttpServletRequest request) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		CompanyFill cfill = check_fill(request);
		if (cfill.getId() > 0) {
			cfillService.update(cfill);
		} else {
			return result.setStatus(-2, "No id.");
		}

		return result.setStatus(0, "ok");

	}

	@RequestMapping(value = "/fill", method = RequestMethod.POST)
	@ResponseBody
	public Object fillCuryear(HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request)
				&& !loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		CompanyFill cfill = check_fill(request);

		try {
			int companyid;
			if (loginManage.isCompanyLogin(request)) {
				Company c = loginManage.getLoginCompany(request);
				companyid = c.getId();
			} else if (loginManage.isUserLogin(request)) {
				companyid = cfill.getEnterpriceId();
			} else {
				return result.setStatus(-1, "No login.");
			}

			List<CompanyFill> cfillList = cfillService.getByCompany(companyid);

			cfill.setEnterpriceId(companyid);
			Calendar cal = Calendar.getInstance();
			cfill.setFillTime(Tools.ToDateStr(cal));
			
			CompanyFill cfFound = null;
			for (CompanyFill cf : cfillList) {
				if (cf.getFillyear() == cfill.getFillyear()) {
					cfFound = cf;
					break;
				}
			}
			if (cfFound == null) {
				if (cfill.getStatus() < 1)
					cfill.setStatus(WorkFlowController.STATUS_FILL);
				cfillService.add(cfill);
			} else {
				cfill.setId(cfFound.getId());
				cfill.setStatus(cfFound.getStatus());
				if (cfill.getStatus() < 1)
					cfill.setStatus(WorkFlowController.STATUS_FILL);
				if (cfill.getStatus() > 2)
					return result.setStatus(-2, "已审核，不能修改");
				cfillService.update(cfill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");

	}

	@RequestMapping(value = "/fill/getyear/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object fillGet(@PathVariable int year,HttpServletRequest request) {

		if (!loginManage.isCompanyLogin(request))
			return result.setStatus(-1, "No login.");

		try {

			Company c = loginManage.getLoginCompany(request);
			int companyid = c.getId();

			List<CompanyFill> cfillList = cfillService.getByCompany(companyid);

			List<CompanyFill> fills = new ArrayList<CompanyFill>();
			for (CompanyFill cf : cfillList) {
				if(cf.getFillyear()==year){
					fills.add(cf);
				}
			}
			result.put("fills", fills);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/fill/get/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object fillGet(HttpServletRequest request, @PathVariable int fillyear) {

		if (!loginManage.isCompanyLogin(request))
			return result.setStatus(-1, "No login.");

		try {

			Company c = loginManage.getLoginCompany(request);
			int companyid = c.getId();
			
			CompanyFill cfill=new CompanyFill(); 
			cfill.setEnterpriceId(companyid);
			cfill.setFillyear(fillyear);
			List<CompanyFill> cfillList = cfillService.getByCompanyYear(cfill);

			List<CompanyFill> fills = new ArrayList<CompanyFill>();
			for (CompanyFill cf : cfillList) {
				fills.add(cf);
			}
			result.put("fills", fills);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/fill/get/{companyid}/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object fillGetById(HttpServletRequest request,
			@PathVariable int fillyear, @PathVariable int companyid) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		if (companyid <= 0)
			return result.setStatus(-2, "error companyid.");

		try {
			CompanyFill cf = cfillService.yearFill(companyid, fillyear);
			result.put("data", cf);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/fill/checked", method = RequestMethod.POST)
	@ResponseBody
	public Object fillChecked(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		int accountid;
		int fillyear;
		String s = new String();
		s = request.getParameter("accountid");
		if (s != null && Tools.isInteger(s)) {
			accountid = Integer.parseInt(s);
		} else
			return result.setStatus(-1, "need accountid.");

		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s)) {
			fillyear = Integer.parseInt(s);
		} else
			return result.setStatus(-1, "need fillyear.");

		try {
			cfillService.setstatus(WorkFlowController.STATUS_CHECKED,
					accountid, fillyear);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception.");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/getLostPwd", method = RequestMethod.POST)
	@ResponseBody
	public Object getLostPwd(HttpServletRequest request) {
		String companyName = request.getParameter("companyName");
		String username = request.getParameter("name");
		String telno = request.getParameter("telephone");

		Company com = companyService.getByName(username);

		try {
			if (com != null && com.getTypeid() == 2
					&& com.getCompanyName().equals(companyName)) {
				result.put("company", com);
				return result.setStatus(0, "企业验证成功");
			}
			if (com != null && com.getTypeid() > 2
					&& com.getContactNo().equals(telno)) {
				result.put("company", com);
				return result.setStatus(0, "政府部门验证成功");
			}
			return result.setStatus(-2, "验证信息有误");
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "验证信息有误");
		}
	}

	@RequestMapping(value = "/setLostPwd", method = RequestMethod.POST)
	@ResponseBody
	public Object setLostPwd(HttpServletRequest request) {

		String username = request.getParameter("name");
		String pwd = request.getParameter("password");
		try {
			Company com = companyService.getByName(username);
			if (com != null && pwd != null && pwd != "") {
				com.setPassword(LoginManage.md5sum(pwd));
				companyService.update(com);
				return result.setStatus(0, "密码设置成功");
			} else {
				return result.setStatus(-2, "密码不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "该用户不存在");
		}

	}

	@RequestMapping(value = "/iscompany", method = RequestMethod.POST)
	@ResponseBody
	public Object iscompany(HttpServletRequest request) {

		String username = request.getParameter("name");

		Company com = companyService.getByName(username);

		try {
			if (com.getTypeid() == 2) {
				result.put("type", 0);
				return result.setStatus(0, "企业");
			} else if (com.getTypeid() > 2) {
				result.put("type", 1);
				return result.setStatus(0, "政府");
			} else {
				return result.setStatus(-2, "查无此用户");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "查无此用户");
		}

	}
}
