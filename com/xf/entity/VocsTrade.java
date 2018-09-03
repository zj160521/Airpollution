package com.xf.entity;


public class VocsTrade {

	private int id;
	private int parent_id;
	private String trade_name;
	private String trade_code;
	private String remark;
	private String id_path;
	private String trade_name_path;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public String getTrade_code() {
		return trade_code;
	}
	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId_path() {
		return id_path;
	}
	public void setId_path(String id_path) {
		this.id_path = id_path;
	}
	public String getTrade_name_path() {
		return trade_name_path;
	}
	public void setTrade_name_path(String trade_name_path) {
		this.trade_name_path = trade_name_path;
	}

	
}
