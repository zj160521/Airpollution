package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("HouseholdFuel")
public class HouseholdFuel {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private double population;
	private double coalConsume;
	private double lifeCoal;
	private double lifeNatgas;
	private double lifeCoalSulphur;
	private double lifeCoalash;
	private String addressStr;
	private String cityName;
	private int status;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
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
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getStreet() {
		return street;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public double getPopulation() {
		return population;
	}
	public void setPopulation(double population) {
		this.population = population;
	}
	public double getCoalConsume() {
		return coalConsume;
	}
	public void setCoalConsume(double coalConsume) {
		this.coalConsume = coalConsume;
	}
	public double getLifeCoal() {
		return lifeCoal;
	}
	public void setLifeCoal(double lifeCoal) {
		this.lifeCoal = lifeCoal;
	}
	public double getLifeNatgas() {
		return lifeNatgas;
	}
	public void setLifeNatgas(double lifeNatgas) {
		this.lifeNatgas = lifeNatgas;
	}
	public double getLifeCoalSulphur() {
		return lifeCoalSulphur;
	}
	public void setLifeCoalSulphur(double lifeCoalSulphur) {
		this.lifeCoalSulphur = lifeCoalSulphur;
	}
	public double getLifeCoalash() {
		return lifeCoalash;
	}
	public void setLifeCoalash(double lifeCoalash) {
		this.lifeCoalash = lifeCoalash;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	}