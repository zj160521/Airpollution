package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("productfill")
public class ProductFill {
	int id;
	int productId;
	int enterpriceId;
	int produceStepId;
	String fillTime;
	int fillyear;
	Double hoursPerDay;
	Double daysPerYear;
	double designOutput;
	double realOutput;
	private int status;
	Double m1;
	Double m2;
	Double m3;
	Double m4;
	Double m5;
	Double m6;
	Double m7;
	Double m8;
	Double m9;
	Double m10;
	Double m11;
	Double m12;
	String remark;
	int fuelId;
	String fuelName;
	String fuelunit;
	double fuelValue;
	String productName;
	String productUnit;
	String materialUnit;
	String materialName;
	int materialId;
	String totalYear;
	
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public String getTotalYear() {
		return totalYear;
	}
	public void setTotalYear(String totalYear) {
		this.totalYear = totalYear;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getFuelunit() {
		return fuelunit;
	}
	public void setFuelunit(String fuelunit) {
		this.fuelunit = fuelunit;
	}
	public double getFuelValue() {
		return fuelValue;
	}
	public void setFuelValue(double fuelValue) {
		this.fuelValue = fuelValue;
	}
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public int getFuelId() {
		return fuelId;
	}
	public void setFuelId(int fuelId) {
		this.fuelId = fuelId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public int getProduceStepId() {
		return produceStepId;
	}
	public void setProduceStepId(int produceStepId) {
		this.produceStepId = produceStepId;
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
	public Double getHoursPerDay() {
		return hoursPerDay;
	}
	public void setHoursPerDay(Double hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}
	public Double getDaysPerYear() {
		return daysPerYear;
	}
	public void setDaysPerYear(Double daysPerYear) {
		this.daysPerYear = daysPerYear;
	}
	public double getDesignOutput() {
		return designOutput;
	}
	public void setDesignOutput(double designOutput) {
		this.designOutput = designOutput;
	}
	public double getRealOutput() {
		return realOutput;
	}
	public void setRealOutput(double realOutput) {
		this.realOutput = realOutput;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	
	
	}
