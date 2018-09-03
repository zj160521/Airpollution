package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("elecfill")
public class ElecFill {

	private int id;
	private int elecId;
	private int elecDeviceId;
	private Double elecPerDay;
	private Double elecPerYear;
	private int status;
	private String elecDeviceName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getElecId() {
		return elecId;
	}
	public void setElecId(int elecId) {
		this.elecId = elecId;
	}
	public int getElecDeviceId() {
		return elecDeviceId;
	}
	public void setElecDeviceId(int elecDeviceId) {
		this.elecDeviceId = elecDeviceId;
	}
	public Double getElecPerDay() {
		return elecPerDay;
	}
	public void setElecPerDay(Double elecPerDay) {
		this.elecPerDay = elecPerDay;
	}
	public Double getElecPerYear() {
		return elecPerYear;
	}
	public void setElecPerYear(Double elecPerYear) {
		this.elecPerYear = elecPerYear;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getElecDeviceName() {
		return elecDeviceName;
	}
	public void setElecDeviceName(String elecDeviceName) {
		this.elecDeviceName = elecDeviceName;
	}
	
	
	}
