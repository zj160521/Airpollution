package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("VehicleAction")
public class VehicleAction {
	private int id;
	private int accountid;
	private int status;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private int serial;
	private String vehicletype;
	private String platescolor;
	private String registerdate ;
	private String checkdate ;
	private double mileage;
	private String gastype;
	private int importflag;
	private double avgmile;
	private int standard;
	private int vehiclemodel;
	
//	public VehicleAction(){
//		
//	}
//	public VehicleAction(VehicleAction va){
//		this.id=va.id;
//		this.accountid=va.accountid;
//		this.status=va.status;
//		this.fillTime=va.fillTime;
//		this.fillyear=va.fillyear;
//		this.province=va.province;
//		this.city=va.city;
//		this.town=va.town;
//		this.country=va.country;
//		this.street=va.street;
//		this.serial=va.serial;
//		this.vehicletype=va.vehicletype;
//		this.platescolor=va.platescolor;
//		this.registerdate=va.registerdate;
//		this.checkdate=va.checkdate;
//		this.mileage=va.mileage;
//		this.gastype=va.gastype;
//		this.importflag=va.importflag;
//		this.avgmile=va.avgmile;
//		this.standard=va.standard;
//		this.vehiclemodel=va.vehiclemodel;
//	}
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	public int getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(int vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public double getAvgmile() {
		return avgmile;
	}
	public void setAvgmile(double avgmile) {
		this.avgmile = avgmile;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public int getImportflag() {
		return importflag;
	}
	public void setImportflag(int importflag) {
		this.importflag = importflag;
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
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getSerial() {
		return serial;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setPlatescolor(String platescolor) {
		this.platescolor = platescolor;
	}
	public String getPlatescolor() {
		return platescolor;
	}
	
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public void setGastype(String gastype) {
		this.gastype = gastype;
	}
	public String getGastype() {
		return gastype;
	}
}