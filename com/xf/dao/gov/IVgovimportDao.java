package com.xf.dao.gov;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.gov.Vgovimport;


public interface IVgovimportDao {
	
	public List<Vgovimport> getAll(int fillyear);
	public List<Vgovimport> getByCity(@Param("city")int city,@Param("fillyear")int fillyear);
	public List<Vgovimport> getBymorename(@Param("fillyear")int fillyear,@Param("name") String name);
	public List<Vgovimport> cityBymorename(@Param("city") int city,@Param("fillyear")int fillyear,@Param("name") String name);
}
