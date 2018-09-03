package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Factor")
public class Factor {
	private int id;
	private int typeid;
	private int pollutantId;
	private int productId;
	private double factor;
	private String addressStr;
	private int materialId;
	private int groupid;
	private int formulaId;
	private int tradeid;
	private Integer isFollow;
	private int fId;
	
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public Integer getIsFollow() {
		return isFollow;
	}
	public void setIsFollow(Integer isFollow) {
		this.isFollow = isFollow;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(int formulaId) {
		this.formulaId = formulaId;
	}
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public double getFactor() {
		return factor;
	}
	public int getPollutantId() {
		return pollutantId;
	}
	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}