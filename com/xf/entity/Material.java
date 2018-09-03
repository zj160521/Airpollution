package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("material")
public class Material {

	int id;
	int enterpriceId;
	String materialSerial;
	String materialName;
	String unit;
	String remark;
	int fillyear;
	
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public String getMaterialSerial() {
		return materialSerial;
	}
	public void setMaterialSerial(String materialSerial) {
		this.materialSerial = materialSerial;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
