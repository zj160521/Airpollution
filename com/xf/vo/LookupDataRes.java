package com.xf.vo;

public class LookupDataRes implements Comparable<Object>{
	private int pollutionid;
	private String pollutionName;
	private int tradeid;
	private String tradeName;
	private double value;
	
	public String getPollutionName() {
		return pollutionName;
	}
	public void setPollutionName(String pollutionName) {
		this.pollutionName = pollutionName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public int getPollutionid() {
		return pollutionid;
	}
	public void setPollutionid(int pollutionid) {
		this.pollutionid = pollutionid;
	}
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public int compareTo(Object o) { 
		  if(o instanceof LookupDataRes){ 
			  LookupDataRes s=(LookupDataRes)o; 
		   if(this.pollutionid>s.pollutionid){ 
		    return 1; 
		   } 
		   else{ 
		    return 0; 
		   } 
		  } 
		  return -1; 
		 } 
}
