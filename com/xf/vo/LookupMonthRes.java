package com.xf.vo;

public class LookupMonthRes {
	private int month;
	private int pollutionid;
	private String pollutantName;
	private double total;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getPollutionid() {
		return pollutionid;
	}
	public void setPollutionid(int pollutionid) {
		this.pollutionid = pollutionid;
	}
	
	public String getPollutantName() {
		return pollutantName;
	}
	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
