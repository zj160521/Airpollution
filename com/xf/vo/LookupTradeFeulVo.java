package com.xf.vo;

import java.util.List;

public class LookupTradeFeulVo {
	private int tradeId;
	private String tradeName;
	private List<LookupTradeFeulRes1> list;
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public List<LookupTradeFeulRes1> getList() {
		return list;
	}
	public void setList(List<LookupTradeFeulRes1> list) {
		this.list = list;
	}
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
} 
