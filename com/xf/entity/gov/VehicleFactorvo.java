package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("VehicleFactorvo")
public class VehicleFactorvo {
private String vehiclemodel;
private String pollutantName;
private String standard;
private double factor;
public String getVehiclemodel() {
	return vehiclemodel;
}
public void setVehiclemodel(String vehiclemodel) {
	this.vehiclemodel = vehiclemodel;
}
public String getPollutantName() {
	return pollutantName;
}
public void setPollutantName(String pollutantName) {
	this.pollutantName = pollutantName;
}
public String getStandard() {
	return standard;
}
public void setStandard(String standard) {
	this.standard = standard;
}
public double getFactor() {
	return factor;
}
public void setFactor(double factor) {
	this.factor = factor;
}
}
