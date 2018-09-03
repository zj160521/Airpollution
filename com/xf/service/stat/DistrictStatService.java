package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.DistrictStatDao;
import com.xf.vo.DistrictStat;

@Service
public class DistrictStatService implements com.xf.dao.stat.DistrictStatDao {
	@Autowired
	private DistrictStatDao dao;

	public List<DistrictStat> getData(int fillyear, int issmall, int in) {

		try {
			return dao.getData(fillyear, issmall, in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
