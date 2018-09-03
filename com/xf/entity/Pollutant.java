package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("pollutant")
public class Pollutant {
	private int id;
	private String pollutantName;
	private String pollutantType;
	private String remark;
	private int groupid;
	private String groupname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPollutantName() {
		return pollutantName;
	}
	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}
	public String getPollutantType() {
		return pollutantType;
	}
	public void setPollutantType(String pollutantType) {
		this.pollutantType = pollutantType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}
