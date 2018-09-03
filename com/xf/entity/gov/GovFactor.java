package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("GovFactor")
public class GovFactor {

	private int id;
	private String typename;
	private String type_x;
	private String type_x2;
	private String type_y;
	private String type_y2;
	private Double factor;
	private Double param;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getType_x() {
		return type_x;
	}
	public void setType_x(String type_x) {
		this.type_x = type_x;
	}
	public String getType_y() {
		return type_y;
	}
	public void setType_y(String type_y) {
		this.type_y = type_y;
	}
	public String getType_x2() {
		return type_x2;
	}
	public void setType_x2(String type_x2) {
		this.type_x2 = type_x2;
	}
	public String getType_y2() {
		return type_y2;
	}
	public void setType_y2(String type_y2) {
		this.type_y2 = type_y2;
	}
	public Double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
	}
	public Double getParam() {
		return param;
	}
	public void setParam(Double param) {
		this.param = param;
	}



}
