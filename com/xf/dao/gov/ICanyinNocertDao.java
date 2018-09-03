package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.CanyinNocert;

public interface ICanyinNocertDao {

	public void add(CanyinNocert obj);
	public void delete(int id);
	public void update(CanyinNocert obj);
	public CanyinNocert getById(int id);
	public List<Integer> getYears(int accountid);
	public CanyinNocert getByField(CanyinNocert obj);
	public List<CanyinNocert> getByYear(CanyinNocert obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void clearData(CanyinNocert obj);
}