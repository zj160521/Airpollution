package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("outletpollutant")
public class OutletPollutant {
	private int id;
	private int outletfillId;
	private int pollutantId;
	private double s1Nongdu;
	private double s2Nongdu;
	private double s3Nongdu;
	private double s4Nongdu;
	private double yearNongdu;
	private double totalAmount;
	
	private String pollutantName;
	private String pollutantType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOutletfillId() {
		return outletfillId;
	}
	public void setOutletfillId(int outletfillId) {
		this.outletfillId = outletfillId;
	}
	public int getPollutantId() {
		return pollutantId;
	}
	public void setPollutantId(int pollutantId) {
		this.pollutantId = pollutantId;
	}
	public double getS1Nongdu() {
		return s1Nongdu;
	}
	public void setS1Nongdu(double s1Nongdu) {
		this.s1Nongdu = s1Nongdu;
	}
	public double getS2Nongdu() {
		return s2Nongdu;
	}
	public void setS2Nongdu(double s2Nongdu) {
		this.s2Nongdu = s2Nongdu;
	}
	public double getS3Nongdu() {
		return s3Nongdu;
	}
	public void setS3Nongdu(double s3Nongdu) {
		this.s3Nongdu = s3Nongdu;
	}
	public double getS4Nongdu() {
		return s4Nongdu;
	}
	public void setS4Nongdu(double s4Nongdu) {
		this.s4Nongdu = s4Nongdu;
	}
	public double getYearNongdu() {
		return yearNongdu;
	}
	public void setYearNongdu(double yearNongdu) {
		this.yearNongdu = yearNongdu;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPollutantName() {
		return pollutantName;
	}
	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}
	public String getPollutantType() {
		return pollutantType;
	}
	public void setPollutantType(String pollutantType) {
		this.pollutantType = pollutantType;
	}
		
		
}
