package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Industry;

public interface IIndustryDao {

	public void add(Industry obj);
	public void delete(int id);
	public void update(Industry obj);
	public Industry getById(int id);
	public List<Integer> getYears(int accountid);
	public Industry getByField(Industry obj);
	public List<Industry> getByYear(Industry obj);
	public void clearData(Industry obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
}