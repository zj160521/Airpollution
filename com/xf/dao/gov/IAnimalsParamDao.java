package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.AnimalsParam;

public interface IAnimalsParamDao {

	public void add(AnimalsParam obj);
	public void delete(int id);
	public void update(AnimalsParam obj);
	public AnimalsParam getById(int id);
	public List<Integer> getYears();
	public AnimalsParam getByField(AnimalsParam obj);
	public List<AnimalsParam> getByYear(AnimalsParam obj);
	public AnimalsParam getPara(String feedType,String animalType);	
	public List<AnimalsParam> getAll();
}