package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IAccountReportDao2;
import com.xf.entity.gov.AccountReport2;

@Service
public class AccountReportService2 implements IAccountReportDao2 {

	@Autowired
	private IAccountReportDao2 dao;
	
	public void add(AccountReport2 obj) {
		dao.add(obj);
	}
	public void update(AccountReport2 obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public AccountReport2 getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears() {
		return dao.getYears();
	}
	public AccountReport2 getByField(AccountReport2 obj) {
		return dao.getByField(obj);
	}
	public List<AccountReport2> getByYear(AccountReport2 obj) {
		return dao.getByYear(obj);
	}
	public List<AccountReport2> getByTypeId(int typeid) {
		try {
			return dao.getByTypeId(typeid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
