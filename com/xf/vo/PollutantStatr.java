package com.xf.vo;

import java.util.List;

public class PollutantStatr {
private String districtName;
private String pollutantName;
private List<PollutantStat1> list;

public String getPollutantName() {
	return pollutantName;
}
public void setPollutantName(String pollutantName) {
	this.pollutantName = pollutantName;
}
public String getDistrictName() {
	return districtName;
}
public void setDistrictName(String districtName) {
	this.districtName = districtName;
}
public List<PollutantStat1> getList() {
	return list;
}
public void setList(List<PollutantStat1> list) {
	this.list = list;
}
}
