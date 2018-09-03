package com.xf.dao.gov;

import java.util.List;

import com.xf.entity.Proportion;

public interface IProportionDao {

	public void update(List<Proportion> list);
	public void addup(List<Proportion> list);
	public List<Proportion> getAll();
	public List<Proportion> getByName(String name);
	
}