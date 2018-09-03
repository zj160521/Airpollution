package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IProduceStepDao;
import com.xf.entity.Condition;
import com.xf.entity.ProduceStep;

@Service
public class ProduceStepService implements IProduceStepDao {

	@Autowired
	private IProduceStepDao stepDao;

	public void add(ProduceStep step) {
		stepDao.add(step);
	}

	public void delete(int id) {
		stepDao.delete(id);
	}

	public void update(ProduceStep step) {
		stepDao.update(step);
	}

	public ProduceStep getById(int id,int fillyear) {
		return stepDao.getById(id,fillyear);
	}

	public List<ProduceStep> getByCompany(int fillyear,int companyid) {
		return stepDao.getByCompany(fillyear,companyid);
	}

	public List<ProduceStep> getByFacility(int facilityid,int fillyear) {
		return stepDao.getByFacility(facilityid,fillyear);
	}

	public List<ProduceStep> yearList() {
		return stepDao.yearList();
	}

	public ProduceStep getBySerial(ProduceStep step) {
		
		try {
			return stepDao.getBySerial(step);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProduceStep> getyearList(Condition con) {
		return stepDao.getyearList(con);
	}

	public ProduceStep getById1(int id) {
		return stepDao.getById1(id);
	}

}
