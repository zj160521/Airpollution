package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("VgovTName")
public class VgovTName {
	private int typeid;
	private String reportname;
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	
}
