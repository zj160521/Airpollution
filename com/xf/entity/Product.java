package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("product")
public class Product {

	private int id;
	private int enterpriceId;
	private String productSerial;
	private String productName;
	private String remark;
	private int trade1;
	private int trade2;
	private int trade3;
	private int trade4;
	private int groupid;
	private String unit;
	private int isgroup;
	private int pollutantId;
	private double factor;
	
	public int getPollutantId() {
		return pollutantId;
	}
	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public int getIsgroup() {
		return isgroup;
	}
	public void setIsgroup(int isgroup) {
		this.isgroup = isgroup;
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
	public String getProductSerial() {
		return productSerial;
	}
	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
