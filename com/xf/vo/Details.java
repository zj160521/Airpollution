package com.xf.vo;

import org.apache.ibatis.type.Alias;

@Alias("Details")
public class Details {
private String districtName1;
private String districtName2;
private String districtName3;
private String tradeName;
private String companyName;
private String deviceName;
private String pollutantName;
private String fuelName;
private String productName;
private String materialName;
private int fillyear;
private int month;
private double statvalue;
private String exp;
private String factor;
private String valtype;
private String value;
private String dsrate;
private double e_point;
private double n_point;
private String tradeNo;

public String getExp() {
	return exp;
}
public void setExp(String exp) {
	this.exp = exp;
}
public String getFactor() {
	return factor;
}
public void setFactor(String factor) {
	this.factor = factor;
}
public String getValtype() {
	return valtype;
}
public void setValtype(String valtype) {
	this.valtype = valtype;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public String getDsrate() {
	return dsrate;
}
public void setDsrate(String dsrate) {
	this.dsrate = dsrate;
}
public String getDistrictName1() {
	return districtName1;
}
public void setDistrictName1(String districtName1) {
	this.districtName1 = districtName1;
}
public String getDistrictName3() {
	return districtName3;
}
public void setDistrictName3(String districtName3) {
	this.districtName3 = districtName3;
}
public String getDistrictName2() {
	return districtName2;
}
public void setDistrictName2(String districtName2) {
	this.districtName2 = districtName2;
}
public String getTradeName() {
	return tradeName;
}
public void setTradeName(String tradeName) {
	this.tradeName = tradeName;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getDeviceName() {
	return deviceName;
}
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}
public String getPollutantName() {
	return pollutantName;
}
public void setPollutantName(String pollutantName) {
	this.pollutantName = pollutantName;
}
public String getFuelName() {
	return fuelName;
}
public void setFuelName(String fuelName) {
	this.fuelName = fuelName;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getMaterialName() {
	return materialName;
}
public void setMaterialName(String materialName) {
	this.materialName = materialName;
}
public int getFillyear() {
	return fillyear;
}
public void setFillyear(int fillyear) {
	this.fillyear = fillyear;
}
public int getMonth() {
	return month;
}
public void setMonth(int month) {
	this.month = month;
}
public double getStatvalue() {
	return statvalue;
}
public void setStatvalue(double statvalue) {
	this.statvalue = statvalue;
}
public double getE_point() {
	return e_point;
}
public void setE_point(double e_point) {
	this.e_point = e_point;
}
public double getN_point() {
	return n_point;
}
public void setN_point(double n_point) {
	this.n_point = n_point;
}
public String getTradeNo() {
	return tradeNo;
}
public void setTradeNo(String tradeNo) {
	this.tradeNo = tradeNo;
}

}
