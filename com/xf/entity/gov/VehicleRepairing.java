package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("VehicleRepairing")
public class VehicleRepairing {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private int class1Number;
	private int class2Number;
	private int class3Number;
	private String addressStr;
	private int he;
	private int status;
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
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
	public int getClass1Number() {
		return class1Number;
	}
	public void setClass1Number(int class1Number) {
		this.class1Number = class1Number;
	}
	public int getClass2Number() {
		return class2Number;
	}
	public void setClass2Number(int class2Number) {
		this.class2Number = class2Number;
	}
	public int getClass3Number() {
		return class3Number;
	}
	public void setClass3Number(int class3Number) {
		this.class3Number = class3Number;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public int getHe() {
		return he;
	}
	public void setHe(int he) {
		this.he = he;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	}