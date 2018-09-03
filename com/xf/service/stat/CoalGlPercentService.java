package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.CoalGlPercentDao;
import com.xf.vo.CoalGlPercent;

@Service
public class CoalGlPercentService implements CoalGlPercentDao {
	
	@Autowired
	private CoalGlPercentDao dao;

	public List<CoalGlPercent> getData(int year) {
		try {
			return dao.getData(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
