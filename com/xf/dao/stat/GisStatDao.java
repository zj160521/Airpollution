package com.xf.dao.stat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.vo.Gis;
import com.xf.vo.GisBoiler;
import com.xf.vo.GisCompany;
import com.xf.vo.GisStatistic;
import com.xf.vo.GisTrade;

public interface GisStatDao {
	
	public List<GisStatistic> getNewData(int fillyear,int pollutantid,@Param("tradeid") int tradeid);
	public List<GisTrade> makeUpByTrade(Gis gis);
	public List<GisCompany> getCompanyGisByTradeAndDistrict(Gis gis);
	public List<GisBoiler> getBoilerGisByDistrict(Gis gis);
	
}
