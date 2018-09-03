package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Oildepot;

public interface IOildepotDao {

	public void add(Oildepot obj);
	public void delete(int id);
	public void update(Oildepot obj);
	public Oildepot getById(int id);
	public List<Integer> getYears(int accountid);
	public Oildepot getByField(Oildepot obj);
	public List<Oildepot> getByYear(Oildepot obj);
	public List<Oildepot> getByName(Oildepot obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void clearData(Oildepot obj);
}