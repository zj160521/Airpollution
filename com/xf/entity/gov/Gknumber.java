package com.xf.entity.gov;



import org.apache.ibatis.type.Alias;

@Alias("Gknumber")
public class Gknumber {
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
	private String companyName;
	private String model;
	private String gktypeName;
	private String fuelTypeName;
	private int gktype;
	private double shippingTon;
	private int fuelType;
	private double yearlyFuel;
	private String unit;
	private String dateUsed;
	private double height;
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
	public String getGktypeName() {
		return gktypeName;
	}
	public void setGktypeName(String gktypeName) {
		this.gktypeName = gktypeName;
	}
	public String getFuelTypeName() {
		return fuelTypeName;
	}
	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
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
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModel() {
		return model;
	}
	public void setGktype(int gktype) {
		this.gktype = gktype;
	}
	public int getGktype() {
		return gktype;
	}
	public void setShippingTon(double shippingTon) {
		this.shippingTon = shippingTon;
	}
	public double getShippingTon() {
		return shippingTon;
	}
	public void setFuelType(int fuelType) {
		this.fuelType = fuelType;
	}
	public int getFuelType() {
		return fuelType;
	}
	public void setYearlyFuel(double yearlyFuel) {
		this.yearlyFuel = yearlyFuel;
	}
	public double getYearlyFuel() {
		return yearlyFuel;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit() {
		return unit;
	}

	public String getDateUsed() {
		return dateUsed;
	}
	public void setDateUsed(String dateUsed) {
		this.dateUsed = dateUsed;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getHeight() {
		return height;
	}
}