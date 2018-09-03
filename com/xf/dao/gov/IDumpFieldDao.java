package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.DumpField;

public interface IDumpFieldDao {

	public void add(DumpField obj);
	public void delete(int id);
	public void update(DumpField obj);
	public DumpField getById(int id);
	public List<Integer> getYears(int companyid);
	public DumpField getByField(DumpField obj);
	public List<DumpField> getByYear(DumpField obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void clearData(DumpField obj);
}