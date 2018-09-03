package com.xf.vo;

import org.apache.ibatis.type.Alias;

@Alias("ProdFactor")
public class ProdFactor {
	private int productid;
	private String productName;
	private int pollutantId;
	private String pollutantName;
	private double factor;
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
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
	public String getPollutantName() {
		return pollutantName;
	}
	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	
}
