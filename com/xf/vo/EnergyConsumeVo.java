package com.xf.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("EnergyConsume")
public class EnergyConsumeVo {

	private String purpose;
	private int status;
	private List energy;
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public List getEnergy() {
		return energy;
	}
	public void setEnergy(List energy) {
		this.energy = energy;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}