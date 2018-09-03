package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.CompanyFill;
import com.xf.entity.Condition;

public interface ICompanyFillDao {

	public void add(CompanyFill company);
	public void delete(int id);
	public void delBycomIdYear(int comId, int year);
	public void update(CompanyFill company);
	public CompanyFill getById(int id);
	public List<CompanyFill> getByCompanyYear(CompanyFill company);
	public CompanyFill getbyCompanyYear(CompanyFill company);
	public List<Integer> getSmall();
	public List<CompanyFill> getByCompany(int companyid);
	public void setstatus(int status, int companyid,int fillyear);
	public void setstatus2(int status, int companyid, int orignalStatus);
	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear);
	public CompanyFill yearFill(@Param(value="companyid")int companyid,@Param(value="fillyear")int fillyear);
	public List<CompanyFill> yearList();
	public List<CompanyFill> getyearList(Condition con);
	public void changeStatus(int enterpriceId);
	public void smallSetstatus(int status, int id,int fillyear);
	public void smallSetstatus3(int status, int companyid, int orignalStatus,int fillyear);
}
