package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IPesticideDao;
import com.xf.entity.gov.Pesticide;

@Service
public class PesticideService extends BaseService implements IPesticideDao {

	@Autowired
	private IPesticideDao dao;

	public void add(Pesticide obj) {
		dao.add(obj);
	}

	public void update(Pesticide obj) {
		dao.update(obj);
	}

	public void delete(int id, int fillYear) {
		dao.delete(id,fillYear);
	}

	public Pesticide getById(int id) {
		return dao.getById(id);
	}

	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}

	public Pesticide getByField(Pesticide obj) {
		return dao.getByField(obj);
	}

	public List<Pesticide> getByYear(Pesticide obj) {
		try {
			return dao.getByYear(obj);
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

	public List<Pesticide> getByTown(int id, int fillYear) {
		try {
			return dao.getByTown(id, fillYear);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getTowns(int accountid, int fillYear) {
		try {
			return dao.getTowns(accountid, fillYear);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Pesticide> getCheck(int town, int accountid, int fillYear) {
		try {
			return dao.getCheck(town, accountid, fillYear);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setstatus3(int status, int accountid, int orignalStatus,
			int fillYear) {
		dao.setstatus3(status, accountid, orignalStatus, fillYear);
	}

	public void delete2(int cityid, int accountid) {
		dao.delete2(cityid, accountid);
	}

	public List<Integer> getCitys(int accountid, int fillYear) {
		return dao.getCitys(accountid, fillYear);
	}

}
