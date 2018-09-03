package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Boat;

public interface IBoatDao {

	public void add(Boat obj);
	public void delete(int id);
	public void update(Boat obj);
	public Boat getById(int id);
	public Boat getBoat(Boat obj);
	public List<Integer> getYears(int accountid);
	public Boat getByField(Boat obj);
	public List<Boat> getByYear(Boat obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<Boat> getByCompany(int accountid);
}