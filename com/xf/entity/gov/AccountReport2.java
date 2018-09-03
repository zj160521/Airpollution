package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("AccountReport2")
public class AccountReport2 {
	private int id;
	private int typeid;
	private String address;
	private String reportname;
	private String reportdesp;
	private int isimport;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportdesp(String reportdesp) {
		this.reportdesp = reportdesp;
	}
	public String getReportdesp() {
		return reportdesp;
	}
	public void setIsimport(int isimport) {
		this.isimport = isimport;
	}
	public int getIsimport() {
		return isimport;
	}
}