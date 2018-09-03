package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("Devices")
public class Devices {
	
	private int id;
	private int enterpriceId;
	private String deviceSerial;
	private String deviceName;
	private int devClass;
	private int deviceTypeId;
	private int deviceTypeId2;
	private int fuelTypeId;
	private int fuelTypeId2;
	private String deviceModel;
	private String serviceLife;
	private double shippingTon;
	private int produceStepId;
	private int enabled;
	private String remark;
	private int fillyear;
	private String devicetypeName;
	private String fueltypeName;
	private String devicetypeName2;
	private String fueltypeName2;
	private int status;
	public DevFill devfill;
	private String unit;
	private int fuel;
	private int parentId;
	private double fuelcost;
	private String fuelName;
	private int fuelId2;
	
	public int getFuelId2() {
		return fuelId2;
	}
	public void setFuelId2(int fuelId2) {
		this.fuelId2 = fuelId2;
	}
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public int getFuel() {
		return fuel;
	}
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public double getFuelcost() {
		return fuelcost;
	}
	public void setFuelcost(double fuelcost) {
		this.fuelcost = fuelcost;
	}
	public String getDevicetypeName2() {
		return devicetypeName2;
	}
	public void setDevicetypeName2(String devicetypeName2) {
		this.devicetypeName2 = devicetypeName2;
	}
	public String getFueltypeName2() {
		return fueltypeName2;
	}
	public void setFueltypeName2(String fueltypeName2) {
		this.fueltypeName2 = fueltypeName2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getShippingTon() {
		return shippingTon;
	}
	public void setShippingTon(double shippingTon) {
		this.shippingTon = shippingTon;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDevicetypeName() {
		return devicetypeName;
	}
	public void setDevicetypeName(String devicetypeName) {
		this.devicetypeName = devicetypeName;
	}
	public String getFueltypeName() {
		return fueltypeName;
	}
	public void setFueltypeName(String fueltypeName) {
		this.fueltypeName = fueltypeName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getDevClass() {
		return devClass;
	}
	public void setDevClass(int devClass) {
		this.devClass = devClass;
	}
	public int getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public int getDeviceTypeId2() {
		return deviceTypeId2;
	}
	public void setDeviceTypeId2(int deviceTypeId2) {
		this.deviceTypeId2 = deviceTypeId2;
	}
	public int getFuelTypeId() {
		return fuelTypeId;
	}
	public void setFuelTypeId(int fuelTypeId) {
		this.fuelTypeId = fuelTypeId;
	}
	public int getFuelTypeId2() {
		return fuelTypeId2;
	}
	public void setFuelTypeId2(int fuelTypeId2) {
		this.fuelTypeId2 = fuelTypeId2;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getServiceLife() {
		return serviceLife;
	}
	public void setServiceLife(String serviceLife) {
		this.serviceLife = serviceLife;
	}
	public int getProduceStepId() {
		return produceStepId;
	}
	public void setProduceStepId(int produceStepId) {
		this.produceStepId = produceStepId;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
