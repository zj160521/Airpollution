package com.xf.vo;

import java.util.List;
import java.util.Map;

public class EquipmentM {
	private int id;
	private int accountid;
	private int status;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private String cityName;
//	private int etype;
	private List<Map> area;
//	private int area;
//	private String emodel;
//	private int enumber;
//	private double eduration;
	
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getId() {
		return id;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}
	public String getFillTime() {
		return fillTime;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getProvince() {
		return province;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getCity() {
		return city;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public int getTown() {
		return town;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getCountry() {
		return country;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public int getStreet() {
		return street;
	}
//	public void setEtype(int etype) {
//		this.etype = etype;
//	}
//	public int getEtype() {
//		return etype;
//	}
	public List<Map> getArea() {
		return area;
	}
	public void setArea(List<Map> area) {
		this.area = area;
	}
	
}