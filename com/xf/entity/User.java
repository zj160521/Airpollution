package com.xf.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {

	private int id;
	private String username;
	private int typeid;
	private int usertype;
	private String password;
	private int province;
	private int city;
	private String remark;
	private Integer town;
	private String menuitems;
	private String[] targetRange;
	private List<String> menulist;
	private List<String> targetlist;
	private String address;
	private int role_id;
	private String role_name;
	
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<String> menulist) {
		this.menulist = menulist;
	}

	public List<String> getTargetlist() {
		return targetlist;
	}

	public void setTargetlist(List<String> targetlist) {
		this.targetlist = targetlist;
	}

	public String[] getTargetRange() {
		return targetRange;
	}

	public void setTargetRange(String[] targetRange) {
		this.targetRange = targetRange;
	}

	public Integer getTown() {
		return town;
	}

	public void setTown(Integer town) {
		this.town = town;
	}

	public String getMenuitems() {
		return menuitems;
	}

	public void setMenuitems(String funs) {
		this.menuitems = funs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(int id, String username, int usertype, String password,
			String remark) {
		super();
		this.id = id;
		this.username = username;
		this.usertype = usertype;
		this.password = password;
		this.setRemark(remark);
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

}
