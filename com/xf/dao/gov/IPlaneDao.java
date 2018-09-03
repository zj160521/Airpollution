package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Plane;

public interface IPlaneDao {

	public void add(Plane obj);
	public void delete(int id);
	public void update(Plane obj);
	public Plane getById(int id);
	public List<Integer> getYears(int accountid);
	public Plane getByField(Plane obj);
	public List<Plane> getByYear(Plane obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public List<Plane> getAll(int fillYear);
	public List<Plane> getAll2(int fillYear);
	public void clearData(Plane obj);
}