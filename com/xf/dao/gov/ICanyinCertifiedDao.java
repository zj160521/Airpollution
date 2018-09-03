package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.CanyinCertified;

public interface ICanyinCertifiedDao {

	public void add(CanyinCertified obj);
	public void delete(int id);
	public void update(CanyinCertified obj);
	public CanyinCertified getById(int id);
	public List<Integer> getYears(int accountid);
	public CanyinCertified getByField(CanyinCertified obj);
	public List<CanyinCertified> getByYear(CanyinCertified obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void clearData(CanyinCertified obj);
}