package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("CountyArea")
public class CountyArea {
	private int id;
	private int consDustId;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private int countyConstructNumber;
	private double countyConstructArea;
	private String townname;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getTownname() {
		return townname;
	}
	public void setTownname(String townname) {
		this.townname = townname;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setCountyConstructNumber(int countyConstructNumber) {
		this.countyConstructNumber = countyConstructNumber;
	}
	public int getCountyConstructNumber() {
		return countyConstructNumber;
	}
	public void setCountyConstructArea(double countyConstructArea) {
		this.countyConstructArea = countyConstructArea;
	}
	public double getCountyConstructArea() {
		return countyConstructArea;
	}
	public int getConsDustId() {
		return consDustId;
	}
	public void setConsDustId(int consDustId) {
		this.consDustId = consDustId;
	}
}