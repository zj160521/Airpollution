package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("RoadDust")
public class RoadDust {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private int ksPitch;
	private int ksCement;
	private int ksNotShop;
	private int zgPitch;
	private int zgCement;
	private int zgNotShop;
	private int cgPitch;
	private int cgCement;
	private int cgNotShop;
	private int zPitch;
	private int zCement;
	private int zNotShop;
	private int rainDays;
	private int controlType;
	private String department;
	private String addressStr;
	private String control;
	private String cityName;
	private int status;
	
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
	public int getKsPitch() {
		return ksPitch;
	}
	public void setKsPitch(int ksPitch) {
		this.ksPitch = ksPitch;
	}
	public int getKsCement() {
		return ksCement;
	}
	public void setKsCement(int ksCement) {
		this.ksCement = ksCement;
	}
	public int getKsNotShop() {
		return ksNotShop;
	}
	public void setKsNotShop(int ksNotShop) {
		this.ksNotShop = ksNotShop;
	}
	public int getZgPitch() {
		return zgPitch;
	}
	public void setZgPitch(int zgPitch) {
		this.zgPitch = zgPitch;
	}
	public int getZgCement() {
		return zgCement;
	}
	public void setZgCement(int zgCement) {
		this.zgCement = zgCement;
	}
	public int getZgNotShop() {
		return zgNotShop;
	}
	public void setZgNotShop(int zgNotShop) {
		this.zgNotShop = zgNotShop;
	}
	public int getCgPitch() {
		return cgPitch;
	}
	public void setCgPitch(int cgPitch) {
		this.cgPitch = cgPitch;
	}
	public int getCgCement() {
		return cgCement;
	}
	public void setCgCement(int cgCement) {
		this.cgCement = cgCement;
	}
	public int getCgNotShop() {
		return cgNotShop;
	}
	public void setCgNotShop(int cgNotShop) {
		this.cgNotShop = cgNotShop;
	}
	public int getzPitch() {
		return zPitch;
	}
	public void setzPitch(int zPitch) {
		this.zPitch = zPitch;
	}
	public int getzCement() {
		return zCement;
	}
	public void setzCement(int zCement) {
		this.zCement = zCement;
	}
	public int getzNotShop() {
		return zNotShop;
	}
	public void setzNotShop(int zNotShop) {
		this.zNotShop = zNotShop;
	}
	public int getRainDays() {
		return rainDays;
	}
	public void setRainDays(int rainDays) {
		this.rainDays = rainDays;
	}
	public int getControlType() {
		return controlType;
	}
	public void setControlType(int controlType) {
		this.controlType = controlType;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}