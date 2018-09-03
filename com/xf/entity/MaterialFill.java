package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("materialfill")
public class MaterialFill {
	int id;
	int productFillId;
	int materialId;
	String fillTime;
	int fillyear;
	double consumeOfYear;
	String unit;
	String remark;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductFillId() {
		return productFillId;
	}
	public void setProductFillId(int productFillId) {
		this.productFillId = productFillId;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
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
	public double getConsumeOfYear() {
		return consumeOfYear;
	}
	public void setConsumeOfYear(double consumeOfYear) {
		this.consumeOfYear = consumeOfYear;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
