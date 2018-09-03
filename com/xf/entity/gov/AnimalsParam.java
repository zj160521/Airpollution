package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("AnimalsParam")
public class AnimalsParam {
	private int id;
	private String feedtype;
	private String stattype;
	private double shitamount;
	private double Nratio;
	private double NH3ratio;
	private double RoomRatio;
	private double OutsideRatio;
	private double Rfeed;
	private double Xliquid;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setFeedtype(String feedtype) {
		this.feedtype = feedtype;
	}
	public String getFeedtype() {
		return feedtype;
	}
	public void setStattype(String stattype) {
		this.stattype = stattype;
	}
	public String getStattype() {
		return stattype;
	}
	public void setShitamount(double shitamount) {
		this.shitamount = shitamount;
	}
	public double getShitamount() {
		return shitamount;
	}
	public void setNratio(double Nratio) {
		this.Nratio = Nratio;
	}
	public double getNratio() {
		return Nratio;
	}
	public void setNH3ratio(double NH3ratio) {
		this.NH3ratio = NH3ratio;
	}
	public double getNH3ratio() {
		return NH3ratio;
	}
	public void setRoomRatio(double RoomRatio) {
		this.RoomRatio = RoomRatio;
	}
	public double getRoomRatio() {
		return RoomRatio;
	}
	public void setOutsideRatio(double OutsideRatio) {
		this.OutsideRatio = OutsideRatio;
	}
	public double getOutsideRatio() {
		return OutsideRatio;
	}
	public void setRfeed(double Rfeed) {
		this.Rfeed = Rfeed;
	}
	public double getRfeed() {
		return Rfeed;
	}
	public void setXliquid(double Xliquid) {
		this.Xliquid = Xliquid;
	}
	public double getXliquid() {
		return Xliquid;
	}
}