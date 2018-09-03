package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IEnergySellDao;
import com.xf.entity.gov.EnergyConsume;
import com.xf.entity.gov.EnergySell;

@Service
public class EnergySellService extends BaseService implements IEnergySellDao {

	@Autowired
	private IEnergySellDao dao;
	
	public void add(EnergySell obj) {
		dao.add(obj);
	}
	public void update(EnergySell obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public EnergySell getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public EnergySell getByField(EnergySell obj) {
		return dao.getByField(obj);
	}
	public List<EnergySell> getByYear(EnergySell obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int orignalStatus) {
		dao.setstatus2(status, accountid, orignalStatus);
	}
	public List<EnergySell> getByTown(int id,int accountid,int fillyear) {
		try {
			return dao.getByTown(id,accountid,fillyear);
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
