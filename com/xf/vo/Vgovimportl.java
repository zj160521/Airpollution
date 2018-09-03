package com.xf.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
@Alias("Vgovimportl")
public class Vgovimportl {
	private int id;
	private String name;
	private int fileid;
	private String provinceName;
	private String cityName;
	private String contact;
	private String contactNo;
	private List<Map> allFile;
	private String govname;
	private String reportname;
	
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
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public String getGovname() {
		return govname;
	}
	public void setGovname(String govname) {
		this.govname = govname;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<Map> getAllFile() {
		return allFile;
	}
	public void setAllFile(List<Map> allFile) {
		this.allFile = allFile;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	
}
