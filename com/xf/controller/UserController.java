package com.xf.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

import com.xf.entity.District;
import com.xf.entity.Permission;
import com.xf.entity.User;
import com.xf.security.LoginManage;
import com.xf.security.SeperateTable;
import com.xf.security.Tools;
import com.xf.service.DistrictService;
import com.xf.service.UserService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private DistrictService disService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private User checkUser(HttpServletRequest request) {
		String s = new String();
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setRemark(request.getParameter("remark"));
		s = request.getParameter("id");
		if (s != null && Tools.isInteger(s))
			user.setId(Integer.parseInt(s));
		s = request.getParameter("usertype");
		if (s != null && Tools.isInteger(s))
			user.setUsertype(Integer.parseInt(s));
		s = request.getParameter("typeid");
		if (s != null && Tools.isInteger(s))
			user.setTypeid(Integer.parseInt(s));
		s = request.getParameter("province");
		if (s != null && Tools.isInteger(s))
			user.setProvince(Integer.parseInt(s));
		s = request.getParameter("city");
		if (s != null && Tools.isInteger(s))
			user.setProvince(Integer.parseInt(s));
		s = request.getParameter("town");
		if (s != null && Tools.isInteger(s))
			user.setCity(Integer.parseInt(s));
		s = request.getParameter("role_id");
		if (s != null && Tools.isInteger(s))
			user.setRole_id(Integer.parseInt(s));
		return user;
	}

	@RequestMapping(value = "/changeyear/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object changeYear(@PathVariable int year, HttpServletRequest request) throws NoSuchAlgorithmException {
		if (!loginManage.isUserLogin(request) && !loginManage.isCompanyLogin(request))
			return result.setStatus(-1, "No login.");

		if (year < 2015 && year > 2099)
			return result.setStatus(-3, "非法年份");
		
		HttpSession session = request.getSession();
		session.setAttribute("curyear", year);

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addUser(@RequestBody User user, HttpServletRequest request) throws NoSuchAlgorithmException {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

			if (user.getPassword() != null && user.getPassword().length() > 0)
				user.setPassword(LoginManage.md5sum(user.getPassword()));
			if (user.getId() <= 0)
				userService.add(user);
			else
				userService.update(user);

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByName(@PathVariable String name,
			HttpServletRequest request) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		try {

			User admin = loginManage.getLoginUser(request);
			if (!admin.getUsername().equals("admin"))
				return result.setStatus(-2, "not admin.");

			User u = userService.getByName(name);
			if (u == null)
				return result.setStatus(-4, "user not exist.");
			result.put("user", u);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "exception");
		}

		return result.setStatus(0, "");
	}

	// 显示所有用户的方法
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Object allUser(HttpServletRequest request) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		try {

			User admin = loginManage.getLoginUser(request);
			if (!admin.getUsername().equals("admin"))
				return result.setStatus(-2, "not admin.");

			List<User> userList = userService.getAll();
			if (userList != null) {
				for (User u : userList) {
					u.setPassword("******");

					List<String> melist = new ArrayList<String>();
					List<String> talist = new ArrayList<String>();

					if (u != null && u.getMenuitems()!=null && !u.getMenuitems().isEmpty()) {
						String[] str = u.getMenuitems().split(":");
						String[] s = str[0].split(",");
						for (int i = 0; i < s.length; i++) {
							melist.add(s[i]);
						}
						u.setMenulist(melist);

						if (str.length > 1) {
							String[] s1 = str[1].split(",");
							for (int i = 0; i < s1.length; i++) {
								talist.add(s1[i]);
							}
							u.setTargetlist(talist);
						}
					}
				}
			}
			result.put("userlist", userList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "exception");
		}

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/myself", method = RequestMethod.GET)
	@ResponseBody
	public Object myself(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		User u = loginManage.getLoginUser(request);
		
		if(u!=null){
			String p=new String();
			String c=new String();
			String t=new String();
			
			if(u.getProvince()>0){
			District disp=disService.getById(u.getProvince());
			if(disp!=null) p=disp.getDistrictName();
			}
			if(u.getCity()>0){
			District disc=disService.getById(u.getCity());
			if(disc!=null) c=disc.getDistrictName();
			}
			if(u.getTown()!=null){
			District dist=disService.getById(u.getTown());
			if(dist!=null) t=dist.getDistrictName();
			}
			u.setAddress(p+" "+c+" "+t);
		}
		
		List<String> melist = new ArrayList<String>();
		List<String> talist = new ArrayList<String>();
		
		if (u != null && u.getMenuitems() != null
				&& !u.getMenuitems().isEmpty()) {
			String[] str = u.getMenuitems().split(":");
			String[] s = str[0].split(",");
			for (int i = 0; i < s.length; i++) {
				melist.add(s[i]);
			}
			u.setMenulist(melist);

			if (str.length > 1) {
				String[] s1 = str[1].split(",");
				for (int i = 0; i < s1.length; i++) {
					talist.add(s1[i]);
				}
				u.setTargetlist(talist);
			}
		}
		
		result.put("user", u);

		return result.setStatus(0, "");
	}

	// restFul风格来写delete
	@RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteUser(@PathVariable String name,
			HttpServletRequest request) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		try {

			User admin = loginManage.getLoginUser(request);
			if (!admin.getUsername().equals("admin"))
				return result.setStatus(-2, "not admin.");

			User u = userService.getByName(name);
			if (u == null)
				return result.setStatus(-4, "user " + name + " not exist.");
			if(!u.getUsername().equals("admin"))userService.delete(u.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "exception");
		}

		return result.setStatus(0, "delete ok");
	}

	// 登录方法
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {

		User user1 = checkUser(request);
		if (user1.getUsername() == null || user1.getPassword() == null
				|| user1.getUsername().equals("")
				|| user1.getPassword().equals("")) {
			return result.setStatus(-3, "No user and password.");
		}
		String ret = loginManage.login(request, user1);
		if (ret.equals("ok")) {
			User u = loginManage.getLoginUser(request);
			result.put("user", u);
			if(u.getRole_id()>0){
				Permission p=loginManage.getPermission(u.getId());
				result.put("permission", p);
			}
			
			ArrayList<String> years = SeperateTable.instance().getYears();
			result.put("years", years);

			HttpSession session = request.getSession();
			session.setAttribute("curyear", Integer.valueOf(years.get(0)));

			result.setStatus(0, "");
		} else
			result.setStatus(-1, ret);
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

		if (loginManage.isUserLogin(request)) {
			User u = loginManage.getLoginUser(request);
			result.put("user", u);
			if(u.getRole_id()>0){
				Permission p=loginManage.getPermission(u.getId());
				result.put("permission", p);
			}
			result.put("years", SeperateTable.instance().getYears());
			result.setStatus(0, "");
		} else {
			result.setStatus(-1, "not login.");
		}
		return result.getResult();
	}

	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	@ResponseBody
	public Object userupdate(HttpServletRequest request) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		try {

			User loginuser = loginManage.getLoginUser(request);
			User user = checkUser(request);
			if (user.getId() <= 0)
				return result.setStatus(-2, "没有用户ID");
			if (loginuser.getId() != user.getId())
				return result.setStatus(-2, "不是登录用户");

			if (user.getPassword() == null || user.getPassword().length() < 6)
				return result.setStatus(-2, "密码长度必须大于6");

			user.setPassword(LoginManage.md5sum(user.getPassword()));
			userService.update(user);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-3, "exception");
		}

		return result.setStatus(0, "ok");
	}
	
	@RequestMapping(value = "/antistop/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAntistop(HttpServletRequest request,
			@PathVariable String name) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		
		name="%"+name+"%";
		
		List<User> list= userService.getByName2(name);
		for(User u:list){
			u.setPassword("******");
		}
		result.put("data", list);
		
		return result.setStatus(0, "");
	}
}
