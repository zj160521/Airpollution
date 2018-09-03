package com.xf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xf.dao.txDao;

@Service
public class txService implements ItxService {
	
	@Autowired
	txDao dao;
	
	@Transactional
	public void update(String name) {
		dao.update(name);
		throw new RuntimeException("no error!");
	}
}
