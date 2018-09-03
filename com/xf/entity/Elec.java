package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("elec")
public class Elec {

	private int id;
	private int enterpriceId;
	private String fillTime;
	private int fillyear;
	private double yearTotal;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public String getFillTime() {
		return fillTime;
	}
	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public double getYearTotal() {
		return yearTotal;
	}
	public void setYearTotal(double yearTotal) {
		this.yearTotal = yearTotal;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
