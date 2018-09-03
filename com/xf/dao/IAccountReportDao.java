package com.xf.dao;

import java.util.List;

import com.xf.entity.AccountReport;

public interface IAccountReportDao {

	public void add(AccountReport obj);
	public void delete(int id);
	public void update(AccountReport obj);
	public AccountReport getById(int id);
	public AccountReport getReports();
	public List<AccountReport> getAll();
}