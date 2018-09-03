package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("CompanyProduct")
public class CompanyProduct {
private int id;
private String enterpriceName;
private int productId;
private String productName;
private int groupid;
private String groupName;
private String unit;
private String materialName;
private int materialId;
private int status;
private int trade1;
private int trade2;
private int trade3;
private int trade4;

public String getMaterialName() {
	return materialName;
}
public void setMaterialName(String materialName) {
	this.materialName = materialName;
}
public int getMaterialId() {
	return materialId;
}
public void setMaterialId(int materialId) {
	this.materialId = materialId;
}
public int getTrade1() {
	return trade1;
}
public void setTrade1(int trade1) {
	this.trade1 = trade1;
}
public int getTrade2() {
	return trade2;
}
public void setTrade2(int trade2) {
	this.trade2 = trade2;
}
public int getTrade3() {
	return trade3;
}
public void setTrade3(int trade3) {
	this.trade3 = trade3;
}
public int getTrade4() {
	return trade4;
}
public void setTrade4(int trade4) {
	this.trade4 = trade4;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getGroupid() {
	return groupid;
}
public void setGroupid(int groupid) {
	this.groupid = groupid;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEnterpriceName() {
	return enterpriceName;
}
public void setEnterpriceName(String enterpriceName) {
	this.enterpriceName = enterpriceName;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}

}
