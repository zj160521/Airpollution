package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IFirewoodDao;
import com.xf.entity.gov.Firewood;
import com.xf.vo.IntString;

@Service
public class FirewoodService extends BaseService implements IFirewoodDao {

	@Autowired
	private IFirewoodDao dao;
	
	public void add(Firewood obj) {
		dao.add(obj);
	}
	public void update(Firewood obj) {
		dao.update(obj);
	}
	public void delete(int id, int fillYear) {
		dao.delete(id,fillYear);
	}
	public Firewood getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public Firewood getByField(Firewood obj) {
		return dao.getByField(obj);
	}
	public List<Firewood> getByYear(Firewood obj) {
		return dao.getByYear(obj);
	}
	public List<Firewood> getByTown(Firewood obj) {
		return dao.getByTown(obj);
	}
	public void setstatus(int status, int accountid,int fillyear) {
		dao.setstatus(status, accountid,fillyear);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<IntString> getTowns(Firewood firewood) {
		
		return dao.getTowns(firewood);
	}
	public void singleCheck(int id, int status) {
		dao.singleCheck(id, status);
	}
	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}
	public List<IntString> getCitys(Firewood firewood) {
		return dao.getCitys(firewood);
	}
	public void delete2(int cityid, int accountid) {
		dao.delete2(cityid, accountid);
	}
}
