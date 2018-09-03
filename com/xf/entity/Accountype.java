package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("Accountype")
public class Accountype {
	private int id;
	private String groupname;
	private String typename;
	private String privilage;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getTypename() {
		return typename;
	}
	public void setPrivilage(String privilage) {
		this.privilage = privilage;
	}
	public String getPrivilage() {
		return privilage;
	}
}