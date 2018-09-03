package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("companyfill")
public class CompanyFill {

	private int id;
	private int enterpriceId;
	private String fillTime;
	private int fillyear;
	private double gdp;
	private double daysOfWork;
	private double hoursOfDay;
	private double totalHour;
	private double totalElec;
	private int status;
	private int[] companyId;
	private int type;
	private int all_status;
	
	public int getAll_status() {
		return all_status;
	}
	public void setAll_status(int all_status) {
		this.all_status = all_status;
	}
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
	public double getGdp() {
		return gdp;
	}
	public void setGdp(double gdp) {
		this.gdp = gdp;
	}
	
	public double getDaysOfWork() {
		return daysOfWork;
	}
	public void setDaysOfWork(double daysOfWork) {
		this.daysOfWork = daysOfWork;
	}
	public double getHoursOfDay() {
		return hoursOfDay;
	}
	public void setHoursOfDay(double hoursOfDay) {
		this.hoursOfDay = hoursOfDay;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(double totalHour) {
		this.totalHour = totalHour;
	}
	public double getTotalElec() {
		return totalElec;
	}
	public void setTotalElec(double totalElec) {
		this.totalElec = totalElec;
	}
	public int[] getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int[] companyId) {
		this.companyId = companyId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
