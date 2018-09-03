package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IAnimalsWildDao;
import com.xf.entity.gov.AnimalsWild;

@Service
public class AnimalsWildService implements IAnimalsWildDao {

	@Autowired
	private IAnimalsWildDao dao;
	
	public void add(AnimalsWild obj) {
		dao.add(obj);
	}
	public void update(AnimalsWild obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public AnimalsWild getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears(int accountid) {
		return dao.getYears(accountid);
	}
	public AnimalsWild getByField(AnimalsWild obj) {
		return dao.getByField(obj);
	}
	public List<AnimalsWild> getByYear(AnimalsWild obj) {
		return dao.getByYear(obj);
	}
	public void setstatus(int status, int accountid) {
		dao.setstatus(status, accountid);
	}
	public void setstatus2(int status, int accountid, int originStatus) {
		dao.setstatus2(status, accountid, originStatus);
	}
	public List<AnimalsWild> getAll() {
		try {
			return dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<AnimalsWild> statAll(int year) {
		try {
			return dao.statAll(year);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void clearData(AnimalsWild obj) {
		dao.clearData(obj);
	}
	public List<AnimalsWild> statAll2(int year) {
		return dao.statAll2(year);
	}
}
