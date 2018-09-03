package com.xf.dao.gov;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.gov.Factor;

public interface IFactorDao {

	public void add(Factor obj);
	public void delete(int id);
	public void update(Factor obj);
	public void updateIsFollow(Factor obj);
	public Factor getById(int id);
	public List<Integer> getYears();
	public Factor getByField(Factor obj);
	public List<Factor> getByYear(Factor obj);
	public Factor getByIdId(Factor obj);
	public List<Factor> findFactor(int productid,int pollutantid);
	public List<Factor> findFactorBygroupId(int groupid,int pollutantid);
	public void addGroupfactor(List<Factor> list);
	public void addFactor(Factor factor);
	public void deleteGrf(int groupid);
	public void deleteFacBypid(int productid);
	public List<Factor> getSurefactor(int pollutantid, int groupid);
	public List<Factor> getAll();
	public List<Factor> getProductfactor(@Param("groupid") int groupid, @Param("pollutantid") int pollutantid);
	public List<Factor> getFuelfactor(int typeid);
}