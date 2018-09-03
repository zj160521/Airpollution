package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("EquipmentFarm")
public class EquipmentFarm {
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
	private String farmtype;
	private String farmtype2;
	private int enumber;
	private double ekw;
	private double fuelconsume;
	private String addressStr;
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	public void setFarmtype(String farmtype) {
		this.farmtype = farmtype;
	}
	public String getFarmtype() {
		return farmtype;
	}
	public void setFarmtype2(String farmtype2) {
		this.farmtype2 = farmtype2;
	}
	public String getFarmtype2() {
		return farmtype2;
	}
	
	public int getEnumber() {
		return enumber;
	}
	public void setEnumber(int enumber) {
		this.enumber = enumber;
	}
	public double getEkw() {
		return ekw;
	}
	public void setEkw(double ekw) {
		this.ekw = ekw;
	}
	public double getFuelconsume() {
		return fuelconsume;
	}
	public void setFuelconsume(double fuelconsume) {
		this.fuelconsume = fuelconsume;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	
}