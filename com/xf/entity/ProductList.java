package com.xf.entity;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("ProductList")
public class ProductList {
private int isgroup;
private String groupName;
private String productName;
private List<HashMap<String, Object>> list;
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
public int getIsgroup() {
	return isgroup;
}
public void setIsgroup(int isgroup) {
	this.isgroup = isgroup;
}
public List<HashMap<String, Object>> getList() {
	return list;
}
public void setList(List<HashMap<String, Object>> list) {
	this.list = list;
}

}
