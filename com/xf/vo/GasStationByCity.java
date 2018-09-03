package com.xf.vo;

public class GasStationByCity {
	private int id;
	private String districtName;
	private double dieselGross;
	private double gasolineGross;
	private double natgasGross;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public double getDieselGross() {
		return dieselGross;
	}
	public void setDieselGross(double dieselGross) {
		this.dieselGross = dieselGross;
	}
	public double getGasolineGross() {
		return gasolineGross;
	}
	public void setGasolineGross(double gasolineGross) {
		this.gasolineGross = gasolineGross;
	}
	public double getNatgasGross() {
		return natgasGross;
	}
	public void setNatgasGross(double natgasGross) {
		this.natgasGross = natgasGross;
	}
	
}
