package com.xf.entity;


public class Searchform {

	private int id;
	private int cur_page;// 当前页码
	private int page_rows;// 每页数据条数
	private int total_record;//总记录数
	private int total_pages;// 总页数
	private int areaId;//地区id
	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCur_page() {
		return cur_page;
	}
	public void setCur_page(int cur_page) {
		this.cur_page = cur_page;
	}
	public int getPage_rows() {
		return page_rows;
	}
	public void setPage_rows(int page_rows) {
		this.page_rows = page_rows;
	}
	public int getTotal_record() {
		return total_record;
	}
	public void setTotal_record(int total_record) {
		this.total_record = total_record;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;

	}
	}
