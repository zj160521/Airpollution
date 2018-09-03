package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Vgovimport")
public class Vgovimport {
	private int id;
	private String name;
	private int fileid;
	private String provinceName;	
	private String cityName;
	private String contact;
	private String contactNo;
	private String checkfile;
	private String importfile;
	private int status;
	private String govname;
	private String reportname;
	private String reportDesp;
	private int fillyear;
	
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
	public String getGovname() {
		return govname;
	}
	public void setGovname(String govname) {
		this.govname = govname;
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
	
	
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
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
	public String getCheckfile() {
		return checkfile;
	}
	public void setCheckfile(String checkfile) {
		this.checkfile = checkfile;
	}
	public String getImportfile() {
		return importfile;
	}
	public void setImportfile(String importfile) {
		this.importfile = importfile;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReportDesp() {
		return reportDesp;
	}
	public void setReportDesp(String reportDesp) {
		this.reportDesp = reportDesp;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	
}
