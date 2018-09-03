package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("AnimalsWild")
public class AnimalsWild {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private String townName;
	private int beef;
	private int beefcycle;
	private int cow;
	private int cowcycle;
	private int goat;
	private int goatcycle;
	private int sheep;
	private int sheepcycle;
	private int pig;
	private int pigcycle;
	private int chicken;
	private int chickencycle;
	private int duck;
	private int duckcycle;
	private int goose;
	private int goosecycle;
	private int hen;
	private int layingduck;
	private int layinggoose;
	private int sow;
	private int rabbit;
	private int horse;
	private String addressStr;
	private int he;
	private int status;
	private int importflag;
	private String cityName;
	
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
	public int getImportflag() {
		return importflag;
	}
	public void setImportflag(int importflag) {
		this.importflag = importflag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getHe() {
		return he;
	}
	public void setHe(int he) {
		this.he = he;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
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
	public void setBeef(int beef) {
		this.beef = beef;
	}
	public int getBeef() {
		return beef;
	}
	public void setBeefcycle(int beefcycle) {
		this.beefcycle = beefcycle;
	}
	public int getBeefcycle() {
		return beefcycle;
	}
	public void setCow(int cow) {
		this.cow = cow;
	}
	public int getCow() {
		return cow;
	}
	public void setCowcycle(int cowcycle) {
		this.cowcycle = cowcycle;
	}
	public int getCowcycle() {
		return cowcycle;
	}
	public void setGoat(int goat) {
		this.goat = goat;
	}
	public int getGoat() {
		return goat;
	}
	public void setGoatcycle(int goatcycle) {
		this.goatcycle = goatcycle;
	}
	public int getGoatcycle() {
		return goatcycle;
	}
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}
	public int getSheep() {
		return sheep;
	}
	public void setSheepcycle(int sheepcycle) {
		this.sheepcycle = sheepcycle;
	}
	public int getSheepcycle() {
		return sheepcycle;
	}
	public void setPig(int pig) {
		this.pig = pig;
	}
	public int getPig() {
		return pig;
	}
	public void setPigcycle(int pigcycle) {
		this.pigcycle = pigcycle;
	}
	public int getPigcycle() {
		return pigcycle;
	}
	public void setChicken(int chicken) {
		this.chicken = chicken;
	}
	public int getChicken() {
		return chicken;
	}
	public void setChickencycle(int chickencycle) {
		this.chickencycle = chickencycle;
	}
	public int getChickencycle() {
		return chickencycle;
	}
	public void setDuck(int duck) {
		this.duck = duck;
	}
	public int getDuck() {
		return duck;
	}
	public void setDuckcycle(int duckcycle) {
		this.duckcycle = duckcycle;
	}
	public int getDuckcycle() {
		return duckcycle;
	}
	public void setGoose(int goose) {
		this.goose = goose;
	}
	public int getGoose() {
		return goose;
	}
	public void setGoosecycle(int goosecycle) {
		this.goosecycle = goosecycle;
	}
	public int getGoosecycle() {
		return goosecycle;
	}
	public void setHen(int hen) {
		this.hen = hen;
	}
	public int getHen() {
		return hen;
	}
	public void setLayingduck(int layingduck) {
		this.layingduck = layingduck;
	}
	public int getLayingduck() {
		return layingduck;
	}
	public void setLayinggoose(int layinggoose) {
		this.layinggoose = layinggoose;
	}
	public int getLayinggoose() {
		return layinggoose;
	}
	public void setSow(int sow) {
		this.sow = sow;
	}
	public int getSow() {
		return sow;
	}
	public void setRabbit(int rabbit) {
		this.rabbit = rabbit;
	}
	public int getRabbit() {
		return rabbit;
	}
	public void setHorse(int horse) {
		this.horse = horse;
	}
	public int getHorse() {
		return horse;
	}
}