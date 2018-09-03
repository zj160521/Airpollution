package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("devfill")
public class DevFill {

	private int id;
	private int deviceId;
	private int fuelId;
	private int fuelPid;
	private String fuelName;
	private String fuelPname;
	private String fuelUnit;
	private String fuelPlace;
	private int fillyear;
	private String fillTime;
	private Double SContent;
	private Double AshContent;
	private Double VocContent;
	private Double hoursPerDay;
	private Double daysPerYear;
	private double mTotalYear;
	private double fuelcost;
	private Double m1;
	private Double m2;
	private Double m3;
	private Double m4;
	private Double m5;
	private Double m6;
	private Double m7;
	private Double m8;
	private Double m9;
	private Double m10;
	private Double m11;
	private Double m12;
	private String remark;
	private int materialId;
	private String materialName;
	private Double materialConsume;
	private int productId;
	private String productName;
	private Double productTotalYear;
	private int status;
	private String materialUnit;
	private String productUnit;
	
	public double getFuelcost() {
		return fuelcost;
	}
	public void setFuelcost(double fuelcost) {
		this.fuelcost = fuelcost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getFuelId() {
		return fuelId;
	}
	public void setFuelId(int fuelId) {
		this.fuelId = fuelId;
	}
	public int getFuelPid() {
		return fuelPid;
	}
	public void setFuelPid(int fuelPid) {
		this.fuelPid = fuelPid;
	}
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public String getFuelPname() {
		return fuelPname;
	}
	public void setFuelPname(String fuelPname) {
		this.fuelPname = fuelPname;
	}
	public String getFuelUnit() {
		return fuelUnit;
	}
	public void setFuelUnit(String fuelUnit) {
		this.fuelUnit = fuelUnit;
	}
	public String getFuelPlace() {
		return fuelPlace;
	}
	public void setFuelPlace(String fuelPlace) {
		this.fuelPlace = fuelPlace;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public String getFillTime() {
		return fillTime;
	}
	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}
	public double getmTotalYear() {
		return mTotalYear;
	}
	public void setmTotalYear(double mTotalYear) {
		this.mTotalYear = mTotalYear;
	}
	public Double getM1() {
		return m1;
	}
	public void setM1(Double m1) {
		this.m1 = m1;
	}
	public Double getM2() {
		return m2;
	}
	public void setM2(Double m2) {
		this.m2 = m2;
	}
	public Double getM3() {
		return m3;
	}
	public void setM3(Double m3) {
		this.m3 = m3;
	}
	public Double getM4() {
		return m4;
	}
	public void setM4(Double m4) {
		this.m4 = m4;
	}
	public Double getM5() {
		return m5;
	}
	public void setM5(Double m5) {
		this.m5 = m5;
	}
	public Double getM6() {
		return m6;
	}
	public void setM6(Double m6) {
		this.m6 = m6;
	}
	public Double getM7() {
		return m7;
	}
	public void setM7(Double m7) {
		this.m7 = m7;
	}
	public Double getM8() {
		return m8;
	}
	public void setM8(Double m8) {
		this.m8 = m8;
	}
	public Double getM9() {
		return m9;
	}
	public void setM9(Double m9) {
		this.m9 = m9;
	}
	public Double getM10() {
		return m10;
	}
	public void setM10(Double m10) {
		this.m10 = m10;
	}
	public Double getM11() {
		return m11;
	}
	public void setM11(Double m11) {
		this.m11 = m11;
	}
	public Double getM12() {
		return m12;
	}
	public void setM12(Double m12) {
		this.m12 = m12;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getMaterialConsume() {
		return materialConsume;
	}
	public void setMaterialConsume(Double materialConsume) {
		this.materialConsume = materialConsume;
	}
	public Double getProductTotalYear() {
		return productTotalYear;
	}
	public void setProductTotalYear(Double productTotalYear) {
		this.productTotalYear = productTotalYear;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public void setSContent(Double sContent) {
		SContent = sContent;
	}
	public void setAshContent(Double ashContent) {
		AshContent = ashContent;
	}
	public void setVocContent(Double vocContent) {
		VocContent = vocContent;
	}
	public void setHoursPerDay(Double hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}
	public void setDaysPerYear(Double daysPerYear) {
		this.daysPerYear = daysPerYear;
	}
	public Double getSContent() {
		return SContent;
	}
	public Double getAshContent() {
		return AshContent;
	}
	public Double getVocContent() {
		return VocContent;
	}
	public Double getHoursPerDay() {
		return hoursPerDay;
	}
	public Double getDaysPerYear() {
		return daysPerYear;
	}

	
}
