package com.xf.dao;

import java.util.List;

import com.xf.entity.Trade;

public interface ITradeDao {

	public Trade getById(int id);
	
	public Trade getByNo(String no);

	public List<Trade> getByParent(int parentId);

	public List<Trade> getByLevel(int level);
	
	public List<Trade> getByClass(String cls);

	public List<Trade> getAll();
}
