package com.xf.dao;

import java.util.List;

import com.xf.entity.ProductFill;

public interface IProductFillDao {

	public void add(ProductFill fill);
	public void update(ProductFill fill);
	public List<ProductFill> getByProductId(int pid);
	public List<ProductFill> getByStepId(int stepid);
	public ProductFill getById1(int pfillid);
	public List<ProductFill> getById(int productid, int stepid);
	public List<ProductFill> getByCompany(int companyid);
	public List<ProductFill> getByYear(int year, int companyid);
	public int getRelation(int productid, int stepid);
	public void addRelation(int productid, int stepid);
	public void delRelation(int productid, int stepid);
	public void delBycomIdYear(int comId, int year);
	public void setstatus(int status, int companyid,int fillyear);
	public void setstatus2(int status, int companyid, int orignalStatus);
	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear);
	public void unpass(int status, int id, int orignalStatus);
	public List<ProductFill> getFillid(int id);
	public void deletepfill(int pfillid);
	public List<ProductFill> getSmallpfill(ProductFill pf);
	public void smallSetstatus(int status, int companyid,int fillyear);
	public void smallSetstatus3(int status, int companyid, int orignalStatus,int fillyear);
	public List<ProductFill> getotalYear(int fillyear);
	public void update2(ProductFill fill);
}
