package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("AccountReport")
public class AccountReport {
	private int id;
	private int typeid;
	private String reportname;
	private String reportdesp;
	private int isimport;
	
	public String getReportdesp() {
		return reportdesp;
	}
	public void setReportdesp(String reportdesp) {
		this.reportdesp = reportdesp;
	}
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
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public String getReportname() {
		return reportname;
	}
	public void setIsimport(int isimport) {
		this.isimport = isimport;
	}
	public int getIsimport() {
		return isimport;
	}
}