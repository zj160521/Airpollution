package com.xf.dao;

import java.util.List;

import com.xf.entity.ActRecords;

public interface IActRecordsDao {

	public void add(ActRecords obj);
	public void delete(int id);
	public void update(ActRecords obj);
	public ActRecords getById(int id);
	public List<ActRecords> getAll();
	public List<ActRecords> getByProcessInstanceId(int processInstanceId);
	public List<ActRecords> getByAct(ActRecords obj);
}