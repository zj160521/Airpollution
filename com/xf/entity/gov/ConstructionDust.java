package com.xf.entity.gov;

import java.util.Map;

import org.apache.ibatis.type.Alias;

@Alias("ConstructionDust")
public class ConstructionDust {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private int area;
	private double constructArea;
	private double buildingArea;
	private double startWorkArea;
	private double completeArea;
	private double hasStartedArea;
	private int hasStartNumber;
	private double notStartArea;
	private int notStartNumber;
	private String department;
	private String townname;
	private int status;
	private double avgWorktime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public String getFillTime() {
		return fillTime;
	}
	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getStreet() {
		return street;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public double getConstructArea() {
		return constructArea;
	}
	public void setConstructArea(double constructArea) {
		this.constructArea = constructArea;
	}
	public double getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(double buildingArea) {
		this.buildingArea = buildingArea;
	}
	public double getStartWorkArea() {
		return startWorkArea;
	}
	public void setStartWorkArea(double startWorkArea) {
		this.startWorkArea = startWorkArea;
	}
	public double getCompleteArea() {
		return completeArea;
	}
	public void setCompleteArea(double completeArea) {
		this.completeArea = completeArea;
	}
	public double getHasStartedArea() {
		return hasStartedArea;
	}
	public void setHasStartedArea(double hasStartedArea) {
		this.hasStartedArea = hasStartedArea;
	}
	public int getHasStartNumber() {
		return hasStartNumber;
	}
	public void setHasStartNumber(int hasStartNumber) {
		this.hasStartNumber = hasStartNumber;
	}
	public double getNotStartArea() {
		return notStartArea;
	}
	public void setNotStartArea(double notStartArea) {
		this.notStartArea = notStartArea;
	}
	public int getNotStartNumber() {
		return notStartNumber;
	}
	public void setNotStartNumber(int notStartNumber) {
		this.notStartNumber = notStartNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTownname() {
		return townname;
	}
	public void setTownname(String townname) {
		this.townname = townname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getAvgWorktime() {
		return avgWorktime;
	}
	public void setAvgWorktime(double avgWorktime) {
		this.avgWorktime = avgWorktime;
	}
	
		
}