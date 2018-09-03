package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.AccountReport2;

public interface IAccountReportDao2 {

	public void add(AccountReport2 obj);
	public void delete(int id);
	public void update(AccountReport2 obj);
	public AccountReport2 getById(int id);
	public List<Integer> getYears();
	public AccountReport2 getByField(AccountReport2 obj);
	public List<AccountReport2> getByYear(AccountReport2 obj);
	public List<AccountReport2> getByTypeId(int typeid);
}