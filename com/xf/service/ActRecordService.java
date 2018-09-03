package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IActRecordsDao;
import com.xf.entity.ActRecords;


@Service
public class ActRecordService implements IActRecordsDao {

	@Autowired
	private IActRecordsDao dao;

	public void add(ActRecords obj) {
		dao.add(obj);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public void update(ActRecords obj) {
		dao.update(obj);
	}

	public ActRecords getById(int id) {
		return dao.getById(id);
	}

	public List<ActRecords> getAll() {
		return dao.getAll();
	}

	public List<ActRecords> getByProcessInstanceId(int processInstanceId) {
		return dao.getByProcessInstanceId(processInstanceId);
	}

	public List<ActRecords> getByAct(ActRecords obj) {
		return dao.getByAct(obj);
	}
	
	
}
