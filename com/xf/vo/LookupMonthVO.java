package com.xf.vo;

import java.util.List;

public class LookupMonthVO {
	private int month;
	private int name;
	private List<LookupMonthRes1> list;
	
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public List<LookupMonthRes1> getList() {
		return list;
	}
	public void setList(List<LookupMonthRes1> list) {
		this.list = list;
	}
	
}
