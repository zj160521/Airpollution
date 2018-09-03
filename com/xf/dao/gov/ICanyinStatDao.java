package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.gov.AccountStat;
import com.xf.entity.gov.CanyinStat;

public interface ICanyinStatDao {
	public void add(CanyinStat obj);
	public void delete(int id);
	public void update(CanyinStat obj);
	public CanyinStat getById(int id);
	public List<Integer> getYears(int accountid);
	public CanyinStat getByField(CanyinStat obj);
	public List<CanyinStat> getByYear(CanyinStat obj);
	public List<CanyinStat> getByTown(CanyinStat obj);
	public List<CanyinStat> getFillTime(CanyinStat obj);
	public void setstatus(int status, int accountid);
	public List<AccountStat> getAccountStat(int year);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public List<CanyinStat> getAll();
	public List<CanyinStat> statAll(int year);
	public List<CanyinStat> statAll2(int year);
	public void clearData(CanyinStat obj);
	public List<CanyinStat> statAll_pc(int year);
}
