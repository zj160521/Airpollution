package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Gknumber;

public interface IGknumberDao {

	public void add(Gknumber obj);
	public void delete(int id);
	public void update(Gknumber obj);
	public Gknumber getById(int id);
	public List<Integer> getYears(int accountid);
	public Gknumber getByField(Gknumber obj);
	public List<Gknumber> getByYear(Gknumber obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void clearData(Gknumber obj);
}