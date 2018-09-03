package com.xf.dao.stat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.gov.GovStat;

public interface DetailOfComputeAllDao {
	public List<GovStat> getDetails(@Param("year")int year,@Param("did")int did,@Param("tablename")String tablename);
	public List<GovStat> moveDetails(@Param("year")int year,@Param("did")int did);
	public List<GovStat> getDetails2(@Param("year")int year,@Param("did")int did,@Param("tablename")String tablename);
	public List<GovStat> moveDetails2(@Param("year")int year,@Param("did")int did);
}
