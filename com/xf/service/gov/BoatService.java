package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IBoatDao;
import com.xf.entity.gov.Boat;

@Service
public class BoatService extends BaseService implements IBoatDao {

	@Autowired
	private IBoatDao dao;
	
	public void add(Boat obj) {
		dao.add(obj);
	}
	public void update(Boat obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Boat getByField(Boat obj) {
		return dao.getByField(obj);
	}
	public List<Boat> getByYear(Boat obj) {
		try {
			List<Boat> lb=dao.getByYear(obj);
			return lb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
		
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
		
	}
	public Boat getById(int id) {
		return dao.getById(id);
	}
	public Boat getBoat(Boat obj) {
		return dao.getBoat(obj);
	}
	public List<Boat> getByCompany(int accountid) {
		try {
			List<Boat> list= dao.getByCompany(accountid);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
