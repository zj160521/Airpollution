package com.xf.vo;

import org.apache.ibatis.type.Alias;

@Alias("OutletTotal")
public class OutletTotal {
private String districtName;
private String tradeName;
private double outletTotal;

public String getDistrictName() {
	return districtName;
}
public void setDistrictName(String districtName) {
	this.districtName = districtName;
}
public String getTradeName() {
	return tradeName;
}
public void setTradeName(String tradeName) {
	this.tradeName = tradeName;
}
public double getOutletTotal() {
	return outletTotal;
}
public void setOutletTotal(double outletTotal) {
	this.outletTotal = outletTotal;
}
}
