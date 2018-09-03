package com.xf.vo;

public class Gis {
	private String type;
	private int startyear;
	private int endyear;
	private int tradeid;
	private int pollutantid;
	private int issmall;
	private int cityId;
	private int shippingTon;
	
	
	public int getShippingTon() {
		return shippingTon;
	}
	public void setShippingTon(int shippingTon) {
		this.shippingTon = shippingTon;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getIssmall() {
		return issmall;
	}
	public void setIssmall(int issmall) {
		this.issmall = issmall;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStartyear() {
		return startyear;
	}
	public void setStartyear(int startyear) {
		this.startyear = startyear;
	}
	public int getEndyear() {
		return endyear;
	}
	public void setEndyear(int endyear) {
		this.endyear = endyear;
	}
	
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public int getPollutantid() {
		return pollutantid;
	}
	public void setPollutantid(int pollutantid) {
		this.pollutantid = pollutantid;
	}
	
	
}
