package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Nfertilizer")
public class Nfertilizer {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int layingType;
	private int ferType;
	private String ferTypeName;
	private String layingTypeName;
	private double amountTotal;
	private Double amountMu;
	private Double m1;
	private Double m2;
	private Double m3;
	private Double m4;
	private Double m5;
	private Double m6;
	private Double m7;
	private Double m8;
	private Double m9;
	private Double m10;
	private Double m11;
	private Double m12;
	private String addressStr;
	private int he;
	private int status;
	private String cityName;
	
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
	public int getLayingType() {
		return layingType;
	}
	public void setLayingType(int layingType) {
		this.layingType = layingType;
	}
	public int getFerType() {
		return ferType;
	}
	public void setFerType(int ferType) {
		this.ferType = ferType;
	}
	public String getFerTypeName() {
		return ferTypeName;
	}
	public void setFerTypeName(String ferTypeName) {
		this.ferTypeName = ferTypeName;
	}
	public String getLayingTypeName() {
		return layingTypeName;
	}
	public void setLayingTypeName(String layingTypeName) {
		this.layingTypeName = layingTypeName;
	}
	public double getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(double amountTotal) {
		this.amountTotal = amountTotal;
	}
	public Double getAmountMu() {
		return amountMu;
	}
	public void setAmountMu(Double amountMu) {
		this.amountMu = amountMu;
	}
	public Double getM1() {
		return m1;
	}
	public void setM1(Double m1) {
		this.m1 = m1;
	}
	public Double getM2() {
		return m2;
	}
	public void setM2(Double m2) {
		this.m2 = m2;
	}
	public Double getM3() {
		return m3;
	}
	public void setM3(Double m3) {
		this.m3 = m3;
	}
	public Double getM4() {
		return m4;
	}
	public void setM4(Double m4) {
		this.m4 = m4;
	}
	public Double getM5() {
		return m5;
	}
	public void setM5(Double m5) {
		this.m5 = m5;
	}
	public Double getM6() {
		return m6;
	}
	public void setM6(Double m6) {
		this.m6 = m6;
	}
	public Double getM7() {
		return m7;
	}
	public void setM7(Double m7) {
		this.m7 = m7;
	}
	public Double getM8() {
		return m8;
	}
	public void setM8(Double m8) {
		this.m8 = m8;
	}
	public Double getM9() {
		return m9;
	}
	public void setM9(Double m9) {
		this.m9 = m9;
	}
	public Double getM10() {
		return m10;
	}
	public void setM10(Double m10) {
		this.m10 = m10;
	}
	public Double getM11() {
		return m11;
	}
	public void setM11(Double m11) {
		this.m11 = m11;
	}
	public Double getM12() {
		return m12;
	}
	public void setM12(Double m12) {
		this.m12 = m12;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public int getHe() {
		return he;
	}
	public void setHe(int he) {
		this.he = he;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}