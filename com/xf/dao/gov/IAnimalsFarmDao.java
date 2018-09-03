package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.AnimalsFarm;

public interface IAnimalsFarmDao {

	public void add(AnimalsFarm obj);
	public void delete(int id);
	public void update(AnimalsFarm obj);
	public AnimalsFarm getById(int id);
	public List<Integer> getYears(int accountid);
	public AnimalsFarm getByField(AnimalsFarm obj);
	public List<AnimalsFarm> getByYear(AnimalsFarm obj);
	public void clearData(AnimalsFarm obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public List<AnimalsFarm> getAll();
	public List<AnimalsFarm> statAll(int year);
	public List<AnimalsFarm> statAll2(int year);
}