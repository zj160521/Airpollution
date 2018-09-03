package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("CanyinNocert")
public class CanyinNocert {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private String shoptype;
	private String shopaddr;
	private String name;
	private String idnum;
	private String homeaddr;
	private String telephone;
	private double incoming;
	private String productType;
	private String districtId;
	private String remark;
	private String addressStr;
	private int status;
	private int importflag;
	
	
	
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
	public void setShoptype(String shoptype) {
		this.shoptype = shoptype;
	}
	public String getShoptype() {
		return shoptype;
	}
	public void setShopaddr(String shopaddr) {
		this.shopaddr = shopaddr;
	}
	public String getShopaddr() {
		return shopaddr;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setHomeaddr(String homeaddr) {
		this.homeaddr = homeaddr;
	}
	public String getHomeaddr() {
		return homeaddr;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setIncoming(double incoming) {
		this.incoming = incoming;
	}
	public double getIncoming() {
		return incoming;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductType() {
		return productType;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
}