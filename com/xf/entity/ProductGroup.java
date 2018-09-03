package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("ProductGroup")
public class ProductGroup {
 private int id;
 private String groupName;
 private String remark;
 private int trade1;
 private int trade2;
 private int trade3;
 private int trade4;

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
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
 
}
