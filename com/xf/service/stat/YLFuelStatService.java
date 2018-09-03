package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.YLFuelStatDao;
import com.xf.vo.YLFuelStat;

@Service
public class YLFuelStatService implements YLFuelStatDao {
	@Autowired
	private YLFuelStatDao dao;

	public List<YLFuelStat> getData(int year) {
		
		try {
			return dao.getData(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
