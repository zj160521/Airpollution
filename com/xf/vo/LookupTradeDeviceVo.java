package com.xf.vo;

import java.util.List;

public class LookupTradeDeviceVo {
	private String tradeName;
	private List<LookupTradeDeviceRes1> list;
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public List<LookupTradeDeviceRes1> getList() {
		return list;
	}
	public void setList(List<LookupTradeDeviceRes1> list) {
		this.list = list;
	}
	
}
