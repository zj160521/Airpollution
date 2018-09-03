package com.xf.vo;

import java.util.List;

public class LookupCityTradeTechVo1 {
	private int tradeid;
	private String tradeName;
	private List<LookupCityTradeTechRes1> list;
	
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public List<LookupCityTradeTechRes1> getList() {
		return list;
	}
	public void setList(List<LookupCityTradeTechRes1> list) {
		this.list = list;
	}
	
}
