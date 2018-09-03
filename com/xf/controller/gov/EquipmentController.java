package com.xf.controller.gov;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.controller.WorkFlowController;
import com.xf.entity.Company;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.CompanyService;
import com.xf.service.DistrictService;
import com.xf.entity.gov.ConstructionDust;
import com.xf.entity.gov.Equipment;
import com.xf.vo.ConstructionDustM;
import com.xf.vo.EquipmentM;
import com.xf.service.gov.EquipmentService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/equipment")
public class EquipmentController extends BaseController {

	@Autowired
	private EquipmentService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DistrictService districtService;

	private List<Equipment> check_input(HttpServletRequest request) {
		Map userMap = new HashMap();
		List<Equipment> li = new ArrayList<Equipment>();
		for (int i = 0; i < 108; i++) {
			userMap.put("u" + i, new Equipment());

			String s = new String();
			s = request.getParameter("id");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setId(Integer.parseInt(s));
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setAccountid(Integer
						.parseInt(s));
			s = request.getParameter("status");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setStatus(Integer
						.parseInt(s));
			((Equipment) userMap.get("u" + i)).setFillTime(request
					.getParameter("fillTime"));
			s = request.getParameter("fillyear");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setFillyear(Integer
						.parseInt(s));
			s = request.getParameter("province");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setProvince(Integer
						.parseInt(s));
			s = request.getParameter("city");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setCity(Integer.parseInt(s));
			s = request.getParameter("town");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setTown(Integer.parseInt(s));
			s = request.getParameter("country");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setCountry(Integer
						.parseInt(s));
			s = request.getParameter("street");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setStreet(Integer
						.parseInt(s));
			s = request.getParameter("area[" + i + "][etype]");
			if ("挖掘机".equals(s))
				s = "14001";
			if ("推土机".equals(s))
				s = "14002";
			if ("装载机".equals(s))
				s = "14003";
			if ("叉车".equals(s))
				s = "14004";
			if ("起重机".equals(s))
				s = "14005";
			if ("混凝土搅拌机".equals(s))
				s = "14006";
			if ("压路机".equals(s))
				s = "14007";
			if ("摊铺机".equals(s))
				s = "14008";
			if ("平地机".equals(s))
				s = "14009";
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i))
						.setEtype(Integer.parseInt(s));
			s = request.getParameter("area[" + i + "][area]");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setArea(Integer.parseInt(s));
			s = request.getParameter("area[" + i + "][emodel]");
			if (s != null && Tools.isInteger(s)) {
				if (Integer.parseInt(s) == 0)
					((Equipment) userMap.get("u" + i)).setEmodel("小型");
				if (Integer.parseInt(s) == 1)
					((Equipment) userMap.get("u" + i)).setEmodel("中型");
				if (Integer.parseInt(s) == 2)
					((Equipment) userMap.get("u" + i)).setEmodel("大型");
			}

			s = request.getParameter("area[" + i + "][enumber]");
			if (s != null && Tools.isInteger(s))
				((Equipment) userMap.get("u" + i)).setEnumber(Integer
						.parseInt(s));
			s = request.getParameter("area[" + i + "][eduration]");
			if (s != null && Tools.isNumeric(s))
				((Equipment) userMap.get("u" + i)).setEduration(Double
						.parseDouble(s));
			if (request.getParameter("area[" + i + "][emodel]") != null) {
				li.add(((Equipment) userMap.get("u" + i)));
			}
		}

		return li;
	}

	private Equipment check_input2(HttpServletRequest request) {

		Equipment eq = new Equipment();
		String s = new String();

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			eq.setId(Integer.parseInt(s));
		s = request.getParameter("accountid");
		if (s != null && Tools.isInteger(s))
			eq.setAccountid(Integer.parseInt(s));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			eq.setStatus(Integer.parseInt(s));
		eq.setFillTime(request.getParameter("fillTime"));
		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			eq.setFillyear(Integer.parseInt(s));
		s = request.getParameter("province");
		if (s != null && Tools.isInteger(s))
			eq.setProvince(Integer.parseInt(s));
		s = request.getParameter("city");
		if (s != null && Tools.isInteger(s))
			eq.setCity(Integer.parseInt(s));
		s = request.getParameter("town");
		if (s != null && Tools.isInteger(s))
			eq.setTown(Integer.parseInt(s));
		s = request.getParameter("country");
		if (s != null && Tools.isInteger(s))
			eq.setCountry(Integer.parseInt(s));
		s = request.getParameter("street");
		if (s != null && Tools.isInteger(s))
			eq.setStreet(Integer.parseInt(s));
		s = request.getParameter("etype");
		if (s != null && Tools.isInteger(s))
			eq.setEtype(Integer.parseInt(s));
		s = request.getParameter("area");
		if (s != null && Tools.isInteger(s))
			eq.setArea(Integer.parseInt(s));
		eq.setEmodel(request.getParameter("emodel"));
		s = request.getParameter("enumber");
		if (s != null && Tools.isInteger(s))
			eq.setEnumber(Integer.parseInt(s));
		s = request.getParameter("eduration");
		if (s != null && Tools.isNumeric(s))
			eq.setEduration(Double.parseDouble(s));
		if (request.getParameter("emodel") != null) {

		}

		return eq;
	}

	// ==============================================================================

	Company c;
	int companyid;

	private HashMap<String, Object> check_account(HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			String s = new String();
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s))
				companyid = Integer.parseInt(s);
			if (companyid <= 0)
				return result.setStatus(-2, "no company id.");
			else
				c = companyService.getById(companyid);
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Equipment input = check_input(request).get(0);
			if (input.getEtype() <= 0)
				return result.setStatus(-3, "no data.");

			Equipment search = theService.getByField(input);
			if (search != null && search.getId() > 0)
				result.put("id", search.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			List<Equipment> input = check_input(request);

			for (int i = 0; i < input.size(); i++) {
				if (input.get(i).getEnumber() > 0) {
					input.get(i).setAccountid(companyid);
					Equipment search = null;
					if (input.get(i).getAccountid() > 0) {
						search = theService.getEquipment(input.get(i));
					}

					Calendar cal = Calendar.getInstance();
					input.get(i).setFillTime(Tools.ToDateStr(cal));
					if (input.get(i).getStatus() < 1)
						input.get(i).setStatus(WorkFlowController.STATUS_FILL);
					if (search != null && search.getId() > 0) {
						theService.update(input.get(i));
					} else {
						if (c.getProvince() == 0) {
							input.get(i).setProvince(1);
						} else {
							input.get(i).setProvince(c.getProvince());
							input.get(i).setCity(c.getCity());
							input.get(i).setTown(c.getTown());
						}

						if (input.get(i).getEmodel() != null
								&& input.get(i).getEmodel() != "") {
							theService.add(input.get(i));
						} else {
							return result.setStatus(-3, "机型不能为空");
						}

					}
					result.put("id" + i, input.get(i).getId());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/addup2", method = RequestMethod.POST)
	@ResponseBody
	public Object addup2(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;
			Equipment input = check_input2(request);
			String s = request.getParameter("city");
			int city = 0;
			if (s != null && !s.equals("")) {
				city = Integer.parseInt(s);
			}
			if (input.getEnumber() > 0) {
				input.setAccountid(companyid);
				Equipment search = null;
				if (input.getAccountid() > 0) {
					search = theService.getById(input.getId());
				}

				Calendar cal = Calendar.getInstance();
				input.setFillTime(Tools.ToDateStr(cal));
				if (input.getStatus() < 1)
					input.setStatus(WorkFlowController.STATUS_FILL);
				if (search != null && search.getId() > 0) {
					theService.update2(input);
				} else {
					if (c.getProvince() == 0) {
						input.setProvince(1);
						input.setCity(city);
					} else {
						input.setProvince(c.getProvince());
						input.setCity(c.getCity());
						input.setTown(c.getTown());
					}
					if (input.getEmodel() != null && input.getEmodel() != "") {
						theService.add(input);
					} else {
						return result.setStatus(-3, "机型不能为空");
					}

				}
				// result.put("id", search.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	public Equipment getEquipment(Equipment obj) {
		Equipment eq = theService.getEquipment(obj);
		if (eq != null)
			return eq;
		return null;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;
			System.out.println(id);
			Equipment search = theService.getById(id);
			if (null == search)
				return result.setStatus(-3, "No data.");

			result.put("data", search);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteById(@PathVariable int id, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Equipment search = theService.getById(id);
			if (null == search)
				return result.setStatus(-3, "无该记录！");

			theService.delete(id);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/get2/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByYear2(@PathVariable int year, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Equipment input = new Equipment();
			input.setFillyear(year);
			input.setAccountid(companyid);

			List<Equipment> list0 = theService.getByYear2(input);
			List<Equipment> list = new ArrayList<Equipment>();
			list.addAll(list0);
			Set<Integer> set = new HashSet<Integer>();
			for (Equipment b : list) {
				set.add(b.getCity());
			}
			if (list.size() > 0) {

				List<EquipmentM> reslist = new ArrayList<EquipmentM>();

				for (Integer i : set) {
					List<Map> listm = new ArrayList<Map>();
					for (Equipment search : list) {
						if (search.getCity() == i) {
							Map map = new HashMap();
							map.put("id", search.getId());
							map.put("etype", search.getEtype());
							map.put("area", search.getArea());
							map.put("emodel", search.getEmodel());
							map.put("enumber", search.getEnumber());
							map.put("eduration", search.getEduration());
							listm.add(map);
						}
					}
					Equipment search = list.get(0);
					EquipmentM m = new EquipmentM();
					m.setId(search.getId());
					m.setAccountid(search.getAccountid());
					m.setStatus(search.getStatus());
					m.setFillTime(search.getFillTime());
					m.setFillyear(search.getFillyear());
					m.setProvince(search.getProvince());
					m.setCity(search.getCity());
					m.setTown(search.getTown());
					m.setCountry(search.getCountry());
					m.setStreet(search.getStreet());
					// m.setEtype(search.getEtype());
					m.setArea(listm);
					m.setCityName(districtService.getById(i).getDistrictName());
					reslist.add(m);
				}
				result.put("data", reslist);
			} else {
				return result.setStatus(-3, "无记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "无记录");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/{year}/{city}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByYear(@PathVariable int year, @PathVariable int city,
			HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			Equipment input = new Equipment();
			input.setFillyear(year);
			input.setAccountid(companyid);

			List<Equipment> list0 = theService.getByYear(input);
			List<Equipment> list = new ArrayList<Equipment>();
			if (city > 0) {
				for (Equipment cd : list0) {
					if (cd.getCity() == city) {
						list.add(cd);
					}
				}
			} else {
				list.addAll(list0);
			}
			Set<Integer> set = new HashSet<Integer>();
			for (Equipment b : list) {
				set.add(b.getCity());
			}
			if (list.size() > 0) {

				List<EquipmentM> reslist = new ArrayList<EquipmentM>();

				for (Integer i : set) {
					List<Map> listm = new ArrayList<Map>();
					for (Equipment search : list) {
						if (search.getCity() == i) {
							Map map = new HashMap();
							map.put("id", search.getId());
							map.put("etype", search.getEtype());
							map.put("area", search.getArea());
							if (search.getEmodel().equals("大型"))
								map.put("emodel", 2);
							if (search.getEmodel().equals("中型"))
								map.put("emodel", 1);
							if (search.getEmodel().equals("小型"))
								map.put("emodel", 0);
							map.put("enumber", search.getEnumber());
							map.put("eduration", search.getEduration());
							listm.add(map);
						}
					}
					Equipment search = list.get(0);
					EquipmentM m = new EquipmentM();
					m.setId(search.getId());
					m.setAccountid(search.getAccountid());
					m.setStatus(search.getStatus());
					m.setFillTime(search.getFillTime());
					m.setFillyear(search.getFillyear());
					m.setProvince(search.getProvince());
					m.setCity(search.getCity());
					m.setTown(search.getTown());
					m.setCountry(search.getCountry());
					m.setStreet(search.getStreet());
					// m.setEtype(search.getEtype());
					m.setArea(listm);
					m.setCityName(districtService.getById(i).getDistrictName());
					reslist.add(m);
				}
				result.put("data", reslist);
			} else {
				result.put("data", list);
				return result.setStatus(0, "无记录");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	public void setService() {
		super.theService = theService;
	}
}
