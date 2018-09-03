package com.xf.vo;

import java.util.List;

public class LookupCityTradeASVo {
	private String districtName;
	private List<LookupCityTradeASRes1> list;
	private List<OutletTotalRes> olist;
	public List<OutletTotalRes> getOlist() {
		return olist;
	}
	public void setOlist(List<OutletTotalRes> olist) {
		this.olist = olist;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<LookupCityTradeASRes1> getList() {
		return list;
	}
	public void setList(List<LookupCityTradeASRes1> list) {
		this.list = list;
	}
	
}
