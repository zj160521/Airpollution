package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.RoadDust;

public interface IRoadDustDao {

	public void add(RoadDust obj);
	public void delete(int id);
	public void update(RoadDust obj);
	public RoadDust getById(int id);
	public List<Integer> getYears(int accountid);
	public RoadDust getByField(RoadDust obj);
	public List<RoadDust> getByYear(RoadDust obj);
	public List<RoadDust> getByTown(RoadDust obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<RoadDust> statAll(int year);
}