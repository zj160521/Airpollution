package com.xf.vo;

public class LookupCityTradeASRes {
	private int cityid;
	private String districtName;
	private String pollutantName;
	private int tradeid;
	private String tradeName;
	private double sum;
	public String getPollutantName() {
		return pollutantName;
	}
	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
