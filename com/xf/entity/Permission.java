package com.xf.entity;

import java.util.ArrayList;
import java.util.List;

public class Permission {

	private int userid;
	private String username;
	private int roleid;
	private String rolename;
	private int id;
	private int pid;
	private String pname;
	private boolean enable;
	private String icon;
	private String link;
	private List<Permission> list=new ArrayList<Permission>();
	private int[] perids;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int[] getPerids() {
		return perids;
	}
	public void setPerids(int[] perids) {
		this.perids = perids;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public List<Permission> getList() {
		return list;
	}
	public void setList(List<Permission> list) {
		this.list = list;
	}
	
}
