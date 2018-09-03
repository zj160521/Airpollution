package com.xf.vo;

import java.util.List;

public class LookupCityTradeASVVo {
	private int cityid;
	private String districtName;
	private List<LookupCityTradeAS1> list;
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
	public List<LookupCityTradeAS1> getList() {
		return list;
	}
	public void setList(List<LookupCityTradeAS1> list) {
		this.list = list;
	}
	
}
