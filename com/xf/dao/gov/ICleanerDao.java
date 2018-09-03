package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Cleaner;

public interface ICleanerDao {

	public void add(Cleaner obj);
	public void delete(int id);
	public void update(Cleaner obj);
	public Cleaner getById(int id);
	public List<Integer> getYears(int accountid);
	public Cleaner getByField(Cleaner obj);
	public List<Cleaner> getByYear(Cleaner obj);
	public List<Cleaner> getByTown(Cleaner obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
}