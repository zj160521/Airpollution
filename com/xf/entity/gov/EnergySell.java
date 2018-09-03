package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("EnergySell")
public class EnergySell {
	private int id;
	private int accountid;
	private int status;
	private int importflag;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private String purpose;
	private double coal;
	private double gas;
	private double liqgas;
	private double oil;
	private double elec;
	private String addressStr;
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setImportflag(int importflag) {
		this.importflag = importflag;
	}
	public int getImportflag() {
		return importflag;
	}
	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}
	public String getFillTime() {
		return fillTime;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getProvince() {
		return province;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getCity() {
		return city;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public int getTown() {
		return town;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getCountry() {
		return country;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public int getStreet() {
		return street;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setCoal(double coal) {
		this.coal = coal;
	}
	public double getCoal() {
		return coal;
	}
	public void setGas(double gas) {
		this.gas = gas;
	}
	public double getGas() {
		return gas;
	}
	public void setLiqgas(double liqgas) {
		this.liqgas = liqgas;
	}
	public double getLiqgas() {
		return liqgas;
	}
	public void setOil(double oil) {
		this.oil = oil;
	}
	public double getOil() {
		return oil;
	}
	public void setElec(double elec) {
		this.elec = elec;
	}
	public double getElec() {
		return elec;
	}
}