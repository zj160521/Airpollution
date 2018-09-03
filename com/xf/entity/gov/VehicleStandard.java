package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("VehicleStandard")
public class VehicleStandard {
	private int id;
	private int vehiclemodel;
	private String gastype;
	private String startdate;
	private String enddate;
	private int standard;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(int vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public String getGastype() {
		return gastype;
	}
	public void setGastype(String gastype) {
		this.gastype = gastype;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	
}
