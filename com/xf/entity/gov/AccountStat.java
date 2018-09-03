package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("AccountStat")
public class AccountStat {
	private int id;
	private int cnt;

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}