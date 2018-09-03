package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Pesticide;

public interface IPesticideDao {

	public void add(Pesticide obj);
	public void delete(int id, int fillYear);
	public void delete2(int cityid, int accountid);
	public void update(Pesticide obj);
	public Pesticide getById(int id);
	public List<Integer> getYears(int accountid);
	public Pesticide getByField(Pesticide obj);
	public List<Pesticide> getByYear(Pesticide obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<Pesticide> getByTown(int id, int fillYear);
	public List<Integer> getTowns(int accountid, int fillYear);
	public List<Integer> getCitys(int accountid, int fillYear);
	public List<Pesticide> getCheck(int town, int accountid,int fillYear);
}