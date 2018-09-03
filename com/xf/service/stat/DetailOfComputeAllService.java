package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.DetailOfComputeAllDao;
import com.xf.entity.gov.GovStat;

@Service
public class DetailOfComputeAllService implements DetailOfComputeAllDao{

	@Autowired
	private DetailOfComputeAllDao dao; 
	public List<GovStat> getDetails(int year,int did, String tablename) {
		return dao.getDetails(year,did, tablename);
	}
	public List<GovStat> moveDetails(int year, int did) {
		return dao.moveDetails(year, did);
	}
	public List<GovStat> getDetails2(int year, int did, String tablename) {
		return dao.getDetails2(year, did, tablename);
	}
	public List<GovStat> moveDetails2(int year, int did) {
		return dao.moveDetails2(year, did);
	}

}
