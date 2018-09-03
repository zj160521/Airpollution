package com.xf.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("outletfill")
public class OutletFill {
	private int id;
	private int outletId;
	private String fillTime;
	private int fillyear;
	private String detectDevice;
	private double outletVelocity;
	private double outletTemperature;
	private double outletFlow;
	private double outletTotal;
	private String remark;
	private int status;
	
	private List<OutletPollutant> pollutants;
	
	public String getDetectDevice() {
		return detectDevice;
	}
	public void setDetectDevice(String detectDevice) {
		this.detectDevice = detectDevice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOutletId() {
		return outletId;
	}
	public void setOutletId(int outletId) {
		this.outletId = outletId;
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
	public double getOutletVelocity() {
		return outletVelocity;
	}
	public void setOutletVelocity(double outletVelocity) {
		this.outletVelocity = outletVelocity;
	}
	public double getOutletTemperature() {
		return outletTemperature;
	}
	public void setOutletTemperature(double outletTemperature) {
		this.outletTemperature = outletTemperature;
	}
	public double getOutletFlow() {
		return outletFlow;
	}
	public void setOutletFlow(double outletFlow) {
		this.outletFlow = outletFlow;
	}
	public double getOutletTotal() {
		return outletTotal;
	}
	public void setOutletTotal(double outletTotal) {
		this.outletTotal = outletTotal;
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
	public List<OutletPollutant> getPollutants() {
		return pollutants;
	}
	public void setPollutants(List<OutletPollutant> pollutants) {
		this.pollutants = pollutants;
	}
	
}
