package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Gasstation;

public interface IGasstationDao {

	public void add(Gasstation obj);
	public void delete(int id);
	public void update(Gasstation obj);
	public Gasstation getById(int id);
	public List<Integer> getYears(int accountid);
	public Gasstation getByField(Gasstation obj);
	public List<Gasstation> getByYear(Gasstation obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void clearData(Gasstation obj);
}