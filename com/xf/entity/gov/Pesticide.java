package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("Pesticide")
public class Pesticide {
	private int id;
	private int accountid;
	private int status;
	private int importflag;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private String crop;
	private double worm_ddt;
	private double worm_leguo;
	private double worm_juzhi;
	private double worm_total;
	private double grass_baicao;
	private double germ_duojun;
	private double grass_ganlin;
	private double grass_total;
	private double germ_daowen;
	private double germ_total;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setImportflag(int importflag) {
		this.importflag = importflag;
	}
	public int getImportflag() {
		return importflag;
	}
	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}
	public String getFillTime() {
		return fillTime;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getProvince() {
		return province;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getCity() {
		return city;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public int getTown() {
		return town;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getCountry() {
		return country;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public int getStreet() {
		return street;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	public String getCrop() {
		return crop;
	}
	public void setWorm_ddt(double worm_ddt) {
		this.worm_ddt = worm_ddt;
	}
	public double getWorm_ddt() {
		return worm_ddt;
	}
	public void setWorm_leguo(double worm_leguo) {
		this.worm_leguo = worm_leguo;
	}
	public double getWorm_leguo() {
		return worm_leguo;
	}
	public void setWorm_juzhi(double worm_juzhi) {
		this.worm_juzhi = worm_juzhi;
	}
	public double getWorm_juzhi() {
		return worm_juzhi;
	}
	public void setWorm_total(double worm_total) {
		this.worm_total = worm_total;
	}
	public double getWorm_total() {
		return worm_total;
	}
	public void setGrass_baicao(double grass_baicao) {
		this.grass_baicao = grass_baicao;
	}
	public double getGrass_baicao() {
		return grass_baicao;
	}
	public double getGerm_duojun() {
		return germ_duojun;
	}
	public void setGerm_duojun(double germ_duojun) {
		this.germ_duojun = germ_duojun;
	}
	public double getGerm_daowen() {
		return germ_daowen;
	}
	public void setGerm_daowen(double germ_daowen) {
		this.germ_daowen = germ_daowen;
	}
	public void setGrass_ganlin(double grass_ganlin) {
		this.grass_ganlin = grass_ganlin;
	}
	public double getGrass_ganlin() {
		return grass_ganlin;
	}
	public void setGrass_total(double grass_total) {
		this.grass_total = grass_total;
	}
	public double getGrass_total() {
		return grass_total;
	}
	public void setGerm_total(double germ_total) {
		this.germ_total = germ_total;
	}
	public double getGerm_total() {
		return germ_total;
	}
}