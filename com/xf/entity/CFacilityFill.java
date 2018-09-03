package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("cfacilityfill")
public class CFacilityFill {

	private int id;
	private int facilityId;
	private String fillTime;
	private int fillyear;
	private double daysPerYear;
	private double collectRate;
	private double disRate;
	private int status;
	private double yearCost;
	private String materialName;
	private double materialConsume;
	private double NH3Release;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
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
	public double getDaysPerYear() {
		return daysPerYear;
	}
	public void setDaysPerYear(double daysPerYear) {
		this.daysPerYear = daysPerYear;
	}
	public double getCollectRate() {
		return collectRate;
	}
	public void setCollectRate(double collectRate) {
		this.collectRate = collectRate;
	}
	public double getDisRate() {
		return disRate;
	}
	public void setDisRate(double disRate) {
		this.disRate = disRate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getYearCost() {
		return yearCost;
	}
	public void setYearCost(double yearCost) {
		this.yearCost = yearCost;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public double getMaterialConsume() {
		return materialConsume;
	}
	public void setMaterialConsume(double materialConsume) {
		this.materialConsume = materialConsume;
	}
	public double getNH3Release() {
		return NH3Release;
	}
	public void setNH3Release(double nH3Release) {
		NH3Release = nH3Release;
	}
	
	
	}
