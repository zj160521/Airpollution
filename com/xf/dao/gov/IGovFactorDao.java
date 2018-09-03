package com.xf.dao.gov;

import java.util.List;
import java.util.Map;

import com.xf.entity.gov.GovFactor;


public interface IGovFactorDao {
	
	public void add(GovFactor obj);
	public void delete(int id);
	public void update(Map<String,String> map);
	public GovFactor getById(int id);
	public List<GovFactor> getByTypeName(String typename);
	public List<GovFactor> getTypex(String typename);//获取有x2的x的个数
	public GovFactor getByThree(String typename,String x,String y,String y2);
	public GovFactor getByFour(String typename,String x,String x2,String y,String y2);
	public List<GovFactor> getAnimal2Factor();
	

}
