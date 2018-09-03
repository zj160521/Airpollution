package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.IDetailOfpollutantDao;
import com.xf.entity.Condition;
import com.xf.vo.Details;
@Service
public class DetailOfpollutantService implements IDetailOfpollutantDao {
	@Autowired
	private IDetailOfpollutantDao dao;
	public List<Details> getDetailDevice(Condition condition) {
		return dao.getDetailDevice(condition);
	}

	public List<Details> getDetailProduct(Condition condition) {
		return dao.getDetailProduct(condition);
	}
}
