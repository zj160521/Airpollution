package com.xf.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("facility")
public class ControlFacility {

	private int id;
	private int enterpriceId;
	private int pollutantId;
	private int outletId;
	private String facilityModel;
	private int technique1;
	private int technique2;
	private int serial;
	private int enabled;
	private String remark;
	private int status;
	
	private String pollutantName;
	private String technique1Name;
	private String technique2Name;
	private String outletSerial;
	
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getPollutantName() {
		return pollutantName;
	}
	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}
	public String getTechnique1Name() {
		return technique1Name;
	}
	public void setTechnique1Name(String technique1Name) {
		this.technique1Name = technique1Name;
	}
	public String getTechnique2Name() {
		return technique2Name;
	}
	public void setTechnique2Name(String technique2Name) {
		this.technique2Name = technique2Name;
	}
	public String getOutletSerial() {
		return outletSerial;
	}
	public void setOutletSerial(String outletSerial) {
		this.outletSerial = outletSerial;
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
	public int getPollutantId() {
		return pollutantId;
	}
	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
	}
	public int getOutletId() {
		return outletId;
	}
	public void setOutletId(int outletId) {
		this.outletId = outletId;
	}
	public String getFacilityModel() {
		return facilityModel;
	}
	public void setFacilityModel(String facilityModel) {
		this.facilityModel = facilityModel;
	}
	public int getTechnique1() {
		return technique1;
	}
	public void setTechnique1(int technique1) {
		this.technique1 = technique1;
	}
	public int getTechnique2() {
		return technique2;
	}
	public void setTechnique2(int technique2) {
		this.technique2 = technique2;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
