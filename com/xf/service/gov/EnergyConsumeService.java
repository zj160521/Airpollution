package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IEnergyConsumeDao;
import com.xf.entity.gov.EnergyConsume;

@Service
public class EnergyConsumeService extends BaseService implements IEnergyConsumeDao {

	@Autowired
	private IEnergyConsumeDao dao;
	
	public void add(EnergyConsume obj) {
		dao.add(obj);
	}
	public void update(EnergyConsume obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public EnergyConsume getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public EnergyConsume getByField(EnergyConsume obj) {
		return dao.getByField(obj);
	}
	public List<EnergyConsume> getByYear(EnergyConsume obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int orignalStatus) {
		dao.setstatus2(status, accountid, orignalStatus);
	}
	public List<EnergyConsume> getByTown(int id, int accountid, int fillyear) {
		try {
			return dao.getByTown(id, accountid, fillyear);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}

}
