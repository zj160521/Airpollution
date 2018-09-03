package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IAnimalsParamDao;
import com.xf.entity.gov.AnimalsParam;

@Service
public class AnimalsParamService implements IAnimalsParamDao {

	@Autowired
	private IAnimalsParamDao dao;
	
	public void add(AnimalsParam obj) {
		dao.add(obj);
	}
	public void update(AnimalsParam obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public AnimalsParam getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears() {
		return dao.getYears();
	}
	public AnimalsParam getByField(AnimalsParam obj) {
		return dao.getByField(obj);
	}
	public List<AnimalsParam> getByYear(AnimalsParam obj) {
		return dao.getByYear(obj);
	}
	public AnimalsParam getPara(String feedType, String animalType) {
		try {
			return dao.getPara(feedType, animalType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<AnimalsParam> getAll() {
		try {
			return dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
