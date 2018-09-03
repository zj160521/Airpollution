package com.xf.vo;

import java.util.List;

public class PollutantStat1 {
private String name;
private List<PollutantStat> list;
private List<PollutantStat> value;
public List<PollutantStat> getValue() {
	return value;
}
public void setValue(List<PollutantStat> value) {
	this.value = value;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<PollutantStat> getList() {
	return list;
}
public void setList(List<PollutantStat> list) {
	this.list = list;
}

}
