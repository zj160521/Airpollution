package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("CanyinCertified")
public class CanyinCertified {
	private int id;
	private int accountid;
	private String fillTime;
	private int fillyear;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private String storename;
	private String storeaddr;
	private String legalPerson;
	private String legalPersonID;
	private String contact;
	private String contactNo;
	private int staffNum;
	private String certifiedID;
	private String certifiedType;
	private String certifiedGov;
	private String Qlevel;
	private double incoming;
	private double tax;
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
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getStorename() {
		return storename;
	}
	public void setStoreaddr(String storeaddr) {
		this.storeaddr = storeaddr;
	}
	public String getStoreaddr() {
		return storeaddr;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPersonID(String legalPersonID) {
		this.legalPersonID = legalPersonID;
	}
	public String getLegalPersonID() {
		return legalPersonID;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact() {
		return contact;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setStaffNum(int staffNum) {
		this.staffNum = staffNum;
	}
	public int getStaffNum() {
		return staffNum;
	}
	public void setCertifiedID(String certifiedID) {
		this.certifiedID = certifiedID;
	}
	public String getCertifiedID() {
		return certifiedID;
	}
	public void setCertifiedType(String certifiedType) {
		this.certifiedType = certifiedType;
	}
	public String getCertifiedType() {
		return certifiedType;
	}
	public void setCertifiedGov(String certifiedGov) {
		this.certifiedGov = certifiedGov;
	}
	public String getCertifiedGov() {
		return certifiedGov;
	}
	public void setQlevel(String Qlevel) {
		this.Qlevel = Qlevel;
	}
	public String getQlevel() {
		return Qlevel;
	}
	public void setIncoming(double incoming) {
		this.incoming = incoming;
	}
	public double getIncoming() {
		return incoming;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTax() {
		return tax;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
}