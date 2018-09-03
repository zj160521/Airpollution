package com.xf.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

@Alias("VgovHande")
public class VgovHande {
	private int id;
	private String name;
	private int typeid;
	private String contact;
	private String contactNo;
	private String provinceName;
	private String cityName;
	private String govname;
	private List<Map<String,String>> list;
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getGovname() {
		return govname;
	}
	public void setGovname(String govname) {
		this.govname = govname;
	}
	public List<Map<String, String>> getList() {
		return list;
	}
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
	
	
	
}
