package com.xf.vo;

import org.apache.ibatis.type.Alias;

@Alias("GovHandlem")
public class GovHandlem {
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
	private int count;
	private int nocert;
	private int cert;
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNocert() {
		return nocert;
	}
	public void setNocert(int nocert) {
		this.nocert = nocert;
	}
	public int getCert() {
		return cert;
	}
	public void setCert(int cert) {
		this.cert = cert;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	
}
