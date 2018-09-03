package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IAccountReportDao;
import com.xf.entity.AccountReport;

@Service
public class AccountReportService implements IAccountReportDao {

	@Autowired
	private IAccountReportDao dao;
	
	public void add(AccountReport obj) {
		dao.add(obj);
	}
	public void update(AccountReport obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public AccountReport getById(int id) {
		return dao.getById(id);
	}
	public AccountReport getReports() {
		return dao.getReports();
	}
	public List<AccountReport> getAll() {
		return dao.getAll();
	}

}
