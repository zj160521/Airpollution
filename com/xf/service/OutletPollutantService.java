package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IOutletPollutantDao;
import com.xf.entity.OutletPollutant;

@Service
public class OutletPollutantService implements IOutletPollutantDao {

	@Autowired
	private IOutletPollutantDao outpDao;
	
	public void add(OutletPollutant o) {
		outpDao.add(o);
	}

	public void delete(int id) {
		outpDao.delete(id);
	}

	public void update(OutletPollutant o) {
		outpDao.update(o);
	}

	public OutletPollutant getById(int id) {
		return outpDao.getById(id);
	}

	public List<OutletPollutant> getByOutletFill(int outletfillid) {
		return outpDao.getByOutletFill(outletfillid);
	}

	public void deleteBy(OutletPollutant o) {
		outpDao.deleteBy(o);
	}

}
