package com.xf.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("CompanyProductList")
public class CompanyProductList {
private int companyId;
private String companyName;
private List<CompanyProduct> list;
public int getCompanyId() {
	return companyId;
}
public void setCompanyId(int companyId) {
	this.companyId = companyId;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public List<CompanyProduct> getList() {
	return list;
}
public void setList(List<CompanyProduct> list) {
	this.list = list;
}

}
