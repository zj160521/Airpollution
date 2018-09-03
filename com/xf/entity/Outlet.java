package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("outlet")
public class Outlet {
	private int id;
	private int enterpriceId;
	private String outletSerial;
	private int outletTypeId;
	private double outletHeight;
	private double outletDiameter;
	private double e_outlet;
	private double n_outlet;
	private String remark;
	private int enabled;
	private int status;
	
	private String outletTypeName;
	
	private OutletFill ofill;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public String getOutletSerial() {
		return outletSerial;
	}
	public void setOutletSerial(String outletSerial) {
		this.outletSerial = outletSerial;
	}
	public int getOutletTypeId() {
		return outletTypeId;
	}
	public void setOutletTypeId(int outletTypeId) {
		this.outletTypeId = outletTypeId;
	}
	public double getOutletHeight() {
		return outletHeight;
	}
	public void setOutletHeight(double outletHeight) {
		this.outletHeight = outletHeight;
	}
	public double getOutletDiameter() {
		return outletDiameter;
	}
	public void setOutletDiameter(double outletDiameter) {
		this.outletDiameter = outletDiameter;
	}
	public double getE_outlet() {
		return e_outlet;
	}
	public void setE_outlet(double e_outlet) {
		this.e_outlet = e_outlet;
	}
	public double getN_outlet() {
		return n_outlet;
	}
	public void setN_outlet(double n_outlet) {
		this.n_outlet = n_outlet;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOutletTypeName() {
		return outletTypeName;
	}
	public void setOutletTypeName(String outletTypeName) {
		this.outletTypeName = outletTypeName;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public OutletFill getOfill() {
		return ofill;
	}
	public void setOfill(OutletFill ofill) {
		this.ofill = ofill;
	}

}
