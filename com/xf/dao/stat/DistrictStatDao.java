package com.xf.dao.stat;

import java.util.List;

import com.xf.vo.DistrictStat;

public interface DistrictStatDao {

	public List<DistrictStat> getData(int fillyear, int issmall, int in);
}
