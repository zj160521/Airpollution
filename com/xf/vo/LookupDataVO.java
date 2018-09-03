package com.xf.vo;

import java.util.List;

public class LookupDataVO {
	private int tradeid;
	private String tradeName;
	private List<LookupDataRes> list;
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
	public List<LookupDataRes> getList() {
		return list;
	}
	public void setList(List<LookupDataRes> list) {
		this.list = list;
	}
	
}
