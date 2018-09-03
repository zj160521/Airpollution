package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.gov.AnimalsWild;

public interface IAnimalsWildDao {

	public void add(AnimalsWild obj);
	public void delete(int id);
	public void update(AnimalsWild obj);
	public AnimalsWild getById(int id);
	public List<Integer> getYears(int accountid);
	public AnimalsWild getByField(AnimalsWild obj);
	public List<AnimalsWild> getByYear(AnimalsWild obj);
	public void setstatus(int status, int accountid);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public List<AnimalsWild> getAll();
	public List<AnimalsWild> statAll(int year);
	public List<AnimalsWild> statAll2(int year);
	public void clearData(AnimalsWild obj);
}