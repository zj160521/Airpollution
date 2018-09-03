package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("trade")
public class Trade {

	private int id;
	private int parentId;
	private String tradeClass;
	private int tradeLevel;
	private String tradeNo;
	private int productCount;
	private int groupCount;
	
	public int getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getTradeClass() {
		return tradeClass;
	}
	public void setTradeClass(String tradeClass) {
		this.tradeClass = tradeClass;
	}
	public int getTradeLevel() {
		return tradeLevel;
	}
	public void setTradeLevel(int tradeLevel) {
		this.tradeLevel = tradeLevel;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	private String tradeName;

}
