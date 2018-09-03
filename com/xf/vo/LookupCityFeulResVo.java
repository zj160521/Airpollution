package com.xf.vo;

import java.util.List;

public class LookupCityFeulResVo {
	private String districtName;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private List<LookupCityFeulRes1> list;
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<LookupCityFeulRes1> getList() {
		return list;
	}
	public void setList(List<LookupCityFeulRes1> list) {
		this.list = list;
	}
	
}
