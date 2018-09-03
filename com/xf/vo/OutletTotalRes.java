package com.xf.vo;

import org.apache.ibatis.type.Alias;

@Alias("OutletTotalRes")
public class OutletTotalRes {
private String tradeName;
private double outletTotal;
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
