package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Firewood")
public class Firewood {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private int cropType;
	private Double cultivatedArea;
	private Double yield;
	private Double utilizeRatio;
	
	private String addressStr;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
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
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getStreet() {
		return street;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public int getCropType() {
		return cropType;
	}
	public void setCropType(int cropType) {
		this.cropType = cropType;
	}
	public Double getCultivatedArea() {
		return cultivatedArea;
	}
	public void setCultivatedArea(Double cultivatedArea) {
		this.cultivatedArea = cultivatedArea;
	}
	public Double getYield() {
		return yield;
	}
	public void setYield(Double yield) {
		this.yield = yield;
	}
	public Double getUtilizeRatio() {
		return utilizeRatio;
	}
	public void setUtilizeRatio(Double utilizeRatio) {
		this.utilizeRatio = utilizeRatio;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}