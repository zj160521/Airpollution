package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("MotorVehicle")
public class MotorVehicleDb {
	private int id;
	private int districtId;
	private int city;
	private String cityName;
	private int statyear;
	private int standard;
	private int status;
	private int importflag;
	private String variation;
	private int vehiclemodel;
	private int holdings;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getCity() {
		return city;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setStatyear(int statyear) {
		this.statyear = statyear;
	}
	public int getStatyear() {
		return statyear;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public int getStandard() {
		return standard;
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
	public void setVariation(String variation) {
		this.variation = variation;
	}
	public String getVariation() {
		return variation;
	}
	public void setVehiclemodel(int vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public int getVehiclemodel() {
		return vehiclemodel;
	}
	public void setHoldings(int holdings) {
		this.holdings = holdings;
	}
	public int getHoldings() {
		return holdings;
	}
}