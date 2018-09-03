package com.xf.dao.stat;

import java.util.List;

import com.xf.vo.YLFuelStat;

public interface YLFuelStatDao {
	
	public List<YLFuelStat> getData(int year);

}
