package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.INfertilizerDao;
import com.xf.entity.gov.Nfertilizer;

@Service
public class NfertilizerService extends BaseService implements INfertilizerDao {

	@Autowired
	private INfertilizerDao dao;
	
	public void add(Nfertilizer obj) {
		dao.add(obj);
	}
	public void update(Nfertilizer obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Nfertilizer getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Nfertilizer getByField(Nfertilizer obj) {
		return dao.getByField(obj);
	}
	public List<Nfertilizer> getByYear(Nfertilizer obj) {
		return dao.getByYear(obj);
	}
	public List<Nfertilizer> getByFerType(Nfertilizer obj) {
		return dao.getByFerType(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public Nfertilizer getByContion(Nfertilizer obj) {
		return dao.getByContion(obj);
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}
}
