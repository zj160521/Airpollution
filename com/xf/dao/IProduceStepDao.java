package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.Condition;
import com.xf.entity.ProduceStep;

public interface IProduceStepDao {

	public void add(ProduceStep step);
	public void delete(int id);
	public void update(ProduceStep step);
	public ProduceStep getById(@Param(value="id")int id,@Param(value="fillyear")int fillyear);
	public ProduceStep getById1(int id);
	public List<ProduceStep> getByCompany(int fillyear,int companyid);

	public List<ProduceStep> getByFacility(@Param(value="facilityid")int facilityid,@Param(value="fillyear")int fillyear);
	public List<ProduceStep> yearList();
	public List<ProduceStep> getyearList(Condition con);
	public ProduceStep getBySerial(ProduceStep step);
}
