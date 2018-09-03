package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("SmallFacility")
public class SmallFacility {
	private int id;
	private int enterpriceId;
	private int fillyear;
	private int technique1;
	private int technique2;
	private int technique3;
	private int technique4;
	private int status;
	private String techniqueName1;
	private String techniqueName2;
	private String techniqueName3;
	private String techniqueName4;
	private int parentId1;
	private int parentId2;
	private int parentId3;
	private int parentId4;
	private double removal1;
	private double removal2;
	private double removal3;
	private double removal4;
	
	public double getRemoval1() {
		return removal1;
	}
	public void setRemoval1(double removal1) {
		this.removal1 = removal1;
	}
	public double getRemoval2() {
		return removal2;
	}
	public void setRemoval2(double removal2) {
		this.removal2 = removal2;
	}
	public double getRemoval3() {
		return removal3;
	}
	public void setRemoval3(double removal3) {
		this.removal3 = removal3;
	}
	public double getRemoval4() {
		return removal4;
	}
	public void setRemoval4(double removal4) {
		this.removal4 = removal4;
	}
	public int getParentId1() {
		return parentId1;
	}
	public void setParentId1(int parentId1) {
		this.parentId1 = parentId1;
	}
	public int getParentId2() {
		return parentId2;
	}
	public void setParentId2(int parentId2) {
		this.parentId2 = parentId2;
	}
	public int getParentId3() {
		return parentId3;
	}
	public void setParentId3(int parentId3) {
		this.parentId3 = parentId3;
	}
	public int getParentId4() {
		return parentId4;
	}
	public void setParentId4(int parentId4) {
		this.parentId4 = parentId4;
	}
	public String getTechniqueName1() {
		return techniqueName1;
	}
	public void setTechniqueName1(String techniqueName1) {
		this.techniqueName1 = techniqueName1;
	}
	public String getTechniqueName2() {
		return techniqueName2;
	}
	public void setTechniqueName2(String techniqueName2) {
		this.techniqueName2 = techniqueName2;
	}
	public String getTechniqueName3() {
		return techniqueName3;
	}
	public void setTechniqueName3(String techniqueName3) {
		this.techniqueName3 = techniqueName3;
	}
	public String getTechniqueName4() {
		return techniqueName4;
	}
	public void setTechniqueName4(String techniqueName4) {
		this.techniqueName4 = techniqueName4;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setTechnique1(int technique1) {
		this.technique1 = technique1;
	}
	public int getTechnique1() {
		return technique1;
	}
	public void setTechnique2(int technique2) {
		this.technique2 = technique2;
	}
	public int getTechnique2() {
		return technique2;
	}
	public void setTechnique3(int technique3) {
		this.technique3 = technique3;
	}
	public int getTechnique3() {
		return technique3;
	}
	public void setTechnique4(int technique4) {
		this.technique4 = technique4;
	}
	public int getTechnique4() {
		return technique4;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
}