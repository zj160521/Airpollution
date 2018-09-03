package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IAnimalsFarmDao;
import com.xf.entity.gov.AnimalsFarm;

@Service
public class AnimalsFarmService implements IAnimalsFarmDao {

	@Autowired
	private IAnimalsFarmDao dao;
	
	public void add(AnimalsFarm obj) {
		dao.add(obj);
	}
	public void update(AnimalsFarm obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public AnimalsFarm getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public AnimalsFarm getByField(AnimalsFarm obj) {
		return dao.getByField(obj);
	}
	public List<AnimalsFarm> getByYear(AnimalsFarm obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<AnimalsFarm> getAll() {
		try {
			return dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<AnimalsFarm> statAll(int year) {
		try {
			return dao.statAll(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void clearData(AnimalsFarm obj) {
		dao.clearData(obj);
	}
	public List<AnimalsFarm> statAll2(int year) {
		return dao.statAll2(year);
	}
}
