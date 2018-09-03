package com.xf.vo;

import java.util.List;

public class LookupCityTradeTechVo {
	private int cityid;
	private String districtName;
	private List<LookupCityTradeTechVo1> list;
	
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<LookupCityTradeTechVo1> getList() {
		return list;
	}
	public void setList(List<LookupCityTradeTechVo1> list) {
		this.list = list;
	}
	
}
