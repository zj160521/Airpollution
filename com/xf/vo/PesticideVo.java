package com.xf.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

public class PesticideVo {
	private int status;
	private int townid;
	private String town;
	private int fillyear;
    private List crops;

    
	public int getFillyear() {
		return fillyear;
	}
	public void setFillyear(int fillyear) {
		this.fillyear = fillyear;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTownid() {
		return townid;
	}
	public void setTownid(int townid) {
		this.townid = townid;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public List getCrops() {
		return crops;
	}
	public void setCrops(List crops) {
		this.crops = crops;
	}
	
}