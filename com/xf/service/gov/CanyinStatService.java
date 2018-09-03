package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.ICanyinStatDao;
import com.xf.entity.gov.AccountStat;
import com.xf.entity.gov.CanyinStat;

@Service
public class CanyinStatService implements ICanyinStatDao {

	@Autowired
	private ICanyinStatDao dao;
	
	public void add(CanyinStat obj) {
		dao.add(obj);
	}
	public void update(CanyinStat obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public CanyinStat getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public CanyinStat getByField(CanyinStat obj) {
		return dao.getByField(obj);
	}
	public List<CanyinStat> getByYear(CanyinStat obj) {
		return dao.getByYear(obj);
	}
	public List<CanyinStat> getFillTime(CanyinStat obj) {
		return dao.getFillTime(obj);
	}
	public List<CanyinStat> getByTown(CanyinStat obj) {
		// TODO Auto-generated method stub
		return dao.getByTown(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public List<AccountStat> getAccountStat(int year) {
		return dao.getAccountStat(year);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<CanyinStat> getAll() {
		try {
			return dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<CanyinStat> statAll(int year) {
		try {
			return dao.statAll(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void clearData(CanyinStat obj) {
		dao.clearData(obj);
	}
	public List<CanyinStat> statAll2(int year) {
		return dao.statAll2(year);
	}
	public List<CanyinStat> statAll_pc(int year) {
		return dao.statAll_pc(year);
	}
}
