package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.ITradeDao;
import com.xf.entity.Trade;

@Service
public class TradeService implements ITradeDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private ITradeDao tradeDao;

	public Trade getById(int id) {
		return tradeDao.getById(id);
	}

	public Trade getByNo(String no) {
		return tradeDao.getByNo(no);
	}

	public List<Trade> getByParent(int parentId) {
		return tradeDao.getByParent(parentId);
	}

	public List<Trade> getByLevel(int level) {
		return tradeDao.getByLevel(level);
	}
	
	public List<Trade> getByClass(String cls) {
		return tradeDao.getByClass(cls);
	}

	public List<Trade> getAll() {
		return tradeDao.getAll();
	}

}
