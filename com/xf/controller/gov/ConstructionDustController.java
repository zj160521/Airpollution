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
import com.xf.entity.gov.Boat;
import com.xf.entity.gov.ConstructionDust;
import com.xf.vo.BoatM;
import com.xf.vo.ConstructionDustM;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.DistrictService;
import com.xf.service.gov.ConstructionDustService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/constructionDust")
public class ConstructionDustController extends BaseController {

	@Autowired
	private ConstructionDustService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
    @Autowired
    private DistrictService districtService;
	
	private List<ConstructionDust> check_input(HttpServletRequest request) {
		List<ConstructionDust> li = new ArrayList<ConstructionDust>();

		for (int i = 0; i < 4; i++) {
			ConstructionDust ret = new ConstructionDust();

			String s = new String();
			s = request.getParameter("id");
			if (s != null && Tools.isInteger(s))
				ret.setId(Integer.parseInt(s));
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s))
				ret.setAccountid(Integer.parseInt(s));
			ret.setFillTime(request.getParameter("fillTime"));
			s = request.getParameter("fillyear");
			if (s != null && Tools.isInteger(s))
				ret.setFillyear(Integer.parseInt(s));
			s = request.getParameter("province");
			if (s != null && Tools.isInteger(s))
				ret.setProvince(Integer.parseInt(s));
			s = request.getParameter("city");
			if (s != null && Tools.isInteger(s))
				ret.setCity(Integer.parseInt(s));
			s = request.getParameter("town");
			if (s != null && Tools.isInteger(s))
				ret.setTown(Integer.parseInt(s));
			s = request.getParameter("country");
			if (s != null && Tools.isInteger(s))
				ret.setCountry(Integer.parseInt(s));
			s = request.getParameter("street");
			if (s != null && Tools.isInteger(s))
				ret.setStreet(Integer.parseInt(s));
			s = request.getParameter("area[" + i + "][area]");
			if (s != null && Tools.isInteger(s))
				ret.setArea(Integer.parseInt(s));
			s = request.getParameter("constructArea");
			if (s != null && Tools.isNumeric(s))
				ret.setConstructArea(Double.parseDouble(s));
			s = request.getParameter("buildingArea");
			if (s != null && Tools.isNumeric(s))
				ret.setBuildingArea(Double.parseDouble(s));
			s = request.getParameter("startWorkArea");
			if (s != null && Tools.isNumeric(s))
				ret.setStartWorkArea(Double.parseDouble(s));
			s = request.getParameter("completeArea");
			if (s != null && Tools.isNumeric(s))
				ret.setCompleteArea(Double.parseDouble(s));
			s = request.getParameter("area[" + i + "][hasStartedArea]");
			if (s != null && Tools.isNumeric(s))
				ret.setHasStartedArea(Double.parseDouble(s));
			s = request.getParameter("area[" + i + "][hasStartNumber]");
			if (s != null && Tools.isInteger(s))
				ret.setHasStartNumber(Integer.parseInt(s));
			s = request.getParameter("area[" + i + "][notStartArea]");
			if (s != null && Tools.isNumeric(s))
				ret.setNotStartArea(Double.parseDouble(s));
			s = request.getParameter("area[" + i + "][notStartNumber]");
			if (s != null && Tools.isInteger(s))
				ret.setNotStartNumber(Integer.parseInt(s));
			ret.setDepartment(request.getParameter("department"));
			s = request.getParameter("area[" + i + "][avgWorktime]");
			if (s != null && Tools.isNumeric(s))
				ret.setAvgWorktime(Double.parseDouble(s));

			li.add(ret);

		}
		return li;
	}

	// ==============================================================================

	int companyid;

	private HashMap<String, Object> check_account(HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			companyid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			String s = new String();
			s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s))
				companyid = Integer.parseInt(s);
			if (companyid <= 0)
				return result.setStatus(-2, "no company id.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	// ==============================================================================

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Object getCheck(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			List<ConstructionDust> input = check_input(request);
			if (input.get(0).getCity() <= 0)
				return result.setStatus(-3, "no data.");

			for (int i = 0; i < input.size(); i++) {
				ConstructionDust search = theService.getByField(input.get(i));
				if (search != null && search.getId() > 0)
					result.put("id" + i, search.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	// ==============================================================================

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;
			String s=request.getParameter("city");
			int city=0;
			if(s!=null&&!s.equals("")){
				city=Integer.parseInt(s);
			}
			List<ConstructionDust> input = check_input(request);

			for (int i = 0; i < input.size(); i++) {

				input.get(i).setAccountid(companyid);
				Calendar cal = Calendar.getInstance();
				input.get(i).setFillTime(Tools.ToDateStr(cal));
				ConstructionDust search = null;
				if (input.get(i).getAccountid() > 0) {
					search = theService.getConstructionDust(input.get(i));
				}

				if (input.get(i).getStatus() < 1)
					input.get(i).setStatus(WorkFlowController.STATUS_FILL);
				if (search != null && search.getId() > 0) {
					theService.update(input.get(i));
				} else {

					Company c = loginManage.getLoginCompany(request);
					if(c.getProvince()==0){
						input.get(i).setProvince(1);
						input.get(i).setCity(city);
					}else{
						input.get(i).setProvince(c.getProvince());
						input.get(i).setCity(c.getCity());
						input.get(i).setTown(c.getTown());
					}
					
					theService.add(input.get(i));
				}
				result.put("id", input.get(i).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	// ==============================================================================

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable int id, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			List<ConstructionDust> listc = theService.getById(id);
			if (listc.size() > 0) {
				List<Map> listm = new ArrayList<Map>();
				for (ConstructionDust search : listc) {
					Map map = new HashMap();
					map.put("area", search.getArea());
					map.put("hasStartedArea", search.getHasStartedArea());
					map.put("hasStartNumber", search.getHasStartNumber());
					map.put("notStartArea", search.getNotStartArea());
					map.put("notStartNumber", search.getNotStartNumber());
					map.put("notStartNumber", search.getNotStartNumber());
					map.put("avgWorktime", search.getAvgWorktime());
					listm.add(map);
				}
				ConstructionDust search = listc.get(0);
				ConstructionDustM m = new ConstructionDustM();
				m.setId(search.getId());
				m.setAccountid(search.getAccountid());
				m.setFillTime(search.getFillTime());
				m.setFillyear(search.getFillyear());
				m.setProvince(search.getProvince());
				m.setCity(search.getCity());
				m.setTown(search.getTown());
				m.setCountry(search.getCountry());
				m.setStreet(search.getStreet());
				m.setConstructArea(search.getConstructArea());
				m.setBuildingArea(search.getBuildingArea());
				m.setStartWorkArea(search.getStartWorkArea());
				m.setCompleteArea(m.getCompleteArea());
				m.setDepartment(search.getDepartment());
				m.setTownname(search.getTownname());
				m.setStatus(search.getStatus());
				m.setArea(listm);
				if (null == search) {
					return result.setStatus(-3, "No data.");
				}

				result.put("data", m);
			} else {
				return result.setStatus(-3, "no data");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "");
	}

	public ConstructionDust getConstructionDust(ConstructionDust obj) {
		return theService.getConstructionDust(obj);

	}

	// ==============================================================================

	@RequestMapping(value = "/get/{year}/{city}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByYear(@PathVariable int year,@PathVariable int city, HttpServletRequest request) {
		try {
			HashMap<String, Object> ret = check_account(request);
			if (ret != null)
				return ret;

			ConstructionDust input = new ConstructionDust();
			input.setFillyear(year);
			input.setAccountid(companyid);

			List<ConstructionDust> list0 = theService.getByYear(input);
			List<ConstructionDust> list = new ArrayList<ConstructionDust>();
			if(city>0){
				for(ConstructionDust cd:list0){
					if(cd.getCity()==city){
						list.add(cd);
					}
				}
			}else{
				list.addAll(list0);
			}
			Set<Integer> set=new HashSet<Integer>();
			for(ConstructionDust b:list){
				set.add(b.getCity());
			}
			if (list.size() > 0) {
				
				List<ConstructionDustM> reslist = new ArrayList<ConstructionDustM>();
				for(Integer i:set){
					List<Map> listm = new ArrayList<Map>();
					for(ConstructionDust search : list){
						if(search.getCity()==i){
							Map<Object, Object> map = new HashMap<Object, Object>();
							map.put("id", search.getId());
							map.put("area", search.getArea());
							map.put("hasStartedArea", search.getHasStartedArea());
							map.put("hasStartNumber", search.getHasStartNumber());
							map.put("notStartArea", search.getNotStartArea());
							map.put("notStartNumber", search.getNotStartNumber());
							map.put("notStartNumber", search.getNotStartNumber());
							map.put("avgWorktime", search.getAvgWorktime());
							listm.add(map);
						}
					}
					ConstructionDust search = list.get(0);
					ConstructionDustM m = new ConstructionDustM();
					m.setAccountid(search.getAccountid());
					m.setFillTime(search.getFillTime());
					m.setFillyear(search.getFillyear());
					m.setProvince(search.getProvince());
					m.setCity(search.getCity());
					m.setTown(search.getTown());
					m.setCountry(search.getCountry());
					m.setStreet(search.getStreet());
					m.setConstructArea(search.getConstructArea());
					m.setBuildingArea(search.getBuildingArea());
					m.setStartWorkArea(search.getStartWorkArea());
					m.setCompleteArea(search.getCompleteArea());
					m.setDepartment(search.getDepartment());
					m.setTownname(search.getTownname());
					m.setStatus(search.getStatus());
					m.setArea(listm);
					m.setCityName(districtService.getById(i).getDistrictName());
					reslist.add(m);
				}
				
				result.put("data", reslist);
				
			} else {
				return result.setStatus(0, "no data");
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
