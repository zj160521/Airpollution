package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IRoadDustDao;
import com.xf.entity.gov.RoadDust;

@Service
public class RoadDustService extends BaseService implements IRoadDustDao {

	@Autowired
	private IRoadDustDao dao;
	
	public void add(RoadDust obj) {
		dao.add(obj);
	}
	public void update(RoadDust obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public RoadDust getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public RoadDust getByField(RoadDust obj) {
		return dao.getByField(obj);
	}
	public List<RoadDust> getByYear(RoadDust obj) {
		return dao.getByYear(obj);
	}
	public List<RoadDust> getByTown(RoadDust obj) {
		return dao.getByTown(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<RoadDust> statAll(int year) {
		try {
			return dao.statAll(year);
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
