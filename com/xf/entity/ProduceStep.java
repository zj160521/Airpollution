package com.xf.entity;

import org.apache.ibatis.type.Alias;


@Alias("producestep")
public class ProduceStep {

	private int id;
	private int enterpriceId;
	private String stepSerial;
	private String stepName;
	private String techChart;
	private String techDesp;
	private String remark;
	private int enabled;
	private int status;
	private String mainDevices;
	
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterpriceId() {
		return enterpriceId;
	}
	public void setEnterpriceId(int enterpriceId) {
		this.enterpriceId = enterpriceId;
	}
	public String getStepSerial() {
		return stepSerial;
	}
	public void setStepSerial(String stepSerial) {
		this.stepSerial = stepSerial;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getTechChart() {
		return techChart;
	}
	public void setTechChart(String techChart) {
		this.techChart = techChart;
	}
	public String getTechDesp() {
		return techDesp;
	}
	public void setTechDesp(String techDesp) {
		this.techDesp = techDesp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMainDevices() {
		return mainDevices;
	}
	public void setMainDevices(String mainDevices) {
		this.mainDevices = mainDevices;
	}
}
