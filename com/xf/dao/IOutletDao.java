package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.Condition;
import com.xf.entity.Outlet;


public interface IOutletDao {

	public void add(Outlet o);
	public void delete(int id);
	public void update(Outlet o);
	public Outlet getById(int id);
	public List<Outlet> getByCompany(@Param("companyid")int companyid,@Param("fillyear")int fillyear);
	public List<Outlet> yearList();
	public List<Outlet> getyearList(Condition con);
	public Outlet check(Outlet o);
}
