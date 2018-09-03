package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.gov.CountyArea;


public interface ICountyAreaDao {

	public void add(CountyArea obj);
	public void delete(int id);
	public void update(CountyArea obj);
	public CountyArea getById(int id);
	public List<Integer> getYears(int accountid);
	public CountyArea getByField(CountyArea obj);
	public List<CountyArea> getByYear(CountyArea obj);
	public List<CountyArea> getByTown(CountyArea obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
}