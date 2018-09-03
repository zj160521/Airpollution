package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("district")
public class District {

	private int id;
	private int parentId;
	private int districtLevel;
	private String districtNo;
	private String districtName;
	private double e_point;
	private double n_point;
	
	public double getE_point() {
		return e_point;
	}
	public void setE_point(double e_point) {
		this.e_point = e_point;
	}
	public double getN_point() {
		return n_point;
	}
	public void setN_point(double n_point) {
		this.n_point = n_point;
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
	public int getDistrictLevel() {
		return districtLevel;
	}
	public void setDistrictLevel(int districtLevel) {
		this.districtLevel = districtLevel;
	}
	public String getDistrictNo() {
		return districtNo;
	}
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}
