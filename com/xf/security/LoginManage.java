package com.xf.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xf.controller.ResultObj;
import com.xf.entity.Permission;
import com.xf.entity.Company;
import com.xf.entity.User;
import com.xf.service.CompanyService;
import com.xf.service.PermissionService;
import com.xf.service.UserService;

@Component
public class LoginManage {

	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	
	private ResultObj result=new ResultObj();
	@Autowired
	private PermissionService perService;
	
	static char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	public static String md5sum(String str) throws NoSuchAlgorithmException {
		byte[] buf = str.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(buf);
		byte[] tmp = md5.digest();
		
		int j = tmp.length;
        char s[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = tmp[i];
            s[k++] = hexDigits[byte0 >>> 4 & 0xf];
            s[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(s);
	}

	public boolean isUserLogin(HttpServletRequest request) {
		
		boolean ret = false;
		
		try {
			
			HttpSession session = request.getSession();
			String authval = (String) session.getAttribute("auth");

			if (authval != null && authval.equals("user")) {
				User u = (User) session.getAttribute("user");
				if (u != null) ret = true;
			}

		} catch(Exception e) {
			ret = false;
		}
		
		return ret;
	}

	public boolean isCompanyLogin(HttpServletRequest request) {
		boolean ret = false;
		
		try {
			
			HttpSession session = request.getSession();
			String authval = (String) session.getAttribute("auth");
			if (authval != null && authval.equals("company")) {
				ret = true;
			}
			else if(authval != null && authval.equals("govenment")){
				ret = true;
			}
			
			Company c = (Company) session.getAttribute("company");
			if (c != null) ret = true;
			else ret = false;

		} catch(Exception e) {
			ret = false;
		}
		
		return ret;
	}
	
	public User getLoginUser(HttpServletRequest request) {
		
		try {
			
			if (isUserLogin(request) == true) {
				HttpSession session = request.getSession();
				User u = (User) session.getAttribute("user");
				return u;
			}

		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Company getLoginCompany(HttpServletRequest request) {
		
		try {
			
			if (isCompanyLogin(request) == true) {
				HttpSession session = request.getSession();
				Company c = (Company) session.getAttribute("company");
				return c;
			}

		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public String login(HttpServletRequest request, User user) {

		if (isUserLogin(request) == true)
			return "ok";

		HttpSession session = request.getSession();
		User u = userService.getByName(user.getUsername());

		if (u == null) {
			return "No user.";
		}

		try {
			String inputpwd = md5sum(user.getPassword());
			if (u.getPassword().equals(inputpwd)) {
				session.setAttribute("auth", "user");
				session.setAttribute("user", u);
				return "ok";
			} else {
				return "Wrong password.";
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "Server error.";
		}
	}

	public String login(HttpServletRequest request, Company company) {
		
		if (isCompanyLogin(request) == true)
			return "ok";
		
		HttpSession session = request.getSession();
		Company c = companyService.getByName(company.getName());

		if (c == null) {
			return "No user.";
		}

		try {
			String inputpwd = md5sum(company.getPassword());
			//System.out.println(c.getPassword().length() + "--" + c.getPassword());
			//System.out.println(inputpwd.length() + "--" + inputpwd);
			if (c.getPassword().equals(inputpwd)) {
				session.setAttribute("auth", "company");
				session.setAttribute("company", c);
				return "ok";
			} else {
				return "Wrong password.";
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "Server error.";
		}
	}

	public void logout(HttpServletRequest request) {
		
		try {
			HttpSession session = request.getSession();
			String authval = (String) session.getAttribute("auth");
			if (authval != null) {
				session.removeAttribute("auth");
				session.removeAttribute("user");
				session.removeAttribute("company");
				session.removeAttribute("permission");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 审核权限和存储日志
		public Object checkPermission(HttpServletRequest request, int pid) {
			User u = getLoginUser(request);
			List<Permission> list = perService.getAllPermissionByRole(u.getRole_id());
			for (Permission p : list) {
				if (p.getId() == pid) {
//					addLog(request,u,pid);
					return null;
				}
			}
			return result.setStatus(-2, "no permission.");
		}

		//存储用户日志
//		public void addLog(HttpServletRequest request,User u,int pid){
//			User_log log = new User_log();
//			log.setUser_id(u.getId());
//			log.setIp(getIpAddr(request));
//			log.setPermission_id(pid);
//			
//			userDao.addLog(log);
//		}
		
		// 获取用户角色和权限
		public Permission getPermission(int id) {
			List<Permission> list = userService.getPermission(id);
			int userid = list.get(0).getUserid();
			int roleid = list.get(0).getRoleid();
			String username = list.get(0).getUsername();
			String rolename = list.get(0).getRolename();
			List<Permission> list1 = perService.getAllPermission();
			for (Permission p : list1) {
				p.setUserid(userid);
				p.setRoleid(roleid);
				p.setUsername(username);
				p.setRolename(rolename);
			}
			for (Permission p : list1) {
				for (Permission p1 : list) {
					if (p.getId() == p1.getId()) {
						p.setEnable(true);
						break;
					}
				}
			}
			Permission per = recursiveTree(10, list1);
			return per;
		}

		public Permission recursiveTree(int id, List<Permission> list) {
			Permission node = getTreeNode(id, list);
			List<Permission> childTreeNodes = getChildTreeNodes(id, list);
			// 遍历子节点
			for (Permission child : childTreeNodes) {
				// 递归
				Permission n = recursiveTree(child.getId(), list);
				if (n != null) {
					node.getList().add(n);
				}
			}
			return node;
		}

		public Permission getTreeNode(int id, List<Permission> list) {
			for (Permission per : list) {
				if (per.getId() == id) {
					return per;
				}
			}
			return null;
		}

		public List<Permission> getChildTreeNodes(int id, List<Permission> list) {
			List<Permission> reslist = new ArrayList<Permission>();
			for (Permission per : list) {
				if (per.getPid() == id) {
					reslist.add(per);
				}
			}
			return reslist;
		}

		// 获得用户ip地址
		public String getIpAddr(HttpServletRequest request) {
			String ip = request.getHeader("X-Forwarded-For");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
}
