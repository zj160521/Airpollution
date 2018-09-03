package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Gasstation")
public class Gasstation {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private String townName;
	private int country;
	private int street;
	private String countryname;
	private String streetname;
	private String gasStationName;
	private double gasolineGross;
	private double dieselGross;
	private double natgasGross;
	private double gasolineSellMonth;
	private double dieselSellMonth;
	private double natgasSellMonth;
	private int gasolineSpear;
	private int dieselSpear;
	private int natgasSpear;
	private int recycleDevice;
	private double recovery;
	private String addressStr;
	private int status;
	private int importflag;
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public int getImportflag() {
		return importflag;
	}
	public void setImportflag(int importflag) {
		this.importflag = importflag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
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
	public void setGasStationName(String gasStationName) {
		this.gasStationName = gasStationName;
	}
	public String getGasStationName() {
		return gasStationName;
	}
	public void setGasolineGross(double gasolineGross) {
		this.gasolineGross = gasolineGross;
	}
	public double getGasolineGross() {
		return gasolineGross;
	}
	public void setDieselGross(double dieselGross) {
		this.dieselGross = dieselGross;
	}
	public double getDieselGross() {
		return dieselGross;
	}
	public void setNatgasGross(double natgasGross) {
		this.natgasGross = natgasGross;
	}
	public double getNatgasGross() {
		return natgasGross;
	}
	public void setGasolineSellMonth(double gasolineSellMonth) {
		this.gasolineSellMonth = gasolineSellMonth;
	}
	public double getGasolineSellMonth() {
		return gasolineSellMonth;
	}
	public void setDieselSellMonth(double dieselSellMonth) {
		this.dieselSellMonth = dieselSellMonth;
	}
	public double getDieselSellMonth() {
		return dieselSellMonth;
	}
	public void setNatgasSellMonth(double natgasSellMonth) {
		this.natgasSellMonth = natgasSellMonth;
	}
	public double getNatgasSellMonth() {
		return natgasSellMonth;
	}
	public void setGasolineSpear(int gasolineSpear) {
		this.gasolineSpear = gasolineSpear;
	}
	public int getGasolineSpear() {
		return gasolineSpear;
	}
	public void setDieselSpear(int dieselSpear) {
		this.dieselSpear = dieselSpear;
	}
	public int getDieselSpear() {
		return dieselSpear;
	}
	public void setNatgasSpear(int natgasSpear) {
		this.natgasSpear = natgasSpear;
	}
	public int getNatgasSpear() {
		return natgasSpear;
	}
	public void setRecycleDevice(int recycleDevice) {
		this.recycleDevice = recycleDevice;
	}
	public int getRecycleDevice() {
		return recycleDevice;
	}
	public void setRecovery(double recovery) {
		this.recovery = recovery;
	}
	public double getRecovery() {
		return recovery;
	}
}