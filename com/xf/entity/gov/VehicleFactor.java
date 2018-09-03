package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("VehicleFactor")
public class VehicleFactor {
	private int id;
	private String pollutant;
	private String standard;
	private int pollutantId;
	private int standardId;
	private int vehiclemodel;
	private double factor;
	private double guestmini_rentgas;
	private double guestmini_rentrest;
	private double guestmini_restgas;
	private double guestmini_restrest;
	private double guestsmall_rentgas;
	private double guestsmall_rentdiesel;
	private double guestsmall_rentrest;
	private double guestsmall_restgas;
	private double guestsmall_restdiesel;
	private double guestsmall_restrest;
	private double guestmiddle_busgas;
	private double guestmiddle_busdiesel;
	private double guestmiddle_busrest;
	private double guestmiddle_restgas;
	private double guestmiddle_restdiesel;
	private double guestmiddle_restrest;
	private double guestlarge_busgas;
	private double guestlarge_busdiesel;
	private double guestlarge_busrest;
	private double guestlarge_restgas;
	private double guestlarge_restdiesel;
	private double guestlarge_restrest;
	private double goodsmini_gas;
	private double goodsmini_diesel;
	private double goodssmall_gas;
	private double goodssmall_diesel;
	private double goodsmiddle_gas;
	private double goodsmiddle_diesel;
	private double goodslarge_gas;
	private double goodslarge_diesel;
	private double tricycle;
	private double goodsslow;
	private double motorcycle_ordinary;
	private double motorcycle_light;
	public int getPollutantId() {
		return pollutantId;
	}
	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
	}
	public int getStandardId() {
		return standardId;
	}
	public void setStandardId(int standardId) {
		this.standardId = standardId;
	}
	public int getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(int vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPollutant(String pollutant) {
		this.pollutant = pollutant;
	}
	public String getPollutant() {
		return pollutant;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getStandard() {
		return standard;
	}
	public void setGuestmini_rentgas(double guestmini_rentgas) {
		this.guestmini_rentgas = guestmini_rentgas;
	}
	public double getGuestmini_rentgas() {
		return guestmini_rentgas;
	}
	public void setGuestmini_rentrest(double guestmini_rentrest) {
		this.guestmini_rentrest = guestmini_rentrest;
	}
	public double getGuestmini_rentrest() {
		return guestmini_rentrest;
	}
	public void setGuestmini_restgas(double guestmini_restgas) {
		this.guestmini_restgas = guestmini_restgas;
	}
	public double getGuestmini_restgas() {
		return guestmini_restgas;
	}
	public void setGuestmini_restrest(double guestmini_restrest) {
		this.guestmini_restrest = guestmini_restrest;
	}
	public double getGuestmini_restrest() {
		return guestmini_restrest;
	}
	public void setGuestsmall_rentgas(double guestsmall_rentgas) {
		this.guestsmall_rentgas = guestsmall_rentgas;
	}
	public double getGuestsmall_rentgas() {
		return guestsmall_rentgas;
	}
	public void setGuestsmall_rentdiesel(double guestsmall_rentdiesel) {
		this.guestsmall_rentdiesel = guestsmall_rentdiesel;
	}
	public double getGuestsmall_rentdiesel() {
		return guestsmall_rentdiesel;
	}
	public void setGuestsmall_rentrest(double guestsmall_rentrest) {
		this.guestsmall_rentrest = guestsmall_rentrest;
	}
	public double getGuestsmall_rentrest() {
		return guestsmall_rentrest;
	}
	public void setGuestsmall_restgas(double guestsmall_restgas) {
		this.guestsmall_restgas = guestsmall_restgas;
	}
	public double getGuestsmall_restgas() {
		return guestsmall_restgas;
	}
	public void setGuestsmall_restdiesel(double guestsmall_restdiesel) {
		this.guestsmall_restdiesel = guestsmall_restdiesel;
	}
	public double getGuestsmall_restdiesel() {
		return guestsmall_restdiesel;
	}
	public void setGuestsmall_restrest(double guestsmall_restrest) {
		this.guestsmall_restrest = guestsmall_restrest;
	}
	public double getGuestsmall_restrest() {
		return guestsmall_restrest;
	}
	public void setGuestmiddle_busgas(double guestmiddle_busgas) {
		this.guestmiddle_busgas = guestmiddle_busgas;
	}
	public double getGuestmiddle_busgas() {
		return guestmiddle_busgas;
	}
	public void setGuestmiddle_busdiesel(double guestmiddle_busdiesel) {
		this.guestmiddle_busdiesel = guestmiddle_busdiesel;
	}
	public double getGuestmiddle_busdiesel() {
		return guestmiddle_busdiesel;
	}
	public void setGuestmiddle_busrest(double guestmiddle_busrest) {
		this.guestmiddle_busrest = guestmiddle_busrest;
	}
	public double getGuestmiddle_busrest() {
		return guestmiddle_busrest;
	}
	public void setGuestmiddle_restgas(double guestmiddle_restgas) {
		this.guestmiddle_restgas = guestmiddle_restgas;
	}
	public double getGuestmiddle_restgas() {
		return guestmiddle_restgas;
	}
	public void setGuestmiddle_restdiesel(double guestmiddle_restdiesel) {
		this.guestmiddle_restdiesel = guestmiddle_restdiesel;
	}
	public double getGuestmiddle_restdiesel() {
		return guestmiddle_restdiesel;
	}
	public void setGuestmiddle_restrest(double guestmiddle_restrest) {
		this.guestmiddle_restrest = guestmiddle_restrest;
	}
	public double getGuestmiddle_restrest() {
		return guestmiddle_restrest;
	}
	public void setGuestlarge_busgas(double guestlarge_busgas) {
		this.guestlarge_busgas = guestlarge_busgas;
	}
	public double getGuestlarge_busgas() {
		return guestlarge_busgas;
	}
	public void setGuestlarge_busdiesel(double guestlarge_busdiesel) {
		this.guestlarge_busdiesel = guestlarge_busdiesel;
	}
	public double getGuestlarge_busdiesel() {
		return guestlarge_busdiesel;
	}
	public void setGuestlarge_busrest(double guestlarge_busrest) {
		this.guestlarge_busrest = guestlarge_busrest;
	}
	public double getGuestlarge_busrest() {
		return guestlarge_busrest;
	}
	public void setGuestlarge_restgas(double guestlarge_restgas) {
		this.guestlarge_restgas = guestlarge_restgas;
	}
	public double getGuestlarge_restgas() {
		return guestlarge_restgas;
	}
	public void setGuestlarge_restdiesel(double guestlarge_restdiesel) {
		this.guestlarge_restdiesel = guestlarge_restdiesel;
	}
	public double getGuestlarge_restdiesel() {
		return guestlarge_restdiesel;
	}
	public void setGuestlarge_restrest(double guestlarge_restrest) {
		this.guestlarge_restrest = guestlarge_restrest;
	}
	public double getGuestlarge_restrest() {
		return guestlarge_restrest;
	}
	public void setGoodsmini_gas(double goodsmini_gas) {
		this.goodsmini_gas = goodsmini_gas;
	}
	public double getGoodsmini_gas() {
		return goodsmini_gas;
	}
	public void setGoodsmini_diesel(double goodsmini_diesel) {
		this.goodsmini_diesel = goodsmini_diesel;
	}
	public double getGoodsmini_diesel() {
		return goodsmini_diesel;
	}
	public void setGoodssmall_gas(double goodssmall_gas) {
		this.goodssmall_gas = goodssmall_gas;
	}
	public double getGoodssmall_gas() {
		return goodssmall_gas;
	}
	public void setGoodssmall_diesel(double goodssmall_diesel) {
		this.goodssmall_diesel = goodssmall_diesel;
	}
	public double getGoodssmall_diesel() {
		return goodssmall_diesel;
	}
	public void setGoodsmiddle_gas(double goodsmiddle_gas) {
		this.goodsmiddle_gas = goodsmiddle_gas;
	}
	public double getGoodsmiddle_gas() {
		return goodsmiddle_gas;
	}
	public void setGoodsmiddle_diesel(double goodsmiddle_diesel) {
		this.goodsmiddle_diesel = goodsmiddle_diesel;
	}
	public double getGoodsmiddle_diesel() {
		return goodsmiddle_diesel;
	}
	public void setGoodslarge_gas(double goodslarge_gas) {
		this.goodslarge_gas = goodslarge_gas;
	}
	public double getGoodslarge_gas() {
		return goodslarge_gas;
	}
	public void setGoodslarge_diesel(double goodslarge_diesel) {
		this.goodslarge_diesel = goodslarge_diesel;
	}
	public double getGoodslarge_diesel() {
		return goodslarge_diesel;
	}
	public void setTricycle(double tricycle) {
		this.tricycle = tricycle;
	}
	public double getTricycle() {
		return tricycle;
	}
	public void setGoodsslow(double goodsslow) {
		this.goodsslow = goodsslow;
	}
	public double getGoodsslow() {
		return goodsslow;
	}
	public void setMotorcycle_ordinary(double motorcycle_ordinary) {
		this.motorcycle_ordinary = motorcycle_ordinary;
	}
	public double getMotorcycle_ordinary() {
		return motorcycle_ordinary;
	}
	public void setMotorcycle_light(double motorcycle_light) {
		this.motorcycle_light = motorcycle_light;
	}
	public double getMotorcycle_light() {
		return motorcycle_light;
	}
}