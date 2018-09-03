package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Construction")
public class Construction {
	private int id;
	private int accountid;
	private int status;
	private int importflag;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private String townname;
	private int country;
	private int street;
	private double completeArea;
	private double asphaltRoadArea;
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
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setImportflag(int importflag) {
		this.importflag = importflag;
	}
	public int getImportflag() {
		return importflag;
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
	public void setCompleteArea(double completeArea) {
		this.completeArea = completeArea;
	}
	public double getCompleteArea() {
		return completeArea;
	}
	public void setAsphaltRoadArea(double asphaltRoadArea) {
		this.asphaltRoadArea = asphaltRoadArea;
	}
	public double getAsphaltRoadArea() {
		return asphaltRoadArea;
	}
	public String getTownname() {
		return townname;
	}
	public void setTownname(String townname) {
		this.townname = townname;
	}
}