package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.GisStatDao;
import com.xf.vo.Gis;
import com.xf.vo.GisBoiler;
import com.xf.vo.GisCompany;
import com.xf.vo.GisStatistic;
import com.xf.vo.GisTrade;
@Service
public class GisStatService implements GisStatDao {
	@Autowired
	private GisStatDao dao;

	public List<GisStatistic> getNewData(int fillyear, int pollutantid,
			int tradeid) {
		try {
			return dao.getNewData(fillyear, pollutantid, tradeid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<GisTrade> makeUpByTrade(Gis gis) {
		try {
			return dao.makeUpByTrade(gis);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<GisCompany> getCompanyGisByTradeAndDistrict(Gis gis) {
		try {
			return dao.getCompanyGisByTradeAndDistrict(gis);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<GisBoiler> getBoilerGisByDistrict(Gis gis) {
		try {
			return dao.getBoilerGisByDistrict(gis);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
