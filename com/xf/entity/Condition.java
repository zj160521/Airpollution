package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("Condition")
public class Condition {
private int tradeid1;
private int tradeid2;
private int tradeid3;
private int tradeid4;
private int province;
private int cityid;
private int townid;
private String companyName;
private String deviceSerial;
private String productName;
private int pollutantId;
private int fillyear;
private int month;
private int status;
private int ismall;
private int type;
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getProvince() {
	return province;
}
public void setProvince(int province) {
	this.province = province;
}
public int getTownid() {
	return townid;
}
public void setTownid(int townid) {
	this.townid = townid;
}
public int getTradeid1() {
	return tradeid1;
}
public void setTradeid1(int tradeid1) {
	this.tradeid1 = tradeid1;
}
public int getTradeid2() {
	return tradeid2;
}
public void setTradeid2(int tradeid2) {
	this.tradeid2 = tradeid2;
}
public int getCityid() {
	return cityid;
}
public void setCityid(int cityid) {
	this.cityid = cityid;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}

public String getDeviceSerial() {
	return deviceSerial;
}
public void setDeviceSerial(String deviceSerial) {
	this.deviceSerial = deviceSerial;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getPollutantId() {
	return pollutantId;
}
public void setPollutantId(int pollutantId) {
	this.pollutantId = pollutantId;
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
public int getIsmall() {
	return ismall;
}
public void setIsmall(int ismall) {
	this.ismall = ismall;
}
public int getTradeid3() {
	return tradeid3;
}
public void setTradeid3(int tradeid3) {
	this.tradeid3 = tradeid3;
}
public int getTradeid4() {
	return tradeid4;
}
public void setTradeid4(int tradeid4) {
	this.tradeid4 = tradeid4;
}

}
