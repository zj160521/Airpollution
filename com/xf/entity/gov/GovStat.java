package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("GovStat")
public class GovStat {
	private int id;
	private int pollutantId;
	private String pollutantName;
	private int province;
	private int city;
	private String cityName;
	private int town;
	private String townName;
	private int fillyear;
	private String stattype;
	private double statvalue;
	private int months;
	private int stattype2;
	private String stattype3;
	private String stat_exp;
	private String stat_valtype;
	private String stat_value;
	private String stat_factor;
	private double stat_dsrate;

	public String getPollutantName() {
		return pollutantName;
	}

	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getStat_exp() {
		return stat_exp;
	}

	public void setStat_exp(String stat_exp) {
		this.stat_exp = stat_exp;
	}

	public String getStat_valtype() {
		return stat_valtype;
	}

	public void setStat_valtype(String stat_valtype) {
		this.stat_valtype = stat_valtype;
	}

	public String getStat_value() {
		return stat_value;
	}

	public void setStat_value(String stat_value) {
		this.stat_value = stat_value;
	}

	public String getStat_factor() {
		return stat_factor;
	}

	public void setStat_factor(String stat_factor) {
		this.stat_factor = stat_factor;
	}

	public double getStat_dsrate() {
		return stat_dsrate;
	}

	public void setStat_dsrate(double stat_dsrate) {
		this.stat_dsrate = stat_dsrate;
	}

	public String getStattype3() {
		return stattype3;
	}

	public void setStattype3(String stattype3) {
		this.stattype3 = stattype3;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getStattype2() {
		return stattype2;
	}

	public void setStattype2(int stattype2) {
		this.stattype2 = stattype2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPollutantId() {
		return pollutantId;
	}

	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
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

	public int getFillyear() {
		return fillyear;
	}

	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}

	public String getStattype() {
		return stattype;
	}

	public void setStattype(String stattype) {
		this.stattype = stattype;
	}

	public double getStatvalue() {
		return statvalue;
	}

	public void setStatvalue(double statvalue) {
		this.statvalue = statvalue;
	}

}
