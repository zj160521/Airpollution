package com.xf.vo;

import java.util.List;
import java.util.Map;

public class PollutantCountM {
	private int fId;
	private String fName;
	private List<Map> map;
	private int cId;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public List<Map> getMap() {
		return map;
	}
	public void setMap(List<Map> map) {
		this.map = map;
	}
	
	
	

}
