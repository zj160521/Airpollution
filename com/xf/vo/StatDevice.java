package com.xf.vo;

import org.apache.ibatis.type.Alias;

@Alias("StatDevice")
public class StatDevice {
	private int id;
	private int companyid;
	private int deviceid;
	private int pollutantId;
	private int fillyear;
	private double statvalue;
	private int devclass;
	private int fuelid;
	private String fuelname;
	private String fuelunit;
	private int fuelgroupid;
	private String fuelgroupname;
	private String pollutantName;
	private int statmonth;
	private int trade1;
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
	public int getTrade1() {
		return trade1;
	}
	public void setTrade1(int trade1) {
		this.trade1 = trade1;
	}
	public int getStatmonth() {
		return statmonth;
	}
	public void setStatmonth(int statmonth) {
		this.statmonth = statmonth;
	}
	public String getPollutantName() {
		return pollutantName;
	}
	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}
	public int getDevclass() {
		return devclass;
	}
	public void setDevclass(int devclass) {
		this.devclass = devclass;
	}
	public int getFuelid() {
		return fuelid;
	}
	public void setFuelid(int fuelid) {
		this.fuelid = fuelid;
	}
	public String getFuelname() {
		return fuelname;
	}
	public void setFuelname(String fuelname) {
		this.fuelname = fuelname;
	}
	public String getFuelunit() {
		return fuelunit;
	}
	public void setFuelunit(String fuelunit) {
		this.fuelunit = fuelunit;
	}
	public int getFuelgroupid() {
		return fuelgroupid;
	}
	public void setFuelgroupid(int fuelgroupid) {
		this.fuelgroupid = fuelgroupid;
	}
	public String getFuelgroupname() {
		return fuelgroupname;
	}
	public void setFuelgroupname(String fuelgroupname) {
		this.fuelgroupname = fuelgroupname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public int getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	public int getPollutantId() {
		return pollutantId;
	}
	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public double getStatvalue() {
		return statvalue;
	}
	public void setStatvalue(double statvalue) {
		this.statvalue = statvalue;
	}
	
}
