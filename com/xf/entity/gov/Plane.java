package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Plane")
public class Plane {
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
	private String airport;
	private String factory;
	private String planemodel;
	private int numbers;
	private int importflag;
	private String usages;
	private String addressStr;
	
	public String getUsages() {
		return usages;
	}
	public void setUsages(String usages) {
		this.usages = usages;
	}
	public int getImportflag() {
		return importflag;
	}
	public void setImportflag(int importflag) {
		this.importflag = importflag;
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
	public void setAirport(String airport) {
		this.airport = airport;
	}
	public String getAirport() {
		return airport;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactory() {
		return factory;
	}
	public void setPlanemodel(String planemodel) {
		this.planemodel = planemodel;
	}
	public String getPlanemodel() {
		return planemodel;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	public int getNumbers() {
		return numbers;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
}