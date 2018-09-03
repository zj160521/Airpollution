package com.xf.entity.gov;

public class MotorStat {
	private int id;
	private int pollutantId;
	private int province;
	private int city;
	private int town;
	private int fillyear;
	private int vehiclemodel;
	private double statvalue;
	private int standard;
	private String stat_exp;
	private String stat_valtype;
	private String stat_value;
	private String stat_factor;
	private double stat_dsrate;
	
	public String getStat_exp() {
		return stat_exp;
	}
	public void setStat_exp(String stat_exp) {
		this.stat_exp = stat_exp;
	}
	public String getStat_valtype() {
		return stat_valtype;
	}
	public void setStat_valtype(String stat_valtype) {
		this.stat_valtype = stat_valtype;
	}
	
	public String getStat_value() {
		return stat_value;
	}
	public void setStat_value(String stat_value) {
		this.stat_value = stat_value;
	}
	public String getStat_factor() {
		return stat_factor;
	}
	public void setStat_factor(String stat_factor) {
		this.stat_factor = stat_factor;
	}
	public double getStat_dsrate() {
		return stat_dsrate;
	}
	public void setStat_dsrate(double stat_dsrate) {
		this.stat_dsrate = stat_dsrate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPollutantId() {
		return pollutantId;
	}
	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(int vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public double getStatvalue() {
		return statvalue;
	}
	public void setStatvalue(double statvalue) {
		this.statvalue = statvalue;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	
}
