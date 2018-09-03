package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("CanyinStat")
public class CanyinStat {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int certified;
	private int province;
	private int city;
	private int town;
	private String townName;
	private int canguan_huge;
	private int canguan_big;
	private int canguan_mid;
	private int canguan_small;
	private int snack;
	private int fastfood;
	private int milk;
	private int drink;
	private int shitang_shiye;
	private int shitang_school;
	private int shitang_gongdi;
	private int total;
	private double incoming;
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
	public void setCertified(int certified) {
		this.certified = certified;
	}
	public int getCertified() {
		return certified;
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
	public void setCanguan_huge(int canguan_huge) {
		this.canguan_huge = canguan_huge;
	}
	public int getCanguan_huge() {
		return canguan_huge;
	}
	public void setCanguan_big(int canguan_big) {
		this.canguan_big = canguan_big;
	}
	public int getCanguan_big() {
		return canguan_big;
	}
	public void setCanguan_mid(int canguan_mid) {
		this.canguan_mid = canguan_mid;
	}
	public int getCanguan_mid() {
		return canguan_mid;
	}
	public void setCanguan_small(int canguan_small) {
		this.canguan_small = canguan_small;
	}
	public int getCanguan_small() {
		return canguan_small;
	}
	public void setSnack(int snack) {
		this.snack = snack;
	}
	public int getSnack() {
		return snack;
	}
	public void setFastfood(int fastfood) {
		this.fastfood = fastfood;
	}
	public int getFastfood() {
		return fastfood;
	}
	public void setMilk(int milk) {
		this.milk = milk;
	}
	public int getMilk() {
		return milk;
	}
	public void setDrink(int drink) {
		this.drink = drink;
	}
	public int getDrink() {
		return drink;
	}
	public void setShitang_shiye(int shitang_shiye) {
		this.shitang_shiye = shitang_shiye;
	}
	public int getShitang_shiye() {
		return shitang_shiye;
	}
	public void setShitang_school(int shitang_school) {
		this.shitang_school = shitang_school;
	}
	public int getShitang_school() {
		return shitang_school;
	}
	public void setShitang_gongdi(int shitang_gongdi) {
		this.shitang_gongdi = shitang_gongdi;
	}
	public int getShitang_gongdi() {
		return shitang_gongdi;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	public void setIncoming(double incoming) {
		this.incoming = incoming;
	}
	public double getIncoming() {
		return incoming;
	}
}