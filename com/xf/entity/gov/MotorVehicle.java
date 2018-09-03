package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("MotorVehicle")
public class MotorVehicle {
	private int id;
	private int administrationID;
	private int city;
	private String cityName;
	private int status;
	private int importflag;
	private String fillTime;
	private int fillyear;
	private String variation;
	private String standard;
	private int guestmini_rentgas;
	private int guestmini_rentrest;
	private int guestmini_restgas;
	private int guestmini_restrest;
	private int guestsmall_rentgas;
	private int guestsmall_rentdiesel;
	private int guestsmall_rentrest;
	private int guestsmall_restgas;
	private int guestsmall_restdiesel;
	private int guestsmall_restrest;
	private int guestmiddle_busgas;
	private int guestmiddle_busdiesel;
	private int guestmiddle_busrest;
	private int guestmiddle_restgas;
	private int guestmiddle_restdiesel;
	private int guestmiddle_restrest;
	private int guestlarge_busgas;
	private int guestlarge_busdiesel;
	private int guestlarge_busrest;
	private int guestlarge_restgas;
	private int guestlarge_restdiesel;
	private int guestlarge_restrest;
	private int goodsmini_gas;
	private int goodsmini_diesel;
	private int goodssmall_gas;
	private int goodssmall_diesel;
	private int goodsmiddle_gas;
	private int goodsmiddle_diesel;
	private int goodslarge_gas;
	private int goodslarge_diesel;
	private int tricycle;
	private int goodsslow;
	private int motorcycle_ordinary;
	private int motorcycle_light;
	
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setAdministrationID(int administrationID) {
		this.administrationID = administrationID;
	}
	public int getAdministrationID() {
		return administrationID;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getCity() {
		return city;
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
	public void setVariation(String variation) {
		this.variation = variation;
	}
	public String getVariation() {
		return variation;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getStandard() {
		return standard;
	}
	public void setGuestmini_rentgas(int guestmini_rentgas) {
		this.guestmini_rentgas = guestmini_rentgas;
	}
	public int getGuestmini_rentgas() {
		return guestmini_rentgas;
	}
	public void setGuestmini_rentrest(int guestmini_rentrest) {
		this.guestmini_rentrest = guestmini_rentrest;
	}
	public int getGuestmini_rentrest() {
		return guestmini_rentrest;
	}
	public void setGuestmini_restgas(int guestmini_restgas) {
		this.guestmini_restgas = guestmini_restgas;
	}
	public int getGuestmini_restgas() {
		return guestmini_restgas;
	}
	public void setGuestmini_restrest(int guestmini_restrest) {
		this.guestmini_restrest = guestmini_restrest;
	}
	public int getGuestmini_restrest() {
		return guestmini_restrest;
	}
	public void setGuestsmall_rentgas(int guestsmall_rentgas) {
		this.guestsmall_rentgas = guestsmall_rentgas;
	}
	public int getGuestsmall_rentgas() {
		return guestsmall_rentgas;
	}
	public void setGuestsmall_rentdiesel(int guestsmall_rentdiesel) {
		this.guestsmall_rentdiesel = guestsmall_rentdiesel;
	}
	public int getGuestsmall_rentdiesel() {
		return guestsmall_rentdiesel;
	}
	public void setGuestsmall_rentrest(int guestsmall_rentrest) {
		this.guestsmall_rentrest = guestsmall_rentrest;
	}
	public int getGuestsmall_rentrest() {
		return guestsmall_rentrest;
	}
	public void setGuestsmall_restgas(int guestsmall_restgas) {
		this.guestsmall_restgas = guestsmall_restgas;
	}
	public int getGuestsmall_restgas() {
		return guestsmall_restgas;
	}
	public void setGuestsmall_restdiesel(int guestsmall_restdiesel) {
		this.guestsmall_restdiesel = guestsmall_restdiesel;
	}
	public int getGuestsmall_restdiesel() {
		return guestsmall_restdiesel;
	}
	public void setGuestsmall_restrest(int guestsmall_restrest) {
		this.guestsmall_restrest = guestsmall_restrest;
	}
	public int getGuestsmall_restrest() {
		return guestsmall_restrest;
	}
	public void setGuestmiddle_busgas(int guestmiddle_busgas) {
		this.guestmiddle_busgas = guestmiddle_busgas;
	}
	public int getGuestmiddle_busgas() {
		return guestmiddle_busgas;
	}
	public void setGuestmiddle_busdiesel(int guestmiddle_busdiesel) {
		this.guestmiddle_busdiesel = guestmiddle_busdiesel;
	}
	public int getGuestmiddle_busdiesel() {
		return guestmiddle_busdiesel;
	}
	public void setGuestmiddle_busrest(int guestmiddle_busrest) {
		this.guestmiddle_busrest = guestmiddle_busrest;
	}
	public int getGuestmiddle_busrest() {
		return guestmiddle_busrest;
	}
	public void setGuestmiddle_restgas(int guestmiddle_restgas) {
		this.guestmiddle_restgas = guestmiddle_restgas;
	}
	public int getGuestmiddle_restgas() {
		return guestmiddle_restgas;
	}
	public void setGuestmiddle_restdiesel(int guestmiddle_restdiesel) {
		this.guestmiddle_restdiesel = guestmiddle_restdiesel;
	}
	public int getGuestmiddle_restdiesel() {
		return guestmiddle_restdiesel;
	}
	public void setGuestmiddle_restrest(int guestmiddle_restrest) {
		this.guestmiddle_restrest = guestmiddle_restrest;
	}
	public int getGuestmiddle_restrest() {
		return guestmiddle_restrest;
	}
	public void setGuestlarge_busgas(int guestlarge_busgas) {
		this.guestlarge_busgas = guestlarge_busgas;
	}
	public int getGuestlarge_busgas() {
		return guestlarge_busgas;
	}
	public void setGuestlarge_busdiesel(int guestlarge_busdiesel) {
		this.guestlarge_busdiesel = guestlarge_busdiesel;
	}
	public int getGuestlarge_busdiesel() {
		return guestlarge_busdiesel;
	}
	public void setGuestlarge_busrest(int guestlarge_busrest) {
		this.guestlarge_busrest = guestlarge_busrest;
	}
	public int getGuestlarge_busrest() {
		return guestlarge_busrest;
	}
	public void setGuestlarge_restgas(int guestlarge_restgas) {
		this.guestlarge_restgas = guestlarge_restgas;
	}
	public int getGuestlarge_restgas() {
		return guestlarge_restgas;
	}
	public void setGuestlarge_restdiesel(int guestlarge_restdiesel) {
		this.guestlarge_restdiesel = guestlarge_restdiesel;
	}
	public int getGuestlarge_restdiesel() {
		return guestlarge_restdiesel;
	}
	public void setGuestlarge_restrest(int guestlarge_restrest) {
		this.guestlarge_restrest = guestlarge_restrest;
	}
	public int getGuestlarge_restrest() {
		return guestlarge_restrest;
	}
	public void setGoodsmini_gas(int goodsmini_gas) {
		this.goodsmini_gas = goodsmini_gas;
	}
	public int getGoodsmini_gas() {
		return goodsmini_gas;
	}
	public void setGoodsmini_diesel(int goodsmini_diesel) {
		this.goodsmini_diesel = goodsmini_diesel;
	}
	public int getGoodsmini_diesel() {
		return goodsmini_diesel;
	}
	public void setGoodssmall_gas(int goodssmall_gas) {
		this.goodssmall_gas = goodssmall_gas;
	}
	public int getGoodssmall_gas() {
		return goodssmall_gas;
	}
	public void setGoodssmall_diesel(int goodssmall_diesel) {
		this.goodssmall_diesel = goodssmall_diesel;
	}
	public int getGoodssmall_diesel() {
		return goodssmall_diesel;
	}
	public void setGoodsmiddle_gas(int goodsmiddle_gas) {
		this.goodsmiddle_gas = goodsmiddle_gas;
	}
	public int getGoodsmiddle_gas() {
		return goodsmiddle_gas;
	}
	public void setGoodsmiddle_diesel(int goodsmiddle_diesel) {
		this.goodsmiddle_diesel = goodsmiddle_diesel;
	}
	public int getGoodsmiddle_diesel() {
		return goodsmiddle_diesel;
	}
	public void setGoodslarge_gas(int goodslarge_gas) {
		this.goodslarge_gas = goodslarge_gas;
	}
	public int getGoodslarge_gas() {
		return goodslarge_gas;
	}
	public void setGoodslarge_diesel(int goodslarge_diesel) {
		this.goodslarge_diesel = goodslarge_diesel;
	}
	public int getGoodslarge_diesel() {
		return goodslarge_diesel;
	}
	public void setTricycle(int tricycle) {
		this.tricycle = tricycle;
	}
	public int getTricycle() {
		return tricycle;
	}
	public void setGoodsslow(int goodsslow) {
		this.goodsslow = goodsslow;
	}
	public int getGoodsslow() {
		return goodsslow;
	}
	public void setMotorcycle_ordinary(int motorcycle_ordinary) {
		this.motorcycle_ordinary = motorcycle_ordinary;
	}
	public int getMotorcycle_ordinary() {
		return motorcycle_ordinary;
	}
	public void setMotorcycle_light(int motorcycle_light) {
		this.motorcycle_light = motorcycle_light;
	}
	public int getMotorcycle_light() {
		return motorcycle_light;
	}
}