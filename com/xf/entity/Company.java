package com.xf.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("company")
public class Company {
	
	private int id;
	private String name;
	private String password;
	private String companyName;
	private String companySerial;
	private int trade1;
	private int trade2;
	private int trade3;
	private int trade4;
	private String contact;
	private String contactNo;
	private String legalPerson;
	private String legalPersonPhone;
	private int province;
	private int city;
	private int town;
	private int country;
	private int street;
	private double e_point;
	private double n_point;
	private String completedTime;
	private String address;
	private String remark;
	
	private String tradeStr;
	private String addressStr;
	private int typeid;
	private String typename;
	private String groupname;
	private String code;
	private String companyCode;
	private String industrialPark;
	private String buildTime;
	private int ismall;
	private int status;
	private int fillyear;
	private CompanyFill clist;
	private List<ProductFill> plist;
	private List<SmallFacility>	slist;
	private List<Devices> dlist;
	private List<DevFill> dflist;
	private List<Material> mList;
	private int all_status;
	private int companyId[];
	private String processInstanceId;
	private String taskId;
	private String taskIds[];
	private String assignee;
	
	private String tradeName;
	private String areaName;
	private int trade_id;
	private int area_id;
	
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String[] getTaskIds() {
		return taskIds;
	}
	public void setTaskIds(String[] taskIds) {
		this.taskIds = taskIds;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<Material> getmList() {
		return mList;
	}
	public void setmList(List<Material> mList) {
		this.mList = mList;
	}
	public int[] getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int[] companyId) {
		this.companyId = companyId;
	}
	public int getAll_status() {
		return all_status;
	}
	public void setAll_status(int all_status) {
		this.all_status = all_status;
	}
	public List<DevFill> getDflist() {
		return dflist;
	}
	public void setDflist(List<DevFill> dflist) {
		this.dflist = dflist;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public List<Devices> getDlist() {
		return dlist;
	}
	public void setDlist(List<Devices> dlist) {
		this.dlist = dlist;
	}
	public CompanyFill getClist() {
		return clist;
	}
	public void setClist(CompanyFill clist) {
		this.clist = clist;
	}
	public List<ProductFill> getPlist() {
		return plist;
	}
	public void setPlist(List<ProductFill> plist) {
		this.plist = plist;
	}
	public List<SmallFacility> getSlist() {
		return slist;
	}
	public void setSlist(List<SmallFacility> slist) {
		this.slist = slist;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanySerial() {
		return companySerial;
	}
	public void setCompanySerial(String companySerial) {
		this.companySerial = companySerial;
	}
	public int getTrade1() {
		return trade1;
	}
	public void setTrade1(int trade1) {
		this.trade1 = trade1;
	}
	public int getTrade2() {
		return trade2;
	}
	public void setTrade2(int trade2) {
		this.trade2 = trade2;
	}
	public int getTrade3() {
		return trade3;
	}
	public void setTrade3(int trade3) {
		this.trade3 = trade3;
	}
	public int getTrade4() {
		return trade4;
	}
	public void setTrade4(int trade4) {
		this.trade4 = trade4;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
	public double getE_point() {
		return e_point;
	}
	public void setE_point(double e_point) {
		this.e_point = e_point;
	}
	public double getN_point() {
		return n_point;
	}
	public void setN_point(double n_point) {
		this.n_point = n_point;
	}
	public String getCompletedTime() {
		return completedTime;
	}
	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTradeStr() {
		return tradeStr;
	}
	public void setTradeStr(String tradeStr) {
		this.tradeStr = tradeStr;
	}
	public String getAddressStr() {
		return addressStr;
	}
	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalPersonPhone() {
		return legalPersonPhone;
	}
	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone = legalPersonPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getIndustrialPark() {
		return industrialPark;
	}
	public void setIndustrialPark(String industrialPark) {
		this.industrialPark = industrialPark;
	}
	public String getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
	public int getIsmall() {
		return ismall;
	}
	public void setIsmall(int ismall) {
		this.ismall = ismall;
	}
	
}
