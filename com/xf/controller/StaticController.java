package com.xf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Company;
import com.xf.entity.Static;
import com.xf.entity.User;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.StaticService;
import com.xf.vo.IntString;

@Scope("prototype")
@Controller
@RequestMapping(value = "/static")
public class StaticController {

	@Autowired
	private StaticService staticService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	private static final String STATIC_NAME = "其它";

	private Static check(HttpServletRequest request) {

		String s = new String();
		Static staticinfo = new Static();
		staticinfo.setGroupname(request.getParameter("groupname"));
		staticinfo.setName(request.getParameter("name"));
		staticinfo.setUnit(request.getParameter("unit"));
		staticinfo.setRemark(request.getParameter("remark"));

		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			staticinfo.setId(Integer.parseInt(s));
		s = request.getParameter("groupid");
		if (s != null && Tools.isInteger(s))
			staticinfo.setGroupid(Integer.parseInt(s));
		s = request.getParameter("pid");
		if (s != null && Tools.isInteger(s))
			staticinfo.setPid(Integer.parseInt(s));

		return staticinfo;
	}

	private List<Static> check_list(HttpServletRequest request) {

		List<Static> list = new ArrayList<Static>();
		List<Static> listp = new ArrayList<Static>();
		if (request.getParameter("name[0][pid]") != null
				&& request.getParameter("name[0][pid]") != "") {
			int pid = Integer.parseInt(request.getParameter("name[0][pid]"));
			listp = staticService.getByPid(pid);
		}

		if (listp.size() > 0) {
			String s = new String();
			for (int i = 0; i < listp.size(); i++) {

				Static staticinfo = new Static();
				staticinfo.setGroupname(request.getParameter("name[" + i
						+ "][groupname]"));
				staticinfo.setName(request
						.getParameter("name[" + i + "][name]"));
				s = request.getParameter("name[" + i + "][unit]");
				if (s != null && Tools.isNumeric(s)
						&& Double.parseDouble(s) < 0) {
					staticinfo.setUnit("0");
				} else {
					staticinfo.setUnit(request.getParameter("name[" + i
							+ "][unit]"));
				}
				staticinfo.setRemark(request.getParameter("name[" + i
						+ "][remark]"));

				s = request.getParameter("name[" + i + "][id]");
				if (s != null && Tools.isInteger(s))
					staticinfo.setId(Integer.parseInt(s));
				s = request.getParameter("name[" + i + "][groupid]");
				if (s != null && Tools.isInteger(s))
					staticinfo.setGroupid(Integer.parseInt(s));
				s = request.getParameter("name[" + i + "][pid]");
				if (s != null && Tools.isInteger(s))
					staticinfo.setPid(Integer.parseInt(s));
				s = request.getParameter("name[" + i + "][accountid]");
				if (s != null && Tools.isInteger(s))
					staticinfo.setAccountid(Integer.parseInt(s));

				list.add(staticinfo);
			}
			return list;
		} else {
			return null;
		}
	}

	int accountid;

	private HashMap<String, Object> check_account(HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			Company c = loginManage.getLoginCompany(request);
			accountid = c.getId();
		} else if (loginManage.isUserLogin(request)) {
			User u = loginManage.getLoginUser(request);
			accountid = u.getId();
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addCompany(HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		Static staticinfo = check(request);

		if (staticinfo.getId() > 0) {
			staticService.update(staticinfo);
			return result.setStatus(0, "更新成功");
		}

		List<String> names = staticService.getAllNames();

		for (String name : names) {
			if (name.equals(staticinfo.getName())) {
				return result.setStatus(-3, "已存在该名称的记录");
			}
		}

		if (staticinfo.getGroupid() <= 0 || staticinfo.getPid() < 0
				|| staticinfo.getName() == null
				|| staticinfo.getName().isEmpty()) {
			return result.setStatus(-3, "need pid, groupid, name.");
		}

		try {
			if(accountid>1)
			staticinfo.setAccountid(accountid);
			
			staticService.add(staticinfo);
			result.put("lastid", staticinfo.getId());
			return result.setStatus(0, "添加成功");

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "device fill error");
		}

	}

	@RequestMapping(value = "/{pid}/{groupid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByDevId(@PathVariable int pid, @PathVariable int groupid,
			HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		Static info = new Static();
		info.setGroupid(groupid);
		info.setPid(pid);
		info.setAccountid(accountid);
		List<Static> slist = staticService.getGroupPid(info);
		if (null == slist)
			return result.setStatus(-3, "No static info.");

		result.put("staticlist", slist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/pid/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByPid(@PathVariable int pid, HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		Static info = new Static();
		info.setPid(pid);
		info.setAccountid(accountid);
		List<Static> slist = staticService.getPid(info);
		if (null == slist)
			return result.setStatus(-3, "No static info.");

		result.put("staticlist", slist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/ppid/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getPid(@PathVariable int pid, HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		List<Static> slist = staticService.getByPid(pid);
		if (null == slist)
			return result.setStatus(-3, "No static info.");

		result.put("staticlist", slist);

		return result.setStatus(0, "");
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/group/{groupid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByGroupId(@PathVariable int groupid,
			HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		Static info = new Static();
		info.setGroupid(groupid);
		info.setAccountid(accountid);

		List<Static> list = new ArrayList<Static>();
		List<Static> slist = staticService.getGroup(info); 
		List<String> nlist = staticService.getNameByGroupid(info);

		if (slist.size() > 0) {
			for (String str : nlist) {
				for (Static sl : slist) {
					if (str.equals(sl.getName())) {
						list.add(sl);
						break;
					}
				}
			}
		}

		if (null == slist)
			return result.setStatus(-3, "No static info.");

		result.put("staticlist", list);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByDevId(@PathVariable int id, HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		Static info = staticService.getById(id);
		if (null == info)
			return result.setStatus(-3, "No static info.");

		result.put("staticinfo", info);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllGroup(HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		int gid = 0;
		List<Object> data = new ArrayList<Object>();
		List<Static> glist = null;
		List<Static> slist = staticService.getAll();
		for (Static s : slist) {
			if (gid != s.getGroupid()) {
				if (glist != null) {
					data.add(glist);
				}
				glist = new ArrayList<Static>();
			}
			glist.add(s);
			gid = s.getGroupid();
		}

		result.put("staticlist", data);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/getbypid/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getbypid(@PathVariable int pid, HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		List<Static> slist = staticService.getByPid(pid);
		if (null == slist)
			return result.setStatus(-3, "工艺分类下无治理方法");

		result.put("staticlist", slist);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/getTech1", method = RequestMethod.GET)
	@ResponseBody
	public Object getTech1(HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		List<IntString> list = staticService.getTech1();
		if (null == list)
			return result.setStatus(-3, "无治理工艺类");

		result.put("staticlist", list);

		return result.setStatus(0, "");
	}

	// 按组插入
	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		List<Static> staticinfo = check_list(request);

		if (staticinfo != null && staticinfo.size() > 0) {
			for (Static sta : staticinfo) {
				if ((sta.getUnit()!=null && !Tools.isNumeric(sta.getUnit())) 
						|| (sta.getRemark()!=null && !sta.getRemark().equals("") && !Tools.isNumeric(sta.getRemark())))
					return result.setStatus(-2, "值请填写数字，没有请填0！");
				
				if(sta.getUnit()!=null&&!sta.getUnit().equals("")){
				if(Double.parseDouble(sta.getUnit())>1||Double.parseDouble(sta.getUnit())<0)
					return result.setStatus(-2, "去除效率区间为:0-1");
				}
				
				if(sta.getRemark()!=null&&!sta.getRemark().equals("")){
				if(Double.parseDouble(sta.getRemark())>1||Double.parseDouble(sta.getRemark())<0)
					return result.setStatus(-2, "去除效率区间为:0-1");
				}
				
				if (sta.getId() > 0) {
					staticService.update(sta);
				}

			}
			return result.setStatus(0, "ok");
		} else {
			return result.setStatus(-3, "无治理工艺");
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		Static staticinfo = check(request);
		int id = staticinfo.getId();

		try {
			HashSet<Integer> set = new LinkedHashSet<Integer>();
			List<Static> slist = staticService.getByName(STATIC_NAME);
			Static statics = staticService.getById(id);
			Static sta = staticService.getById(id);
			List<Static> spid = staticService.getByPid(id);
			if (spid.size() > 0) {
				for (Static sp : spid) {
					set.add(sp.getGroupid());
				}
			}
			if (statics != null && !statics.getName().equals(STATIC_NAME)) {
				int i = 0;
				if (slist.size() > 0) {
					for (Static sl : slist) {
						if (sl.getGroupid() == statics.getGroupid()
								&& sl.getPid() == statics.getPid()) {
							i = 1;
						}
					}
				}

				staticService.delete(id);
				staticService.deleteBypid(id);

				if (i == 0) {
					statics.setId(0);
					statics.setName(STATIC_NAME);
					statics.setAccountid(0);
					staticService.add(statics);
				}

				if (spid.size() > 0) {
					int j = 0;
					for (Static sl : slist) {
						if (sl.getGroupid() == statics.getGroupid()
								&& sl.getPid() == statics.getPid()) {
							statics.setPid(sl.getId());
							j = 1;
						}
					}
					if (j == 0) {
						statics.setPid(statics.getId());
					}
					statics.setId(0);
					statics.setName(STATIC_NAME);
					for (Integer in : set) {
						statics.setGroupid(in);
						int k = 0;
						if (slist.size() > 0) {
							for (Static sl : slist) {
								if (sl.getGroupid() == in
										&& sl.getPid() == statics.getId()) {
									k = 1;
								}
							}
						}
						if (k == 0) {
							staticService.add(statics);
						}
					}
				}

				slist = staticService.getByName(STATIC_NAME);

				if (slist.size() > 0) {
					for (Static sl : slist) {
						if (sl.getGroupid() == sta.getGroupid()
								&& sl.getPid() == sta.getPid()) {
							staticService.updateElec(sl.getId(), id);
							staticService.updateDev(sl.getId(), id);
							staticService.updateFire(sl.getId(), id);
							staticService.updateGK(sl.getId(), id);
							staticService.updateNflay(sl.getId(), id);
							staticService.updateNffer(sl.getId(), id);
							staticService.updateOildev(sl.getId(), id);
							staticService.updateOilcon(sl.getId(), id);
							staticService.updateRoad(sl.getId(), id);
							staticService.deleteStadev(id);
							staticService.deleteStagov(id);
							staticService.deleteStamotor(id);
							staticService.deleteStapro(id);
							staticService.updateVehifac(sl.getId(), id);
							staticService.updateVehistan(sl.getId(), id);
							if (sta.getPid() == 0) {
								staticService.updateFac(sl.getPid(),
										sl.getId(), id, 0);
							} else {
								staticService.updateFac(sl.getPid(),
										sl.getId(), sta.getPid(), id);
							}

							if (spid.size() > 0) {
								for (Static sp : spid) {
									if (sl.getGroupid() == sp.getGroupid()
											&& sl.getPid() == sp.getPid()) {
										staticService.updateElec(sl.getId(),
												sp.getId());
										staticService.updateDev(sl.getId(),
												sp.getId());
										staticService.updateFire(sl.getId(),
												sp.getId());
										staticService.updateGK(sl.getId(),
												sp.getId());
										staticService.updateNflay(sl.getId(),
												sp.getId());
										staticService.updateNffer(sl.getId(),
												sp.getId());
										staticService.updateOildev(sl.getId(),
												sp.getId());
										staticService.updateOilcon(sl.getId(),
												sp.getId());
										staticService.updateRoad(sl.getId(),
												sp.getId());
										staticService.deleteStadev(sp.getId());
										staticService.deleteStagov(sp.getId());
										staticService
												.deleteStamotor(sp.getId());
										staticService.deleteStapro(sp.getId());
										staticService.updateVehifac(sl.getId(),
												sp.getId());
										staticService.updateVehistan(
												sl.getId(), sp.getId());
										staticService.updateFac(sl.getPid(),
												sl.getId(), id, sp.getId());
									}
								}
							}
						}
					}
				}
			} else {
				return result.setStatus(-3, "Can't delete this");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/getAllByGroupid/{groupid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllByGroupId(@PathVariable int groupid,
			HttpServletRequest request) {

		Object ret = check_account(request);
		if (ret != null)
			return ret;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Static> staList = staticService.getBygroupid(groupid);
		if(!(staList.size() > 0))
			return result.setStatus(-3, "No such group.");
		
		List<Static> allList = staticService.getAll();
		for(Static sta:staList){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pid", sta.getPid());
			map.put("label", sta.getName());
			map.put("value", sta.getId());
			getChildren(map, sta, allList);
			list.add(map);
		}

		result.put("list", list);

		return result.setStatus(0, "ok");
	}
//递归得到子代
	public void getChildren(Map<String, Object> map, Static sta, List<Static> allList){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(Static staNext:allList){
			if(sta.getId() == staNext.getPid() && staNext.getId() > sta.getId()){
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("pid", staNext.getPid());
				map1.put("label", staNext.getName());
				map1.put("value", staNext.getId());
				list.add(map1);
				getChildren(map1,staNext, allList);
			}
		}
		if(list.size()>0)
			map.put("children", list);
		
	}
}
