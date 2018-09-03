package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("DumpField")
public class DumpField {
	private int id;
	private int accountid;
	private int status;
	private int importflag;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private String country;
	private String street;
	private String factoryname;
	private String factorytype;
	private double rubbish_burn;
	private double rubbish_bury;
	private double rubbish_hill;
	private double rubbish_capability;
	private double rubbish_used;
	private double sewerage_total;
	private String townName;
	private String cityName;
	private String provinceName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
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
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry() {
		return country;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet() {
		return street;
	}
	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}
	public String getFactoryname() {
		return factoryname;
	}
	public void setFactorytype(String factorytype) {
		this.factorytype = factorytype;
	}
	public String getFactorytype() {
		return factorytype;
	}
	public void setRubbish_burn(double rubbish_burn) {
		this.rubbish_burn = rubbish_burn;
	}
	public double getRubbish_burn() {
		return rubbish_burn;
	}
	public void setRubbish_bury(double rubbish_bury) {
		this.rubbish_bury = rubbish_bury;
	}
	public double getRubbish_bury() {
		return rubbish_bury;
	}
	public void setRubbish_hill(double rubbish_hill) {
		this.rubbish_hill = rubbish_hill;
	}
	public double getRubbish_hill() {
		return rubbish_hill;
	}
	public void setRubbish_capability(double rubbish_capability) {
		this.rubbish_capability = rubbish_capability;
	}
	public double getRubbish_capability() {
		return rubbish_capability;
	}
	public void setRubbish_used(double rubbish_used) {
		this.rubbish_used = rubbish_used;
	}
	public double getRubbish_used() {
		return rubbish_used;
	}
	public void setSewerage_total(double sewerage_total) {
		this.sewerage_total = sewerage_total;
	}
	public double getSewerage_total() {
		return sewerage_total;
	}
}