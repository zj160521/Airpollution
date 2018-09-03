package com.xf.dao;

import java.util.List;

import com.xf.entity.OutletPollutant;


public interface IOutletPollutantDao {

	public void add(OutletPollutant o);
	public void delete(int id);
	public void deleteBy(OutletPollutant o);
	public void update(OutletPollutant o);
	public OutletPollutant getById(int id);
	public List<OutletPollutant> getByOutletFill(int outletfillid);
}
