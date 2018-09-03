package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.ICompanyFillDao;
import com.xf.entity.CompanyFill;
import com.xf.entity.Condition;

@Service
public class CompanyFillService implements ICompanyFillDao {

	@Autowired
	private ICompanyFillDao cfillDao;

	public void add(CompanyFill company) {
		if (company.getTotalHour() <= 0)
			company.setTotalHour(company.getHoursOfDay()
					* company.getDaysOfWork());
		cfillDao.add(company);
	}

	public void delete(int id) {
		cfillDao.delete(id);
	}

	public void update(CompanyFill company) {
		if (company.getTotalHour() <= 0)
			company.setTotalHour(company.getHoursOfDay()
					* company.getDaysOfWork());
		cfillDao.update(company);
	}

	public CompanyFill getById(int id) {
		return cfillDao.getById(id);
	}

	public List<CompanyFill> getByCompanyYear(CompanyFill com) {
		return cfillDao.getByCompanyYear(com);
	}

	public List<CompanyFill> getByCompany(int companyid) {
		return cfillDao.getByCompany(companyid);
	}

	public void setstatus(int status, int companyid, int fillyear) {
		cfillDao.setstatus(status, companyid, fillyear);
	}

	public CompanyFill yearFill(int companyid, int fillyear) {
		return cfillDao.yearFill(companyid, fillyear);
	}

	public List<CompanyFill> yearList() {
		return cfillDao.yearList();
	}

	public void setstatus3(int status, int companyid, int orignalStatus,
			int fillyear) {
		cfillDao.setstatus3(status, companyid, orignalStatus, fillyear);
	}

	public void setstatus2(int status, int companyid, int orignalStatus) {
		cfillDao.setstatus2(status, companyid, orignalStatus);
	}

	public List<CompanyFill> getyearList(Condition con) {
		return cfillDao.getyearList(con);
	}

	public void smallSetstatus(int status, int id, int fillyear) {
		cfillDao.smallSetstatus(status, id, fillyear);
	}

	public void smallSetstatus3(int status, int companyid, int orignalStatus,
			int fillyear) {
		cfillDao.smallSetstatus3(status, companyid, orignalStatus, fillyear);
	}

	public CompanyFill getbyCompanyYear(CompanyFill company) {
		return cfillDao.getbyCompanyYear(company);
	}

	public void delBycomIdYear(int comId, int year) {
		cfillDao.delBycomIdYear(comId, year);
		
	}

	public List<Integer> getSmall() {
		return cfillDao.getSmall();
	}

	public void changeStatus(int enterpriceId) {
		cfillDao.changeStatus(enterpriceId);
	}
}
