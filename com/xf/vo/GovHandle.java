package com.xf.vo;

import org.apache.ibatis.type.Alias;

@Alias("GovHandle")
public class GovHandle {
	private int id;
	private String name;
	private int typeid;
	private String contact;
	private String contactNo;
	private String provinceName;
	private String cityName;
	private String govname;
	private String reportdesp;
	private String reportname;
	private int status;
	private int cnt;
	
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
	public String getReportdesp() {
		return reportdesp;
	}
	public void setReportdesp(String reportdesp) {
		this.reportdesp = reportdesp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	

}
