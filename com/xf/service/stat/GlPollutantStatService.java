package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.GlPollutantStatDao;
import com.xf.vo.DistrictStat;

@Service
public class GlPollutantStatService implements GlPollutantStatDao {

	@Autowired
	private GlPollutantStatDao dao;
	
	public List<DistrictStat> getData(int fillyear, int issmall, int in) {

		try {
			return dao.getData(fillyear, issmall, in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
