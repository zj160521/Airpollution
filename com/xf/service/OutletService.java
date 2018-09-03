package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IOutletDao;
import com.xf.entity.Condition;
import com.xf.entity.Outlet;

@Service
public class OutletService implements IOutletDao {

	@Autowired
	private IOutletDao outletDao;
	
	public void add(Outlet o) {
		outletDao.add(o);
	}

	public void delete(int id) {
		outletDao.delete(id);
	}

	public void update(Outlet o) {
		outletDao.update(o);
	}

	public Outlet getById(int id) {
		return outletDao.getById(id);
	}

	public List<Outlet> getByCompany(int companyid,int fillyear) {
		return outletDao.getByCompany(companyid,fillyear);
	}

	public List<Outlet> yearList() {
		return outletDao.yearList();
	}

	public List<Outlet> getyearList(Condition con) {
		return outletDao.getyearList(con);
	}

	public Outlet check(Outlet o) {
		return outletDao.check(o);
	}

}
