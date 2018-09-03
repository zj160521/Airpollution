package com.xf.dao;

import java.util.List;

import com.xf.entity.Enterprise;

public interface IEnterpriseDao {

	public List<Enterprise> getAll(int issmall,int in);

}
